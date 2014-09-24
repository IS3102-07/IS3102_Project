<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="../header1.html" />

    <body class="dark">

        <%
            String errMsg = request.getParameter("errMsg");
            if (errMsg == null || errMsg.equals("")) {
                errMsg = "";
            } else {
                errMsg +="<br/><br/>";
            }
            String email = request.getParameter("email");
            
        %>
        <div role="main" class="main">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="featured-box featured-boxes.login" style="height: auto;margin-top: 100px;">
                        <div class="panel-body">
                            <form role="form" name="registrationForm" action="../AccountManagement_ResetPasswordServlet" onsubmit="return validatePassword()">
                                <div class="box-content">
                                    <h3>Reset Account Password</h3>
                                    <h10 class="text-info"><%=errMsg%></h10>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Email</label>
                                                <input type="text" name="email" class="form-control input-lg" required="true" value="<%=email%>">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Reset Code</label>
                                                <input type="text" name="resetCode" class="form-control input-lg" required="true">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Password</label>
                                                <input type="password" name="password" class="form-control input-lg" required="true">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Re-Password</label>
                                                <input type="password" name="repassword" class="form-control input-lg" required="true">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="submit" value="Reset Password" class="btn btn-lg btn-primary btn-block">
                                        </div>
                                    </div>
                                    <input type="hidden" value="A1/staffLogin.jsp" name="source">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function validatePassword() {
                var password = document.getElementById("password").value;
                var repassword = document.getElementById("repassword").value;
                var ok = true;
                if (password != repassword) {
                    //alert("Passwords Do not match");
                    document.getElementById("password").style.borderColor = "#E34234";
                    document.getElementById("repassword").style.borderColor = "#E34234";
                    alert("Passwords do not match. Please key again.");
                    ok = false;
                }
                return ok;
            }
        </script>

    </body>
</html>