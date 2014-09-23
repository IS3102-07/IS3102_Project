<%@page import="EntityManager.StoreEntity"%>
<%@page import="EntityManager.SaleAndOperationPlanEntity"%>
<%@page import="EntityManager.ProductGroupEntity"%>
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
                                    <i class="icon icon-list"></i>  <a href="#">Dashboard</a>
                                </li>
                            </ol>
                        </div>
                    </div>

                    <div class="row">                             
                        <div class="col-lg-4">
                            <%  StoreEntity store = (StoreEntity) request.getAttribute("store");%>
                            <h4><b> Store:  </b><%= store.getName()%></h4>
                        </div>                                                
                        <div class="col-lg-4">
                            <% MonthScheduleEntity schedule = (MonthScheduleEntity) request.getAttribute("schedule");%>
                            <h4><b> Period: </b><%= schedule.getYear()%> - <%= schedule.getMonth()%> </h4>
                        </div>                                      
                    </div>
                    <br>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4><b>Unplanned Product Groups</b></h4>
                                </div>
                                <!-- /.panel-heading -->

                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                            <form action="../SaleAndOperationPlanning_Servlet/sop_main_POST">
                                                <table class="table table-striped table-bordered table-hover" id="dataTable1">
                                                    <thead>
                                                        <tr>
                                                            <th>Product Group Name</th>
                                                            <th>Action</th>                                                                                                                        
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<ProductGroupEntity> unplannedProductGroupList = (List<ProductGroupEntity>) request.getAttribute("unplannedProductGroupList");

                                                            for (ProductGroupEntity p : unplannedProductGroupList) {
                                                        %>
                                                        <tr>
                                                            <td><%= p.getProductGroupName()%></td>                                                            
                                                            <td>
                                                                <button class="btn btn-primary" name="productGroupId" value="<%= p.getId()%>">Plan</button>
                                                            </td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>                                                    
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
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4><b>Sale And Operation Plans</b></h4>
                                </div>
                                <!-- /.panel-heading -->

                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                            <form action="../SaleAndOperationPlanning_Servlet/deleteSchedule">
                                                <table class="table table-striped table-bordered table-hover" id="dataTable2">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>Year</th>
                                                            <th>Month</th>                                                                                                                        
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%--                    
                                                        <%
                                                            List<SaleAndOperationPlanEntity> sopList = (List<SaleAndOperationPlanEntity>) request.getAttribute("sopList");
                                                            for (SaleAndOperationPlanEntity sop : sopList) {
                                                        %>
                                                        <tr>
                                                            <td></td><td></td><td></td><td></td><td></td>
                                                        </tr>
                                                        <%
                                                            }

                                                        %>
                                                        --%>
                                                    </tbody>
                                                </table>    
                                                <div class="row">
                                                    <div class="col-md-12">                                                        
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
                $('#dataTable1').dataTable();
                $('#dataTable2').dataTable();
            }
            );
        </script>
    </body>

</html>
