<%@page import="EntityManager.StoreEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="checkCountry.jsp" />
<%
    StoreEntity storeEntity = (StoreEntity) (session.getAttribute("storeEntity"));
    String itemQty = (String) (session.getAttribute("itemQty"));
%>


Store: <%=storeEntity.getName()%><br/>
Status: <%if (Integer.parseInt(itemQty) > 0) {%>Available<%} else {%>Unavailable<%}%>
<br/>
Remaining Qty: <%=itemQty%>


