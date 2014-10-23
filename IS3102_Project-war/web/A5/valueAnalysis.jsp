<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.MemberEntity"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateMember(id) {
                memberManagement.id.value = id;
                document.memberManagement.action = "../MemberManagement_UpdateMemberServlet";
                document.memberManagement.submit();
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
                    document.memberManagement.action = "../MemberManagement_MemberServlet";
                    document.memberManagement.submit();
                } else {
                    window.event.returnValue = true;
                    document.memberManagement.action = "../MemberManagement_RemoveMemberServlet";
                    document.memberManagement.submit();
                }
            }
            function addMember() {
                window.event.returnValue = true;
                document.memberManagement.action = "memberManagement_add.jsp";
                document.memberManagement.submit();
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
                            <h1 class="page-header">Value Analysis</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-users"></i> <a href="analytical.jsp">Value Analysis</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-users"></i> Value Analysis
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
                                            out.println("Register a new member or remove an existing member");
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
                                <form name="memberManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">

                                            <br>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <h2 class="short">Progress Bar</h2>

                                                    <div class="progress-bars">
                                                        <div class="progress-label">
                                                            <span>HTML/CSS</span>
                                                        </div>
                                                        <div class="progress">
                                                            <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
                                                                100
                                                            </div>
                                                        </div>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-primary" data-appear-progress-animation="70%">
                                                                <span class="progress-bar-tooltip">70%</span>
                                                            </div>
                                                        </div>
                                                        <div class="progress-label">
                                                            <span>Design</span>
                                                        </div>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-primary" data-appear-progress-animation="85%" data-appear-animation-delay="300">
                                                                <span class="progress-bar-tooltip">85%</span>
                                                            </div>
                                                        </div>
                                                        <div class="progress-label">
                                                            <span>WordPress</span>
                                                        </div>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-primary" data-appear-progress-animation="75%" data-appear-animation-delay="600">
                                                                <span class="progress-bar-tooltip">75%</span>
                                                            </div>
                                                        </div>
                                                        <div class="progress-label">
                                                            <span>Photoshop</span>
                                                        </div>
                                                        <div class="progress">
                                                            <div class="progress-bar progress-bar-primary" data-appear-progress-animation="85%" data-appear-animation-delay="900">
                                                                <span class="progress-bar-tooltip">85%</span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <!-- /.table-responsive -->

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


        <div role="dialog" class="modal fade" id="myModal">
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
            $(document).ready(function() {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
