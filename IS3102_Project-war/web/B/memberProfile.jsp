<%@page import="EntityManager.MemberEntity"%>
<%@page import="EntityManager.LoyaltyTierEntity"%>
<%@page import="java.util.List;"%>
<jsp:include page="checkCountry.jsp" />
<html> 
    <jsp:include page="header.html" />
    <body>
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
                                                <h2 class="short">Progress Bar</h2>
                                                <%
                                                    int a = 100;
                                                    out.println(member.getLoyaltyPoints());
                                                    for (int i = 0; i < loyaltyTiers.size(); i++) {


                                                %>
                                                <div class="progress">
                                                    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: <%=a%>%; color:red;" >

                                                        <%=loyaltyTiers.get(i).getAmtOfSpendingRequired()%>
                                                    </div>
                                                </div>
                                                <%
                                                        a = a - 25;
                                                    }
                                                %>
                                            </div>

                                            <div class="col-md-6">
                                                <h2 class="short">Progress Bar</h2>
                                                <%
                                                    out.println(member.getLoyaltyPoints());
                                                    out.println(member.getCummulativeSpending());


                                                %>
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