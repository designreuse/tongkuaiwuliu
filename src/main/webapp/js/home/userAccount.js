/**
 * Created by hwen on 15/8/31.
 */

function getUser(){
    $.ajax({
        url:contextPath+"/user?json",
        type:"get",
        async:false,
        success: function (data) {
            var user = data;
            $("#userName").val(user.name);
            $("#userEmail").val(user.email);
            $("#userPhone").val(user.phone);
            var str = user.address;
            var adds = str.split("-");
            $("#seachprov3").val(adds[0]);
            changeComplexProvince(adds[0], sub_array, 'seachcity3', 'seachdistrict3');
            $("#seachcity3").val(adds[1]);
            changeCity(adds[1],'seachdistrict3','seachdistrict3');
            $("#seachdistrict3").val(adds[2]);
            $("#detailAddress").val(adds[3]);
        }
    });
}

function updateAccount(){
    var name = $("#userName").val();
    var email = $("#userEmail").val();
    var phone = $("#userPhone").val();
    var address = $("#seachprov3").val();
    address += "-"+ $("#seachcity3").val();
    address += "-"+ $("#seachdistrict3").val();
    address += "-"+ $("#detailAddress").val();

    var record = {"name":name,"email":email,"phone":phone,"address":address,"_method":"put"};
    $.ajax({
        url:contextPath+"/user",
        type:"post",
        data:record,
        success: function () {
        }
    });
}

function updatePassword(){
    if($("#oldPassword").val().replace(/\s/g,"")==''){
        $("#pswTip").text("旧密码不能为空");
    }
    else if($("#newPassword").val()!=$("#newPasswords").val()){
        $("#pswTip").text("新密码两次输入不一致");
    }else{
        var oldP = $("#oldPassword").val();
        var newP = $("#newPassword").val();
        var record = {"oldPass":oldP,"newPass":newP};
        $.ajax({
            url:contextPath+"/user/passwd",
            type:"post",
            data:record,
            success: function (data) {
                if(data>0){
                    $("#oldPassword").val("");
                    $("#newPassword").val("");
                    $("#newPasswords").val("");
                }else{
                    $("#pswTip").text("旧密码不正确");
                }
            }
        });
    }
}


