<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>

        <script>
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
        </script>

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
                                    <i class="icon icon-home"></i>  <a href="../A6/facilityManagement.jsp">Facility Management</a>
                                </li>                             
                                <li>
                                    <i class="icon icon-home"></i> Warehouse Management
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">

                                </div>
                                <!-- /.panel-heading -->

                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <form action="../FacilityManagement_Servlet/createWarehouse_GET">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input type="submit" name="submit-btn" value="Add Warehouse" class="btn btn-primary" data-loading-text="Loading...">
                                                    <input type="submit" name="submit-btn" value="Delete Warehouse" class="btn btn-primary" data-loading-text="Loading...">
                                                </div>
                                            </div>
                                            <br/>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">

                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>Warehouse Name</th>
                                                            <th>Address</th>
                                                            <th>Telephone</th>
                                                            <th>Email Address</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<WarehouseEntity> warehouseList = (List<WarehouseEntity>) request.getAttribute("warehouseList");
                                                            if (warehouseList != null) {
                                                                for (WarehouseEntity warehouse : warehouseList) {
                                                        %>
                                                        <tr>
                                                            <td><input type="checkbox" name="delete" value="<%= warehouse.getId()%>" /></td>
                                                            <td><%= warehouse.getWarehouseName()%></td>
                                                            <td><%= warehouse.getAddress()%></td>
                                                            <td><%= warehouse.getTelephone()%></td>
                                                            <td><%= warehouse.getEmail()%></td>
                                                            <td><button class="btn btn-primary" name="submit-btn" value="<%= warehouse.getId() %>">View</button></td>                                                            
                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>

                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input type="submit" name="submit-btn" value="Add Warehouse" class="btn btn-primary" data-loading-text="Loading...">
                                                        <input type="submit" name="submit-btn" value="Delete Warehouse" class="btn btn-primary" data-loading-text="Loading...">
                                                    </div>
                                                </div>
                                        </form>    
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
        $(document).ready(function() {
            $('#dataTables-example').dataTable();
        });
    </script>

</body>

</html>
