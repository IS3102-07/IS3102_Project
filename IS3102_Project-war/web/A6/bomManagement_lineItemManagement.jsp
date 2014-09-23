<%@page import="EntityManager.LineItemEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="EntityManager.BillOfMaterialEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
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
                            <h1 class="page-header">Line Item Management for BOM: <%=request.getParameter("bomName")%></h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-sitemap"></i>  <a href="bomManagement.jsp">Bill of Material Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-calendar"></i> Line Item Management for BOM: <%=request.getParameter("bomName")%>
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
                                    Add or remove line item(s)
                                </div>
                                <!-- /.panel-heading -->
                                <form name="lineItemManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Line Item" onclick="addLineItem()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Line Item(s)" onclick="removeLineItem()"  />
                                                </div>
                                            </div>
                                            <br/>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th style="width:5%"><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th style="width:30%">Raw Material</th>
                                                            <th style="width:15%">Quantity</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<LineItemEntity> listOfLineItem = (List<LineItemEntity>) (session.getAttribute("bomListLineOfItems"));
                                                            try {
                                                                if (listOfLineItem != null) {
                                                                    for (int i = 0; i < listOfLineItem.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=listOfLineItem.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                             Raw Material 1
                                                            </td>
                                                            <td>
                                                                <%=listOfLineItem.get(i).getQuantity()%>
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
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Line Item" onclick="addLineItem()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Line Item(s)" onclick="removeLineItem()"  />
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
