<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="EntityManager.MessageInboxEntity"%>
<%@page import="CommonInfrastructure.AccountManagement.AccountManagementBeanLocal"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
<%
    StaffEntity staffEntity = (StaffEntity) (session.getAttribute("staffEntity"));
    if (staffEntity == null) {
%>
<jsp:forward page="A1/staffLogin.jsp?errMsg=Session Expired." />
<%
} else {
    List<RoleEntity> roles = staffEntity.getRoles();
    Long[] approvedRolesID;
    boolean roleCanView;
%>

<%
    List<MessageInboxEntity> listOfInboxMsg = (List<MessageInboxEntity>) session.getAttribute("inboxMessages");
    /*  MessageInboxEntity msg = new MessageInboxEntity();
     msg.setId(1L);
     msg.setMessage("helloooooooo how are you?");
     StaffEntity staff = new StaffEntity();
     staff.setName("Gabriel");
     msg.setSender(staff);
     msg.setSentDate(Calendar.getInstance().getTime());

     listOfInboxMsg.add(msg);*/

    int sizeOfList = listOfInboxMsg.size();
    MessageInboxEntity inbox;
    String inbox1_id = "";
    String inbox1_date = "";
    String inbox1_sender = "";
    String inbox1_message = "";

    String inbox2_id = "";
    String inbox2_date = "";
    String inbox2_sender = "";
    String inbox2_message = "";

    String inbox3_id = "";
    String inbox3_date = "";
    String inbox3_sender = "";
    String inbox3_message = "";
    switch (sizeOfList) {
        case 0:
            break;
        case 1:
            inbox = listOfInboxMsg.get(0);
            inbox1_id = inbox.getId() + "";
            inbox1_date = inbox.getSentDate().toString();
            inbox1_sender = inbox.getSender().getName();
            inbox1_message = inbox.getMessage();
            break;
        case 2:
            inbox = listOfInboxMsg.get(0);
            inbox1_id = inbox.getId() + "";
            inbox1_date = inbox.getSentDate().toString();
            inbox1_sender = inbox.getSender().getName();
            inbox1_message = inbox.getMessage();

            inbox = listOfInboxMsg.get(1);
            inbox2_id = inbox.getId() + "";
            inbox2_date = inbox.getSentDate().toString();
            inbox2_sender = inbox.getSender().getName();
            inbox2_message = inbox.getMessage();
            break;
        default:
            inbox = listOfInboxMsg.get(0);
            inbox1_id = inbox.getId() + "";
            inbox1_date = inbox.getSentDate().toString();
            inbox1_sender = inbox.getSender().getName();
            inbox1_message = inbox.getMessage();

            inbox = listOfInboxMsg.get(1);
            inbox2_id = inbox.getId() + "";
            inbox2_date = inbox.getSentDate().toString();
            inbox2_sender = inbox.getSender().getName();
            inbox2_message = inbox.getMessage();

            inbox = listOfInboxMsg.get(2);
            inbox3_id = inbox.getId() + "";
            inbox3_date = inbox.getSentDate().toString();
            inbox3_sender = inbox.getSender().getName();
            inbox3_message = inbox.getMessage();
    }
%>


<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="../Workspace_Servlet">Island Furniture - Staff Portal</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-envelope"> Inbox</i> <b class="caret"></b></a>
            <ul class="dropdown-menu message-dropdown">
                <li class="message-preview" id="m1">
                    <a href="../WorkspaceMessage_Servlet">
                        <div class="media">
                            <div class="media-body">
                                <h5 class="media-heading"><strong><%=inbox1_sender%></strong></h5><!--Sender-->
                                <p class="small text-muted"><i class="icon icon-clock-o"><%=inbox1_date%></i> </p><!--Date sent-->
                                <p><%=inbox1_message%></p> <!--Message Content-->
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-preview" id="m2">
                    <a href="../WorkspaceMessage_Servlet">
                        <div class="media">
                            <div class="media-body">
                                <h5 class="media-heading"><strong><%=inbox2_sender%></strong></h5><!--Sender-->
                                <p class="small text-muted"><i class="icon icon-clock-o"><%=inbox2_date%></i> </p><!--Date sent-->
                                <p><%=inbox2_message%></p> <!--Message Content-->
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-preview" onLoad="refreshMsg()">
                    <a href="../WorkspaceMessage_Servlet">
                        <div class="media" id="m3">
                            <div class="media-body">
                                <h5 class="media-heading"><strong><%=inbox3_sender%></strong></h5><!--Sender-->
                                <p class="small text-muted"><i class="icon icon-clock-o"><%=inbox3_date%></i> </p><!--Date sent-->
                                <p><%=inbox3_message%></p> <!--Message Content-->
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-footer">
                    <a href="../WorkspaceMessage_Servlet">Read All New Messages</a>
                </li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-group"></i> My Roles <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <%
                    for (RoleEntity role : roles) {
                %>
                <li>
                    <a href="#"><i class="icon icon-user"></i> <%= role.getName()%></a>
                </li>    
                <%
                    }
                %>                       
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-user"></i> <%=staffEntity.getName()%><b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li>
                    <a href="../A1/staffProfile.jsp"><i class="icon icon-user"></i> Profile</a>
                </li>
                <li>
                    <a href="../WorkspaceMessage_Servlet?view=inbox"><i class="icon icon-envelope"></i> Inbox</a>
                </li>
                <li>
                    <a href="#"><i class="icon icon-gear"></i> Settings</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="../AccountManagement_LogoutServlet"><i class="icon icon-power-off"></i> Log Out</a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <%
                approvedRolesID = new Long[]{1L, 2L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#commonInfrastructure">
                    <i class="icon icon-user"></i> Common Infrastructure <i class="icon icon-caret-down"></i>
                </a>
                <ul id="commonInfrastructure" class="collapse">
                    <li>
                        <a href="../A1/accountManagement.jsp">Account Management</a>
                    </li>
                </ul>
            </li>
            <%}
                approvedRolesID = new Long[]{1L, 2L, 3L, 4L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#MRP">
                    <i class="icon icon-book"></i> MRP <i class="icon icon-caret-down"></i>
                </a>
                <ul id="MRP" class="collapse">
                    <li>
                        <a href="../SaleForecast_Servlet/SaleForecast_index_GET">Sales Forecast</a>
                    </li>
                    <li>
                        <a href="../SaleAndOperationPlanning_Servlet/sop_index_GET">Sales and Operations Planning</a>
                    </li>
                    <li>
                        <a href="#">Production Plan Distribution</a>
                    </li>
                    <li>
                        <a href="#">Demand Management</a>
                    </li>
                    <li>
                        <a href="#">Manufacturing Requirement Planning</a>
                    </li>
                    <li>
                        <a href="#">Material Requirement Fulfilment</a>
                    </li>
                </ul>
            </li>
            <% }
                approvedRolesID = new Long[]{1L, 2L, 3L, 4L, 7L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#SCM">
                    <i class="icon icon-home"></i> SCM <i class="icon icon-caret-down"></i>
                </a>
                <ul id="SCM" class="collapse">
                    <li>
                        <a href="../PurchaseOrderManagement_Servlet">Retail Products and Raw Materials Purchasing</a>
                    </li>
                    <li>
                        <a href="../SupplierManagement_SupplierServlet">Supplier Management</a>
                    </li>
                    <li>
                        <a href="../ShippingOrderManagement_Servlet">Inbound and Outbound Logistics</a>
                    </li>
                    <li>
                        <a href="../ManufacturingWarehouseManagement_Servlet">Manufacturing's Warehouse Management</a>
                    </li>
                </ul>
            </li>
            <% }
                approvedRolesID = new Long[]{1L,2L,4L,5L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#pperationalCRM">
                    <i class="icon icon-cogs"></i> Operational CRM <i class="icon icon-caret-down"></i>
                </a>
                <ul id="pperationalCRM" class="collapse">
                    <li>
                        <a href="#">Loyalty & Rewards</a>
                    </li>
                    <li>
                        <a href="#">Customer Service</a>
                    </li>
                    <li>
                        <a href="#">Promotional Sales</a>
                    </li>
                    <li>
                        <a href="#">Product Retirement Campaign</a>
                    </li>
                    <li>
                        <a href="#"> Customer Information Management</a>
                    </li>
                </ul>
            </li>
            <% }
                approvedRolesID = new Long[]{1L, 2L, 4L, 5L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#analyticalCRM">
                    <i class="icon icon-bar-chart-o"></i> Analytical CRM <i class="icon icon-caret-down"></i>
                </a>
                <ul id="analyticalCRM" class="collapse">
                    <li>
                        <a href="#">Customer Value Analysis</a>
                    </li>
                    <li>
                        <a href="#">Segmentation Marketing</a>
                    </li>
                    <li>
                        <a href="#">Product Retirement Campaign</a>
                    </li>
                </ul>
            </li>
            <% }
                approvedRolesID = new Long[]{1L,2L,6L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#corporateCRM">
                    <i class="icon icon-briefcase"></i> Corporate CRM <i class="icon icon-caret-down"></i>
                </a>
                <ul id="corporateCRM" class="collapse">
                    <li>
                        <a href="../A6/facilityManagement.jsp">Facility Management</a>
                    </li>
                    <li>
                        <a href="../A6/itemManagement.jsp">Item Management</a>
                    </li>
                </ul>
            </li>
            <% }
            %>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>

<%}%>