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
                                Broadcast Announcement
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="workspace.jsp">Workspace</a>
                                </li>
                                <li>
                                    <i class="icon icon-user"></i>  <a href="workspace_viewAnnouncement.jsp">Announcement</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i>Broadcast Announcement
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <jsp:include page="../displayMessage.jsp" />
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="../WorkspaceAnnouncement_AddServlet" onsubmit="">
                                <div class="form-group">
                                    <label>Sender</label>
                                    <input class="form-control" name="sender" type="text" required="true" value="Announcement Messenger">
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <input class="form-control" required="true" type="text" name="title" >
                                </div>
                                <div class="form-group">
                                    <label>Message</label>
                                    <input class="form-control" required="true" type="text" name="message" >
                                </div>
                                <div class="form-group">
                                    <label>Expiry Date</label>
                                    <input class="form-control" required="true" type="date" name="expiryDate" >
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Broadcast Message" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="A1/workspace_viewAnnouncement.jsp" name="source">
                            </form>
                        </div>
                        <!-- /.row -->

                    </div>
                </div>

            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->
    </body>

</html>
