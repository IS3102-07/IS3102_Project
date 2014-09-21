<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="EntityManager.SupplierEntity"%>
<%@page import="EntityManager.PurchaseOrderEntity"%>
<%@page import="java.util.List"%>

<% List<PurchaseOrderEntity> purchaseOrders = (List<PurchaseOrderEntity>) (session.getAttribute("purchaseOrders"));
    if (purchaseOrders == null) {
        response.sendRedirect("../PurchaseOrderManagement_Servlet");
    } else {
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updatePO(id) {
                purchaseOrderManagement.id.value = id;
                document.purchaseOrderManagement.action = "purchaseOrderManagement_Update.jsp";
                document.purchaseOrderManagement.submit();
            }
            function addPO() {
                window.event.returnValue = true;
                document.purchaseOrderManagement.action = "purchaseOrderManagement_Add.jsp";
                document.purchaseOrderManagement.submit();
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
                            <h1 class="page-header">Purchase Order Management</h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-exchange"></i> Purchase Order Management
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
                                            errMsg = "Insert some text";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="purchaseOrderManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th>Purchase Order ID</th>
                                                            <th>Supplier</th>
                                                            <th>Shipping Destination</th>
                                                            <th>Expected Receiving Date</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (purchaseOrders != null) {
                                                                for (int i = 0; i < purchaseOrders.size(); i++) {
                                                                    SupplierEntity supplier = purchaseOrders.get(i).getSupplier();
                                                                    WarehouseEntity warehouse = purchaseOrders.get(i).getReceivedWarehouse();
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <%=purchaseOrders.get(i).getId()%>
                                                            </td>
                                                            <td>
                                                                <%=supplier.getSupplierName()%>
                                                            </td>
                                                            <td>
                                                                <%=warehouse.getWarehouseName()%>
                                                            </td>
                                                            <td>
                                                                <%=purchaseOrders.get(i).getExpectedReceivedDate()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary" id="<%=purchaseOrders.get(i).getId()%>" value="Update" onclick="javascript:updatePO('<%=purchaseOrders.get(i).getId()%>')"/>
                                                                <input type="button" name="btnEdit" class="btn btn-primary" id="<%=purchaseOrders.get(i).getId()%>" value="Submit" onclick="javascript:updateTO('<%=purchaseOrders.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                response.sendRedirect("manufacturingWarehouseManagement.jsp");
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Create Purchase Order" onclick="addPO()"  />
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
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
<%}%>