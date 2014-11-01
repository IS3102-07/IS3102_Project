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
        <%
            List<PickRequestEntity> pickRequestLinkedList = (List<PickRequestEntity>) (session.getAttribute("pickRequestLinkedList"));
            if (pickRequestLinkedList == null || pickRequestLinkedList.size() == 0) {
        %>
        <meta http-equiv="refresh" content="1">
        <jsp:forward page="../PickerRefreshJob_Servlet" />
        <%
        } else {
        %>

        <%}%>


    </head>
    <body class="dark">


    </body>
</html>
