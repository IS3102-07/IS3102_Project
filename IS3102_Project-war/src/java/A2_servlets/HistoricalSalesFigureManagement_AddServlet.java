package A2_servlets;

import EntityManager.SalesFigureEntity;
import MRP.SalesForecast.SalesForecastBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HistoricalSalesFigureManagement_AddServlet extends HttpServlet {

    @EJB
    private SalesForecastBeanLocal sfbl;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String month = request.getParameter("month");
            String store = request.getParameter("store");
            String itemId = request.getParameter("itemId");
            String quantity = request.getParameter("quantity");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(month);

            SalesFigureEntity sf = sfbl.createSalesFigure(date, Integer.parseInt(quantity), Long.parseLong(store), Long.parseLong(itemId));
            if (sf == null) {
                result = "?errMsg=Failed to add sales figures.";
                response.sendRedirect("A2/historicalSalesFigureManagement_add.jsp" + result);
            } else {
                result = "?goodMsg=Sales Figures created successfully";
                response.sendRedirect("HistoricalSalesFigureManagement_Servlet" + result);
            }

            response.sendRedirect("A2/historicalSalesFigureManagement.jsp");

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
