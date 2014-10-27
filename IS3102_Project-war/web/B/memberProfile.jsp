<%@page import="EntityManager.MemberEntity"%>
<%@page import="EntityManager.LoyaltyTierEntity"%>
<%@page import="EntityManager.SalesRecordEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List;"%>
<jsp:include page="checkCountry.jsp" />
<html> 
    <jsp:include page="header.html" />
    <body>
        <script>
            function pdpaWindow() {
                var myWindow = window.open(": http://localhost:8080/IS3102_Project-war/B/pdpa.jsp");
            }
        </script>
        <jsp:include page="menu2.jsp" />
        <div role="main" class="main">
            <section class="page-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>User Profile</h2>
                        </div>
                    </div>
                </div>
            </section>
            <div class="container">
                <jsp:include page="../displayMessageLong.jsp" />
                <!-- /.warning -->
                <div class="col-md-12">
                    <%
                        try {
                            MemberEntity member = (MemberEntity) session.getAttribute("member");
                            List<LoyaltyTierEntity> loyaltyTiers = (List<LoyaltyTierEntity>) (session.getAttribute("loyaltyTiers"));
                    %>
                    <div class="row">
                        <div class="tabs">
                            <ul class="nav nav-tabs">
                                <li class="active">
                                    <a href="#overview" data-toggle="tab"><i class="icon icon-user"></i> Overview</a>
                                </li>
                                <li>
                                    <a href="#loyaltyProgram" data-toggle="tab">Loyalty Program</a>
                                </li>
                                <li>
                                    <a href="#salesHistory" data-toggle="tab">Sales History</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div id="overview" class="tab-pane active">
                                    <form role="form" action="../ECommerce_MemberEditProfileServlet">
                                        <h4>Personal Information</h4>
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input class="form-control" required="true" name="name" type="text" value="<%if (member.getName() == null) {
                                                    out.println("");
                                                } else {
                                                    out.println(member.getName());
                                                }%>">
                                        </div>
                                        <div class="form-group">
                                            <label>E-mail Address</label>
                                            <input class="form-control" required="true" value="<%=member.getEmail()%>" disabled/>
                                        </div>
                                        <div class="form-group">
                                            <label>Phone</label>
                                            <input class="form-control" required="true" type="text" name="phone" value="<%if (member.getPhone() == null) {
                                                    out.println("");
                                                } else {
                                                    out.println(member.getPhone());
                                                }%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Address</label>
                                            <input class="form-control" type="text" required="true" name="address" value="<%if (member.getAddress() == null) {
                                                    out.println("");
                                                } else {
                                                    out.println(member.getAddress());
                                                }%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Set Challenge Question</label>
                                            <select name="securityQuestion">
                                                <option value="1">What is your mother's maiden name?</option>
                                                <option value="2">What is your first pet's name?</option>
                                                <option value="3">What is your favourite animal?</option>
                                            </select>
                                            <input class="form-control" type="text" required="true" name="securityAnswer" value="<%if (member.getSecurityAnswer() == null) {
                                                    out.println("");
                                                } else {
                                                    out.println(member.getSecurityAnswer());
                                                }%>">
                                        </div>
                                        <hr class="more-spaced "/>
                                        <h4>Change Password</h4>
                                        <div class="form-group">
                                            <label>New Password (leave blank unless setting a new password).<br/>Password to be at least 8 characters long.</label>
                                            <input class="form-control" type="password" name="password" id="password">
                                        </div>
                                        <div class="form-group">
                                            <label>Re-enter New Password</label>
                                            <input class="form-control" type="password"  name="repassword" id="repassword">
                                        </div>
                                        <div class="panel-footer" style="padding-bottom: 0px;">
                                            <div class="row">
                                                <input type="checkbox" name="serviceLevelAgreement">Accept our <a href=#" onclick="pdpaWindow()">service level agreement.</a>
                                            </div>
                                        </div>
                                        <div class="panel-footer" style="padding-bottom: 0px;">
                                            <div class="row">
                                                <div class="form-group">
                                                    <input type="submit" value="Submit" class="btn btn-primary"/>
                                                    <input type="reset" value="Reset" class="btn btn-primary"/>
                                                </div>
                                                <input type="hidden" value="<%=member.getId()%>" name="id">
                                                <input type="hidden" value="<%=member.getEmail()%>" name="email"/>
                                            </div>
                                        </div>


                                    </form>
                                </div>
                                <div id="loyaltyProgram" class="tab-pane" onclick="">
                                    <div class="row">

                                        <div class="row">
                                            <div class="col-md-6">
                                                <h2 class="short">Loyalty Rewards</h2>
                                                <%
                                                    int a = 100;

                                                    for (int i = 0; i < loyaltyTiers.size(); i++) {


                                                %>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="progress">
                                                            <br/>
                                                            Tier : <%=loyaltyTiers.get(i).getTier()%> <br/>
                                                            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: <%=a%>%; color:red; background-image: 'none';
                                                                 background-color: 'red'" >
                                                                <%=loyaltyTiers.get(i).getAmtOfSpendingRequired()%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%
                                                        a = a - 25;
                                                    }
                                                %>
                                            </div>

                                            <div class="col-md-6">
                                                <h2 class="short">Loyalty Rewards</h2>

                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="progress">
                                                            <br/>
                                                            My Points : <%=member.getLoyaltyPoints()%> <br/>                                                            
                                                            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 100%; color:red; background-image: 'none';
                                                                 background-color: 'red'" >
                                                                test
                                                            </div>
                                                        </div>
                                                        <%
                                                            int barPercentage = 0;
                                                            int barRemainder = 0;
                                                            if (member.getLoyaltyPoints() < 5000) {
                                                                barPercentage = member.getLoyaltyPoints() / 5000;
                                                                barRemainder = 100 - barPercentage;
                                                            }
                                                        %>
                                                        <div class="progress">
                                                            My Tier : <%=member.getLoyaltyTier().getTier()%> <br/>
                                                            <div class="progress-bar progress-bar-success" style="width: <%=barPercentage%>%">
                                                                <span class="sr-only">35% Complete (success)</span>
                                                                <%=barPercentage%>
                                                            </div>
                                                            <div class="progress-bar progress-bar-danger" style="width: <%=barRemainder%>%">
                                                                <span class="sr-only">20% Complete (warning)</span>
                                                                <%=barRemainder%>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="salesHistory" class="tab-pane" onclick="">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <h4>My Sales Records</h4>


                                            <div role="main" class="main">

                                                <div class="container">

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
                                                                                                &nbsp;
                                                                                            </th>
                                                                                            <th class="product-thumbnail">
                                                                                                &nbsp;
                                                                                            </th>
                                                                                            <th class="product-name">
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
                                                                                            <td class="product-remove">
                                                                                                <a title="Remove this item" class="remove" href="#">
                                                                                                    <i class="fa fa-times"></i>
                                                                                                </a>
                                                                                            </td>
                                                                                            <td class="product-thumbnail">
                                                                                                <a href="shop-product-sidebar.html">
                                                                                                    <img width="100" height="100" alt="" class="img-responsive" src="img/products/product-1.jpg">
                                                                                                </a>
                                                                                            </td>
                                                                                            <td class="product-name">
                                                                                                <a href="shop-product-sidebar.html">Photo Camera</a>
                                                                                            </td>
                                                                                            <td class="product-price">
                                                                                                <span class="amount">$299</span>
                                                                                            </td>
                                                                                            <td class="product-quantity">
                                                                                                <form enctype="multipart/form-data" method="post" class="cart">
                                                                                                    <div class="quantity">
                                                                                                        <input type="button" class="minus" value="-">
                                                                                                        <input type="text" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1">
                                                                                                        <input type="button" class="plus" value="+">
                                                                                                    </div>
                                                                                                </form>
                                                                                            </td>
                                                                                            <td class="product-subtotal">
                                                                                                <span class="amount">$299</span>
                                                                                            </td>
                                                                                        </tr>


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
                                                        </div>
                                                    </div>

                                                </div>

                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%                        } catch (Exception ex) {
                        response.sendRedirect("index.jsp");
                        ex.printStackTrace();
                    }%>
            </div>
        </div>
    </div>
    <jsp:include page="footer.html" />
</body>
</html>