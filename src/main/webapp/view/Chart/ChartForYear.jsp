<%--
  Created by IntelliJ IDEA.
  User: wuhaibin
  Date: 15/8/21
  Time: 上午9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <title>公司收入一览图</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css" >
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ichart.latest.min.js"></script>
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
                <span id="title">公司收入</span>
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
</div>
<div class="page-content">

    <!-- /.page-header -->
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="page-header ">
                <h1 class="text-center">
                    选择查看年月
                </h1>
            </div>
        </div>
    </div>
    <!-- /.page-header -->

    <!--表单开始-->
    <div>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-4 control-label">选择年份</label>
                <div class="col-sm-2" align="center">
                    <select id="Year" class="form-control">
                        <option value="2013">2013</option>
                        <option value="2014">2014</option>
                        <option value="2015">2015</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-6 col-sm-6">
                    <input id="submit" class="btn btn-default" value="提交">
                </div>
            </div>
        </form>
    </div>
</div>
<div id='ichart-render-T'></div>
<div id='ichart-render-O'></div>
<div id='ichart-render-B'></div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/report/TChartForYear.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/ichart.latest.min.js"></script>

</body>

</html>