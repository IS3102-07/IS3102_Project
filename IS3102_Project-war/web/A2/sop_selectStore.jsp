<%@page import="EntityManager.StoreEntity"%>
<%@page import="EntityManager.RegionalOfficeEntity"%>
<%@page import="HelperClasses.MessageHelper"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>        
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Sale And Operation Planning</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-dashboard"></i>  <a href="#">Sale And Operation Planning</a>
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
                                            errMsg = "Select one regional office to access";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <div class="panel-body">

                                    <form action="">
                                        
                                        <table class="table table-bordered">
                                            
                                            <thead>
                                                <tr>
                                                    <th></th><th></th><th>Action</th>
                                                </tr>
                                            </thead>
                                            
                                            <tbody>
                                                <tr></tr>
                                            </tbody>
                                            
                                        </table>
                                        
                                        <input type="submit" class="btn btn-primary" value="Access">

                                    </form>

                                </div>                               

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
            }
            );
        </script>
    </body>
</html>
