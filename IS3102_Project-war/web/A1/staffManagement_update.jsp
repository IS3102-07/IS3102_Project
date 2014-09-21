<%@page import="EntityManager.RoleEntity"%>
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
                                    <i class="icon icon-users"></i> <a href="accountManagement.jsp">Account Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-users"></i> <a href="staffManagement.jsp">Staff Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i>  Staff Particulars Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <jsp:include page="../displayMessage.jsp" />


                    <%
                        List<StaffEntity> staffs = (List<StaffEntity>) session.getAttribute("staffs");
                        try {
                            String id = (String) session.getAttribute("staffUpdateId");
                            StaffEntity staff = new StaffEntity();
                            for (int i = 0; i < staffs.size(); i++) {
                                if (staffs.get(i).getId() == Long.parseLong(id)) {
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
                                    <label>New Password (leave blank unless setting a new password)</label>
                                    <input class="form-control" type="password" name="password">
                                </div>
                                <div class="form-group">
                                    <label>Re-enter New Password</label>
                                    <input class="form-control" type="password"  name="repassword">
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="form-control" type="text" required="true" name="address" value="<%=staff.getAddress()%>">
                                </div>
                                <div class="form-group">
                                    <label>Roles</label><br/>
                                    <%
                                        List<RoleEntity> roles = (List<RoleEntity>) session.getAttribute("staffUpdateRoles");
                                        boolean role1, role2, role3, role4, role5, role6, role7;
                                        role1 = role2 = role3 = role4 = role5 = role6 = role7 = false;

                                        for (RoleEntity currentRole : roles) {
                                            System.out.println(currentRole.getName());
                                            if (currentRole.getId().toString().equals("1")) {
                                                role1 = true;
                                            } else if (currentRole.getId().toString().equals("2")) {
                                                role2 = true;
                                            } else if (currentRole.getId().toString().equals("3")) {
                                                role3 = true;
                                            } else if (currentRole.getId().toString().equals("4")) {
                                                role4 = true;
                                            } else if (currentRole.getId().toString().equals("5")) {
                                                role5 = true;
                                            } else if (currentRole.getId().toString().equals("6")) {
                                                role6 = true;
                                            } else if (currentRole.getId().toString().equals("7")) {
                                                role7 = true;
                                            }
                                        }
                                    %>
                                    <input type="checkbox" name="roles" value="1" <%if (role1) {%>checked<%}%>/> Administrator &nbsp; &nbsp; &nbsp;<br/>
                                    <input type="checkbox" name="roles" value="2" <%if (role2) {%>checked<%}%>/> Regional Manager &nbsp; &nbsp; &nbsp;<br/>
                                    <input type="checkbox" name="roles" value="3" <%if (role3) {%>checked<%}%>/> Warehouse Manager &nbsp; &nbsp; &nbsp;<br/>
                                    <input type="checkbox" name="roles" value="4" <%if (role4) {%>checked<%}%>/> Store Manager &nbsp; &nbsp; &nbsp;<br/>
                                    <input type="checkbox" name="roles" value="5" <%if (role5) {%>checked<%}%>/> Marketing Director &nbsp; &nbsp; &nbsp;<br/>
                                    <input type="checkbox" name="roles" value="6" <%if (role6) {%>checked<%}%>/> Product Development Engineer &nbsp; &nbsp; &nbsp;<br/>
                                    <input type="checkbox" name="roles" value="7" <%if (role7) {%>checked<%}%>/> Purchasing Manager &nbsp; &nbsp; &nbsp;<br/>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" name="update" value="yes"/>
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block"/>
                                </div>
                                <input type="hidden" value="<%=staff.getId()%>" name="id">
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
                    <%
                        } catch (Exception ex) {
                            response.sendRedirect("../StaffManagement_StaffServlet");
                            ex.printStackTrace();
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
