package B_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import ECommerce.ECommerceBeanLocal;
import EntityManager.ShoppingListEntity;
import ECommerce.ECommerceBean;
import EntityManager.FurnitureEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.WishListEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 *
 * @author yang
 */
public class ECommerce_WishListServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBean;

    @EJB
    private ECommerceBeanLocal ecb;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String errMsg = request.getParameter("errMsg");
            System.out.println("Error message received is " + errMsg);
            Cookie[] cookies = request.getCookies();
            String email = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("memberId")) {
                        System.out.println("Cookie value : " + cookie.getValue());
                        email = cookie.getValue();
                    }
                }
            }

            HttpSession session;
            session = request.getSession();
            String URLprefix = (String) session.getAttribute("URLprefix");
            if (URLprefix == null) {
                response.sendRedirect("/IS3102_Project-war/B/selectCountry.jsp");
            }

            WishListEntity wishList = ecb.getWishList(email);
            session.setAttribute("wishList", wishList);
            List<FurnitureEntity> furnitures = itemManagementBean.listAllFurniture();
            Long countryID = (Long) session.getAttribute("countryID");
            List<Item_CountryEntity> item_countryList = itemManagementBean.listAllItemsOfCountry(countryID);
            session.setAttribute("furnitures", furnitures);
            session.setAttribute("item_countryList", item_countryList);
            
            if (errMsg == null) {
                errMsg = "";
            }
            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "wishList.jsp?errMsg=" + errMsg);

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
