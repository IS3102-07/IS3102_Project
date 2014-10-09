<%@page import="EntityManager.ShoppingListEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <script>
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
                    document.shoppingList.action = "../ECommerce_ShoppingCartServlet";
                    document.shoppingList.submit();
                } else {
                    window.event.returnValue = true;
                    document.shoppingList.action = "../ECommerce_RemoveItemFromListServlet";
                    document.shoppingList.submit();
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
                }
            }
            function plus(source) {
                var priceOfProduct = document.getElementById("price" + source).innerHTML;
                document.getElementById(source).value++;
                document.getElementById("totalPrice" + source).innerHTML = priceOfProduct * document.getElementById(source).value;
            }

            function finalTotalPrice() {

                var finalTotalPrice
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
                                            <form method="post" action="" name="shoppingList">
                                                <%
                                                    String errMsg = request.getParameter("errMsg");
                                                    if (errMsg == null) {
                                                        out.println("Successfully added item to cart.");
                                                    } else if (errMsg != null) {
                                                        if (!errMsg.equals("")) {
                                                            out.println(errMsg);
                                                        }
                                                    } else if (errMsg == "") {
                                                        out.println("Successfully added item to cart.");
                                                    }

                                                %>
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
                                                        <tr class="cart_table_item">

                                                            <%                                                                ShoppingListEntity shoppingList = (ShoppingListEntity) (session.getAttribute("shoppingList"));

                                                                try {
                                                                    if (shoppingList != null) {
                                                                        for (int i = 0; i < shoppingList.getItems().size(); i++) {
                                                            %>
                                                            <td class="product-remove">
                                                                <input type="checkbox" name="delete" value="<%=shoppingList.getItems().get(i).getSKU()%>" />
                                                            </td>
                                                            <td class="product-thumbnail">
                                                                <a href="shop-product-sidebar.html">
                                                                    <img width="100" height="100" alt="" class="img-responsive" src="../img/products/<%=i % 5%>.JPG">
                                                                </a>
                                                            </td>
                                                            <td class="product-name">
                                                                <a href="shop-product-sidebar.html"><%=shoppingList.getItems().get(i).getName()%></a>
                                                            </td>

                                                            <td class="product-price">
                                                                $<span class="amount" id="price<%=shoppingList.getItems().get(i).getId()%>">299</span>
                                                            </td>
                                                            <td class="product-quantity">
                                                                <form enctype="multipart/form-data" method="post" class="cart">
                                                                    <div class="quantity">
                                                                        <input type="button" class="minus" value="-" onclick="minus(<%=shoppingList.getItems().get(i).getId()%>)">
                                                                        <input type="text" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1" id="<%=shoppingList.getItems().get(i).getId()%>">
                                                                        <input type="button" class="plus" value="+" onclick="plus(<%=shoppingList.getItems().get(i).getId()%>)">
                                                                    </div>
                                                                </form>
                                                            </td>
                                                            <td class="product-subtotal">
                                                                $<span class="amount" id="totalPrice<%=shoppingList.getItems().get(i).getId()%>">299</span>

                                                            </td>
                                                        </tr>

                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                System.out.println(ex);
                                                            }
                                                        %>


                                                        <tr>
                                                            <td>
                                                                <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Remove Item</button></a>
                                                            </td>
                                                            <td class="actions" colspan="6">
                                                                <div class="actions-continue">
                                                                    $<span class="amount" id="finalTotalPrice">0</span>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
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
                            <p id="messageBox">Item will be removed. Are you sure?</p>
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
            <script src="../js/theme.plugins.js"></script>
            <script src="../js/theme.js"></script>

            <!-- Current Page JS -->
            <script src="../vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
            <script src="../vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
            <script src="../vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
            <script src="../js/views/view.home.js"></script>   
        </div>
    </body>
</html>
