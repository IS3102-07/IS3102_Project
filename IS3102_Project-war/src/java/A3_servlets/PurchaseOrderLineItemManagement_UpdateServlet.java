package A3_servlets;

import SCM.RetailProductsAndRawMaterialsPurchasing.RetailProductsAndRawMaterialsPurchasingBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseOrderLineItemManagement_UpdateServlet extends HttpServlet {

    @EJB
    private RetailProductsAndRawMaterialsPurchasingBeanLocal retailProductsAndRawMaterialsPurchasingBean;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String purchaseOrderId = request.getParameter("id");
            String lineItemId = request.getParameter("lineitemId");
            String sku = request.getParameter("sku");
            String quantity = request.getParameter("quantity");

            boolean canUpdate = retailProductsAndRawMaterialsPurchasingBean.updateLineItemFromPurchaseOrder(Long.parseLong(purchaseOrderId), Long.parseLong(lineItemId), sku, Integer.parseInt(quantity));
            if (!canUpdate) {
                result = "?errMsg=Purchase Order or SKU not found.&id=" + purchaseOrderId + "&lineItemId=" + lineItemId;
                response.sendRedirect("A3/purchaseOrderManagement_UpdateLineItem.jsp" + result);
            } else {
                result = "?errMsg=Line Item updated successfully.&id=" + purchaseOrderId;
                response.sendRedirect("PurchaseOrderLineItemManagement_Servlet" + result);
            }

            //retailProductsAndRawMaterialsPurchasingBean.updateLineItemFromPurchaseOrder(Long.MIN_VALUE, Long.MIN_VALUE, lineItemId, Integer.SIZE)
        } catch (Exception ex) {
            out.println("\n\n " + ex.getMessage());
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
