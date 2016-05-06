<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/24
  Time: 14:41
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
      max-width: 600px;
      text-align: center;
      margin-left: 80px;
    }
    .cTable td{
      max-width: 20px;
      text-align: left;
    }

    tr{
      border: 1px solid #438EB9;
      border-radius: 5px;
    }

    .taskTable td,.taskTable th{
      border: 1px solid #438EB9;
      border-radius: 5px;
    }

    .table td{
      max-width: 80px;
    }
    #customerInfo label,#carInfo label{
      background-color: #438EB9;
      border-bottom-left-radius: 5px;
      border-top-left-radius: 5px;
      color: white;
      height: 30px;width:95px;
      padding-top:5px ;
    }
    #customerInfo span,#carrierInfo span,#carInfo span{
      border: 1px solid #438EB9;
      border-top-right-radius: 5px;
      border-bottom-right-radius: 5px;
      text-align: left;
      height: 30px;
      padding-top:5px ;
    }

    #carrierInfo label{
      background-color: #438EB9;
      border-bottom-left-radius: 5px;
      border-top-left-radius: 5px;
      color: white;
      height: 30px;width:95px;
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
      <i class="fa fa-leaf">&nbsp;&nbsp;运输任务详情</i>
    </div>
  </div>
  <!-- navbar -->

  <div class="nav-list">
    <nav>
      <ul class="pager">
        <li class="previous"><a href="<%=request.getContextPath()%>/view/transTask/transTask.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
      </ul>
    </nav>
  </div>

  <!-- addForm -->
  <form class="addForm form-horizontal" role="form">
    <h2>运输任务</h2>
    <hr style="border:1px solid #438EB9;border-radius:1px;">
    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>运单信息
    </div>
    <br>
    <% String sid=request.getParameter("sid");
       String uid=request.getParameter("uid");
    %>
    <input id="sid" type="text" value="<%=sid%>" style="display:none;">
    <input id="uid" type="text" value="<%=uid%>" style="display:none;">
    <div class="form-group">
      <table class="table table-striped table-bordered col-sm-12">
        <tbody class="taskTbody">
        <tr>
          <th>名称</th>
          <th>起运地</th>
          <th>止运地</th>
          <th>下单时间</th>
          <th>完成时间</th>
          <th>状态</th>
          <th>承运类型</th>
          <th>承运利率</th>
          <th>总价</th>
        </tr>
        <tr>
          <td id="title"></td>
          <td id="startPlace"></td>
          <td id="endPlace"></td>
          <td id="startDate"></td>
          <td id="endDate"></td>
          <td id="state"></td>
          <td id="type"></td>
          <input id="typeVal" type="text" style="display: none;">
          <td id="rate"></td>
          <td id="sumPrice"></td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="carrierBox" style="display: none;">
      <div class="subHeader">
        <i class="fa  fa-minus fa-rotate-90"></i>承运信息
      </div>
      <br>
      <div class="form-group">
        <div id="carrierInfo">
          <div class="row"><label class="col-sm-4 col-sm-offset-2">承运商：</label><span class="col-sm-4 " id="company"></span></div>
          <div class="row"><label class="col-sm-4 col-sm-offset-2">承运利率：</label><span class="col-sm-4 " id="cRate"></span></div>
          <div class="row"><label class="col-sm-4 col-sm-offset-2">是否支付：</label><span class="col-sm-4 " id="isPayed"></span></div>
        </div>
      </div>
    </div>

    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>下单用户
    </div>
    <br>
    <div class="form-group">
      <%--<input id="customer" type="text" style="display: none">--%>
      <div id="customer" style="display: none"></div>
      <div id="customerInfo">
        <div class="row"><label class="col-sm-1 col-sm-offset-2">姓名：</label><span class="col-sm-4 " id="ctmName"></span></div>
        <div class="row"><label class="col-sm-1 col-sm-offset-2">电话：</label><span class="col-sm-4 " id="ctmPhone"></span></div>
        <div class="row"><label class="col-sm-1 col-sm-offset-2">邮箱：</label><span class="col-sm-4 " id="ctmEmail"></span></div>
        <div class="row"><label class="col-sm-1 col-sm-offset-2">地址：</label><span class="col-sm-4 " id="ctmAddress"></span></div>
      </div>
    </div>

    <div class="carInfo" style="display: none;">
      <div class="subHeader">
        <i class="fa  fa-minus fa-rotate-90"></i>车辆信息
      </div>
      <br>
      <div class="form-group">
        <div id="carInfo">
          <div class="row"><label class="col-sm-1 col-sm-offset-2">驾驶员：</label><span class="col-sm-4 " id="driver"></span></div>
          <div class="row"><label class="col-sm-1 col-sm-offset-2">车牌号：</label><span class="col-sm-4 " id="carNumber"></span></div>
          <div class="row"><label class="col-sm-1 col-sm-offset-2">颜色：</label><span class="col-sm-4 " id="color"></span></div>
        </div>
      </div>
    </div>


    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>货物详情
    </div>
    <br>

    <div class="form-group">
      <table class="table table-striped table-bordered cTable">
        <tbody class="mainTbody">
        <tr>
          <th>名称</th>
          <th>重量</th>
          <th>单价</th>
          <th>数量</th>
        </tr>
        </tbody>
        <tbody class="hidedTbody" style="display: none;">
        <tr>
          <td class="cName"></td>
          <td class="cWeight"></td>
          <td class="cPrice"></td>
          <td class="cCount"></td>
        </tr>
        </tbody>
      </table>
    </div>
    <br><br><br><br>
  </form>


</div> <!-- main-container -->
<script src="<%=request.getContextPath()%>/js/dateFormat.js"></script>
<script src="<%=request.getContextPath()%>/js/transTask/detailTransTask.js"></script>
</body>

</html>
