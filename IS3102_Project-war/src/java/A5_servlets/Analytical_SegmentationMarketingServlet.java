package A5_servlets;

import AnalyticalCRM.ValueAnalysis.CustomerValueAnalysisBeanLocal;
import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.MemberEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Analytical_SegmentationMarketingServlet extends HttpServlet {

    @EJB
    private CustomerValueAnalysisBeanLocal customerValueAnalysisBean;
    
    @EJB
    private AccountManagementBeanLocal accountManagementBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session;
            session = request.getSession();
            String errMsg = request.getParameter("errMsg");
            String goodMsg = request.getParameter("goodMsg");
            Double totalCustomerRevenue = customerValueAnalysisBean.totalMemberRevenue();
            List <MemberEntity> members = accountManagementBean.listAllMember();
            session.setAttribute("totalCustomerRevenue", totalCustomerRevenue);
            session.setAttribute("members", members);
            
            Integer cummulativeSpendingAgeGrp1 = customerValueAnalysisBean.totalCummulativeSpendingOfAge(17, 26);
            Integer cummulativeSpendingAgeGrp2 = customerValueAnalysisBean.totalCummulativeSpendingOfAge(25, 41);
            Integer cummulativeSpendingAgeGrp3 = customerValueAnalysisBean.totalCummulativeSpendingOfAge(40, 56);
            Integer cummulativeSpendingAgeGrp4 = customerValueAnalysisBean.totalCummulativeSpendingOfAge(55, 76);
            
            session.setAttribute("cummulativeSpendingAgeGrp1", cummulativeSpendingAgeGrp1);
            session.setAttribute("cummulativeSpendingAgeGrp2", cummulativeSpendingAgeGrp2);
            session.setAttribute("cummulativeSpendingAgeGrp3", cummulativeSpendingAgeGrp3);
            session.setAttribute("cummulativeSpendingAgeGrp4", cummulativeSpendingAgeGrp4);
            
            Integer numOfMembersInAgeGroup1 = customerValueAnalysisBean.numOfMembersInAgeGroup(17, 26);
            Integer numOfMembersInAgeGroup2 = customerValueAnalysisBean.numOfMembersInAgeGroup(25, 41);
            Integer numOfMembersInAgeGroup3 = customerValueAnalysisBean.numOfMembersInAgeGroup(40, 56);
            Integer numOfMembersInAgeGroup4 = customerValueAnalysisBean.numOfMembersInAgeGroup(55, 76);

            session.setAttribute("numOfMembersInAgeGroup1", numOfMembersInAgeGroup1);
            session.setAttribute("numOfMembersInAgeGroup2", numOfMembersInAgeGroup2);
            session.setAttribute("numOfMembersInAgeGroup3", numOfMembersInAgeGroup3);
            session.setAttribute("numOfMembersInAgeGroup4", numOfMembersInAgeGroup4);
            
            Integer numOfMembersInIncomeGroup1 = customerValueAnalysisBean.numOfMembersInIncomeGroup(0, 30000);
            Integer numOfMembersInIncomeGroup2 = customerValueAnalysisBean.numOfMembersInIncomeGroup(20000, 60000);
            Integer numOfMembersInIncomeGroup3 = customerValueAnalysisBean.numOfMembersInIncomeGroup(30000, 100000);
            Integer numOfMembersInIncomeGroup4 = customerValueAnalysisBean.numOfMembersInIncomeGroup(40000, 250000);

            session.setAttribute("numOfMembersInIncomeGroup1", numOfMembersInIncomeGroup1);
            session.setAttribute("numOfMembersInIncomeGroup2", numOfMembersInIncomeGroup2);
            session.setAttribute("numOfMembersInIncomeGroup3", numOfMembersInIncomeGroup3);
            session.setAttribute("numOfMembersInIncomeGroup4", numOfMembersInIncomeGroup4);
            
            Integer cummulativeSpendingIncomeGrp1 = customerValueAnalysisBean.totalCummulativeSpendingOfIncome(0, 30000);
            Integer cummulativeSpendingIncomeGrp2 = customerValueAnalysisBean.totalCummulativeSpendingOfIncome(20000, 60000);
            Integer cummulativeSpendingIncomeGrp3 = customerValueAnalysisBean.totalCummulativeSpendingOfIncome(30000, 100000);
            Integer cummulativeSpendingIncomeGrp4 = customerValueAnalysisBean.totalCummulativeSpendingOfIncome(40000, 250000);
            
            session.setAttribute("cummulativeSpendingIncomeGrp1", cummulativeSpendingIncomeGrp1);
            session.setAttribute("cummulativeSpendingIncomeGrp2", cummulativeSpendingIncomeGrp2);
            session.setAttribute("cummulativeSpendingIncomeGrp3", cummulativeSpendingIncomeGrp3);
            session.setAttribute("cummulativeSpendingIncomeGrp4", cummulativeSpendingIncomeGrp4);
            
            Integer numOfMembersInJoinDateGroup1 = customerValueAnalysisBean.numOfMembersInIncomeGroup(0, 30000);
            Integer numOfMembersInJoinDateGroup2 = customerValueAnalysisBean.numOfMembersInIncomeGroup(20000, 60000);
            Integer numOfMembersInJoinDateGroup3 = customerValueAnalysisBean.numOfMembersInIncomeGroup(30000, 100000);
            Integer numOfMembersInJoinDateGroup4 = customerValueAnalysisBean.numOfMembersInIncomeGroup(40000, 250000);

            session.setAttribute("numOfMembersInJoinDateGroup1", numOfMembersInIncomeGroup1);
            session.setAttribute("numOfMembersInJoinDateGroup2", numOfMembersInIncomeGroup2);
            session.setAttribute("numOfMembersInJoinDateGroup3", numOfMembersInIncomeGroup3);
            session.setAttribute("numOfMembersInJoinDateGroup4", numOfMembersInIncomeGroup4);
            
            Integer cummulativeSpendingJoinDateGrp1 = customerValueAnalysisBean.totalCummulativeSpendingOfIncome(0, 30000);
            Integer cummulativeSpendingJoinDateGrp2 = customerValueAnalysisBean.totalCummulativeSpendingOfIncome(20000, 60000);
            Integer cummulativeSpendingJoinDateGrp3 = customerValueAnalysisBean.totalCummulativeSpendingOfIncome(30000, 100000);
            Integer cummulativeSpendingJoinDateGrp4 = customerValueAnalysisBean.totalCummulativeSpendingOfIncome(40000, 250000);
            
            session.setAttribute("cummulativeSpendingJoinDateGrp1", cummulativeSpendingJoinDateGrp1);
            session.setAttribute("cummulativeSpendingJoinDateGrp2", cummulativeSpendingJoinDateGrp2);
            session.setAttribute("cummulativeSpendingJoinDateGrp3", cummulativeSpendingJoinDateGrp3);
            session.setAttribute("cummulativeSpendingJoinDateGrp4", cummulativeSpendingJoinDateGrp4);
            
            Integer totalCummulativeSpendingOfCountry1 = customerValueAnalysisBean.totalCummulativeSpendingOfCountry("Singapore");
            session.setAttribute("totalCummulativeSpendingOfCountry1", totalCummulativeSpendingOfCountry1);
            
            Integer numOfMembersInCountry1 = customerValueAnalysisBean.numOfMembersInCountry("Singapore");
            session.setAttribute("numOfMembersInCountry1", numOfMembersInCountry1);
            
            Integer totalCummulativeSpendingOfCountry2 = customerValueAnalysisBean.totalCummulativeSpendingOfCountry("Malaysia");
            session.setAttribute("totalCummulativeSpendingOfCountry2", totalCummulativeSpendingOfCountry2);
            
            Integer numOfMembersInCountry2 = customerValueAnalysisBean.numOfMembersInCountry("Malaysia");
            session.setAttribute("numOfMembersInCountry2", numOfMembersInCountry2);
            
            Integer getRevenueOfJoinDate1 = customerValueAnalysisBean.getRevenueOfJoinDate(1);
            Integer getRevenueOfJoinDate2 = customerValueAnalysisBean.getRevenueOfJoinDate(2);
            Integer getRevenueOfJoinDate3 = customerValueAnalysisBean.getRevenueOfJoinDate(3);
            Integer getRevenueOfJoinDate4 = customerValueAnalysisBean.getRevenueOfJoinDate(4);
            
            session.setAttribute("getRevenueOfJoinDate1",getRevenueOfJoinDate1);
            session.setAttribute("getRevenueOfJoinDate2",getRevenueOfJoinDate2);
            session.setAttribute("getRevenueOfJoinDate3",getRevenueOfJoinDate3);
            session.setAttribute("getRevenueOfJoinDate4",getRevenueOfJoinDate4);
            
            Integer numOfMembersInJoinDate1 = customerValueAnalysisBean.numOfMembersInJoinDate(1);
            Integer numOfMembersInJoinDate2 = customerValueAnalysisBean.numOfMembersInJoinDate(2);
            Integer numOfMembersInJoinDate3 = customerValueAnalysisBean.numOfMembersInJoinDate(3);
            Integer numOfMembersInJoinDate4 = customerValueAnalysisBean.numOfMembersInJoinDate(4);
            
            session.setAttribute("numOfMembersInJoinDate1",numOfMembersInJoinDate1);
            session.setAttribute("numOfMembersInJoinDate2",numOfMembersInJoinDate2);
            session.setAttribute("numOfMembersInJoinDate3",numOfMembersInJoinDate3);
            session.setAttribute("numOfMembersInJoinDate4",numOfMembersInJoinDate4);
            if (errMsg == null && goodMsg == null) {
                response.sendRedirect("A5/segmentationMarketing.jsp");
            } else if ((errMsg != null) && (goodMsg == null)) {
                if (!errMsg.equals("")) {
                    response.sendRedirect("A5/segmentationMarketing.jsp?errMsg=" + errMsg);
                }
            } else if ((errMsg == null && goodMsg != null)) {
                if (!goodMsg.equals("")) {
                    response.sendRedirect("A5/segmentationMarketing.jsp?goodMsg=" + goodMsg);
                }
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
