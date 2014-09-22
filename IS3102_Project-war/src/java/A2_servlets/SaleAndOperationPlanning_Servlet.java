/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A2_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.MonthScheduleEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.RegionalOfficeEntity;
import EntityManager.SaleAndOperationPlanEntity;
import MRP.SalesAndOperationPlanning.SalesAndOperationPlanningBeanLocal;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class SaleAndOperationPlanning_Servlet extends HttpServlet {

    @EJB
    private FacilityManagementBeanLocal fmBean;
    @EJB
    private SalesAndOperationPlanningBeanLocal sopBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nextPage = "/A2/sop_index";
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession();
        List<MonthScheduleEntity> scheduleList;
        String target = request.getPathInfo();

        switch (target) {

            case "/sop_scheduleManagement_GET":
                scheduleList = sopBean.getScheduleList();
                request.setAttribute("scheduleList", scheduleList);
                nextPage = "/A2/sop_scheduleManagement";
                break;
                
            case "/sop_scheduleManagement_POST":
                
                break;

            case "/sop_index_GET":
                List<RegionalOfficeEntity> regionalOfficeList = fmBean.viewListOfRegionalOffice();
                if (regionalOfficeList == null) {
                    regionalOfficeList = new ArrayList<>();
                }
                request.setAttribute("regionalOfficeList", regionalOfficeList);
                nextPage = "/A2/sop_index";
                break;

            case "/sop_index_Post":
                try {
                    Long storeId = Long.parseLong(request.getParameter("store"));
                    session.setAttribute("sop_storeId", storeId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                nextPage = "/SaleAndOperationPlanning_Servlet/sop_schedule_GET";
                break;

            case "/sop_schedule_GET":
                scheduleList = sopBean.getScheduleList();
                
                // for testing ......
                MonthScheduleEntity model1 = new MonthScheduleEntity();
                model1.setYear(2014);
                model1.setMonth(6);
                MonthScheduleEntity model2 = new MonthScheduleEntity();
                model2.setYear(2014);
                model2.setMonth(7);
                scheduleList.add(model1);
                scheduleList.add(model2);
                
                request.setAttribute("scheduleList", scheduleList);

                nextPage = "/A2/sop_schedule";
                break;

            case "/sop_schedule_POST":
                try {
                    Long schedulelId = Long.parseLong(request.getParameter("scheduleId"));
                    Long storeId = (long) session.getAttribute("sop_storeId");
                    List<ProductGroupEntity> unplannedProductGroupList = sopBean.getUnplannedProductGroup(storeId, schedulelId);
                    List<SaleAndOperationPlanEntity> sopList = sopBean.getSaleAndOperationPlanList(storeId, schedulelId);
                    request.setAttribute("unplannedProductGroupList", unplannedProductGroupList);
                    request.setAttribute("sopList", sopList);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                nextPage = "";
                break;

            case "":

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
