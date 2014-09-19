package A1_servlets;

import CommonInfrastructure.Workspace.WorkspaceBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkspaceAnnouncement_UpdateServlet extends HttpServlet {

    @EJB
    private WorkspaceBeanLocal workspaceBeanLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String result;
        try {
            String announcementId = request.getParameter("id");
            String message = request.getParameter("message");
            Long expiryDateLong = Date.parse(request.getParameter("expiryDate"));
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(expiryDateLong);
            Date expiryDate = cal.getTime();
            boolean canUpdate = workspaceBeanLocal.updateAnnouncement(Long.valueOf(announcementId), message, expiryDate);
            if (!canUpdate) {
                result = "?errMsg=Please try again.";
                response.sendRedirect("roleManagement_update.jsp" + result);
            } else {
                result = "?errMsg=Role updated successfully.";
                response.sendRedirect("RoleManagement_RoleServlet" + result);
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
