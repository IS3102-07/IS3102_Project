<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body class="dark">
        <script>
            function validateForm() {
                var blnIsError = false;

                for (var i = 0; i < LoginForm.elements.length; i++) {
                    var e = LoginForm.elements[i];

                    if (e.name == "email") {
                        if (trim(e.value) == "") {
                            document.getElementById('emailclass').className = "form-group has-error";
                            blnIsError = true;
                        } else {
                            document.getElementById('emailclass').className = "form-group";
                        }
                    }

                    if (e.name == "password") { 
                        if (trim(e.value) == "") {
                            document.getElementById('passwordclass').className = "form-group has-error";
                            blnIsError = true;
                        } else {
                            document.getElementById('passwordclass').className = "form-group";
                        }
                    }

                }
                if (blnIsError == true) {
                    window.event.returnValue = false;
                   // alert("Please try again!");
                } else {
                    window.event.returnValue = true;
                    document.LoginForm.action = "AccountManagement_LoginServlet";
                    document.LoginForm.submit();
                }
            }

            function trim(stringToTrim) {
                return stringToTrim.replace(/^\s+|\s+$/g, "");
            }
        </script>

        <div role="main" class="main">
            <div class="row">
                <div class="col-md-2 col-md-offset-5">
                    <div class="featured-box featured-boxes.login" style="height: auto;margin-top: 100px;">
                        <div class="panel-heading">
                            <i class="icon-4x icon icon-unlock-alt"></i><h6 class="panel-title">Sign In</h6>
                        </div>
                        <div class="panel-body">
                            <form role="form" name="LoginForm">
                                <div class="row">
                                    <div class="form-group" id="emailclass">
                                        <div class="col-md-12">
                                            <label>E-mail Address</label>
                                            <input type="text" name="email" id="email" class="form-control input-lg">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group" id="passwordclass">
                                        <div class="col-md-12">
                                            <label>Password</label>
                                            <input type="password" name="password" id="password" class="form-control input-lg">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="submit" onclick="validateForm()" value="Login" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading...">
                                    </div>
                                </div>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
