<%@page import="EntityManager.LineItemEntity"%>
<%@page import="EntityManager.TransferOrderEntity"%>
<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="EntityManager.StorageBinEntity"%>
<%@page import="java.util.List"%>
<% WarehouseEntity warehouseEntity = (WarehouseEntity) (session.getAttribute("warehouseEntity"));
    if (warehouseEntity == null) {
        pageContext.forward("manufacturingWarehouseManagement_view.jsp");
    } else {
        try {
            String transferOrderID = request.getParameter("id");
            List<TransferOrderEntity> transferOrders = (List<TransferOrderEntity>) (session.getAttribute("transferOrders"));
            TransferOrderEntity transferOrder = new TransferOrderEntity();
            for (int i = 0; i < transferOrders.size(); i++) {
                if (transferOrders.get(i).getId() == Integer.parseInt(transferOrderID)) {
                    transferOrder = transferOrders.get(i);
                }
            }
            LineItemEntity lineItem = transferOrder.getLineItem();

%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>

        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />

            <div id="page-wrapper">
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Transfer Order: <%=transferOrder.getId()%>
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i> <a href="manufacturingWarehouseManagement_view.jsp">Manufacturing Warehouse Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-home"></i> <a href="manufacturingWarehouseManagement.jsp"><%=warehouseEntity.getWarehouseName()%></a>
                                </li>
                                <li>
                                    <i class="icon icon-exchange"></i><a href="transferOrderManagement.jsp"> Transfer Order Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Transfer Order <%=transferOrder.getId()%>
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
                                    <h3 class="panel-title"> Line Item Management</h3>
                                </div>
                                <div class="panel-body">
                                    <%if (lineItem == null) {%>
                                    <form role="form" action="../LineItemManagement_Servlet">
                                        <div class="form-group">
                                            <label>SKU</label>
                                            <input class="form-control" name="sku" type="text"  >
                                        </div>
                                        <div class="form-group">
                                            <label>Quantity</label>
                                            <input class="form-control" name="quantity" type="number" required="true" >
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Add Line Item" class="btn btn-lg btn-primary btn-block">
                                        </div>
                                        <input type="hidden" value="<%=transferOrder.getId()%>" name="id">
                                    </form>
                                    <%} else {%>
                                    <form role="form" action="../LineItemManagement_RemoveServlet">
                                        <div class="form-group">
                                            <label>SKU</label>
                                            <input class="form-control" name="sku" type="text" value="<%=transferOrder.getLineItem().getItem().getSKU()%>" disabled >
                                        </div>
                                        <div class="form-group">
                                            <label>Quantity</label>
                                            <input class="form-control" name="quantity" type="number" value="<%=transferOrder.getLineItem().getQuantity()%>" disabled >
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Remove Line Item" class="btn btn-lg btn-primary btn-block">
                                        </div>
                                        <input type="hidden" value="<%=transferOrder.getId()%>" name="id">
                                    </form>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <!-- /.row -->

                        <%
                            if (lineItem != null) {
                        %>
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"> Transfer Order Status</h3>
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="../LineItemManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select class="form-control" name="status" required="true">
                                                <option></option>
                                                <option>Completed</option>
                                                <option>Unfulfillable</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Update Status" class="btn btn-lg btn-primary btn-block">
                                        </div>
                                        <input type="hidden" value="<%=transferOrder.getId()%>" name="id">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%                           }
                            } catch (Exception ex) {
                                response.sendRedirect("../TransferOrderManagement_Servlet");
                                ex.printStackTrace();
                            }
                        }%>

                </div>

            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->

    </body>

</html>
