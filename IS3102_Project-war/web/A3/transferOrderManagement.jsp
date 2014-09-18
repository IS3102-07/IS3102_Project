<%@page import="EntityManager.StorageBinEntity"%>
<%@page import="EntityManager.TransferOrderEntity"%>
<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>

<% WarehouseEntity warehouseEntity = (WarehouseEntity) (session.getAttribute("warehouseEntity"));
    if (warehouseEntity == null) {
        pageContext.forward("manufacturingWarehouseManagement_view.jsp");
    } else {
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateTO(id) {
                transferOrderManagement.id.value = id;
                document.transferOrderManagement.action = "transferOrderManagement_Update.jsp";
                document.transferOrderManagement.submit();
            }
            function removeTO() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.transferOrderManagement.action = "../TransferOrderManagement_RemoveServlet";
                    document.transferOrderManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function addTO() {
                window.event.returnValue = true;
                document.transferOrderManagement.action = "transferOrderManagement_Add.jsp";
                document.transferOrderManagement.submit();
            }
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <%
                try {
            %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Transfer Order Management</h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-edit"></i> <a href="manufacturingWarehouseManagement_view.jsp"><%=warehouseEntity.getWarehouseName()%></a>
                                </li>
                                <li>
                                    <i class="icon icon-edit"></i> <a href="manufacturingWarehouseManagement.jsp">Manufacturing Warehouse Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Transfer Order Management
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
                                    insert some wordings
                                </div>
                                <!-- /.panel-heading -->
                                <form name="transferOrderManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>Date Created</th>
                                                            <th>Date Transferred</th>
                                                            <th>Origin</th>
                                                            <th>Target</th>
                                                            <th>Status</th>
                                                            <th>Warehouse</th>
                                                            <th>Details </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<TransferOrderEntity> transferOrders = (List<TransferOrderEntity>) (session.getAttribute("transferOrders"));
                                                            if (transferOrders != null) {
                                                                for (int i = 0; i < transferOrders.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=transferOrders.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=transferOrders.get(i).getDateCreated()%>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    if (transferOrders.get(i).getDateTransferred() == null) {
                                                                        out.println("-");
                                                                    } else {
                                                                        out.println(transferOrders.get(i).getDateTransferred());
                                                                    }%>
                                                            </td>
                                                            <td>
                                                                Bin <%=((StorageBinEntity) transferOrders.get(i).getOrigin()).getId()%>
                                                            </td>
                                                            <td>
                                                                Bin <%=((StorageBinEntity) transferOrders.get(i).getTarget()).getId()%>
                                                            </td>
                                                            <td>
                                                                <%=transferOrders.get(i).getStatus()%>
                                                            </td>
                                                            <td>
                                                                <%=warehouseEntity.getWarehouseName()%>
                                                            </td>
                                                            <td>

                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                response.sendRedirect("manufacturingWarehouseManagement.jsp");
                                                            }
    }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Create Transfer Order" onclick="addTO()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Delete Transfer Order" onclick="removeTO()"  />
                                                </div>
                                            </div>
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
