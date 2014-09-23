<%@page import="EntityManager.ProductGroupLineItemEntity"%>
<%@page import="EntityManager.ProductGroupEntity"%>
<%@page import="java.util.List"%>

<%
    List<ProductGroupEntity> productGroups = (List<ProductGroupEntity>) (session.getAttribute("productGroups"));
    if (productGroups == null) {
        response.sendRedirect("../ProductGroupManagement_Servlet");
    } else {
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updatePG(id) {
                productGroupManagement.id.value = id;
                document.productGroupManagement.action = "../ProductGroupLineItemManagement_Servlet";
                document.productGroupManagement.submit();
            }
            function addPG() {
                window.event.returnValue = true;
                document.productGroupManagement.action = "productGroupManagement_Add.jsp";
                document.productGroupManagement.submit();
            }
            function checkAll() {
                var allRows = document.supplierManagement.getElementsByTagName("delete");
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
                try {
            %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Product Group Management</h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-exchange"></i> Product Group Management
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
                                    <%
                                        String errMsg = request.getParameter("errMsg");
                                        if (errMsg == null || errMsg.equals("")) {
                                            errMsg = "Add Product Group";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="productGroupManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Create Product Group" onclick="addPG()"  />
                                                </div>
                                            </div>
                                            <br>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll()" /></th>
                                                            <th>Product Group</th>
                                                            <th>Work Hours</th>
                                                            <th>Item SKUs</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            if (productGroups != null) {
                                                                for (int i = 0; i < productGroups.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=productGroups.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=productGroups.get(i).getProductGroupName()%>
                                                            </td>
                                                            <td>
                                                                <%=productGroups.get(i).getWorkHours()%>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    List<ProductGroupLineItemEntity> lineItems = productGroups.get(i).getLineItemList();
                                                                    for (int k = 0; k < lineItems.size(); k++) {
                                                                        if (k == lineItems.size() - 1) {
                                                                            out.println(lineItems.get(k).getFurniture().getSKU());
                                                                        } else {
                                                                            out.println(lineItems.get(k).getFurniture().getSKU() + " , ");
                                                                        }
                                                                    }
                                                                %>
                                                            </td>
                                                            <td style="width:200px">
                                                                <input  type="button" name="btnEdit" class="btn btn-primary btn-block"  value="Update" onclick="javascript:updatePG('<%=productGroups.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                response.sendRedirect("../A1/workspace.jsp");
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Create Product Group" onclick="addPG()"  />
                                                </div>
                                            </div>
                                            <input type="hidden" name="id" value="">    
                                            <input type="hidden" name="source" value="">   
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
<%}%>