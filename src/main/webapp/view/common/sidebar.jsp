<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/21
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse">
  <script type="text/javascript">
    try{ace.settings.check('sidebar','fixed')}catch(e){}
  </script>

  <ul class="nav nav-list" style="top:0px;">
    <li class="hover">
      <a href="<%=request.getContextPath()%>/view/staff/staff.jsp">
        <i class="menu-icon fa fa-user"></i>
        <span class="menu-text">员工管理</span>
      </a>

      <b class="arrow"></b>
    </li>

    <li class="hover hsub">
      <a href="#" class="dropdown-toggle">
        <i class="menu-icon fa fa-list"></i>
        <span class="menu-text">资产和物资管理</span>

        <b class="arrow fa fa-angle-down"></b>
      </a>

      <b class="arrow"></b>

      <ul class="submenu">
        <li class="hover">
          <a href="<%=request.getContextPath()%>/view/company/goodsManage.jsp">
            <i class="menu-icon fa fa-caret-right"></i>
            物资信息
          </a>
          <b class="arrow"></b>
        </li>
        <li class="hover">
          <a href="<%=request.getContextPath()%>/view/vehicle/VehicleManage.jsp">
            <i class="menu-icon fa fa-caret-right"></i>
            车辆信息
          </a>
          <b class="arrow"></b>
        </li>
          <li class="hover">
              <a href="<%=request.getContextPath()%>/view/vehicle/VehicleTypeManage.jsp">
                  <i class="menu-icon fa fa-caret-right"></i>
                  车型信息
              </a>
              <b class="arrow"></b>
          </li>
          <li class="hover">
              <a href="<%=request.getContextPath()%>/view/oilBuy/OilBuyManage.jsp">
                  <i class="menu-icon fa fa-caret-right"></i>
                  燃油管理
              </a>
              <b class="arrow"></b>
          </li>
      </ul>
    </li>
    <li class="hover hsub">
      <a href="#" class="dropdown-toggle">
        <i class="menu-icon fa fa-truck"></i>
        <span class="menu-text">物流信息管理</span>

        <b class="arrow fa fa-angle-down"></b>
      </a>
      <b class="arrow"></b>
      <ul class="submenu">
        <li class="hover">
          <a href="<%=request.getContextPath()%>/view/transTask/transTask.jsp">
            <i class="menu-icon fa fa-caret-right"></i>
            运输任务管理
          </a>
          <b class="arrow"></b>
        </li>
        <li class="hover">
          <a href="<%=request.getContextPath()%>/view/address/addressManage.jsp">
            <i class="menu-icon fa fa-caret-right"></i>
            地址信息管理
          </a>
          <b class="arrow"></b>
        </li>
        <li class="hover">
          <a href="<%=request.getContextPath()%>/view/logistics/carBussiness.jsp">
            <i class="menu-icon fa fa-caret-right"></i>
            单车运营记录
          </a>
          <b class="arrow"></b>
        </li>
      </ul>
    </li>

    <li class="hover">
      <a href="<%=request.getContextPath()%>/view/company/companyManage.jsp">
        <i class="menu-icon fa fa-building-o"></i>
        <span class="menu-text">承运商管理</span>
      </a>
      <b class="arrow"></b>
    </li>
    <li class="hover hsub">
      <a href="<%=request.getContextPath()%>/view/Chart/ChartForMonth.jsp">
        <i class="menu-icon fa fa-bar-chart-o"></i>
        <span class="menu-text">数据报表</span>
      </a>
      <b class="arrow"></b>
        <ul class="submenu">
            <li class="hover">
            <a href="<%=request.getContextPath()%>/view/Chart/ChartForMonth.jsp">
                <i class="menu-icon fa fa-caret-right"></i>
                公司运营报表（月)
            </a>
            <b class="arrow"></b>
            </li>
            <li class="hover">
                <a href="<%=request.getContextPath()%>/view/Chart/ChartForSeason.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    公司运营报表(季度)
                </a>
                <b class="arrow"></b>
            </li>
            <li class="hover">
                <a href="<%=request.getContextPath()%>/view/Chart/ChartForYear.jsp">
                    <i class="menu-icon fa fa-caret-right"></i>
                    公司运营报表(年)
                </a>
                <b class="arrow"></b>
            </li>
            </ul>
    </li>
      <li class="hover">
          <a href="<%=request.getContextPath()%>/view/admin/adminManage.jsp">
              <i class="menu-icon fa fa-users"></i>
              <span class="menu-text">管理员帐号</span>
          </a>
          <b class="arrow"></b>
      </li>
    <li class="hover hsub">
      <a href="<%=request.getContextPath()%>/view/company/about.jsp">
        <i class="menu-icon fa fa-tag"></i>
        <span class="menu-text">关于</span>
        <b class="arrow fa fa-angle-down"></b>
      </a>
      <b class="arrow"></b>
    </li>
  </ul> <!-- nav-list -->
  <!-- #section:basics/sidebar.layout.minimize -->
  <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
    <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
  </div>
  <!-- /section:basics/sidebar.layout.minimize -->
  <script type="text/javascript">
    try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
  </script>
</div> <!-- sidebar -->
