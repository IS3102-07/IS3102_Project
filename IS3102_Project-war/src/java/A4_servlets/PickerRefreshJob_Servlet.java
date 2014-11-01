package A4_servlets;

import EntityManager.LineItemEntity;
import EntityManager.PickRequestEntity;
import EntityManager.PickerEntity;
import EntityManager.StorageBinEntity;
import InventoryManagement.StoreAndKitchenInventoryManagement.StoreAndKitchenInventoryManagementBeanLocal;
import OperationalCRM.CustomerService.CustomerServiceBeanLocal;
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

public class PickerRefreshJob_Servlet extends HttpServlet {

    @EJB
    CustomerServiceBeanLocal customerServiceBeanLocal;

    @EJB
    StoreAndKitchenInventoryManagementBeanLocal storeAndKitchenInventoryManagementBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session;
            session = request.getSession();

            PickerEntity picker = (PickerEntity) (session.getAttribute("picker"));

            List<PickRequestEntity> pickRequestLinkedList = customerServiceBeanLocal.getPickRequests(picker.getId());
            session.setAttribute("pickRequestLinkedList", pickRequestLinkedList);

            if (pickRequestLinkedList != null && pickRequestLinkedList.size() > 0) {
                Long warehouseID = picker.getStore().getWarehouse().getId();
                List<LineItemEntity> itemsToBePicked = pickRequestLinkedList.get(0).getItems();
                List<List<StorageBinEntity>> storageBinsList = new ArrayList<>();
                for (int i = 0; i < itemsToBePicked.size(); i++) {
                    String SKU = itemsToBePicked.get(i).getItem().getSKU();
                    List<StorageBinEntity> storageBinsThatHasCurrentSKU = storeAndKitchenInventoryManagementBean.findRetailStorageBinsThatContainsItem(warehouseID, SKU);
                    storageBinsList.add(storageBinsThatHasCurrentSKU);
                }
                session.setAttribute("storageBinsList", storageBinsList);
                response.sendRedirect("A4/pickerStartJob.jsp");
            } else {
                response.sendRedirect("A4/pickerLogin_waiting.jsp");
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
