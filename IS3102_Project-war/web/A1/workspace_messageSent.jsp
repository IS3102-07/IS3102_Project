<%@page import="HelperClasses.MessageHelper"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />

    <body>
        <script>

            var checkFlag = 'false';
            function readMessage() {
                document.messageManagement.action = "../WorkspaceMessage_ViewServlet";
                document.messageManagement.submit();
            }
            function deleteMessage() {
                var yes = confirm("Are you sure you want to delete the selected messages?\nThis action can not be undone!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.messageManagement.action = "../WorkspaceMessage_RemoveServlet";
                    document.messageManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function sendMessage() {
                window.event.returnValue = true;
                document.messageManagement.action = "workspace_messageAdd.jsp";
                document.messageManagement.submit();
            }
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
            function viewInbox() {
                document.messageManagement.action = "workspace_messageInbox.jsp";
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Sent Messages</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="workspace.jsp">Workspace</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i> <a href="../WorkspaceMessage_Servlet">Messages</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Sent Messages
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
                                <form name="messageManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox" onclick="checkAll(this)" /></th>
                                                            <th>From</th>
                                                            <th>Date Received</th>
                                                            <th>To</th>
                                                            <th>Open</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<MessageHelper> sent = (List<MessageHelper>) (session.getAttribute("sentMessagesHelpers"));
                                                            if (sent != null) {
                                                                for (int i = 0; i < sent.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="hidden" name="deleteMessageType" value="sentMessage" />
                                                                <input type="checkbox" name="delete" value="<%=sent.get(i).getMessageId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=sent.get(i).getSenderName()%>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                                    String dateString = formatter.format(sent.get(i).getSentDate());
                                                                    out.println(dateString);
                                                                %>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    List<String> receviers = (List<String>) (sent.get(i).getReceiversName());
                                                                    if (receviers.isEmpty()) {
                                                                        out.println("-");
                                                                    } else {
                                                                        for (int k = 0; k < receviers.size(); k++) {
                                                                            out.println(receviers.get(k));
                                                                        }
                                                                    }
                                                                %>
                                                            </td>
                                                            <td>
                                                                <input type="hidden" name="messageID" value="<%=sent.get(i).getMessageId()%>" />
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" value="Read" onclick="readMessage()" />
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
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Delete Selected Message" onclick="deleteMessage()"  /><br/><br/>
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Create Message" onclick="sendMessage()"  />
                                                    <input type="hidden" name="view" value="sentMessages"/>
                                                    <button class="btn btn-primary" onclick="javascript:viewInbox()">View Inbox</button>
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
            }
            );
        </script>
    </body>
</html>
