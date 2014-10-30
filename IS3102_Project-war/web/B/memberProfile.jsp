<%@page import="EntityManager.MemberEntity"%>
<%@page import="EntityManager.LoyaltyTierEntity"%>
<%@page import="EntityManager.SalesRecordEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List;"%>
<jsp:include page="checkCountry.jsp" />
<html> 
    <jsp:include page="/B/header.html" />
    <body>
        <script>
            function pdpaWindow() {
                var myWindow = window.open("http://localhost:8080/IS3102_Project-war/B/pdpa.html");
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
                <%%>
                <jsp:include page="/displayMessageLong.jsp" />
                <!-- /.warning -->
                <div class="col-md-12">
                    <%
                        try {
                            MemberEntity member = (MemberEntity) session.getAttribute("member");
                            List<LoyaltyTierEntity> loyaltyTiers = (List<LoyaltyTierEntity>) (session.getAttribute("loyaltyTiers"));
                            LoyaltyTierEntity nextLoyaltyTier = (LoyaltyTierEntity) session.getAttribute("nextLoyaltyTier");
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
                                    <form role="form" action="/IS3102_Project-war/ECommerce_MemberEditProfileServlet">
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
                                                <%int securityQn = 0;
                                                    if (member.getSecurityQuestion() == null) {
                                                        securityQn = 0;
                                                    } else {
                                                        securityQn = member.getSecurityQuestion();
                                                    }%>
                                                <option value="1" <%if (securityQn == 1) {
                                                        out.println("selected");
                                                    }%>>What is your mother's maiden name?</option>
                                                <option value="2" <%if (securityQn == 2) {
                                                        out.println("selected");
                                                    }%>>What is your first pet's name?</option>
                                                <option value="3" <%if (securityQn == 3) {
                                                        out.println("selected");
                                                    }%>>What is your favourite animal?</option>
                                            </select>
                                            <input class="form-control" type="text" required="true" name="securityAnswer" value="<%if (member.getSecurityAnswer() == null) {
                                                    out.println("");
                                                } else {
                                                    out.println(member.getSecurityAnswer());
                                                }%>">
                                        </div>
                                        <div class="form-group">
                                            <input type="checkbox" name="serviceLevelAgreement"> Allow us to use your particulars to serve you better<br/>Checking the box above indicates that you agree to our <a onclick="pdpaWindow()">personal data protection policy.</a>
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
                                <div id="loyaltyProgram" class="tab-pane">
                                    <h4>My Loyalty Rewards</h4>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h2 class="short">Loyalty Tiers</h2>
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
                                                    a = a - (100 / loyaltyTiers.size());
                                                }
                                            %>
                                        </div>

                                        <div class="col-md-6">
                                            <h2 class="short">My Loyalty Points</h2>

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="progress">
                                                        <br/>
                                                        My Points : <%=member.getLoyaltyPoints()%> <br/>      
                                                    </div>
                                                    <%
                                                        Double barPercentage = (nextLoyaltyTier.getAmtOfSpendingRequired() - member.getLoyaltyPoints().doubleValue()) / nextLoyaltyTier.getAmtOfSpendingRequired();
                                                        Double barRemainder = 1 - barPercentage;
                                                    %>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="progress">
                                                                <br/>
                                                                Current Tier : <%=member.getLoyaltyTier().getTier()%> <br/>

                                                                <div class="progress">
                                                                    <div class="progress-bar progress-bar-success" style="width: <%=barRemainder * 100%>%">
                                                                        <span class="sr-only">35% Complete (success)</span>
                                                                        <%=member.getLoyaltyPoints()%>
                                                                    </div>
                                                                    <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: <%=barPercentage * 100%>%">
                                                                        <span class="sr-only">20% Complete (warning)</span>
                                                                        <%=nextLoyaltyTier.getAmtOfSpendingRequired() - member.getLoyaltyPoints().doubleValue()%>
                                                                    </div>
                                                                </div>
                                                                Next Tier : <%=nextLoyaltyTier.getTier()%>
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


                                            <table class="table table-hover">

                                                <thead>
                                                <td>
                                                    Receipt No
                                                </td>
                                                <td>
                                                    Amount Due
                                                </td>
                                                <td>
                                                    Amount Paid
                                                </td>
                                                <td>
                                                    Amount Paid Using Points
                                                </td>
                                                <td>
                                                    Created Date
                                                </td>
                                                <td>
                                                    Loyalty Points Deducted
                                                </td>
                                                <td>
                                                    Items Purchased
                                                </td>
                                                </thead>
                                                <%
                                                    for (int i = 0; i < member.getPurchases().size(); i++) {


                                                %>
                                                <tr>
                                                    <td>
                                                        <%=member.getPurchases().get(i).getReceiptNo()%>
                                                    </td>
                                                    <td>
                                                        <%=member.getPurchases().get(i).getAmountDue()%>
                                                    </td>
                                                    <td>
                                                        <%=member.getPurchases().get(i).getAmountPaid()%>

                                                    </td>
                                                    <td>
                                                        <%=member.getPurchases().get(i).getAmountPaidUsingPoints()%>

                                                    </td>
                                                    <td>
                                                        <%=member.getPurchases().get(i).getCreatedDate()%>

                                                    </td>
                                                    <td>
                                                        <%=member.getPurchases().get(i).getLoyaltyPointsDeducted()%>

                                                    </td>
                                                    <td> 
                                                        <%
                                                            for (int j = 0; j < member.getPurchases().get(i).getItemsPurchased().size(); j++) {
                                                                out.print(member.getPurchases().get(i).getItemsPurchased().get(j).getItem().getName());
                                                            }
                                                        %>

                                                    </td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </table>
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