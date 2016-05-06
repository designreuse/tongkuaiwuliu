<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/23
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <!-- 强制国内垃圾浏览器开启高速模式-->
  <meta name="renderer" content="webkit">
  <title>修改人员信息</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/staffForm.css" >

  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>
  <style>
    .slideTip{
      position: fixed;
      bottom: 0; left: 0;
      width: 200px; height: 80px;
      border-radius: 5px;
      display: none;
      border:1px solid #438EB9;
      z-index: 10;
    }

    .slideTip .slideHeader{
      background-color: #438EB9;
      color: white;
      padding-left: 10px;
      height: 30px; width: 100%;
    }

    .slideTip .slideContent{
      font-weight: bold;
      text-align: center;
      padding-top: 10px;
      background-color: lightcyan;
    }

  </style>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>

<body class="no-skin">

<!-- 主体部分 -->
<div class="main-container" id="main-container">

  <!-- navbar -->
  <div id="navbar">
    <div class="nav-brand">
      <i class="fa fa-leaf">&nbsp;&nbsp;修改人员信息</i>
    </div>
  </div>
  <!-- navbar -->

  <div class="nav-list">
    <nav>
      <ul class="pager">
        <li class="previous"><a href="<%=request.getContextPath()%>/view/staff/staff.jsp"><span aria-hidden="true">&larr;</span>返回</a></li>
      </ul>
    </nav>
  </div>
  <% String sid=request.getParameter("sid"); %>
  <!-- addForm -->
  <div class="addForm form-horizontal" role="form">
    <h2>修改员工信息</h2>
    <hr style="border:1px solid #438EB9;border-radius:1px;" color="#438EB9">
    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>添加头像
    </div>
    <div class="form-group">
      <span class="profile-picture">
                <img id="avatar" class="editable img-responsive editable-click editable-empty" width="150px" height="150px" />
                <%--<img width="150px" height="200px" src="../image/get?staffId=2"/>--%>
                  <form name="userForm" id="imgForm" action="<%=request.getContextPath()%>/image/upload" method="post" enctype="multipart/form-data" class="form-inline editableform" >
                  <div class="control-group form-group">
                    <div>
                      <div class="editable-input editable-image">
                            <span>
                                <label class="ace-file-input ace-file-multiple" style="width: 150px;">
                                  <input type="hidden" id="imgUrl" name="staffId">
                                  <input type="file" name="file" id="upfile" onchange="refleshFile(this)">
                                    <span class="ace-file-container" data-title="点我选择新头像">
                                        <span class="ace-file-name" data-title="No File ...">
                                            <i class=" ace-icon fa fa-picture-o"></i>
                                        </span>
                                    </span>
                                  <img id="preview" style="display: none;"/>
                                  <a class="remove" href="#">
                                    <i class=" ace-icon fa fa-times"></i>
                                  </a>
                                </label>
                            </span>
                      </div>
                      <div class="editable-buttons">
                        <button type="submit" id="uploadImage" class="btn btn-info editable-submit">
                          <i class="ace-icon fa fa-check"></i></button>
                        <button type="button" class="btn editable-cancel">
                          <i class="ace-icon fa fa-times"></i></button></div>
                    </div>
                    <div class="editable-error-block help-block" style="display: none;"></div>
                  </div>
                </form>
      </span>
    </div>
    <br>
    <br>
    <br>
    <div class="subHeader">
      <i class="fa  fa-minus fa-rotate-90"></i>基本信息
    </div>
    <br>

    <input id="sid" type="text" value="<%=sid%>" style="display:none;">
    <div class="form-group">
      <label class="col-sm-2 control-label"><i class="red" style="font-size:1.2em;">*</i>&nbsp;姓名</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="realName" placeholder="姓名">
      </div>
      <label class="col-sm-2 control-label">性别</label>
      <div class="col-sm-1">
        <select name="" id="gender">
          <option value="男">男</option>
          <option value="女" selected="selected">女</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label"><i class="red" style="font-size:1.2em;">*</i>&nbsp;身份证号</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="idCardNumber" placeholder="身份证号">
      </div>
      <label class="col-sm-2 control-label">电话号码</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="phoneNumber" placeholder="电话号码">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">政治面貌</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="politicalGroup" placeholder="政治面貌">
      </div>
      <label class="col-sm-2 control-label">教育水平</label>
      <div class="col-sm-1">
        <select id="levelOfEducation">
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
        <input type="text" class="form-control" id="job" placeholder="职位">
      </div>
      <label class="col-sm-2 control-label">状态</label>
      <div class="col-sm-1">
        <select id="state">
          <option value="普通">普通</option>
          <option value="休假">休假</option>
          <option value="业务中">业务中</option>
          <option value="离退">离退</option>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label"><i class="red" style="font-size:1.2em;">*</i>&nbsp;月薪</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="salary" placeholder="月薪">
      </div>

      <label class="col-sm-2 control-label">入职时间</label>
      <div class="col-sm-4">
        <input type="date" class="form-control" id="dateOfHire" placeholder="入职时间">
      </div>
    </div>
    <div class="btnArea">
      <button class="btn btn-primary" id="saveEdit">确认</button>
      <%--<button class="btn btn-danger" id="cancal">取消</button>--%>
      <a class="btn btn-danger" id="cancal" href="staff.jsp">取消</a>
    </div>
  </div>
  <br><br><br><br>

</div> <!-- main-container -->

<%@include file="/view/common/alertBox.jsp"%>
<script src="<%=request.getContextPath()%>/js/dateFormat.js"></script>
<script src="<%=request.getContextPath()%>/js/staff/editStaff.js"></script>
</body>

</html>
