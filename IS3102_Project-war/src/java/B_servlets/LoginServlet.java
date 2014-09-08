package B_servlets;

import CommonInfrastructureModule.CommonInfrastructureBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
public class LoginServlet extends HttpServlet {
    CommonInfrastructureBeanLocal commonInfrastructureBean = lookupCommonInfrastructureBeanLocal();

    
    private String result;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session = request.getSession();
            session.removeAttribute("member");

//            List<String> memberDetails = shsmbr.getMember(passportNumber, password);
//            if (memberDetails == null) {
//                out.println("1");
//                result = "Login fail. Please try again.";
//                response.sendRedirect("index.jsp?errMsg=" + result);
//            } else {
//                memberDetails.get(0);
//                out.println("2");
//                session.setAttribute("member", memberDetails);
//                response.sendRedirect("index.jsp");
//            }
        } catch (Exception ex) {
            out.println("3");
            result = "Login fail. Please try again.";
            response.sendRedirect("index.jsp?errMsg=" + result);
        } finally {
            out.close();
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

    private CommonInfrastructureBeanLocal lookupCommonInfrastructureBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CommonInfrastructureBeanLocal) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/CommonInfrastructureBean!CommonInfrastructureModule.CommonInfrastructureBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
