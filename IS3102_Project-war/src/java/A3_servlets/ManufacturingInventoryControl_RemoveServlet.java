package A3_servlets;

import SCM.ManufacturingInventoryControl.ManufacturingInventoryControlBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManufacturingInventoryControl_RemoveServlet extends HttpServlet {

    @EJB
    private ManufacturingInventoryControlBeanLocal manufacturingInventoryControlBeanLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try {

            String storageBin_ItemID = request.getParameter("storageBin_ItemID");
            String storageBinID = request.getParameter("storageBinID");
            
            if (storageBinID != null && storageBin_ItemID!= null) {
                manufacturingInventoryControlBeanLocal.emptyStorageBin_ItemEntity(Long.parseLong(storageBin_ItemID), Long.parseLong(storageBinID));
                response.sendRedirect("ManufacturingInventoryControl_Servlet?errMsg=Successfully removed all instance of the selected item from storage bin.");
            } else {
                response.sendRedirect("A3/manufacturingInventoryControlManagement.jsp?errMsg=Nothing is selected.");
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
