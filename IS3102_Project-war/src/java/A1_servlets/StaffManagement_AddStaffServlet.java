package A1_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CommonInfrastructure.SystemSecurity.SystemSecurityBeanLocal;
import EntityManager.StaffEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaffManagement_AddStaffServlet extends HttpServlet {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    private String result;

    @EJB
    private SystemSecurityBeanLocal systemSecurityBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String identificationNo = request.getParameter("identificationNo");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String source = request.getParameter("source");

            boolean ifExist = accountManagementBean.checkStaffEmailExists(email);
            if (ifExist) {
                result = "?errMsg=Registration fail. Staff email already registered.";
                response.sendRedirect(source + result);
            } else {
                StaffEntity staffEntity = accountManagementBean.registerStaff(identificationNo, name, phone, email, address, password);
                System.out.println("Reached here1" + email);
                System.out.println("Reached here2");

                Boolean testSendMail = systemSecurityBean.sendActivationEmailForStaff(email);

                if (testSendMail) {
                    System.out.println("Successfully sent email to staff email : " + email);
                }
                result = "?errMsg=Staff added successfully.";
                if (source.equals("A1/staffManagement_add.jsp")) {
                    response.sendRedirect("StaffManagement_StaffServlet" + result);
                }
                response.sendRedirect(source);
            }
        } catch (Exception ex) {
            out.println(ex);
        } finally {
            out.close();
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
