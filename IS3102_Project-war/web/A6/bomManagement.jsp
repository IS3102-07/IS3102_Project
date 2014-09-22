<%@page import="EntityManager.BillOfMaterialEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateBillOfMaterial(id) {
                billOfMaterialManagement.id.value = id;
                document.billOfMaterialManagement.action = "billOfMaterialManagement_update.jsp";
                document.billOfMaterialManagement.submit();
            }
            function removeBillOfMaterial() {
                checkboxes = document.getElementsByName('delete');
                var numOfTicks = 0;
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    if (checkboxes[i].checked) {
                        numOfTicks++;
                    }
                }
                if (checkboxes.length == 0 || numOfTicks == 0) {
                    alert("No items selected.");
                    window.event.returnValue = false;
                } else {

                    var yes = confirm("Are you sure?!");
                    if (yes == true) {
                        window.event.returnValue = true;
                        document.announcementsManagement.action = "../BillOfMaterialManagement_RemoveBillOfMaterialServlet";
                        document.announcementsManagement.submit();
                    } else {
                        window.event.returnValue = false;
                    }
                }
            }
            function addBillOfMaterial() {
                window.event.returnValue = true;
                document.billOfMaterialManagement.action = "billOfMaterialManagement_add.jsp";
                document.billOfMaterialManagement.submit();
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
                            <h1 class="page-header">Bill Of Material Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="billOfMaterialManagement.jsp">Bill Of Material Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Bill Of Material Management
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
                                <form name="billOfMaterialManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Bill Of Material" onclick="addBillOfMaterial()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Bill Of Material" onclick="removeBillOfMaterial()"  />
                                                </div>
                                            </div>
                                            <br/>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>Name</th>
                                                            <th>Category</th>
                                                            <th>Description</th>
                                                            <th>SKU</th>
                                                            <th>Length</th>
                                                            <th>Width</th>
                                                            <th>Height</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<BillOfMaterialEntity> billOfMaterials = (List<BillOfMaterialEntity>) (session.getAttribute("billOfMaterialList"));

                                                            try {
                                                                if (billOfMaterials != null) {
                                                                    for (int i = 0; i < billOfMaterials.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=billOfMaterials.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=billOfMaterials.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                Blank
                                                            </td>
                                                            <td>
                                                                Blank
                                                            </td>
                                                            <td>
                                                                Blank
                                                            </td>
                                                            <td>
                                                                Blank
                                                            </td>
                                                            <td>
                                                                Blank
                                                            </td>
                                                            <td>
                                                                Blank
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=billOfMaterials.get(i).getId()%>" value="update" onclick="javascript:updateRawMaterial('<%=billOfMaterials.get(i).getId()%>')"/>
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
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Bill Of Material" onclick="addRawMaterial()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Bill Of Material" onclick="removeRawMaterial()"  />
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
