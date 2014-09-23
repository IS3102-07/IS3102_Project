/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A6_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.ManufacturingFacilityEntity;
import EntityManager.RegionalOfficeEntity;
import HelperClasses.ManufacturingFacilityHelper;
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
public class FacilityManagement_ManufacturingFacilityServlet extends HttpServlet {

    @EJB
    private FacilityManagementBeanLocal fmBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nextPage = "/A6/facilityManagement";
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Long manufacturingFacilityId;
        String target = request.getPathInfo();

        switch (target) {

            case "/manufacturingFacilityManagement_index":
                List<ManufacturingFacilityHelper> helperList = fmBean.getManufacturingFacilityHelperList();
                request.setAttribute("helperList", helperList);
                nextPage = "/A6/manufacturingFacilityManagement";
                break;

            case "/createManufacturingFacility_GET":
                System.out.println("Create manufacturing facility in servlet");
                String submit_btn = request.getParameter("submit-btn");
                System.out.println(submit_btn);
                if (submit_btn.equals("Add Manufacturing Facility")) {
                    List<RegionalOfficeEntity> regionalOfficeList = fmBean.viewListOfRegionalOffice();
                    request.setAttribute("regionalOfficeList", regionalOfficeList);
                    nextPage = "/A6/createManufacturingFacility";
                } else if (submit_btn.equals("Delete Manufacturing Facility")) {
                    nextPage = "/FacilityManagement_ManufacturingFacilityServlet/deleteManufacturingFacility";
                } else {
                    manufacturingFacilityId = Long.parseLong(submit_btn);
                    request.setAttribute("manufacturingFacilityId", manufacturingFacilityId);
                    nextPage = "/FacilityManagement_ManufacturingFacilityServlet/editManufacturingFacility_GET";
                }
                break;

            case "/createManufacturingFacility_POST":
                try {
                    String manufacturingFacilityName = request.getParameter("manufacturingFacilityName");
                    String address = request.getParameter("address");
                    String telephone = request.getParameter("telephone");
                    String email = request.getParameter("email");
                    Long regionalOfficeId = Long.parseLong(request.getParameter("regionalOfficeId"));
                    Integer capacity = Integer.valueOf(request.getParameter("capacity"));

                    if (fmBean.checkNameExistsOfManufacturingFacility(manufacturingFacilityName)) {
                        request.setAttribute("alertMessage", "Fail to create manufacturing facility due to duplicated manufacturing facility name.");
                        request.setAttribute("manufacturingFacility", true);
                        nextPage = "/FacilityManagement_ManufacturingFacilityServlet/manufacturingFacilityManagement_index";
                    } else {
                        ManufacturingFacilityEntity manufacturingFacility = fmBean.createManufacturingFacility(manufacturingFacilityName, address, telephone, email, capacity);
                        fmBean.addManufacturingFacilityToRegionalOffice(regionalOfficeId, manufacturingFacility.getId());
                        if (manufacturingFacility != null) {
                            request.setAttribute("alertMessage", "A new manufacturing facility record has been saved.");
                        } else {
                            request.setAttribute("alertMessage", "Fail to create manufacturing facility due to duplicated name.");
                        }
                        request.setAttribute("manufacturingFacility", manufacturingFacility);
                        nextPage = "/FacilityManagement_ManufacturingFacilityServlet/manufacturingFacilityManagement_index";
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case "/editManufacturingFacility_GET":
                manufacturingFacilityId = (long) request.getAttribute("manufacturingFacilityId");
                System.out.println("Manufacturing Facility ID is " + manufacturingFacilityId);
                ManufacturingFacilityHelper mfHelper = fmBean.getManufacturingFacilityHelper(manufacturingFacilityId);
                request.setAttribute("mfHelper", mfHelper);
                List<RegionalOfficeEntity> regionalOfficeList = fmBean.viewListOfRegionalOffice();
                request.setAttribute("regionalOfficeList", regionalOfficeList);
                nextPage = "/A6/editManufacturingFacility";
                break;

            case "/editManufacturingFacility_POST":
                System.out.println("reach manufacturing facility post");
                String manufacturingFacilityName = request.getParameter("manufacturingFacilityName");
                String address = request.getParameter("address");
                String telephone = request.getParameter("telephone");
                String email = request.getParameter("email");
                Long regionalOfficeId = Long.parseLong(request.getParameter("regionalOfficeId"));
                System.out.println(manufacturingFacilityName + address + telephone + email);
                String capacity = request.getParameter("capacity");
                Long mfId = Long.parseLong(request.getParameter("manufacturingFacilityId"));
                System.out.println(mfId + " is id");
                if (fmBean.editManufacturingFacility(mfId, manufacturingFacilityName, address, telephone, email, Integer.valueOf(capacity))
                        && fmBean.updateManufacturingFacilityToRegionalOffice(regionalOfficeId, mfId)) {
                    request.setAttribute("alertMessage", "The manufacturing facility has been saved.");
                } else {
                    request.setAttribute("alertMessage", "Fail to edit manufacturing facility.");
                }
                nextPage = "/FacilityManagement_ManufacturingFacilityServlet/manufacturingFacilityManagement_index";
                break;
            case "/deleteManufacturingFacility":
                String[] deletes = request.getParameterValues("delete");
                if (deletes != null) {
                    for (String manufacturingFacilityString : deletes) {
                        String manufacturingFacility_Id = manufacturingFacilityString;
                        mfId = Long.parseLong(manufacturingFacility_Id);
                        fmBean.removeManufacturingFacility(mfId);
                    }
                }
                nextPage = "/FacilityManagement_ManufacturingFacilityServlet/manufacturingFacilityManagement_index";
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
