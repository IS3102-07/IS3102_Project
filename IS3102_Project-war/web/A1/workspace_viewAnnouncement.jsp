<%@page import="EntityManager.RoleEntity"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.AnnouncementEntity"%>
<%@page import="java.util.List"%>
<%
    StaffEntity staffEntity = (StaffEntity) (session.getAttribute("staffEntity"));
    boolean roleCanEditAnnouncement = false;
    if (staffEntity != null) {
        List<RoleEntity> roles = staffEntity.getRoles();
        Long[] approvedRolesID = new Long[]{1L, 2L};
        for (RoleEntity roleEntity : roles) {
            for (Long ID : approvedRolesID) {
                if (roleEntity.getId().equals(ID)) {
                    roleCanEditAnnouncement = true;
                    break;
                }
            }
            if (roleCanEditAnnouncement) {
                break;
            }
        }
    }
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateAnnouncement(id) {
                announcementsManagement.id.value = id;
                document.announcementsManagement.action = "workspace_updateAnnouncement.jsp";
                document.announcementsManagement.submit();
            }
            function removeAnnouncement() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.announcementsManagement.action = "../WorkspaceAnnouncement_DeleteServlet";
                    document.announcementsManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function addAnnouncement() {
                window.event.returnValue = true;
                document.announcementsManagement.action = "workspace_BroadcastAnnouncement.jsp";
                document.announcementsManagement.submit();
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
                            <h1 class="page-header">View Announcements</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-dashboard"></i>  <a href="../Workspace_Servlet">Workspace</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-bell"></i> View Announcements
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
                                <form name="announcementsManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Announcement" onclick="addAnnouncement()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Announcements" onclick="removeAnnouncement()"  />
                                                </div>
                                            </div>
                                            <br>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox" onclick="checkAll(this)" /></th>
                                                            <th>ID</th>
                                                            <th>Sender</th>
                                                            <th>Title</th>
                                                            <th>Message</th>
                                                            <th>Broadcasted Date</th>
                                                                <% if (roleCanEditAnnouncement) {%>
                                                            <th>Expiry Date</th>
                                                            <th>Update</th>
                                                                <%}%>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<AnnouncementEntity> listOfAnnouncements = (List<AnnouncementEntity>) (session.getAttribute("listOfAnnouncements"));
                                                            if (listOfAnnouncements != null) {
                                                                for (int i = 0; i < listOfAnnouncements.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=listOfAnnouncements.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=listOfAnnouncements.get(i).getId()%>
                                                            </td>
                                                            <td>
                                                                <%=listOfAnnouncements.get(i).getSender()%>
                                                            </td>
                                                            <td>
                                                                <%=listOfAnnouncements.get(i).getTitle()%>
                                                            </td>
                                                            <td>
                                                                <%=listOfAnnouncements.get(i).getMessage()%>
                                                            </td>
                                                            <td>      
                                                                <%=listOfAnnouncements.get(i).getBroadcastedDate()%>
                                                            </td>
                                                            <% if (roleCanEditAnnouncement) {%>
                                                            <td>      
                                                                <%=listOfAnnouncements.get(i).getExpiryDate()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=listOfAnnouncements.get(i).getId()%>" value="Update" onclick="javascript:updateAnnouncement('<%=listOfAnnouncements.get(i).getId()%>')"/>
                                                            </td>
                                                            <%}%>
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
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Announcement" onclick="addAnnouncement()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Announcement" onclick="removeAnnouncement()"  />
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
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>
    </body>
</html>
