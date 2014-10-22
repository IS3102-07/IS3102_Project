package B_servlets;

import EntityManager.MemberEntity;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import ECommerce.ECommerceBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class ECommerce_RemoveItemFromListServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBean;
    
    @EJB
    private AccountManagementBeanLocal accountManagementBean;

    @EJB
    private ECommerceBean ecb;

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
            String[] deleteArr = request.getParameterValues("delete");

            if (deleteArr != null) {
                for (int i = 0; i < deleteArr.length; i++) {
                    ecb.removeItemFromWishlist(deleteArr[i], member.getId());
                }
                response.sendRedirect("ECommerce_ShoppingCartServlet?errMsg=Successfully removed: " + deleteArr.length + " record(s).");
            } else {
                response.sendRedirect("ECommerce_ShoppingCartServlet?errMsg=Nothing selected.");
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
