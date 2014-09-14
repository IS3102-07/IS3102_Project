package A1_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleManagement_UpdateRoleServlet extends HttpServlet {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String roleId = request.getParameter("id");
            String roleName = request.getParameter("roleName");
            String name = request.getParameter("name");
            String accessLevel = request.getParameter("accessLevel");
            String source = request.getParameter("source");
            boolean ifExist = accountManagementBean.checkIfRoleExists(name);
            if (ifExist) {
                if (!name.equals(roleName)) {
                    result = "?errMsg=Role already exist.";
                    response.sendRedirect(source + result);
                }
                accountManagementBean.updateRole(Long.parseLong(roleId), name, accessLevel);
                response.sendRedirect("RoleManagement_RoleServlet");
            } else {
                boolean canUpdate = accountManagementBean.updateRole(Long.parseLong(roleId), name, accessLevel);
                if (!canUpdate) {
                    result = "?errMsg=Please try again.";
                    response.sendRedirect(source + result);
                } else {
                    response.sendRedirect("RoleManagement_RoleServlet");
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