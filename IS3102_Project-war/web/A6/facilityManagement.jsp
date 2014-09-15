<html lang="en">

    <jsp:include page="../header2.html" />

    <body>

        <div id="wrapper">

            <jsp:include page="../menu1.jsp" />
            
            <style>
                h4{
                    color: blueviolet;
                }
            </style>
            
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Facility Management</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    insert some wordings
                                </div>

                                <div class="panel-body">

                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="thumbnail">
                                                <a href=""><img src="../img/regionalOffice.jpg" alt="Regional Office"></a>
                                                <div class="caption">                                                    
                                                    <center><h4>Regional Office Management</h4></center>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="thumbnail">
                                                <a href=""><img src="../img/store.jpg" alt="Store"></a>
                                                <div class="caption">
                                                    <br />
                                                    <center><h4>Store Management</h4></center>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="thumbnail">
                                                <a href=""><img src="../img/factory.jpg" alt="Manufacturing Facility"></a>
                                                <div class="caption">                                                    
                                                    <center><h4>Manufacturing Facility Management</h4></center>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="thumbnail">
                                                <a href="../FacilityManagement_Servlet/warehouseManagement_index"><img src="../img/warehouse.jpg" alt="Warehouse"></a>
                                                <div class="caption">
                                                    <center><h4>Warehouse Management</h4></center>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

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


        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function() {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
