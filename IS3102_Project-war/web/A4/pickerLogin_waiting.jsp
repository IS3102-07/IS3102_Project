<%@ page import="java.io.*,java.util.*" %>
<%
    Long pickerID = (Long) (session.getAttribute("pickerID"));
    if (pickerID == null) {
%>
<jsp:forward page="A4/pickerLogin.jsp?errMsg=Session Expired." />
<%
    }
%>

<html>
    <head>
        <jsp:include page="../header1.html" />
    </head>
    <body class="dark">
        <%
            response.setIntHeader("Refresh", 1);
            Calendar calendar = new GregorianCalendar();
            String am_pm;
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            if (calendar.get(Calendar.AM_PM) == 0) {
                am_pm = "AM";
            } else {
                am_pm = "PM";
            }
            String CT = hour + ":" + minute + ":" + second + " " + am_pm;
            out.println("Crrent Time: " + CT + "\n");
        %>
        <div role="main" class="main">

            <div class="header-container">
                <div class="row" style="background-color : rgb(153, 0, 0); margin-bottom: 50px" >
                    <div class="col-md-4 col-md-offset-4">  
                        <img class="center-block img-responsive"  src="../img/logo-label.png" style="margin-top: 20px; margin-bottom: 20px;">
                    </div>
                </div>
            </div>


            <div class="container">
                <div class="row">
                    <div class="col-md-9" >

                        <h2>No <strong>Jobs</strong> At the moment</h2>

                        <div class="row">
                            <div class="col-md-12">
                                <p class="lead">
                                    No jobs available... No jobs available... No jobs available... No jobs available...  
                                </p>
                            </div>
                        </div>

                        <hr class="tall">
                    </div>

                    <div class="col-md-3">
                        <aside class="sidebar">
                            <form action="../PickerLogout_Servlet">
                                <input type="submit" value="Logout" class="btn btn-lg btn-primary btn-block" style="min-height: 500px; font-size: 50px;">
                            </form>
                        </aside>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
