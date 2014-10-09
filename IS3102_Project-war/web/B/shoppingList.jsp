<%@page import="EntityManager.ItemEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <script>
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
                document.getElementById(source).value--;

                document.getElementById("totalPrice" + source).innerHTML = priceOfProduct * document.getElementById(source).value;

            }
            function plus(source) {
                var priceOfProduct = document.getElementById("price" + source).innerHTML;
                document.getElementById(source).value++;

                document.getElementById("totalPrice" + source).innerHTML = priceOfProduct * document.getElementById(source).value;

            }

        </script>

        <div class="body">
            <jsp:include page="menu2.jsp" />


            <div role="main" class="main shop">
                <section class="page-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>Shopping List</h2>
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
                                            <form method="post" action="">
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

                                                            <%

                                                                List<ItemEntity> shoppingList = (List<ItemEntity>) (session.getAttribute("shoppingList"));

                                                                try {
                                                                    if (shoppingList != null) {
                                                                        for (int i = 0; i < shoppingList.size(); i++) {
                                                            %>
                                                            <td class="product-remove">
                                                                <input type="checkbox" name="delete" value="" />
                                                            </td>
                                                            <td class="product-thumbnail">
                                                                <a href="shop-product-sidebar.html">
                                                                    <img width="100" height="100" alt="" class="img-responsive" src="../img/products/<%=i % 5%>.JPG">
                                                                </a>
                                                            </td>
                                                            <td class="product-name">
                                                                <a href="shop-product-sidebar.html"><%=shoppingList.get(i).getName()%></a>
                                                            </td>

                                                            <td class="product-price">
                                                                $<span class="amount" id="price<%=shoppingList.get(i).getId()%>">299</span>
                                                            </td>
                                                            <td class="product-quantity">
                                                                <form enctype="multipart/form-data" method="post" class="cart">
                                                                    <div class="quantity">
                                                                        <input type="button" class="minus" value="-" onclick="minus(<%=shoppingList.get(i).getName()%>)">
                                                                        <input type="text" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1" id="<%=shoppingList.get(i).getName()%>">
                                                                        <input type="button" class="plus" value="+" onclick="plus(<%=shoppingList.get(i).getName()%>)">
                                                                    </div>
                                                                </form>
                                                            </td>
                                                            <td class="product-subtotal">
                                                                $<span class="amount" id="totalPrice<%=shoppingList.get(i).getName()%>">299</span>

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
                                                            <td class="actions" colspan="6">
                                                                <div class="actions-continue">
                                                                    <input type="submit" value="Update Cart" name="update_cart" class="btn btn-default">
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

                            <div class="row featured-boxes cart">
                                <div class="col-md-6">
                                    <div class="featured-box featured-box-secundary default">
                                        <div class="box-content">
                                            <h4>Calculate Shipping</h4>
                                            <form action="" id="" method="post">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <label>Country</label>
                                                            <select class="form-control">
                                                                <option value="">Select a country</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-6">
                                                            <label>State</label>
                                                            <input type="text" value="" class="form-control">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>Zip Code</label>
                                                            <input type="text" value="" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input type="submit" value="Update Totals" class="btn btn-default pull-right push-bottom" data-loading-text="Loading...">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="featured-box featured-box-secundary default">
                                        <div class="box-content">
                                            <h4>Cart Totals</h4>
                                            <table cellspacing="0" class="cart-totals">
                                                <tbody>
                                                    <tr class="cart-subtotal">
                                                        <th>
                                                            <strong>Cart Subtotal</strong>
                                                        </th>
                                                        <td>
                                                            <strong><span class="amount">$431</span></strong>
                                                        </td>
                                                    </tr>
                                                    <tr class="shipping">
                                                        <th>
                                                            Shipping
                                                        </th>
                                                        <td>
                                                            Free Shipping<input type="hidden" value="free_shipping" id="shipping_method" name="shipping_method">
                                                        </td>
                                                    </tr>
                                                    <tr class="total">
                                                        <th>
                                                            <strong>Order Total</strong>
                                                        </th>
                                                        <td>
                                                            <strong><span class="amount">$431</span></strong>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
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
