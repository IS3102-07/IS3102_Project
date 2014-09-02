<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
    <head>

        <!-- Basic -->
        <meta charset="utf-8">
        <title>Island Furniture</title>

        <!-- Web Fonts  -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

        <!-- Libs CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
        <link rel="stylesheet" href="vendor/owl-carousel/owl.carousel.css" media="screen">
        <link rel="stylesheet" href="vendor/owl-carousel/owl.theme.css" media="screen">
        <link rel="stylesheet" href="vendor/magnific-popup/magnific-popup.css" media="screen">

        <!-- Theme CSS -->
        <link rel="stylesheet" href="css/theme.css">
        <link rel="stylesheet" href="css/theme-elements.css">
        <link rel="stylesheet" href="css/theme-animate.css">

        <!-- Current Page Styles -->
        <link rel="stylesheet" href="vendor/rs-plugin/css/settings.css" media="screen">
        <link rel="stylesheet" href="vendor/circle-flip-slideshow/css/component.css" media="screen">

        <!-- Skin CSS -->
        <link rel="stylesheet" href="css/skins/blue.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/custom.css">

        <!-- Responsive CSS -->
        <link rel="stylesheet" href="css/theme-responsive.css" />

        <!-- Head Libs -->
        <script src="vendor/modernizr.js"></script>

        <!--[if IE]>
                <link rel="stylesheet" href="css/ie.css">
        <![endif]-->

        <!--[if lte IE 8]>
                <script src="vendor/respond.js"></script>
        <![endif]-->
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
    </head>
    <body>
        <div class="body">
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
            <header>
                <div class="container">
                    <h1 class="logo">
                        <a href="index.html">
                            <img alt="Porto" src="img/logo.png">
                        </a>
                    </h1>
                </div>
                <div class="navbar-collapse nav-main-collapse collapse">
                    <div class="container">
                        <nav class="nav-main mega-menu">
                            <ul class="nav nav-pills nav-main" id="mainMenu">
                                <li>
                                    <a href="index.jsp">
                                        Home
                                    </a>
                                </li>
                                <%if (isMember) {%>
                                <li>
                                    <a href="register.jsp">Search</a>
                                </li>
                                <li>
                                    <a href="register.jsp">View Bookings</a>
                                </li>
                                <li>
                                    <a href="register.jsp">Shopping Cart</a>
                                </li>
                                <li>
                                    <a href="MemberLogout">Logout</a>
                                </li>
                                <%} else {%>
                                <li class="dropdown active">
                                    <a href="#">Register</a>
                                </li>
                                <%}%>
                            </ul>
                        </nav>
                    </div>
                </div>
            </header>
            <div role="main" class="main">
                <%if (!isMember) {%>
                <div id="content" class="content full">
                    <section class="page-top">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <h2>Registration</h2>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="container">
                        <div class="row">

                            <div class="row featured-boxes login">
                                <div class="col-md-8">
                                    <div class="featured-box featured-box-secundary default info-content">
                                        <div class="box-content">
                                            <h4>Register An Account</h4>
                                            <form name="registrationForm">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-6">
                                                            <label id="lblMessage"><%=errMsg%></label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-6">
                                                            <label>Passport Number</label>
                                                            <input type="text" name="txtPassportNumber" id="txtPassportNumber" class="form-control">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>Name</label>
                                                            <input type="text" name="txtName" id="txtName" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-6">
                                                            <label>Password</label>
                                                            <input type="password"  name="txtPassword" id="txtPassword" class="form-control">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>Postal Address</label>
                                                            <input type="text" name="txtAddress" id="txtAddress"  class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-6">
                                                            <label>Phone Number</label>
                                                            <input type="text"  name="txtContactNo" id="txtContactNo" class="form-control">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>E-mail Address</label>
                                                            <input type="text" name="txtEmail" id="txtEmail" class="form-control">
                                                        </div>
                                                    </div>
                                                </div> 
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input type="submit" onclick="registerValidation()" value="Register" class="btn btn-primary pull-right push-bottom" data-loading-text="Loading...">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%} else {%>
                    <section class="page-top">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <h2>Welcome</h2>&nbsp&nbsp&nbsp<h2><%=memberDetails.get(2)%>!</h2>
                                </div>
                            </div>
                        </div>
                    </section>
                    <%}%>
                </div>
            </div>
            <footer id="footer">
                <div class="container">
                    <div class="row">
                        <div class="footer-ribon">
                            <span>Star Hotel System</span>
                        </div>
                        <div class="col-md-3">
                            <div class="newsletter">
                                <h4>Newsletter</h4>
                                <p>Keep up on our always evolving product features and technology. Enter your e-mail and subscribe to our newsletter.</p>

                                <div class="alert alert-success hidden" id="newsletterSuccess">
                                    <strong>Success!</strong> You've been added to our email list.
                                </div>

                                <div class="alert alert-danger hidden" id="newsletterError"></div>

                                <form id="newsletterForm" action="php/newsletter-subscribe.php" method="POST">
                                    <div class="input-group">
                                        <input class="form-control" placeholder="Email Address" name="email" id="email" type="text">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="submit">Go!</button>
                                        </span>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <h4>Latest Tweet</h4>
                            <div id="tweet" class="twitter" data-account-id="">
                                <p>Please wait...</p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="contact-details">
                                <h4>Contact Us</h4>
                                <ul class="contact">
                                    <li><p><i class="icon icon-map-marker"></i> <strong>Address:</strong> 1234 Street Name, City Name, United States</p></li>
                                    <li><p><i class="icon icon-phone"></i> <strong>Phone:</strong> (123) 456-7890</p></li>
                                    <li><p><i class="icon icon-envelope"></i> <strong>Email:</strong> <a href="mailto:mail@example.com">mail@example.com</a></p></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <h4>Follow Us</h4>
                            <div class="social-icons">
                                <ul class="social-icons">
                                    <li class="facebook"><a href="http://www.facebook.com/" target="_blank" data-placement="bottom" rel="tooltip" title="Facebook">Facebook</a></li>
                                    <li class="twitter"><a href="http://www.twitter.com/" target="_blank" data-placement="bottom" rel="tooltip" title="Twitter">Twitter</a></li>
                                    <li class="linkedin"><a href="http://www.linkedin.com/" target="_blank" data-placement="bottom" rel="tooltip" title="Linkedin">Linkedin</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer-copyright">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-1">
                                <a href="index.html" class="logo">
                                    <img alt="Porto Website Template" class="img-responsive" src="img/logo-footer.png">
                                </a>
                            </div>
                            <div class="col-md-7">
                                <p>© Copyright 2014. All Rights Reserved.</p>
                            </div>
                            <div class="col-md-4">
                                <nav id="sub-menu">
                                    <ul>
                                        <li><a href="page-faq.html">FAQ's</a></li>
                                        <li><a href="sitemap.html">Sitemap</a></li>
                                        <li><a href="contact-us.html">Contact</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>

        <!-- Libs -->
        <script src="vendor/jquery.js"></script>
        <script src="js/plugins.js"></script>
        <script src="vendor/jquery.easing.js"></script>
        <script src="vendor/jquery.appear.js"></script>
        <script src="vendor/jquery.cookie.js"></script>

        <script src="vendor/bootstrap.js"></script>
        <script src="vendor/twitterjs/twitter.js"></script>
        <script src="vendor/rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
        <script src="vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
        <script src="vendor/owl-carousel/owl.carousel.js"></script>
        <script src="vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
        <script src="vendor/magnific-popup/magnific-popup.js"></script>
        <script src="vendor/jquery.validate.js"></script>

        <!-- Current Page Scripts -->
        <script src="js/views/view.home.js"></script>

        <!-- Theme Initializer -->
        <script src="js/theme.js"></script>

        <!-- Custom JS -->
        <script src="js/custom.js"></script>
    </body>
</html>
