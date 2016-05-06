<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/27
  Time: 23:05
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
      <i class="fa fa-leaf">&nbsp;&nbsp;运输任务详情</i>
    </div>
  </div>
  <!-- navbar -->

  <div class="nav-list">
    <nav>
      <ul class="pager">
        <li class="previous"><a href="<%=request.getContextPath()%>/view/logistics/carBussiness.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
      </ul>
    </nav>
  </div>

  <!-- addForm -->
  <form class="addForm form-horizontal" role="form">
    <h2>运输任务</h2>
    <hr style="border:1px solid #438EB9;border-radius:1px;">
    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>承运信息
    </div>
    <br>
    <% String sid=request.getParameter("sid");
      String uid=request.getParameter("uid");
    %>
    <input id="sid" type="text" value="<%=sid%>" style="display:none;">
    <input id="uid" type="text" value="<%=uid%>" style="display:none;">
    <div class="form-group">
      <table class="taskTable col-sm-10 col-sm-offset-1">
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
          <td id="rate"></td>
          <td id="sumPrice"></td>
        </tr>
        </tbody>
      </table>
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
    <br><br><br><br>
  </form>


</div> <!-- main-container -->
<script type="text/javascript">
  $(document).ready(function() {
    showOrg();

    var hideTr = $(".hidedTbody").children().first();
    var orgTr = hideTr.clone().show();
    function showOrg(){
      var taskId              = $("#sid").val();
      var userId;

      $.ajax({
        url:contextPath+"/transportTask/"+taskId+"?json",
        type:"get",
        async:false,
        success: function (data) {
          var task = data;

          $("#title").text(task.title);
          $("#startPlace").text(task.startPlace);
          $("#endPlace").text(task.endPlace);
          $("#startDate").text(task.startDate);
          $("#endDate").text(task.endDate);
          $("#state").text(task.state);
          $("#type").text(task.type);
          $("#rate").text(task.rate);
          $("#sumPrice").text(task.sumPrice);
          userId=task.customer;
        }
      });



      $.get(contextPath+"/user/"+userId, function (data) {
        $("#ctmName").text(data.name);
        //$("#ctmPhone").text(data.phone);
        $("#ctmEmail").text(data.email);
        $("#ctmAddress").text(data.address);
      });

      $.get(contextPath+"/cargo?json", function (data) {
        var cargoList=$(data.cargoList);
        cargoList.each(function (index,cargo) {
          console.log(cargo);
          var newTr = orgTr.clone();
          $(".cName",newTr).text(cargo.name);
          $(".cWeight",newTr).text(cargo.weight);
          $(".cPrice",newTr).text(cargo.price);
          $(".cCount",newTr).text(cargo.count);

          $(".mainTbody").append(newTr);
        });
      });
    }

  });
</script>
</body>

</html>

