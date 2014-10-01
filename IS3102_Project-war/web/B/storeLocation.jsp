<%@page import="java.util.List"%>
<%@page import="EntityManager.StoreEntity"%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>

        <div class="body">
            <jsp:include page="menu1.html" />

            <div role="main" class="main">
                <div class="slider-container">
                    <div class="slider" id="revolutionSlider">
                        <ul>
                            <li data-transition="fade" data-slotamount="13" data-masterspeed="300" >

                                <img src="../img/slides/slide-bg.jpg" data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">

                                <div class="tp-caption sft stb visible-lg"
                                     data-x="72"
                                     data-y="180"
                                     data-speed="300"
                                     data-start="1000"
                                     data-easing="easeOutExpo"><img src="../img/slides/slide-title-border.png" alt=""></div>

                                <div class="tp-caption top-label lfl stl"
                                     data-x="132"
                                     data-y="180"
                                     data-speed="300"
                                     data-start="500"
                                     data-easing="easeOutExpo">DO YOU NEED NEW</div>

                                <div class="tp-caption sft stb visible-lg"
                                     data-x="372"
                                     data-y="180"
                                     data-speed="300"
                                     data-start="1000"
                                     data-easing="easeOutExpo"><img src="../img/slides/slide-title-border.png" alt=""></div>

                                <div class="tp-caption main-label sft stb"
                                     data-x="30"
                                     data-y="210"
                                     data-speed="300"
                                     data-start="1500"
                                     data-easing="easeOutExpo">FURNITURE?</div>

                                <div class="tp-caption bottom-label sft stb"
                                     data-x="80"
                                     data-y="280"
                                     data-speed="500"
                                     data-start="2000"
                                     data-easing="easeOutExpo">Check out our options and features.</div>

                                <div class="tp-caption randomrotate"
                                     data-x="800"
                                     data-y="248"
                                     data-speed="500"
                                     data-start="2500"
                                     data-easing="easeOutBack"><img src="../img/slides/slide-concept-2-1.png" alt=""></div>

                                <div class="tp-caption sfb"
                                     data-x="850"
                                     data-y="200"
                                     data-speed="400"
                                     data-start="3000"
                                     data-easing="easeOutBack"><img src="../img/slides/slide-concept-2-2.png" alt=""></div>

                                <div class="tp-caption sfb"
                                     data-x="820"
                                     data-y="170"
                                     data-speed="700"
                                     data-start="3150"
                                     data-easing="easeOutBack"><img src="../img/slides/slide-concept-2-3.png" alt=""></div>

                                <div class="tp-caption sfb"
                                     data-x="770"
                                     data-y="130"
                                     data-speed="1000"
                                     data-start="3250"
                                     data-easing="easeOutBack"><img src="../img/slides/slide-concept-2-4.png" alt=""></div>

                                <div class="tp-caption sfb"
                                     data-x="500"
                                     data-y="80"
                                     data-speed="600"
                                     data-start="3450"
                                     data-easing="easeOutExpo"><img src="../img/slides/slide-concept-2-5.png" alt=""></div>

                                <div class="tp-caption blackboard-text lfb "
                                     data-x="530"
                                     data-y="300"
                                     data-speed="500"
                                     data-start="3450"
                                     data-easing="easeOutExpo" style="font-size: 37px;">Build</div>

                                <div class="tp-caption blackboard-text lfb "
                                     data-x="555"
                                     data-y="350"
                                     data-speed="500"
                                     data-start="3650"
                                     data-easing="easeOutExpo" style="font-size: 47px;">your</div>

                                <div class="tp-caption blackboard-text lfb "
                                     data-x="580"
                                     data-y="400"
                                     data-speed="500"
                                     data-start="3850"
                                     data-easing="easeOutExpo" style="font-size: 32px;">own furniture :)</div>
                            </li>
                            <li data-transition="fade" data-slotamount="5" data-masterspeed="1000" >

                                <img src="img/slides/slide-bg.jpg" data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">

                                <div class="tp-caption fade"
                                     data-x="50"
                                     data-y="100"
                                     data-speed="1500"
                                     data-start="500"
                                     data-easing="easeOutExpo"><img src="../img/slides/slide-concept.png" alt=""></div>

                                <div class="tp-caption blackboard-text fade "
                                     data-x="180"
                                     data-y="180"
                                     data-speed="1500"
                                     data-start="1000"
                                     data-easing="easeOutExpo" style="font-size: 30px;">Prof Tan</div>

                                <div class="tp-caption blackboard-text fade "
                                     data-x="180"
                                     data-y="220"
                                     data-speed="1500"
                                     data-start="1200"
                                     data-easing="easeOutExpo" style="font-size: 40px;">Give me an A+!</div>

                                <div class="tp-caption main-label sft stb"
                                     data-x="580"
                                     data-y="190"
                                     data-speed="300"
                                     data-start="1500"
                                     data-easing="easeOutExpo">Island Furniture</div>

                                <div class="tp-caption bottom-label sft stb"
                                     data-x="580"
                                     data-y="250"
                                     data-speed="500"
                                     data-start="2000"
                                     data-easing="easeOutExpo">IS3102 IT-07.</div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="home-intro">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8">
                                <p>
                                    The fastest way to grow your business with the leader in <em>Furniture.</em>
                                    <span>Check out our options and features included.</span>
                                </p>
                            </div>
                            <div class="col-md-4">
                                <div class="get-started">
                                    <a href="#" class="btn btn-lg btn-primary">Get Started Now!</a>
                                    <div class="learn-more">or <a href="index.html">learn more.</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="container">
                        <div class="row center">
                            <div class="col-md-12">
                                <h2 class="short word-rotator-title">
                                    Porto is
                                    <strong class="inverted">
                                        <span class="word-rotate">
                                            <span class="word-rotate-items">
                                                <span>incredibly</span>
                                                <span>especially</span>
                                                <span>extremely</span>
                                            </span>
                                        </span>
                                    </strong>
                                    beautiful and fully responsive.
                                </h2>
                                <p class="featured lead">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce elementum, nulla vel pellentesque consequat, ante nulla hendrerit arcu, ac tincidunt mauris lacus sed leo. vamus suscipit molestie vestibulum.
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="container">
                        <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading"> <%

                                    String errMsg = request.getParameter("errMsg");
                                    String goodMsg = request.getParameter("goodMsg");
                                    if (errMsg == null && goodMsg == null) {
                                        out.println("Add or remove furniture");
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
                                </div>
                                <!-- /.panel-heading -->
                                <form name="furnitureManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Furniture" onclick="addFurniture()"  />
                                                    <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Remove Furniture</button></a>
                                                </div>
                                            </div>
                                            <br/>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>Name</th>
                                                            <th>Category</th>
                                                            <th>Description</th>
                                                            <th>Image URL</th>
                                                            <th>SKU</th>
                                                            <th>Length</th>
                                                            <th>Width</th>
                                                            <th>Height</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<StoreEntity> stores = (List<StoreEntity>) (session.getAttribute("stores"));

                                                            try {
                                                                if (stores != null) {
                                                                    for (int i = 0; i < stores.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=stores.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=stores.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=stores.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=stores.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=stores.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=stores.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=stores.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=stores.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <%=stores.get(i).getName()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=stores.get(i).getId()%>" value="Update" onclick="javascript:updateFurniture('<%=stores.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                System.out.println(ex);
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Furniture" onclick="addFurniture()"  />
                                                    <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Remove Furniture</button></a>
                                                </div>
                                            </div>
                                            <input type="hidden" name="id" value="">    
                                        </div>

                                    </div>
                                    <!-- /.panel-body -->
                                </form>

                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    </div>
                    
                    

                    <div class="container">

                        <div class="row">
                            <div class="col-md-8">
                                <h2>Our <strong>Features</strong></h2>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-group"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Customer Support</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-file"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">HTML5 / CSS3 / JS</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-google-plus"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">500+ Google Fonts</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-adjust"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Colors</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-film"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Sliders</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-user"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Icons</h4>
                                                <p class="tall">Lorem ipsum dolor sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-bars"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Buttons</h4>
                                                <p class="tall">Lorem ipsum dolor sit, consectetur adip.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-desktop"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Lightbox</h4>
                                                <p class="tall">Lorem sit amet, consectetur adip.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <h2>and more...</h2>

                                <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                                    <i class="icon icon-usd"></i>
                                                    Pricing Tables
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseOne" class="accordion-body collapse in">
                                            <div class="panel-body">
                                                Donec tellus massa, tristique sit amet condim vel, facilisis quis sapien. Praesent id enim sit amet odio vulputate eleifend in in tortor.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                                    <i class="icon icon-comment"></i>
                                                    Contact Forms
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseTwo" class="accordion-body collapse">
                                            <div class="panel-body">
                                                Donec tellus massa, tristique sit amet condimentum vel, facilisis quis sapien.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                                    <i class="icon icon-laptop"></i>
                                                    Portfolio Pages
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseThree" class="accordion-body collapse">
                                            <div class="panel-body">
                                                Donec tellus massa, tristique sit amet condimentum vel, facilisis quis sapien.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <hr class="tall" />

                        <div class="row center">
                            <div class="col-md-12">
                                <h2 class="short word-rotator-title">
                                    We're not the only ones
                                    <strong>
                                        <span class="word-rotate">
                                            <span class="word-rotate-items">
                                                <span>excited</span>
                                                <span>happy</span>
                                            </span>
                                        </span>
                                    </strong>
                                    about Porto Template...
                                </h2>
                                <h4 class="lead tall">5,500 customers in 100 countries use Porto Template. Meet our customers.</h4>
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
