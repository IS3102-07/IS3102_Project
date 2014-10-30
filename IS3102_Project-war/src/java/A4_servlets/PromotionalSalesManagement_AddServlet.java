package A4_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import OperationalCRM.PromotionalSales.PromotionalSalesBeanLocal;
import SCM.InboundAndOutboundLogistics.InboundAndOutboundLogisticsBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PromotionalSalesManagement_AddServlet extends HttpServlet {

    @EJB
    private PromotionalSalesBeanLocal promotionalSalesBeanLocal;
    @EJB 
    private InboundAndOutboundLogisticsBeanLocal inboundAndOutboundLogisticsBeanLocal;   
    @EJB 
    private ItemManagementBeanLocal itemManagementBeanLocal;  
    String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String sku = request.getParameter("sku");
            String countryId = request.getParameter("country");
            String source = request.getParameter("source");
            String discountRate = request.getParameter("discountRate");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String description = request.getParameter("description");
            String imageURL = request.getParameter("imageURL");

            if (inboundAndOutboundLogisticsBeanLocal.checkSKUExists(sku)){
                ItemEntity item = itemManagementBeanLocal.getItemBySKU(sku);
                CountryEntity country = promotionalSalesBeanLocal.getCountryByCountryId(Long.parseLong(countryId));
                promotionalSalesBeanLocal.createPromotion(item,country,Double.parseDouble(discountRate),Date.parse(startDate),Date.parse(endDate),description);
                result = "?goodMsg=Promotion has been created successfully.";
                response.sendRedirect("PromotionalSalesManagement_Servlet" + result);
            } else {
                result = "?errMsg=Failed to add promotion, SKU: " + sku + " does not exist.";
                response.sendRedirect(source + result);
            }
        } catch (Exception ex) {
            out.println(ex);
        } finally {
            out.close();
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
