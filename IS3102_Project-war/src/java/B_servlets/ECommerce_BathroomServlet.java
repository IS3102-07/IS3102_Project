package B_servlets;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.FurnitureEntity;
import EntityManager.RetailProductEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import java.util.List;
/**
 *
 * @author yang
 */
public class ECommerce_BathroomServlet extends HttpServlet {

    @EJB
    private ItemManagementBeanLocal itemManagementBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            HttpSession session;
            session = request.getSession();
            
            List<FurnitureEntity> furnitures = itemManagementBean.viewFurnitureByCategory("Bathroom");
            session.setAttribute("furnitures", furnitures);
            String country = (String) session.getAttribute("countryName");
            if (country == null) {
                country = "";
            }
            switch (country) {
//                case "France":
//                    response.sendRedirect("B/FRA/bathroom.jsp");
//                    break;
//                case "USA":
//                    response.sendRedirect("B/USA/bathroom.jsp");
//                    break;
//                case "China":
//                    response.sendRedirect("B/CN/bathroom.jsp");
//                    break;
//                case "Singapore":
//                    response.sendRedirect("B/SG/bathroom.jsp");
//                    break;
//                case "Malaysia":
//                    response.sendRedirect("B/MY/bathroom.jsp");
//                    break;
//                case "Indonesia":
//                    response.sendRedirect("B/IDN/bathroom.jsp");
//                    break;
                default:
                    response.sendRedirect("B/bathroom.jsp");
                    break;
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
