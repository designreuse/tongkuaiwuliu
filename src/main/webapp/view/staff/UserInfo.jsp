<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<head>
    <meta charset="UTF-8">
    <!-- 强制国内垃圾浏览器开启高速模式-->
    <meta name="renderer" content="webkit">
    <title>我的信息</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ace.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" >
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/manager.css" >
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/company/UserInfo.js"></script>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>
<html>
<body class="no-skin">
<!-- 导航栏 -->
<div id="navbar" class="navbar navbard-default">
    <script type="text/javascript">
        try{ace.settings.check('navbar','fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar=container">
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
                <i class="fa fa-leaf"></i>
                <span id="title">物资信息管理</span>
            </a>
            <!-- #section:basics/navbar.layout.brand -->
        </div>
        <!-- /section:basics/sidebar.mobile.toggle -->
    </div><!-- /.navbar-container -->
</div>
<!-- 主体部分-->
<div class="main-container" id="main-container">
    <!-- 面包屑-->
    <%@ include file="/view/common/sidebar.jsp"%>
    <!-- 面包屑结束-->
    <!-- 信息页-->
    <div class="page-content">
        <!-- /section:settings.box -->
        <div class="page-header">
            <h1>
                个人信息页
            </h1>
        </div><!-- /.page-header -->

        <div class="row">
            <div class="col-xs-12" id="page1">
                <!-- PAGE CONTENT BEGINS -->
                <div class="clearfix">


                    <div class="pull-right">
                        <span class="green middle bolder">选择界面: &nbsp;</span>

                        <div class="btn-toolbar inline middle no-margin">
                            <div data-toggle="buttons" class="btn-group no-margin">
                                <label class="btn btn-sm btn-yellow active" id="change1">
                                    <span class="bigger-110">1</span>
                                    <input type="radio" value="1">
                                </label>
                                <label class="btn btn-sm btn-yellow" id="change2">
                                    <span class="bigger-110">2</span>
                                    <input type="radio" value="2">
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <%--<div class="hr dotted"></div>--%>
                <div>
                    <div id="user-profile-1" class="user-profile row" style="margin-left:300px;">
                        <div class="col-xs-12 col-sm-9">
                            <%--<div class="space-12"></div>--%>
                            <div class="profile-user-info profile-user-info-striped">
                                <div class="profile-info-row">
                                    <div class="profile-info-name"> 姓名 </div>

                                    <div class="profile-info-value">
                                        <span class="name"></span>
                                    </div>
                                </div>

                                <div class="profile-info-row">
                                    <div class="profile-info-name"> 地址信息 </div>

                                    <div class="profile-info-value">
                                        <span  class="address"></span>
                                    </div>
                                </div>

                                <div class="profile-info-row">
                                    <div class="profile-info-name">e-mail</div>
                                    <div class="profile-info-value">
                                        <span class="email"></span>
                                    </div>
                                </div>

                                <div class="profile-info-row">
                                    <div class="profile-info-name"> 密码 </div>

                                    <div class="profile-info-value">
                                        <span class="password"></span>
                                    </div>
                                </div>
                                <div class="profile-info-row">
                                    <div class="profile-info-name"> 电话号码 </div>
                                    <div class="profile-info-value">
                                        <span class="phone"></span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="hr hr2 hr-double"></div>
                <div class="space-6"></div>
            </div>
        </div>
    </div>
    <!-- 分块一结束-->
    <div id="user-profile-3" class="user-profile row">
        <div class="col-sm-offset-1 col-sm-10">
            <div class="space"></div>
            <form class="form-horizontal" style="margin:auto">
                <div class="tabbable">
                    <ul class="nav nav-tabs padding-16">
                        <li class="active">
                            <a data-toggle="tab" href="#edit-basic">
                                <i class="green ace-icon fa fa-pencil-square-o bigger-125"></i>
                                基本信息
                            </a>
                        </li>
                    </ul>
                    <div id="edit-basic" class="tab-pane in active">
                        <h4 class="header blue bolder smaller">个人信息</h4>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name">姓名</label>
                            <div class="col-sm-9">
                                <input type="text" id="name" style="height:30px;width:300px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="address">旧密码</label>
                            <div class="col-sm-9">
                                <input type="text" id="address" style="height:30px;width:300px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="phone">电话</label>
                            <div class="col-sm-9">
									<span class="input-icon input-icon-right">
										<input class="input-medium input-mask-phone" type="text" id="phone" style="height:30px;width:300px;">
										<i class="ace-icon fa fa-phone fa-flip-horizontal"></i>
									</span>
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button id="SaveButton" class="btn btn-info" type="button">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    保存
                                </button>
                                &nbsp; &nbsp;
                                <button id="Refresh1" class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    重置
                                </button>
                            </div>
                        </div>
                            <div class="space-10"></div>
                            <div class="space"></div>
                            <h4 class="header blue bolder smaller">修改密码</h4>
                            <div id="edit-password" class="tab-pane">
                            </div>
                          <div class="form-group">
                              <label class="col-sm-3 control-label no-padding-right" for="oldPassword">旧密码</label>
                               <div class="col-sm-9">
                                   <input type="password" id="oldPassword" style="height:30px;width:300px;">
                            </div>
                           </div>
                            <div class="space-10"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="newPassword">新密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="newPassword" style="height:30px;width:300px;">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="newPasswordAgain">再次输入密码</label>
                                <div class="col-sm-9">
                                    <input type="password" id="newPasswordAgain" style="height:30px;width:300px;">
                                </div>
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button id="SavePassword" class="btn btn-info" type="button">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    保存
                                </button>
                                &nbsp; &nbsp;
                                <button id="Refresh" class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    重置
                                </button>
                            </div>
                        </div>
                    </div>
                <%--</div>--%>
            </form>
        </div><!-- /.span -->
    </div>
</div>
</div><!-- /.col -->
</div><!-- /.row -->
</div>
<!-- 信息页结束-->
</div>
<!-- 主体结束-->
</body>
</html>
</html>



<script type="text/javascript">
    $(document).ready(
            function(){
                $('#user-profile-3').hide();
            }
    );
    $('#change1').click(
            function(){
                $('#user-profile-3').hide();
                $('#user-profile-1').show();
            }
    );
    $('#change2').click(
            function(){
                $('#user-profile-3').show();
                $('#user-profile-1').hide();
            }
    );
    $('#SaveButton').click(Refresh);
    $('#SavePassword').click(RefreshPassword);
</script>
