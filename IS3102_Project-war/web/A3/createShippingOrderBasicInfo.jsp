<%-- 
    Document   : createShippingOrderBasicInfo
    Created on : Sep 11, 2014, 11:12:39 AM
    Author     : Administrator
--%>

<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <div id="wrapper" class="wapper">

            <jsp:include page="../menu1.jsp" />

            <div class="container-fluid" style="background-color: white; height: 80%">
                
                <h3><b>Shipping Order</b></h3>
                
                <form id="form" role="form" action="../Inbound_OutboundServlet/createShippingOrderBasicInfo_POST">

                    <br>

                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active"><a href="#"><b>Basic Information</b></a></li>                             
                        <li><a href="#"><b>Line Items</b></a></li>
                        <li><a href="#"><b>Overview</b></a></li>
                    </ul>                              

                    <div class="container well">
                        
                        <table style="width:100%">
                            <thead>
                                
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label for="input_shippedDate">Shipped Date</label>
                                            <input type="date" class="form-control" id="input_shippedDate" name="shippedDate">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label for="input_expectedReceivedDate">Expected Received Date</label>
                                            <input type="date" class="form-control" id="input_expectedReceivedDate" name="expectedReceivedDate">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label for="input_origin">Origin</label>
                                            <select class="form-control" id="input_origin" name="origin">
                                                <% 
                                                    List<WarehouseEntity> warehouseList = (List<WarehouseEntity>) request.getAttribute("warehouseList");
                                                    for(WarehouseEntity w: warehouseList){
                                                        %>
                                                        <option value="<% w.getWarehouseName(); %>" > <% w.getWarehouseName(); %> </option>
                                                        <%
                                                    }
                                                %>
                                            </select>                        
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label for="input_destination">Destination</label>
                                            <select class="form-control" id="input_destination" name="destination">
                                                <%                                                     
                                                    for(WarehouseEntity w: warehouseList){
                                                        %>
                                                        <option value="<% w.getWarehouseName(); %>" > <% w.getWarehouseName(); %> </option>
                                                        <%
                                                    }
                                                %>
                                                                                                
                                            </select>                        
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        
                        <br>
                        
                        <div>
                            <button type="submit" class="btn btn-default">Save</button>
                        </div>
                        
                    </div>                    
                                                            
                    <br>
                </form>

            </div>

        </div>        
    </body>
</html>