<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/9/5
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- 强制国内垃圾浏览器开启高速模式-->
  <meta name="renderer" content="webkit">
  <title>痛快物流</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css" >
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>

  <style>
    #info{
      text-align: center;
      margin-top: 50px;
      font-size: 120%;
      color: #438EB9;
    }
  </style>
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
        <span id="title">痛快物流系统</span>
      </a>
      <!-- #section:basics/navbar.layout.brand -->
    </div>
    <%@include file="/view/common/adminAccountTag.jsp"%>
  </div><!-- /.navbar-container -->
</div>
<!-- 导航栏结束 -->

<!-- 主体部分 -->
<div class="main-container" id="main-container">
  <%@ include file="/view/common/sidebar.jsp"%>
  <div id="info">Powered by hnndjavaee10</div>
</div>
</body>

</html>


