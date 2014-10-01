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
                                <div class="panel-heading"> <%

                                    String errMsg = request.getParameter("errMsg");
                                    String goodMsg = request.getParameter("goodMsg");
                                    if (errMsg == null && goodMsg == null) {
                                        out.println("Add or remove furniture");
                                    } else if ((errMsg != null) && (goodMsg == null)) {
                                        if (!errMsg.equals("")) {
                                            out.println(errMsg);
                                        }
                                    } else if ((errMsg == null && goodMsg != null)) {
                                        if (!goodMsg.equals("")) {
                                            out.println(goodMsg);
                                        }
                                    }
                                    %>                                  
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
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=furnitures.get(i).getId()%>" value="Update" onclick="javascript:updateFurniture('<%=furnitures.get(i).getId()%>')"/>
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
                            <div class="col-md-12">
                                <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                                    <i class="icon icon-usd"></i>
                                                    Pricing Tables
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseOne" class="accordion-body collapse in">
                                            <div class="panel-body">
                                                Donec tellus massa, tristique sit amet condim vel, facilisis quis sapien. Praesent id enim sit amet odio vulputate eleifend in in tortor.
                                            </div>
                                        </div>
                                    </div>

                                    <%
                                        
                                        if (furnitures != null) {
                                            for (int i = 0; i < furnitures.size(); i++) {

                                    %>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse<%=i%>">
                                                    <i class="icon icon-comment"></i>
                                                    <%=furnitures.get(i).getName()%>
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapse<%=i%>" class="accordion-body collapse">
                                            <div class="panel-body">
                                                <%=furnitures.get(i).getDescription()%>
                                            </div>
                                        </div>
                                    </div>

                                    <%                                            
                                            }
                                        }

                                    %>
                                    
                                    <%
                                        List<RetailProductEntity> retailProducts = (List<RetailProductEntity>) (session.getAttribute("retailProducts"));

                                        if (furnitures != null) {
                                            for (int i = 0; i < retailProducts.size(); i++) {

                                    %>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapse<%=i%>">
                                                    <i class="icon icon-comment"></i>
                                                    <%=retailProducts.get(i).getName()%>
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapse<%=i%>" class="accordion-body collapse">
                                            <div class="panel-body">
                                                <%=retailProducts.get(i).getDescription()%>
                                            </div>
                                        </div>
                                    </div>

                                    <%                                            
                                            }
                                        }

                                    %>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                                    <i class="icon icon-laptop"></i>
                                                    Portfolio Pages
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseThree" class="accordion-body collapse">
                                            <div class="panel-body">
                                                Donec tellus massa, tristique sit amet condimentum vel, facilisis quis sapien.
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
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
