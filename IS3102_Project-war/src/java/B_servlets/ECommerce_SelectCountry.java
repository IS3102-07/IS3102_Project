package B_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.CountryEntity;
import EntityManager.StoreEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ECommerce_SelectCountry extends HttpServlet {

    @EJB
    FacilityManagementBeanLocal facilityManagementBeanLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session;
            session = request.getSession();
            String country = request.getParameter("Country");
            Long countryID = facilityManagementBeanLocal.getCountryID(country);
            List<CountryEntity> fullCountryList = facilityManagementBeanLocal.getListOfCountries();
            CountryEntity selectedCountry = null;
            List<StoreEntity> storesInCountry = new ArrayList<>();
            //List all stores in the country
            for (CountryEntity countryEntity : fullCountryList) {
                if (countryEntity.getId().equals(countryID)) {
                    selectedCountry = countryEntity;
                    storesInCountry = countryEntity.getStores();
                    break;
                }
            }
            session.setAttribute("countryID", countryID);
            session.setAttribute("countryName", country);
            session.setAttribute("storesInCountry", storesInCountry);
            switch (country) {
                case "France":
                    session.setAttribute("URLprefix", "FRA/");
                    response.sendRedirect("B/FRA/index.jsp");
                    break;
                case "USA":
                    session.setAttribute("URLprefix", "USA/");
                    response.sendRedirect("B/USA/index.jsp");
                    break;
                case "China":
                    session.setAttribute("URLprefix", "CN/");
                    response.sendRedirect("B/CN/index.jsp");
                    break;
                case "Singapore":
                    session.setAttribute("URLprefix", "SG/");
                    response.sendRedirect("B/SG/index.jsp");
                    break;
                case "Malaysia":
                    session.setAttribute("URLprefix", "MY/");
                    response.sendRedirect("B/MY/index.jsp");
                    break;
                case "Indonesia":
                    session.setAttribute("URLprefix", "IDN/");
                    response.sendRedirect("B/IDN/index.jsp");
                    break;
                default:
                    session.setAttribute("URLprefix", "");
                    response.sendRedirect("B/index.jsp");
                    break;
            }

        } catch (Exception ex) {
            out.println(ex);
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
