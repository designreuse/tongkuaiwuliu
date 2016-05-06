/**
 * Created by wuhaibin on 15/8/24.
 */


function showRecord(tr){
    $.ajax({
        url:contextPath+"/user?json",
        type:"get",
        success: function (res) {
            console.log(res);
            var obj = res;
            $(".address").text(obj.address);
            $(".email").text(obj.email);
            $(".name").text(obj.name);
            $(".password").text(obj.password);
            $(".phone").text(obj.phone);
        },
        fail: function(data) {
            console.log(data);
        }
    });

}
showRecord();

function Refresh(){
    var address = $("#address").val();
    var email   = $("#email").val();
    var name    = $("#name").val();
    var phone   = $("#phone").val();
    var record = {"name":name,"address":address,"email":email,"phone":phone,"_method":"put"};
    $.ajax({
        url:contextPath+"/user",
        type:"post",
        data:record,
        success: function (res) {
        }
    });
}

function RefreshPassword(){
    var oldpassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var newPasswordAgain = $("#newPasswordAgain").val();
    console.log(oldpassword);
    console.log(newPassword);
    var record = {"oldPass":oldpassword,"newPass":newPassword};
    if(newPassword === newPasswordAgain){
        $.ajax({
            url:contextPath+"/user/passwd",
            type:"post",
            data:record,
            success:function(res){
                alert("成功");
            }
        });
    }else{
        alert("两次输入的密码不相同");
    }
}