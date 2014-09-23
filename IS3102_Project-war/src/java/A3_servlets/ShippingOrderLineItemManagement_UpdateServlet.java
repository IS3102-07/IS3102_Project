package A3_servlets;

import SCM.InboundAndOutboundLogistics.InboundAndOutboundLogisticsBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShippingOrderLineItemManagement_UpdateServlet extends HttpServlet {

    @EJB
    private InboundAndOutboundLogisticsBeanLocal inboundAndOutboundLogisticsBeanLocal;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String shippingOrderId = request.getParameter("id");
            String lineItemId = request.getParameter("lineitemId");
            String sku = request.getParameter("sku");
            String quantity = request.getParameter("quantity");
            String status1 = request.getParameter("status1");
            String status3 = request.getParameter("status3");
            if (status1 != null) {
                boolean canUpdate = inboundAndOutboundLogisticsBeanLocal.updateShippingOrderStatus(Long.parseLong(shippingOrderId), "Submitted");
                if (!canUpdate) {
                    result = "?source=isSubmit&errMsg=Failed to submit Shipping Order.&id=" + shippingOrderId + "&lineItemId=" + lineItemId;
                    response.sendRedirect("A3/shippingOrderManagement_UpdateLineItem.jsp" + result);
                } else {
                    result = "?errMsg=Shipping Order submitted successfully.&id=" + shippingOrderId;
                    response.sendRedirect("ShippingOrderLineItemManagement_Servlet" + result);
                }

            } else if (status3 != null) {

                boolean canUpdate = inboundAndOutboundLogisticsBeanLocal.updateShippingOrderStatus(Long.parseLong(shippingOrderId), status3);
                result = "?errMsg=Shipping Order updated successfully.&id=" + shippingOrderId;
                response.sendRedirect("ShippingOrderLineItemManagement_Servlet" + result);
            } else {
                if (!inboundAndOutboundLogisticsBeanLocal.checkSKUExists(sku)) {
                    result = "?errMsg=SKU not found.&id=" + shippingOrderId + "&lineItemId=" + lineItemId;
                    response.sendRedirect("A3/shippingOrderManagement_UpdateLineItem.jsp" + result);
                    boolean canUpdate = inboundAndOutboundLogisticsBeanLocal.updateLineItemFromShippingOrder(Long.parseLong(shippingOrderId), Long.parseLong(lineItemId), sku, Integer.parseInt(quantity));
                    if (!canUpdate) {
                        result = "?errMsg=Shipping Order not found.&id=" + shippingOrderId + "&lineItemId=" + lineItemId;
                        response.sendRedirect("A3/shippingOrderManagement_UpdateLineItem.jsp" + result);
                    } else {
                        result = "?errMsg=Line Item updated successfully.&id=" + shippingOrderId;
                        response.sendRedirect("ShippingOrderLineItemManagement_Servlet" + result);
                    }
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
