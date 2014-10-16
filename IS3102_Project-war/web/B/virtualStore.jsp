<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@page import="net.tanesha.recaptcha.ReCaptchaImpl"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <script>
            function validatePassword() {
                var password = document.getElementById("password").value;
                var repassword = document.getElementById("repassword").value;
                var ok = true;
                if ((password != null && repassword != null) || (password != "" && repassword != "")) {
                    if (password != repassword) {
                        //alert("Passwords Do not match");
                        document.getElementById("password").style.borderColor = "#E34234";
                        document.getElementById("repassword").style.borderColor = "#E34234";
                        alert("Passwords do not match. Please key again.");
                        ok = false;
                    } else if (password == repassword) {
                        if (password.length < 8) {
                            alert("Passwords too short. At least 8 characters.");
                            ok = false;
                        }
                    }
                } else {
                    return ok;
                }
                return ok;
            }
        </script>
        <jsp:include page="menu2.jsp" />
        <div role="main" class="main">
            <section class="page-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Login / Register</h2>
                        </div>
                    </div>
                </div>
            </section>
            <div class="container">
                <jsp:include page="../displayMessageLong.jsp" />
                <%
                    String errMsg = request.getParameter("errMsg");
                    String goodMsg = request.getParameter("goodMsg");
                    if (errMsg == null && goodMsg == null) {
                        out.println("");
                    } else if ((errMsg != null) && (goodMsg == null)) {
                        if (!errMsg.equals("")) {
                            out.println(errMsg);
                        }
                    } else if ((errMsg == null && goodMsg != null)) {
                        if (!goodMsg.equals("")) {
                            out.println(goodMsg);
                        }
                    }
                %>
                <div class="row">
                    <div class="col-md-12">
                        <div class="row featured-boxes login">

                            <div class="col-md-12">
                                <div class="featured-box featured-box-secundary default info-content">
                                    <div class="box-content">
                                        <h4>Virtual Store</h4>
                                        <img src="../img/islandFurnitureStoreMap.jpg" alt="Mountain View" style="width:100%;height:100%" usemap="#storemap">

                                        <map name="storemap">
                                            <area shape="rect" coords="0,0,50%,50%" alt="Sun" href="sun.htm">
                                            <area shape="rect" coords="90,58,3" alt="Mercury" href="mercur.htm">
                                            <area shape="rect" coords="124,58,8" alt="Venus" href="venus.htm">
                                        </map>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.html" />

    </body>
</html>
