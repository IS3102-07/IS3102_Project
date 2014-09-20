package A1_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.RoleEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StaffManagement_UpdateStaffServlet extends HttpServlet {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    private String result ="";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session;
            session = request.getSession();
            String update = request.getParameter("update");
            if (update != null && update.equals("yes")) {
                String staffId = request.getParameter("id");
                String identificationNo = request.getParameter("identificationNo");
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                
                //TODO WHY IS THIS PART NOT GETTING ROLES????
                String[] roles = request.getParameterValues("roles");
                if(roles==null){
                    System.out.println("JIALAT LIAOOO");
                }
                List<Long> roleIDs = new ArrayList();
                for (int i = 0; i < roles.length; i++) {
                    roleIDs.add(Long.parseLong(roles[i]));
                }
                boolean canUpdateRoles = accountManagementBean.editStaffRole(Long.parseLong(staffId), roleIDs);
                boolean canUpdateInfo = accountManagementBean.editStaff(Long.parseLong(staffId), identificationNo, name, phone, password, address);
                if (!canUpdateInfo) {
                    result += "&errMsg=Error updating staff particulars.";
                }
                if (!canUpdateRoles) {
                    result += "&errMsg=Error updating staff role(s).";
                }
                if (!canUpdateInfo || !canUpdateRoles) {
                    response.sendRedirect("A1/staffManagement_update.jsp?" + result);
                } else {
                    result = "?errMsg=Staff updated successfully.";
                    response.sendRedirect("StaffManagement_StaffServlet" + result);
                }
            } else {
                response.sendRedirect("A1/staffManagement_update.jsp");
                session.setAttribute("staffUpdateId", request.getParameter("id"));
                List<RoleEntity> staffUpdateRoles = (List<RoleEntity>) accountManagementBean.listRolesHeldByStaff(Long.parseLong(request.getParameter("id")));
                session.setAttribute("staffUpdateRoles", staffUpdateRoles);
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
