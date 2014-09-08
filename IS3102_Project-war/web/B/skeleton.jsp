<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>

        <div class="body">
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

                <section class="page-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>Title</h2>
                            </div>
                        </div>
                    </div>
                </section>
                <div class="container">

                    <div class="row">
                        <div class="col-md-12">


                            <!--booooooooooooooooooodddddddddddddyyyyyyyyyy-->

                        </div>
                    </div>

                </div>
                <jsp:include page="footer.html" />
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
