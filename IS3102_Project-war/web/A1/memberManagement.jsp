<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.MemberEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateMember(id) {
                membersManagement.id.value = id;
                document.membersManagement.action = "memberManagement_update.jsp";
                document.membersManagement.submit();
            }
            function removeMember() {
                checkboxes = document.getElementsByName('delete');
                var numOfTicks = 0;
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    if (checkboxes[i].checked) {
                        numOfTicks++;
                    }
                }
                if (checkboxes.length == 0 || numOfTicks == 0) {
                    window.event.returnValue = true;
                    document.membersManagement.action = "../MemberManagement_MemberServlet";
                    document.membersManagement.submit();
                } else {
                    window.event.returnValue = true;
                    document.membersManagement.action = "../MemberManagement_RemoveMemberServlet";
                    document.membersManagement.submit();
                }
            }
            function addMember() {
                window.event.returnValue = true;
                document.membersManagement.action = "memberManagement_add.jsp";
                document.membersManagement.submit();
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
                            <h1 class="page-header">Members Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-users"></i> <a href="accountManagement.jsp">Account Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-users"></i> Member Management
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
                                        String goodMsg = request.getParameter("goodMsg");
                                        if (errMsg == null && goodMsg == null) {
                                            out.println("Add new members or remove existing members");
                                        } else if ((errMsg != null) && (goodMsg == null)) {
                                            if (!errMsg.equals("")) {
                                                out.println(errMsg);
                                            }
                                        } else if ((errMsg == null && goodMsg != null)) {
                                            if (!goodMsg.equals("")) {
                                                out.println(goodMsg);
                                            }
                                        }
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="membersManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">                                          
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Member" onclick="addMember()"  />
                                                    <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Remove Member</button></a>
                                                </div>
                                            </div>
                                            <br>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" member="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox" onclick="checkAll(this)" /></th>
                                                            <th>Name</th>
                                                            <th>Access Level</th>
                                                            <th>Staff</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<MemberEntity> members = (List<MemberEntity>) (session.getAttribute("members"));
                                                            if (members != null) {
                                                                for (MemberEntity member : members) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=member.getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=member.getName()%>
                                                            </td>
                                                            <td>
                                                                <%=member.getAddress()%>
                                                            </td>
                                                            <td>
                                                                <%=member.getEmail()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=member.getId()%>" value="Update" onclick="javascript:updateMember('<%=member.getId()%>')"/>
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
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Member" onclick="addMember()"  />
                                                    <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Remove Member</button></a>
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
        <div member="dialog" class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Alert</h4>
                    </div>
                    <div class="modal-body">
                        <p id="messageBox">Member will be removed. Are you sure?</p>
                    </div>
                    <div class="modal-footer">                        
                        <input class="btn btn-primary" name="btnRemove" type="submit" value="Confirm" onclick="removeMember()"  />
                        <a class="btn btn-default" data-dismiss ="modal">Close</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>
    </body>
</html>
