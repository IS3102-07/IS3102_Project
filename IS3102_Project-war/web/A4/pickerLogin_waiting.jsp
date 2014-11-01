<%@page import="EntityManager.PickRequestEntity"%>
<%@page import="EntityManager.PickerEntity"%>
<%@ page import="java.io.*,java.util.*" %>
<%
    PickerEntity picker = (PickerEntity) (session.getAttribute("picker"));
    if (picker == null) {
%>
<jsp:forward page="pickerLogin.jsp?errMsg=Session Expired." />
<% } %>
<html>
    <head>
        <jsp:include page="../header1.html" />
        <meta http-equiv="refresh" content="3; url=../PickerRefreshJob_Servlet">
    </head>
    <body class="dark">


        <div role="main" class="main">

            <div class="header-container">
                <div class="row" style="background-color : rgb(153, 0, 0); margin-bottom: 50px" >
                    <div class="col-md-4 col-md-offset-4">  
                        <img class="center-block img-responsive"  src="../img/logo-label.png" style="margin-top: 20px; margin-bottom: 20px;">
                    </div>
                </div>
            </div>


            <div class="container">
                <div class="row">
                    <div class="col-md-9" >
                        <h2>No <strong>Jobs</strong> At the moment</h2>

                        <div class="row">
                            <div class="col-md-3">
                                <p class="lead">
                                    No jobs available...
                                </p>
                            </div>
                        </div>
                        <hr class="tall">
                    </div>

                    <div class="col-md-3">
                        <aside class="sidebar">
                            <form action="../PickerLogout_Servlet">
                                <input type="submit" value="Logout" class="btn btn-lg btn-primary btn-block" style="min-height: 150px; font-size: 50px;">
                            </form>
                        </aside>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
