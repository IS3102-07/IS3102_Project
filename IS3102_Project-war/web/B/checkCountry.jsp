<%
    Long countryID = (Long) (session.getAttribute("countryID"));
    if (countryID == null) {
        response.sendRedirect("B/selectCountry.jsp");
    }
%>