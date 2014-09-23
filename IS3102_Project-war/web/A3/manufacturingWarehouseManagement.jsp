<%@page import="EntityManager.WarehouseEntity"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />

            <%
                try {
                    WarehouseEntity warehouseEntity = (WarehouseEntity) (session.getAttribute("warehouseEntity"));
            %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Manufacturing Warehouse Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i> <a href="manufacturingWarehouseManagement_view.jsp">Manufacturing Warehouse Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-home"></i> <%=warehouseEntity.getWarehouseName()%>
                                </li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                    <div class="row featured-boxes">
                        <div class="col-md-12">
                            <div class="col-md-4">
                                <div class="featured-box featured-box-primary">
                                    <div class="box-content">
                                        <a href="../StorageBinManagement_Servlet"><i class="icon-featured icon icon-archive"> </i>
                                            <h4>Storage Bin Management</h4>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="featured-box featured-box-secundary">
                                    <div class="box-content">
                                        <a href="../TransferOrderManagement_Servlet"><i class="icon-featured icon icon-exchange"> </i>
                                            <h4>Transfer Order Management</h4>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="featured-box featured-box-secundary">
                                    <div class="box-content">
                                        <a href="../ManufacturingInventoryControl_Servlet"><i class="icon-featured icon icon-th"> </i>
                                            <h4>Inventory Control</h4>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-long-arrow-right fa-fw"></i> Warehouse Capacity</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="progress-bars">
                                        <div class="progress-label">
                                            <span>HTML/CSS</span>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary" data-appear-progress-animation="100%">
                                                <span class="progress-bar-tooltip">100%</span>
                                            </div>
                                        </div>
                                        <div class="progress-label">
                                            <span>Design</span>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary" data-appear-progress-animation="85%" data-appear-animation-delay="300">
                                                <span class="progress-bar-tooltip">85%</span>
                                            </div>
                                        </div>
                                        <div class="progress-label">
                                            <span>WordPress</span>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary" data-appear-progress-animation="75%" data-appear-animation-delay="600">
                                                <span class="progress-bar-tooltip">75%</span>
                                            </div>
                                        </div>
                                        <div class="progress-label">
                                            <span>Photoshop</span>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-primary" data-appear-progress-animation="85%" data-appear-animation-delay="900">
                                                <span class="progress-bar-tooltip">85%</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-right">
                                        <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Recent Item Movement</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="list-group">
                                        <a href="#" class="list-group-item">
                                            <span class="badge">just now</span>
                                            <i class="fa fa-fw fa-calendar"></i> Calendar updated
                                        </a>
                                        <a href="#" class="list-group-item">
                                            <span class="badge">4 minutes ago</span>
                                            <i class="fa fa-fw fa-comment"></i> Commented on a post
                                        </a>
                                        <a href="#" class="list-group-item">
                                            <span class="badge">23 minutes ago</span>
                                            <i class="fa fa-fw fa-truck"></i> Order 392 shipped
                                        </a>
                                        <a href="#" class="list-group-item">
                                            <span class="badge">46 minutes ago</span>
                                            <i class="fa fa-fw fa-money"></i> Invoice 653 has been paid
                                        </a>
                                        <a href="#" class="list-group-item">
                                            <span class="badge">1 hour ago</span>
                                            <i class="fa fa-fw fa-user"></i> A new user has been added
                                        </a>
                                        <a href="#" class="list-group-item">
                                            <span class="badge">2 hours ago</span>
                                            <i class="fa fa-fw fa-check"></i> Completed task: "pick up dry cleaning"
                                        </a>
                                        <a href="#" class="list-group-item">
                                            <span class="badge">yesterday</span>
                                            <i class="fa fa-fw fa-globe"></i> Saved the world
                                        </a>
                                        <a href="#" class="list-group-item">
                                            <span class="badge">two days ago</span>
                                            <i class="fa fa-fw fa-check"></i> Completed task: "fix error on sales page"
                                        </a>
                                    </div>
                                    <div class="text-right">
                                        <a href="../TransferOrderManagement_Servlet">View All Activity <i class="fa fa-arrow-circle-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-money fa-fw"></i> Recent Transfer Orders</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th>Order #</th>
                                                    <th>Order Placed</th>
                                                    <th>Order Completed</th>
                                                    <th>Destination</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>3326</td>
                                                    <td>10/21/2013</td>
                                                    <td>3:29 PM</td>
                                                    <td>$321.33</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="text-right">
                                        <a href="../TransferOrderManagement_Servlet">View All Transfer Orders <i class="fa fa-arrow-circle-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.container-fluid -->
                    </div>
                    <!-- /#page-wrapper -->

                    <%
                        } catch (Exception ex) {
                            response.sendRedirect("../ManufacturingWarehouseManagement_Servlet");
                            ex.printStackTrace();
                        }%>
                </div>
            </div>
            <!-- /#wrapper -->


            <!-- Vendor -->
            <script src="../vendor/jquery.js"></script>
            <script src="../vendor/jquery.appear.js"></script>
            <script src="../vendor/jquery.easing.js"></script>

            <!-- Theme Base, Components and Settings -->
            <script src="../js/theme.js"></script>

            <!-- Theme Initialization Files -->
            <script src="../js/theme.init.js"></script>
    </body>
</html>
