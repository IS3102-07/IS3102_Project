package B_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.MemberEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ECommerce_MemberEditProfileServlet extends HttpServlet {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            System.out.println("ECommerce_MemberEditProfileServlet:");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String id = request.getParameter("id");
            String country = request.getParameter("country");
            if (country == null) {
                country = "";
            }
            String address = request.getParameter("address");
            String securityQuestion = request.getParameter("securityQuestion");
            String securityAnswer = request.getParameter("securityAnswer");
            String ageString = request.getParameter("age");
            Integer age = null;
            if (ageString == "") {
                ageString = null;
            } else {
                age = Integer.parseInt(ageString);
            }
            String incomeString = request.getParameter("income");
            Integer income = null;
            if (incomeString == "") {
                incomeString = null;
            } else {
                income = Integer.parseInt(incomeString);
            }

            boolean test = accountManagementBean.editMember(Long.valueOf(id), null, name, address, email, phone, null, country, null, password, Integer.valueOf(securityQuestion), securityAnswer);
            accountManagementBean.editMemberAgeAndIncome(Long.valueOf(id), age, income);
            HttpSession session;
            session = request.getSession();
            MemberEntity memberEntity = accountManagementBean.getMemberByEmail(email);
            session.setAttribute("member", memberEntity);
            String URLprefix = (String) session.getAttribute("URLprefix");
            if (URLprefix == null) {
                URLprefix = "";
            }
            if (test) {
                result = "Account updated successfully.";
                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "memberProfile.jsp?goodMsg=" + result);
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
