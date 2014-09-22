<%@page import="HelperClasses.ItemStorageBinHelper"%>
<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="EntityManager.StorageBinEntity"%>
<%@page import="java.util.List"%>
<% WarehouseEntity warehouseEntity = (WarehouseEntity) (session.getAttribute("warehouseEntity"));
    if (warehouseEntity == null) {
        response.sendRedirect("../ManufacturingWarehouseManagement_Servlet");
    } else {
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function removeItemFromStorageBin(id) {
                var yes = confirm("Are you sure you want to delete the all instance of this item in this storage bin?\nThis action cannot be reversed!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.inventoryControl.storageBinID.value=id;
                    document.inventoryControl.action = "../ManufacturingInventoryControl_RemoveServlet";
                    document.inventoryControl.submit();
                } else {
                    window.event.returnValue = false;
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
                            <h1 class="page-header">Inventory Control</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i> <a href="manufacturingWarehouseManagement_view.jsp">Manufacturing Warehouse Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-home"></i> <a href="manufacturingWarehouseManagement.jsp"><%=warehouseEntity.getWarehouseName()%></a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-th"></i> Inventory Control
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
                                    <%
                                        String errMsg = request.getParameter("errMsg");
                                        if (errMsg == null || errMsg.equals("")) {
                                            errMsg = "The following are the list of items in each storage bin";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="inventoryControl">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th>SKU</th>
                                                            <th>Item Name</th>
                                                            <th>Storage Bin ID</th>
                                                            <th>Item Quantity</th>
                                                            <th>Item Type</th>
                                                            <th>Delete</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<ItemStorageBinHelper> itemStorageBinHelpers = (List<ItemStorageBinHelper>) (session.getAttribute("itemStorageBinHelpers"));
                                                            if (itemStorageBinHelpers != null) {
                                                                for (int i = 0; i < itemStorageBinHelpers.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <%=itemStorageBinHelpers.get(i).getSKU()%>
                                                            </td>
                                                            <td>
                                                                <%=itemStorageBinHelpers.get(i).getItemName()%>
                                                            </td>
                                                            <td>
                                                                <%=itemStorageBinHelpers.get(i).getStorageBinID()%>
                                                            </td>
                                                            <td>
                                                                <%=itemStorageBinHelpers.get(i).getItemQty()%>
                                                            </td>
                                                            <td>
                                                                <%=itemStorageBinHelpers.get(i).getItemType()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" value="Remove" onclick="removeItemFromStorageBin('<%=itemStorageBinHelpers.get(i).getStorageBinID()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                ex.printStackTrace();
                                                                response.sendRedirect("manufacturingWarehouseManagement.jsp");
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <input type="hidden" name="storageBinID" value="">    
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
<%}%>