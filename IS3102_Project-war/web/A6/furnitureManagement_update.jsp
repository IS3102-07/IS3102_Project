<%@page import="EntityManager.CountryEntity"%>
<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="java.util.List"%>
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
                                Furniture Details Update
                            </h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="furnitureManagement.jsp"> Furniture Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i>  Furniture Details Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <%
                        try {
                            String id = request.getParameter("id");
                            List<FurnitureEntity> furnitures = (List<FurnitureEntity>) (session.getAttribute("furnitures"));
                            FurnitureEntity furniture = new FurnitureEntity();
                            for (int i = 0; i < furnitures.size(); i++) {
                                if (furnitures.get(i).getId() == Integer.parseInt(id)) {
                                    furniture = furnitures.get(i);
                                }
                            }
                    %>

                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../FurnitureManagement_UpdateFurnitureServlet">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" required="true" name="name" type="text" value="<%=furniture.getName()%>">
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <input class="form-control" required="true" type="text" name="category" value="<%=furniture.getCategory()%>">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input class="form-control" type="text"  name="description"required="true" value="<%=furniture.getDescription()%>" >
                                </div>
                                <div class="form-group">
                                    <label>Image URL</label>
                                    <input class="form-control" type="text" required="true" name="address" value="<%=furniture.getImageURL()%>">
                                </div>
                                <div class="form-group">
                                    <label>SKU</label>
                                    <input class="form-control" type="text" required="true" name="SKU" value="<%=furniture.getItem().getSKU()%>">
                                </div>
                                <div class="form-group">
                                    <label>Length</label>
                                    <input class="form-control" type="text" required="true" name="length" value="<%=furniture.getItem().getHeight()%>">
                                </div>
                                <div class="form-group">
                                    <label>Width</label>
                                    <input class="form-control" type="text" required="true" name="width" value="<%=furniture.getItem().getWidth()%>">
                                </div>
                                <div class="form-group">
                                    <label>Height</label>
                                    <input class="form-control" type="text" required="true" name="height" value="<%=furniture.getItem().getWidth()%>">
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="<%=furniture.getId()%>" name="id">
                                <input type="hidden" value="<%=furniture.getName()%>" name="furnitureName">
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
                    <%} catch (Exception ex) {
                            response.sendRedirect("../FurnitureManagement_FurnitureServlet");
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
