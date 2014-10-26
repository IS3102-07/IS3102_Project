<%@page import="EntityManager.MemberEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
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
                                                Integer cummulativeSpendingAgeGrp1 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp1");
                                                Integer cummulativeSpendingAgeGrp2 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp2");
                                                Integer cummulativeSpendingAgeGrp3 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp3");
                                                Integer cummulativeSpendingAgeGrp4 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp4");
                                                Integer averageCummulativeSpending = (Integer) session.getAttribute("averageCummulativeSpending");
                                                List <MemberEntity> members = (List <MemberEntity>) (session.getAttribute("members"));
                                                
                                                Integer numOfMembersInAgeGroup1 = (Integer) session.getAttribute("numOfMembersInAgeGroup1");
                                                Integer numOfMembersInAgeGroup2 = (Integer) session.getAttribute("numOfMembersInAgeGroup2");
                                                Integer numOfMembersInAgeGroup3 = (Integer) session.getAttribute("numOfMembersInAgeGroup3");
                                                Integer numOfMembersInAgeGroup4 = (Integer) session.getAttribute("numOfMembersInAgeGroup4");
                                            %>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-6">
                                                    Total Customer Revenue : <%=totalCustomerRevenue%>
                                                    <div class="progress">
                                                        <div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
                                                            <span class="sr-only">45% Complete</span>
                                                        </div>
                                                    </div>
                                                    Total Number of Customers : <%=members.size()%> <br/>
                                                    Average Customer Cummulative Spending : <%=averageCummulativeSpending%>
                                                </div>
                                                <div class="col-md-6">
                                                    <div id="myfirstchart"></div>
                                                </div>
                                            </div>
                                            <div class="row">       
                                                <div class="col-md-12">        
                                                    <table class="table">
                                                        <tr>
                                                            <td>
                                                                Group 1 Members Profile
                                                            </td>
                                                            <td>
                                                                Group 2 Members Profile
                                                            </td>
                                                            <td>
                                                                Group 3 Members Profile
                                                            </td>
                                                            <td>
                                                                Group 4 Members Profile
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                testing 123
                                                            </td>
                                                            <td>
                                                                testing 321
                                                            </td>
                                                        </tr>
                                                    </table>
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
            new Morris.Bar({
                // ID of the element in which to draw the chart.
                element: 'myfirstchart',
                // Chart data records -- each entry in this array corresponds to a point on
                // the chart.
                data: [
                    {y: '18-25', a: <%=cummulativeSpendingAgeGrp1%>, b: <%=numOfMembersInAgeGroup1%>, c: 1},
                    {y: '26-40', a: <%=cummulativeSpendingAgeGrp2%>, b: <%=numOfMembersInAgeGroup2%>, c: 2},
                    {y: '41-55', a: <%=cummulativeSpendingAgeGrp3%>, b: <%=numOfMembersInAgeGroup2%>, c: 3},
                    {y: '56-75', a: <%=cummulativeSpendingAgeGrp4%>, b: <%=numOfMembersInAgeGroup1%>, c: 4}
                ],
                xkey: 'y',
                ykeys: ['a', 'b', 'c'],
                labels: ['Total Cummulative Spending',  'Total Number Of Members', 'Age Group']
            });

        </script>
    </body>
</html>
