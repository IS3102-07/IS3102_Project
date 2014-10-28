package A5_servlets;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import AnalyticalCRM.ValueAnalysis.CustomerValueAnalysisBeanLocal;
import EntityManager.MemberEntity;
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

public class Analytical_ValueAnalysisServlet extends HttpServlet {

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
            System.out.println("Analytical_ValueAnalysisServlet");
            Double totalCustomerRevenue = customerValueAnalysisBean.totalMemberRevenue();
            Double totalNonCustomerRevenue = customerValueAnalysisBean.totalNonMemberRevenue();
            System.out.println(totalCustomerRevenue);
            session.setAttribute("totalCustomerRevenue", totalCustomerRevenue);
            session.setAttribute("totalNonCustomerRevenue", totalNonCustomerRevenue);

            List<MemberEntity> members = accountManagementBean.listAllMember();
            List<Integer> memberRecencyValue = new ArrayList();
            for (MemberEntity member : members) {
                memberRecencyValue.add(customerValueAnalysisBean.getCustomerRecency(member.getId()));
            }
            for (int i=0;i<members.size();i++) {
                System.out.println(members.get(i).getId()+"||||||"+memberRecencyValue.get(i));
            }
            session.setAttribute("members", members);
            session.setAttribute("memberRecencyValue", memberRecencyValue);

            Integer cummulativeSpendingAgeGrp1 = customerValueAnalysisBean.totalCummulativeSpending(17, 26);
            Integer cummulativeSpendingAgeGrp2 = customerValueAnalysisBean.totalCummulativeSpending(25, 41);
            Integer cummulativeSpendingAgeGrp3 = customerValueAnalysisBean.totalCummulativeSpending(40, 56);
            Integer cummulativeSpendingAgeGrp4 = customerValueAnalysisBean.totalCummulativeSpending(55, 76);
            Integer averageCummulativeSpending = customerValueAnalysisBean.averageCummulativeSpending();

            session.setAttribute("cummulativeSpendingAgeGrp1", cummulativeSpendingAgeGrp1);
            session.setAttribute("cummulativeSpendingAgeGrp2", cummulativeSpendingAgeGrp2);
            session.setAttribute("cummulativeSpendingAgeGrp3", cummulativeSpendingAgeGrp3);
            session.setAttribute("cummulativeSpendingAgeGrp4", cummulativeSpendingAgeGrp4);
            session.setAttribute("averageCummulativeSpending", averageCummulativeSpending);

            Integer numOfMembersInAgeGroup1 = customerValueAnalysisBean.numOfMembersInAgeGroup(17, 26);
            Integer numOfMembersInAgeGroup2 = customerValueAnalysisBean.numOfMembersInAgeGroup(25, 41);
            Integer numOfMembersInAgeGroup3 = customerValueAnalysisBean.numOfMembersInAgeGroup(40, 56);
            Integer numOfMembersInAgeGroup4 = customerValueAnalysisBean.numOfMembersInAgeGroup(55, 76);

            session.setAttribute("numOfMembersInAgeGroup1", numOfMembersInAgeGroup1);
            session.setAttribute("numOfMembersInAgeGroup2", numOfMembersInAgeGroup2);
            session.setAttribute("numOfMembersInAgeGroup3", numOfMembersInAgeGroup3);
            session.setAttribute("numOfMembersInAgeGroup4", numOfMembersInAgeGroup4);

            Integer averageMemberRecency = customerValueAnalysisBean.getAverageCustomerRecency();
            session.setAttribute("averageMemberRecency", averageMemberRecency);

            Integer averageMemberFrequency = customerValueAnalysisBean.getAverageCustomerFrequency();
            session.setAttribute("averageMemberFrequency", averageMemberFrequency);

            Integer averageMemberMonetaryValue = customerValueAnalysisBean.getAverageCustomerMonetaryValue();
            session.setAttribute("averageMemberMonetaryValue", averageMemberMonetaryValue);
            if (errMsg == null && goodMsg == null) {
                response.sendRedirect("A5/valueAnalysis.jsp");
            } else if ((errMsg != null) && (goodMsg == null)) {
                if (!errMsg.equals("")) {
                    response.sendRedirect("A5/valueAnalysis.jsp?errMsg=" + errMsg);
                }
            } else if ((errMsg == null && goodMsg != null)) {
                if (!goodMsg.equals("")) {
                    response.sendRedirect("A5/valueAnalysis.jsp?goodMsg=" + goodMsg);
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
