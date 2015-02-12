<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<%@page import="EntityManager.MemberEntity"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <script>
            function validatePassword() {
                var password = document.getElementById("password").value;
                var repassword = document.getElementById("repassword").value;
                var ok = true;
                if ((password != null && repassword != null) || (password != "" && repassword != "")) {
                    if (password != repassword) {
                        document.getElementById("password").style.borderColor = "#E34234";
                        document.getElementById("repassword").style.borderColor = "#E34234";
                        alert("Passwords do not match. Please key again.");
                        ok = false;
                    } else if (password == repassword) {
                        if (password.length < 8) {
                            alert("Passwords too short. At least 8 characters.");
                            ok = false;
                        }
                    }
                } else {
                    return ok;
                }
                return ok;
            }
        </script>
        <jsp:include page="menu2.jsp" />
        <div role="main" class="main">
            <section class="page-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Login / Register</h2>
                        </div>
                    </div>
                </div>
            </section>
            <%
                MemberEntity member = (MemberEntity) (session.getAttribute("memberForgetPassword"));
            %>
            <div class="container">
                <jsp:include page="/displayMessageLong.jsp" />
                <div class="row">
                    <div class="col-md-12">
                        <div class="row featured-boxes login">
                            <div class="col-md-12">
                                <div class="featured-box featured-box-secundary default info-content">
                                    <div class="box-content">
                                        <h4>Forgot Password</h4>
                                        <%
                                            if (member.getSecurityQuestion() != null) {%>
                                        <h5>Security Challenge Question</h5>
                                        <form action="../../ECommerce_SendResetPasswordServlet">
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <label>Security Question</label>
                                                        <%
                                                            if (member.getSecurityQuestion() == 1) {
                                                                out.println("What is your mother's maiden name?");
                                                            } else if (member.getSecurityQuestion() == 2) {
                                                                out.println("What is your first pet's name?");
                                                            } else if (member.getSecurityQuestion() == 3) {
                                                                out.println("What is your favourite animal?");
                                                            }
                                                        %>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <label>Security Answer</label>
                                                        <input type="text" name="securityAnswer" class="form-control input-lg" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input type="submit" value="Submit" class="btn btn-primary pull-right push-bottom">
                                                </div>
                                                <input type="hidden" value="<%=member.getEmail()%>" name="email"/>
                                            </div>
                                        </form>
                                        <%} else {%>
                                        <p>This account password cannot be reset online. Please contact support for assistance.</p>
                                        <%}%>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.html" />
    </body>
</html>
