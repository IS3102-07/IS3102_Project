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
            function validateForm() {
                var blnIsError = false;
                var strErrMsg = "";

                for (var i = 0; i < LoginForm.elements.length; i++) {
                    var e = LoginForm.elements[i];

                    if (e.name == "txtUsername") {
                        if (trim(e.value) == "") {
                            strErrMsg = "\n    - Passport Number is empty";
                            document.getElementById('txtUsername').style.background = "yellow";
                            blnIsError = true;
                        } else {
                            document.getElementById('txtUsername').style.background = "white";
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
                }
                if (blnIsError == true) {
                    window.event.returnValue = false;
                    document.getElementById('lblMessage').innerText = "The following fields are incorrect:" + strErrMsg;
                } else {
                    window.event.returnValue = true;
                    document.LoginForm.action = "LoginServlet";
                    document.LoginForm.submit();
                }
            }

            function trim(stringToTrim) {
                return stringToTrim.replace(/^\s+|\s+$/g, "");
            }
        </script>
        <jsp:include page="menu.html" />
        <%
            List<ArrayList> memberDetails = (List<ArrayList>) session.getAttribute("member");
            if (memberDetails != null) {
                response.sendRedirect("home.jsp");
            } else {
                String errMsg = request.getParameter("errMsg");
                if (errMsg == null || errMsg.equals("")) {
                    errMsg = "IM TESTING ";
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

                                        <form name="LoginForm">
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <label>E-mail Address</label>
                                                        <input type="text" name="txtUsername" id="txtUsername" class="form-control input-lg">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-12">
                                                        <a class="pull-right" href="#">(Lost Password?)</a>
                                                        <label>Password</label>
                                                        <input type="password" name="txtPassword" id="txtPassword" class="form-control input-lg">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input type="submit" onclick="validateForm()" value="Login" class="btn btn-primary pull-right push-bottom" data-loading-text="Loading...">
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
                                        <form name="RegisterForm">
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
                        <div class="col-md-12">
                            <label id="lblMessage">test <%=errMsg%></label>
                        </div>
                    </div>
                </div>
                <%}%>
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
    </body>
</html>
