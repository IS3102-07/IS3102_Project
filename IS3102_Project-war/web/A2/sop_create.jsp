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
                            <h1 class="page-header">Sale And Operation Planning</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-dashboard"></i>  <a href="#">Sale And Operation Planning</a>
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
                                    <p>Store, Schedule, Product Group</p>
                                </div>
                                <div class="panel-body">
                                    
                                    <form action="../SaleAndOperationPlanning_Servlet/sop_index_Post">
                                        <div class="form-group">
                                            <label>Sale Forecast</label>
                                            <input type="number" class="form-control" name="saleForecast" value="300" required="true" >
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Production Plan</label>
                                            <input type="number" class="form-control" name="productionPlan" required="true" >
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Current Inventory level</label>
                                            <input type="number" class="form-control" name="currentInventory" value="20" required="true">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Target Inventory Level</label>
                                            <input type="number" class="form-control" name="targetInventoty" required="true" >
                                        </div>
                                        
                                        <input type="submit" class="btn btn-primary" value="Access">

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
                    while(x.length > 0){
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
