<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/9/5
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <title>修改密码</title>
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
          修改密码
        </h1>
      </div>
    </div>
  </div>
  <!-- /.page-header -->
  <br>
  <br>
  <!--表单开始-->
  <div>
    <form class="form-horizontal" role="form">

      <br>
      <div class="form-group">
        <label class="col-sm-4 control-label">名称</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="adminName" name="adminName">
        </div>
      </div>
      <br>
      <div class="form-group">
        <label class="col-sm-4 control-label">帐号类型</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="type" disabled="disabled">
        </div>
      </div>
      <br>
      <div class="form-group">
        <label class="col-sm-4 control-label">旧密码</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="oldPass" name="oldPass" placeholder="旧密码">
        </div>
      </div>
      <br>
      <div class="form-group">
        <label class="col-sm-4 control-label">新密码</label>
        <div class="col-sm-4">
          <input type="password" class="form-control" id="newPass" name="newPass" placeholder="新密码">
        </div>
      </div>
      <br>
      <div class="form-group">
        <label class="col-sm-4 control-label">确认密码</label>
        <div class="col-sm-4">
          <input type="password" class="form-control" id="againPassword" placeholder="重复新密码">
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-5 col-sm-2">
          <%--<input type="submit" class="btn btn-primary" value="添加">--%>
          <input id="savePassword" type="button" class="btn btn-primary" value="确认">
          <a class="btn btn-danger" href=contextPath+"/view/common/start.jsp">取消</a>
        </div>
      </div>
    </form>
  </div>

</div>

<script type="text/javascript">
  $(document).ready(function () {
    showAdmin();
    function showAdmin(){
      $.ajax({
        url:contextPath+"/admin?json",
        type:"get",
        async:false,
        success: function (data) {
          var admin=data;
          $("#adminName").val(admin.adminName);
          $("#type").val(getType(admin.type));
        }
      });
    }

    $("#savePassword").click(function () {
      var adminName = $("#adminName").val();
      var oldPass   = $("#oldPass").val();
      var newPass   = $("#newPass").val();
      var record = {"adminName":adminName,"oldPass":oldPass,"newPass":newPass};

      if(oldPass.replace(/\s/g,"")==''){
        alert("旧密码不能为空");
      }
      else if(newPass!=$("#againPassword").val()){
        alert("新密码两次输入不一致");
      }else{

        $.ajax({
          url:contextPath+"/admin/passwd",
          type:"post",
          data:record,
          success: function (data) {
            if(data>0){
              location.href=contextPath+"/view/company/about.jsp";
            }else{
              alert("旧密码不正确!");
            }
          }
        });
      }


    });


    function getType(value){
      switch (value){
        case "1":
          return "超级管理员";
        case "2":
          return "物资管理员";
        case "3":
          return "运输管理员";
        case "4":
          return "车辆管理员";
        case "5":
          return "人力资源管理员";
        case "6":
          return "公司信息管理员";
        case "7":
          return "财务管理员";
      }
    }
  });
</script>
</body>

</html>
