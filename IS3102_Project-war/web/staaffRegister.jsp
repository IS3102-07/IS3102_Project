<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="header.html" />

    <body class="dark">

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
                            <form name="LoginForm">
                                <div class="box-content">
                                    <h4>Register An Account</h4>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-4">
                                                <label>Identification No</label>
                                                <input type="text" value="" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Name</label>
                                                <input type="text" value="" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>E-mail Address</label>
                                                <input type="text" value="" class="form-control input-lg">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-4">
                                                <label>Phone</label>
                                                <input type="text" value="" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Password</label>
                                                <input type="password" value="" class="form-control input-lg">
                                            </div>
                                            <div class="col-md-4">
                                                <label>Re-enter Password</label>
                                                <input type="password" value="" class="form-control input-lg">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Address</label>
                                                <input type="text" value="" class="form-control input-lg">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="submit" onclick="validateForm()" value="Register" class="btn btn-lg btn-primary btn-block" data-loading-text="Loading...">
                                        </div>
                                    </div>
  
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>



        </div>
        <!-- Theme Initializer -->
        <script src="js/theme.plugins.js"></script>
        <script src="js/theme.js"></script>

        <!-- Current Page JS -->
        <script src="vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
        <script src="vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
        <script src="vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
        <script src="js/views/view.home.js"></script>   


    </body>
</html>
