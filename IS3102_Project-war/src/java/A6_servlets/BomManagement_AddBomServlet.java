/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A6_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import javax.ejb.EJB;

/**
 *
 * @author Neo
 */
public class BomManagement_AddBomServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBeanLocal;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String source = request.getParameter("source");

            boolean ifExist = false;
//            if (itemManagementBeanLocal.viewBillOfMaterial(name).getName().equals(name)) {
//                ifExist = true;
//            } //to be fixed
            if (ifExist) {
                result = "?errMsg=Registration fail. BOM already registered.";
                response.sendRedirect(source + result);
            } else {
                itemManagementBeanLocal.createBOM(name, description);
                if (source.equals("A1/staffManagement_add.jsp")) {
                    response.sendRedirect("StaffManagement_StaffServlet");
                }
                response.sendRedirect(source);
            }
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
