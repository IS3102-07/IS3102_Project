<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <script>
            function registerValidation() {
                var blnIsError = false;
                var strErrMsg = "";

                for (var i = 0; i < registrationForm.elements.length; i++) {
                    var e = registrationForm.elements[i];

                    if (e.name == "txtPassportNumber") {
                        if (trim(e.value) == "") {
                            strErrMsg = "\n    - Passport Number is empty";
                            document.getElementById('txtPassportNumber').style.background = "yellow";
                            blnIsError = true;
                        } else {
                            document.getElementById('txtPassportNumber').style.background = "white";
                        }
                    }
                    if (e.name == "txtName") {
                        if (trim(e.value) == "") {
                            strErrMsg = "\n    - Name is empty";
                            document.getElementById('txtName').style.background = "yellow";
                            blnIsError = true;
                        } else {
                            document.getElementById('txtName').style.background = "white";
                        }
                    }
                    if (e.name == "txtEmail") {
                        var myRegExp = new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]+$");
                        if (trim(e.value) == "") {
                            strErrMsg += "\n    - E-mail Address is empty";
                            blnIsError = true;
                            document.getElementById('txtEmail').style.background = "yellow";
                        } else if (myRegExp.test(e.value) == false) {
                            strErrMsg += "\n   - Invalid email";
                            blnIsError = true;
                            document.getElementById('txtEmail').style.background = "yellow";
                        } else {
                            document.getElementById('txtEmail').style.background = "white";
                        }
                    }
                    if (e.name == "txtPassword") {
                        if (trim(e.value) == "") {
                            strErrMsg += "\n  - Password is empty";
                            document.getElementById('txtPassword').style.background = "yellow";
                            blnIsError = true;
                        } else {
                            document.getElementById('txtPassword').style.background = "white";
                        }
                    }

                    if (e.name == "txtContactNo") {
                        var myRegExp = new RegExp("^[89]\\d{7}$");
                        if (trim(e.value) == "") {
                            strErrMsg += "\n   - Contact no.";
                            blnIsError = true;
                            document.getElementById('txtContactNo').style.background = "yellow";
                        } else if (myRegExp.test(e.value) == false) {
                            strErrMsg += "\n   - Contact (Digits Only)";
                            blnIsError = true;
                            document.getElementById('txtContactNo').style.background = "yellow";
                        } else {
                            document.getElementById('txtContactNo').style.background = "white";
                        }
                    }

                    if (e.name == "txtAddress") {
                        if (trim(e.value) == "") {
                            strErrMsg += "\n  - Address is empty";
                            document.getElementById('txtAddress').style.background = "yellow";
                            blnIsError = true;
                        } else {
                            document.getElementById('txtAddress').style.background = "white";
                        }
                    }
                }
                if (blnIsError == true) {
                    window.event.returnValue = false;
                    document.getElementById('lblMessage').innerText = "The following fields are incorrect:" + strErrMsg;
                } else {
                    window.event.returnValue = true;
                    document.registrationForm.action = "MemberRegistration";
                    document.registrationForm.submit();
                }
            }

            function trim(stringToTrim) {
                return stringToTrim.replace(/^\s+|\s+$/g, "");
            }
        </script>
        <div class="body">
            <jsp:include page="menu.html" />
            <%
                List<ArrayList> memberDetails = (List<ArrayList>) session.getAttribute("member");
                boolean isMember = false;
                if (memberDetails != null) {
                    isMember = true;
                }
                String errMsg = request.getParameter("errMsg");
                if (errMsg == null || errMsg.equals("")) {
                    errMsg = "";
                }
            %>
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
                <div class="container">

                    <div class="row">
                        <div class="col-md-12">

                            <div class="row featured-boxes login">
                                <div class="col-md-6">
                                    <div class="featured-box featured-box-secundary default info-content">
                                        <div class="box-content">
                                            <h4>I'm a Returning Customer</h4>
                                            <form action="" id="" type="post">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <label>Username or E-mail Address</label>
                                                            <input type="text" value="" class="form-control input-lg">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <a class="pull-right" href="#">(Lost Password?)</a>
                                                            <label>Password</label>
                                                            <input type="password" value="" class="form-control input-lg">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <span class="remember-box checkbox">
                                                            <label for="rememberme">
                                                                <input type="checkbox" id="rememberme" name="rememberme">Remember Me
                                                            </label>
                                                        </span>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <input type="submit" value="Login" class="btn btn-primary pull-right push-bottom" data-loading-text="Loading...">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="featured-box featured-box-secundary default info-content">
                                        <div class="box-content">
                                            <h4>Register An Account</h4>
                                            <form action="" id="" type="post">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <label>E-mail Address</label>
                                                            <input type="text" value="" class="form-control input-lg">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-6">
                                                            <label>Password</label>
                                                            <input type="password" value="" class="form-control input-lg">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>Re-enter Password</label>
                                                            <input type="password" value="" class="form-control input-lg">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input type="submit" value="Register" class="btn btn-primary pull-right push-bottom" data-loading-text="Loading...">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
                <jsp:include page="footer.html" />
            </div>
            <!-- Libs -->
            <script src="vendor/jquery.js"></script>
            <script src="vendor/jquery.appear.js"></script>
            <script src="vendor/jquery.easing.js"></script>
            <script src="vendor/jquery.cookie.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.js"></script>
            <script src="vendor/jquery.validate.js"></script>
            <script src="vendor/jquery.stellar.js"></script>
            <script src="vendor/jquery.knob.js"></script>
            <script src="vendor/jquery.gmap.js"></script>
            <script src="vendor/twitterjs/twitter.js"></script>
            <script src="vendor/isotope/jquery.isotope.js"></script>
            <script src="vendor/owl-carousel/owl.carousel.js"></script>
            <script src="vendor/jflickrfeed/jflickrfeed.js"></script>
            <script src="vendor/magnific-popup/magnific-popup.js"></script>
            <script src="vendor/mediaelement/mediaelement-and-player.js"></script>

            <!-- Current Page JS -->
            <script src="vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
            <script src="vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
            <script src="vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
            <script src="js/views/view.home.js"></script>

            <!-- Custom JS -->
            <script src="js/custom.js"></script>
    </body>
</html>
