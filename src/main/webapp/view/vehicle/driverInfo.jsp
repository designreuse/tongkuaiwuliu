<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/26
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- 强制国内垃圾浏览器开启高速模式-->
  <meta name="renderer" content="webkit">
  <title>运输任务详情</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/staffForm.css" >

  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>

  <style type="text/css">
    .cTable{
      text-align: center;
      font-size: 1.2em;
    }
    .cTable th{
      text-align: center;
    }
    tr{
      border: 1px solid #438EB9;
      border-radius: 5px;
    }
    .taskTable td,.taskTable th{
      border: 1px solid #438EB9;
      border-radius: 5px;
    }
    #customerInfo label{
      background-color: #438EB9;
      border-bottom-left-radius: 5px;
      border-top-left-radius: 5px;
      color: white;
      height: 30px;
      padding-top:5px ;
    }
    #customerInfo span{
      border: 1px solid #438EB9;
      border-top-right-radius: 5px;
      border-bottom-right-radius: 5px;
      text-align: left;
      height: 30px;
      padding-top:5px ;
    }
  </style>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>

<body class="no-skin">

<!-- 主体部分 -->
<div class="main-container" id="main-container">

  <!-- navbar -->
  <div id="navbar">
    <div class="nav-brand">
      <i class="fa fa-leaf">&nbsp;&nbsp;驾驶员详情</i>
    </div>
  </div>
  <!-- navbar -->

  <div class="nav-list">
    <nav>
      <ul class="pager">
        <li class="previous"><a href="<%=request.getContextPath()%>/view/vehicle/VehicleManage.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
      </ul>
    </nav>
  </div>

  <!-- addForm -->
  <form class="addForm form-horizontal" role="form">
    <h2>驾驶员信息</h2>
    <hr style="border:1px solid #438EB9;border-radius:1px;">
    <% String sid=request.getParameter("sid"); %>
    <input id="sid" type="text" value="<%=sid%>" style="display:none;">

    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>驾驶员信息
    </div>
    <br><br>
    <div class="form-group">
      <%--<input id="customer" type="text" style="display: none">--%>
      <div id="customer" style="display: none"></div>
      <div id="customerInfo">
        <div class="row">
          <label class="col-sm-1 col-sm-offset-1">姓名：</label>
          <span class="col-sm-4 " id="dName"></span>
        </div>
        <div class="row">
          <label class="col-sm-1 col-sm-offset-1">性别：</label>
          <span class="col-sm-4 " id="dGender"></span>
        </div>
        <div class="row">
          <label class="col-sm-1 col-sm-offset-1">电话：</label>
          <span class="col-sm-4 " id="dPhone"></span>
        </div>
        <div class="row">
          <label class="col-sm-1 col-sm-offset-1">状态：</label>
          <span class="col-sm-4 " id="dState"></span>
        </div>
      </div>
    </div>


    <br><br><br><br>
  </form>


</div> <!-- main-container -->
<script src="<%=request.getContextPath()%>/js/staff/driverInfo.js"></script>
</body>

</html>
