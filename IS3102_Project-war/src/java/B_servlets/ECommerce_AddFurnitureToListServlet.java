package B_servlets;

import EntityManager.MemberEntity;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import ECommerce.ECommerceBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class ECommerce_AddFurnitureToListServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBean;
    
    @EJB
    private AccountManagementBeanLocal accountManagementBean;

    @EJB
    private ECommerceBeanLocal ecb;

    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("ECommerce_AddFurnitureToListServlet");
        try {
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
            
            MemberEntity member = accountManagementBean.getMemberByEmail(email);
            
            String sku = request.getParameter("SKU");

            Boolean testing = ecb.addItemToWishlist(sku, member.getId()); //got problem here
            System.out.println("ECommerce_AddFurnitureToListServlet: Ends successfully.");
            
            result = "Item added successfully.";
            if (testing) {
                response.sendRedirect("ECommerce_WishListServlet?errMsg=" + result);
            } else {
                result = "Item already added to cart.";
                response.sendRedirect("ECommerce_WishListServlet?errMsg=" + result);
            }
        } catch (Exception ex) {
            out.println(ex);
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
