<%@page import="java.util.List"%>
<%@page import="EntityManager.CountryEntity"%>
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
                                Add Supplier
                            </h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="supplierManagement.jsp"> Supplier Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Add Supplier
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <%
                        String errMsg = request.getParameter("errMsg");
                        if (errMsg == null || errMsg.equals("")) {
                            errMsg = "";
                        } else {
                    %>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="alert alert-danger">
                                <%=errMsg%>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <!-- /.warning -->
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="../SupplierManagement_AddSupplierServlet">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" name="name"  type="text" required="true">
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input class="form-control" required="true" type="text" name="phone" >
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input class="form-control" required="true" type="email" name="email">
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="form-control" type="text" required="true" name="address" >
                                </div>
                                <div class="form-group">
                                    <label>Country</label>
                                    <select required="" name="country" class="form-control">
                                        <option></option>
                                        <%
                                            List<CountryEntity> countries = (List<CountryEntity>) (session.getAttribute("countries"));
                                            if (countries != null) {
                                                for (int i = 0; i < countries.size(); i++) {
                                                    out.println("<option value='" + countries.get(i).getId() + "'>" + countries.get(i).getName() + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Add" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="A1/supplierManagement_add.jsp" name="source">
                            </form>
                        </div>
                        <!-- /.row -->

                    </div>
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
