<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="../header1.html" />
    <body class="dark">

        <div role="main" class="main">
            <div class="row">

                <div class="header-container">
                    <div class="row" style="background-color : rgb(153, 0, 0);">
                        <div class="col-md-4 col-md-offset-4">  
                            <img class="center-block img-responsive"  src="../img/logo-label.png" style="margin-top: 20px; margin-bottom: 20px;">
                        </div>
                    </div>
                </div>

                <div class="col-md-4 col-md-offset-4">  
                    <div class="featured-box featured-boxes.login" style="height: auto;margin-top: 80px;">
                        <div class="panel-heading"> 
                            <i class="icon-4x icon icon-unlock-alt"  style="margin-top: 10px;"></i><h6 class="panel-title">Sign In</h6>
                        </div>

                        <div class="panel-body">

                            <jsp:include page="../displayMessageLong.jsp" />

                            <form role="form" name="LoginForm" action="../AccountManagement_LoginServlet">

                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <label>E-mail Address</label>
                                            <input type="email" name="email" id="email" class="form-control input-lg" required="true">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <label>Password</label>
                                            <input type="password" name="password" id="password" class="form-control input-lg" required="true">
                                            <a class="pull-right" href="staffForgetPassword.jsp">(Lost Password?)</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="submit" value="Login" class="btn btn-lg btn-primary btn-block">
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
