<%@page import="HelperClasses.StoreHelper"%>
<%@page import="EntityManager.RegionalOfficeEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StoreEntity"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>

        <div id="wrapper">

            <jsp:include page="../menu1.jsp" />

            <style>
                label{
                    font-size: 18px;
                }
                input{
                    max-width: 280px;
                }
                select{
                    max-width: 280px;
                }
            </style>

            <div id="page-wrapper">
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Store Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i>  <a href="../A6/facilityManagement.jsp">Facility Management</a>
                                </li>                                                             
                                <li>
                                    <i class="icon icon-home"></i>  <a href="../FacilityManagement_StoreServlet/storeManagement_index">Store Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-edit"></i> Edit Store</a>
                                </li>
                            </ol>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">                           
                            <% StoreHelper storeHelper = (StoreHelper) request.getAttribute("storeHelper"); %>
                            <form class="myForm" action="../FacilityManagement_StoreServlet/editStore_POST">
                                <div class="form-group">
                                    <label for="input_storeName">Store Name</label>
                                    <input type="text" class="form-control" id="input_storeName" name="storeName" value="<%= storeHelper.store.getName()%>" required="true">
                                </div>

                                <div class="form-group">
                                    <label>Regional Office</label>                                    
                                    <select name="regionalOfficeId" class="form-control" required="true">
                                        <option value="<%= storeHelper.regionalOffice.getId()%>"> <%= storeHelper.regionalOffice.getName()%> </option>

                                        <% List<RegionalOfficeEntity> regionalOfficeList = (List<RegionalOfficeEntity>) request.getAttribute("regionalOfficeList");
                                            for (RegionalOfficeEntity ro : regionalOfficeList) {
                                                if (ro.getId() != storeHelper.regionalOffice.getId()) {
                                        %>
                                        <option value="<%= ro.getId()%>"><%= ro.getName()%></option>
                                        <%
                                                }
                                            }
                                        %>

                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="input_address">Address</label>
                                    <input type="text" class="form-control" id="input_address"  name="address" value="<%= storeHelper.store.getAddress()%>" >
                                </div>

                                <div class="form-group">
                                    <label for="input_telephone">Telephone</label>
                                    <input type="text" class="form-control" id="input_telephone"  name="telephone" value="<%= storeHelper.store.getTelephone()%>" >
                                </div>

                                <div class="form-group">
                                    <label for="input_email">Email</label>
                                    <input type="email" class="form-control" id="input_email"  name="email" value="<%= storeHelper.store.getEmail()%>" >
                                </div>
                                
                                <input type="hidden" name="storeId" value="<%= storeHelper.store.getId()%>">
                                
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary" value="submit">
                                </div>
                            </form>

                        </div>
                        <!-- /.col-lg-6 -->
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
        %><script>
            alert("<%= request.getAttribute("alertMessage")%>");
        </script><%
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
