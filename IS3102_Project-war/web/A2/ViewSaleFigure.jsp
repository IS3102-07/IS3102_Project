<%@page import="MRP.SalesAndOperationPlanning.SOP_Helper"%>
<%@page import="EntityManager.StoreEntity"%>
<%@page import="EntityManager.SaleAndOperationPlanEntity"%>
<%@page import="EntityManager.ProductGroupEntity"%>
<%@page import="EntityManager.MonthScheduleEntity"%>
<%@page import="EntityManager.WarehouseEntity"%>
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
                            <h1 class="page-header">
                                Sales And Operation Planning
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-dashboard"></i>  <a href="../SaleAndOperationPlanning_Servlet/sop_index_GET">Sales And Operation Planning</a>
                                </li>   
                                <li>
                                    <i class="icon icon-calendar"></i>  <a href="../SaleAndOperationPlanning_Servlet/sop_schedule_GET">Schedule</a>
                                </li>
                                <li>
                                    <i class="icon icon-list"></i>  <a href="#">Dashboard</a>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <%--
                    <div class="row">                             
                        <div class="col-lg-4">
                            <%  StoreEntity store = (StoreEntity) request.getAttribute("store");%>
                            <h4><b> Store:  </b><%= store.getName()%></h4>
                        </div>                                                
                        <div class="col-lg-4">
                            <% MonthScheduleEntity schedule = (MonthScheduleEntity) request.getAttribute("schedule");%>
                            <h4><b> Period: </b><%= schedule.getYear()%> - <%= schedule.getMonth()%> </h4>
                        </div>                                      
                    </div>
                    <br>
                    --%>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-green">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Area Line Graph Example with Tooltips</h3>
                                </div>
                                <div class="panel-body">
                                    <div id="morris-area-chart"></div>
                                </div>
                            </div>
                        </div>
                    </div>


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
                $('#dataTable1').dataTable();
                $('#dataTable2').dataTable();
            }
            );
        </script>

        <script>

            Morris.Area({
                element: 'morris-area-chart',
                data: [{
                        period: '2010 Q1',
                        iphone: 2666,
                        ipad: null,
                        itouch: 2647
                    }, {
                        period: '2010 Q2',
                        iphone: 2778,
                        ipad: 2294,
                        itouch: 2441
                    }, {
                        period: '2010 Q3',
                        iphone: 4912,
                        ipad: 1969,
                        itouch: 2501
                    }, {
                        period: '2010 Q4',
                        iphone: 3767,
                        ipad: 3597,
                        itouch: 5689
                    }, {
                        period: '2011 Q1',
                        iphone: 6810,
                        ipad: 1914,
                        itouch: 2293
                    }, {
                        period: '2011 Q2',
                        iphone: 5670,
                        ipad: 4293,
                        itouch: 1881
                    }, {
                        period: '2011 Q3',
                        iphone: 4820,
                        ipad: 3795,
                        itouch: 1588
                    }, {
                        period: '2011 Q4',
                        iphone: 15073,
                        ipad: 5967,
                        itouch: 5175
                    }, {
                        period: '2012 Q1',
                        iphone: 10687,
                        ipad: 4460,
                        itouch: 2028
                    }, {
                        period: '2012 Q2',
                        iphone: 8432,
                        ipad: 5713,
                        itouch: 1791
                    }],
                xkey: 'period',
                ykeys: ['iphone', 'ipad', 'itouch'],
                labels: ['iPhone', 'iPad', 'iPod Touch'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });

        </script>

    </body>

</html>
