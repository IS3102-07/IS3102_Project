package A1_servlets;

import EntityManager.StaffEntity;
import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CommonInfrastructure.Workspace.WorkspaceBeanLocal;
import EntityManager.CountryEntity;
import SCM.SupplierManagement.SupplierManagementBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountManagement_LoginServlet extends HttpServlet {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    @EJB
    private SupplierManagementBeanLocal supplierManagementBean;
    @EJB
    private WorkspaceBeanLocal workspaceBean;
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session;
            session = request.getSession();

            StaffEntity staffEntity = (StaffEntity) session.getAttribute("StaffEntity");
            if (staffEntity == null) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                staffEntity = accountManagementBean.loginStaff(email, password);
            }
            List<CountryEntity> countries = supplierManagementBean.getListOfCountries();
            session.setAttribute("countries", countries);

            if (staffEntity == null) {
                result = "Login fail. Please try again.";
                response.sendRedirect("A1/staffLogin.jsp?errMsg=" + result);
            } else {
                session.setAttribute("staffEntity", staffEntity);
                session.setAttribute("unreadMessages", workspaceBean.listAllUnreadInboxMessages(staffEntity.getId()));
                session.setAttribute("inbox", workspaceBean.listAllInboxMessages(staffEntity.getId()));
                session.setAttribute("sentMessages", workspaceBean.listAllOutboxMessages(staffEntity.getId()));
                response.sendRedirect("A1/workspace.jsp");
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
