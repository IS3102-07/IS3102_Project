<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="EntityManager.Item_CountryEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntityManager.MemberEntity"%>
<jsp:include page="checkCountry.jsp" />
<%
    Boolean displayCartOption = false;
    MemberEntity member = (MemberEntity) (session.getAttribute("member"));
    if (member == null) {
        displayCartOption = false;
    } else {
        displayCartOption = true;
    }
%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <%
            List<FurnitureEntity> furnitures = (List<FurnitureEntity>) (session.getAttribute("furnitures"));
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
                                    <h2>Furnitures</h2>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="container">

                        <div class="row">
                            <div class="col-md-6">
                                <h2 class="shorter"><strong>Shop</strong></h2>
                                <p>Showing <%=furnitures.size()%> results.</p>
                            </div>
                        </div>
                        <div class="row">

                            <ul class="products product-thumb-info-list" data-plugin-masonry>

                                <%

                                    try {
                                        if (furnitures != null) {
                                            for (int i = 0; i < furnitures.size(); i++) {
                                %>

                                <li class="col-md-3 col-sm-6 col-xs-12 product">
                                    <span class="product-thumb-info">
                                        <%
                                            if (displayCartOption == true) {
                                        %>

                                        <a href="../ECommerce_AddFurnitureToListServlet?SKU=<%=furnitures.get(i).getSKU()%>" data-toggle="modal" class="add-to-cart-product">                                                
                                            <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=furnitures.get(i).getSKU()%>" value="Add To Cart"/>
                                        </a>
                                        <%
                                            }
                                        %>

                                        <span class="product-thumb-info-image">
                                            <span class="product-thumb-info-act">                                                
                                                <span class="product-thumb-info-act-left"><a href="productDetails.jsp?sku=<%=furnitures.get(i).getSKU()%>"  style="color: white"><em>View Details</em></a></span>
                                            </span>
                                            <img alt="" class="img-responsive" src="../img/products/<%=i % 5%>.JPG">
                                        </span>

                                        <span class="product-thumb-info-content">

                                            <h4><%=furnitures.get(i).getName()%></h4>

                                            <span class="product-thumb-info-act-left"><em>Height: <%=furnitures.get(i).getHeight()%></em></span><br/>
                                            <span class="product-thumb-info-act-left"><em>Length: <%=furnitures.get(i).getLength()%></em></span><br/>
                                            <span class="product-thumb-info-act-left"><em>Width: <%=furnitures.get(i).getWidth()%></em></span><br/>
                                            <%
                                                for (int j = 0; j < item_countryList.size(); j++) {
                                                    if (item_countryList.get(j).getItem().getId().equals(furnitures.get(i).getId())) {
                                            %>
                                            <span class="product-thumb-info-act-left"><em>Price: $ <%=item_countryList.get(j).getRetailPrice()%></em></span>

                                            <%
                                                        break;
                                                    }
                                                }

                                            %>
                                            <br/>
                                            <a href="productDetails.jsp?sku=<%=furnitures.get(i).getSKU()%>"><span class="product-thumb-info-act-left"><em>View Availability <%%></em></span></a>

                                        </span>
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
