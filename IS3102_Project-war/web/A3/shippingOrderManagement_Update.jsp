<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.LineItemEntity"%>
<%@page import="EntityManager.ShippingOrderEntity"%>
<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>
<%
    List<ShippingOrderEntity> shippingOrders = (List<ShippingOrderEntity>) (session.getAttribute("shippingOrders"));
    String id = request.getParameter("id");
    String isSubmit = request.getParameter("source");
    if (shippingOrders == null || id == null) {
        response.sendRedirect("../ShippingOrderManagement_Servlet");
    } else {
        List<WarehouseEntity> warehouses = (List<WarehouseEntity>) (session.getAttribute("warehouses"));

        ShippingOrderEntity shippingOrder = new ShippingOrderEntity();
        for (int i = 0; i < shippingOrders.size(); i++) {
            if (shippingOrders.get(i).getId() == Integer.parseInt(id)) {
                shippingOrder = shippingOrders.get(i);
            }
        }

%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <script>
            function addPOLineItem(id) {
                shippingOrderManagement.id.value = id;
                document.shippingOrderManagement.action = "shippingOrderManagement_AddLineItem.jsp";
                document.shippingOrderManagement.submit();
            }
            function updatePOLineItem(lineItemId) {
                shippingOrderManagement.lineItemId.value = lineItemId;
                document.shippingOrderManagement.action = "shippingOrderManagement_UpdateLineItem.jsp";
                document.shippingOrderManagement.submit();
            }
            function removePOLineItem() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.shippingOrderManagement.action = "../ShippingOrderLineItemManagement_RemoveServlet";
                    document.shippingOrderManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
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
            <div id="page-wrapper">
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Shipping Order
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-exchange"></i> <a href="shippingOrderManagement.jsp">Shipping Order Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Shipping Order - Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <% //DON"T REMOVE THIS, HARDCODED SPECIAL
                        String errMsg = request.getParameter("errMsg");
                        if (errMsg == null || errMsg.equals("")) {
                            errMsg = "";
                        } else {
                    %>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="alert alert-info">
                                <%=errMsg%>
                            </div>
                        </div>
                    </div>
                    <%}%>


                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Shipping Order ID: <%=shippingOrder.getId()%></h3>
                                </div>
                                <div class="panel-body">

                                    <form role="form" action="../ShippingOrderManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Origin</label>
                                            <select class="form-control" name="origin" required="true" <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%>>
                                                <%
                                                    for (int i = 0; i < warehouses.size(); i++) {
                                                        if (warehouses.get(i).getWarehouseName().equals(shippingOrder.getOrigin().getWarehouseName())) {
                                                            out.println("<option selected value='" + warehouses.get(i).getId() + "'>" + warehouses.get(i).getWarehouseName() + "</option>");
                                                        } else {
                                                            out.println("<option value='" + warehouses.get(i).getId() + "'>" + warehouses.get(i).getWarehouseName() + "</option>");
                                                        }
                                                    }

                                                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                                    String formatedDate = df.format(shippingOrder.getExpectedReceivedDate());
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Destination</label>
                                            <select class="form-control" name="destination" required="true" <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%>>
                                                <%
                                                    for (int i = 0; i < warehouses.size(); i++) {
                                                        if (warehouses.get(i).getWarehouseName().equals(shippingOrder.getDestination().getWarehouseName())) {
                                                            out.println("<option selected value='" + warehouses.get(i).getId() + "'>" + warehouses.get(i).getWarehouseName() + "</option>");
                                                        } else {
                                                            out.println("<option value='" + warehouses.get(i).getId() + "'>" + warehouses.get(i).getWarehouseName() + "</option>");
                                                        }
                                                    }

                                                    df = new SimpleDateFormat("yyyy-MM-dd");
                                                    formatedDate = df.format(shippingOrder.getExpectedReceivedDate());
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Expected Receiving Date:</label>
                                            <input class="form-control" name="expectedDate" type="date" required="true" value="<%=formatedDate%>" <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%>/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Update Shipping Order" class="btn btn-lg btn-primary btn-block" <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%>>
                                        </div>  
                                        <input type="hidden" value="<%=shippingOrder.getId()%>" name="id">
                                    </form>
                                </div>
                            </div>
                        </div>


                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Shipping Order Status: <%=shippingOrder.getStatus()%></h3>
                                </div>
                                <div class="panel-body">
                                    <%if (isSubmit != null) {%>
                                    <form role="form" action="../ShippingOrderLineItemManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select class="form-control" name="status1" disabled>
                                                <option><%=shippingOrder.getStatus()%></option>
                                            </select>
                                            <br>
                                            <div class="form-group">
                                                <input type="submit" value="Submit Shipping Order" class="btn btn-lg btn-primary btn-block">
                                            </div>
                                            <input type="hidden" name="status1" value="1">
                                            <input type="hidden" value="<%=shippingOrder.getId()%>" name="id">
                                        </div>
                                    </form>
                                    <%
                                    } else if (shippingOrder.getStatus().equals("Completed") || shippingOrder.getStatus().equals("Unfulfillable")) {
                                    %>
                                    <form role="form" >
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select class="form-control" name="status2">
                                                <option><%=shippingOrder.getStatus()%></option>
                                            </select>
                                            <br>
                                            <div class="form-group">
                                                <input type="submit" value="Update Shipping Order Status" class="btn btn-lg btn-primary btn-block" disabled>
                                            </div>
                                        </div>
                                    </form>
                                    <%
                                    } else {
                                    %>
                                    <form role="form" action="../ShippingOrderLineItemManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select class="form-control" name="status3" required="true">
                                                <%
                                                    if (shippingOrder.getStatus().equals("Pending")) {
                                                        out.println("<option>Pending</option>");
                                                    } else if (shippingOrder.getStatus().equals("Submitted")) {
                                                        out.println("<option>Shipped</option>");
                                                        out.println("<option>Unfulfillable</option>");
                                                    } else if (shippingOrder.getStatus().equals("Shipped")) {
                                                        out.println("<option>Completed</option>");
                                                        out.println("<option>Unfulfillable</option>");
                                                    }
                                                %>
                                            </select>
                                            <br>
                                            <div class="form-group">
                                                <input type="hidden" value="<%=shippingOrder.getDestination().getId()%>" name="destinationWarehouseID">
                                                <input type="submit" value="Update Shipping Order Status" class="btn btn-lg btn-primary btn-block <% if (shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%>">
                                            </div>
                                        </div>
                                        <input type="hidden" value="<%=shippingOrder.getId()%>" name="id">
                                    </form>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Line item Management</h3>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="shippingOrderManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%> type="button" name="btnAddLineItem" class="btn btn-primary" value="Add Line Item" onclick="javascript:addPOLineItem('<%=shippingOrder.getId()%>')"/>
                                                        <input <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%> class="btn btn-primary" name="btnRemove" type="submit" value="Remove Line Item" onclick="javascript:removePOLineItem()"  />
                                                    </div>
                                                </div>
                                                <br>
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>SKU</th>
                                                            <th>Item Name</th>
                                                            <th>Quantity</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<LineItemEntity> lineItems = shippingOrder.getLineItems();
                                                            if (lineItems != null) {
                                                                for (int i = 0; i < lineItems.size(); i++) {
                                                        %>

                                                        <tr>
                                                            <td>
                                                                <input <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%> type="checkbox" name="delete" value="<%=lineItems.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=lineItems.get(i).getItem().getSKU()%>
                                                            </td>
                                                            <td>
                                                                <%=lineItems.get(i).getItem().getName()%>
                                                            </td>
                                                            <td>
                                                                <%=lineItems.get(i).getQuantity()%>
                                                            </td>
                                                            <td>
                                                                <input <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%> type="button" name="btnEdit" class="btn btn-primary btn-block" value="Update" onclick="javascript:updatePOLineItem('<%=lineItems.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%}
                                                            }%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <input <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%> type="button" name="btnAddLineItem" class="btn btn-primary" value="Add Line Item" onclick="javascript:addPOLineItem('<%=shippingOrder.getId()%>')"/>
                                                <input <%if (isSubmit != null || !shippingOrder.getStatus().equals("Pending")) {%>disabled<%}%> class="btn btn-primary" name="btnRemove" type="submit" value="Remove Line Item" onclick="javascript:removePOLineItem()"  />
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="<%=shippingOrder.getId()%>" name="id">
                                    <input type="hidden" name="lineItemId">
                                </form>
                            </div>
                        </div>
                    </div> 
                    <!-- /.row for line item management -->

                </div>
                <!-- /#page-wrapper -->
            </div>
            <!-- /#container fluid -->
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
