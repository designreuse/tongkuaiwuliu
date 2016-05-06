<%--
  Created by IntelliJ IDEA.
  User: Mklaus
  Date: 15/7/23
  Time: 下午10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
  <title>管理员登录</title>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>

<body>
<form action="<%=request.getContextPath()%>/admin/passwd" method="post">
  帐号：<input type="textfield" name="adminName" /><br>
  密码：<input type="password" name="oldPass"  /><br>
  密码：<input type="password" name="newPass"  /><br>
  <input type="submit" value="登录" />
</form>
</body>
</html>