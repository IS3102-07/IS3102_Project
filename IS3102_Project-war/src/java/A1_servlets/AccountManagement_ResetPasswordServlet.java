/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CommonInfrastructure.SystemSecurity.SystemSecurityBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountManagement_ResetPasswordServlet extends HttpServlet {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    @EJB
    private SystemSecurityBeanLocal systemSecurityBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session;
            session = request.getSession();

            String email = request.getParameter("email");
            String resetCode = request.getParameter("resetCode");
            String accountType = request.getParameter("accountType");
            if (email == null || resetCode == null || accountType == null) {
                if (accountType != null && accountType.equals("member")) {
                    response.sendRedirect("A1/memberResetAccount.jsp");
                } else if (accountType != null && accountType.equals("staff")) {
                    response.sendRedirect("A1/staffResetAccount.jsp");
                } else {
                    response.sendRedirect("A1/error.jsp");
                }
            } else {
                if (accountType.equals("staff")) {
                    if (systemSecurityBean.validatePasswordResetForStaff(email, resetCode)) {

                    } else {

                    }
                } else if (accountType.equals("member")) {
                    if (systemSecurityBean.validatePasswordResetForMember(email, resetCode)) {

                    } else {

                    }
                }
            }

        } catch (Exception ex) {
            response.sendRedirect("A1/error.jsp?errMsg="+ex);
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
