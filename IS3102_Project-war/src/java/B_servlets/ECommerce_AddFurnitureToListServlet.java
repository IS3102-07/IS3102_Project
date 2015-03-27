package B_servlets;
//###
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

public class ECommerce_AddFurnitureToListServlet extends HttpServlet {

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
            String id = request.getParameter("id");
            String SKU = request.getParameter("SKU");
            String price = request.getParameter("price");
            String name = request.getParameter("name");
            String imageURL = request.getParameter("imageURL");
            ShoppingCartLineItem lineItem = new ShoppingCartLineItem();
            lineItem.setId(id);
            lineItem.setSKU(SKU);
            lineItem.setName(name);
            lineItem.setPrice(Double.parseDouble(price));
            lineItem.setImageURL(imageURL);
            lineItem.setCountryID(countryID);
            lineItem.setQuantity(1);

            int quantity = checkQuantity(countryID, SKU);

            ArrayList<ShoppingCartLineItem> shoppingCart = (ArrayList<ShoppingCartLineItem>) session.getAttribute("shoppingCart");
            if (quantity < 1) {
                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Item not added to cart, not enough quantity available.");
                return;
            }
            if (shoppingCart == null) {
                shoppingCart = new ArrayList<ShoppingCartLineItem>();
                shoppingCart.add(lineItem);
            } else if (!shoppingCart.contains(lineItem)) {
                shoppingCart.add(lineItem);
            } else {
                if (shoppingCart.contains(lineItem)) {
                    for (ShoppingCartLineItem item : shoppingCart) {
                        if (item.equals(lineItem)) {
                            if (quantity < item.getQuantity() + 1) {
                                response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Item not added to cart, not enough quantity available.");
                                return;
                            } else {
                                item.setQuantity(item.getQuantity() + 1);
                            }
                            System.out.println("item quantity: " + item.getQuantity());
                            break;
                        }
                    }
                }
            }
            session.setAttribute("shoppingCart", shoppingCart);

            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?goodMsg=Item successfully added into the cart!");
        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
            response.sendRedirect("/IS3102_Project-war/B/" + URLprefix + "shoppingCart.jsp?errMsg=Error while adding item to cart.");
        }
    }

    public int checkQuantity(Long countryId, String SKU) {
        try {
            System.out.println("checkQuantity() SKU: " + SKU);
            URL url = new URL("http://localhost:8080/IS3102_MobileWS/webresources/entity.countryentity/getQuantity?countryID=" + countryId + "&SKU=" + SKU);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            int qty = 0;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                qty = Integer.parseInt(output);
            }
            conn.disconnect();
            return qty;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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
