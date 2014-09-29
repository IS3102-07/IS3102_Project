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
                                Schedule Management
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-dashboard"></i>  <a href="../SaleForecast_Servlet/SaleForecast_index_GET">Sales Forecast</a>
                                </li>                              
                                <li>
                                    <i class="icon icon-calendar"></i>  <a href="#">Schedule Management</a>
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
                                            <form action="../SaleAndOperationPlanning_Servlet/deleteSchedule">
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
                                                    <td><input type="checkbox" name="delete" value="<%=schedule.getId()%>" /></td>
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
                                                        <input type="submit" name="submit-btn" value="Delete Schedule" class="btn btn-primary" data-loading-text="Loading...">
                                                    </div>
                                                </div>
                                            </form>  
                                            <hr/>
                                            <form class="form-inline" action="../SaleAndOperationPlanning_Servlet/addSchedule">
                                                <h4><b>Add Schedule:</b></h4>
                                                <div class="form-group">
                                                    <label><b>Year</b></label>
                                                    <input type="number" name="year" class="form-control" style="min-width: 200px" min="2014" max="2050" required="true">
                                                </div>                                                
                                                <div class="form-group">
                                                    <label><b>Month</b></label>
                                                    <select class="form-control" style="min-width: 200px" name="month" required="true">
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
                                                    </select>
                                                </div>     
                                                <div class="form-group">
                                                    <input type="submit" name="submit-btn" value="Add Schedule" class="btn btn-primary" data-loading-text="Loading...">
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
