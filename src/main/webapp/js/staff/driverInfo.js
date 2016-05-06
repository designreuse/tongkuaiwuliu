/**
 * Created by hwen on 15/8/26.
 */

$(document).ready(function() {
    showOrg();

    function showOrg(){
        var staffId              = $("#sid").val();

        $.ajax({
            url:contextPath+"/staff/"+staffId+"?json",
            type:"get",
            async:false,
            success: function (data) {
                var staff = data;
                $("#dName").text(staff.realName);
                $("#dGender").text(staff.gender);
                $("#dPhone").text(staff.phoneNumber);
                $("#dState").text(staff.state);
            }
        });
    }

});