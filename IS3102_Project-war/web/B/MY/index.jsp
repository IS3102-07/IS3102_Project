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
                                     data-easing="easeOutExpo">ADAKAH ANDA PERLU</div>

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
                                     data-easing="easeOutExpo">PERABOT?</div>

                                <div class="tp-caption bottom-label sft stb"
                                     data-x="80"
                                     data-y="280"
                                     data-speed="500"
                                     data-start="2000"
                                     data-easing="easeOutExpo">Semak pilihan dan ciri-ciri kami.</div>

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
                                     data-easing="easeOutExpo" style="font-size: 37px;">Membangun</div>

                                <div class="tp-caption blackboard-text lfb "
                                     data-x="555"
                                     data-y="350"
                                     data-speed="500"
                                     data-start="3650"
                                     data-easing="easeOutExpo" style="font-size: 47px;">anda</div>

                                <div class="tp-caption blackboard-text lfb "
                                     data-x="580"
                                     data-y="400"
                                     data-speed="500"
                                     data-start="3850"
                                     data-easing="easeOutExpo" style="font-size: 32px;">perabot sendiri :)</div>
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
                                     data-easing="easeOutExpo" style="font-size: 30px;">Produk Kami</div>

                                <div class="tp-caption blackboard-text fade "
                                     data-x="180"
                                     data-y="220"
                                     data-speed="1500"
                                     data-start="1200"
                                     data-easing="easeOutExpo" style="font-size: 40px;">Adakah Estetis<br/>Menyenangkan!</div>

                                <div class="tp-caption main-label sft stb"
                                     data-x="580"
                                     data-y="190"
                                     data-speed="300"
                                     data-start="1500"
                                     data-easing="easeOutExpo">Perabot Pulau</div>

                                <div class="tp-caption bottom-label sft stb"
                                     data-x="580"
                                     data-y="250"
                                     data-speed="500"
                                     data-start="2000"
                                     data-easing="easeOutExpo">Cari Out More Bawah!</div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="home-intro">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8">
                                <p>
                                    Cara yang paling cepat berkembang rumah anda dengan pemimpin dalam <em>Perabot.</em>
                                    <span>Semak perabot dan produk-produk runcit.</span>
                                </p>
                            </div>
                            <div class="col-md-4">
                                <div class="get-started">
                                    <a href="allFurnitureProducts.jsp" class="btn btn-lg btn-primary">Mula Sekarang!</a>
                                    <div class="learn-more">atau <a href="allFurnitureProducts.jsp">ketahui lebih lanjut.</a></div>
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
                                    Produk kami adalah
                                    <strong class="inverted">
                                        <span class="word-rotate">
                                            <span class="word-rotate-items">
                                                <span>luar biasa</span>
                                                <span>terutama</span>
                                                <span>sangat</span>
                                            </span>
                                        </span>
                                    </strong>
                                    cantik dan sesuai dengan reka bentuk semua.
                                </h2>
                                <p class="featured lead">
                                    Perabot Pulau perabot dan produk runcit pemain terkemuka di pasaran. Desain baru kami adalah bertujuan untuk membantu mengubah rumah anda ke dalam reka bentuk yang ideal anda.
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
                                <h2>Our <strong>Services</strong></h2>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-group"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Sokongan Pelanggan</h4>
                                                <p class="tall">Pelanggan datang pertama.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-file"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Perhimpunan percuma</h4>
                                                <p class="tall">Semua produk akan dipasang percuma.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-google-plus"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">1 Tahun Waranti</h4>
                                                <p class="tall">Semua produk kami datang dengan jaminan 1 tahun.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-adjust"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Warna</h4>
                                                <p class="tall">Perabot mempunyai warna yang berbeza.</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-film"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Rekabentuk Profesional</h4>
                                                <p class="tall">Direka oleh keadaan teknologi seni.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-user"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Dapur</h4>
                                                <p class="tall">Kami menjual masakan Asia atas.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-bars"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Teknologi</h4>
                                                <p class="tall">Memuat turun aplikasi mudah alih kami sekarang.</p>
                                            </div>
                                        </div>
                                        <div class="feature-box">
                                            <div class="feature-box-icon">
                                                <i class="icon icon-desktop"></i>
                                            </div>
                                            <div class="feature-box-info">
                                                <h4 class="shorter">Kualiti Tinggi</h4>
                                                <p class="tall">Semua produk datang dengan reka bentuk kualitatif.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <h2>dan banyak lagi ...</h2>

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
                                                    Borang Hubungi
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
                                    Kita bukan satu-satunya
                                    <strong>
                                        <span class="word-rotate">
                                            <span class="word-rotate-items">
                                                <span>gembira</span>
                                                <span>senang</span>
                                            </span>
                                        </span>
                                    </strong>
                                    tentang Perabot Pulau ...
                                </h2>
                                <h4 class="lead tall">5,500 pelanggan di 100 buah negara menggunakan Perabot Island. Memenuhi pelanggan kami.</h4>
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
