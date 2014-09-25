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
                                    <h3 class="panel-title"><i class="icon icon-bar-chart-o"></i> Year 2012 - Product Group PGA07 Sales Figure </h3>
                                </div>
                                <div class="panel-body">
                                    <div id="morris-area-chart1"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-green">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="icon icon-bar-chart-o"></i> Year 2013 - Product Group PGA07 Sales Figure</h3>
                                </div>
                                <div class="panel-body">
                                    <div id="morris-area-chart2"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-green">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><i class="icon icon-bar-chart-o"></i> Year 2014 - Product Group PGA07 Sales Figure</h3>
                                </div>
                                <div class="panel-body">
                                    <div id="morris-area-chart3"></div>
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
        <%  List<Integer> saleDate1 = (List<Integer>) request.getAttribute("saleDate1");
            List<Integer> saleDate2 = (List<Integer>) request.getAttribute("saleDate2");
            List<Integer> saleDate3 = (List<Integer>) request.getAttribute("saleDate3");%>
            
        <script>

            Morris.Area({
                element: 'morris-area-chart1',
                data: [
                    {
                        period: '2013-01',
                        PGA07: <%= saleDate1.get(0) %>,
                    }, {
                        period: '2013-02',
                        PGA07: <%= saleDate1.get(1) %>,
                    }, {
                        period: '2013-03',
                        PGA07: <%= saleDate1.get(2) %>,
                    }, {
                        period: '2013-04',
                        PGA07: <%= saleDate1.get(3) %>,
                    }, {
                        period: '2013-05',
                        PGA07: <%= saleDate1.get(4) %>,
                    }, {
                        period: '2013-06',
                        PGA07: <%= saleDate1.get(5) %>,
                    }, {
                        period: '2013-07',
                        PGA07: <%= saleDate1.get(6) %>,
                    }, {
                        period: '2013-08',
                        PGA07: <%= saleDate1.get(7) %>,
                    }, {
                        period: '2013-09',
                        PGA07: <%= saleDate1.get(8) %>,
                    }, {
                        period: '2013-10',
                        PGA07: <%= saleDate1.get(9) %>,
                    }, {
                        period: '2013-11',
                        PGA07: <%= saleDate1.get(10) %>,
                    }, {
                        period: '2013-12',
                        PGA07: <%= saleDate1.get(11) %>,
                    },
                ],
                xkey: 'period',
                ykeys: ['PGA07'],
                labels: ['PGA07'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });
            
            Morris.Area({
                element: 'morris-area-chart2',
                data: [
                    {
                        period: '2013-01',
                        PGA07: <%= saleDate2.get(0) %>,
                    }, {
                        period: '2013-02',
                        PGA07: <%= saleDate2.get(1) %>,
                    }, {
                        period: '2013-03',
                        PGA07: <%= saleDate2.get(2) %>,
                    }, {
                        period: '2013-04',
                        PGA07: <%= saleDate2.get(3) %>,
                    }, {
                        period: '2013-05',
                        PGA07: <%= saleDate2.get(4) %>,
                    }, {
                        period: '2013-06',
                        PGA07: <%= saleDate2.get(5) %>,
                    }, {
                        period: '2013-07',
                        PGA07: <%= saleDate2.get(6) %>,
                    }, {
                        period: '2013-08',
                        PGA07: <%= saleDate2.get(7) %>,
                    }, {
                        period: '2013-09',
                        PGA07: <%= saleDate2.get(8) %>,
                    }, {
                        period: '2013-10',
                        PGA07: <%= saleDate2.get(9) %>,
                    }, {
                        period: '2013-11',
                        PGA07: <%= saleDate2.get(10) %>,
                    }, {
                        period: '2013-12',
                        PGA07: <%= saleDate2.get(11) %>,
                    },
                ],
                xkey: 'period',
                ykeys: ['PGA07'],
                labels: ['PGA07'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });
            
            Morris.Area({
                element: 'morris-area-chart3',
                data: [
                    {
                        period: '2013-01',
                        PGA07: <%= saleDate3.get(0) %>,
                    }, {
                        period: '2013-02',
                        PGA07: <%= saleDate3.get(1) %>,
                    }, {
                        period: '2013-03',
                        PGA07: <%= saleDate3.get(2) %>,
                    }, {
                        period: '2013-04',
                        PGA07: <%= saleDate3.get(3) %>,
                    }, {
                        period: '2013-05',
                        PGA07: <%= saleDate3.get(4) %>,
                    }, {
                        period: '2013-06',
                        PGA07: <%= saleDate3.get(5) %>,
                    }, {
                        period: '2013-07',
                        PGA07: <%= saleDate3.get(6) %>,
                    }, {
                        period: '2013-08',
                        PGA07: <%= saleDate3.get(7) %>,
                    }, {
                        period: '2013-09',
                        PGA07: <%= saleDate3.get(8) %>,
                    }, 
                ],
                xkey: 'period',
                ykeys: ['PGA07'],
                labels: ['PGA07'],
                pointSize: 2,
                hideHover: 'auto',
                resize: true
            });
            
        </script>

    </body>

</html>
