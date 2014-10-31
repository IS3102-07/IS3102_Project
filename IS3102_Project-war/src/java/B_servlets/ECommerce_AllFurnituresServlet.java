package B_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.FurnitureEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.PromotionEntity;
import OperationalCRM.PromotionalSales.PromotionalSalesBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ECommerce_AllFurnituresServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal imbl;
    @EJB
    private PromotionalSalesBeanLocal psbl;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            HttpSession session;
            session = request.getSession();
            List<FurnitureEntity> furnitures = imbl.listAllFurniture();
            Long countryID = (Long) session.getAttribute("countryID");
            List<Item_CountryEntity> item_countryList = imbl.listAllItemsOfCountry(countryID);
            List<PromotionEntity> promotions = psbl.getAllActivePromotionsInCountry(countryID);
            session.setAttribute("furnitures", furnitures);
            session.setAttribute("item_countryList", item_countryList);
            session.setAttribute("promotions", promotions);

            String URLprefix = (String) session.getAttribute("URLprefix");
            if (URLprefix == null) {
                response.sendRedirect("/IS3102_Project-war/B/selectCountry.jsp");
            }
            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "allFurnitureProducts.jsp");

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
