<%@page import="HelperClasses.MessageHelper"%>
<%@page import="EntityManager.MessageEntity"%>
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
                            <h1 class="page-header">Read Message</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="workspace.jsp">Workspace</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i> <a href="workspace_messageInbox.jsp">Messages</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i> <a href="workspace_messageInbox.jsp">Inbox</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Read Message
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <%

                        try {
                            MessageHelper messageHelper = (MessageHelper) (session.getAttribute("message"));
                    %>
                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../WorkspaceMessage_RemoveServlet">
                                <input type="hidden" name="deleteMessageType" value="inbox" />
                                <input type="hidden" name="messageID" value="<%=messageHelper.getMessageId()%>" />
                                <div class="form-group">
                                    <label>From</label>
                                    <input class="form-control" name="from" type="text" value="<%=messageHelper.getSenderName()%>" disabled/>
                                </div>
                                <div class="form-group">
                                    <label>Sent</label>
                                    <input class="form-control" name="accessLevel" type="text" value="<%=messageHelper.getSentDate()%>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>To</label>
                                    <%
                                        String receiverDisplayString = "";
                                        for (int i = 0; i < messageHelper.getReceiversName().size(); i++) {
                                            receiverDisplayString += messageHelper.getReceiversName().get(i);
                                            receiverDisplayString += " <" + messageHelper.getReceiversEmail().get(i) + ">;";
                                        }
                                    %>
                                    <input class="form-control" required="true" type="text" name="<%=receiverDisplayString%>" disabled/>
                                </div>

                                <div class="form-group">
                                    <input type="submit" value="Back to Inbox" class="btn btn-lg btn-primary btn-block">
                                    <input type="submit" value="Delete" class="btn btn-lg btn-primary btn-block">
                                </div>
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
                    <%
                        }catch (Exception ex) {
                            response.sendRedirect("../RoleManagement_RoleServlet");
                        }%>

                </div>

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
