<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function removeStaff() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.staffManagement.action = "../AccountManagement_RemoveStaff";
                    document.staffManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function addStaff() {
                window.event.returnValue = true;
                document.staffManagement.action = "staffManagement_add.jsp";
                document.staffManagement.submit();
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu2.html" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Staff  Management</h1>
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
                                <!-- /.panel-heading -->
                                <form name="staffManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th>Name</th>
                                                            <th>Email</th>
                                                            <th>Gender</th>
                                                            <th>Role</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>













                                                            <td>Trident</td>
                                                            <td>Internet Explorer 4.0</td>
                                                            <td>Win 95+</td>
                                                            <td class="center">4</td>
                                                            <td><input type="button" class="btn btn-primary btn-block" name="btnDetails" id="1" value="Vew" onclick=""/></td>
                                                        </tr>
                                                </table>

                                                <div class="row">
                                                    <div class="col-md-12">

                                                        <input class="btn btn-primary" name="btnAdd" type="submit" value="Register Staff" onclick="addStaff()"  />
                                                        <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Staff" onclick="removeStaff()"  />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.table-responsive -->
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
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
