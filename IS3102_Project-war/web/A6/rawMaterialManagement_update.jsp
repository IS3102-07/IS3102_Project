<%@page import="EntityManager.RawMaterialEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.SupplierEntity"%>

<html lang="en">

    <jsp:include page="../header2.html" />
    <body>
<script>
            function goBackPage() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.furnitureManagement.action = "../FurnitureManagement_RemoveFurnitureServlet";
                    document.furnitureManagement.submit();
                } else {
                    window.event.returnValue = false;
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
                                Raw Material Details Update
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="itemManagement.jsp">Item Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-align-center"></i><a href="../RawMaterialManagement_RawMaterialServlet"> Raw Material Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i>  Raw Material Details Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <%
                        try {
                            String id = request.getParameter("id");
                            List<RawMaterialEntity> rawMaterials = (List<RawMaterialEntity>) (session.getAttribute("rawMaterialList"));
                            RawMaterialEntity rawMaterial = new RawMaterialEntity();
                            for (int i = 0; i < rawMaterials.size(); i++) {
                                if (rawMaterials.get(i).getId() == Integer.parseInt(id)) {
                                    rawMaterial = rawMaterials.get(i);
                                }
                            }
                    %>

                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../RawMaterialManagement_UpdateRawMaterialServlet">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" required="true" name="name" type="text" value="<%=rawMaterial.getName()%>">
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <input class="form-control"  type="text" name="category" value="<%=rawMaterial.getCategory()%>">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input class="form-control" type="text"  name="description" required="true" value="<%=rawMaterial.getDescription()%>" >
                                </div>
                               <div class="form-group">
                                    <label>SKU</label>
                                    <input class="form-control" type="text"  name="SKU" required="true" value="<%=rawMaterial.getSKU()%>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Length</label>
                                    <input class="form-control" type="text"  name="length" required="true" value="<%=rawMaterial.getLength()%>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Width</label>
                                    <input class="form-control" type="text"  name="width" required="true" value="<%=rawMaterial.getWidth()%>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Height</label>
                                    <input class="form-control" type="text"  name="height" required="true" value="<%=rawMaterial.getHeight()%>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Lot Size</label>
                                    <input class="form-control" required="true" type="number" min="1" step="1" name="lotsize" value="<%=rawMaterial.getLotSize()%>">
                                </div>
                                 <div class="form-group">
                                    <label>Lead Time</label>
                                    <input class="form-control" required="true" type="number" min="1" step="1" name="leadtime" value="<%=rawMaterial.getLeadTime()%>">
                                </div>
                                 <div class="form-group">
                                    <label>Price</label>
                                    <input class="form-control" required="true" type="number" min="1" step="0.1" name="price" value="<%=rawMaterial.getPrice()%>">
                                 </div>
                                 <div class="form-group">
                                    <label>Supplier</label>
                                    <select required="" name="supplier" class="form-control">
                                        <option value="<%=(rawMaterial.getSupplier().getId())%>"><%=rawMaterial.getSupplier().getSupplierName()%></option>
                                        <%
                                            List<SupplierEntity> suppliers = (List<SupplierEntity>) (session.getAttribute("suppliers"));
                                            if (suppliers != null) {
                                                for (SupplierEntity supplier : suppliers) {
                                                    if (!supplier.getSupplierName().equals(rawMaterial.getSupplier().getSupplierName())) {
                                        %>
                                        <option value="<%= supplier.getId()%>"> <%= supplier.getSupplierName()%> </option>
                                        <%
                                                    }
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="<%=rawMaterial.getId()%>" name="id">
                                <input type="hidden" value="<%=rawMaterial.getName()%>" name="rawMaterialName">
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
                    <%} catch (Exception ex) {
                        
                            //response.sendRedirect("../RawMaterialManagement_RawMaterialServlet");
                        }%>
                </div>

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
