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
                                Register Staff
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="accountManagement.jsp">Account Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="staffManagement.jsp"> Staff Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Register Staff
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <%
                        String errMsg = request.getParameter("errMsg");
                        if (errMsg == null || errMsg.equals("")) {
                            errMsg = "";
                        } else {
                    %>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="alert alert-danger">
                                <%=errMsg%>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <!-- /.warning -->
                    
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="../StaffManagement_AddStaffServlet" onsubmit="return validatePassword()">
                                <div class="form-group">
                                    <label>Identification No</label>
                                    <input class="form-control" name="identificationNo" type="text" required="true">
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" name="name"  type="text" required="true">
                                </div>
                                <div class="form-group">
                                    <label>E-mail Address</label>
                                    <input class="form-control" required="true" type="email" name="email" >
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input class="form-control" required="true" type="text" name="phone" >
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input class="form-control" type="password" name="password" id="password">
                                </div>
                                <div class="form-group">
                                    <label>Re-enter Password</label>
                                    <input class="form-control" type="password" name="repassword" id="repassword">
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="form-control" type="text" required="true" name="address" >
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Register" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="A1/staffManagement_add.jsp" name="source">
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
        <script>
            function validatePassword() {
                var password = document.getElementById("password").value;
                var repassword = document.getElementById("repassword").value;
                var ok = true;
                if (password != repassword) {
                    //alert("Passwords Do not match");
                    document.getElementById("password").style.borderColor = "#E34234";
                    document.getElementById("repassword").style.borderColor = "#E34234";
                    alert("Passwords do not match. Please key again.");
                    ok = false;
                }
                return ok;
            }
        </script>
    </body>

</html>
