<%@page import="EntityManager.ManufacturingFacilityEntity"%>
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
            </style>

            <div id="page-wrapper">
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Manufacturing Facility Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i>  <a href="../A6/facilityManagement.jsp">Facility Management</a>
                                </li>                                                             
                                <li>
                                    <i class="icon icon-home"></i>  <a href="../A6/manufacturingFacilityManagement.jsp">Manufacturing Facility Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-edit"></i> Edit Manufacturing Facility
                                </li>
                            </ol>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">                           
                            <% ManufacturingFacilityEntity manufacturingFacility = (ManufacturingFacilityEntity) request.getAttribute("manufacturingFacility");%>
                            <form class="myForm" action="../FacilityManagement_ManufacturingFacilityServlet/editManufacturingFacility_POST">
                                <div class="form-group">
                                    <label for="input_manufacturingFacilityName">Manufacturing Facility Name</label>
                                    <input type="text" class="form-control" id="input_manufacturingFacilityName" name="manufacturingFacilityName" value="<%= manufacturingFacility.getName()%>" required="true">
                                </div>

                                <div class="form-group">
                                    <label for="input_address">Address</label>
                                    <input type="text" class="form-control" id="input_address"  name="address" value="<%= manufacturingFacility.getAddress()%>" >
                                </div>

                                <div class="form-group">
                                    <label for="input_telephone">Telephone</label>
                                    <input type="text" class="form-control" id="input_telephone"  name="telephone" value="<%= manufacturingFacility.getTelephone()%>" >
                                </div>

                                <div class="form-group">
                                    <label for="input_email">Email</label>
                                    <input type="email" class="form-control" id="input_email"  name="email" value="<%= manufacturingFacility.getEmail()%>" >
                                </div>
                                
                                <div class="form-group">
                                    <label for="input_email">Capacity</label>
                                    <input type="number" class="form-control" id="input_email"  name="capacity" value="<%= manufacturingFacility.getCapacity()%>" >
                                </div>
                                <input type="hidden" name="manufacturingFacilityId" value="<%= manufacturingFacility.getId()%>">
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
            alert("<%= request.getAttribute("alertMessage") %>");
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
