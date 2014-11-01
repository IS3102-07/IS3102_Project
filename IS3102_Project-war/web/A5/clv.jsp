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
                                                List<MemberEntity> members = (List<MemberEntity>) (session.getAttribute("members"));

                                            %>
                                            <!-- /.table-responsive -->
                                            

                                        </div>



                                        <div class="row">
                                            <div class="col-lg-12">
                                            
                                                        
                                                            <h4>Customer Lifetime Value</h4>
                                                            <table class="table">
                                                                <tr>
                                                                    <td>

                                                                    </td>
                                                                    <td>
                                                                        Acquisition Year
                                                                    </td>
                                                                    <td>
                                                                        Second Year
                                                                    </td>
                                                                    <td>
                                                                        Third Year
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Customers
                                                                    </td>
                                                                    <td>
                                                                        <%=members.size()%>
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Retention Rate
                                                                    </td>
                                                                    <td>
                                                                        <%
                                                                            Double customerRetentionRate = (Double) session.getAttribute("customerRetentionRate");
                                                                            DecimalFormat df = new DecimalFormat("#.00");
                                                                        %>
                                                                        <% out.print(df.format(customerRetentionRate * 100));%>%
                                                                    </td>
                                                                    <td>

                                                                    </td>
                                                                    <td>
                                                                        Total Revenue
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Orders per Year
                                                                    </td>
                                                                    <td>
                                                                        <% Double averageOrdersPerAcquiredYear = (Double) session.getAttribute("averageOrdersPerAcquiredYear"); %>
                                                                        <% out.print(df.format(averageOrdersPerAcquiredYear));%>
                                                                    </td>
                                                                    <td>

                                                                    </td>
                                                                    <td>

                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Avg Order Price
                                                                    </td>
                                                                    <td>
                                                                        
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Total Revenue
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>


                                                                <tr>
                                                                    <td>
                                                                        Total Costs
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Profit Margin
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td>
                                                                        Customer LTV
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                                    <div id="products" class="tab-pane">
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
        

    </script>
</body>
</html>
