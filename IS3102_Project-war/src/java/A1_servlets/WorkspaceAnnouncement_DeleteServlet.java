package A1_servlets;

import CommonInfrastructure.Workspace.WorkspaceBeanLocal;
import EntityManager.StaffEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WorkspaceAnnouncement_DeleteServlet extends HttpServlet {

    @EJB
    private WorkspaceBeanLocal workspaceBeanLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String result;
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            StaffEntity currentLoggedInStaffEntity = (StaffEntity) session.getAttribute("staffEntity");
            String currentLoggedInStaffID;
            if (currentLoggedInStaffEntity == null) {
                currentLoggedInStaffID = "System";
            } else {
                currentLoggedInStaffID = currentLoggedInStaffEntity.getId().toString();
            }
            String[] deleteArr = request.getParameterValues("delete");
            if (deleteArr != null) {
                for (int i = 0; i < deleteArr.length; i++) {
                    workspaceBeanLocal.deleteAnnouncement(currentLoggedInStaffID, Long.parseLong(deleteArr[i]));
                }
                response.sendRedirect("WorkspaceAnnouncement_Servlet?goodMsg=Successfully removed: " + deleteArr.length + " record(s).");
            } else {
                response.sendRedirect("A1/workspace_viewAnnouncement.jsp?errMsg=Nothing is selected.");
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
