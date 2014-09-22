<%@page import="EntityManager.MonthScheduleEntity"%>
<%@page import="EntityManager.WarehouseEntity"%>
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
                            <h1 class="page-header">
                                Sale And Operation Planning
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-dashboard"></i>  <a href="#">Sale And Operation Planning</a>
                                </li>                             
                                <li>
                                    <i class="icon icon-list"></i>  <a href="#">Schedule</a>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <p>Access one scheme to start planning</p>
                                </div>
                                <!-- /.panel-heading -->

                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                            <form action="../SaleAndOperationPlanning_Servlet/sop_schedule_POST">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>Year</th>
                                                            <th>Month</th>                                                                                                                        
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<MonthScheduleEntity> scheduleList = (List<MonthScheduleEntity>) request.getAttribute("scheduleList");
                                                            if (scheduleList != null) {
                                                                for (MonthScheduleEntity schedule : scheduleList) {
                                                        %>
                                                        <tr>   
                                                    <input type="checkbox" name="delete" value="<%=schedule.getId()%>" />
                                                    <td><%= schedule.getYear()%></td>
                                                    <td><%= schedule.getMonth()%></td>                                                            
                                                    </tr>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                    </tbody>
                                                </table>    
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input type="submit" name="submit-btn" value="Add Schedule" class="btn btn-primary" data-loading-text="Loading...">
                                                        <input type="submit" name="submit-btn" value="Delete Schedule" class="btn btn-primary" data-loading-text="Loading...">
                                                    </div>
                                                </div>
                                            </form>    
                                        </div>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.panel-body -->

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

        <%
            if (request.getAttribute("alertMessage") != null) {
        %>
        <script>
            alert("<%= request.getAttribute("alertMessage")%>");
        </script>
        <%
            }
        %>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->        
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            }
            );
        </script>
    </body>

</html>
