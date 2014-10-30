<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../header2.html" />
<head>
    <title>Picker Login</title>
</head>
<body style="margin-top: 0px;">
    <script>
        function validatePassword() {
            document.LoginForm.action = "../PickerLogin_Servlet";
            document.LoginForm.submit();
        }
    </script>
    <div class="body" >
        <div class="header-container">
            <div class="row" style="background-color : rgb(153, 0, 0);">
                <div class="col-md-6 col-md-offset-3">
                    <img src="../img/logo-label.png">
                </div>
            </div>
        </div>

        <br/>

        <div role="main" class="main">
            <div class="container">
                <jsp:include page="../displayMessageLong.jsp" />

                <form role="form" name="LoginForm"  onsubmit="validatePassword()">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label style="color: white;">E-mail Address</label>
                                <input type="email" name="email" class="form-control input-lg" required="true">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label style="color: white;">Password</label>
                                <input type="password" name="password" class="form-control input-lg" required="true">
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-md-12">
                            <input type="submit" value="Login" class="btn btn-lg btn-primary btn-block">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>