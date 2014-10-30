package A4_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import OperationalCRM.PromotionalSales.PromotionalSalesBeanLocal;
import SCM.InboundAndOutboundLogistics.InboundAndOutboundLogisticsBeanLocal;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
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
            Part file = request.getPart("javafile");
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(startDate);
            Date date1 = formatter.parse(endDate);
            String datestring = formatter.format(date1); 

            if (date1.before(date)){
                result = "?errMsg=Failed to add promotion. End Date is earlier than start date.";
                response.sendRedirect(source + result);
            }
            
            if (inboundAndOutboundLogisticsBeanLocal.checkSKUExists(sku)) {               
                String fileName = sku + datestring + ".jpg";
                String imageURL = "/IS3102_Project-war/img/products/" + fileName;
                
                
                ItemEntity item = itemManagementBeanLocal.getItemBySKU(sku);
                CountryEntity country = promotionalSalesBeanLocal.getCountryByCountryId(Long.parseLong(countryId));
                promotionalSalesBeanLocal.createPromotion(item, country, Double.parseDouble(discountRate), date, date1, imageURL, description);
                result = "?goodMsg=Promotion has been created successfully.";
                
                 if (file != null) {
                    String s = file.getHeader("content-disposition");
                    InputStream fileInputStream = file.getInputStream();
                    OutputStream fileOutputStream = new FileOutputStream(request.getServletContext().getRealPath("/img/products/") + "/" + fileName);

                    System.out.println("fileOutputStream  " + fileOutputStream);
                    int nextByte;
                    while ((nextByte = fileInputStream.read()) != -1) {
                        fileOutputStream.write(nextByte);
                    }
                    fileOutputStream.close();
                    fileInputStream.close();
                }

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
