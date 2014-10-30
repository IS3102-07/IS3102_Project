<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../header2.html" />
<head>
    <link rel="stylesheet" type="text/css" href="../css/blended_layout.css">
    <title>Picker</title>
</head>
<body>
    <div class="blended_grid">
        <div class="pageHeader">
            <div style=" margin-left: 8%;">
                <img src="../img/logo-label.png">
            </div>
        </div>
        <div class="pageLeftMenu">
            <form role="form" name="LoginForm" action="#">
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
                        <input type="submit" onclick="validateForm()" value="Login" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading...">
                    </div>
                </div>
            </form>
        </div>
        <div class="pageContent">
            3
        </div>
        <div class="pageFooter">
            4
        </div>
    </div>
</body>
</html>