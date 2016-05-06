<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/17
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/login.css">

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>

    <script src="../js/crypto/rsa.js"></script>
    <script src="../js/crypto/jsbn.js"></script>
    <script src="../js/crypto/prng4.js"></script>
    <script src="../js/crypto/rng.js"></script>

    <script>         var contextPath = '<%=request.getContextPath()%>';     </script>
</head>

<body>
<div class="navbar navbar-default">
    <div class="navbar-container">
        <a class="navbar-brand" href="http://www.baidu.com">
            <i class="fa fa-leaf"></i>
        </a>

        <div class="navbar-header pull-left" id="navbar-header">
            <small style="font-size:25px;">
                痛快物流平台
            </small>
        </div>
    </div>
</div>

<div class="page-content">
    <br><br>

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="page-header">
                <h2 class="text-center" id="text-head">
                    痛快物流平台
                </h2>
            </div>
        </div>
    </div>

    <%--登录框--%>

    <form class="form-horizontal" id="login-form">

        <div class="from-group">
            <label class="col-sm-5 control-label"></label>

            <div class="col-sm-2" id="login-message"></div>
        </div>
        <br><br>

        <div class="from-group">
            <label class="col-sm-5 control-label">帐号</label>

            <div class="col-sm-2">
                <input type="text" class="form-control" placeholder="帐号" id="adminName" name="adminName"
                       value="admin">
            </div>
        </div>
        <br><br>

        <div class="from-group">
            <label class="col-sm-5 control-label">密码</label>

            <div class="col-sm-2">
                <input type="password" class="form-control" placeholder="密码" id="password" name="password"
                       value="admin">
            </div>
        </div>
        <br><br><br>

        <div class="form-group">
            <label class="col-sm-5 control-label">
                <a href="">忘记密码<img src="<%=request.getContextPath()%>/images/sign-right-icon.png" alt="忘记密码"
                                    class="img-circle"></a>
            </label>

            <div class="col-sm-2">
                <button type="button" id="submit" class="btn btn-primary btn-block">登录</button>
            </div>
        </div>
    </form>
</div>
</body>

<script>
    function doLogin() {
        var adminName = $("#adminName").val();
        var password = $("#password").val();


        var rsaData = {"adminName": adminName};


        $.post(contextPath + "/admin/get_rsa_key", rsaData, function (key) {
            if (key.status == -1) {
                console.log(key);
                var message = $("#login-message");
                message.html("<p style='color: red'>帐号或密码错误</p>");
            } else {
                var rsa = new RSAKey();
                rsa.setPublic(key.m, key.e);
                var encrypted = rsa.encrypt(password);

                var loginData = {"adminName": adminName, "password": encrypted};

                console.log("encrypted = " + encrypted);

                $.post(contextPath + "/admin/doLogin", loginData, function (data) {
                    if (data.status == 0) {
                        window.location.href = contextPath + "/admin/loginSuccess";
                    } else {
                        var message = $("#login-message");
                        message.html("<p style='color: red'>帐号或密码错误</p>");
                    }
                });
            }
        })

    }
    var submitBtn = $("#submit");
    submitBtn.click(function () {
        doLogin();
    });

</script>

</html>
