<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="header.html" />

    <body class="dark">
        <jsp:include page="menu.html" />
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

            <div class="container">

                <div class="row featured-boxes">
                    <div class="col-md-4">
                        <div class="featured-box featured-box-primary">
                            <div class="box-content">
                                <i class="icon-featured icon icon-user"></i>
                                <h4>Common Infrastructure</h4>
                                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="featured-box featured-box-secundary">
                            <div class="box-content">
                                <i class="icon-featured icon icon-book"></i>
                                <h4>Manufacturing Resource Planning</h4>
                                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="featured-box featured-box-tertiary">
                            <div class="box-content">
                                <i class="icon-featured icon icon-home"></i>
                                <h4>Supply Chain Management</h4>
                                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="featured-box featured-box-sixenary">
                            <div class="box-content">
                                <i class="icon-featured icon icon-bar-chart-o"></i>
                                <h4>Analytical<br>CRM</h4>
                                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus. </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="featured-box featured-box-fiveneary">
                            <div class="box-content">
                                <i class="icon-featured icon icon-cogs"></i>
                                <h4>Operational<br>CRM</h4>
                                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus. </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="featured-box featured-box-quartenary">
                            <div class="box-content">
                                <i class="icon-featured icon icon-briefcase"></i>
                                <h4>Corporate <br>Management</h4>
                                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus. </p>
                            </div>
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
