<%@page import="java.util.List"%>
<%@page import="EntityManager.StoreEntity"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>

        <div class="body">
            <jsp:include page="menu1.html" />

            <div role="main" class="main">
                <section class="page-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>Store Locations</h2>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="container">

                    <div class="row">
                        <div class="col-md-12">
                            
                            <div class="toogle" data-plugin-toggle>
                                <%
                                    List<StoreEntity> stores = (List<StoreEntity>) (session.getAttribute("stores"));

                                    try {
                                        if (stores != null) {
                                            for (int i = 0; i < stores.size(); i++) {
                                %>

                                <section class="toggle">
                                    <label>Name: <%=stores.get(i).getName()%></label>
                                    <div class="toggle-content">
                                        <p>
                                            Address: <%=stores.get(i).getAddress()%><br/>
                                            Telephone: <%=stores.get(i).getTelephone()%><br/>
                                            Email: <%=stores.get(i).getEmail()%><br/>
                                        </p>
                                    </div>
                                </section>

                                <%
                                            }
                                        }
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                %>

                            </div>
                        </div>

                    </div>
                </div>
                <jsp:include page="footer.html" />

                <!-- Theme Initializer -->
                <script src="../js/theme.plugins.js"></script>
                <script src="../js/theme.js"></script>

                <!-- Current Page JS -->
                <script src="../vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
                <script src="../vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
                <script src="../vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
                <script src="../js/views/view.home.js"></script>
            </div>
    </body>
</html>
