<%@page import="EntityManager.MemberEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
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
                    document.staffManagement.action = "../StaffManagement_StaffServlet";
                    document.staffManagement.submit();
                } else {
                    window.event.returnValue = true;
                    document.staffManagement.action = "../StaffManagement_RemoveStaffServlet";
                    document.staffManagement.submit();
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
                                <li class="active">
                                    <i class="icon icon-users"></i> Value Analysis
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
                                        String goodMsg = request.getParameter("goodMsg");
                                        if (errMsg == null && goodMsg == null) {
                                            out.println("List of Analytics");
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
                                <form name="staffManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <br>
                                            <%
                                                Double totalCustomerRevenue = (Double) session.getAttribute("totalCustomerRevenue");
                                                Double totalNonCustomerRevenue = (Double) session.getAttribute("totalNonCustomerRevenue");
                                                Integer numOfMembers = (Integer) (session.getAttribute("numOfMembers"));

                                            %>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <h3 class="panel-title"><i class="fa fa-long-arrow-right fa-fw"></i> Total Revenue : <%=totalCustomerRevenue + totalNonCustomerRevenue%></h3>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div id="morris-donut-chart"></div>
                                                            <div class="text-right">
                                                                <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <table class="table">
                                                        <tr>
                                                            <td>Item</td><td>Value</td>
                                                        </tr>
                                                        <tr>
                                                            <td>Total Number of Customers :</td><td><%=numOfMembers%></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Total Number of Transactions :</td><td><% Integer numOfTransactions = (Integer) session.getAttribute("numOfTransactions"); out.println(numOfTransactions); %></td>
                                                        </tr>
                                                    </table>
                                                      
                                                </div>

                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="col-md-4">
                                                        <div class="featured-box featured-box-secundary">
                                                            <div class="box-content">
                                                                <a href="../Analytical_ValueAnalysisRFMServlet"><i class="icon-featured icon icon-user"> </i>
                                                                    <h4>Recency Frequency Monetary Value</h4>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="featured-box featured-box-secundary">
                                                            <div class="box-content">
                                                                <a href="../Analytical_ValueAnalysisCLVServlet"><i class="icon-featured icon icon-user"> </i>
                                                                    <h4>Customer Lifetime Value</h4>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="featured-box featured-box-primary">
                                                            <div class="box-content">
                                                                <a href="../MemberManagement_MemberServlet"><i class="icon-featured icon icon-user"></i>
                                                                    <h4>Items Analysis</h4>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
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
                    <p id="messageBox">Staff will be removed. Are you sure?</p>
                </div>
                <div class="modal-footer">                        
                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Confirm" onclick="removeStaff()"  />
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


        new Morris.Donut({
            element: 'morris-donut-chart',
            data: [{
                    label: "Member Sales",
                    value: <%=totalCustomerRevenue%>
                }, {
                    label: "Non-Member Sales",
                    value: <%=totalNonCustomerRevenue%>
                }],
            resize: true
        });

    </script>
</body>
</html>
