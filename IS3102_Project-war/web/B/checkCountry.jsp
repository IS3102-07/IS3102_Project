<%
    Long countryID = (Long) (session.getAttribute("countryID"));
    if (countryID == null) { %>
<jsp:forward page="selectCountry.jsp" />
<% }%>