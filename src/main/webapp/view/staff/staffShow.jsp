<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/23
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- 强制国内垃圾浏览器开启高速模式-->
  <meta name="renderer" content="webkit">
  <title>人员信息</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/staffForm.css" >

  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>

<body class="no-skin">



<!-- 主体部分 -->
<div class="main-container" id="main-container">

  <!-- navbar -->
  <div id="navbar">
    <div class="nav-brand">
      <i class="fa fa-leaf">&nbsp;&nbsp;人员信息</i>
    </div>
  </div>
  <!-- navbar -->

  <div class="nav-list">
    <nav>
      <ul class="pager">
        <li class="previous"><a onclick="javascript:history.go(-1)" style="cursor: pointer"><span aria-hidden="true">&larr;</span>返回</a></li>
      </ul>
    </nav>
  </div>



  <!-- addForm -->
  <div class="addForm form-horizontal" role="form">
    <h2>员工信息</h2>
    <hr style="border:1px solid #438EB9;border-radius:1px;" color="#438EB9">
    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>头像
    </div>
    <div class="form-group">
        <img width="150px" height="200px" src="../image/get?staffId=${staff.id}"/>
    </div>
    <br>
    <br>
    <br>
    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>基本信息
    </div>
    <br>



  <div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">基本信息</div>
    <table class="table">
      <tr>
        <td>联系人</td>
        <td id="cont_name">${staff.realName}</td>
        <td>电话</td>
        <td >${staff.phoneNumber}</td>
      </tr>
      <tr>
        <td>性别</td>
        <td id="gender">${staff.gender}</td>
        <td>身份证</td>
        <td id="idcard">${staff.idCardNumber}</td>
      </tr>
      <tr>
        <td>政治面貌</td>
        <td id="email">${staff.politicalGroup}</td>
        <td>教育水平</td>
        <td id="">${staff.levelOfEducation}</td>
      </tr>
    </table>
  </div>

    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>职员空间
    </div>
    <br>
    <div class="panel panel-default">
      <!-- Default panel contents -->
      <div class="panel-heading">职员空间</div>
      <table class="table">
        <tr>
          <td>职位</td>
          <td id="job">${staff.job}</td>
          <td>状态</td>
          <td >${staff.state}</td>
        </tr>
        <tr>
          <td>月薪</td>
          <td id="salary">${staff.salary}</td>
          <td>入职时间</td>

          <td id="dateOfHire"></td>
        </tr>
      </table>
    </div>
    <br><br><br><br>

</div> <!-- main-container -->
</div>
</body>
<script src="<%=request.getContextPath()%>/js/dateFormat.js"></script>
<script>
  $(document).ready(function () {
    var longDate = ${staff.dateOfHire};
    $("#dateOfHire").text(getDateStr(longDate));
  });
</script>


</html>