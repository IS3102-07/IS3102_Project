package B_servlets;
//###-->
import EntityManager.MemberEntity;
import HelperClasses.ShoppingCartLineItem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ECommerce_PaymentServlet extends HttpServlet {

    private String URLprefix = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("ECommerce_AddFurnitureToListServlet");
        try {
            HttpSession session = request.getSession();

            URLprefix = (String) session.getAttribute("URLprefix");
            if (URLprefix == null) {
                response.sendRedirect("/IS3102_Project-war/B/selectCountry.jsp");
                return;
            }

            Long countryID = (Long) session.getAttribute("countryID");
            MemberEntity m = (MemberEntity) session.getAttribute("member");
            Long memberId = m.getId();

            ArrayList<ShoppingCartLineItem> shoppingCart = (ArrayList<ShoppingCartLineItem>) session.getAttribute("shoppingCart");

            double amountPaid = 0.0;
            for (ShoppingCartLineItem item : shoppingCart) {
                amountPaid += item.getPrice() * item.getQuantity();
            }
            String salesRecordID = createECommerceTransactionRecord(memberId, amountPaid, countryID);
            if (salesRecordID.equals("0")) {
                //error
                System.out.println("Error creating ECommerce Transaction Record. Sales record ID returned 0.");
                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error processing transaction.");

                return;
            }
            for (ShoppingCartLineItem item : shoppingCart) {
                String itemID = item.getId();
                int quantity = item.getQuantity();
                //call ws to insert lineitem and salesrecordentity_lineitementity based on salesRecordID and lineItemID
                String result = createECommerceLineItemRecord(salesRecordID, itemID, quantity, countryID);
                if (!result.equals("0")) {
                    System.out.println("createECommerceLineItemRecord successful");
                } else {
                    System.out.println("Error creating createECommerceLineItemRecord, returned 0.");
                    response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error checking out.");
                    return;
                }
            }

            session.setAttribute("shoppingCart", null);

            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?goodMsg=Thank you for shopping at Island Furniture. You have checkout successfully!");
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error checking out.");
        }
    }

    public String createECommerceLineItemRecord(String salesRecordID, String itemID, int quantity, Long countryID) {
        try {
            URL url = new URL("http://localhost:8080/IS3102_MobileWS/webresources/generic/createECommerceLineItemRecord?salesRecordID=" + salesRecordID + "&itemID=" + itemID + "&quantity=" + quantity + "&countryID=" + countryID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            String result = "";
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                result = output;
            }
            conn.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public String createECommerceTransactionRecord(Long memberId, Double amountPaid, Long countryId) {
        try {
            URL url = new URL("http://localhost:8080/IS3102_MobileWS/webresources/generic/createECommerceTransactionRecord?memberID=" + memberId + "&amountPaid=" + amountPaid + "&countryID=" + countryId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            Long id = 0L;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                id = Long.parseLong(output);
            }
            conn.disconnect();
            return id + "";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
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
