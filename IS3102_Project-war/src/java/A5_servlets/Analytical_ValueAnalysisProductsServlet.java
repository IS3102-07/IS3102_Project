package A5_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import AnalyticalCRM.ValueAnalysis.CustomerValueAnalysisBeanLocal;
import EntityManager.LineItemEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;

public class Analytical_ValueAnalysisProductsServlet extends HttpServlet {

    @EJB
    CustomerValueAnalysisBeanLocal customerValueAnalysisBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            List<LineItemEntity> sortBestSellingFurniture = customerValueAnalysisBean.sortBestSellingFurniture();
            session.setAttribute("sortBestSellingFurniture", sortBestSellingFurniture);

            List<LineItemEntity> sortBestSellingRetailProducts = customerValueAnalysisBean.sortBestSellingRetailProducts();
            session.setAttribute("sortBestSellingRetailProducts", sortBestSellingRetailProducts);
            
            List<LineItemEntity> getTotalFurnitureSoldInCountry = customerValueAnalysisBean.getTotalFurnitureSoldInCountry("singapore");
            Integer totalNumberOfFurnitureInSG = 0;
            for (int i = 0; i < getTotalFurnitureSoldInCountry.size(); i++) {
                totalNumberOfFurnitureInSG += getTotalFurnitureSoldInCountry.get(i).getQuantity();
            }
            session.setAttribute("totalNumberOfFurnitureInSG",totalNumberOfFurnitureInSG);
            response.sendRedirect("A5/products.jsp");
        } catch (Exception ex) {
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
