package B_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.Item_CountryEntity;
import EntityManager.StoreEntity;
import EntityManager.WarehouseEntity;
import InventoryManagement.StoreAndKitchenInventoryManagement.StoreAndKitchenInventoryManagementBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ECommerce_StockAvailability extends HttpServlet {

    @EJB
    private StoreAndKitchenInventoryManagementBeanLocal skimb;
    @EJB
    private FacilityManagementBeanLocal fmb;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session;
            session = request.getSession();
            String URLprefix = (String) session.getAttribute("URLprefix");
            if (URLprefix == null) {
                response.sendRedirect("/IS3102_Project-war/B/selectCountry.jsp");
            }
            String storeIDstring = (String) request.getParameter("storeID");
            String SKU = (String) request.getParameter("sku");
            if ((storeIDstring == null || storeIDstring.equals(""))&& (SKU == null || SKU.equals(""))) {
                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "index.jsp");
            } else if (storeIDstring == null || storeIDstring.equals("")) {
                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "productDetails.jsp?sku=" + SKU);
            }
            Long storeID = Long.parseLong(storeIDstring);
            StoreEntity storeEntity = fmb.getStoreByID(storeID);
            WarehouseEntity warehouseEntity = storeEntity.getWarehouse();
            String itemQty = skimb.checkItemQty(warehouseEntity.getId(), SKU) + "";
            session.setAttribute("storeEntity", storeEntity);

            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "productDetails.jsp?sku=" + SKU + "&itemQty=" + itemQty + "&storeID=" + storeEntity.getId());

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
