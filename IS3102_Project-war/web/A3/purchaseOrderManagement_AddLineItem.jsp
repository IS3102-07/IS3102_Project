<%@page import="EntityManager.SupplierEntity"%>
<%@page import="EntityManager.PurchaseOrderEntity"%>
<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>
<%
    List<PurchaseOrderEntity> purchaseOrders = (List<PurchaseOrderEntity>) (session.getAttribute("purchaseOrders"));
    if (purchaseOrders == null) {
        response.sendRedirect("../PurchaseOrderManagement_Servlet");
    } else {
        List<SupplierEntity> activeSuppliers = (List<SupplierEntity>) (session.getAttribute("activeSuppliers"));
        List<WarehouseEntity> warehouses = (List<WarehouseEntity>) (session.getAttribute("warehouses"));
        String id = request.getParameter("id");

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
            function updatePOLineItem(id) {
                purchaseOrderManagement.id.value = id;
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
            function addPOLineItem() {
                window.event.returnValue = true;
                document.purchaseOrderManagement.action = "purchaseOrderManagement_AddLineItem.jsp";
                document.purchaseOrderManagement.submit();
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
                                Add Line Item
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-exchange"></i> <a href="purchaseOrderManagement.jsp">Purchase Order Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-exchange"></i> <a href="purchaseOrderManagement_Update.jsp?id=<%=purchaseOrder.getId()%>">Purchase Order ID: <%=purchaseOrder.getId()%></a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Add Line Item
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <jsp:include page="../displayMessage.jsp" />


                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Purchase Order ID: <%=purchaseOrder.getId()%> - Add Line Item </h3>
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="../PurchaseOrderManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Supplier</label>
                                            <select class="form-control" name="supplier" required="true">
                                                <%                                                    for (int i = 0; i < activeSuppliers.size(); i++) {
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
                                            <select class="form-control" name="destination" required="true">
                                                <%
                                                    for (int i = 0; i < warehouses.size(); i++) {
                                                        if (warehouses.get(i).getWarehouseName().equals(purchaseOrder.getReceivedWarehouse().getWarehouseName())) {
                                                            out.println("<option selected value='" + warehouses.get(i).getId() + "'>" + warehouses.get(i).getWarehouseName() + "</option>");
                                                        } else {
                                                            out.println("<option value='" + warehouses.get(i).getId() + "'>" + warehouses.get(i).getWarehouseName() + "</option>");
                                                        }

                                                    }
                                                %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Expected Receiving Date:</label>
                                            <input class="form-control" name="expectedDate" type="date" required="true" value="<%=purchaseOrder.getExpectedReceivedDate()%>"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Update Purchase Order" class="btn btn-lg btn-primary btn-block">
                                        </div>  
                                        <input type="hidden" value="<%=purchaseOrder.getId()%>" name="id">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

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
