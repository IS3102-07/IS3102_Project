<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<jsp:include page="checkCountry.jsp" />
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
        </script>

        <div class="body">
            <jsp:include page="menu2.jsp" />
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
                            <div class="col-lg-12">
                                    <div class="row featured-boxes">
                                        <div class="col-md-12">
                                            
                                            <div class="col-md-4">
                                                <div class="featured-box featured-box-secundary">
                                                    <div class="box-content">
                                                        <a href="../ECommerce_AllProductsServlet"><i class="icon-featured icon icon-archive"> </i>
                                                            
                                                            <h4>Furnitures</h4>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="col-md-4">
                                                <div class="featured-box featured-box-primary">
                                                    <div class="box-content">
                                                        <a href="../ECommerce_AllFoodsServlet"><i class="icon-featured icon icon-cutlery"> </i>
                                                            <h4>Food</h4>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="featured-box featured-box-tertiary">
                                                    <div class="box-content">
                                                        <a href="../ECommerce_AllRetailProductsServlet"><i class="icon-featured icon icon-coffee"> </i>
                                                            <h4>Retail Products</h4>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                    </div>

                            </div>
                            <!-- /.col-lg-12 -->
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
