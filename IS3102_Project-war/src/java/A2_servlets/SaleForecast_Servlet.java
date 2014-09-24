/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A2_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.MonthScheduleEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.RegionalOfficeEntity;
import EntityManager.SaleAndOperationPlanEntity;
import EntityManager.StoreEntity;
import HelperClasses.StoreHelper;
import MRP.SalesAndOperationPlanning.SOP_Helper;
import MRP.SalesAndOperationPlanning.SalesAndOperationPlanningBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SaleForecast_Servlet extends HttpServlet {
    @EJB
    private SalesAndOperationPlanningBeanLocal sopBean;
    @EJB
    private FacilityManagementBeanLocal fmBean;
    @EJB
    private ItemManagementBeanLocal imBean;
    
    
    
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

            case "/SaleForecast_index_GET":
                List<RegionalOfficeEntity> regionalOfficeList = fmBean.viewListOfRegionalOffice();
                if (regionalOfficeList == null) {
                    regionalOfficeList = new ArrayList<>();
                }
                request.setAttribute("regionalOfficeList", regionalOfficeList);
                nextPage = "/A2/SaleForecast_index";
                break;

            case "/SaleForecast_index_POST":
                try {
                    String storeName = request.getParameter("storeName");
                    StoreEntity store = fmBean.getStoreByName(storeName);
                    session.setAttribute("sf_storeId", store.getId());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                nextPage = "/SaleForecast_Servlet/SaleForecast_schedule_GET";
                break;

            case "/SaleForecast_schedule_GET":
                scheduleList = sopBean.getScheduleList();
                request.setAttribute("scheduleList", scheduleList);
                nextPage = "/A2/SaleForecast_schedule";
                break;

            case "/SaleForecast_schedule_POST":
                try {
                    Long schedulelId = Long.parseLong(request.getParameter("scheduleId"));
                    session.setAttribute("scheduleId", schedulelId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                nextPage = "/SaleForecast_Servlet/SaleForecast_main_GET";
                break;

            case "/SaleForecast_main_GET":
                try {
                    Long storeId = (long) session.getAttribute("sf_storeId");
                    Long schedulelId = (long) session.getAttribute("scheduleId");
                    List<ProductGroupEntity> unplannedProductGroupList = sopBean.getUnplannedProductGroup(storeId, schedulelId);
                    List<SOP_Helper> sopHelperList = sopBean.getSOPHelperList(storeId, schedulelId);
                    StoreEntity store = fmBean.viewStoreEntity(storeId);
                    MonthScheduleEntity schedule = sopBean.getScheduleById(schedulelId);
                    request.setAttribute("store", store);
                    request.setAttribute("schedule", schedule);
                    request.setAttribute("unplannedProductGroupList", unplannedProductGroupList);
                    request.setAttribute("sopHelperList", sopHelperList);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                nextPage = "/A2/SaleForecast_main";
                break;

            case "/SaleForecast_main_POST":
                Long productGroupId = Long.parseLong(request.getParameter("productGroupId"));
                session.setAttribute("productGroupId", productGroupId);
                nextPage = "/SaleForecast_Servlet/ViewSaleFigure_GET";
                break;     
                
            case "/ViewSaleFigure_GET":
                
                nextPage = "/A2/ViewSaleFigure";
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
