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
            }
        %>
        <div role="main" class="main">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="featured-box featured-boxes.login" style="height: auto;margin-top: 100px;">
                        <div class="panel-body">
                            <form role="form" name="registrationForm" action="../AccountManagement_RegistrationServlet">
                                <div class="box-content">
                                    <h3>Register An Account</h3>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-4">
                                                <label>Identification No</label>
                                                <input type="text" name="identificationNo" id="identificationNo" class="form-control input-lg" required="true">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Name</label>
                                                <input type="text" value="" name="name" id="name" class="form-control input-lg" required="true">
                                            </div>
                                            <div class="col-md-4">
                                                <label>E-mail Address</label>
                                                <input type="email" value="" name="email" id="email" class="form-control input-lg" required="true">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-4">
                                                <label>Phone</label>
                                                <input type="number" name="phone" id="phone" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Password</label>
                                                <input type="password" name="password" id="password" class="form-control input-lg" required="true">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Re-enter Password</label>
                                                <input type="password" name="repassword" id="repassword" class="form-control input-lg" required="true">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Address</label>
                                                <input type="text" name="address" id="address" class="form-control input-lg" required="true">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="submit" value="Register" class="btn btn-lg btn-primary btn-block">
                                        </div>
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
