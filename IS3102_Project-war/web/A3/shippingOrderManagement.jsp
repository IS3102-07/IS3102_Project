<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="EntityManager.ShippingOrderEntity"%>
<%@page import="java.util.List"%>

<% List<ShippingOrderEntity> shippingOrders = (List<ShippingOrderEntity>) (session.getAttribute("shippingOrders"));
    if (shippingOrders == null) {
        response.sendRedirect("../ShippingOrderManagement_Servlet");
    } else {
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateSO(id) {
                shippingOrderManagement.id.value = id;
                document.shippingOrderManagement.action = "../ShippingOrderLineItemManagement_Servlet";
                document.shippingOrderManagement.submit();
            }
            function submitSO(id) {
                shippingOrderManagement.id.value = id;
                shippingOrderManagement.source.value = "submit";
                document.shippingOrderManagement.action = "../ShippingOrderLineItemManagement_Servlet";
                document.shippingOrderManagement.submit();
            }
            function addSO() {
                window.event.returnValue = true;
                document.shippingOrderManagement.action = "shippingOrderManagement_Add.jsp";
                document.shippingOrderManagement.submit();
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
                            <h1 class="page-header">Shipping Order Management</h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-exchange"></i> Shipping Order Management
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
                                            errMsg = "Add shipping orders";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="shippingOrderManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Create Shipping Order" onclick="addSO()"  />
                                                </div>
                                            </div>
                                            <br>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th>Shipping Order ID</th>
                                                            <th>Shipping Origin</th>
                                                            <th>Shipping Destination</th>
                                                            <th>Expected Receiving Date</th>
                                                            <th>Status</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (shippingOrders != null) {
                                                                for (int i = 0; i < shippingOrders.size(); i++) {
                                                                    WarehouseEntity source = shippingOrders.get(i).getOrigin();
                                                                    WarehouseEntity destination = shippingOrders.get(i).getDestination();
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <%=shippingOrders.get(i).getId()%>
                                                            </td>
                                                            <td>
                                                                <%=source.getWarehouseName()%>
                                                            </td>
                                                            <td>
                                                                <%=destination.getWarehouseName()%>
                                                            </td>
                                                            <td>
                                                                <%=shippingOrders.get(i).getExpectedReceivedDate()%>
                                                            </td>
                                                            <td>
                                                                <%=shippingOrders.get(i).getStatus()%>
                                                            </td>
                                                            <td style="width:200px">
                                                                <input <%if (shippingOrders.get(i).getStatus().equals("Completed") || shippingOrders.get(i).getStatus().equals("Unfulfillable")) {%>disabled<%}%> type="button" name="btnEdit" class="btn btn-primary"  value="Update" onclick="javascript:updateSO('<%=shippingOrders.get(i).getId()%>')"/>
                                                                <input <%if (shippingOrders.get(i).getStatus().equals("Submitted") || shippingOrders.get(i).getStatus().equals("Shipped") || shippingOrders.get(i).getStatus().equals("Completed") || shippingOrders.get(i).getStatus().equals("Unfulfillable")) {%>disabled<%}%> type="button" name="btnEdit" class="btn btn-primary"  value="Submit" onclick="javascript:submitSO('<%=shippingOrders.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                response.sendRedirect("../A1/workspace.jsp");
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Create Shipping Order" onclick="addSO()"  />
                                                </div>
                                            </div>
                                            <input type="hidden" name="id" value="">    
                                            <input type="hidden" name="source" value="">   
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