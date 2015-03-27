<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.PromotionEntity"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="EntityManager.Item_CountryEntity"%>
<%@page import="EntityManager.StoreEntity"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntityManager.MemberEntity"%>
<jsp:include page="checkCountry.jsp" />
<%
    String sku = request.getParameter("sku");
    if (sku == null) {
%>
<jsp:forward page="index.jsp" />
<%
    }
    Boolean displayShoppingCartOption = false;
    MemberEntity member = (MemberEntity) (session.getAttribute("member"));
    if (member == null) {
        displayShoppingCartOption = false;
    } else {
        displayShoppingCartOption = true;
    }
%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <%
            List<RetailProductEntity> retailProducts = (List<RetailProductEntity>) (session.getAttribute("retailProducts"));
            RetailProductEntity retailProduct = new RetailProductEntity();
            List<StoreEntity> storesInCountry = (List<StoreEntity>) session.getAttribute("storesInCountry");
            List<Item_CountryEntity> itemCountryPrices = (List<Item_CountryEntity>) session.getAttribute("item_countryList");
            if (retailProducts != null) {
                for (int i = 0; i < retailProducts.size(); i++) {
                    if (retailProducts.get(i).getSKU().equals(sku)) {
                        retailProduct = retailProducts.get(i);
                    }
                }
            }
            PromotionEntity promotion = null;
            List<PromotionEntity> promotions = (List<PromotionEntity>) session.getAttribute("promotions");
            if (promotions != null) {
                for (int i = 0; i < promotions.size(); i++) {
                    if (promotions.get(i).getItem().getSKU().equals(sku)) {
                        promotion = promotions.get(i);
                    }
                }
            }

        %>
        <script>
            function popupwindow(url, title, w, h) {
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
            }
        </script>
        <div class="body">
            <jsp:include page="menu2.jsp" />
            <div class="body">
                <div role="main" class="main">
                    <section class="page-top">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <h2>Retail Products</h2>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="container">

                        <hr class="tall">

                        <div class="row">
                            <div class="col-md-6">
                                <div>
                                    <div class="thumbnail">
                                        <img alt="" class="img-responsive img-rounded" src="../../..<%=retailProduct.getImageURL()%>">
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="summary entry-summary">

                                    <h2 class="shorter"><strong><%=retailProduct.getName()%></strong></h2>
                                            <%
                                                if (displayShoppingCartOption == true) {
                                            %>

                                    <a href="../../ECommerce_AddFurnitureToListServlet?SKU=<%=retailProduct.getSKU()%>" data-toggle="modal" class="add-to-cart-product">                                                
                                        <input type="button" name="btnEdit" class="btn btn-primary" id="<%=retailProduct.getSKU()%>" value="Add To Wishlist"/>
                                    </a>
                                    <%
                                        }
                                        String price = "Unavailable";
                                        String promoPrice = "";
                                        String promoEndDate = "";
                                        for (Item_CountryEntity curr : itemCountryPrices) {
                                            if (curr.getItem().getSKU().equals(retailProduct.getSKU())) {
                                                if (promotion == null) {
                                                    price = curr.getRetailPrice() + "0 " + curr.getCountry().getCurrency();
                                                } else {
                                                    price = curr.getRetailPrice() + "0 " + curr.getCountry().getCurrency();
                                                    promoPrice = curr.getRetailPrice() * (100 - promotion.getDiscountRate()) / 100 + "0 " + curr.getCountry().getCurrency();
                                                    DateFormat df = new SimpleDateFormat("MMM dd");
                                                    promoEndDate = df.format(promotion.getEndDate());
                                                }
                                            }
                                        }
                                    %>
                                    <%if (promotion == null) {%>
                                    <p class="price"><h4 class="amount"><%=price%></h4></p>
                                    <%} else {%>
                                    <p class="price"><h4 class="amount"><s><%=price%></s><br/><%=promoPrice%> (Promo! Till <%=promoEndDate%>)</h4></p>
                                            <%}%>
                                    <p class="taller">
                                        <%if (retailProduct.getDescription() != null) {
                                                out.println(retailProduct.getDescription());
                                            }
                                        %>
                                    </p>
                                    <p>
                                        Height: <%=retailProduct.getHeight()%><br/>
                                        Length: <%=retailProduct.getLength()%><br/>
                                        Width: <%=retailProduct.getWidth()%>
                                    </p>
                                    <div class="product_meta">
                                        <span class="posted_in">Category: <a rel="tag" href="#"><%=retailProduct.getCategory()%></a></span>
                                    </div>
                                    <br/><br/>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <form action="../../ECommerce_StockAvailability">
                                                View Item Availability<br/>
                                                <select style="color: black;" name="storeID">
                                                    <option> </option>
                                                    <%String storeIDstring = (request.getParameter("storeID"));
                                                        Long storeID = 1L;
                                                        if (storeIDstring != null) {
                                                            storeID = Long.parseLong(storeIDstring);
                                                        }
                                                        for (int i = 0; i < storesInCountry.size(); i++) {
                                                            if (!storesInCountry.get(i).getId().equals(storeID)) {%>
                                                    <option value="<%=storesInCountry.get(i).getId()%>"><%=storesInCountry.get(i).getName()%></option>
                                                    <%} else {%>
                                                    <option selected value="<%=storesInCountry.get(i).getId()%>"><%=storesInCountry.get(i).getName()%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select><br/><br/>
                                                <input type="submit" class="btn btn-primary btn-icon" value="Check Item Availability"/>
                                                <input type="hidden" name="sku" value="<%=sku%>"/>
                                                <input type="hidden" name="type" value="Retail Product"/>
                                            </form>
                                        </div>
                                        <%
                                            String itemQty = (String) (request.getParameter("itemQty"));
                                            if (itemQty != null) {
                                        %>
                                        <div class="col-md-6">
                                            Status: <%if (Integer.parseInt(itemQty) > 0) {%>Available<%} else {%>Unavailable<%}%>
                                            <br/>
                                            Remaining Qty: <%=itemQty%>
                                            <%}%>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <hr class="tall">
                        </div>
                    </div>
                </div>
                <jsp:include page="footer.jsp" />
            </div>
    </body>
</html>
