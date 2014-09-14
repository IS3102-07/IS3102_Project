<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Add Role
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="accountManagement.jsp">Account Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="roleManagement.jsp"> Role Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Add Role
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="../RoleManagement_AddRoleServlet">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" name="name" type="text" required="true" >
                                </div>
                                <div class="form-group">
                                    <label>Access Level</label>
                                    <input class="form-control" name="accessLevel" type="text" required="true">
                                </div>
                                <div class="form-group">
                                    <label>Staff</label>
                                    <input class="form-control" required="true" type="text" name="email" disabled/>
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Add" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="A1/roleManagement_add.jsp" name="source">
                            </form>
                        </div>
                        <!-- /.row -->

                    </div>
                </div>

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
