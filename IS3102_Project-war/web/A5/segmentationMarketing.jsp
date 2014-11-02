<%@page import="EntityManager.MemberEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="java.text.DecimalFormat"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateStaff(id) {
            staffManagement.id.value = id;
                    document.staffManagement.action = "../StaffManagement_UpdateStaffServlet";
                    document.staffManagement.submit();
            }
            function removeStaff() {
            checkboxes = document.getElementsByName('delete');
                    var numOfTicks = 0;
                    for (var i = 0, n = checkboxes.length; i < n; i++) {
            if (checkboxes[i].checked) {
            numOfTicks++;
            }
            }
            if (checkboxes.length == 0 || numOfTicks == 0) {
            window.event.returnValue = true;
                    document.staffManagement.action = "../StaffManagement_StaffServlet";
                    document.staffManagement.submit();
            } else {
            window.event.returnValue = true;
                    document.staffManagement.action = "../StaffManagement_RemoveStaffServlet";
                    document.staffManagement.submit();
            }
            }
            function addStaff() {
            window.event.returnValue = true;
                    document.staffManagement.action = "staffManagement_add.jsp";
                    document.staffManagement.submit();
            }
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
                            <h1 class="page-header">Segmentation Marketing</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-users"></i> <a href="analytical.jsp">Analytical CRM</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-users"></i> Segmentation Marketing
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
                                            out.println("Register a new staff or remove an existing staff");
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
                                <form name="staffManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <br>
                                            <%
                                                List<MemberEntity> members = (List<MemberEntity>) session.getAttribute("members");
                                            %>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="tabs">
                                                                <ul class="nav nav-tabs">
                                                                    <li class="active">
                                                                        <a href="#Demographics" data-toggle="tab"><i class="icon icon-user"></i> Demographics</a>
                                                                    </li>

                                                                </ul>
                                                                <div class="tab-content">
                                                                    <div id="Demographics" class="tab-pane active">

                                                                        <div class="panel-body">
                                                                            <div class="table-responsive">
                                                                                <div class="col-md-6">
                                                                                    <h4>Age Group</h4>
                                                                                    <div id="ageGroupChart"></div>
                                                                                    <div id="countryChart"></div>

                                                                                </div>
                                                                                <div class="col-md-6">
                                                                                    <h4>Income Group</h4>
                                                                                    <div id="incomeGroupChart"></div>
                                                                                    <div id="marriageChart"></div>
                                                                                </div>

                                                                            </div>
                                                                        </div>
                                                                    </div>


                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    List Customers Income : 
                                                    <select name="cars">
                                                        <option value="volvo">Volvo</option>
                                                        <option value="saab">Saab</option>
                                                        <option value="fiat">Fiat</option>
                                                        <option value="audi">Audi</option>
                                                    </select>
                                                    List Customers Last Purchase : 
                                                    <select name="cars">
                                                        <option value="volvo">Volvo</option>
                                                        <option value="saab">Saab</option>
                                                        <option value="fiat">Fiat</option>
                                                        <option value="audi">Audi</option>
                                                    </select>
                                                    <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" member="grid">
                                                        <table class="table table-bordered" id="dataTables-example">
                                                            <thead>
                                                                <tr>
                                                                    <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                                    <th>Name</th>
                                                                    <th>Age</th>
                                                                    <th>Income</th>
                                                                    <th>Monetary Value</th>
                                                                    <th>Action</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%
                                                                    
                                                                    if (members != null) {
                                                                        for (int i = 0; i < members.size(); i++) {
                                                                            MemberEntity member = members.get(i);
                                                                %>
                                                                <tr>                   
                                                                    <td>
                                                                        <input type="checkbox" name="delete" value="<%=member.getId()%>" />
                                                                    </td>
                                                                    <td>
                                                                        <%=member.getName()%>
                                                                    </td>
                                                                    <td>
                                                                        <%=member.getAge()%>
                                                                    </td>
                                                                    <td>
                                                                        <%=member.getIncome()%>
                                                                    </td>
                                                                    <td>
                                                                        <%=member.getCity()%>
                                                                    </td>

                                                                    <td>
                                                                        
                                                                    </td>
                                                                </tr>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </tbody>
                                                        </table>
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

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
                    $(document).ready(function() {
            $('#dataTables-example').dataTable();
            });
                    new Morris.Bar({
                    // ID of the element in which to draw the chart.
                    element: 'ageGroupChart',
                            // Chart data records -- each entry in this array corresponds to a point on
                            // the chart.

            <%
                Integer cummulativeSpendingAgeGrp1 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp1");
                Integer cummulativeSpendingAgeGrp2 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp2");
                Integer cummulativeSpendingAgeGrp3 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp3");
                Integer cummulativeSpendingAgeGrp4 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp4");

                Integer numOfMembersInAgeGroup1 = (Integer) session.getAttribute("numOfMembersInAgeGroup1");
                Integer numOfMembersInAgeGroup2 = (Integer) session.getAttribute("numOfMembersInAgeGroup2");
                Integer numOfMembersInAgeGroup3 = (Integer) session.getAttribute("numOfMembersInAgeGroup3");
                Integer numOfMembersInAgeGroup4 = (Integer) session.getAttribute("numOfMembersInAgeGroup4");

            %>
                    data: [
                    {y: '18-25', a: <%=cummulativeSpendingAgeGrp1%>, b: <%=numOfMembersInAgeGroup1%>, c: 1},
                    {y: '26-40', a: <%=cummulativeSpendingAgeGrp2%>, b: <%=numOfMembersInAgeGroup2%>, c: 2},
                    {y: '41-55', a: <%=cummulativeSpendingAgeGrp3%>, b: <%=numOfMembersInAgeGroup3%>, c: 3},
                    {y: '56-75', a: <%=cummulativeSpendingAgeGrp4%>, b: <%=numOfMembersInAgeGroup4%>, c: 4}
                    ],
                            xkey: 'y',
                            ykeys: ['a', 'b'],
                            labels: ['Total Revenue', 'Num Of Members']
                    });</script>

        <script>
                    new Morris.Bar({
                    // ID of the element in which to draw the chart.
                    element: 'incomeGroupChart',
                            // Chart data records -- each entry in this array corresponds to a point on
                            // the chart.

            <%
                Integer cummulativeSpendingIncomeGrp1 = (Integer) session.getAttribute("cummulativeSpendingIncomeGrp1");
                Integer cummulativeSpendingIncomeGrp2 = (Integer) session.getAttribute("cummulativeSpendingIncomeGrp2");
                Integer cummulativeSpendingIncomeGrp3 = (Integer) session.getAttribute("cummulativeSpendingIncomeGrp3");
                Integer cummulativeSpendingIncomeGrp4 = (Integer) session.getAttribute("cummulativeSpendingIncomeGrp4");

                Integer numOfMembersInIncomeGroup1 = (Integer) session.getAttribute("numOfMembersInIncomeGroup1");
                Integer numOfMembersInIncomeGroup2 = (Integer) session.getAttribute("numOfMembersInIncomeGroup2");
                Integer numOfMembersInIncomeGroup3 = (Integer) session.getAttribute("numOfMembersInIncomeGroup3");
                Integer numOfMembersInIncomeGroup4 = (Integer) session.getAttribute("numOfMembersInIncomeGroup4");

            %>
                    data: [
                    {y: '30,000', a: <%=cummulativeSpendingIncomeGrp1%>, b: <%=numOfMembersInIncomeGroup1%>, c: 1},
                    {y: '60,000', a: <%=cummulativeSpendingIncomeGrp2%>, b: <%=numOfMembersInIncomeGroup2%>, c: 2},
                    {y: '100,000', a: <%=cummulativeSpendingIncomeGrp3%>, b: <%=numOfMembersInIncomeGroup3%>, c: 3},
                    {y: '250,000', a: <%=cummulativeSpendingIncomeGrp4%>, b: <%=numOfMembersInIncomeGroup4%>, c: 4}
                    ],
                            xkey: 'y',
                            ykeys: ['a', 'b'],
                            labels: ['Total Revenue', 'Num Of Members']
                    });
        </script>
        <script>
                    new Morris.Bar({
                    // ID of the element in which to draw the chart.
                    element: 'countryChart',
                            // Chart data records -- each entry in this array corresponds to a point on
                            // the chart.

            <%
                Integer totalCummulativeSpendingOfCountry1 = (Integer) session.getAttribute("totalCummulativeSpendingOfCountry1");
                Integer totalCummulativeSpendingOfCountry2 = (Integer) session.getAttribute("totalCummulativeSpendingOfCountry2");

                Integer numOfMembersInCountry1 = (Integer) session.getAttribute("numOfMembersInCountry1");
                Integer numOfMembersInCountry2 = (Integer) session.getAttribute("numOfMembersInCountry2");

            %>
                    data: [
                    {y: 'Singapore', a: <%=totalCummulativeSpendingOfCountry1%>, b: <%=numOfMembersInCountry1%>, c: 1},
                    {y: 'Malaysia', a: <%=totalCummulativeSpendingOfCountry2%>, b: <%=numOfMembersInCountry2%>, c: 2},
                    ],
                            xkey: 'y',
                            ykeys: ['a', 'b'],
                            labels: ['Total Revenue', 'Num Of Members']
                    });
        </script>
    </body>
</html>
