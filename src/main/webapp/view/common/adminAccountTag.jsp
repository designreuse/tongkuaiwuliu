<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/9/5
  Time: 2:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/accountTag.css" >

<div class="accountTag" style="display: none;">
  <button id="tagBtn" class="btn btn-info btn-sm" type="button">
    <span id="accountName"></span>
    <span class="badgeCount"></span>
  </button>
  <ul class="dropdownMenu" style="display:none;">
    <li id="func1"><a id="a0" href="#"></a></li>
    <li id="func2"><a id="a1" href="#"></a><span class="badgeCount"></span></li>
    <li id="func3"><a id="a2" href="#"></a></li>
  </ul>
</div>

<script type="text/javascript">
  $(document).ready(function(){
    var adminId=null;
    var adminName=null;

    $("#tagBtn").click(function(){
      if ($(".dropdownMenu").is(':hidden')) {
        $(".badgeCount","#tagBtn").css("visibility","hidden");
        $("#tagBtn").css("border-radius","20px 20px 0 0");
        $(".dropdownMenu").slideDown(1000);
      }else{
        $(".badgeCount","#tagBtn").css("visibility","");
        $(".dropdownMenu").slideUp(1000);
        $("#tagBtn").css("border-radius","30px 30px 30px 30px");
      }
    });

    $.ajax({
      url:contextPath+"/admin?json",
      type:"get",
      async:false,
      success: function (data) {
        var admin = data;
        adminId=admin.id;
        adminName=admin.adminName;
      }
    });

    if(adminId>0){
      $(".accountTag").show();
      var atxt = ["修改密码","退出"];
      initTag(adminName,0,atxt);

      $("#func1").click(function () {
        location.href=contextPath+"/view/admin/editPassword.jsp";
      });

      $("#func2").click(function () {
        location.href=contextPath+"/admin/logout";
      });
    }
  });


  /**
   *
   * @param name  必须
   * @param count 必须
   * @param atxt  可缺省，li文本，类型数组
   * @param func1 同上
   * @param func2 ..
   * @param func3 同上
   */
  function initTag(name,count,atxt,func1,func2,func3){
    $("#accountName").text(name);
    if(count==0){
      $(".badgeCount").remove();
    }else{
      $(".badgeCount").text(count);
    }

    atxt.forEach(function(item,index){
      $("#a"+index).text(item);
    });


    $("#func1").on("click",func1);
    $("#func2").on("click",func2);
    $("#func3").on("click",func3);
  }

</script>
