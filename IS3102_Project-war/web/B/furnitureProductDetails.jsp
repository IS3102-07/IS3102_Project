<%@page import="java.net.URLEncoder"%>
<%@page import="EntityManager.StoreEntity"%>
<%@page import="EntityManager.FurnitureEntity"%>
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
    Boolean displayWishlistOption = false;
    MemberEntity member = (MemberEntity) (session.getAttribute("member"));
    if (member == null) {
        displayWishlistOption = false;
    } else {
        displayWishlistOption = true;
    }
%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <%
            List<FurnitureEntity> furnitures = (List<FurnitureEntity>) (session.getAttribute("furnitures"));
            FurnitureEntity furniture = new FurnitureEntity();
            List<StoreEntity> storesInCountry = (List<StoreEntity>) session.getAttribute("storesInCountry");

            if (furnitures != null) {
                for (int i = 0; i < furnitures.size(); i++) {
                    if (furnitures.get(i).getSKU().equals(sku)) {
                        furniture = furnitures.get(i);
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
                                    <h2>Furnitures</h2>
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
                                        <img alt="" class="img-responsive img-rounded" src="../..<%=furniture.getImageURL()%>">
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="summary entry-summary">

                                    <h2 class="shorter"><strong><%=furniture.getName()%></strong></h2>
                                            <%
                                                if (displayWishlistOption == true) {
                                            %>
                                    <!--
                                                                        <a href="../ECommerce_AddFurnitureToListServlet?SKU=<%=furniture.getSKU()%>" data-toggle="modal" class="add-to-cart-product">                                                
                                                                            <input type="button" name="btnEdit" class="btn btn-primary" id="<%=furniture.getSKU()%>" value="Add To Wishlist"/>
                                                                        </a>
                                    -->
                                    <%
                                        }
                                    %>
                                    <p class="taller">
                                        <%if (furniture.getDescription() != null) {
                                                out.println(furniture.getDescription());
                                            }%>
                                    </p>
                                    <p>
                                        Height: <%=furniture.getHeight()%><br/>
                                        Length: <%=furniture.getLength()%><br/>
                                        Width: <%=furniture.getWidth()%>
                                    </p>
                                    <div class="product_meta">
                                        <span class="posted_in">Category: <a rel="tag" href="../ECommerce_FurnitureCategoryServlet?cat=<%=URLEncoder.encode(furniture.getCategory())%>"><%=furniture.getCategory()%></a></span>
                                    </div>
                                    <br/><br/>
                                </div>
                            </div>

                            <hr class="tall">
                        </div>
                    </div>
                </div>
                <jsp:include page="footer.html" />
            </div>
    </body>
</html>
