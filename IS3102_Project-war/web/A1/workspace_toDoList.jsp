
<%@page import="EntityManager.ToDoEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>


        <script>

            function updateToDoList(id) {
                toDoList.id.value = id;
                document.toDoList.action = "workspace_toDoListUpdate.jsp";
                document.toDoList.submit();
            }
            function removeToDoList() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.toDoList.action = "../WorkspaceToDoList_RemoveServlet";
                    document.toDoList.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function markDoneOrUndone(id) {
                toDoList.id.value = id;
                document.toDoList.action = "workspace_toDoListUpdate.jsp";
                document.toDoList.submit();
            }
            function addToDoList() {
                document.toDoList.action = "../WorkspaceToDoList_AddServlet";
                document.toDoList.submit();
            }
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }

        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">ToDo List</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="workspace.jsp">Workspace</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> ToDo List Management
                                </li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <%
                                        String errMsg = request.getParameter("errMsg");
                                        if (errMsg == null || errMsg.equals("")) {
                                            errMsg = "Welcome to ToDo List Management!";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="toDoList">
                                    <div class="panel-body">
                                        <div class="table-responsive">

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary btnAdd" id="add" name="" type="button" value="Add ToDo"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Delete  ToDo" onclick="removeToDoList()"  />
                                                </div>
                                            </div>
                                            <br>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox" onclick="checkAll(this)" /></th>
                                                            <th>Description</th>
                                                            <th>Done/Undone</th>
                                                            <th>Mark As Done</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<ToDoEntity> toDoList = (List<ToDoEntity>) (session.getAttribute("toDoList"));
                                                            if (toDoList != null) {
                                                                for (int i = 0; i < toDoList.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td style="width:40px;" >
                                                                <input type="hidden" name="done" disabled value="<%=toDoList.get(i).isDone()%>"/>
                                                                <input type="checkbox" name="delete" value="<%=toDoList.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=toDoList.get(i).getDescription()%>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    boolean isDone = toDoList.get(i).isDone();
                                                                    String val = "";
                                                                    String doneVal = "Mark As Done";
                                                                    if (isDone == true) {
                                                                        val = "checked";
                                                                        doneVal = "Mark As Undone";
                                                                    }
                                                                %>
                                                                <input type="checkbox" disabled name="status" <%=val%> value="<%=toDoList.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <input type="submit" name="btnDone" class="btn btn-primary btn-block" value="<%=doneVal%>"  onclick="javascript:markDoneOrUndone('<%=toDoList.get(i).getId()%>')"/>
                                                            </td>
                                                            <td style="width: 150px;">
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=toDoList.get(i).getId()%>" value="update" onclick="javascript:updateToDoList('<%=toDoList.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary btnAdd" id="add" name="" type="button" value="Add ToDo"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Delete ToDo" onclick="removeToDoList()"  />
                                                </div>
                                            </div>  
                                        </div>
                                        <div id="addToDoForm" hidden>
                                            <div class="row">
                                                <div class="form-group">
                                                    <div class="col-md-5">
                                                        <p class="page-header">Add New ToDo</p>
                                                        Description: 
                                                        <textarea class="form-control" type="" name="description"></textarea><br>
                                                        <input class="btn btn-primary" name="btnAdd" type="submit" value="Add" onclick="addToDoList()"  />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.panel-body -->

                                </form>

                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->


                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->


        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });

            $(".btnAdd").click(function () {
                $("html, body").animate({scrollTop: $(document).height()}, "slow");
                $("#addToDoForm").show("slow", function () {
                });
            });
        </script>
    </body>
</html>
