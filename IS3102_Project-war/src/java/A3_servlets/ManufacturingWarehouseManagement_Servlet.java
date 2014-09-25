package A3_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.WarehouseEntity;
import SCM.ManufacturingInventoryControl.ManufacturingInventoryControlBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManufacturingWarehouseManagement_Servlet extends HttpServlet {

    @EJB
    private FacilityManagementBeanLocal facilityManagementBeanLocal;
    private ManufacturingInventoryControlBeanLocal micbl;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session;
            session = request.getSession();
            String errMsg = request.getParameter("errMsg");
            String destination = request.getParameter("destination");
            String warehouseId = request.getParameter("id");

            if (destination != null && warehouseId != null) {

                int[] warehousesCapcity = new int[4];
                System.out.println(warehouseId + "<<<<<<<<<<<<<<");
//                int totalPallet = micbl.getTotalVolumeOfPalletStorageBin(Long.parseLong(warehouseId));
//                int freePallet = micbl.getTotalFreeVolumeOfPalletStorageBin(Long.parseLong(warehouseId));
//                int totalShelf = micbl.getTotalVolumeOfShelfStorageBin(Long.parseLong(warehouseId));
//                int freeShelf = micbl.getTotalFreeVolumeOfShelfStorageBin(Long.parseLong(warehouseId));
//                int totalInbound = micbl.getTotalVolumeOfInboundStorageBin(Long.parseLong(warehouseId));
//                int freeInbound = micbl.getTotalFreeVolumeOfInboundStorageBin(Long.parseLong(warehouseId));
//                int totalOutbound = micbl.getTotalVolumeOfOutboundStorageBin(Long.parseLong(warehouseId));
//                int freeOutbound = micbl.getTotalFreeVolumeOfOutboundStorageBin(Long.parseLong(warehouseId));
//
//                warehousesCapcity[0] = ((totalPallet - freePallet) * 100) / totalPallet;
//                warehousesCapcity[1] = ((totalShelf - freeShelf) * 100) / totalShelf;
//                warehousesCapcity[2] = ((totalInbound - freeInbound) * 100) / totalInbound;
//                warehousesCapcity[3] = ((totalOutbound - freeOutbound) * 100) / totalOutbound;

                //session.setAttribute("warehousesCapcity", warehousesCapcity);

                if (destination.equals("manufacturingWarehouseManagement.jsp")) {
                    WarehouseEntity warehouseEntity = facilityManagementBeanLocal.getWarehouseById(Long.parseLong(warehouseId));
                    session.setAttribute("warehouseEntity", warehouseEntity);
                    response.sendRedirect("A3/manufacturingWarehouseManagement.jsp");
                }
            } else {
                List<WarehouseEntity> warehouses = facilityManagementBeanLocal.getWarehouseList();
                session.setAttribute("warehouses", warehouses);

                if (errMsg == null || errMsg.equals("")) {
                    response.sendRedirect("A3/manufacturingWarehouseManagement_view.jsp");
                } else {
                    response.sendRedirect("A3/manufacturingWarehouseManagement_view.jsp?errMsg=" + errMsg);
                }
            }
        } catch (Exception ex) {
            out.println("\n\n " + ex.getMessage());
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
