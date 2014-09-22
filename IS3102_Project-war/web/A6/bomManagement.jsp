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
                            <h1 class="page-header">Bill of Materials Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-sitemap"></i>  <a href="billOfMaterialManagement.jsp">Bill of Material Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Bill of Material Management
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
                                    Welcome to BOM Management!
                                </div>
                                <!-- /.panel-heading -->
                                <form name="billOfMaterialManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add BOM" onclick="addBillOfMaterial()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove BOM(s)" onclick="removeBillOfMaterial()"  />
                                                </div>
                                            </div>
                                            <br/>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>BOM Name</th>
                                                            <th>Description</th>
                                                            <th>Furniture</th>
                                                            <th>Link BOM And Furniture</th>
                                                            <th>View BOM</th>
                                                            <th>Edit BOM</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<BillOfMaterialEntity> listOfBOM = (List<BillOfMaterialEntity>) (session.getAttribute("listOfBOM"));

                                                            try {
                                                                if (listOfBOM != null) {
                                                                    for (int i = 0; i < listOfBOM.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=listOfBOM.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=listOfBOM.get(i).getName()%>
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
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=listOfBOM.get(i).getId()%>" value="update" onclick="javascript:updateRawMaterial('<%=listOfBOM.get(i).getId()%>')"/>
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
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add BOM" onclick="addRawMaterial()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove BOM(s)" onclick="removeRawMaterial()"  />
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
