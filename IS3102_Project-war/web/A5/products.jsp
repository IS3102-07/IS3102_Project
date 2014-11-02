<%@page import="EntityManager.MemberEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.LineItemEntity"%>
<%@page import="java.text.DecimalFormat"%>
<html lang="en">
    <jsp:include page="../header2.html" />

    <body>

        <script>
            function updateStaff(id) {
                staffManagement.id.value = id;
                document.staffManagement.action = "../StaffManagement_UpdateStaffServlet";
                document.staffManagement.submit();
            }
            function sendLoyaltyPoints() {
                checkboxes = document.getElementsByName('delete');
                var numOfTicks = 0;
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    if (checkboxes[i].checked) {
                        numOfTicks++;
                    }
                }
                if (checkboxes.length == 0 || numOfTicks == 0) {
                    window.event.returnValue = true;
                    document.rfm.action = "../Analytical_ValueAnalysisServlet";
                    document.rfm.submit();
                } else {
                    window.event.returnValue = true;
                    document.rfm.action = "../Analytical_ValueAnalysisSendLoyaltyServlet";
                    document.rfm.submit();
                }
            }
            function removeStaff() {
                checkboxes = document.getElementsByName('delete');
                var numOfTicks = 0;
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    if (checkboxes[i].checked) {
                        numOfTicks++;
                    }
                }
                if (checkboxes.length == 0 || numOfTicks == 0) {
                    window.event.returnValue = true;
                    document.rfm.action = "../StaffManagement_StaffServlet";
                    document.rfm.submit();
                } else {
                    window.event.returnValue = true;
                    document.rfm.action = "../StaffManagement_RemoveStaffServlet";
                    document.rfm.submit();
                }
            }
            function addStaff() {
                window.event.returnValue = true;
                document.staffManagement.action = "staffManagement_add.jsp";
                document.staffManagement.submit();
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
                            <h1 class="page-header">Value Analysis</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-users"></i> <a href="analytical.jsp">Analytical CRM</a>
                                </li>
                                <li>
                                    <i class="icon icon-users"></i> <a href="../Analytical_ValueAnalysisServlet">Value Analysis</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-users"></i> Products Analysis
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
                                   Products Analysis
                                </div>
                                <!-- /.panel-heading -->
                                <form name="rfm">
                                   
                                            <%
                                                List<LineItemEntity> sortBestSellingFurniture = (List<LineItemEntity>) (session.getAttribute("sortBestSellingFurniture"));
                                            %>
                                            <!-- /.table-responsive -->

                                    <div class="row">
                                        <div class="col-lg-12">

                                            <div class="panel-body">
                                                <div class="table-responsive">

                                                    <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" member="grid">
                                                        <table class="table table-bordered" id="dataTables-example">
                                                            <thead>
                                                                <tr>
                                                                    <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                                    <th>Name</th>
                                                                    <th>Recency</th>
                                                                    <th>Frequency</th>
                                                                    <th>Monetary Value</th>
                                                                    <th>Action</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%
                                                                    
                                                                    if (sortBestSellingFurniture != null) {
                                                                        for (int i = 0; i < sortBestSellingFurniture.size(); i++) {
                                                                            LineItemEntity item = sortBestSellingFurniture.get(i);
                                                                %>
                                                                <tr>                   
                                                                    <td>
                                                                        <input type="checkbox" name="delete" value="<%=item.getId()%>" />
                                                                    </td>
                                                                    <td>
                                                                        <%=item.getId()%>
                                                                    </td>
                                                                    <td >
                                                                        
                                                                    </td>
                                                                    <td>
                                                                        
                                                                    </td>
                                                                    <td>
                                                                        
                                                                    </td>

                                                                    <td>
                                                                        <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=item.getId()%>" value="View Sales Record" onclick="javascript:viewSalesRecord('<%=item.getId()%>')"/>
                                                                    </td>
                                                                </tr>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <!-- /.table-responsive -->
                                                    <div class="row">
                                                        <div class="col-md-12">                                                 
                                                            <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Send Loyalty Points</button></a>
                                                        </div>
                                                    </div>
                                                    <input type="hidden" name="id" value="">    
                                                </div>

                                            </div>
                                        </div>

                                        <div id="products" class="tab-pane">
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

        <div role="dialog" class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Alert</h4>
                    </div>
                    <div class="modal-body">
                        <p id="messageBox">Enter Loyalty Amount : 
                            <input type="number" name="loyaltyPoints"></p>
                    </div>
                    <div class="modal-footer">                        
                        <input class="btn btn-primary" name="btnRemove" type="submit" value="Confirm" onclick="sendLoyaltyPoints()"  />
                        <a class="btn btn-default" data-dismiss ="modal">Close</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function() {
                $('#dataTables-example').dataTable();
            });
        </script>
    </body>
</html>
