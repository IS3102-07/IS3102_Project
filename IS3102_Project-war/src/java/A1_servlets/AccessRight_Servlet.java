/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.AccessRightEntity;
import EntityManager.ManufacturingFacilityEntity;
import EntityManager.RegionalOfficeEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import EntityManager.StoreEntity;
import EntityManager.WarehouseEntity;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
public class AccessRight_Servlet extends HttpServlet {

    AccountManagementBeanLocal amBean = lookupAccountManagementBeanLocal();
    @EJB
    private FacilityManagementBeanLocal fmBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nextPage = "/A1/AccessRight";
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession();
        String target = request.getPathInfo();
        Long staffId = (long) -1;
        Long roleId = (long) -1;

        switch (target) {

            case "/AccessRight_GET":

                staffId = Long.parseLong(request.getParameter("staffId"));
                roleId = Long.parseLong(request.getParameter("roleId"));

                session.setAttribute("AR_staffId", staffId);
                session.setAttribute("AR_roleId", roleId);

                StaffEntity staff = amBean.getStaffById(staffId);
                RoleEntity role = amBean.getRoleById(roleId);
                List<RegionalOfficeEntity> regionalOfficeList = fmBean.viewListOfRegionalOffice();
                List<StoreEntity> storeList = fmBean.viewListOfStore();
                List<WarehouseEntity> warehouseList = fmBean.getWarehouseList();
                List<ManufacturingFacilityEntity> manufacturingFacilityList = fmBean.viewListOfManufacturingFacility();

                request.setAttribute("staff", staff);
                request.setAttribute("role", role);
                request.setAttribute("regionalOfficeList", regionalOfficeList);
                request.setAttribute("storeList", storeList);
                request.setAttribute("warehouseList", warehouseList);
                request.setAttribute("manufacturingFacilityList", manufacturingFacilityList);

                nextPage = "/A1/AccessRight";
                break;

            case "/AccessRight_POST":
                try {
                    String regionalOffice = request.getParameter("regionalOffice");
                    Long regionalOfficeId;
                    if (!regionalOffice.equals("")) {
                        System.out.println("regionalOffice: " + regionalOffice);
                        regionalOfficeId = Long.parseLong(regionalOffice);
                    } else {
                        regionalOfficeId = (long) -1;
                    }

                    String store = request.getParameter("store");
                    Long storeId;
                    if (!store.equals("")) {
                        storeId = Long.parseLong(store);
                    } else {
                        storeId = (long) -1;
                    }

                    String warehouse = request.getParameter("warehouse");
                    Long warehouseId;
                    if (!warehouse.equals("")) {
                        warehouseId = Long.parseLong(warehouse);
                    } else {
                        warehouseId = (long) -1;
                    }

                    String manufacturingFacility = request.getParameter("manufacturingFacility");
                    Long manufacturingFacilityId;
                    if (!manufacturingFacility.equals("")) {
                        manufacturingFacilityId = Long.parseLong(manufacturingFacility);
                    } else {
                        manufacturingFacilityId = (long) -1;
                    }

                    staffId = (long) session.getAttribute("AR_staffId");
                    roleId = (long) session.getAttribute("AR_roleId");

                    AccessRightEntity accessRight = amBean.createAccessRight(staffId, roleId, regionalOfficeId, storeId, warehouseId, manufacturingFacilityId);
                    if (accessRight != null) {
                        request.setAttribute("alertMessage", "Custom access right has been set for the staff.");
                    } else {
                        request.setAttribute("alertMessage", "Failed to customize access right for the staff.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                nextPage = "/StaffManagement_UpdateStaffServlet?id=" + (long) session.getAttribute("AR_staffId");
                response.sendRedirect(".." + nextPage);
                return;

        }
        System.out.println(nextPage);
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

    private AccountManagementBeanLocal lookupAccountManagementBeanLocal() {
        try {
            Context c = new InitialContext();
            return (AccountManagementBeanLocal) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/AccountManagementBean!CommonInfrastructure.AccountManagement.AccountManagementBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}