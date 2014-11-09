<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer id="footer">
    <script>
        function addToSubscription() {
            
            document.subscriptionManagement.action = "/IS3102_Project-war/ECommerce_NewsletterSubscribeServlet";
            document.subscriptionManagement.submit();
        }
    </script>

    <div class="container">
        <div class="row">
            <div class="footer-ribbon">
                <span>取得联系</span>
            </div>
            <div class="col-md-3">
                <div class="newsletter">
                    <h4>通讯</h4>
                    <p>跟上我们总是在不断发展的产品功能和技术。请输入您的电子邮件和订阅我们的通讯。</p>

                    <div class="alert alert-success hidden" id="newsletterSuccess">
                        <strong>Success!</strong> You've been added to our email list.
                    </div>

                    <div class="alert alert-danger hidden" id="newsletterError"></div>

                    <form name="subscriptionManagement">
                        <div class="input-group">
                            <input class="form-control" placeholder="Email Address" name="email" id="newsletterEmail" type="text">
                            <span class="input-group-btn">
                                <a href="#myModal" data-toggle="modal"><button class="btn btn-default" type="submit">Go!</button></a>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-4">
                <div class="contact-details">
                    <h4>联系我们</h4>
                    <ul class="contact">
                        <li><p><i class="icon icon-map-marker"></i> <strong>地址:</strong> 徐汇区漕溪路126号, 近三汇路</p></li>
                        <li><p><i class="icon icon-phone"></i> <strong>电话:</strong> (86) 2154254532</p></li>
                        <li><p><i class="icon icon-envelope"></i> <strong>电子邮件:</strong> <a href="mailto:caoxi@if.com">caoxi@if.com</a></p></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-2">
                <h4>跟着我们</h4>
                <div class="social-icons">
                    <ul class="social-icons">
                        <li class="facebook"><a href="http://www.facebook.com/" target="_blank" data-placement="bottom" rel="tooltip" title="Facebook">Facebook</a></li>
                        <li class="twitter"><a href="http://www.twitter.com/" target="_blank" data-placement="bottom" rel="tooltip" title="Twitter">Twitter</a></li>
                        <li class="linkedin"><a href="http://www.linkedin.com/" target="_blank" data-placement="bottom" rel="tooltip" title="Linkedin">Linkedin</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-1">
                    <a href="index.html" class="logo">
                        <img alt="Island Furniture" class="img-responsive" src="/IS3102_Project-war/img/logo-footer.png">
                    </a>
                </div>
                <div class="col-md-7">
                    <p>Copyright 2014. All Rights Reserved.</p>
                </div>
                <div class="col-md-4">
                    <nav id="sub-menu">
                        <ul>
                            <li><a href="/IS3102_Project-war/B/selectCountry.jsp">更改位置 (Change Location)</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <div role="dialog" class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Thank you for subscribing</h4>
                </div>
                <div class="modal-body">
                    <p id="messageBox">You have been added to our subscription list!</p>
                </div>
                <div class="modal-footer">                        
                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Ok" onclick="addToSubscription()"  />
                </div>
            </div>
        </div>
    </div>
</footer>