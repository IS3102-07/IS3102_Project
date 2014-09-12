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
            function validateRegistrationForm() {
                var blnIsError = false;
                var strErrMsg = "";

                for (var i = 0; i < registrationForm.elements.length; i++) {
                    var e = registrationForm.elements[i];

                    /*
                    if (e.name == "IdentificationNo") {
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
                    */
                    
                }
                if (blnIsError == true) {
                    window.event.returnValue = false;
                    //document.getElementById('lblMessage').innerText = "The following fields are incorrect:" + strErrMsg;
                } else {
                    window.event.returnValue = true;
                    document.registrationForm.action = "AccountManagement_RegistrationServlet";
                    document.registrationForm.submit();
                }
            }

            function trim(stringToTrim) {
                return stringToTrim.replace(/^\s+|\s+$/g, "");
            }
        </script>

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



            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="featured-box featured-boxes.login" style="height: auto;margin-top: 100px;">
                        <div class="panel-body">
                            <form name="registrationForm">
                                <div class="box-content">
                                    <h3>Register An Account</h3>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-4">
                                                <label>Identification No</label>
                                                <input type="text" name="IdentificationNo" id="IdentificationNo" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Name</label>
                                                <input type="text" value="" name="name" id="name" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>E-mail Address</label>
                                                <input type="text" value="" name="email" id="email" class="form-control input-lg">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-4">
                                                <label>Phone</label>
                                                <input type="text" name="phone" id="phone" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Password</label>
                                                <input type="password" name="password" id="password" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Re-enter Password</label>
                                                <input type="password" name="repassword" id="repassword" class="form-control input-lg">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Address</label>
                                                <input type="text" name="address" id="address" class="form-control input-lg">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="submit" onclick="validateRegistrationForm()" value="Register" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading...">
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
