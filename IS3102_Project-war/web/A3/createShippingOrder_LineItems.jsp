<%-- 
    Document   : createShippingOrderBasicInfo
    Created on : Sep 11, 2014, 11:12:39 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <div id="wrapper" class="wapper">

            <jsp:include page="../menu2.html" />

            <div class="container" style="background-color: white; height: 80%">

                <h3><b>Shipping Order</b></h3>

                <form id="form" role="form">

                    <br>

                    <ul class="nav nav-tabs" role="tablist">
                        <li><a href="#"><b>Basic Information</b></a></li>                             
                        <li class="active"><a href="#"><b>Line Items</b></a></li>   
                        <li><a href="#"><b>Overview</b></a></li>
                    </ul>

                    <div class="container well" style="min-height: 200px">

                        <table class="table table-bordered" style="width:90%">

                            <thead>
                                <tr>
                                    <th><input type="checkbox" name="" onclick="" title="check all"></th>
                                    <th>Line</th><th>Item Name</th><th>Quantity</th><th>Pack Type</th>
                                </tr>
                            </thead>

                            <tbody>

                            </tbody>                                

                        </table>                        
                        <br>                        
                    </div>

                    <button type="submit" class="btn btn-default">Submit</button>

                </form>

                <hr>

                <form class="form-inline" role="form">

                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active"><a href="#"><b>Line Item Editor</b></a></li>
                    </ul>

                    <div class="container well" style="min-height: 100px">

                        <div class="form-group">
                            <label for="input_item">Item</label>
                            <input type="text" class="form-control" id="input_item" name="item">
                        </div>
                        
                        <div class="form-group">
                            <label for="input_quantity">Quantity</label>
                            <input type="number" class="form-control" id="input_quantity" name="quantity">
                        </div>
                        
                        <div class="form-group">
                            <label for="input_packType">Pack Type</label>
                            <select class="form-control" id="input_packTyep" name="packTyep">
                                <option>Box</option>
                                <option>Bundle</option>
                                <option>...</option>
                            </select>
                        </div>
                        
                        <div class="form-group" style="padding-left: 80px">
                            <button type="submit" class="btn btn-default">Add Line Item</button>                            
                        </div>
                        
                    </div>
                </form>

            </div>
        </div>        
    </body>
</html>