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
            TransferOrderEntity transferOrder = null;
            for (int i = 0; i < transferOrders.size(); i++) {
                if (transferOrders.get(i).getId() == Integer.parseInt(transferOrderID)) {
                    transferOrder = transferOrders.get(i);
                }
            }

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
                                Line Item
                            </h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-edit"></i> <a href="manufacturingWarehouseManagement_view.jsp"><%=warehouseEntity.getWarehouseName()%></a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> <a href="manufacturingWarehouseManagement.jsp">Manufacturing Warehouse Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> <a href="transferOrderManagement.jsp">Transfer Order <%=transferOrder.getId()%></a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Line Item <%=transferOrder.getLineItem().getId()%>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../LineItemManagement_UpdateServlet">
                                <div class="form-group">
                                    <label>SKU</label>
                                    <input class="form-control" name="type" type="text" value="<%=transferOrder.getLineItem().getItem().getSKU()%>" disabled >
                                </div>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input class="form-control" name="length" type="number" value="<%=transferOrder.getLineItem().getQuantity()%>" required="true" >
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="<%=transferOrder.getId()%>" name="id">
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>

                    <%

                            } catch (Exception ex) {
                                //response.sendRedirect("../StorageBinManagement_UpdateServlet");
                            }
                        }%>

                </div>

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
