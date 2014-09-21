/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A2_servlets;

import EntityManager.RegionalOfficeEntity;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String nextPage = "/A2/sop_index";
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession();

        String target = request.getPathInfo();
        
        switch (target) {
            case "/sop_index_GET":                
                List<RegionalOfficeEntity> regionalOfficeList = new ArrayList<>(); // 1234567890 wait for regional office 
                request.setAttribute("regionalOfficeList", regionalOfficeList);
                nextPage = "/A2/sop_index";
                break;
            case "/sop_index_Post":
                try{
                    Long storeId = Long.parseLong(request.getParameter("store"));
                    session.setAttribute("sop_storeId", storeId);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
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
