<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/27
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- 强制国内垃圾浏览器开启高速模式-->

  <meta name="renderer" content="webkit">
  <title>员工详情</title>
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
      <i class="fa fa-leaf">&nbsp;&nbsp;员工详情</i>
    </div>
  </div>
  <!-- navbar -->

  <div class="nav-list">
    <nav>
      <ul class="pager">
        <li class="previous"><a href="<%=request.getContextPath()%>/view/oilBuy/OilBuyManage.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
      </ul>
    </nav>
  </div>

  <!-- addForm -->
  <form class="addForm form-horizontal" role="form">
    <h2>员工信息</h2>
    <hr style="border:1px solid #438EB9;border-radius:1px;" color="#438EB9">
    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>基本信息
    </div>
    <br>
    <% String sid=request.getParameter("sid"); %>
    <input id="sid" type="text" value="<%=sid%>" style="display:none;">
    <div class="form-group">
      <label class="col-sm-2 control-label"><i class="red" style="font-size:1.2em;">*</i>&nbsp;姓名</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="realName" placeholder="姓名" disabled="disabled">
      </div>
      <label class="col-sm-2 control-label" aria-disabled="true">性别</label>
      <div class="col-sm-1">
        <select name="" id="gender" disabled="disabled">
          <option value="男">男</option>
          <option value="女" selected="selected">女</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label"><i class="red" style="font-size:1.2em;">*</i>&nbsp;身份证号</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="idCardNumber" placeholder="身份证号" disabled="disabled">
      </div>
      <label class="col-sm-2 control-label">生日</label>
      <div class="col-sm-4">
        <input type="date" class="form-control" id="dateOfBirth" disabled="disabled">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">姓名简称</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="pinyin" placeholder="姓名简称" disabled="disabled">
      </div>
      <label class="col-sm-2 control-label">电话号码</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="phoneNumber" placeholder="电话号码" disabled="disabled">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">政治面貌</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="politicalGroup" placeholder="政治面貌" disabled="disabled">
      </div>
      <label class="col-sm-2 control-label">教育水平</label>
      <div class="col-sm-1">
        <select id="levelOfEducation" disabled="disabled">
          <option value="高中以下">高中以下</option>
          <option value="高中">高中</option>
          <option value="中专">中专</option>
          <option value="大专">大专</option>
          <option value="本科">本科</option>
          <option value="研究生">研究生</option>
          <option value="硕士">硕士</option>
          <option value="博士">博士</option>
        </select>
      </div>
    </div>

    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>职员空间
    </div>
    <br>

    <div class="form-group">
      <label class="col-sm-2 control-label"><i class="red" style="font-size:1.2em;">*</i>&nbsp;职位</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="job" placeholder="职位" disabled="disabled">
      </div>
      <label class="col-sm-2 control-label">职工类型</label>
      <div class="col-sm-1">
        <select id="typeOfEmployment" disabled="disabled">
          <option value="正式工">正式工</option>
          <option value="临时工">临时工</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label"><i class="red" style="font-size:1.2em;">*</i>&nbsp;月薪</label>
      <div class="col-sm-2">
        <input type="text" class="form-control" id="salary" placeholder="月薪" disabled="disabled">
      </div>
      <label class="col-sm-1 control-label">状态</label>
      <div class="col-sm-1">
        <select id="state" disabled="disabled">
          <option value="普通">普通</option>
          <option value="休假">休假</option>
          <option value="业务中">业务中</option>
          <option value="离退">离退</option>
        </select>
      </div>
      <label class="col-sm-2 control-label">入职时间</label>
      <div class="col-sm-4">
        <input type="date" class="form-control" id="dateOfHire" placeholder="入职时间" disabled="disabled">
      </div>
    </div>
    <br><br><br><br><br><br>
  </form>


</div> <!-- main-container -->
<script type="text/javascript">
  showOrg();
  function showOrg(){
    var staffId              = $("#sid").val();
    if(staffId>0){
      $.get(contextPath+"/staff/"+staffId+"?json",null, function (data) {

        var staff = data;

        $("#realName").val(staff.realName);
        $("#phoneNumber").val(staff.phoneNumber);
        $("#gender").val(staff.gender);
        $("#job").val(staff.job);
        //$("#dateOfHire").val(staff.dateOfHire);
        $("#idCardNumber").val(staff.idCardNumber);
        //$("#dateOfBirth").val(staff.dateOfBirth);
        $("#levelOfEducation").val(staff.levelOfEducation);
        $("#politicalGroup").val(staff.politicalGroup);
        $("#typeOfEmployment").val(staff.typeOfEmployment);
        $("#state").val(staff.state);
        $("#pinyin").val(staff.pinyin);
        $("#salary").val(staff.salary);
      });
    }
  }
</script>
</body>

</html>

