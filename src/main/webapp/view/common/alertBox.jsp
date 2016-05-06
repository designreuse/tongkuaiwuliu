<%--
  Created by IntelliJ IDEA.
  User: hwen
  Date: 15/8/24
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- confrim-box -->
<div class="confirmBox" style="display:none;">
  <div class="boxLayer">
    <div class="popBox">
      <div class="boxHeader">
        提示!
      </div>
      <div class="boxContent">
        <div id="tipText"></div>
        <div class="btnArea">
          <button id="confirmDel" class="btn btn-danger btn-sm">删除</button>
          <button id="confirmCancal" class="btn btn-primary btn-sm">取消</button>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- slideTip-box -->
<div class="slideTip">
  <div class="slideHeader">
    <i class="fa fa-leaf"></i>
  </div>
  <div class="slideContent">
  </div>
</div>