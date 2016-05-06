<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/9/2
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <title>管理员帐号管理</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css" >
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>

<body class="no-skin">
<!-- 导航栏 -->
<div id="navbar" class="navbar navbard-default">
  <script type="text/javascript">
    try{ace.settings.check('navbar','fixed')}catch(e){}
  </script>

  <div class="navbar-container" id="navbar=container">
    <!-- #section:basics/sidebar.mobile.toggle -->
    <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
      <span class="sr-only">Toggle sidebar</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>

    <!-- /section:basics/sidebar.mobile.toggle -->
    <div class="navbar-header pull-left">
      <!-- #section:basics/navbar.layout.brand -->
      <a href="#" class="navbar-brand">
        <i class="fa fa-leaf"></i>
        <span id="title">管理员帐号管理</span>
      </a>
      <!-- #section:basics/navbar.layout.brand -->
    </div>
    <%@include file="/view/common/adminAccountTag.jsp"%>
    <!-- /section:basics/sidebar.mobile.toggle -->
  </div><!-- /.navbar-container -->
</div>
<!-- 导航栏结束 -->

<!-- 主体部分 -->
<div class="main-container" id="main-container">
  <%@ include file="/view/common/sidebar.jsp"%>
  <div class="col-xs-12">
    <h3 class="header smaller lighter blue">管理员帐号管理</h3>
    <div class="table-header">查询结果按时间先后顺序排列</div>

    <div id="base-info" class="tab-pane active dataTables_wrapper form-inline" role="grid">
      <div class="row">

        <div class="col-xs-3">
          <div class="btn-group">
            <a id="addItem" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i>添加</a>
            <a id="deleteItem" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash-o"></i>删除</a>
            <button class="btn-primary btn-sm" disabled="disabled">显示条目</button>
            <select id="pageSize" size="1" name="sample-table-2_length" aria-controls="sample-table-2">
              <option value="5">5</option>
              <option value="10" selected="selected">10</option>
              <option value="25">25</option>
            </select>
          </div>
        </div>

        <div class="col-xs-3 col-xs-offset-6">
          <div class="input-group">
            <input id="searchText" type="text" class="form-control" placeholder="搜索...">
            <span class="input-group-btn">
              <button id="search" class="btn btn-primary btn-sm" type="button">搜索</button>
            </span>
          </div>
        </div>

      </div> <!-- row -->

      <table id="sample-table" class="table table-striped table-bordered table-hover dataTable" aria-describedby="sample-table-2_info" >
        <thead>
        <tr role="row">
          <th class="center sorting_disabled" role="columnheader" rowspan="1" colspan="1" aria-label="">
            <label class="position-relative">
              <input id="selectAll" type="checkbox" class="ace">
              <span class="lbl"></span>
            </label>
          </th>

          <th class="sorting" role="columnheader" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1" aria-label="Update: activate to sort column ascending">
            名称
          </th>

          <th class="sorting" role="columnheader" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1" aria-label="Update: activate to sort column ascending">
            密码
          </th>

          <th class="sorting" role="columnheader" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1" aria-label="Update: activate to sort column ascending">
            类型
          </th>

          <th class="sorting" role="columnheader" tabindex="0" aria-controls="sample-table-2" rowspan="1" colspan="1" aria-label="Update: activate to sort column ascending"
              id="operation">
            操作
          </th>

        </tr>
        </thead>

        <tbody id="mainTbody" role="alert" aria-live="polite" aria-relevant="all">
        </tbody>

        <tbody id="hidedTbody" role="alert" aria-live="polite" aria-relevant="all">
        <tr class="odd" style="display:none;">
          <td class="center sorting_1">
            <label class="position-relative">
              <input type="checkbox" class="ace">
              <span class="lbl"></span>
            </label>
          </td>
          <td class="id" style="display: none;"></td>
          <td class="adminName"><input type="text"></td>
          <td class="adminPassword"><input type="text"></td>
          <td class="type">
            <select class="selectType">
              <option value="1">超级管理员</option>
              <option value="2">物资管理员</option>
              <option value="3">运输管理员</option>
              <option value="4">车辆管理员</option>
              <option value="5">人力资源管理员</option>
              <option value="6">公司信息管理员</option>
              <option value="7">财务管理员</option>
            </select>
          </td>
          <td class="op">
            <div id="operationButton" class="hidden-sm hidden-xs action-buttons">
              <a id="saveAdd" class="blue">
                <i class="ace-icon fa fa-check bigger-150"></i>
              </a>
              <a id="reset" class="blue">
                <i class="ace-icon fa fa-refresh bigger-150"></i>
              </a>
              <a id="update" class="blue">
                <i class="ace-icon fa fa-spinner fa-spin bigger-150"></i>
              </a>
              <a id="cancal" class="red">
                <i class="ace-icon fa fa-times bigger-150"></i>
              </a>
              <a id="deleteItem2" class="red">
                <i class="ace-icon fa fa-trash-o bigger-150"></i>
              </a>
            </div>
          </td>
        </tr>
        </tbody>

      </table>

      <div class="row">
        <div class="col-xs-12">
          <div class="dataTables_paginate paging_bootstrap">
            <ul class="pagination">
              <li id="beginPage" class="prev disabled">
                <a>
                  <i class="fa fa-angle-double-left"></i>
                </a>
              </li>
              <li id="prevPage" class="prev disabled">
                <a>
                  <i class="fa fa-angle-left"></i>
                </a>
              </li>

              <li id="nextPage" class="next">
                <a>
                  <i class="fa fa-angle-right"></i>
                </a>
              </li>

              <li id="endPage" class="next">
                <a>
                  <i class="fa fa-angle-double-right"></i>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div> <!-- row -->
    </div> <!-- sample-table -->

  </div> <!-- main-container -->
</div>

<%@ include file="/view/common/alertBox.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/admin/adminManage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/manage.js"></script>
</body>

</html>
