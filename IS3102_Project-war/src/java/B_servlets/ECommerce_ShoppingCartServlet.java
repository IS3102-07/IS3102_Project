package B_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import OperationalCRM.CustomerInformationManagement.CustomerInformationManagementBeanLocal;
import EntityManager.ShoppingListEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import java.util.List;
import javax.servlet.http.Cookie;

/**
 *
 * @author yang
 */
public class ECommerce_ShoppingCartServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBean;

    @EJB
    private CustomerInformationManagementBeanLocal customerInformationManagementBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("ECommerce_ShoppingCartServlet");
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

            ShoppingListEntity shoppingList = customerInformationManagementBean.shoppingList(email);
            session.setAttribute("shoppingList", shoppingList);
            
            if (errMsg == null) {
                errMsg = "";
            }
            response.sendRedirect("B/shoppingList.jsp?errMsg=" + errMsg);

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
