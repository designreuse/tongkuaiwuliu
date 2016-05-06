/**
 * Created by IntelliJ IDEA.
 * User: hwen
 * Date: 15/8/17
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 *
 */


"use strict";

jQuery(document).ready(function($){


/*------------------------------------------------------------------------*/
/*	1.	Plugins Init
/*------------------------------------------------------------------------*/


	/************** Single Page Nav Plugin *********************/
	$('.menu').singlePageNav();




	/************** FlexSlider Plugin *********************/
	$('.flexslider').flexslider({
		animation : 'fade',
		controlNav : false,
		nextText : '',
		prevText : '',
	});

	$('.flex-caption').addClass('animated bounceInDown');

	$('.flex-direction-nav a').on('click', function() {
        $('.flex-caption').removeClass('animated bounceInDown');
        $('.flex-caption').fadeIn(0).addClass('animated bounceInDown');
    });



/*------------------------------------------------------------------------*/
/*	2.	Site Specific Functions
/*------------------------------------------------------------------------*/


	/************** Go Top *********************/
	$('#go-top').click(function(event) {
        event.preventDefault();
        jQuery('html, body').animate({scrollTop: 0}, 800);
        return false;
    });



    /************** Responsive Navigation *********************/
	$('.toggle-menu').click(function(){
        $('.menu').stop(true,true).toggle();
        return false;
    });
    $(".responsive-menu .menu a").click(function(){
        $('.responsive-menu .menu').hide();
    });


	/************** Log form *********************/
	$('#login').click(function(){
		$("#logon").show();
		$('#logon2').hide();
		$("#login").toggle();
		$('#login2').toggle();
		$('#logon-form').slideUp();
		$('#login-form').slideToggle("slow",function(){
		});
	});

	$('#logon').click(function(){
		$("#login").show();
		$('#login2').hide();
		$("#logon").toggle();
		$('#logon2').toggle();
		$('#login-form').slideUp();
		$('#logon-form').slideToggle("slow",function(){
		});
	});

	$(".main-header").click(function () {
		$("#login").show();
		$("#logon").show();
		$('#login2').hide();
		$('#logon2').hide();
		$('#logon-form').slideUp();
		$('#login-form').slideUp();
	});

	$(".flexslider").click(function () {
		$("#login").show();
		$("#logon").show();
		$('#login2').hide();
		$('#logon2').hide();
		$('#logon-form').slideUp();
		$('#login-form').slideUp();
	});

	/************** Log-in *********************/
	$("#login2").click(function () {
		var account = $("#account","#login-form").val();
		var password = $("#password","#login-form").val();
		var record;
		if(isNaN(account)){
			record = {"email":account,"password":password};
		}else{
			record = {"phone":account,"password":password};
		}
		$.get(contextPath+"/user/login",record, function (check) {
			if(check.status == -1){
				$("#forgetPassword").fadeIn(500);
				$("#logTip").fadeIn(500);
				$("#logTip").text("");
				$("#logTip").text("帐号或密码错误！！");
				$("#forgetPassword").empty();
				$("#logTip").fadeOut(5000);
				$("#forgetPassword").append("<a href='/view/home/forgetPassword.jsp'>忘记密码?</a>");
			}else{
				location.href=contextPath+"/view/home/userService.jsp";
			}
		});
	});

	$("#logon2").click(function () {
		var name = $("#logonName","#logon-form").val();
		var pe   = $("#phone-email","#logon-form").val();
		var password = $("#logonPassword","#logon-form").val();
		var record,tip;
		var flag = true;

		var check = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(isNaN(pe)){
			if(!check.test(pe)){
				$("#logTip").fadeIn(500);
				$("#logTip").text("");
				$("#logTip").text("邮箱格式有误！！");
				$("#logTip").fadeOut(5000);
				flag=false;
			}
		}else{
			if(pe.length!=7&&pe.length!=11){
				$("#logTip").fadeIn(500);
				$("#logTip").text("");
				$("#logTip").text("电话格式有误！！");
				$("#logTip").fadeOut(5000);
				flag=false;
			}
		}

		if(flag){
			if(isNaN(pe)){
				record = {"email":pe,"name":name,"password":password};
				tip = "该邮箱已注册！！";
			}else{
				record = {"phone":pe,"name":name,"password":password};
				tip = "该电话已注册！！";
			}
			$.post(contextPath+"/user/register",record, function (check) {
				if(check.status==-1){
					$("#logTip").fadeIn(500);
					$("#logTip").text("");
					$("#logTip").text(tip);
					$("#logTip").fadeOut(5000);
				}else{
					location.href=contextPath+"/view/home/userService.jsp";
				}
			});
		}
	});
});

/************** check login*********************/
var uid=null;
var uname=null;
var checkLogin = function () {
	$.ajax({
		url:contextPath+"/user?json",
		type:"get",
		async:false,
		success: function (data) {
			var user = data;
			uid = user.id;
			uname=user.name;
		}
	});

	if(uid>0){
		$("#log-icon").hide();
		$(".accountTag").show();
		$(".top-header").height(120);

		var txt =["寄货物","查订单","退出"];
		initTag(uname,0,txt);
	}else{
		$("#log-icon").show();
		$(".accountTag").hide();
	}

	$("#func1").click(function () {
		location.href=contextPath+"/view/home/userService.jsp";
	});

	$("#func2").click(function () {
		location.href=contextPath+"/view/home/userService.jsp";
	});

	$("#func3").click(function () {
		location.href=contextPath+"/user/logout"
	});
}


$("#click1").click(function () {
	if(uid>0){
		location.href=contextPath+"/view/home/userService.jsp";
	}else{
		alert("请先登录！！");
	}
});

$("#click2").click(function () {
	if(uid>0){
		location.href=contextPath+"/view/home/userService.jsp";
	}else{
		alert("请先登录！！");
	}
});

$("#click3").click(function () {
	if(uid>0){
		location.href=contextPath+"/view/home/userService.jsp";
	}else{
		alert("请先登录！！");
	}
});