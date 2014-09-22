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

                    <style>
                        select {
                            max-width: 300px;
                        }
                    </style>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <%
                                        String errMsg = request.getParameter("errMsg");
                                        if (errMsg == null || errMsg.equals("")) {
                                            errMsg = "Select regional office first, then select store";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <div class="panel-body">

                                    <form action="../SaleAndOperationPlanning_Servlet/sop_index_Post">
                                        <div class="form-group">
                                            <label for="select_regionalOffice">Regional Office</label>
                                            <select id="select_regionalOffice" class="form-control" name="regionalOffice" onchange="getStore()">
                                                <option>South China Regional Office</option>
                                                <%
                                                    List<RegionalOfficeEntity> regionalOfficeList = (List<RegionalOfficeEntity>) request.getAttribute("regionalOfficeList");
                                                    for (RegionalOfficeEntity r : regionalOfficeList) {
                                                %>
                                                <option value="<%= r.getId()%>"><%= r.getName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>                                                 
                                        </div>
                                        <div class="form-group">
                                            <label for="select_store">Store</label>
                                            <select id="select_store" class="form-control" name="store">
                                                <option>Tangling Store</option>
                                                <%
                                                    
                                                %>
                                            </select>
                                        </div>
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

        <script>
            function getStore() {
                var regionalOffice = $("#select_regionalOffice").find('option:selected').text();

                var xmlhttp = new XMLHttpRequest();

                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET", "url?regionalOffice=" + regionalOffice, true);
                xmlhttp.send();
            }
        </script>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            }
            );
        </script>
    </body>
</html>
