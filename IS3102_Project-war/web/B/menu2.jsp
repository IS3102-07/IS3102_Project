<%@page import="EntityManager.MemberEntity"%>
<%String URLprefix = (String) session.getAttribute("URLprefix");%>
<header id="header">
    <div class="container">
        <h1 class="logo">
            <a href="index.jsp">
                <img alt="Island Furniture" width="180" height="80" data-sticky-width="82" data-sticky-height="40" src="../img/logo.png">
            </a>
        </h1>
        <%
            MemberEntity member = (MemberEntity) (session.getAttribute("member"));
            if (member == null) {
        %>
        <nav>
            <ul class="nav nav-pills nav-top">
                <li>
                    <a href="../ECommerce_StoresServlet"><i class="icon icon-map-marker"></i>Store Location</a>
                </li>
                <li>
                    <a href="memberLogin.jsp"><i class="icon icon-unlock-alt"></i>Login/Register</a>
                </li>
                <li>
                    <a href="contactUs.jsp"><i class="icon icon-shopping-cart"></i>Contact Us</a>
                </li>
            </ul>
            <button class="btn btn-responsive-nav btn-inverse" data-toggle="collapse" data-target=".nav-main-collapse">
                <i class="icon icon-bars"></i>
            </button>
        </nav>
        <%
        } else {
        %>
        <nav>
            <ul class="nav nav-pills nav-top">
                <li>
                    <a>Welcome <%=member.getName()%>!</a>
                </li>
                <li>
                    <a href="../ECommerce_StoresServlet"><i class="icon icon-map-marker"></i>Store Location</a>
                </li>
                <li>
                    <a href="../ECommerce_WishListServlet"><i class="icon icon-shopping-cart"></i>My Wish List</a>
                </li>
                <li>
                    <a href="memberProfile.jsp"><i class="icon icon-user"></i>Profile</a>
                </li>
                <li>
                    <a href="contactUs.jsp"><i class="icon icon-shopping-cart"></i>Contact Us</a>
                </li>
                <li>
                    <a href="../ECommerce_LogoutServlet"><i class="icon icon-unlock-alt"></i>Logout</a>
                </li>
            </ul>
            <button class="btn btn-responsive-nav btn-inverse" data-toggle="collapse" data-target=".nav-main-collapse">
                <i class="icon icon-bars"></i>
            </button>
        </nav>
        <%}%>
    </div>
    <div class="navbar-collapse nav-main-collapse collapse">
        <div class="container">
            <nav class="nav-main mega-menu">
                <ul class="nav nav-pills nav-main" id="mainMenu">
                    <li class="dropdown active">
                        <a href="allProductsCatalog.jsp">All Products</a>
                    </li>
                    <li>
                        <a href="#">Promotion</a>
                    </li>
                    <li>
                        <a href="virtualStore.jsp">Virtual Store</a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="#">
                            All Departments
                            <i class="icon icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-map-marker"></i> Tables & Desk</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-map-marker"></i> Bathroom</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-map-marker"></i> Beds & Mattresses</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-map-marker"></i> Sofas & Chair</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-map-marker"></i> Cabinets & Storage</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-map-marker"></i> Lightings</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-map-marker"></i> Study</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-map-marker"></i> Children</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-coffee"></i> Food Products</a></li>
                            <li><a href="<%=URLprefix%>about-us.html"><i class="icon icon-cutlery"></i> Restaurant</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
