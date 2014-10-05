<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <script>
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
            function addItem() {
                window.event.returnValue = true;
                id = document.getElementsByName('item');
                document.allFurnitures.action = "../ECommerce_AddFurnitureToListServlet?id=" + id;
                document.allFurnitures.submit();
            }
        </script>

        <div class="body">
            <jsp:include page="menu1.html" />
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
                                <h2 class="shorter"><strong>Shop</strong></h2>
                                <p>Showing 1–12 of 25 results.</p>
                            </div>
                        </div>
                        <div class="row">
                            
                                <ul class="products product-thumb-info-list" data-plugin-masonry>

                                    <%
                                        List<FurnitureEntity> furnitures = (List<FurnitureEntity>) (session.getAttribute("furnitures"));

                                        try {
                                            if (furnitures != null) {
                                                for (int i = 0; i < furnitures.size(); i++) {
                                    %>
                                    
                                    <li class="col-md-3 col-sm-6 col-xs-12 product">
                                        <span class="product-thumb-info">
                                            <a href="../ECommerce_AddFurnitureToListServlet" data-toggle="modal" class="add-to-cart-product">                                                
                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=furnitures.get(i).getId()%>" value="Add To Cart"/>
                                            </a>
                                            <a href="shop-product-sidebar.html">
                                                <span class="product-thumb-info-image">
                                                    <span class="product-thumb-info-act">
                                                        <span class="product-thumb-info-act-left"><em><%=furnitures.get(i).getDescription()%></em></span>
                                                    </span>
                                                    <img alt="" class="img-responsive" src="../img/products/<%=i % 5%>.JPG">
                                                    
                                                </span>
                                            </a>
                                            <span class="product-thumb-info-content">
                                                <a href="shop-product-sidebar.html">
                                                    <h4><%=furnitures.get(i).getName()%></h4>
                                                    <span class="price">
                                                        <span class="amount">$72</span>
                                                    </span>
                                                </a>
                                            </span>
                                        </span>
                                    </li>
                                    <%
                                                }
                                            }
                                        } catch (Exception ex) {
                                            System.out.println(ex);
                                        }
                                    %>


                                </ul>
                            
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <ul class="pagination pull-right">
                                    <li><a href="#"><i class="fa fa-chevron-left"></i></a></li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#"><i class="fa fa-chevron-right"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.html" />
        </div>
        <!-- Theme Initializer -->
        <script src="../js/theme.plugins.js"></script>
        <script src="../js/theme.js"></script>

        <!-- Current Page JS -->
        <script src="../vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
        <script src="../vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
        <script src="../vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
        <script src="../js/views/view.home.js"></script>   
    </div>

    <div role="dialog" class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Alert</h4>
                </div>
                <div class="modal-body">
                    <p id="messageBox">Item will be added to shopping list.</p>
                </div>
                <div class="modal-footer">                        
                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Confirm" onclick="addItem()"/>
                    <a class="btn btn-default" data-dismiss ="modal">Close</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
