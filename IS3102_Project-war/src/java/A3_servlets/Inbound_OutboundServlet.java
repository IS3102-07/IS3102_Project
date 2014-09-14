/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A3_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.ShippingOrderEntity;
import EntityManager.WarehouseEntity;
import SCM.InboundAndOutboundLogistics.InboundAndOutboundLogisticsBeanLocal;
import SCM.InboundAndOutboundLogistics.LineItemModel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class Inbound_OutboundServlet extends HttpServlet {
    
    @EJB
    private ItemManagementBeanLocal imBean;
    @EJB
    private FacilityManagementBeanLocal fmBean;
    @EJB
    private InboundAndOutboundLogisticsBeanLocal ioBean;
        

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nextPage = "shippingOrderBasicInfo";
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        String target = request.getPathInfo();                
        
        switch (target) {
            
            case "/createShippingOrderBasicInfo_GET":                                
                List<WarehouseEntity> warehouseList = fmBean.getWarehouseList();
                request.setAttribute("warehouseList", warehouseList);
                nextPage = "/A3/createShippingOrderBasicInfo";
                break;
            
            case "/createShippingOrderBasicInfo_POST":
                String shippedType = request.getParameter("shippedType");
                String origin = request.getParameter("origin");
                String destination = request.getParameter("destination");
                String shippedDateString = request.getParameter("shippedDate");
                String expectedReceivedDateString = request.getParameter("expectedReceivedDate");

                WarehouseEntity originWarehouse = fmBean.getWarehouseByName(origin);
                WarehouseEntity destinationWarehouse = fmBean.getWarehouseByName(destination);
                fmBean.remove();

                try {
                    Date shippedDate = dateFormatter.parse(shippedDateString);
                    Date expectedReceivedDate = dateFormatter.parse(expectedReceivedDateString);
                    ShippingOrderEntity shippingOrder = ioBean.createShippingOrderBasicInfo(shippedType, shippedDate, expectedReceivedDate, originWarehouse, destinationWarehouse);
                    if (shippingOrder != null) {
                        HttpSession httpSession = request.getSession();
                        httpSession.setAttribute("currentShippingOrderId", shippingOrder.getId());
                        request.setAttribute("alterMessage", "The baisc information has been saved.");
                        nextPage = "/A3/createShippingOrder_LineItems";
                    } else {
                        System.out.println("Fail to create shipping order basic info.");
                    }
                } catch (Exception ex) {
                    System.out.println("+++++ Shipped Date or expectedReceivedDate parse error +++++");
                }
                break;

            case "/createShippingOrder_LineItems_GET":
                HttpSession httpSession = request.getSession();
                Long currentShippingOrderId = (long) httpSession.getAttribute("currentShippingOrderId");
                
                ShippingOrderEntity shippingOrder = ioBean.getShippingOrderById(currentShippingOrderId);
                List<LineItemEntity> lineItemList = shippingOrder.getLineItems();
                
//                List<LineItemModel> modelList = new ArrayList<>();
//                
//                for(LineItemEntity lineItem: lineItemList){
//                    LineItemModel model = new LineItemModel();
//                    model.lineItem = lineItem;
//                    model.item = lineItem.getItem();
//                }                
                
                request.setAttribute("lineItemList", lineItemList);
                
                nextPage = "/A3/createShippingOrder_LineItems";
                break;
            
            case "/addLineItemToShippingOrder_POST":
                
                String itemCode = request.getParameter("itemCode");
                ItemEntity item = imBean.getItemBySKU(itemCode);
                imBean.remove();
                String quantityString = request.getParameter("quantity");
                Integer quantity = Integer.parseInt(quantityString);
                String packType = request.getParameter("packType");
                
                httpSession = request.getSession();
                currentShippingOrderId = (long) httpSession.getAttribute("currentShippingOrderId");
                
                LineItemEntity lineItem = new LineItemEntity(item, quantity, packType);
                
                ioBean.addLineItemToShippingOrder(currentShippingOrderId, lineItem);
                
                nextPage = "/A3/createShippingOrder_LineItems";
                break;
                
            case "/removeLineItemToShippingOrder_POST":
                
                nextPage = "/A3/createShippingOrder_LineItems";
                break;
                
            case "/createShippingOrder_LineItems_POST":
                
                nextPage = "/A3/createShippingOrder_LineItems";
                break;
        }        
        dispatcher = servletContext.getRequestDispatcher(nextPage);        
        dispatcher.forward(request, response);        
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
