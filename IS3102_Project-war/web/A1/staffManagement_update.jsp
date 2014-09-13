<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
<html lang="en">

    <jsp:include page="../header2.html" />
    <body>

        <div id="wrapper">
            <jsp:include page="../menu2.html" />

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
                        int id = 0;
                        if (request.getParameter("id") == null) {
                            response.sendRedirect("staffManagement.jsp");
                        } else {
                            id = Integer.parseInt(request.getParameter("id"));
                        }

                        List<StaffEntity> staffs = (List<StaffEntity>) (session.getAttribute("staffs"));

                        if (staffs == null || staffs.isEmpty()) {
                            response.sendRedirect("../AccountManagement_StaffServlet");
                        } else {

                            StaffEntity staff = new StaffEntity();
                            for (int i = 0; i < staffs.size(); i++) {
                                if (staffs.get(i).getId() == id) {
                                    staff = staffs.get(i);
                                }
                            }
                    %>

                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../AccountManagement_RegistrationServlet">
                                <div class="form-group">
                                    <label>Identification No</label>
                                    <input class="form-control" name="identificationNo" id="identificationNo" type="text" placeholder="<%=staff.getIdentificationNo()%>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" name="name" id="name" type="text" placeholder="<%=staff.getName()%>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>E-mail Address</label>
                                    <input class="form-control" required="true" type="email" name="email" id="email" value="<%=staff.getEmail()%>">
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input class="form-control" required="true" type="number" name="phone" id="phone" value="<%=staff.getPhone()%>">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input class="form-control" type="password" name="password" id="password">
                                </div>
                                <div class="form-group">
                                    <label>Re-enter Password</label>
                                    <input class="form-control" type="password"  name="repassword" id="repassword">
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="form-control" type="text" required="true" name="address" id="address" value="<%=staff.getAddress()%>">
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Register" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="A1/staffManagement.jsp" name="source">
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
                    <%}%>
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
