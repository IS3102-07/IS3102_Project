<%@page import="EntityManager.WarehouseEntity"%>
<% WarehouseEntity warehouseEntity = (WarehouseEntity) (session.getAttribute("warehouseEntity"));
    if (warehouseEntity == null) {
        response.sendRedirect("../ManufacturingWarehouseManagement_Servlet");
    } else {
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
                                Add Storage Bin
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i> <a href="manufacturingWarehouseManagement_view.jsp">Manufacturing Warehouse Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-home"></i> <a href="manufacturingWarehouseManagement.jsp"><%=warehouseEntity.getWarehouseName()%></a>
                                </li>
                                <li>
                                    <i class="icon icon-archive"></i><a href="storageBinManagement.jsp"> Storage Bin Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Add Storage Bin
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <jsp:include page="../displayMessage.jsp" />

                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="../StorageBinManagement_AddServlet">
                                <div class="form-group">
                                    <label>Type</label>
                                    <select  class="form-control" name="type" required="true">
                                        <option>Pallet</option>
                                        <option>Shelf</option>
                                        <option>Inbound</option>
                                        <option>Outbound</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Length</label>
                                    <input class="form-control" name="length" type="number" required="true">
                                </div>
                                <div class="form-group">
                                    <label>Width</label>
                                    <input class="form-control" name="width" type="number" required="true"/>
                                </div>
                                <div class="form-group">
                                    <label>Height</label>
                                    <input class="form-control" name="height" type="number" required="true"/>
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Add" class="btn btn-lg btn-primary btn-block">
                                </div>  
                            </form>
                        </div>
                        <!-- /.row -->

                    </div>
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
<%}%>