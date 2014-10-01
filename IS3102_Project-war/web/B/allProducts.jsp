<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>

        <div class="body">
            <jsp:include page="menu1.html" />

            <div role="main" class="main">
                <section class="page-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>All Products</h2>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="container">                    
                    <div class="container">
                        <div class="row">                            
                            <div class="col-md-12">
                                <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                                    <i class="icon icon-usd"></i>
                                                    Pricing Tables
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseOne" class="accordion-body collapse in">
                                            <div class="panel-body">
                                                Donec tellus massa, tristique sit amet condim vel, facilisis quis sapien. Praesent id enim sit amet odio vulputate eleifend in in tortor.
                                            </div>
                                        </div>
                                    </div>

                                    <%
                                        List<FurnitureEntity> furnitures = (List<FurnitureEntity>) (session.getAttribute("furnitures"));

                                        if (furnitures != null) {
                                            for (int i = 0; i < furnitures.size(); i++) {

                                    %>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse<%=i%>">
                                                    <i class="icon icon-comment"></i>
                                                    <%=furnitures.get(i).getName()%>
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapse<%=i%>" class="accordion-body collapse">
                                            <div class="panel-body">
                                                <%=furnitures.get(i).getDescription()%>
                                            </div>
                                        </div>
                                    </div>

                                    <%                                            
                                            }
                                        }

                                    %>
                                    
                                    <%
                                        List<RetailProductEntity> retailProducts = (List<RetailProductEntity>) (session.getAttribute("retailProducts"));

                                        if (furnitures != null) {
                                            for (int i = 0; i < retailProducts.size(); i++) {

                                    %>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse<%=i%>">
                                                    <i class="icon icon-comment"></i>
                                                    <%=retailProducts.get(i).getName()%>
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapse<%=i%>" class="accordion-body collapse">
                                            <div class="panel-body">
                                                <%=retailProducts.get(i).getDescription()%>
                                            </div>
                                        </div>
                                    </div>

                                    <%                                            
                                            }
                                        }

                                    %>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                                    <i class="icon icon-laptop"></i>
                                                    Portfolio Pages
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseThree" class="accordion-body collapse">
                                            <div class="panel-body">
                                                Donec tellus massa, tristique sit amet condimentum vel, facilisis quis sapien.
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                        </div>
                        <hr class="tall" />                        
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
    </body>
</html>
