<%--
  Created by IntelliJ IDEA.
  User: Mklaus
  Date: 15/7/23
  Time: 下午10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>login success.</title>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>
  你好，${admin.adminName}
  <a href="<%=request.getContextPath()%>/goods" >查看所有物资</a>
  <a href="<%=request.getContextPath()%>/view/goods/goodsAdd.jsp" >添加物资</a>
<body>
</body>
</html>

