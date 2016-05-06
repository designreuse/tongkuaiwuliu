<%--
  Created by IntelliJ IDEA.
  User: wuhaibin
  Date: 15/8/25
  Time: 上午9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>experiment_release</title>

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
                <span id="title">物资信息管理</span>
            </a>
            <!-- #section:basics/navbar.layout.brand -->
        </div>
        <!-- /section:basics/sidebar.mobile.toggle -->
    </div><!-- /.navbar-container -->
</div>
<%@ include file="/view/common/sidebar.jsp"%>

<div class="page-content">
    <br />
    <br />
    <br />
    <!-- /.page-header -->
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="page-header ">
                <h1 class="text-center">
                    填写子账号信息
                </h1>
            </div>
        </div>
    </div>

    <br />
    <br />
    <div>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-4 control-label">e-mail</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="email" placeholder="e-mail">
                </div>
            </div>
            <br />
            <div class="form-group">
                <label class="col-sm-4 control-label">密码</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="password" placeholder="password">
                </div>
                <%--<div class="error" id="passError"></div>--%>
            </div>
            <br/>
            <br/>
            <br/>
            <br/>
            <div class="form-group">
                <div class="col-sm-offset-5 col-sm-6">
                    <input id = "login" type="submit" class="btn btn-login" value="登陆">
                    <input id = "register" type="submit" class="btn btn-Register" value="注册">
                </div>
            </div>
        </form>
    </div>
    <!--表单结束 -->

</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/company/UserLogin.js"></script>
<script type="text/javascript">
    $("#login").click(userLogin);
//    var div = document.createElement('div');
//    div.className = 'errorMessage';
//    div.innerHTML = "您的密码错误";
//    var group = document.getElementById('form-group');
//    group.appendChild(div);
</script>
</body>
</html>