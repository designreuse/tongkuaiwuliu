<%--
  Created by IntelliJ IDEA.
  User: Mklaus
  Date: 15/8/16
  Time: 下午10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/ace.min.js"></script>
  <title>列出物资</title>
    <script>         var contextPath = '<%=request.getContextPath()%>';     </script> </head>
<body>
<c:forEach items="${goodses}" var="goods">
  ${goods.goodsName}
  ${goods.type}
  <form>
    <!-- 注意下面这一行写法，它是调用method=delete的方法 -->
    <input type="hidden" name="_method" value="delete" />
    <input type="hidden" value="${goods.id}" name="goodsId" />

  </form>
  <hr>
</c:forEach>

<input type="button" id="submit" value="删除">
</body>

<script>
  function operation(){


    var data = {"_method":"delete","ids":"10,8"};

    console.log("in")

    $.post(contextPath+"/goods?many",data,function(data){
        if(data == 1){
          alert("ok")
        }else{
          alert("data : " + data);
        }
    });
  }

  var btn = $("#submit");
  btn.click(function(){
    operation();
  });

</script>
</html>
