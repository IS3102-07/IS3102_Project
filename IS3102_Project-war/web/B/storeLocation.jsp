<%@page import="java.util.List"%>
<%@page import="EntityManager.StoreEntity"%>
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
                                <h2>Store Locations</h2>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="container">


                    <div class="container">
                        <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Store Locations
                                </div>
                                <!-- /.panel-heading -->
                                <form name="furnitureManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">


                                            <br/>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            
                                                            <th><h4 class="shorter">Name</h4></th>
                                                            <th><h4 class="shorter">Address</h4></th>
                                                            <th><h4 class="shorter">Email</h4></th>
                                                            <th><h4 class="shorter">Telephone</h4></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<StoreEntity> stores = (List<StoreEntity>) (session.getAttribute("stores"));

                                                            try {
                                                                if (stores != null) {
                                                                    for (int i = 0; i < stores.size(); i++) {
                                                        %>
                                                        <tr>
                                                            
                                                            <td><p class="tall">
                                                                <%=stores.get(i).getName()%>
                                                                </p>
                                                            </td>
                                                            <td><p class="tall">
                                                                <%=stores.get(i).getAddress()%>
                                                                </p>
                                                            </td>
                                                            <td><p class="tall">
                                                                <%=stores.get(i).getEmail()%>
                                                                </p>
                                                            </td>
                                                            <td><p class="tall">
                                                                <%=stores.get(i).getTelephone()%>
                                                                </p>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                System.out.println(ex);
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            
                                            <input type="hidden" name="id" value="">
                                        </div>

                                    </div>
                                    <!-- /.panel-body -->
                                </form>

                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    </div>



                    <div class="container">

                        <div class="row">
                            <div class="col-md-8">
                                <h2>Our <strong>Features</strong></h2>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-group"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Customer Support</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-file"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">HTML5 / CSS3 / JS</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-google-plus"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">500+ Google Fonts</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-adjust"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Colors</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-film"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Sliders</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-user"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Icons</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-bars"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Buttons</h4>
                                                <p class="tall">Lorem ipsum dolor sit, consectetur adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-desktop"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Lightbox</h4>
                                                <p class="tall">Lorem sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <h2>and more...</h2>

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
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                                    <i class="icon icon-comment"></i>
                                                    Contact Forms
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseTwo" class="accordion-body collapse">
                                            <div class="panel-body">
                                                Donec tellus massa, tristique sit amet condimentum vel, facilisis quis sapien.
                                            </div>
                                        </div>
                                    </div>
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

                        <div class="row center">
                            <div class="col-md-12">
                                <h2 class="short word-rotator-title">
                                    We're not the only ones
                                    <strong>
                                        <span class="word-rotate">
                                            <span class="word-rotate-items">
                                                <span>excited</span>
                                                <span>happy</span>
                                            </span>
                                        </span>
                                    </strong>
                                    about Porto Template...
                                </h2>
                                <h4 class="lead tall">5,500 customers in 100 countries use Porto Template. Meet our customers.</h4>
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
    </body>
</html>
