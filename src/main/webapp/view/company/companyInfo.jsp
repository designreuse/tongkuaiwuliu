<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/29
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- 强制国内垃圾浏览器开启高速模式-->
  <meta name="renderer" content="webkit">
  <title>公司信息</title>
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
    #companyInfo label{
      background-color: #438EB9;
      border-bottom-left-radius: 5px;
      border-top-left-radius: 5px;
      color: white;
      height: 30px;
      padding-top:5px ;
    }
    #companyInfo span{
      border: 1px solid #438EB9;
      border-top-right-radius: 5px;
      border-bottom-right-radius: 5px;
      text-align: left;
      height: 30px;
      padding-top:5px ;
    }


    .cIntro .header{
      background-color: #438EB9;
      color: white;
      text-align: center;
      font-size: 1.2em;
      border-top-right-radius: 5px;
      border-top-left-radius: 5px;
    }

    .cIntro .content{
      margin-top: -18px;
      padding-top: 5px;
      border: 1px solid #438EB9;
      text-align: left;
      height: 200px;
      border-bottom-left-radius: 5px;
      border-bottom-right-radius: 5px;
    }
  </style>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>

<body class="no-skin">

<!-- 主体部分 -->
<div class="main-container" id="main-container">

  <!-- navbar -->
  <div id="navbar">
    <div class="nav-brand">
      <i class="fa fa-leaf">&nbsp;&nbsp;公司信息</i>
    </div>
  </div>
  <!-- navbar -->

  <div class="nav-list">
    <nav>
      <ul class="pager">
        <li class="previous"><a href="<%=request.getContextPath()%>/view/company/companyManage.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
      </ul>
    </nav>
  </div>

  <!-- addForm -->
  <form class="addForm form-horizontal" role="form">
    <h2>公司信息</h2>
    <hr style="border:1px solid #438EB9;border-radius:1px;">

    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>基本信息
    </div>
    <br>
    <div class="form-group">
      <%--<input id="company" type="text" style="display: none">--%>
      <div id="company" style="display: none"></div>
      <div id="companyInfo">
        <div class="row"><label class="col-sm-2 col-sm-offset-3">公司名称：</label><span class="col-sm-4 " id="cName">${company.companyName}</span></div>
        <div class="row"><label class="col-sm-2 col-sm-offset-3">联系电话：</label><span class="col-sm-4 " id="cPhone">${company.phoneNumber}</span></div>
        <div class="row"><label class="col-sm-2 col-sm-offset-3">法人代表：</label><span class="col-sm-4 " id="cPeople">${company.corporation}</span></div>
      </div>
        <div class="row cIntro">
          <div class="introBox">
            <div class="header col-sm-6 col-sm-offset-3">
              公司简介
            </div>
            <div class="content col-sm-6 col-sm-offset-3">${company.introduction}
            </div>
          </div>
        </div>
    </div>
    
    <br><br><br><br>
  </form>


</div> <!-- main-container -->
</body>

</html>
