<%@page import="EntityManager.RegionalOfficeEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
 
        <script>
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
                            <h1 class="page-header">
                                Regional Office Management
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i> <a href="../A6/facilityManagement.jsp">Facility Management</a>
                                </li>                             
                                <li>
                                    <i class="icon icon-home"></i> Regional Office Management
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">

                                </div>
                                <!-- /.panel-heading -->

                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <form action="../FacilityManagement_RegionalOfficeServlet/createRegionalOffice_GET">
                                        <div class="row">
                                                <div class="col-md-12">
                                                        <input type="submit" name="submit-btn" value="Add Regional Office" class="btn btn-primary" data-loading-text="Loading...">
                                                        <input type="submit" name="submit-btn" value="Delete Regional Office" class="btn btn-primary" data-loading-text="Loading..."></div>
                                            </div>
                                        <br/>
                                        <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                            
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>Regional Office Name</th>
                                                            <th>Address</th>
                                                            <th>Telephone</th>
                                                            <th>Email Address</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<RegionalOfficeEntity> regionalOfficeList = (List<RegionalOfficeEntity>) request.getAttribute("regionalOfficeList");
                                                            if (regionalOfficeList != null) {
                                                                for (RegionalOfficeEntity regionalOffice : regionalOfficeList) {
                                                        %>
                                                        <tr>
                                                            <td><input type="checkbox" name="delete" value="<%= regionalOffice.getId()%>" /></td>
                                                            <td><%= regionalOffice.getName()%></td>
                                                            <td><%= regionalOffice.getAddress()%></td>
                                                            <td><%= regionalOffice.getTelephone()%></td>
                                                            <td><%= regionalOffice.getEmail()%></td>
                                                            <td><button class="btn btn-primary" name="submit-btn" value="<%= regionalOffice.getId() %>">View</button></td>
                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>

                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input type="submit" name="submit-btn" value="Add Regional Office" class="btn btn-primary" data-loading-text="Loading...">
                                                        <input type="submit" name="submit-btn" value="Delete Regional Office" class="btn btn-primary" data-loading-text="Loading...">
                                                    </div>
                                                </div>
                                            </form>    
                                        </div>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.panel-body -->

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

        <%
            if (request.getAttribute("alertMessage") != null) {
        %>
        <script>
            alert("<%= request.getAttribute("alertMessage")%>");
        </script>
        <%
            }
        %>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
