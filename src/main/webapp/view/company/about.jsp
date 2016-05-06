<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/9/5
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <title>关于</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<style>
  span,a,p,div{
    font-family: "Microsoft YaHei", sans-serif ! important;
  }

  h1, h2, h3, h4, h5, h6, #title span {
    font-family: "Microsoft YaHei", sans-serif ! important;
    font-weight: normal;
  }

  label{
    font-family: "Microsoft YaHei", sans-serif ! important;
    font-size: large;
    font-weight: bold;
  }

  #aboutInfo{
    padding-left: 500px;
    font-size: large;
    font-weight: bold;
    box-shadow: 0px 10px 5px #888888;
  }

  #aboutInfo label{
    margin-left: 85px;
  }

  #aboutInfo nobr{
    margin-left: 75px;
  }
</style>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>
<body class="no-skin">
<!--导航栏开始-->
<div id="navbar" class="navbar navbar-default">
  <script type="text/javascript">
    try{ace.settings.check('navbar' , 'fixed')}catch(e){}
  </script>

  <div class="navbar-container" id="navbar-container">
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
        <span id="title">
          <i class="fa fa-leaf"></i>
          痛快运输管理系统
        </span>
      </a>
    </div>
    </ul>
  </div>
  <!-- /section:basics/navbar.dropdown -->
</div><!-- /.navbar-container -->
</div>
<!--导航栏结束-->

<div class="nav-list">
  <nav>
    <ul class="pager">
      <li class="previous fa-1x"><a href="<%=request.getContextPath()%>/view/common/start.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
    </ul>
  </nav>
</div>


<div class="page-content">
  <br>
  <br>
  <!-- /.page-header -->
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="page-header ">
        <h1 class="text-center">
          关于
        </h1>
      </div>
    </div>
  </div>
  <!-- /.page-header -->
  <br>
  <br>
  <div id="aboutInfo" class="well well-lg">
    <label>members:</label>
    <br>
    项目经理、后台开发：尹彬
    <br>
    配置管理、后台开发：刘金云
    <br>
    系统架构、前端开发：秦静蜜
    <br>
    前端开发： <label>李文斌</label>
    <br>
    系统测试： <label>谢连军</label>
    <br>
    数据库设计：<nobr>金秀通</nobr>
    <br><br><br><br><br><br><br><br><br><br><br><br>
    <span>Powered by hnndjavaee10</span>
  </div>
</div>
</body>

</html>