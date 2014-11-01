/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package A5_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import CommonInfrastructure.SystemSecurity.SystemSecurityBeanLocal;
import javax.ejb.EJB;

public class Analytical_ValueAnalysisSendLoyaltyServlet extends HttpServlet {

    @EJB
    SystemSecurityBeanLocal systemSecurityBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            
            String[] deleteArr = request.getParameterValues("delete");
            if (deleteArr != null) {
                for (int i = 0; i < deleteArr.length; i++) {
                    systemSecurityBean
                }
                response.sendRedirect("StaffManagement_StaffServlet?goodMsg=Successfully removed: " + deleteArr.length + " record(s).");
            } else {
                response.sendRedirect("A1/staffManagement.jsp?errMsg=Nothing is selected.");
            }
        } catch (Exception ex) {
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
