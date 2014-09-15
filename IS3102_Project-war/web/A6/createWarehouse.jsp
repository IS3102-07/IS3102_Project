<html lang="en">

    <jsp:include page="../header2.html" />

    <body>

        <div id="wrapper">

            <jsp:include page="../menu1.jsp" />
            
            <style>
                label{
                    font-size: 18px;
                }
                input{
                    max-width: 280px;
                }
            </style>
            
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Warehouse Management</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">

                                    <form class="myForm" action="">
                                        <div class="form-group">
                                            <label for="input_warehouseName">Warehouse Name</label>
                                            <input type="text" class="form-control" id="input_warehouseName" name="warehouseName" required="true">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="input_address">Address</label>
                                            <input type="text" class="form-control" id="input_address"  name="address">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="input_telephone">Telephone</label>
                                            <input type="text" class="form-control" id="input_telephone"  name="telephone" >
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="input_email">Email</label>
                                            <input type="email" class="form-control" id="input_email"  name="email" >
                                        </div>
                                        
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-default" value="submit">
                                        </div>
                                    </form>

                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">

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
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
