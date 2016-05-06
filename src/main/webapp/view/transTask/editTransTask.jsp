<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/24
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- 强制国内垃圾浏览器开启高速模式-->
  <meta name="renderer" content="webkit">
  <title>修改运输任务</title>
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
      <i class="fa fa-leaf">&nbsp;&nbsp;修改运输任务</i>
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
    <h2>修改运输任务单</h2>
    <hr style="border:1px solid #438EB9;border-radius:1px;" color="#438EB9">
    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>承运信息
    </div>
    <br>
    <% String sid=request.getParameter("sid");
      String uid=request.getParameter("uid");
    %>
    <input id="sid" type="text" value="<%=sid%>" style="display:none;">
    <input id="uid" type="text" value="<%=uid%>" style="display:none;">
    <input id="cid" type="text" style="display: none;">
    <div class="form-group">
      <label class="col-sm-2 control-label">承运利率</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="rate" placeholder="承运利率">
      </div>
      <label class="col-sm-2 control-label">承运类型</label>
      <div class="col-sm-1">
        <select id="type">
          <option value="0">自营</option>
          <option value="1">承运</option>
        </select>
      </div>
      <input id="startPlace" type="text" style="display: none">
      <input id="endPlace" type="text" style="display: none">
      <input id="title" type="text" style="display: none">
      <input id="state" type="text" style="display: none">
      <input id="taskStartDate" type="text" style="display: none">
      <input id="taskEndDate" type="text" style="display: none">
      <input id="cargoSet" type="text" style="display: none">
    </div>

    <div class="form-group">
      <label class="col-sm-2 control-label">选择承运商</label>
      <div class="col-sm-1">
        <select id="company">
          <option value="">无</option>
        </select>
      </div>
      <label class="col-sm-2 col-sm-offset-3 control-label">是否支付</label>
      <div class="col-sm-1">
        <select id="isPayed">
          <option value="">无</option>
        </select>
      </div>
    </div>

    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>运单信息
    </div>
    <br>
    <input id="queryId" type="text" style="display: none">
    <div class="form-group">
      <label class="col-sm-2 control-label">运单状态</label>
      <select id="taskState" class="col-sm-2">
        <option value=0>申请中</option>
        <option value=1>进行中</option>
        <option value=2>已完成</option>
      </select>
      <label class="col-sm-2 col-sm-offset-2 control-label">总价格(元)</label>
      <input id="sumPrice" class="col-sm-2" type="number">
    </div>

    <div id="carBusinessForm" style="display:none;">
      <div class="subHeader">
        <i class="fa  fa-minus fa-rotate-90"></i>选择车辆
      </div>
      <br>
      <input id="carBusinessId" style="display: none;">
      <div class="form-group">
        <label class="col-sm-2">车辆</label><select id="selectVehicle" class="col-sm-2"></select>
      </div>
      <div class="form-group">
        <label class="col-sm-2">开始时间</label><input id="startDate" class="col-sm-3" type="date" style="height: 35px;"/>
        <label class="col-sm-2">结束时间</label><input id="endDate"   class="col-sm-3" type="date" style="height: 35px;"/>
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

    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>货物详情
    </div>
    <br>

    <div class="form-group">
      <table class="cTable col-sm-8 col-sm-offset-2">
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
    <div class="btnArea">
      <button class="btn btn-primary" id="saveEdit">确认</button>
      <a class="btn btn-danger" id="cancal" href="transTask.jsp">取消</a>
    </div>
    <br><br><br><br>
  </form>


</div> <!-- main-container -->
<script src="<%=request.getContextPath()%>/js/dateFormat.js"></script>
<script src="<%=request.getContextPath()%>/js/transTask/editTransTask.js"></script>
</body>

</html>
