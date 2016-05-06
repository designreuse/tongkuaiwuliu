<%--
  Created by IntelliJ IDEA.
  User: Mklaus
  Date: 15/8/16
  Time: 下午10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>查看物资</title>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>
<body>
${goods.goodsName}
${goods.type}
</body>
</html>
