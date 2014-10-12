package B_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CommonInfrastructure.SystemSecurity.SystemSecurityBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

public class ECommerce_MemberRegisterServlet extends HttpServlet {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    private String result;
    
    @EJB
    private SystemSecurityBeanLocal systemSecurityBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            boolean isExist = accountManagementBean.checkMemberEmailExists(email);
            String source = request.getParameter("source");
            
            if (isExist) {
                result = "Email already exist. Please try again.";
                response.sendRedirect("B/memberLogin.jsp?errMsg=" + result);
            } else {
                boolean canUpdate = accountManagementBean.registerMember(null, null, null, email, null, null, null, null, password);
                if (!canUpdate) {
                    result = "Register failed. Please try again.";
                    response.sendRedirect("B/memberLogin.jsp?errMsg=" + result);
                } else {
                    result = "Account successfully registered.";
                    response.sendRedirect("B/memberLogin.jsp?goodMsg=" + result);
                    
                    String remoteAddr = request.getRemoteAddr();
                    ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
                    reCaptcha.setPrivateKey("6LdjyvoSAAAAAHnUl50AJU-edkUqFtPQi9gCqDai");

                    String challenge = request.getParameter("recaptcha_challenge_field");
                    String uresponse = request.getParameter("recaptcha_response_field");
                    ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

                    if (reCaptchaResponse.isValid()) {
                        accountManagementBean.registerMember(null, null, null, email, null, null, null, null, password);
                        systemSecurityBean.sendActivationEmailForMember(email);
                        result = "?goodMsg=Staff added successfully.";
                        response.sendRedirect(source + result);
                    } else {
                        result = "?errMsg=You have entered an wrong Captcha code.";
                        response.sendRedirect("A1/staffRegister.jsp" + result);
                    }
                }
            }
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
