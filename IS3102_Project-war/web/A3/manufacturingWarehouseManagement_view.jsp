<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateManufacturingWarehouse(warehouseId) {
                manufacturingWarehouseManagement.warehouseId.value = warehouseId;
                document.manufacturingWarehouseManagement.action = "manufacturingWarehouseManagement.jsp";
                document.manufacturingWarehouseManagement.submit();
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Warehouse Manufacturing Management</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Select a warehouse
                                </div>
                                <!-- /.panel-heading -->
                                <form name="manufacturingWarehouseManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th>Name</th>
                                                            <th>Country</th>
                                                            <th>Address</th>
                                                            <th>Email</th>
                                                            <th>Phone</th>
                                                            <th>Select</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<WarehouseEntity> warehouses = (List<WarehouseEntity>) (session.getAttribute("warehouses"));
                                                            if (warehouses != null) {
                                                                for (int i = 0; i < warehouses.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <%=warehouses.get(i).getWarehouseName()%>
                                                            </td>
                                                            <td>
                                                                <%=warehouses.get(i).getAddress()%>
                                                            </td>
                                                            <td>
                                                                <%=warehouses.get(i).getAddress()%>
                                                            </td>
                                                            <td>
                                                                <%=warehouses.get(i).getEmail()%>
                                                            </td>
                                                            <td>
                                                                <%=warehouses.get(i).getTelephone()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=warehouses.get(i).getId()%>" onclick="javascript:updateManufacturingWarehouse('<%=warehouses.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                }
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
                    <!-- /.row -->


                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- /#page-wrapper -->

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
