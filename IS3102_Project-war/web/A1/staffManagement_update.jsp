<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
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
                                Staff Particulars Update
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="accountManagement.jsp">Account Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="staffManagement.jsp"> Staff Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i>  Staff Particulars Update
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

                    <%
                        int id = 0;
                        if (request.getParameter("id") == null) {
                            response.sendRedirect("staffManagement.jsp");
                        } else {
                            id = Integer.parseInt(request.getParameter("id"));
                        }

                        List<StaffEntity> staffs = (List<StaffEntity>) (session.getAttribute("staffs"));

                        if (staffs == null || staffs.isEmpty()) {
                            response.sendRedirect("../StaffManagement_StaffServlet");
                        } else {

                            StaffEntity staff = new StaffEntity();
                            if (staff != null) {
                                for (int i = 0; i < staffs.size(); i++) {
                                    if (staffs.get(i).getId() == id) {
                                        staff = staffs.get(i);
                                    }
                                }
                    %>

                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../StaffManagement_UpdateStaffServlet">
                                <div class="form-group">
                                    <label>Identification No</label>
                                    <input class="form-control" required="true" name="identificationNo" type="text" value="<%=staff.getIdentificationNo()%>">
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" required="true" name="name" type="text" value="<%=staff.getName()%>">
                                </div>
                                <div class="form-group">
                                    <label>E-mail Address</label>
                                    <input class="form-control" required="true" type="email" name="email" value="<%=staff.getEmail()%>" disabled/>
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input class="form-control" required="true" type="text" name="phone" value="<%=staff.getPhone()%>">
                                </div>
                                <div class="form-group">
                                    <label>New Password</label>
                                    <input class="form-control" type="password" name="password" required="true" >
                                </div>
                                <div class="form-group">
                                    <label>Re-enter Password</label>
                                    <input class="form-control" type="password"  name="repassword"required="true" >
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="form-control" type="text" required="true" name="address" value="<%=staff.getAddress()%>">
                                </div>
                                <div class="form-group">
                                    <label>Roles</label>

                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="<%=staff.getId()%>" name="id">
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
                    <%}
                        }%>
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
