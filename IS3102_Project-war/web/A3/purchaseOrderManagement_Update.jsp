<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.LineItemEntity"%>
<%@page import="EntityManager.SupplierEntity"%>
<%@page import="EntityManager.PurchaseOrderEntity"%>
<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>
<%
    List<PurchaseOrderEntity> purchaseOrders = (List<PurchaseOrderEntity>) (session.getAttribute("purchaseOrders"));
    String id = request.getParameter("id");
    String isSubmit = request.getParameter("source");
    if (purchaseOrders == null || id == null) {
        response.sendRedirect("../PurchaseOrderManagement_Servlet");
    } else {
        List<SupplierEntity> activeSuppliers = (List<SupplierEntity>) (session.getAttribute("activeSuppliers"));
        List<WarehouseEntity> warehouses = (List<WarehouseEntity>) (session.getAttribute("warehouses"));

        PurchaseOrderEntity purchaseOrder = new PurchaseOrderEntity();
        for (int i = 0; i < purchaseOrders.size(); i++) {
            if (purchaseOrders.get(i).getId() == Integer.parseInt(id)) {
                purchaseOrder = purchaseOrders.get(i);
            }
        }

%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <script>
            function addPOLineItem(id) {
                purchaseOrderManagement.id.value = id;
                document.purchaseOrderManagement.action = "purchaseOrderManagement_AddLineItem.jsp";
                document.purchaseOrderManagement.submit();
            }
            function updatePOLineItem(lineItemId) {
                purchaseOrderManagement.lineItemId.value = lineItemId;
                document.purchaseOrderManagement.action = "purchaseOrderManagement_UpdateLineItem.jsp";
                document.purchaseOrderManagement.submit();
            }
            function removePOLineItem() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.purchaseOrderManagement.action = "../PurchaseOrderLineItemManagement_RemoveServlet";
                    document.purchaseOrderManagement.submit();
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
                                Purchase Order
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-exchange"></i> <a href="purchaseOrderManagement.jsp">Purchase Order Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Purchase Order - Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->


                    <jsp:include page="../displayMessageLong.jsp" />


                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Purchase Order ID: <%=purchaseOrder.getId()%></h3>
                                </div>
                                <div class="panel-body">

                                    <form role="form" action="../PurchaseOrderManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Supplier</label>
                                            <select class="form-control" name="supplier" required="true" <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%>>
                                                <%
                                                    for (int i = 0; i < activeSuppliers.size(); i++) {
                                                        if (activeSuppliers.get(i).getSupplierName().equals(purchaseOrder.getSupplier().getSupplierName())) {
                                                            out.println("<option selected value='" + activeSuppliers.get(i).getId() + "'>" + activeSuppliers.get(i).getSupplierName() + "</option>");
                                                        } else {
                                                            out.println("<option value='" + activeSuppliers.get(i).getId() + "'>" + activeSuppliers.get(i).getSupplierName() + "</option>");
                                                        }
                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Destination</label>
                                            <select class="form-control" name="destination" required="true" <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%>>
                                                <%
                                                    for (int i = 0; i < warehouses.size(); i++) {
                                                        if (warehouses.get(i).getWarehouseName().equals(purchaseOrder.getReceivedWarehouse().getWarehouseName())) {
                                                            out.println("<option selected value='" + warehouses.get(i).getId() + "'>" + warehouses.get(i).getWarehouseName() + "</option>");
                                                        } else {
                                                            out.println("<option value='" + warehouses.get(i).getId() + "'>" + warehouses.get(i).getWarehouseName() + "</option>");
                                                        }
                                                    }

                                                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                                    String formatedDate = df.format(purchaseOrder.getExpectedReceivedDate());
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Expected Receiving Date:</label>
                                            <input class="form-control" name="expectedDate" type="date" required="true" value="<%=formatedDate%>" <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%>/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Update Purchase Order" class="btn btn-lg btn-primary btn-block" <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%>>
                                        </div>  
                                        <input type="hidden" value="<%=purchaseOrder.getId()%>" name="id">
                                    </form>
                                </div>
                            </div>
                        </div>


                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Purchase Order Status: <%=purchaseOrder.getStatus()%></h3>
                                </div>
                                <div class="panel-body">
                                    <%if (isSubmit != null) {%>
                                    <form role="form" action="../PurchaseOrderLineItemManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select class="form-control" name="status1" disabled>
                                                <option><%=purchaseOrder.getStatus()%></option>
                                            </select>
                                            <br>
                                            <div class="form-group">
                                                <input type="submit" value="Submit Purchase Order" class="btn btn-lg btn-primary btn-block">
                                            </div>
                                            <input type="hidden" name="status1" value="1">
                                            <input type="hidden" value="<%=purchaseOrder.getId()%>" name="id">
                                        </div>
                                    </form>
                                    <%
                                    } else if (purchaseOrder.getStatus().equals("Completed") || purchaseOrder.getStatus().equals("Unfulfillable")) {
                                    %>
                                    <form role="form" >
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select class="form-control" name="status2">
                                                <option><%=purchaseOrder.getStatus()%></option>
                                            </select>
                                            <br>
                                            <div class="form-group">
                                                <input type="submit" value="Update Purchase Order Status" class="btn btn-lg btn-primary btn-block" disabled>
                                            </div>
                                        </div>
                                    </form>
                                    <%
                                    } else {
                                    %>
                                    <form role="form" action="../PurchaseOrderLineItemManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select class="form-control" name="status3" required="true">
                                                <%
                                                    if (purchaseOrder.getStatus().equals("Pending")) {
                                                        out.println("<option>Pending</option>");
                                                    } else if (purchaseOrder.getStatus().equals("Submitted")) {
                                                        out.println("<option>Shipped</option>");
                                                        out.println("<option>Unfulfillable</option>");
                                                    } else if (purchaseOrder.getStatus().equals("Shipped")) {
                                                        out.println("<option>Completed</option>");
                                                        out.println("<option>Unfulfillable</option>");
                                                    }
                                                %>
                                            </select>
                                            <br>
                                            <div class="form-group">
                                                <input type="hidden" value="<%=purchaseOrder.getReceivedWarehouse().getId()%>" name="destinationWarehouseID">
                                                <input type="submit" value="Update Purchase Order Status" class="btn btn-lg btn-primary btn-block <% if (purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%>">
                                            </div>
                                        </div>
                                        <input type="hidden" value="<%=purchaseOrder.getId()%>" name="id">
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
                                <form name="purchaseOrderManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%> type="button" name="btnAddLineItem" class="btn btn-primary" value="Add Line Item" onclick="javascript:addPOLineItem('<%=purchaseOrder.getId()%>')"/>
                                                        <input <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%> class="btn btn-primary" name="btnRemove" type="submit" value="Remove Line Item" onclick="javascript:removePOLineItem()"  />
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
                                                            List<LineItemEntity> lineItems = purchaseOrder.getLineItems();
                                                            if (lineItems != null) {
                                                                for (int i = 0; i < lineItems.size(); i++) {
                                                        %>

                                                        <tr>
                                                            <td>
                                                                <input <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%> type="checkbox" name="delete" value="<%=lineItems.get(i).getId()%>" />
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
                                                                <input <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%> type="button" name="btnEdit" class="btn btn-primary btn-block" value="Update" onclick="javascript:updatePOLineItem('<%=lineItems.get(i).getId()%>')"/>
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
                                                <input <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%> type="button" name="btnAddLineItem" class="btn btn-primary" value="Add Line Item" onclick="javascript:addPOLineItem('<%=purchaseOrder.getId()%>')"/>
                                                <input <%if (isSubmit != null || !purchaseOrder.getStatus().equals("Pending")) {%>disabled<%}%> class="btn btn-primary" name="btnRemove" type="submit" value="Remove Line Item" onclick="javascript:removePOLineItem()"  />
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="<%=purchaseOrder.getId()%>" name="id">
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
