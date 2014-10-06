package A6_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RetailProductManagement_AddRetailProductServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBean;
    String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String SKU = request.getParameter("SKU");
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String imageURL = request.getParameter("imageURL");
            Integer _length = Integer.parseInt(request.getParameter("length"));
            Integer width = Integer.parseInt(request.getParameter("width"));
            Integer height = Integer.parseInt(request.getParameter("height"));
            Integer lotSize = Integer.parseInt(request.getParameter("lotsize"));
            Integer leadTime = Integer.parseInt(request.getParameter("leadtime"));
            Double price = Double.parseDouble(request.getParameter("price"));
            String supplier= request.getParameter("supplier");
            String source = request.getParameter("source");

            if (!itemManagementBean.checkSKUExists(SKU)) {
                itemManagementBean.addRetailProduct(SKU, name, category, description, imageURL, _length, width, height, lotSize, leadTime, price, Long.parseLong(supplier));
                result = "?goodMsg=Retail Product with SKU: " + SKU + " has been created successfully.";
                response.sendRedirect("RetailProductManagement_RetailProductServlet" + result);
            } else {
                result = "?errMsg=Failed to add retail product, SKU: " + SKU + " already exist.";
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
