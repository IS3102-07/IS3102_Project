<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]>			<html class="ie ie8"> <![endif]-->
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	
<jsp:include page="checkCountry.jsp" />
<%String URLprefix = (String) session.getAttribute("URLprefix");%>
<html> <!--<![endif]-->
    <jsp:include page="/B/header.html" />
    <body>

        <div class="body">
            <jsp:include page="menu2.jsp" />

            <div role="main" class="main">
                <div class="slider-container">
                    <div class="slider" id="revolutionSlider">
                        <ul>
                            <li data-transition="fade" data-slotamount="13" data-masterspeed="300" >

                                <img src="/IS3102_Project-war/img/slides/slide-bg.jpg" data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">

                                <div class="tp-caption sft stb visible-lg"
                                     data-x="72"
                                     data-y="180"
                                     data-speed="300"
                                     data-start="1000"
                                     data-easing="easeOutExpo"><img src="/IS3102_Project-war/img/slides/slide-title-border.png" alt=""></div>

                                <div class="tp-caption top-label lfl stl"
                                     data-x="132"
                                     data-y="180"
                                     data-speed="300"
                                     data-start="500"
                                     data-easing="easeOutExpo">你需要新</div>

                                <div class="tp-caption sft stb visible-lg"
                                     data-x="372"
                                     data-y="180"
                                     data-speed="300"
                                     data-start="1000"
                                     data-easing="easeOutExpo"><img src="/IS3102_Project-war/img/slides/slide-title-border.png" alt=""></div>

                                <div class="tp-caption main-label sft stb"
                                     data-x="30"
                                     data-y="210"
                                     data-speed="300"
                                     data-start="1500"
                                     data-easing="easeOutExpo">家具?</div>

                                <div class="tp-caption bottom-label sft stb"
                                     data-x="80"
                                     data-y="280"
                                     data-speed="500"
                                     data-start="2000"
                                     data-easing="easeOutExpo">看看我们的选项和功能。</div>

                                <div class="tp-caption randomrotate"
                                     data-x="800"
                                     data-y="248"
                                     data-speed="500"
                                     data-start="2500"
                                     data-easing="easeOutBack"><img src="/IS3102_Project-war/img/slides/slide-concept-2-1.png" alt=""></div>

                                <div class="tp-caption sfb"
                                     data-x="850"
                                     data-y="200"
                                     data-speed="400"
                                     data-start="3000"
                                     data-easing="easeOutBack"><img src="/IS3102_Project-war/img/slides/slide-concept-2-2.png" alt=""></div>

                                <div class="tp-caption sfb"
                                     data-x="820"
                                     data-y="170"
                                     data-speed="700"
                                     data-start="3150"
                                     data-easing="easeOutBack"><img src="/IS3102_Project-war/img/slides/slide-concept-2-3.png" alt=""></div>

                                <div class="tp-caption sfb"
                                     data-x="770"
                                     data-y="130"
                                     data-speed="1000"
                                     data-start="3250"
                                     data-easing="easeOutBack"><img src="/IS3102_Project-war/img/slides/slide-concept-2-4.png" alt=""></div>

                                <div class="tp-caption sfb"
                                     data-x="500"
                                     data-y="80"
                                     data-speed="600"
                                     data-start="3450"
                                     data-easing="easeOutExpo"><img src="/IS3102_Project-war/img/slides/slide-concept-2-5.png" alt=""></div>

                                <div class="tp-caption blackboard-text lfb "
                                     data-x="530"
                                     data-y="300"
                                     data-speed="500"
                                     data-start="3450"
                                     data-easing="easeOutExpo" style="font-size: 37px;">建</div>

                                <div class="tp-caption blackboard-text lfb "
                                     data-x="555"
                                     data-y="350"
                                     data-speed="500"
                                     data-start="3650"
                                     data-easing="easeOutExpo" style="font-size: 47px;">您</div>

                                <div class="tp-caption blackboard-text lfb "
                                     data-x="580"
                                     data-y="400"
                                     data-speed="500"
                                     data-start="3850"
                                     data-easing="easeOutExpo" style="font-size: 32px;">自己的家具:)</div>
                            </li>
                            <li data-transition="fade" data-slotamount="5" data-masterspeed="1000" >

                                <img src="img/slides/slide-bg.jpg" data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">

                                <div class="tp-caption fade"
                                     data-x="50"
                                     data-y="100"
                                     data-speed="1500"
                                     data-start="500"
                                     data-easing="easeOutExpo"><img src="/IS3102_Project-war/img/slides/slide-concept.png" alt=""></div>

                                <div class="tp-caption blackboard-text fade "
                                     data-x="180"
                                     data-y="180"
                                     data-speed="1500"
                                     data-start="1000"
                                     data-easing="easeOutExpo" style="font-size: 30px;">我们的产品</div>

                                <div class="tp-caption blackboard-text fade "
                                     data-x="180"
                                     data-y="220"
                                     data-speed="1500"
                                     data-start="1200"
                                     data-easing="easeOutExpo" style="font-size: 40px;">在美学<br/>愉快!</div>

                                <div class="tp-caption main-label sft stb"
                                     data-x="580"
                                     data-y="190"
                                     data-speed="300"
                                     data-start="1500"
                                     data-easing="easeOutExpo">岛家具</div>

                                <div class="tp-caption bottom-label sft stb"
                                     data-x="580"
                                     data-y="250"
                                     data-speed="500"
                                     data-start="2000"
                                     data-easing="easeOutExpo">了解更多信息下面！</div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="home-intro">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8">
                                <p>
                                    以最快的方式来拓展您的家与领先的 <em>家具.</em>
                                    <span>看看我们的家具和零售产品.</span>
                                </p>
                            </div>
                            <div class="col-md-4">
                                <div class="get-started">
                                    <a href="allFurnitureProducts.jsp" class="btn btn-lg btn-primary">现在就开始吧!</a>
                                    <div class="learn-more">或 <a href="allFurnitureProducts.jsp">了解更多.</a></div>
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
                                    我们的产品
                                    <strong class="inverted">
                                        <span class="word-rotate">
                                            <span class="word-rotate-items">
                                                <span>令人难以置信</span>
                                                <span>尤其</span>
                                                <span>极</span>
                                            </span>
                                        </span>
                                    </strong>
                                    美丽，适合所有的设计。
                                </h2>
                                <p class="featured lead">
                                    岛家具是国内领先的家具零售播放器产品在市场上.我们新颖的设计是为了帮助改变你的家变成你理想的设计.
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="container">
                        <div class="row">
                            <hr class="tall" />
                        </div>
                    </div>

                    <div class="container">

                        <div class="row">
                            <div class="col-md-8">
                                <h2>我们的<strong>服务</strong></h2>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-group"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">客户支持</h4>
                                                <p class="tall">客户是第一位的。</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-file"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">自由组装</h4>
                                                <p class="tall">所有产品都将装配自由。</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-google-plus"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">1年保修</h4>
                                                <p class="tall">我们所有的产品都有1年保修。</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-adjust"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">颜色</h4>
                                                <p class="tall">家具有不同的颜色。</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-film"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">专业设计</h4>
                                                <p class="tall">通过最先进的技术状态设计的。</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-user"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">厨房</h4>
                                                <p class="tall">我们销售的顶级亚洲美食。</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-bars"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">技术</h4>
                                                <p class="tall">立即下载我们的移动应用程序。</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-desktop"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">高质量</h4>
                                                <p class="tall">所有产品均配备了质的设计。</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <h2>更多...</h2>

                                <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                                    <i class="icon icon-usd"></i>
                                                    价格表
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseOne" class="accordion-body collapse in">
                                            <div class="panel-body">
                                                只要地球的质量，它建立舒适，易于任何人SAPIEN是很重要的。这只是大量仇恨的人对宏观的流行文化。
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                                    <i class="icon icon-comment"></i>
                                                    联系表格
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseTwo" class="accordion-body collapse">
                                            <div class="panel-body">
                                                Donec马萨得力士，韦尔condimentum三重坐阿梅德，QUIS facilisis SAPIEN。
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                                    <i class="icon icon-laptop"></i>
                                                    投资组合页面
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseThree" class="accordion-body collapse">
                                            <div class="panel-body">
                                                Donec马萨得力士，韦尔condimentum三重坐阿梅德，QUIS facilisis SAPIEN。
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
                                    我们不是唯一的
                                    <strong>
                                        <span class="word-rotate">
                                            <span class="word-rotate-items">
                                                <span>兴奋</span>
                                                <span>快乐</span>
                                            </span>
                                        </span>
                                    </strong>
                                    关于岛家具...
                                </h2>
                                <h4 class="lead tall">5,500的客户在100个国家用岛家具。满足我们的客户。</h4>
                            </div>
                        </div>
                    </div>
                </div>

                <jsp:include page="footer.html" />
            </div>
            <!-- Theme Initializer -->
            <script src="/IS3102_Project-war/js/theme.plugins.js"></script>
            <script src="/IS3102_Project-war/js/theme.js"></script>

            <!-- Current Page JS -->
            <script src="/IS3102_Project-war/vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
            <script src="/IS3102_Project-war/vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
            <script src="/IS3102_Project-war/vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
            <script src="/IS3102_Project-war/js/views/view.home.js"></script>   
        </div>
    </body>
</html>
