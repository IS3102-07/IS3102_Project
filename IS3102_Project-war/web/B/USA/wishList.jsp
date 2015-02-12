<%@page import="EntityManager.WishListEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.Item_CountryEntity"%>
<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="checkCountry.jsp" />
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <script>
            var totalPrice = 0;
            for (var i = 0, n = wishList.getItems().size; i < n; i++) {
                totalPrice += wishList.getItems().get(i).get
            }

            function removeItem() {
                checkboxes = document.getElementsByName('delete');
                var numOfTicks = 0;
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    if (checkboxes[i].checked) {
                        numOfTicks++;
                    }
                }
                if (checkboxes.length == 0 || numOfTicks == 0) {
                    window.event.returnValue = true;
                    document.wishList.action = "../../ECommerce_WishListServlet";
                    document.wishList.submit();
                } else {
                    window.event.returnValue = true;
                    document.wishList.action = "../../ECommerce_RemoveItemFromListServlet";
                    document.wishList.submit();
                }
            }
            function printDiv(divName) {
                var printContents = document.getElementById(divName).innerHTML;
                var originalContents = document.body.innerHTML;

                document.body.innerHTML = printContents;

                window.print();

                document.body.innerHTML = originalContents;
            }
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
            function minus(source) {
                var priceOfProduct = document.getElementById("price" + source).innerHTML;
                var quantity = document.getElementById(source).value;
                if (quantity > 1) {
                    document.getElementById(source).value--;
                    document.getElementById("totalPrice" + source).innerHTML = priceOfProduct * document.getElementById(source).value;
                    totalPrice -= priceOfProduct;
                }
            }
            function plus(source) {
                var priceOfProduct = document.getElementById("price" + source).innerHTML;
                document.getElementById(source).value++;
                document.getElementById("totalPrice" + source).innerHTML = priceOfProduct * document.getElementById(source).value;
                totalPrice += priceOfProduct;
            }

            function finalTotalPrice() {
                checkboxes = document.getElementsById('totalPrice');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }

        </script>

        <div class="body">
            <jsp:include page="menu2.jsp" />


            <div role="main" class="main shop">
                <section class="page-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>Wish List</h2>
                            </div>
                        </div>
                    </div>
                </section> 

                <div class="container" id="printableArea">
                    <hr class="tall">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row featured-boxes">
                                <div class="col-md-12">
                                    <div class="featured-box featured-box-secundary featured-box-cart">
                                        <div class="box-content">
                                            <form method="post" action="" name="wishList">
                                                <jsp:include page="/displayMessageLong.jsp" />
                                                <table cellspacing="0" class="shop_table cart">
                                                    <thead>
                                                        <tr>                                                                
                                                            <th class="product-remove">
                                                                <input type="checkbox" onclick="checkAll(this)" />
                                                            </th>                                                                
                                                            <th class="product-thumbnail">
                                                                Image
                                                            </th>
                                                            <th class="product-name" >
                                                                Product
                                                            </th>

                                                            <th class="product-price">
                                                                Price
                                                            </th>
                                                            <th class="product-quantity">
                                                                Quantity
                                                            </th>
                                                            <th class="product-subtotal">
                                                                Total
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%WishListEntity wishList = (WishListEntity) (session.getAttribute("wishList"));
                                                            List<FurnitureEntity> furnitures = (List<FurnitureEntity>) (session.getAttribute("furnitures"));
                                                            List<RetailProductEntity> retailProducts = (List<RetailProductEntity>) (session.getAttribute("retailProducts"));
                                                            List<Item_CountryEntity> item_countryList = (List<Item_CountryEntity>) (session.getAttribute("item_countryList"));
                                                            try {
                                                                if (wishList != null && wishList.getItems().size() > 0) {
                                                                    for (int i = 0; i < wishList.getItems().size(); i++) {
                                                        %>
                                                        <tr class="cart_table_item">


                                                            <td class="product-remove">
                                                                <input type="checkbox" name="delete" value="<%=wishList.getItems().get(i).getSKU()%>" />
                                                            </td>
                                                            <td class="product-thumbnail">
                                                                <a href="furnitureProductDetails.jsp?sku=<%=wishList.getItems().get(i).getSKU()%>">
                                                                    <%
                                                                        boolean flag = true;
                                                                        for (int j = 0; j < furnitures.size(); j++) {
                                                                            if (furnitures.get(j).getId().equals(wishList.getItems().get(i).getId())) {
                                                                    %>
                                                                    <img width="100" height="100" alt="" class="img-responsive" src="../../..<%=furnitures.get(j).getImageURL()%>">
                                                                    <%
                                                                                flag = false;
                                                                                break;
                                                                            }

                                                                        }

                                                                        if (flag == true) {
                                                                            for (int j = 0; j < retailProducts.size(); j++) {
                                                                                if (retailProducts.get(j).getId().equals(wishList.getItems().get(i).getId())) {

                                                                    %>
                                                                    <img width="100" height="100" alt="" class="img-responsive" src="../../..<%=retailProducts.get(j).getImageURL()%>">
                                                                    <%

                                                                                    break;
                                                                                }

                                                                            }
                                                                        }
                                                                    %></a>
                                                            </td>
                                                            <td class="product-name">
                                                                <a href="furnitureProductDetails.jsp?sku=<%=wishList.getItems().get(i).getSKU()%>"><%=wishList.getItems().get(i).getName()%></a>
                                                            </td>

                                                            <td class="product-price">
                                                                $<span class="amount" id="price<%=wishList.getItems().get(i).getId()%>">
                                                                    <%
                                                                        for (int j = 0; j < item_countryList.size(); j++) {
                                                                            if (item_countryList.get(j).getItem().getId().equals(furnitures.get(i).getId())) {
                                                                    %>
                                                                    <%=item_countryList.get(j).getRetailPrice()%>

                                                                    <%
                                                                                break;
                                                                            }
                                                                        }

                                                                    %>
                                                                </span>
                                                            </td>
                                                            <td class="product-quantity">
                                                                <form enctype="multipart/form-data" method="post" class="cart">
                                                                    <div class="quantity">
                                                                        <input type="button" class="minus" value="-" onclick="minus(<%=wishList.getItems().get(i).getId()%>)">
                                                                        <input type="text" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1" id="<%=wishList.getItems().get(i).getId()%>">
                                                                        <input type="button" class="plus" value="+" onclick="plus(<%=wishList.getItems().get(i).getId()%>)">
                                                                    </div>
                                                                </form>
                                                            </td>
                                                            <td class="product-subtotal">
                                                                $<span class="amount" id="totalPrice<%=wishList.getItems().get(i).getId()%>">
                                                                    <%
                                                                        for (int j = 0; j < item_countryList.size(); j++) {
                                                                            if (item_countryList.get(j).getItem().getId().equals(furnitures.get(i).getId())) {
                                                                    %>
                                                                    <%=item_countryList.get(j).getRetailPrice()%>

                                                                    <%
                                                                                break;
                                                                            }
                                                                        }
                                                                    %>
                                                                </span>
                                                            </td>
                                                        </tr>

                                                        <%                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                System.out.println(ex);
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                                <%if (wishList != null && wishList.getItems().size() > 0) {%>
                                                <div align="left"><a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Remove Item(s)</button></a></div>
                                                <%}%>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>



                            <div class="row featured-boxes">
                                <div class="col-md-12">
                                    <div class="actions-continue">
                                        <input type="submit" value="Print" name="proceed" class="btn btn-lg btn-primary" onclick="printDiv('printableArea')">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>

            </div>

            <div role="dialog" class="modal fade" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4>Alert</h4>
                        </div>
                        <div class="modal-body">
                            <p id="messageBox">The selected item(s) will be removed from your wishlist. Are you sure?</p>
                        </div>
                        <div class="modal-footer">                        
                            <input class="btn btn-primary" name="btnRemove" type="submit" value="Confirm" onclick="removeItem()"  />
                            <a class="btn btn-default" data-dismiss ="modal">Close</a>
                        </div>
                    </div>
                </div>
            </div>


            <jsp:include page="footer.html" />

            <!-- Theme Initializer -->
            <script src="../../js/theme.plugins.js"></script>
            <script src="../../js/theme.js"></script>

            <!-- Current Page JS -->
            <script src="../../vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
            <script src="../../vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
            <script src="../../vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
            <script src="../../js/views/view.home.js"></script>   
        </div>
    </body>
</html>
