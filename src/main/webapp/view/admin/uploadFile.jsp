<%--
  Created by IntelliJ IDEA.
  User: Mklaus
  Date: 15/8/17
  Time: 上午9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>
<body>
<h1>上传头像</h1>
<form method="post" action="<%=request.getContextPath()%>/image/upload" enctype="multipart/form-data">
  <input type="hidden" name="car">
  <input type="file" name="file" /><br>
  <input type="text" name="name" >
  <input type="submit">
</form>
</body>
</html>
