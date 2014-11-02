<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.PickRequestEntity"%>
<%@ page import="java.io.*,java.util.*" %>
<%
    StaffEntity staff = (StaffEntity) (session.getAttribute("staffEntity"));
    if (staff == null) {
%>
<jsp:forward page="../A1/staffLogin.jsp?errMsg=Session Expired." />
<% }%>
<html>
    <head>
        <jsp:include page="../header1.html" />
        <meta http-equiv="refresh" content="3; url=../ReceptionistLastCalled_Servlet">
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
            
            <div class="container" style="font-size: 300px;">
                <%String queueNo = request.getParameter("queueNo");
                    if (queueNo != null) {
                        out.println(queueNo);
                    }
                %>
            </div>
        </div>
    </body>
</html>
