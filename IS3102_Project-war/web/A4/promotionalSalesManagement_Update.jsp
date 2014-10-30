<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.PromotionEntity"%>
<%@page import="EntityManager.CountryEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.FurnitureEntity"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <script>
        function updatePromotion() {
            document.promotionManagement.action = "../PromotionalSalesManagement_UpdateServlet";
            document.promotionManagement.submit();
        }
    </script>
    <body>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Add Promotion
                            </h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="../PromotionalSalesManagement_Servlet"> Promotion Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Update Promotion
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <%
                        try {
                            String id = request.getParameter("id");
                            List<PromotionEntity> promotions = (List<PromotionEntity>) (session.getAttribute("promotions"));
                            PromotionEntity promotion = new PromotionEntity();
                            for (int i = 0; i < promotions.size(); i++) {
                                if (promotions.get(i).getId() == Integer.parseInt(id)) {
                                    promotion = promotions.get(i);
                                }
                            }
                    %>
                    <jsp:include page="../displayMessage.jsp" />
                    <!-- /.warning -->
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" method="POST" enctype="multipart/form-data" name="promotionManagement">                        
                                <div class="form-group">
                                    <label>Item SKU</label>
                                    <input class="form-control" required="true" type="text" name="sku" value="<%=promotion.getItem().getSKU()%>">
                                </div>
                                <div class="form-group">
                                    <label>Country</label>
                                    <select required="true" name="country" value="<%=promotion.getCountry().getName()%>" class="form-control">
                                        <option></option>
                                        <%
                                            List<CountryEntity> countries = (List<CountryEntity>) (session.getAttribute("countries"));
                                            if (countries != null) {
                                                for (int i = 0; i < countries.size(); i++) {
                                                    out.println("<option value='" + countries.get(i).getId() + "'>" + countries.get(i).getName() + "</option>");
                                                }
                                            }
                                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                            String formatedDate = df.format(promotion.getStartDate());
                                            String formatedDate1 = df.format(promotion.getEndDate());
                                        %>
                                    </select>                                
                                </div>
                                <div class="form-group">
                                    <label>Discount Rate</label>
                                    <input class="form-control" type="number" required="true" min="1" max="100" step="1" name="discountRate" value="<%=promotion.getDiscountRate()%>" >
                                </div>
                                <div class="form-group">
                                    <label>Start Date</label>
                                    <input class="form-control" type="date" required="true" name="startDate" value="<%=formatedDate%>" >
                                </div>
                                <div class="form-group">
                                    <label>End Date</label>
                                    <input class="form-control" type="date" required="true" name="endDate" value="<%=formatedDate1%>">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input class="form-control" type="text" required="true" name="description" value="<%=promotion.getDescription()%>">
                                </div>
                                <div>
                                    <input type="file" name="javafile">
                                </div>
                                <br>
                                <div class="form-group">
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block" onclick="updatePromotion()">
                                </div>
                                <input type="hidden" value="A4/promotionalSalesManagement_Update.jsp" name="source">
                            </form>
                        </div>
                        <!-- /.row -->

                    </div>
                    <%} catch (Exception ex) {

                            //response.sendRedirect("../FurnitureManagement_FurnitureServlet");
                        }%>
                </div>

            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->
        <script>
            var today = new Date().toISOString().split('T')[0];
            document.getElementsByName("startDate")[0].setAttribute('min', today);
            document.getElementsByName("endDate")[0].setAttribute('min', today);
        </script>
    </body>

</html>
