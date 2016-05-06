// 实现增删改查功能

$(document).ready(function(){


	var tb=$("#sample-table");
	var tdData;
	var selectedTr = new Array();

	/*---------------------显示记录---------------------*/
	showRecord();

	/*---------------------添加---------------------*/
	$("#addItem").click(function(){
		var hideTr = $("#hidedTbody",tb).children().first();
		var newTr = hideTr.clone().show();
		$("#mainTbody",tb).append(newTr);
		$("input[type='text']:first",newTr).focus();
		showAdd(newTr);
	});

	/*---------------------保存---------------------*/
	tb.off("click","#saveAdd").on("click","#saveAdd", function () {
		var tr=$(this).parent().parent().parent();  //改动html标签嵌套时，注意选择到tr
		if(checkFormat(tr)){
			$("input[type='text']",tr).each(function(index,element){
				element=$(element);
				element.parent().text(element.val());
			});
			addRecord(tr);
			showNormal(tr);
		}
	});

	/*---------------------修改---------------------*/
	tb.off("click","#editItem2").on("click","#editItem2",function(){
		var tr=$(this).parent().parent().parent();  //改动html标签嵌套时，注意选择到tr
		tdData = new Array();
		$("td:not('.op'):not('.sorting_1'):not('.id')",tr).each(function (index,element) {
			element=$(element);

			tdData.push(element.text());

			var html="<input type='text' value='"+element.text()+"'>";
			element.html(html);
		});
		$("input[type='text']:first",tr).focus();
		showEdit(tr);
	});

	/*---------------------更新数据---------------------*/
	tb.off("click","#saveEdit").on("click","#saveEdit",function(){
		var tr=$(this).parent().parent().parent();  //改动html标签嵌套时，注意选择到tr
		if(checkFormat(tr)){
			$("input[type='text']",tr).each(function(index,element){
				element=$(element);
				element.parent().text(element.val());
			});
			updateRecord(tr);
			showNormal(tr);
		}
	});

	/*---------------------重置---------------------*/
	tb.off("click","#reset").on("click","#reset", function () {
		var tr=$(this).parent().parent().parent();  //改动html标签嵌套时，注意选择到tr
		$("input[type='text']",tr).each(function (index,element) {
			element=$(element);
			element.val("");
		});
	});


	/*---------------------删除一条记录---------------------*/
	tb.off("click","#deleteItem2").on("click","#deleteItem2", function () {
		var tr=$(this).parent().parent().parent();  //改动html标签嵌套时，注意选择到tr
		deleteRecord(tr);
		tr.remove();
	});

	/*---------------------多选删除---------------------*/
	//选择多个记录时提示用户，确认操作无误
	$("#deleteItem").click(function(){
		if(selectedTr.length>0){
			$("#tipText").text("已选择 "+selectedTr.length+"条数据，是否进行删除？")
			$(".confirmBox").show("1000");
		}
	});
	$("#confirmDel").click(function(){
		var selectedId = [];
		$(selectedTr).each(function(index,element){
			selectedId.push($(element).find(".id").text());
			$(element).remove();
		});
		selectedTr = [];

		if(selectedId.length>0){
			deleteRecords(selectedId);
		}
		$(".confirmBox").hide("2000");
	});
	$("#confirmCancal").click(function () {
		$(".confirmBox").hide("2000");
	});


	/*---------------------get selected checkbox's id---------------------*/
	$("#sample-table").on("click",":checkbox",function(){
		if ($(this)[0].id==$("#selectAll")[0].id) {
			//console.log("selectAll is clicked");
			if($("#selectAll").is(':checked')){
				$(":checkbox","#mainTbody").each(function(index,element){
					// $(element).attr('checked',true)
					$(element).prop("checked",true);
				});
			}else{
				$(":checkbox","#mainTbody").each(function(index,element){
					$(element).removeAttr("checked",false);
				});
			}
		};

		selectedTr=[];   //清空数组，据说效率比 selectedId.length =0; 高
		$(":checkbox","#mainTbody").each(function(index,element){
			if($(element).is(":checked")){
				var tr=$(element).parent().parent().parent();
				selectedTr.push(tr);
				//var textId=tr.find(".id").text();
				//console.log("checkbox is checked->"+textId);
				//selectedId.push(textId);
			}
		});
	});

	/*---------------------取消---------------------*/
	tb.off("click","#cancal").on("click","#cancal", function () {
		var tr=$(this).parent().parent().parent();  //改动html标签嵌套时，注意选择到tr
		$("input[type='text']",tr).each(function(index,element){
			element=$(element);
			element.parent().text(tdData[index]);
			element.remove();
		});
		showNormal(tr);
	});

	/*---------------------搜索---------------------*/
	$("#search").click(function () {
		var keyword=$("#searchText");
		var tby=$("#mainTbody",tb);
		console.log("search clicked");
	});

	/*---------------------更改页面显示条目---------------------*/
	$("#pageSize").change(function () {
		var page = $(".pagination").find(".active");
		page.removeClass("active");
		$(".pagination li:nth-of-type(3)").addClass("active");  //第一页
		jump(1);
		pageChange();
		var activePage = $(".pagination").find(".active");
		arrow(activePage);
	});

	/*---------------------跳页---------------------*/
	$(".pagination").on("click","li",function(){
		if($(this).is(".disabled")===false){
			var lid = $(this).attr("id");
			var page = $(this).text();
			var activePage = $(".pagination").find(".active");

			console.log("li is clicked------->"+page);

			switch (lid){
				case "beginPage":
					activePage.removeClass("active");
					$(".pagination li:nth-of-type(3)").addClass("active");  //第一页
					page="1";
					jump(page);
					break;
				case "prevPage":
					var prev=activePage.prev();
					activePage.removeClass("active");
					prev.addClass("active");
					page=prev.text();
					jump(page);
					break;
				case "nextPage":
					var next=activePage.next();
					activePage.removeClass("active");
					next.addClass("active");
					page=next.text();
					jump(page);
					break;
				case "endPage":
					activePage.removeClass();
					var lastPage =$(".pagination li:nth-last-of-type(3)").addClass("active");  //最后一页
					page=lastPage.text();
					jump(page);
					break;
				default :                 //same as-- case "page":break;
					$(".pagination").find(".active").removeClass("active");
					$(this).addClass("active");
					jump(page);
					break;
			}
			activePage = $(".pagination").find(".active");
			arrow(activePage);
		}

	});

	/*---------------------跳页箭头的禁用---------------------*/
	function arrow(activePage){
		if($(".pagination li:nth-last-of-type(3)").text()==="1"){  //只有一页，禁用全部箭头跳转
			$("#beginPage").addClass("disabled");
			$("#prevPage").addClass("disabled");
			$("#nextPage").addClass("disabled");
			$("#endPage").addClass("disabled");
		} else if(activePage.text()==="1"){
			$("#beginPage").addClass("disabled");
			$("#prevPage").addClass("disabled");
			$("#nextPage").removeClass("disabled");
			$("#endPage").removeClass("disabled");

		} else if(activePage.text()===$(".pagination li:nth-last-of-type(3)").text()){
			$("#beginPage").removeClass("disabled");
			$("#prevPage").removeClass("disabled");
			$("#nextPage").addClass("disabled");
			$("#endPage").addClass("disabled");
		} else{
			$("#beginPage").removeClass("disabled");
			$("#prevPage").removeClass("disabled");
			$("#nextPage").removeClass("disabled");
			$("#endPage").removeClass("disabled");
		}
	}


	/*---------------------更新操作图标按钮---------------------*/
	function showAdd(tr){
		$("#saveAdd",tr).show();
		$("#reset",tr).show();
		$("#deleteItem2",tr).show();

		$("#saveEdit",tr).hide();
		$("#cancal",tr).hide();

		$("#editItem2",tr).hide();
	}

	function showEdit(tr){
		$("#saveAdd",tr).hide();
		$("#reset",tr).hide();
		$("#deleteItem2",tr).hide();

		$("#saveEdit",tr).show();
		$("#cancal",tr).show();

		$("#editItem2",tr).hide();
	}

	function showNormal(tr){
		$("#saveAdd",tr).hide();
		$("#reset",tr).hide();
		$("#deleteItem2",tr).show();

		$("#saveEdit",tr).hide();
		$("#cancal",tr).hide();

		$("#editItem2",tr).show();
	}
});


