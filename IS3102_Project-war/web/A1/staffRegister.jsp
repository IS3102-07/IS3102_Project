<%@page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="../header1.html" />
    <body class="dark">
        <div role="main" class="main">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="featured-box featured-boxes.login" style="height: auto;margin-top: 100px;">
                        <div class="panel-body">
                            <form role="form" name="registrationForm" onsubmit="validatePassword()">
                                <div class="box-content">
                                    <h3>Register An Account</h3>
                                    <jsp:include page="../displayMessageLong.jsp" />
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label>Identification No</label>
                                            <input type="text" name="identificationNo" class="form-control input-lg" required="true">
                                        </div>
                                        <div class="col-md-6">
                                            <label>Name</label>
                                            <input type="text" value="" name="name" class="form-control input-lg" required="true">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <label>E-mail Address</label>
                                            <input type="email" value="" name="email" class="form-control input-lg" required="true">
                                        </div>
                                        <div class="col-md-6">
                                            <label>Phone</label>
                                            <input type="text" name="phone" class="form-control input-lg" required="true">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group" hidden>
                                        <div class="col-md-6">
                                            <label>Password</label>
                                            <input id="password" type="password" name="password" class="form-control input-lg" required="true" value="a">
                                        </div>
                                        <div class="col-md-6">
                                            <label>Re-enter Password</label>
                                            <input id="repassword" type="password" name="repassword" class="form-control input-lg" required="true" value="a">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <label>Address</label>
                                            <input type="text" name="address" class="form-control input-lg" required="true">
                                        </div>
                                    </div>
                                </div>

                                <hr class="tall "/>

                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-6 col-md-offset-3">
                                            <%
                                                ReCaptchaImpl recaptcha = new ReCaptchaImpl();
                                                recaptcha.setIncludeNoscript(false);
                                                recaptcha.setPrivateKey("6LdjyvoSAAAAAHnUl50AJU-edkUqFtPQi9gCqDai");
                                                recaptcha.setPublicKey("6LdjyvoSAAAAAL2m-7sPPZEtz0BNVRb-A_yY0BB_");
                                                recaptcha.setRecaptchaServer("https://www.google.com/recaptcha/api");
                                                out.print(recaptcha.createRecaptchaHtml(null, null));
                                            %>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-6 col-md-offset-3">
                                            <input type="submit" value="Register" class="btn btn-primary pull-left"  style="width:318px;">
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" value="A1/staffLogin.jsp" name="source">
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        function validatePassword() {
            document.registrationForm.action = "../StaffManagement_AddStaffServlet";
            document.registrationForm.submit();
        }
    </script>

</body>
</html>
