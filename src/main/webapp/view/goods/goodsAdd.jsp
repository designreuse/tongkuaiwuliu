<%--
  Created by IntelliJ IDEA.
  User: Mklaus
  Date: 15/8/16
  Time: 下午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>添加物资</title>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>
<body>
<form action="<%=request.getContextPath()%>/goods" method="post" >
  名称: <input type="text" name="goodsName" /> <br>
  类型: <input type="text" name="type" /> <br>
  <input type="submit" value="添加">
</form>
</body>
</html>
