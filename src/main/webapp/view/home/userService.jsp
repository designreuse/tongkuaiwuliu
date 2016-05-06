<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/31
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
  <title>网络服务</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/userService.css">

  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/AreaData_min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/Area.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/home/userService.js"></script>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>


<body>
<div class="navbar">
  <div class="row navHeader">
    <div class="headText">
      <a href="mainPage.jsp">
        <i id="headIcon" class="fa fa-leaf"></i>&nbsp;&nbsp;&nbsp;
        <span id="headText">痛快物流</span>
      </a>
    </div>
    <%@include file="/view/common/accountTag.jsp"%>
  </div>
</div>  <!-- /.navbar -->

<div class="mainContainer">
  <ul class="mainMenu">
    <li class="express">
      <a><i class="fa fa-archive fa-2x"></i><p>寄货物</p></a>
    </li>
    <li class="myOrder">
      <a><i class="fa fa-file-text fa-2x"></i><p>我的订单</p></a>
    </li>
    <li class="user">
      <a><i class="fa fa-user fa-2x"></i><p>帐号管理</p></a>
    </li>
  </ul>  <!-- /.mainMenu -->

  <div class="expressBox">
    <div class="taskBox" style="display:none;">
      <div class="subHeader">
        <i class="fa fa-minus fa-rotate-90"></i>
        <label>运单标题</label>
      </div>  <!-- /.subHeader -->
      <div class="row content">
        <label class="col-md-1" style="margin-left: 15px;">标题：</label><input id="title" class="col-md-4" type="text" placeholder="运单标题"/>
      </div>
      <div class="subHeader">
        <i class="fa fa-minus fa-rotate-90"></i>
        <label>地址</label>
      </div>  <!-- /.subHeader -->
      <div class="content">
        <div class="address">
          <label>起送地：</label>
          <select id="seachprov" name="seachprov" onChange="changeComplexProvince(this.value, sub_array, 'seachcity', 'seachdistrict');"></select>&nbsp;&nbsp;
          <select id="seachcity" name="homecity" onChange="changeCity(this.value,'seachdistrict','seachdistrict');"></select>&nbsp;&nbsp;
						<span id="seachdistrict_div">
						<select id="seachdistrict" name="seachdistrict"></select>
						</span>
          <input id="startPlace" type="text" placeholder="详细地址" >
        </div>
        <div class="address">
          <label>目的地：</label>
          <select id="seachprov2" name="seachprov2" onChange="changeComplexProvince(this.value, sub_array, 'seachcity2', 'seachdistrict2');"></select>&nbsp;&nbsp;
          <select id="seachcity2" name="homecity2" onChange="changeCity(this.value,'seachdistrict2','seachdistrict2');"></select>&nbsp;&nbsp;
						<span id="seachdistrict2_div">
						<select id="seachdistrict2" name="seachdistrict2"></select>
						</span>
          <input id="endPlace" type="text" placeholder="详细地址" >
        </div>
      </div>  <!-- /.content -->

      <div class="subHeader">
        <i class="fa fa-minus fa-rotate-90"></i>
        <label>预约时间</label>
      </div>  <!-- /.subHeader -->
      <div class="content">
        <label>起送时间：</label><input id="startDate" type="date" placeholder="">
        <label>送达时间：</label><input id="endDate" type="date" placeholder="">
      </div>  <!-- /.content -->


      <%--<div class="subHeader">--%>
        <%--<i class="fa fa-minus fa-rotate-90"></i>--%>
        <%--<label>个人信息</label>--%>
      <%--</div>  <!-- /.subHeader -->--%>
      <%--<div class="content">--%>
        <%--<label>姓名：</label><input id="realName" type="text" placeholder="真实姓名">--%>
        <%--<label>电话：</label><input id="phone" type="text" placeholder="电话号码">--%>
        <%--<label>邮箱：</label><input id="email" type="text" placeholder="邮箱地址">--%>
      <%--</div>  <!-- /.content -->--%>

      <div class="subHeader">
        <i class="fa fa-minus fa-rotate-90"></i>
        <label>货物详情</label>
      </div>  <!-- /.subHeader -->

      <div class="content cargoContent">
        <div class="cargo">
          <div class="subCargo">
            <label>名称：</label><input class="cargoName" type="text" placeholder="货物名称">
            <label>重量：</label><input class="cargoWeight" type="text" placeholder="货物重量 (吨)">
            <label>数量：</label><input class="cargoCount" type="text" placeholder="货物数量">
            <label class="action-buttons">
              <a id="addCargo" class="green"><i class="ace-icon fa fa-plus bigger-120"></i></a>
            </label>
          </div>
        </div>	 <!-- /.cargo -->
        <div id="hideCargo" style="display: none;">
          <div class="cargoList">
            <label>名称：</label><input class="cargoName" type="text" placeholder="货物名称">
            <label>重量：</label><input class="cargoWeight" type="text" placeholder="货物重量 (吨)">
            <label>数量：</label><input class="cargoCount" type="text" placeholder="货物数量">
            <label class="action-buttons">
              <a id="deleteCargo" class="red deleteCargo"><i class="ace-icon fa fa-minus bigger-120"></i></a>
            </label>
          </div>
        </div>
        <hr>
        <div class="btnArea">
          <button id="addTask" class="btn btn-primary btn-sm">确定</button>
        </div>
      </div>  <!-- /.content -->
    </div>
  </div>  <!-- /.expressBox -->


  <%--<div class="searchBox" style="display:none;">--%>
    <%--<div class="searchContent">--%>
      <%--<input type="text" placeholder="运单号"><i class="fa fa-search"></i>--%>
    <%--</div>--%>
    <%--<div class="searchResult">--%>

    <%--</div>--%>
  <%--</div> <!-- /.searchBox -->--%>

  <div class="myOrderBox" style="display:none;">
  </div> <!-- /.myOrderBox -->
  <div class="hideOrderBox" style="display: none;">
    <div class="subOrderBox" >
      <br>
      <div class="content">
        <i class="fa fa-file-text"></i><span id="queryId"></span>&nbsp;&nbsp;
        <br>
        <span id="create_time" style="margin-left: 45px;"></span>
        <br><br>
        <div class="taskInfo">
          <label>运单：</label><span id="orderTitle"></span>
          <br>
          <label>起运地：</label><span id="orderStartPlace"></span>
          <br>
          <label>目的地：</label><span id="orderEndPlace"></span>
          <br>
          <label>状态：</label><span id="orderState"></span>
        </div>
        <div class="cargoInfo">
          <i class="fa fa-truck"></i><span>货物详情</span>
          <div class="cargos row">
            <br>
            <label class="col-sm-2">名称</label><label class="col-sm-2">重量</label><label class="col-sm-2">数量</label>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="userBox" style="display:none;">
    <div class="subHeader">
      <i class="fa fa-minus fa-rotate-90"></i>
      <label>我的帐号</label>
    </div>  <!-- /.subHeader -->

    <div class="content userContent">
      <div class="row">
        <label class="col-md-2">帐号：</label>
        <input id="userName" class="col-md-4" type="text" placeholder="帐号名称">
      </div>
      <br>
      <div class="row">
        <label class="col-md-2">邮箱：</label>
        <input id="userEmail" class="col-md-4" type="text" placeholder="邮箱">
      </div>
      <br>
      <div class="row">
        <label class="col-md-2">电话：</label>
        <input id="userPhone" class="col-md-4" type="text" placeholder="电话">
      </div>
      <br>

      <div class="subHeader">
        <i class="fa fa-minus fa-rotate-90"></i>
        <span>常用地址</span>
      </div>  <!-- /.subHeader -->
      <div class="row">
        <br>
        <label class="col-md-2">地址：</label>
        <div class="col-md-6" style="margin-left:-10px;">
          <select id="seachprov3" name="seachprov3" onChange="changeComplexProvince(this.value, sub_array, 'seachcity3', 'seachdistrict3');"></select>&nbsp;&nbsp;
          <select id="seachcity3" name="homecity3" onChange="changeCity(this.value,'seachdistrict3','seachdistrict3');"></select>&nbsp;&nbsp;
						<span id="seachdistrict3_div">
						<select id="seachdistrict3" name="seachdistrict3"></select>
						</span>
        </div>
        <br><br>
        <input id="detailAddress" class="col-md-8 col-md-offset-2" type="text" placeholder="详细地址">
      </div>
      <hr>
      <div class="btnArea">
        <button id="saveUserInfo" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div> <!-- /.userBox -->
  <div class="passwordBox" style="display:none;">
    <div class="subHeader">
      <i class="fa fa-minus fa-rotate-90"></i>
      <span>修改密码</span>
    </div>  <!-- /.subHeader -->
    <br>
    <label id="pswTip" style="color: red;margin-left: 45px;margin-bottom: 20px;"></label>
    <br>
    <div class="row">
      <i class="red col-md-2" style="text-align:right;">*</i><input id="oldPassword" class="col-md-6" type="text" placeholder="旧密码">
    </div>
    <br>
    <div class="row">
      <input id="newPassword" class="col-md-6 col-md-offset-2" type="password" placeholder="新密码">
    </div>
    <br>
    <div class="row">
      <input id="newPasswords" class="col-md-6 col-md-offset-2" type="password" placeholder="确认密码">
    </div>
    <br><br>
    <hr>
    <div class="btnArea">
      <button id="savePassword" class="btn btn-primary btn-sm">保存</button>
    </div>
  </div>

  <div class="alertBox" style="display: none;">
    <div class="alertHeader">提示</div>
    <div class="alertContent">

    </div>
  </div> <!-- /.searchBox -->

</div>     <!-- /.MainContainer -->


<div class="siteFooter">
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-sm-8 col-xs-12">
            <p>Copyright &copy 2015 hnndjavaee10</p>
          </div>
          <div class="col-md-4 col-sm-4 col-xs-12">
            <div class="go-top">
              <a href="#" id="go-top">
                <i class="fa fa-angle-up"></i>
                返回顶部
              </a>
            </div>
          </div>
    </div> <!-- /.row -->
  </div> <!-- /.container -->
</div> <!-- /.site-footer -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dateFormat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/home/userAccount.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/home/addTask.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/home/checkMyOrder.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/home/searchTask.js"></script>

</body>
</html>