<%@page import="EntityManager.SaleAndOperationPlanEntity"%>
<%@page import="EntityManager.ProductGroupEntity"%>
<%@page import="HelperClasses.StoreHelper"%>
<%@page import="EntityManager.MonthScheduleEntity"%>
<%@page import="EntityManager.StoreEntity"%>
<%@page import="EntityManager.RegionalOfficeEntity"%>
<%@page import="HelperClasses.MessageHelper"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>        
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Sales And Operation Planning</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-dashboard"></i>  <a href="../SaleAndOperationPlanning_Servlet/sop_index_GET">Sales And Operation Planning</a>
                                </li>   
                                <li>
                                    <i class="icon icon-calendar"></i>  <a href="../SaleAndOperationPlanning_Servlet/sop_schedule_GET">Schedule</a>
                                </li>
                                <li>
                                    <i class="icon icon-list"></i>  <a href="../SaleAndOperationPlanning_Servlet/sop_main_GET">Dashboard</a>
                                </li>
                                <li>
                                    <i class="icon icon-edit"></i>  <a href="#">Edit Sale And Operation Plan</a>
                                </li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <style>
                        select {
                            max-width: 300px;
                        }
                    </style>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">                             
                                        <div class="col-lg-4">
                                            <%  StoreHelper storeHelper = (StoreHelper) request.getAttribute("storeHelper");%>
                                            <h4><b> Store:  </b><%= storeHelper.store.getName()%></h4>
                                        </div>                                                
                                        <div class="col-lg-4">
                                            <% MonthScheduleEntity schedule = (MonthScheduleEntity) request.getAttribute("schedule");%>
                                            <h4><b> Period: </b><%= schedule.getYear()%> - <%= schedule.getMonth()%> </h4>
                                        </div>                                      
                                        <div class="col-lg-4">
                                            <% ProductGroupEntity productGroup = (ProductGroupEntity) request.getAttribute("productGroup"); %>
                                            <h4><b> Product Group:  </b><%= productGroup.getName()%></h4>
                                        </div>
                                    </div>                                        
                                </div>
                                <div class="panel-body">
                                    <% SaleAndOperationPlanEntity sop = (SaleAndOperationPlanEntity)request.getAttribute("sopEntity");  %>
                                    <form action="../SaleAndOperationPlanning_Servlet/sop_edit_POST">
                                        <div class="form-group">
                                            <label>Sale Forecast</label>
                                            <input type="number" class="form-control" name="saleForecast" value="<%= sop.getSaleForcastdata() %>" required="true" >
                                        </div>

                                        <div class="form-group">
                                            <label>Production Plan</label>
                                            <input type="number" class="form-control" name="productionPlan" value="<%= sop.getProductionPlan() %>" required="true" >
                                        </div>

                                        <div class="form-group">
                                            <label>Current Inventory level</label>
                                            <input type="number" class="form-control" name="currentInventory" value="<%= sop.getCurrentInventoryLevel() %>" required="true">
                                        </div>

                                        <div class="form-group">
                                            <label>Target Inventory Level</label>
                                            <input type="number" class="form-control" name="targetInventoty" value="<%= sop.getTargetInventoryLevel() %>" required="true" >
                                        </div>
                                        
                                        <input type="hidden" name="sopId" value="<%= sop.getId() %>" >
                                        
                                        <input type="submit" class="btn btn-primary" value="Submit">

                                    </form>

                                </div>                               

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

        <script>
            function getStore() {
                var regionalOfficeId = $("#select_regionalOffice").find('option:selected').val();
                $.get('../SOP_ajax_Servlet', {regionalOfficeId: regionalOfficeId}, function (responseText) {
                    var stores = responseText.trim().split(';');
                    var x = document.getElementById("select_store");
                    while (x.length > 0) {
                        x.remove(0);
                    }
                    for (var i = 0; i < stores.length - 1; i++) {
                        var option = document.createElement("option");
                        option.text = stores[i];
                        x.add(option);
                    }
                });
            }
        </script>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            }
            );
        </script>
    </body>
</html>
