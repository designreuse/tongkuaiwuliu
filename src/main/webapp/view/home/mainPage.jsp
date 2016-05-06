<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/17
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
    <title>痛快物流平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="痛快物流平台">
    <meta charset="UTF-8">

    <!-- CSS Bootstrap&Custom -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home-misc.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home-main.css">

    <!-- JavaScript -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>

    <!--[if lt IE 8]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img
                src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0"
                alt=""/></a>
    </div>
    <![endif]-->
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>

<body onload="checkLogin()">
<div id="home">
    <div class="site-header">
        <div class="top-header">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-sm-6">
                        <div class="left-header">
                            <span><i class="fa fa-phone"></i>0725-7275666</span>
                            <span><i class="fa fa-envelope"></i>hwen_leung@163.com</span>
                        </div>
                        <!-- /.left-header -->
                    </div>

                    <div id="log-icon" class="col-md-6 col-sm-6">
                        <div id="forgetPassword"></div>
                        <div id="logTip"></div>
                        <div class="right-header text-right">
                            <ul class="social-icons" id="login-icon">
                                <li id="login"><a href="#" class="fa fa-sign-in"></a>登录</li>
                                <li id="login2" style="display:none;">
                                    <a href="#" class="fa fa-chevron-right"></a>登录 go
                                </li>
                                <li id="logon"><a href="#" class="fa fa-leaf"></a>注册</li>
                                <li id="logon2" style="display:none;">
                                    <a href="#" class="fa fa-chevron-right"></a>注册 go
                                </li>
                                <li id="logon4"><a href="<%=request.getContextPath()%>/admin/login" class="fa fa-leaf"></a>管理员登录</li>
                            </ul>
                            <span></span>
                        </div>
                    </div>
                    <%@include file="/view/common/accountTag.jsp" %>
                </div>
                <!-- /.row -->

                <div id="log-form" class="row">

                    <div class="col-md-2 col-xs-3 col-md-offset-10 col-xs-offset-8">
                        <div id="login-form" class="form-horizontal" style="display:none;">
                            <div class="form-group">
                                <input id="account" type="text" class="form-control" placeholder="帐号"
                                       value="1@1.com">
                            </div>
                            <div class="form-group">
                                <input id="password" type="password" class="form-control" placeholder="密码"
                                       value="123456">
                            </div>
                        </div>
                        <!-- /#login-form -->
                    </div>

                    <div class="col-md-2 col-xs-3 col-md-offset-10 col-xs-offset-8">
                        <div id="logon-form" class="form-horizontal" style="display:none;">
                            <div class="form-group">
                                <input id="logonName" type="text" class="form-control" placeholder="帐号名">
                            </div>
                            <div class="form-group">
                                <input id="phone-email" type="text" class="form-control" placeholder="手机号或邮箱">
                            </div>
                            <div class="form-group">
                                <input id="logonPassword" type="password" class="form-control" placeholder="密码">
                            </div>
                        </div>
                        <!-- /#logon-form -->
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->
        </div>
        <!-- /.top-header -->

        <div class="main-header">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-4 col-xs-6">
                        <div class="logo">
                            <h1 style="color:#438EB9;">
                                <i class="fa fa-leaf"></i>
                                <a href="#" title="" style="text-decoration:none;color:#438EB9;">痛快物流</a>
                            </h1>
                        </div>
                        <!-- /.logo -->
                    </div>

                    <div class="col-md-8 col-sm-8 col-xs-6">
                        <div class="menu text-right hidden-sm hidden-xs">
                            <ul>
                                <li><a href="#home">首页</a></li>
                                <li><a href="#services">公司介绍</a></li>
                                <li><a href="#portfolio">网络服务</a></li>
                                <li><a href="#about">关于我们</a></li>
                            </ul>
                        </div>
                        <!-- /.menu -->
                    </div>
                </div>
                <!-- /.row -->
            </div>
        </div>
        <!-- /.main-header -->
    </div>
    <!-- /.site-header -->
</div>
<!-- /#home -->

<div class="flexslider">
    <ul class="slides">
        <li>
            <img src="<%=request.getContextPath()%>/images/banner01.jpg" alt="banner01">

            <div class="flex-caption">
                <h2>迅捷空运</h2>
                <span></span>

                <p>遍布全国 航运迅捷 让你品尝到国疆另一边的雪糕</p>
            </div>
        </li>
        <li>
            <img src="<%=request.getContextPath()%>/images/banner02.jpg" alt="banner02">

            <div class="flex-caption">
                <h2>铁路枢纽</h2>
                <span></span>

                <p>覆盖全国 运量庞大 你，还在犹豫么？</p>
            </div>
        </li>
        <li>
            <img src="<%=request.getContextPath()%>/images/banner03.jpg" alt="banner03">

            <div class="flex-caption">
                <h2>公路运输</h2>
                <span></span>

                <p>只要有路 我们就会把货送到你的手上</p>
            </div>
        </li>
    </ul>
    <!-- /.slides -->
</div>
<!-- /.flexslider -->

<div class="copyrights">Power by <a href="" style="text-decoration:none;">hnndjavaee10</a></div>

<div id="services" class="section-content">
    <div class="container">
        <div class="title-section text-center">
            <h2>公司介绍</h2>
            <span></span>
        </div>
        <!--  /.title-section -->
        <div class="row">
            <div class="col-md-3 col-sm-6">
                <div class="service-item">
                    <div class="service-header">
                        <i class="fa fa-leaf"></i>

                        <h3>顾客为先</h3>
                    </div>
                    <div class="service-description">
                        我们一直将顾客放到第一位，全力投入为顾客提供最优质，最便捷的服务。这是我们公司一贯的宗旨，也是公司一直努力完善的目标。
                        公司虽然刚成立几年，但已经在全国物流行业立于领先的地位，这得益于顾客的信赖，也是我们坚持顾客为先原则的一大动力。
                    </div>
                </div>
                <!-- /.service-item -->
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="service-item">
                    <div class="service-header">
                        <i class="fa fa-truck"></i>

                        <h3>无缝送达</h3>
                    </div>
                    <div class="service-description">
                        无论你住在国内的哪个角落，无论路途多么崎岖，路在，我们就会把货物送达你手上。我们的服务网点覆盖全国，全国各大城市有
                        常驻服务中心，业务流程几乎0差错，货物丢失率低。我们唯一的缺点就是还不能接收送达月球的订单。你，还用犹豫么？
                    </div>
                </div>
                <!-- /.service-item -->
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="service-item">
                    <div class="service-header">
                        <i class="fa fa-bolt"></i>

                        <h3>运输迅捷</h3>
                    </div>
                    <div class="service-description">
                        我们致力于提供行业领先的运输速度，将货物尽快地送到客户手上是我们的愿望。无特殊情况下，我们的运输网络全天24小时运作，
                        有什么比从梦中苏醒后就收到期盼的货物美妙呢？或许你想在睡醒时尝到来自地球另一边生产的雪糕？我们通通为你实现！！
                    </div>
                </div>
                <!-- /.service-item -->
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="service-item">
                    <div class="service-header">
                        <i class="fa fa-users"></i>

                        <h3>优质员工</h3>
                    </div>
                    <div class="service-description">
                        我们拥有全球顶尖的人才，每年公司都会从世界各地吸纳成百上千的行业人才，同时公司提供顶尖的培训课程，每年都会孵化出
                        无数的优质员工，正是他们支持起这座物流行业的帝国，也正是他们为公司提供源源不断的生命力，公司得以长久发展。
                    </div>
                </div>
                <!-- /.service-item -->
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</div>
<!-- /#services -->

<div id="portfolio" class="section-content">
    <div class="container">
        <div class="title-section text-center">
            <h2>网络服务</h2>
            <span></span>
        </div>

        <div class="row">
            <div class="col-md-4 col-sm-6">
                <div class="portfolio-thumb">
                    <p style="text-align: center;color:white;font-size: 1.4em;">货物投递</p>

                    <div style="text-align: center;color: #438EB9;">
            <span class="fa-stack fa-lg fa-5x">
              <i class="fa fa-square-o fa-stack-2x"></i>
              <i class="fa fa-archive fa-stack-1x"></i>
            </span>
                    </div>
                    <div class="overlay">
                        <div class="inner">
                            <h4><a id="click1">前去投递</a></h4>
                            <span>进入投递申请页面</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6">
                <div class="portfolio-thumb">
                    <p style="text-align: center;color:white;font-size: 1.4em;">订单查询</p>

                    <div style="text-align: center;color: #438EB9;">
            <span class="fa-stack fa-lg fa-5x">
              <i class="fa fa-square-o fa-stack-2x"></i>
              <i class="fa fa-file-text fa-stack-1x"></i>
            </span>
                    </div>
                    <div class="overlay">
                        <div class="inner">
                            <h4><a id="click2">前去查询</a></h4>
                            <span>进入订单查询页面</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6">
                <div class="portfolio-thumb">
                    <p style="text-align: center;color:white;font-size: 1.4em;">我的帐号</p>

                    <div style="text-align: center;color: #438EB9;">
            <span class="fa-stack fa-lg fa-5x">
              <i class="fa fa-square-o fa-stack-2x"></i>
              <i class="fa fa-user fa-stack-1x"></i>
            </span>
                    </div>
                    <div class="overlay">
                        <div class="inner">
                            <h4><a id="click3">帐号信息</a></h4>
                            <span>进入帐号管理页面</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</div>
<!-- /#portfolio -->

<div id="about" class="section-content">
    <div class="container">
        <div class="title-section text-center">
            <h2>关于我们</h2>
            <span></span>
        </div>
        <div class="row">
            <div class="col-md-8">
                <h4 class="widget-title">公司背景</h4>

                <p> 痛快物流是国家“AAAAA”级物流企业，主营国内公路运输业务。截止2015年8月，公司已开设直营网点 5,600多家，服务网络遍及全国，
                    自有营运车辆9,300余台，全国转运中心总面积超过121万平方米。公司始终以客户为中心随时候命、持续创新，始终坚持自建营业网点、
                    自购进口车辆、搭建最优线路，优化运力成本，为客户提供快速高效、便捷及时、安全可靠的服务体验，助力客户创造最大的价值。公司
                    秉承“承载信任、助力成功”的服务理念，保持锐意进取、注重品质的态度，强化人才战略，通过不断的技术创新和信息化系统的搭建，提
                    升运输网络和标准化体系，创造最优化的运载模式，为广大客户提供安全、快速、专业、满意的物流服务。一直以来，公司都致力于与员
                    工共同发展和成长，打造人企双赢。在推动经济发展，提升行业水平的同时，努力创造更多的社会效益，为国民经济的持续发展，和谐社
                    会的创建做出积极贡献，努力将痛快物流打造成为中国人首选的国内物流运营商。
                </p>
            </div>
            <div class="col-md-4 our-skills">
                <h4 class="widget-title">为何选择我们？</h4>
                <ul class="progess-bars">
                    <li>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 90%;">行业好评率 90%
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="75" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 75%;">物流覆盖率 75%
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="85" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 85%;">物流精准率 99%
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="95" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 95%;">物流速度 95%
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- /.col-md-4 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</div>
<!-- /#about -->

<div class="site-footer">
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
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</div>
<!-- /.site-footer -->

<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/plugins.js"></script>
<script src="<%=request.getContextPath()%>/js/home/home.js"></script>

</body>
</html>