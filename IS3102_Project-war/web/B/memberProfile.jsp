<%@page import="EntityManager.MemberEntity"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
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

                <div class="row">
                    <div class="col-md-12">
                        <%
                            try {
                                MemberEntity member = (MemberEntity) session.getAttribute("member");

                        %>
                        <div class="tab-content">
                            <div id="overview" class="tab-pane active">
                                <form role="form" action="../ECommerce_MemberEditProfileServlet">
                                    <h4>Personal Information</h4>
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input class="form-control" required="true" name="name" type="text" value="<%if (member.getName() == null){out.println("");} else { out.println(member.getName()); }%>">
                                    </div>
                                    <div class="form-group">
                                        <label>E-mail Address</label>
                                        <input class="form-control" required="true" value="<%=member.getEmail()%>" disabled/>
                                    </div>
                                    <div class="form-group">
                                        <label>Phone</label>
                                        <input class="form-control" required="true" type="text" name="phone" value="<%if (member.getPhone() == null){out.println("");} else { out.println(member.getPhone()); }%>">
                                    </div>
                                    <div class="form-group">
                                        <label>Address</label>
                                        <input class="form-control" type="text" required="true" name="address" value="<%if (member.getAddress() == null){out.println("");} else { out.println(member.getAddress()); }%>">
                                    </div>
                                    <div class="form-group">
                                        <label>Set Challenge Question</label>
                                        <select name="securityQuestion">
                                            <option value="1">What is your mother's maiden name?</option>
                                            <option value="2">What is your first pet's name?</option>
                                            <option value="3">What is your favourite animal?</option>
                                        </select>
                                        <input class="form-control" type="text" required="true" name="securityAnswer" value="<%if (member.getSecurityAnswer() == null){out.println("");} else { out.println(member.getSecurityAnswer()); }%>">
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
                            <div id="recent" class="tab-pane">
                                <p>Recent</p>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitat.</p>
                            </div>
                        </div>
                        <%
                            } catch (Exception ex) {
                                response.sendRedirect("index.jsp");
                                ex.printStackTrace();
                            }%>
                    </div>
                </div>

            </div>
        </div>
        <jsp:include page="footer.html" />
    </body>
</html>