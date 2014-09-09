<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>

        <div id="wrapper">

            <jsp:include page="../menu2.html" />

            <div id="page-wrapper">

                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Item Management
                            </h1>
                        </div>
                    </div>

                    <!-- Table -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Page</th>
                                            <th>Visits</th>
                                            <th>% New Visits</th>
                                            <th>Revenue</th>
                                            <th>Update</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="active">
                                            <td><input type="checkbox" name="chkChecked" value=""></td>
                                            <td>/index.html</td>
                                            <td>1265</td>
                                            <td>32.3%</td>
                                            <td>$321.33</td>
                                            <td><input type="button" class="btn btn-primary btn-block" name="btnDetails" id="" value="Vew" onclick=""/></td>
                                        </tr>
                                        <tr class="success">
                                            <td><input type="checkbox" name="chkChecked" value="1"></td>
                                            <td>/about.html</td>
                                            <td>261</td>
                                            <td>33.3%</td>
                                            <td>$234.12</td>
                                            <td><input type="button" class="btn btn-primary btn-block" name="btnDetails" id="" value="Vew" onclick=""/></td>
                                        </tr>
                                        <tr class="warning">
                                            <td><input type="checkbox" name="chkChecked" value="1"></td>
                                            <td>/sales.html</td>
                                            <td>665</td>
                                            <td>21.3%</td>
                                            <td>$16.34</td>
                                            <td><input type="button" class="btn btn-primary btn-block" name="btnDetails" id="" value="Vew" onclick=""/></td>
                                        </tr>
                                        <tr class="danger">
                                            <td><input type="checkbox" name="chkChecked" value="1"></td>
                                            <td>/blog.html</td>
                                            <td>9516</td>
                                            <td>89.3%</td>
                                            <td>$1644.43</td>
                                            <td><input type="button" class="btn btn-primary btn-block" name="btnDetails" id="" value="Vew" onclick=""/></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="chkChecked" value="1"></td>
                                            <td>/404.html</td>
                                            <td>23</td>
                                            <td>34.3%</td>
                                            <td>$23.52</td>
                                            <td><input type="button" class="btn btn-primary btn-block" name="btnDetails" id="" value="Vew" onclick=""/></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="chkChecked" value="1"></td>
                                            <td>/services.html</td>
                                            <td>421</td>
                                            <td>60.3%</td>
                                            <td>$724.32</td>
                                            <td><input type="button" class="btn btn-primary btn-block" name="btnDetails" id="" value="Vew" onclick=""/></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="chkChecked" value="1"></td>
                                            <td>/blog/post.html</td>
                                            <td>1233</td>
                                            <td>93.2%</td>
                                            <td>$126.34</td>
                                            <td><input type="button" class="btn btn-primary btn-block" name="btnDetails" id="" value="Vew" onclick=""/></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="submit" onclick="" value="Delete" class="btn btn-primary" data-loading-text="Loading...">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery Version 1.11.0 -->
        <script src="../js/jquery-1.11.0.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../js/bootstrap.min.js"></script>

    </body>

</html>
