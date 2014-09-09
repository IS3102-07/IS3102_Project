<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body class="dark">
        <div role="main" class="main">
            <div class="row">
                <div class="col-md-3 col-md-offset-4">
                    <div class="featured-box featured-boxes.login" style="height: auto;margin-top: 90px;">
                        <div class="panel-heading">
                            <i class="icon-4x icon icon-unlock-alt"></i><h6 class="panel-title">Sign In</h6>
                        </div>
                        <div class="panel-body">
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
                                            <label>Password</label>
                                            <input type="password" name="txtPassword" id="txtPassword" class="form-control input-lg">
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
