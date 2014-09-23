/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A6_servlets;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.RegionalOfficeEntity;
import EntityManager.StoreEntity;
import HelperClasses.StoreHelper;
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
public class FacilityManagement_StoreServlet extends HttpServlet {

    @EJB
    private FacilityManagementBeanLocal fmBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nextPage = "/A6/facilityManagement";
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Long storeId;
        String target = request.getPathInfo();

        switch (target) {

            case "/storeManagement_index":                
                List<StoreHelper> models = fmBean.getStoreHelperList();
                request.setAttribute("modelList", models);                
                nextPage = "/A6/storeManagement";
                break;

            case "/createStore_GET":
                System.out.println("Create store in servlet");
                String submit_btn = request.getParameter("submit-btn");
                if (submit_btn.equals("Add Store")) {
                    List<RegionalOfficeEntity> regionalOfficeList = fmBean.viewListOfRegionalOffice();
                    request.setAttribute("regionalOfficeList", regionalOfficeList);
                    nextPage = "/A6/createStore";
                } else if (submit_btn.equals("Delete Store")) {
                    nextPage = "/FacilityManagement_StoreServlet/deleteStore";
                } else {
                    storeId = Long.parseLong(submit_btn);
                    request.setAttribute("storeId", storeId);
                    nextPage = "/FacilityManagement_StoreServlet/editStore_GET";  
                }
                break;

            case "/createStore_POST":
                try {
                    String storeName = request.getParameter("storeName");
                    String address = request.getParameter("address");
                    String telephone = request.getParameter("telephone");
                    String email = request.getParameter("email");
                    Long regionalOfficeId = Long.parseLong(request.getParameter("regionalOfficeId"));
                    
                    System.out.println("Posting from create store :");
                    StoreEntity store = fmBean.createStore(storeName, address, telephone, email);
                    fmBean.addStoreToRegionalOffice(regionalOfficeId, store.getId());
                    if (store != null) {
                        request.setAttribute("alertMessage", "A new store record has been saved.");
                    } else {
                        request.setAttribute("alertMessage", "Fail to create store due to duplicated regional office name.");
                    }
                    request.setAttribute("store", store);
                    nextPage = "/FacilityManagement_StoreServlet/storeManagement_index";
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case "/editStore_GET":
                storeId = (long)request.getAttribute("storeId");
                System.out.println("Store ID is " + storeId);                
                StoreHelper storeHelper = fmBean.getStoreHelperClass(storeId);
                request.setAttribute("storeHelper", storeHelper);                
                List<RegionalOfficeEntity> regionalOfficeList = fmBean.viewListOfRegionalOffice();
                request.setAttribute("regionalOfficeList", regionalOfficeList);
                nextPage = "/A6/editStore";
                break;

            case "/editStore_POST":
                String storeName = request.getParameter("storeName");
                String address = request.getParameter("address");
                String telephone = request.getParameter("telephone");
                String email = request.getParameter("email");
                Long id = Long.parseLong(request.getParameter("storeId"));
                Long regionalOfficeId = Long.parseLong(request.getParameter("regionalOfficeId"));
                
                if (fmBean.editStore(id, storeName, address, telephone, email) && 
                        fmBean.updateStoreToRegionalOffice(regionalOfficeId, id)) {
                    request.setAttribute("alertMessage", "The store has been saved.");
                } else {
                    request.setAttribute("alertMessage", "Fail to edit store.");
                }
                nextPage = "/FacilityManagement_StoreServlet/storeManagement_index";
                break;
                
            case "/deleteStore":
                String[] deletes = request.getParameterValues("delete");
                if (deletes != null) {
                    for (String storeString : deletes) {
                        String store_Id = storeString;
                        storeId = Long.parseLong(store_Id);
                        fmBean.removeStore(storeId);
                    }
                }
                nextPage = "/FacilityManagement_StoreServlet/storeManagement_index";
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
