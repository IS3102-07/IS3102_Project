package A1_servlets;

import CommonInfrastructure.Workspace.WorkspaceBeanLocal;
import EntityManager.MessageEntity;
import EntityManager.StaffEntity;
import HelperClasses.MessageHelper;
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

public class WorkspaceMessage_Servlet extends HttpServlet {

    @EJB
    private WorkspaceBeanLocal workspaceBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session;
            session = request.getSession();
            String errMsg = request.getParameter("errMsg");

            StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
            List<MessageEntity> inboxMessageEntities = workspaceBean.listAllInboxMessages(staffEntity.getId());
            List<MessageHelper> inboxMessageHelpers = new ArrayList<>();
            for (MessageEntity currentMessage : inboxMessageEntities) {
                MessageHelper messageHelper = new MessageHelper();
                messageHelper.setMessageId(currentMessage.getId());
                messageHelper.setSenderName(currentMessage.getSender().getName());
                messageHelper.setSenderEmail(currentMessage.getSender().getEmail());
                List<String> receiversName = new ArrayList<>();
                List<String> receiversEmail = new ArrayList<>();
                for(int i=0;i<currentMessage.getReceivers().size();i++){
                    receiversName.add(currentMessage.getReceivers().get(i).getName());
                    receiversEmail.add(currentMessage.getReceivers().get(i).getEmail());
                }
                messageHelper.setReceiversName(receiversName);
                messageHelper.setReceiversEmail(receiversEmail);
                messageHelper.setMessage(currentMessage.getMessage());
                messageHelper.setSentDate(currentMessage.getSentDate());
                messageHelper.setMessageRead(currentMessage.getMessageRead());
                inboxMessageHelpers.add(messageHelper);
            }
            session.setAttribute("inboxMessages", inboxMessageHelpers);
            List<MessageEntity> sentMessageEntities = workspaceBean.listAllSentMessages(staffEntity.getId());
            List<MessageHelper> sentMessageHelpers = new ArrayList<>();
            for (MessageEntity currentMessage : sentMessageEntities) {
                MessageHelper messageHelper = new MessageHelper();
                messageHelper.setMessageId(currentMessage.getId());
                messageHelper.setSenderName(currentMessage.getSender().getName());
                messageHelper.setSenderEmail(currentMessage.getSender().getEmail());
                List<String> receiversName = new ArrayList<>();
                List<String> receiversEmail = new ArrayList<>();
                for(int i=0;i<currentMessage.getReceivers().size();i++){
                    receiversName.add(currentMessage.getReceivers().get(i).getName());
                    receiversEmail.add(currentMessage.getReceivers().get(i).getEmail());
                }
                messageHelper.setReceiversName(receiversName);
                messageHelper.setReceiversEmail(receiversEmail);
                messageHelper.setMessage(currentMessage.getMessage());
                messageHelper.setSentDate(currentMessage.getSentDate());
                messageHelper.setMessageRead(currentMessage.getMessageRead());
                sentMessageHelpers.add(messageHelper);
            }
            session.setAttribute("inboxMessages", sentMessageHelpers);
            session.setAttribute("sentMessages", sentMessageHelpers);

            if (errMsg == null || errMsg.equals("")) {
                response.sendRedirect("A1/workspace_messageInbox.jsp");
            } else {
                response.sendRedirect("A1/workspace_messageInbox.jsp?errMsg=" + errMsg);
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
