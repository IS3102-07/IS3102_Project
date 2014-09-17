<%@page import="EntityManager.RawMaterialEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateRawMaterial(id) {
                rawMaterialManagement.id.value = id;
                document.rawMaterialManagement.action = "rawMaterialManagement_update.jsp";
                document.rawMaterialManagement.submit();
            }
            function removerawMaterial() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.rawMaterialManagement.action = "../RawMaterialManagement_RemoveRawMaterialServlet";
                    document.rawMaterialManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function addrawMaterial() {
                window.event.returnValue = true;
                document.rawMaterialManagement.action = "rawMaterialManagement_add.jsp";
                document.rawMaterialManagement.submit();
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
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Raw Material Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="rawMaterialManagement.jsp">Raw Material Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Raw Material Management
                                </li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    insert some wordings
                                </div>
                                <!-- /.panel-heading -->
                                <form name="rawMaterialManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>Name</th>
                                                            <th>Category</th>
                                                            <th>Description</th>
                                                            <th>Image URL</th>
                                                            <th>SKU</th>
                                                            <th>Length</th>
                                                            <th>Width</th>
                                                            <th>Height</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<RawMaterialEntity> rawMaterials = (List<RawMaterialEntity>) (session.getAttribute("rawMaterialList"));

                                                            try {
                                                                if (rawMaterials != null) {
                                                                    for (int i = 0; i < rawMaterials.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=rawMaterials.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=rawMaterials.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=rawMaterials.get(i).getCategory()%>
                                                            </td>
                                                            <td>
                                                                <%=rawMaterials.get(i).getDescription()%>
                                                            </td>
                                                        
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=rawMaterials.get(i).getId()%>" value="update" onclick="javascript:updateRawMaterial('<%=rawMaterials.get(i).getId()%>')"/>
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
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Raw Material" onclick="addRawMaterial()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Raw Material" onclick="removeRawMaterial()"  />
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
                    <!-- /.row -->


                </div>
                <!-- /.container-fluid -->

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
