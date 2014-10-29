<%@page import="EntityManager.MenuItemEntity"%>
<%@page import="EntityManager.Item_CountryEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntityManager.MemberEntity"%>
<jsp:include page="checkCountry.jsp" />
<%
    Boolean displayWishlistOption = false;
    MemberEntity member = (MemberEntity) (session.getAttribute("member"));
    if (member == null) {
        displayWishlistOption = false;
    } else {
        displayWishlistOption = true;
    }
%>
<html> <!--<![endif]-->
    <jsp:include page="/B/header.html" />
    <body>
        <%
            List<MenuItemEntity> menuItems = (List<MenuItemEntity>) (session.getAttribute("menuItems"));
            List<Item_CountryEntity> item_countryList = (List<Item_CountryEntity>) (session.getAttribute("item_countryList"));
        %>

        <div class="body">
            <jsp:include page="menu2.jsp" />
            <div class="body">
                <div role="main" class="main">
                    <section class="page-top">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <h2>Restaurant</h2>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="container">

                        <div class="row">
                            <div class="col-md-6">
                                <h2 class="shorter"><strong>Restaurant Menu</strong></h2>
                                <p>Showing <%=menuItems.size()%> results.</p>
                            </div>
                        </div>
                        <div class="row">

                            <ul class="products product-thumb-info-list" data-plugin-masonry>

                                <%

                                    try {
                                        if (menuItems != null) {
                                            for (int i = 0; i < menuItems.size(); i++) {
                                %>

                                <li class="col-md-3 col-sm-6 col-xs-12 product">
                                    <span class="product-thumb-info">
                                        <span class="product-thumb-info-image">
                                            <span class="product-thumb-info-act">                                                
                                                <span class="product-thumb-info-act-left"><a href="restaurantProductDetails.jsp?sku=<%=menuItems.get(i).getSKU()%>"  style="color: white"><em>View Details</em></a></span>
                                            </span>
                                            <img alt="" class="img-responsive" src="<%=menuItems.get(i).getImageURL()%>">
                                        </span>

                                        <span class="product-thumb-info-content">

                                            <h4><%=menuItems.get(i).getName()%></h4>

                                            <%
                                                for (int j = 0; j < item_countryList.size(); j++) {
                                                    if (item_countryList.get(j).getItem().getId().equals(menuItems.get(i).getId())) {
                                            %>
                                            <span class="product-thumb-info-act-left"><em>Price: $ <%=item_countryList.get(j).getRetailPrice()%></em></span>

                                            <%
                                                        break;
                                                    }
                                                }

                                            %>
                                            <br/>
                                            <a href="restaurantProductDetails.jsp?sku=<%=menuItems.get(i).getSKU()%>"><span class="product-thumb-info-act-left"><em>More Details</em></span></a>

                                        </span>
                                        <%
                                            if (displayWishlistOption == true) {
                                        %>

                                        <a href="../ECommerce_AddFurnitureToListServlet?SKU=<%=menuItems.get(i).getSKU()%>" data-toggle="modal" class="add-to-cart-product">                                                
                                            <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=menuItems.get(i).getSKU()%>" value="Add To Wishlist"/>
                                        </a>
                                        <%
                                            }
                                        %>
                                    </span>
                                </li>
                                <%                                            }
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
