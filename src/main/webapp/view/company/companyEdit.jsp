<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/29
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="renderer" content="webkit">
  <title>修改公司信息</title>
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
      <li class="previous fa-1x"><a href="<%=request.getContextPath()%>/view/company/companyManage.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
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
          公司信息
        </h1>
      </div>
    </div>
  </div>
  <!-- /.page-header -->
  <br>
  <br>
  <!--表单开始-->
  <div>
    <form class="form-horizontal" role="form"  action="<%=request.getContextPath()%>/company" method="post">

      <br>
      <br>
      <input id="id" type="text" value="${company.id}" style="display:none;">
      <div class="form-group">
        <label class="col-sm-4 control-label">公司名称</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="companyName" name="companyName" value="${company.companyName}">
        </div>
      </div>
      <br>
      <br>
      <br>
      <div class="form-group">
        <label class="col-sm-4 control-label">联系电话</label>
        <div class="col-sm-4">
          <input type="number" class="form-control" id="phoneNumber" name="phoneNumber" value="${company.phoneNumber}">
        </div>
      </div>
      <br>
      <br>
      <div class="form-group">
        <label class="col-sm-4 control-label">法人代表</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="corporation" name="corporation" value="${company.corporation}">
        </div>
      </div>
      <br>
      <br>
      <div class="form-group">
        <label class="col-sm-4 control-label">公司介绍</label>
        <div class="col-sm-4">
          <textArea name="introduction" id="introduction" class="form-control" style="resize: none;height: 180px;">${company.introduction}</textArea>
        </div>
      </div>
      <br>
      <br>
      <div class="form-group">
        <div class="col-sm-offset-5 col-sm-2">
          <%--<input type="submit" class="btn btn-primary" value="添加">--%>
          <input id="saveEdit" type="button" class="btn btn-primary" value="保存">
          <a class="btn btn-danger" href=contextPath+"/view/company/companyManage.jsp">取消</a>
        </div>
      </div>
    </form>
  </div>

</div>

<script type="text/javascript">
  $(document).ready(function () {
    $("#saveEdit").click(function () {
      var id  = $("#id").val();
      var name= $("#companyName").val();
      var phone=$("#phoneNumber").val();
      var cor = $("#corporation").val();
      var intro=$("#introduction").val();
      var record = {"id":id,"companyName":name,"phoneNumber":phone,"corporation":cor,"introduction":intro,"_method":"put"};
      $.post(contextPath+"/company?json",record, function () {
        location.href=contextPath+"/view/company/companyManage.jsp";
      });
    });
  });
</script>
</body>

</html>
