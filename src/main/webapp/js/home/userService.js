/**
 * Created by hwen on 15/8/31.
 */

var zIndex = 10;

$(document).ready(function () {
    initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, '44', '0', '0');
    initComplexArea('seachprov2', 'seachcity2', 'seachdistrict2', area_array, sub_array, '44', '0', '0');
    initComplexArea('seachprov3', 'seachcity3', 'seachdistrict3', area_array, sub_array, '44', '0', '0');

    checkOrder();
    slideUpAll();
    getUser();
    initAccountTag();

    $(".express", ".mainMenu").click(function () {
        showBox(this);
        $(".taskBox").slideDown(1000);
    });

    function showOrder(th, str) {
        showBox(th, str);
        //$(".myOrderBox").slideDown(1000);
        $(".subOrderBox").slideDown(1000);
        $(".hideOrderBox").slideDown(1000);
    }

    $(".myOrder", ".mainMenu").click(function () {
        showOrder(this);
    });

    function showUser(th, str) {
        showBox(th, str);
        getUser();
        $(".userBox").slideDown(1000);
        $(".passwordBox").slideDown(1000);
    }

    $(".user", ".mainMenu").click(function () {
        showUser(this);
    });

//$(".search",".mainMenu").click(function(){
//    showBox(this);
//    $(".searchBox").slideDown(1000);
//});

    function showAlert(tip) {
        showBox(null, "alert");
        $(".alertContent", ".alertBox").text(tip);
        $(".alertBox").slideDown(1000);
    }


    $("#addTask").click(function () {
        addTask();
        showAlert("运单创建成功。申请成功后会将通知发至你的邮箱和电话");
        emptyTask();
        $(".myOrderBox").empty();
        checkOrder();
    });

    $("#saveUserInfo").click(function () {
        updateAccount();
        showAlert("帐号信息修改成功");
    });

    $("#savePassword").click(function () {
        updatePassword();
        showAlert("密码修改成功！");
    });

    $("#addCargo").click(function () {
        $(".taskBox").animate({height: '+=40px'});
        var subCargo = $(this).parent().parent().parent();
        var newCargo = $("#hideCargo").children().first();
        var cargo = newCargo.clone().show();
        subCargo.append(cargo);
    });

    $(".cargo").on("click", "#deleteCargo", function () {
        $(".taskBox").animate({height: '-=40px'});
        console.log("delete click");
        var thisCargo = $(this).parent().parent();
        thisCargo.remove();
    });

    function showBox(select, str) {
        var sc;
        if (select != null) {
            sc = $(select).attr("class");
        } else {
            sc = str;
        }
        switch (sc) {
            case "express":
                slideUpAll();
                $(".taskBox").css("z-index", zIndex++);
                break;
            case "myOrder":
                slideUpAll();
                $(".subOrderBox").css("z-index", zIndex++);
                break;
            case "user":
                slideUpAll();
                $(".userBox").css("z-index", zIndex++);
                $(".passwordBox").css("z-index", zIndex++);
                break;
            case "alert":
                slideUpAll();
                $(".alertBox").css("z-index", zIndex++);
                break;
            //case "search":
            //    slideUpAll();
            //    $(".searchBox").css("z-index",zIndex++);
            //    break;
        }
    }

    function slideUpAll() {
        $(".taskBox").slideUp(0);
        $(".userBox").slideUp(0);
        $(".passwordBox").slideUp(0);
        $(".myOrderBox").slideUp(0);
        $(".hideOrderBox").slideUp(0);
        $(".alertBox").slideUp(0);
        //$(".searchBox").slideUp(0);
    }

    function initAccountTag() {
        $(".accountTag").show();
        var txt = ["我的帐号", "未完成订单", "退出"];
        initTag($("#userName").val(), taskLength, txt, null, null, logout);
    }

    $("#func1").click(function () {
        showUser(null, "user");
    });

    $("#func2").click(function () {
        showOrder(null, "myOrder");
    });

    function logout() {
        location.href = contextPath + "/user/logout";
    }

});



