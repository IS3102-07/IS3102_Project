package A1_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaffManagement_UpdateStaffServlet extends HttpServlet {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String staffId = request.getParameter("id");
            String staffEmail = request.getParameter("staffEmail");
            String identificationNo = request.getParameter("identificationNo");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String source = request.getParameter("source");

            boolean ifExist = accountManagementBean.checkStaffEmailExists(email);
            if (ifExist) {
                if (!email.equals(staffEmail)) {
                    result = "?errMsg=Update failed. Staff email already registered.";
                    response.sendRedirect(source + result);
                }
                accountManagementBean.editStaff(Long.parseLong(staffId), identificationNo, name, phone, password, address, email);
                response.sendRedirect("StaffManagement_StaffServlet");
            } else {
                boolean canUpdate = accountManagementBean.editStaff(Long.parseLong(staffId), identificationNo, name, phone, password, address, email);

                if (!canUpdate) {
                    result = "?errMsg=Please try again.";
                    response.sendRedirect(source + result);
                } else {
                    response.sendRedirect("StaffManagement_StaffServlet");
                }
            }

        } catch (Exception ex) {
            out.println(ex);

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