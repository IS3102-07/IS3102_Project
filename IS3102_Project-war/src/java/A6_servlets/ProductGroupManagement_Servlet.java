package A6_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.ProductGroupEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductGroupManagement_Servlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal ItemManagementBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session;
            session = request.getSession();
            String errMsg = request.getParameter("errMsg");

            List<ProductGroupEntity> productGroups = ItemManagementBean.getAllProductGroup();
            session.setAttribute("productGroups", productGroups);

            if (errMsg == null || errMsg.equals("")) {
                response.sendRedirect("A6/productGroupManagement.jsp");
            } else {
                response.sendRedirect("A6/productGroupManagement.jsp?errMsg=" + errMsg);
            }

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
