<%@page import="EntityManager.StorageBinEntity"%>
<%@page import="EntityManager.PickRequestEntity"%>
<%@page import="EntityManager.StaffEntity"%>
<%@ page import="java.io.*,java.util.*" %>
<%
    StaffEntity picker = (StaffEntity) (session.getAttribute("picker"));
    if (picker == null) {
%>
<jsp:forward page="pickerLogin.jsp?errMsg=Session Expired." />
<% }
    PickRequestEntity pickRequest = (PickRequestEntity) (session.getAttribute("pickRequest"));
%>
<html>
    <head>
        <jsp:include page="../header1.html" />
    </head>
    <body class="dark">


        <div role="main" class="main">
            <div class="container">
                <div class="row">
                    <% String queueNo = request.getParameter("queueNo");%>
                    <hr class="tall">

                    <div class="row">
                        <div class="col-md-12">
                            <a href="../PickerCollectedJob_Servlet?pickRequestId=<%=pickRequest.getId()%>">
                                <input type="button" value="<%=queueNo%>"  style="min-height: 250px; font-size: 150px;"  class="btn btn-lg btn-primary btn-block">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
