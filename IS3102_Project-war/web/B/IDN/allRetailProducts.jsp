<%@page import="EntityManager.PromotionEntity"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page import="EntityManager.Item_CountryEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntityManager.MemberEntity"%>
<jsp:include page="checkCountry.jsp" />
<%
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
            List<Item_CountryEntity> item_countryList = (List<Item_CountryEntity>) (session.getAttribute("item_countryList"));
            List<PromotionEntity> promotions = (List<PromotionEntity>) session.getAttribute("promotions");
        %>

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

                        <div class="row">
                            <div class="col-md-6">
                                <h2 class="shorter"><strong>All Retail Products</strong></h2>
                            </div>
                        </div>
                        <div class="row">

                            <ul class="products product-thumb-info-list" data-plugin-masonry>

                                <%
                                    try {
                                        if (retailProducts != null) {
                                            for (int i = 0; i < retailProducts.size(); i++) {
                                                for (int j = 0; j < item_countryList.size(); j++) {
                                                    if (item_countryList.get(j).getItem().getId().equals(retailProducts.get(i).getId())) {
                                %>

                                <li class="col-md-3 col-sm-6 col-xs-12 product">
                                    <span class="product-thumb-info">
                                        <span class="product-thumb-info-image">
                                            <span class="product-thumb-info-act">                                                
                                                <span class="product-thumb-info-act-left"><a href="retailProductDetails.jsp?sku=<%=retailProducts.get(i).getSKU()%>"  style="color: white"><em>View Details</em></a></span>
                                            </span>
                                            <img alt="" class="img-responsive" src="../../..<%=retailProducts.get(i).getImageURL()%>">
                                        </span>

                                        <span class="product-thumb-info-content">

                                            <h4><%=retailProducts.get(i).getName()%></h4>

                                            <%
                                                String normalPrice = item_countryList.get(j).getRetailPrice() + "0 " + item_countryList.get(j).getCountry().getCurrency();
                                                PromotionEntity promotion = null;
                                                String promoPrice = "";
                                                if (promotions != null) {
                                                    for (int k = 0; k < promotions.size(); k++) {
                                                        if (promotions.get(k).getItem().getSKU().equals(retailProducts.get(i).getSKU())) {
                                                            promotion = promotions.get(k);
                                                            promoPrice = item_countryList.get(j).getRetailPrice() * (100 - promotion.getDiscountRate()) / 100 + "0 " + item_countryList.get(j).getCountry().getCurrency();
                                                        }
                                                    }
                                                }
                                            %>
                                            <%if (promotion == null) {%>
                                            <span class="product-thumb-info-act-left"><em>Price: <%=normalPrice%></em></span>
                                            <%} else {%>
                                            <span class="product-thumb-info-act-left"><em>Price: <%=promoPrice%></em></span>
                                            <%}%>
                                            <br/>
                                            <a href="retailProductDetails.jsp?sku=<%=retailProducts.get(i).getSKU()%>"><span class="product-thumb-info-act-left"><em>More Details</em></span></a>

                                        </span>
                                        <%
                                            if (displayShoppingCartOption == true) {
                                        %>

                                        <a href="../../ECommerce_AddFurnitureToListServlet?SKU=<%=retailProducts.get(i).getSKU()%>" data-toggle="modal" class="add-to-cart-product">                                                
                                            <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=retailProducts.get(i).getSKU()%>" value="Add To Wishlist"/>
                                        </a>
                                        <%
                                            }
                                        %>
                                    </span>
                                </li>
                                <%
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                %>

                            </ul>
                        </div>
                        <hr class="tall">
                    </div>
                </div>
            </div>
            <jsp:include page="footer.html" />
        </div>
    </body>
</html>
