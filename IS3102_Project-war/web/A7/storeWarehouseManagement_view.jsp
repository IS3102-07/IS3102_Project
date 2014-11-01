<%@page import="java.util.ArrayList"%>
<%@page import="EntityManager.AccessRightEntity"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>
<%
    StaffEntity staffEntity = (StaffEntity) (session.getAttribute("staffEntity"));
    List<AccessRightEntity> accessRights = staffEntity.getAccessRightList();
    ArrayList<Long> warehouseId = new ArrayList();
    for (int i = 0; i < accessRights.size(); i++) {
        if (accessRights.get(i).getWarehouse() != null) {
            warehouseId.add(accessRights.get(i).getWarehouse().getId());
        }
    }
    Boolean canAccess = false;
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateStoreWarehouse(id, destination) {
                window.location.href = "../RetailWarehouseManagement_Servlet?id=" + id + "&destination=" + destination;
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header"> Store Inventory Management</h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-home"></i> Store Inventory Management
                                </li>
                            </ol>
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
                                <form name="storeWarehouseManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
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
                                                                <%=warehouses.get(i).getId()%>
                                                            </td>
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
                                                                <% canAccess = false;
                                                                    for (int j = 0; j < warehouseId.size(); j++) {
                                                                        if (warehouseId.get(j) == warehouses.get(i).getId()) {
                                                                            canAccess = true;
                                                                        }
                                                                    }
                                                                    if (canAccess) {%>
                                                                <input type="button" name="btnEdit" value="Select" class="btn btn-primary btn-block"  onclick="javascript:updateStoreWarehouse('<%=warehouses.get(i).getId()%>', 'storeWarehouseManagement.jsp')"/>
                                                                <%} else {%>
                                                                <input type="button" name="btnEdit" value="Select" class="btn btn-primary btn-block"  disabled/>
                                                                <%}%>
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
            $(document).ready(function() {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
