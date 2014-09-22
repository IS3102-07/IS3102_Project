/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A6_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.RegionalOfficeEntity;
import EntityManager.ManufacturingFacilityEntity;
import EntityManager.WarehouseEntity;
import EntityManager.StoreEntity;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class FacilityManagement_RegionalOfficeServlet extends HttpServlet {

    @EJB
    private FacilityManagementBeanLocal fmBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nextPage = "/A6/facilityManagement";
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        String target = request.getPathInfo();

        switch (target) {

            case "/regionalOfficeManagement_index":
                List<RegionalOfficeEntity> regionalOfficeList = fmBean.viewListOfRegionalOffice();
                request.setAttribute("regionalOfficeList", regionalOfficeList);
                nextPage = "/A6/regionalOfficeManagement";
                break;

            case "/createRegionalOffice_GET":
                System.out.println("Create regional office in servlet");
                List<ManufacturingFacilityEntity> manufacturingFacilityList = fmBean.viewListOfManufacturingFacility();
                request.setAttribute("manufacturingFacilityList", manufacturingFacilityList);
                
                String submit_btn = request.getParameter("submit-btn");
                if (submit_btn.equals("Add Regional Office")) {
                    nextPage = "/A6/createRegionalOffice";
                } else if (submit_btn.equals("Delete Regional Office")) {
                    nextPage = "/FacilityManagement_RegionalOfficeServlet/deleteRegionalOffice";
                }
                break;

            case "/createRegionalOffice_POST":
                try {
                    String regionalOfficeName = request.getParameter("regionalOfficeName");
                    String address = request.getParameter("address");
                    String telephone = request.getParameter("telephone");
                    String email = request.getParameter("email");

                    boolean regionalOffice = fmBean.addRegionalOffice(regionalOfficeName, address, telephone, email);
                    if (regionalOffice != false) {
                        request.setAttribute("alertMessage", "A new regional office record has been saved.");
                    } else {
                        request.setAttribute("alertMessage", "Fail to create regional office due to duplicated regional office name.");
                    }
                    request.setAttribute("regionalOffice", regionalOffice);
                    nextPage = "/FacilityManagement_RegionalOfficeServlet/regionalOfficeManagement_index";
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case "/editRegionalOffice_GET":
                String regionalOfficeId = request.getParameter("delete");
                System.out.println("Regional office ID is " + regionalOfficeId);
                RegionalOfficeEntity regionalOffice = fmBean.viewRegionalOffice(regionalOfficeId);
                request.setAttribute("regionalOffice", regionalOffice);
                nextPage = "/A6/editRegionalOffice";
                break;

            case "/editRegionalOffice_POST":
                String regionalOfficeName = request.getParameter("regionalOfficeName");
                String address = request.getParameter("address");
                String telephone = request.getParameter("telephone");
                String email = request.getParameter("email");
                Long id = Long.parseLong(request.getParameter("regionalOfficeId"));

                if (fmBean.editRegionalOffice(regionalOfficeName)) {
                    request.setAttribute("alertMessage", "The regional office has been saved.");
                } else {
                    request.setAttribute("alertMessage", "Fail to edit regional office.");
                }
                nextPage = "/FacilityManagement_RegionalOfficeServlet/regionalOfficeManagement_index";
                break;
            case "/deleteRegionalOffice":
                String[] deletes = request.getParameterValues("delete");
                if (deletes != null) {
                    for (String regionalOfficeString : deletes) {
                        String regionalOffice_Id = regionalOfficeString;
                        fmBean.removeRegionalOffice(regionalOffice_Id);
                    }
                }
                nextPage = "/FacilityManagement_RegionalOfficeServlet/regionalOfficeManagement_index";
                break;

        }
        dispatcher = servletContext.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
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
