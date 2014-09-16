<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />

            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Warehouse Management
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i>  <a href="#">Facility Management</a>
                                </li>                             
                                <li>
                                    <i class="icon icon-home"></i>  <a href="#">Warehouse Management</a>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel   panel-default">
                                <div class="panel-heading">

                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th>Warehouse Name</th>
                                                        <th>Address</th>
                                                        <th>Telephone</th>
                                                        <th>Email Address</th>
                                                        <th>Update</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        List<WarehouseEntity> warehouseList = (List<WarehouseEntity>) request.getAttribute("warehouseList");
                                                        if (warehouseList != null) {
                                                            for (WarehouseEntity warehouse : warehouseList) {
                                                    %>
                                                    <tr>
                                                        <td></td>
                                                        <td><%= warehouse.getWarehouseName()%></td>
                                                        <td><%= warehouse.getAddress()%></td>
                                                        <td><%= warehouse.getTelephone()%></td>
                                                        <td><%= warehouse.getEmail()%></td>
                                                        <td><button class="btn btn-primary">View</button></td>
                                                    </tr>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </tbody>
                                            </table>

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <a style="margin-left: 10px" href="../FacilityManagement_Servlet/createWarehouse_GET"><button class="btn btn-primary">Add Warehouse</button></a>
                                                    <input type="submit" onclick="" value="Delete Warehouse" class="btn btn-primary" data-loading-text="Loading...">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <%
            if (request.getAttribute("alertMessage") != null) {
        %>
        <script>
            alert("<%= request.getAttribute("alertMessage")%>");
        </script>
        <%
            }
        %>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
