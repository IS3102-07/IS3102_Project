<%@page import="EntityManager.CountryEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.FurnitureEntity"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <script>
        function addPromotion() {
            document.promotionManagement.action = "../PromotionalSalesManagement_AddServlet";
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
                                    <i class="icon icon-user"></i><a href="../promotionalSalesManagement_Servlet"> Promotion Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Add Promotion
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <jsp:include page="../displayMessage.jsp" />
                    <!-- /.warning -->
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" name="promotionManagement">                        
                                <div class="form-group">
                                    <label>Item SKU</label>
                                    <input class="form-control" required="true" type="text" name="sku" >
                                </div>
                                <div class="form-group">
                                    <label>Country</label>
                                    <select required="true" name="country" class="form-control">
                                        <option></option>
                                        <%
                                            List<CountryEntity> countries = (List<CountryEntity>) (session.getAttribute("countries"));
                                            if (countries != null) {
                                                for (int i = 0; i < countries.size(); i++) {
                                                    out.println("<option value='" + countries.get(i).getId() + "'>" + countries.get(i).getName() + "</option>");
                                                }
                                            }
                                        %>
                                    </select>                                
                                </div>
                                <div class="form-group">
                                    <label>Discount Rate</label>
                                    <input class="form-control" type="number" required="true" min="1" max="100" step="1" name="discountRate" >
                                </div>
                                <div class="form-group">
                                    <label>Start Date</label>
                                    <input class="form-control" type="date" required="true" name="startDate" >
                                </div>
                                <div class="form-group">
                                    <label>End Date</label>
                                    <input class="form-control" type="date" required="true" name="endDate" >
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input class="form-control" type="text" required="true" name="description" >
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Add" class="btn btn-lg btn-primary btn-block" onclick="addPromotion()">
                                </div>
                                <input type="hidden" value="A4/promotionalSalesManagement_Add.jsp" name="source">
                            </form>
                        </div>
                        <!-- /.row -->

                    </div>
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
