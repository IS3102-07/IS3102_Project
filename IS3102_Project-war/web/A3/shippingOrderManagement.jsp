<%@page import="EntityManager.AccessRightEntity"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="EntityManager.ShippingOrderEntity"%>
<%@page import="java.util.List"%>

<% List<ShippingOrderEntity> shippingOrders = (List<ShippingOrderEntity>) (session.getAttribute("shippingOrders"));
    if (shippingOrders == null) {
        response.sendRedirect("../ShippingOrderManagement_Servlet");
    } else {
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateSO(id) {
                shippingOrderManagement.id.value = id;
                document.shippingOrderManagement.action = "../ShippingOrderLineItemManagement_Servlet";
                document.shippingOrderManagement.submit();
            }
            function addSO() {
                window.event.returnValue = true;
                document.shippingOrderManagement.action = "shippingOrderManagement_Add.jsp";
                document.shippingOrderManagement.submit();
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <%
                try {
            %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Shipping Order Management</h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-exchange"></i> Shipping Order Management
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
                                            out.println("Add shipping orders");
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
                                <form name="shippingOrderManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary btnCreate" name="btnAdd" type="submit" value="Create Shipping Order" onclick="addSO()"  />
                                                </div>
                                            </div>
                                            <br>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th hidden></th>
                                                            <th>Shipping Order ID</th>
                                                            <th>Shipping Origin</th>
                                                            <th>Shipping Destination</th>
                                                            <th>Expected Receiving Date</th>
                                                            <th>Submitted By</th>
                                                            <th>Status</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                        <%
                                                            List<ShippingOrderEntity> finalListOfSO = new ArrayList<ShippingOrderEntity>();
                                                            StaffEntity staff = (StaffEntity) (session.getAttribute("staffEntity"));
                                                            List<RoleEntity> listOfRoles = staff.getRoles();
                                                            boolean isAdmin = false;
                                                            for (RoleEntity role : listOfRoles) {
                                                                if (role.getName().equals("Administrator") || role.getName().equals("Global Manager")) {
                                                                    isAdmin = true;
                                                                    finalListOfSO = shippingOrders;
                                                                    break;
                                                                }
                                                            }
                                                            boolean isRegional = false;
                                                            if (!isAdmin) {
                                                                for (RoleEntity role : listOfRoles) {
                                                                    if (role.getName().equals("Regional Manager")) {
                                                                        isRegional = true;
                                                                        List<AccessRightEntity> accessList = role.getAccessRightList();
                                                                        for (AccessRightEntity accessRight : accessList) {
                                                                            for (ShippingOrderEntity SO : shippingOrders) {
                                                                                if (accessRight.getRegionalOffice() != null && (accessRight.getRegionalOffice().getId().equals(SO.getOrigin().getRegionalOffice().getId()) || accessRight.getRegionalOffice().getId().equals(SO.getDestination().getRegionalOffice().getId()))) {
                                                                                    if (!finalListOfSO.contains(SO)) {
                                                                                        finalListOfSO.add(SO);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                if (!isRegional) {
                                                        %>
                                                    <script>
                                                        $(".btnCreate").attr('disabled', 'disabled');
                                                    </script>
                                                    <%           for (RoleEntity role : listOfRoles) {
                                                                    if (role.getName().equals("Warehouse Manager")) {
                                                                        List<AccessRightEntity> accessList = role.getAccessRightList();
                                                                        for (AccessRightEntity accessRight : accessList) {
                                                                            for (ShippingOrderEntity SO : shippingOrders) {
                                                                                if (accessRight.getWarehouse() != null && ((accessRight.getWarehouse().getId().equals(SO.getDestination().getId()) && !SO.getStatus().equals("Pending")) || accessRight.getWarehouse().getId().equals(SO.getOrigin().getId()))) {
                                                                                    if (!finalListOfSO.contains(SO)) {
                                                                                        finalListOfSO.add(SO);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (finalListOfSO != null) {
                                                            for (int i = 0; i < finalListOfSO.size(); i++) {
                                                                WarehouseEntity source = finalListOfSO.get(i).getOrigin();
                                                                WarehouseEntity destination = finalListOfSO.get(i).getDestination();
                                                    %>
                                                    <tr>
                                                        <td hidden></td>
                                                        <td>
                                                            <%=finalListOfSO.get(i).getId()%>
                                                        </td>
                                                        <td>
                                                            <%=source.getWarehouseName()%>
                                                        </td>
                                                        <td>
                                                            <%=destination.getWarehouseName()%>
                                                        </td>
                                                        <td>
                                                            <%=finalListOfSO.get(i).getExpectedReceivedDate()%>
                                                        </td>
                                                        <td>
                                                            <%=finalListOfSO.get(i).getSubmittedBy()%>
                                                        </td>
                                                        <td>
                                                            <%=finalListOfSO.get(i).getStatus()%>
                                                        </td>
                                                        <td style="width:200px">
                                                            <input type="button" name="btnEdit" class="btn btn-primary btn-block"  value="View" onclick="javascript:updateSO('<%=finalListOfSO.get(i).getId()%>')"/>
                                                        </td>

                                                    </tr>
                                                    <%
                                                                }
                                                            }
                                                        } catch (Exception ex) {
                                                            response.sendRedirect("../A1/workspace.jsp");
                                                        }
                                                    %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary btnCreate1" name="btnAdd" type="submit" value="Create Shipping Order" onclick="addSO()"  />
                                                </div>
                                            </div>
                                            <script>
                                                $(".btnCreate1").attr('disabled', 'disabled');
                                            </script>
                                            <input type="hidden" name="id" value="">    
                                            <input type="hidden" name="source" value="">   
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
            });
        </script>

    </body>

</html>
<%}%>