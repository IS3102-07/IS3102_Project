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
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="manufacturingWarehouseManagement_view.jsp"> Manufacturing Warehouse Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> <%=warehouseEntity.getWarehouseName()%>
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
                                        <a href="../StorageBinManagement_Servlet"><i class="icon-featured icon icon-user"> </i>
                                            <h4>Storage Bin Management</h4>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="featured-box featured-box-secundary">
                                    <div class="box-content">
                                        <a href="../TransferOrderManagement_Servlet"><i class="icon-featured icon icon-user"> </i>
                                            <h4>Transfer Order Management</h4>
                                        </a>
                                    </div>
                                </div>
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
                }%>
        </div>

    </div>
    <!-- /#wrapper -->
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable();
        });
    </script>
</body>
</html>
