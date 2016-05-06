<%--
  Created by IntelliJ IDEA.
  User: wuhaibin
  Date: 15/8/20
  Time: 下午3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <title>物资信息</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css" >
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>
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
                <small>
                    <i class="fa fa-leaf"></i>
                    痛快运输管理系统
                </small>
            </a>
            <!-- /section:basics/navbar.layout.brand -->

            <!-- #section:basics/navbar.toggle -->

            <!-- /section:basics/navbar.toggle -->
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
            <li class="previous"><a href="<%=request.getContextPath()%>/view/transTask/transTask.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
        </ul>
    </nav>
</div>
<!--
   1、基本功能：增加、删除、修改、查询车辆维修信息；
   2、包含内容：入厂日期、出厂日期、检修类型、维修单位、车牌号、材料费用、修理工；
-->
<div class="page-content">
    <br>
    <br>
    <!-- /.page-header -->
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="page-header ">
                <h1 class="text-center">
                    车辆维修记录管理 增加车辆维修信息.
                </h1>
            </div>
        </div>
    </div>
    <!-- /.page-header -->
    <br>
    <br>
    <!--表单开始-->
    <div>
        <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/vehicleType" method="post">
            <div class="form-group">
                <label class="col-sm-4 control-label">车型编号</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="ID" name="ID" placeholder="ID">
                </div>
            </div>
            <br>
            <br>
            <div class="form-group">
                <label class="col-sm-4 control-label">载货量</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="capacity" name="capacity" placeholder="capacity">
                </div>
            </div>
            <br>
            <br>
            <br>
            <div class="form-group">
                <label class="col-sm-4 control-label">车型描述</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="description" name="description" placeholder="description">
                </div>
            </div>
            <br>
            <br>
            <div class="form-group">
                <label class="col-sm-4 control-label">车型高度</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="height" name="height" placeholder="height">
                </div>
            </div>
            <br>
            <br>
            <div class="form-group">
                <label class="col-sm-4 control-label">车长</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="length" name="length" placeholder="length">
                </div>
            </div>
            <br>
            <br>
            <div class="form-group">
                <label class="col-sm-4 control-label">用油类型</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="oilType" name="oilType" placeholder="oilType">
                </div>
            </div>
            <br>
            <br>
            <br>
            <div class="form-group">
                <label class="col-sm-4 control-label">座位数</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="seat" name="seat" placeholder="seat">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">宽度</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="width" name="width" placeholder="width">
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col-sm-offset-6 col-sm-6">
                    <input type="submit" class="btn btn-default" value="提交">
                </div>
            </div>
        </form>
    </div>
    <!--表单结束 -->
</div>

</body>
</html>