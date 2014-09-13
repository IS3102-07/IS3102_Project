<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateStaff(id) {
                staffManagement.id.value = id;
                document.staffManagement.action = "staffManagement_update.jsp";
                document.staffManagement.submit();
            }
            function removeStaff() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.staffManagement.action = "../AccountManagement_RemoveStaffServlet";
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
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Staff  Management</h1>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <%
                        List<StaffEntity> staffs = (List<StaffEntity>) (session.getAttribute("staffs"));
                        if (staffs == null || staffs.isEmpty()) {
                            out.println("No staff yet");
                        } else {
                    %>

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
                                                            <th></th>
                                                            <th>Identification No</th>
                                                            <th>Name</th>
                                                            <th>Email</th>
                                                            <th>Phone</th>
                                                            <th>Roles</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            for (int i = 0; i < staffs.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=staffs.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=staffs.get(i).getIdentificationNo()%>
                                                            </td>
                                                            <td>
                                                                <%=staffs.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=staffs.get(i).getEmail()%>
                                                            </td>
                                                            <td>
                                                                <%=staffs.get(i).getPhone()%>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    List<RoleEntity> roles = (List<RoleEntity>) (staffs.get(i).getRoles());
                                                                    if (roles.isEmpty()) {
                                                                        out.println("-");
                                                                    } else {
                                                                        for (int k = 0; k < roles.size(); k++) {
                                                                            out.println(roles.get(i).getName());
                                                                        }
                                                                    }
                                                                %>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=staffs.get(i).getId()%>" value="update" onclick="javascript:updateStaff('<%=staffs.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                            }
                                                        %>
                                                </table>

                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input class="btn btn-primary" name="btnAdd" type="submit" value="Register Staff" onclick="addStaff()"  />
                                                        <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Staff" onclick="removeStaff()"  />
                                                    </div>
                                                </div>
                                                <input type="hidden" name="id" value="">    
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

                    <%}%>
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
