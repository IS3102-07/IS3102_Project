<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="EntityManager.StorageBinEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateStorageBin(id) {
                storagebinManagement.id.value = id;
                document.storagebinManagement.action = "storageBinManagement_Update.jsp";
                document.storagebinManagement.submit();
            }
            function removeStorageBin() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.storagebinManagement.action = "../StorageBinManagement_RemoveServlet";
                    document.storagebinManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function addStorageBin() {
                window.event.returnValue = true;
                document.storagebinManagement.action = "storageBinManagement_Add.jsp";
                document.storagebinManagement.submit();
            }
            function checkAll() {
                alert("Check all the checkboxes...");
                var allRows = document.storagebinManagement.getElementsByTagName("delete");
                for (var i = 0; i < allRows.length; i++) {
                    if (allRows[i].type == 'checkbox') {
                        allRows[i].checked = true;
                    }
                }
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <%
                WarehouseEntity warehouseEntity = (WarehouseEntity) (session.getAttribute("warehouseEntity"));
                try {
            %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Storage Bin Management</h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-edit"></i> <a href="manufacturingWarehouseManagement_view.jsp"><%=warehouseEntity.getWarehouseName()%>t</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Storage Bin Management
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
                                <form name="storagebinManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll()" /></th>
                                                            <th>Type</th>
                                                            <th>Length</th>
                                                            <th>Width</th>
                                                            <th>Height</th>
                                                            <th>Total Volume</th>
                                                            <th>Avaliable Volume</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<StorageBinEntity> storageBins = (List<StorageBinEntity>) (session.getAttribute("storageBins"));
                                                            if (storageBins != null) {
                                                                for (int i = 0; i < storageBins.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=storageBins.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getType()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getLength()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getWidth()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getHeight()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getVolume()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getFreeVolume()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=storageBins.get(i).getId()%>" value="update" onclick="javascript:updateStorageBin('<%=storageBins.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                response.sendRedirect("manufacturingWarehouseManagement.jsp");
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Storage Bin" onclick="addStorageBin()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Storage Bin" onclick="removeStorageBin()"  />
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
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
