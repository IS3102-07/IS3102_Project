<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>

        <script>
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }

        </script>
        <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
        <script>

            /*
             Map Settings
             
             Find the Latitude and Longitude of your address:
             - http://universimmedia.pagesperso-orange.fr/geo/loc.htm
             - http://www.findlatitudeandlongitude.com/find-address-from-latitude-and-longitude/
             
             */

            // Map Markers
            var mapMarkers = [{
                    address: "New York, NY 10017",
                    html: "<strong>New York Office</strong><br>New York, NY 10017",
                    icon: {
                        image: "img/pin.png",
                        iconsize: [26, 46],
                        iconanchor: [12, 46]
                    },
                    popup: true
                }];

            // Map Initial Location
            var initLatitude = 40.75198;
            var initLongitude = -73.96978;

            // Map Extended Settings
            var mapSettings = {
                controls: {
                    draggable: true,
                    panControl: true,
                    zoomControl: true,
                    mapTypeControl: true,
                    scaleControl: true,
                    streetViewControl: true,
                    overviewMapControl: true
                },
                scrollwheel: false,
                markers: mapMarkers,
                latitude: initLatitude,
                longitude: initLongitude,
                zoom: 16
            };

            var map = $("#googlemaps").gMap(mapSettings);

            // Map Center At
            var mapCenterAt = function(options, e) {
                e.preventDefault();
                $("#googlemaps").gMap("centerAt", options);
            }

        </script>


        <div class="body">
            <jsp:include page="menu1.html" />


            <div class="body">


                <div role="main" class="main">

                    <section class="page-top">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="breadcrumb">
                                        <li><a href="#">Home</a></li>
                                        <li class="active">Contact Us</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <h2>Contact Us</h2>
                                </div>
                            </div>
                        </div>
                    </section>

                    <!-- Google Maps -->
                    <div id="googlemaps" class="google-map"></div>

                    <div class="container">

                        <div class="row">
                            <div class="col-md-6">

                                <div class="alert alert-success hidden" id="contactSuccess">
                                    <strong>Success!</strong> Your message has been sent to us.
                                </div>

                                <div class="alert alert-danger hidden" id="contactError">
                                    <strong>Error!</strong> There was an error sending your message.
                                </div>

                                <h2 class="short"><strong>Contact</strong> Us</h2>
                                <form id="contactForm" action="php/contact-form.php" method="POST">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-6">
                                                <label>Your name *</label>
                                                <input type="text" value="" data-msg-required="Please enter your name." maxlength="100" class="form-control" name="name" id="name" required>
                                            </div>
                                            <div class="col-md-6">
                                                <label>Your email address *</label>
                                                <input type="email" value="" data-msg-required="Please enter your email address." data-msg-email="Please enter a valid email address." maxlength="100" class="form-control" name="email" id="email" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Subject</label>
                                                <input type="text" value="" data-msg-required="Please enter the subject." maxlength="100" class="form-control" name="subject" id="subject" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Message *</label>
                                                <textarea maxlength="5000" data-msg-required="Please enter your message." rows="10" class="form-control" name="message" id="message" required></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input type="submit" value="Send Message" class="btn btn-primary btn-lg" data-loading-text="Loading...">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-6">

                                <h4 class="push-top">Get in <strong>touch</strong></h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget leo at velit imperdiet varius. In eu ipsum vitae velit congue iaculis vitae at risus.</p>

                                <hr />

                                <h4>The <strong>Office</strong></h4>
                                <ul class="list-unstyled">
                                    <li><i class="fa fa-map-marker"></i> <strong>Address:</strong> 1234 Street Name, City Name, United States</li>
                                    <li><i class="fa fa-phone"></i> <strong>Phone:</strong> (123) 456-7890</li>
                                    <li><i class="fa fa-envelope"></i> <strong>Email:</strong> <a href="mailto:mail@example.com">mail@example.com</a></li>
                                </ul>

                                <hr />

                                <h4>Business <strong>Hours</strong></h4>
                                <ul class="list-unstyled">
                                    <li><i class="fa fa-time"></i> Monday - Friday 9am to 5pm</li>
                                    <li><i class="fa fa-time"></i> Saturday - 9am to 2pm</li>
                                    <li><i class="fa fa-time"></i> Sunday - Closed</li>
                                </ul>

                            </div>

                        </div>

                    </div>

                </div>

            </div>
            <jsp:include page="footer.html" />
        </div>
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
