/**
 * Created by hwen on 15/8/24.
 */

var tasks,taskLength;
var pageSize=10,pageNum=1,page=1; // pageNum--页数,pageSize--每页显示数目,page--当前页码

//tr
var hideTr = $("#hidedTbody").children().first();
var orgTr = hideTr.clone().show();
$("input[type='text']",orgTr).each(function(index,element){
    element=$(element);
    element.remove();
});


/**
 * 无用，但要保留，因为要兼容manage.js
 * @param tr
 */
function addRecord(tr){
}

/**
 * 无用，但要保留，因为要兼容manage.js
 * @param tr
 */
function updateRecord(tr){
}

/**
 * 删除一条记录
 * @param tr
 */
function deleteRecord(tr){
    var id     = $(".id",tr).text();
    var record = {"taskId":id,"_method":"delete"};

    if(id>0){
        $.ajax({
            url:contextPath+"/task",
            type:"post",
            data:record,
            success: function (res) {
                taskLength--;
                pageCheck();
                slideTip("成功删除数据");
            }
        });
    }
}

/**
 * 删除多条记录
 * @param selectedId
 */
function deleteRecords(selectedId){

    var idstr="";
    $(selectedId).each(function(index,element){
        idstr+=element+",";
    });

    console.log(selectedId);
    var records = {"ids":idstr,"_method":"delete"};

    $.post(contextPath+"/task?many",records,function(data){
        taskLength--;
        pageCheck();
        slideTip("成功删除数据");
    });
}

/**
 * 显示数据
 */
function showRecord(){
    pageSize= $("#pageSize").find('option:selected').text();

    $.get(contextPath+"/transportTask?json",function(data,status){

        taskLength=data.taskLength;
        tasks=$(data.tasks);

        tasks.each(function(index,task){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(task.id);
            console.log("userId->"+task.customer);
            $(".uid",newTr).text(task.customer);
            $(".title",newTr).text(task.title);
            $(".startPlace",newTr).text(task.startPlace);
            $(".endPlace",newTr).text(task.endPlace);
            $(".startDate",newTr).text(getDateStr(task.startDate));
            $(".endDate",newTr).text(getDateStr(task.endDate));

            switch (task.state){
                case "0":
                    $(".state",newTr).text("申请中");
                    break;
                case "1":
                    $(".state",newTr).text("进行中");
                    break;
                case "2":
                    $(".state",newTr).text("已完成");
                    break;
            }

            switch (task.type){
                case "0":
                    $(".type",newTr).text("自营");
                    break;
                case "1":
                    $(".type",newTr).text("承运");
                    break;
            }
            $(".rate",newTr).text(task.rate);
            $(".sumPrice",newTr).text(task.sumPrice);

            $("#mainTbody").append(newTr);
        });
        pageNum=Math.ceil(taskLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+taskLength+" pageSize->"+pageSize);

        if(pageNum==1){
            $(".pagination li:nth-last-of-type(2)").before("<li id='page' class='active'><a>"+1+"</a></li>");
            $("#nextPage").addClass("disabled");
            $("#endPage").addClass("disabled");
        }else{
            for(var i=1;i<=pageNum;i++){
                $(".pagination li:nth-last-of-type(2)").before("<li id='page'><a>"+i+"</a></li>");
            }
        }
    });

}

/**
 * 跳页
 * @param page
 */
function jump(page){
    pageSize = $("#pageSize").find('option:selected').text();
    //console.log("option change,pageSize is->"+pageSize);
    var startIdx = (page-1)*pageSize;
    //console.log("now page is->"+page);
    $.get(contextPath+"/transportTask?json",{start:startIdx,size:pageSize},function(data,status){
        //console.log(data);
        tasks=$(data.tasks);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        tasks.each(function(index,task){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(task.id);
            $(".uid",newTr).text(task.customer);
            $(".title",newTr).text(task.title);
            $(".startPlace",newTr).text(task.startPlace);
            $(".endPlace",newTr).text(task.endPlace);
            $(".startDate",newTr).text(getDateStr(task.startDate));
            $(".endDate",newTr).text(getDateStr(task.endDate));
            $(".state",newTr).text(task.state);
            switch (task.state){
                case "0":
                    $(".state",newTr).text("申请中");
                    break;
                case "1":
                    $(".state",newTr).text("进行中");
                    break;
                case "2":
                    $(".state",newTr).text("已完成");
                    break;
            }

            switch (task.type){
                case "0":
                    $(".type",newTr).text("自营");
                    break;
                case "1":
                    $(".type",newTr).text("承运");
                    break;
            }
            $(".sumPrice",newTr).text(task.sumPrice);

            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();

            $("#editItemNewPage",newTr).show();
        });
    });
}

/**
 * 处理更换页面显示条目，引起的页码更新
 */
function pageChange(){
    pageNum=Math.ceil(taskLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
    var lastPage = $(".pagination li:nth-last-of-type(3)").text();
    lastPage = parseInt(lastPage);
    if(pageNum>=lastPage){
        for(var i=lastPage+1;i<=pageNum;i++){
            $(".pagination li:nth-last-of-type(2)").before("<li id='page'><a>"+i+"</a></li>");
        }
        console.log("now last page is->"+lastPage+"pageNum is->"+pageNum);
    } else{
        for(var i=pageNum+1;i<=lastPage;i++){
            $(".pagination li:nth-last-of-type(3)").remove();
        }
    }

}

/**
 * 处理添加，删除数据引起的页码改变
 */
function pageCheck(){
    var newPageNum=Math.ceil(taskLength/pageSize);

    if(pageNum<newPageNum){
        pageNum=newPageNum;
        $(".pagination li:nth-last-of-type(2)").before("<li id='page'><a>"+pageNum+"</a></li>");
        var page = $(".pagination").find(".active").text();
        jump(page);
    }else if(pageNum>newPageNum){
        for(var i=newPageNum;i<pageNum;i++){
            if($(".pagination li:nth-last-of-type(3)").is(".active")){
                $(".pagination li:nth-last-of-type(3)").removeClass("active");
                $(".pagination li:nth-last-of-type(4)").addClass("active");
            }
            $(".pagination li:nth-last-of-type(3)").remove();
        }
        var page = $(".pagination").find(".active").text();
        jump(page);
    }
}

/**
 * 返回操作提示，是否成功
 * @param tip
 * @param warning--可选，为true时，提示警告（红色）
 */
function slideTip(tip,warning){
    if(warning){
        $(".slideHeader",".slideTip").css("background-color","#d14836");
        $(".slideTip").css("border","1px solid #d14836");
    }else{
        $(".slideHeader",".slideTip").css("background-color","#438EB9");
        $(".slideTip").css("border","1px solid #438EB9");
    }
    $(".slideContent",".slideTip").text(tip);
    $(".slideTip").slideDown(1000,function(){
        $(".slideTip").fadeOut(5000);
    });
}


/**
 * 检查输入的数据是否合法，如果合法返回true，否则返回false
 * @returns {boolean}
 */
function checkFormat(tr){
    var realName        = $(".realName",tr).children().first().val(); //不为空
    var idCardNumber    = $(".idCardNumber",tr).children().first().val(); //不为空
    var salary          = $(".salary",tr).children().first().val();

    var notNull =[realName,idCardNumber,salary];                            //不为空的属性写进数组
    var numberOnly = [salary];                    //输入类型需要是数字的写进数组

    var test,flag=true;
    $(notNull).each(function(index,element){
        test=element.replace(/\s/g,"");  //过滤空格
        if(test===''){
            slideTip("名称和类型不能为空",true);
            flag=false;
            return false;               //break
        }
    });

    $(numberOnly).each(function(index,element){
        test=element;
        if(isNaN(test)){
            slideTip("价格，数量，重量只能为数字",true);
            flag=false;
            return false;               //break
        }
    });
    return flag;
}

