<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateRole(id) {
                rolesManagement.id.value = id;
                document.rolesManagement.action = "roleManagement_update.jsp";
                document.rolesManagement.submit();
            }
            function removeRole() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.rolesManagement.action = "../RoleManagement_RemoveRoleServlet";
                    document.rolesManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function addRole() {
                window.event.returnValue = true;
                document.rolesManagement.action = "roleManagement_add.jsp";
                document.rolesManagement.submit();
            }
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }

        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Roles Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-users"></i> <a href="accountManagement.jsp">Account Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-users"></i> Role Management
                                </li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <%
                                        String errMsg = request.getParameter("errMsg");
                                        if (errMsg == null || errMsg.equals("")) {
                                            errMsg = "Insert some text";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="rolesManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">                                          
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Role" onclick="addRole()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Role" onclick="removeRole()"  />
                                                </div>
                                            </div>
                                            <br>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox" onclick="checkAll(this)" /></th>
                                                            <th>Name</th>
                                                            <th>Access Level</th>
                                                            <th>Staff</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<RoleEntity> roles = (List<RoleEntity>) (session.getAttribute("roles"));
                                                            if (roles != null) {
                                                                for (int i = 0; i < roles.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=roles.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=roles.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=roles.get(i).getAccessLevel()%>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    List<StaffEntity> staffs = (List<StaffEntity>) (roles.get(i).getStaffs());
                                                                    if (staffs.isEmpty()) {
                                                                        out.println("-");
                                                                    } else {
                                                                        for (int k = 0; k < staffs.size(); k++) {
                                                                            out.println(staffs.get(i).getName());
                                                                        }
                                                                    }
                                                                %>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=roles.get(i).getId()%>" value="Update" onclick="javascript:updateRole('<%=roles.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Role" onclick="addRole()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Role" onclick="removeRole()"  />
                                                </div>
                                            </div>
                                            <input type="hidden" name="id" value="">    
                                        </div>

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
            $(document).ready(function() {
                $('#dataTables-example').dataTable();
            });
        </script>
    </body>
</html>
