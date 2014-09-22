<%@page import="EntityManager.FurnitureEntity"%>
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
                                    <i class="icon icon-user"></i>  <a href="itemManagement.jsp">Item Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-sitemap"></i> Bill of Material Management
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
                                    Add or remove Bill Of Materials
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
                                                            <th style="width:5%"><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th style="width:15%">BOM Name</th>
                                                            <th style="width:15%">Furniture</th>
                                                            <th style="width:40%">Description</th>
                                                            <th style="width:13%">Edit BOM</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<FurnitureEntity> listOfFurniture = (List<FurnitureEntity>) (session.getAttribute("listOfFurniture"));
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
                                                                <%
                                                                    FurnitureEntity f = listOfBOM.get(i).getFurniture();
                                                                    String furnitureName = null;
                                                                    if (f != null) {
                                                                        furnitureName = f.getName();
                                                                    } else {
                                                                        if (furnitureName != null) {
                                                                            out.print(furnitureName);
                                                                        } else {
                                                                            out.print("<select class=\"form-inline\" name='furnitureDropdown'>");
                                                                            out.print("<option value=''>Select</option>");
                                                                            for (FurnitureEntity furniture : listOfFurniture) {
                                                                                out.print("<option value='" + furniture.getId() + "'>");
                                                                                out.print(furniture.getName());
                                                                                out.print("</option>");
                                                                            }
                                                                            out.print("</select>");
                                                                            out.print("<input type='button' style='width:30%;height:30px;float:right;' name='btnLink' class='btn btn-primary btn-block' value='Link' onclick='javascript:linkFurniture('" + listOfFurniture.get(i).getId() + "')'/>");
                                                                        }
                                                                    }%>
                                                            </td>
                                                            <td>
                                                                <%=listOfBOM.get(i).getDescription()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" value="Edit" onclick="javascript:updateRawMaterial('<%=listOfBOM.get(i).getId()%>')"/>
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
