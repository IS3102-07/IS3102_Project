<%@page import="EntityManager.MemberEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="java.text.DecimalFormat"%>
<html lang="en">
    <jsp:include page="../header2.html" />

    <body>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Value Analysis</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-users"></i> <a href="analytical.jsp">Analytical CRM</a>
                                </li>
                                <li>
                                    <i class="icon icon-users"></i> <a href="../Analytical_ValueAnalysisServlet">Value Analysis</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-users"></i> Customer Lifetime Value
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
                                    Customer Lifetime Value
                                </div>
                                <!-- /.panel-heading -->
                                <form name="staffManagement">
                                    <%
                                        List<MemberEntity> members = (List<MemberEntity>) (session.getAttribute("members"));

                                    %>
                                    <!-- /.table-responsive -->
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <h4>Customer Lifetime Value</h4>
                                            <table class="table">
                                                <tr>
                                                    <td>

                                                    </td>
                                                    <td>
                                                        Acquisition Year
                                                    </td>
                                                    <td>
                                                        Second Year
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td>
                                                        Customers
                                                    </td>
                                                    <td>
                                                        <%                                                                Double customerRetentionRate = (Double) session.getAttribute("customerRetentionRate");
                                                            DecimalFormat df = new DecimalFormat("#.00");
                                                            DecimalFormat noDecimal = new DecimalFormat("#");
                                                        %>
                                                        <%=members.size()%>
                                                    </td>
                                                    <td>
                                                        <%=noDecimal.format(customerRetentionRate * members.size())%>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td>
                                                        Retention Rate
                                                    </td>
                                                    <td>
                                                        <p id="retentionRate">
                                                            <% out.print(df.format(customerRetentionRate * 100));%></p>%

                                                    </td>
                                                    <td>
                                                        <%
                                                            Double getRetainedCustomerRetentionRate = (Double) session.getAttribute("getRetainedCustomerRetentionRate");
                                                        %>
                                                        <% out.print(df.format(getRetainedCustomerRetentionRate * 100));%>%
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td>
                                                        Orders per Year
                                                    </td>
                                                    <td>
                                                        <% Double averageOrdersPerAcquiredYear = (Double) session.getAttribute("averageOrdersPerAcquiredYear"); %>
                                                        <% out.print(df.format(averageOrdersPerAcquiredYear));%>
                                                    </td>
                                                    <td>
                                                        <%
                                                            Double averageOrdersPerRetainedMember = (Double) session.getAttribute("averageOrdersPerRetainedMember");
                                                            out.print(df.format(averageOrdersPerRetainedMember));
                                                        %>
                                                    </td>

                                                </tr>

                                                <tr>
                                                    <td>
                                                        Avg Order Price
                                                    </td>
                                                    <td>
                                                        <% Double averageOrderPriceInAcquiredYear = (Double) session.getAttribute("averageOrderPriceInAcquiredYear"); %>
                                                        <% out.print(df.format(averageOrderPriceInAcquiredYear));%>
                                                    </td>
                                                    <td>
                                                        <% Double averageOrderPriceForRetainedMembers = (Double) session.getAttribute("averageOrderPriceForRetainedMembers"); %>
                                                        <% out.print(df.format(averageOrderPriceForRetainedMembers)); %>
                                                    </td>

                                                </tr>

                                                <tr>
                                                    <td>
                                                        Profit Margin
                                                    </td>
                                                    <td>
                                                        <input type="button" class="minus" value="-" onclick="minus()">
                                                        <input type="number" value="20" id="profitMargin"/>%
                                                        <input type="button" class="plus" value="+" onclick="plus()">
                                                    </td>
                                                    <td>

                                                    </td>

                                                </tr>

                                                <tr>
                                                    <td>
                                                        Customer LTV
                                                    </td>
                                                    <td>
                                                        <p id="acquiredYearLTV">
                                                            <%out.print(df.format(averageOrdersPerAcquiredYear * averageOrderPriceInAcquiredYear * 0.2));%>
                                                        </p>
                                                    </td>
                                                    <td>
                                                        testing 321
                                                    </td>

                                                </tr>
                                            </table>
                                        </div>
                                        <div id="products" class="tab-pane">
                                        </div>
                                    </div>
                            </div>
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

<div role="dialog" class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>Alert</h4>
            </div>
            <div class="modal-body">
                <p id="messageBox">Staff will be removed. Are you sure?</p>
            </div>
            <div class="modal-footer">                        
                <input class="btn btn-primary" name="btnRemove" type="submit" value="Confirm" onclick="removeStaff()"  />
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

    function minus() {
        var profitMargin = document.getElementById("profitMargin").value;
        if (profitMargin > 1) {
            document.getElementById("profitMargin").value--;
            var acquiredYearLTV = <%=averageOrdersPerAcquiredYear * averageOrderPriceInAcquiredYear%> * (document.getElementById("profitMargin").value/100);
            document.getElementById("acquiredYearLTV").innerHTML = parseFloat(Math.round(acquiredYearLTV * 100) / 100).toFixed(2);
        }
    }
    function plus() {
        var profitMargin = document.getElementById("profitMargin").value;
        document.getElementById("profitMargin").value++;
        var acquiredYearLTV = <%=averageOrdersPerAcquiredYear * averageOrderPriceInAcquiredYear%> * (document.getElementById("profitMargin").value/100);
        document.getElementById("acquiredYearLTV").innerHTML = parseFloat(Math.round(acquiredYearLTV * 100) / 100).toFixed(2);
    }
</script>
</body>
</html>
