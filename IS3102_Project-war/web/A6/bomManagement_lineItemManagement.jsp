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
            function addLineItem() {
                document.lineItemManagement.action = "../BomManagement_AddLineItemBomServlet";
                document.lineItemManagement.submit();
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Line Item Management for <%=request.getParameter("bomName")%></h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-sitemap"></i>  <a href="bomManagement.jsp">Bill of Material Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-calendar"></i> Line Item Management for <%=request.getParameter("bomName")%>
                                </li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                    <jsp:include page="../displayMessage.jsp" />

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
                                                    <input class="btn btn-primary btnAdd" name="btnAdd" type="button" value="Add Line Item" />
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
                                                                <%=listOfLineItem.get(i).getItem().getName()%>
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
                                                    <input class="btn btn-primary btnAdd" name="btnAdd" type="button" value="Add Line Item"/>
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Line Item(s)" onclick="removeLineItem()"  />
                                                </div>
                                            </div>
                                            <input type="hidden" name="id" value="">  
                                            <input type="hidden" name="bomId" value="<%=session.getAttribute("bomId")%>">  
                                        </div>
                                        <div id="addLineItemForm" hidden>
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-3"><br>
                                                        Raw Material SKU: 
                                                        <input type="text" class="form-control" name="sku"/><br>
                                                        Quantity: <select class="form-inline" name="qty"> 
                                                            <option value="1">1</option>
                                                            <option value="2">2</option>
                                                            <option value="3">3</option>
                                                            <option value="4">4</option>
                                                            <option value="5">5</option>
                                                            <option value="6">6</option>
                                                            <option value="7">7</option>
                                                            <option value="8">8</option>
                                                            <option value="9">9</option>
                                                            <option value="10">10</option>
                                                            <option value="11">11</option>
                                                            <option value="12">12</option>
                                                            <option value="13">13</option>
                                                            <option value="14">14</option>
                                                            <option value="15">15</option>
                                                            <option value="16">16</option>
                                                            <option value="17">17</option>
                                                            <option value="18">18</option>
                                                            <option value="19">19</option>
                                                            <option value="20">20</option>
                                                        </select>
                                                        <input class="btn btn-primary" name="btnAdd" type="submit" value="Add" onclick="addLineItem()"  />
                                                    </div>
                                                </div>
                                            </div>
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

            $(".btnAdd").click(function () {
                $("html, body").animate({scrollTop: $(document).height()}, "slow");
                $("#addLineItemForm").show("slow", function () {
                });
            });
        </script>

    </body>

</html>
