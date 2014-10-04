<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>

        <div class="body">
            <jsp:include page="menu1.html" />

            <div role="main" class="main">
                <section class="page-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>All Products</h2>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="container">                    
                    <div class="container">

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"> 
                                        List of all furnitures
                                    </div>
                                    <!-- /.panel-heading -->
                                    <form name="furnitureManagement">
                                        <div class="panel-body">
                                            <div class="table-responsive">

                                                <div class="row">
                                                    <div class="col-md-12">

                                                        <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Add To Shopping List</button></a>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                        <thead>
                                                            <tr>
                                                                <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                                <th><h4 class="shorter">Name</h4></th>
                                                        <th><h4 class="shorter">Category</h4></th>
                                                        <th><h4 class="shorter">Description</h4></th>
                                                        <th><h4 class="shorter">Image URL</h4></th>
                                                        <th><h4 class="shorter">SKU</h4></th>
                                                        <th><h4 class="shorter">Length</h4></th>
                                                        <th><h4 class="shorter">Width</h4></th>
                                                        <th><h4 class="shorter">Height</h4></th>
                                                        <th><h4 class="shorter">Action</h4></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                List<FurnitureEntity> furnitures = (List<FurnitureEntity>) (session.getAttribute("furnitures"));

                                                                try {
                                                                    if (furnitures != null) {
                                                                        for (int i = 0; i < furnitures.size(); i++) {
                                                            %>
                                                            <tr>
                                                                <td>
                                                                    <input type="checkbox" name="delete" value="<%=furnitures.get(i).getId()%>" />
                                                                </td>
                                                                <td>
                                                                    <p class="tall"> <%=furnitures.get(i).getName()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"> <%=furnitures.get(i).getCategory()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"><%=furnitures.get(i).getDescription()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"><%=furnitures.get(i).getImageURL()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"> <%=furnitures.get(i).getSKU()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"><%=furnitures.get(i).getLength()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"> <%=furnitures.get(i).getWidth()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"><%=furnitures.get(i).getHeight()%></p>
                                                                </td>
                                                                <td>
                                                                    <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=furnitures.get(i).getId()%>" value="Add" onclick="javascript:updateFurniture('<%=furnitures.get(i).getId()%>')"/>
                                                                </td>
                                                            </tr>
                                                            <%
                                                                        }
                                                                    }
                                                                } catch (Exception ex) {
                                                                    System.out.println(ex);
                                                                }
                                                            %>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <!-- /.table-responsive -->
                                                <div class="row">
                                                    <div class="col-md-12">

                                                        <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Add To Shopping List</button></a>
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
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"> 
                                        Retail Products
                                    </div>
                                    <!-- /.panel-heading -->
                                    <form name="furnitureManagement">
                                        <div class="panel-body">
                                            <div class="table-responsive">

                                                <div class="row">
                                                    <div class="col-md-12">

                                                        <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Add To Shopping List</button></a>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                        <thead>
                                                            <tr>
                                                                <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                                <th><h4 class="shorter">Name</h4></th>
                                                        <th><h4 class="shorter">Category</h4></th>
                                                        <th><h4 class="shorter">Description</h4></th>
                                                        <th><h4 class="shorter">Image URL</h4></th>
                                                        <th><h4 class="shorter">SKU</h4></th>
                                                        <th><h4 class="shorter">Length</h4></th>
                                                        <th><h4 class="shorter">Width</h4></th>
                                                        <th><h4 class="shorter">Height</h4></th>
                                                        <th><h4 class="shorter">Action</h4></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                List<RetailProductEntity> retailProducts = (List<RetailProductEntity>) (session.getAttribute("retailProducts"));

                                                                try {
                                                                    if (retailProducts != null) {
                                                                        for (int i = 0; i < retailProducts.size(); i++) {
                                                            %>
                                                            <tr>
                                                                <td>
                                                                    <input type="checkbox" name="delete" value="<%=furnitures.get(i).getId()%>" />
                                                                </td>
                                                                <td>
                                                                    <p class="tall"> <%=retailProducts.get(i).getName()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"> <%=retailProducts.get(i).getCategory()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"><%=retailProducts.get(i).getDescription()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"><%=retailProducts.get(i).getImageURL()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"> <%=retailProducts.get(i).getSKU()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"><%=retailProducts.get(i).getLength()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"> <%=retailProducts.get(i).getWidth()%></p>
                                                                </td>
                                                                <td>
                                                                    <p class="tall"><%=retailProducts.get(i).getHeight()%></p>
                                                                </td>
                                                                <td>
                                                                    <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=retailProducts.get(i).getId()%>" value="Add" onclick="javascript:updateFurniture('<%=retailProducts.get(i).getId()%>')"/>
                                                                </td>
                                                            </tr>
                                                            <%
                                                                        }
                                                                    }
                                                                } catch (Exception ex) {
                                                                    System.out.println(ex);
                                                                }
                                                            %>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <!-- /.table-responsive -->
                                                <div class="row">
                                                    <div class="col-md-12">

                                                        <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Add To Shopping List</button></a>
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
                        <hr class="tall" />                        
                    </div>
                </div>

                <jsp:include page="footer.html" />
            </div>
            <!-- Theme Initializer -->
            <script src="../js/theme.plugins.js"></script>
            <script src="../js/theme.js"></script>

            <!-- Current Page JS -->
            <script src="../vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
            <script src="../vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
            <script src="../vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
            <script src="../js/views/view.home.js"></script>   
        </div>
    </body>
</html>
