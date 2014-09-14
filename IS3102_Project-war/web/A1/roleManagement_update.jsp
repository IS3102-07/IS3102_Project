<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
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
                                Role Update
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="accountManagement.jsp">Account Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="roleManagement.jsp"> Role Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i>  Role Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <%
                        int id = 0;
                        if (request.getParameter("id") == null) {
                            response.sendRedirect("roleManagement.jsp");
                        } else {
                            id = Integer.parseInt(request.getParameter("id"));
                        }

                        List<RoleEntity> roles = (List<RoleEntity>) (session.getAttribute("roles"));

                        if (roles == null || roles.isEmpty()) {
                            response.sendRedirect("../RoleManagement_RoleServlet");
                        } else {

                            RoleEntity role = new RoleEntity();
                            for (int i = 0; i < roles.size(); i++) {
                                if (roles.get(i).getId() == id) {
                                    role = roles.get(i);
                                }
                            }
                    %>

                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../RoleManagement_UpdateRoleServlet">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" name="name" type="text" value="<%=role.getName()%>" required="true" >
                                </div>
                                <div class="form-group">
                                    <label>Access Level</label>
                                    <input class="form-control" name="accessLevel" type="text" value="<%=role.getAccessLevel()%>">
                                </div>
                                <div class="form-group">
                                    <label>Role</label>
                                    <input class="form-control" required="true" type="text" name="email" disabled/>
                                </div>

                                <div class="form-group">
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="<%=role.getId()%>" name="id">
                                <input type="hidden" value="<%=role.getName()%>" name="roleName">
                                <input type="hidden" value="A1/roleManagement_update.jsp" name="source">
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
