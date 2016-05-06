/**
 * Created by hwen on 15/8/24.
 */


var cargoList,cargoListLength;
var pageSize=10,pageNum=1,page=1; // pageNum--页数,pageSize--每页显示数目,page--当前页码

//tr
var hideTr = $("#hidedTbody").children().first();
var orgTr = hideTr.clone().show();
$("input[type='text']",orgTr).each(function(index,element){
    element=$(element);
    element.remove();
});

/**
 * 显示数据
 */
function showRecord(){
    pageSize= $("#pageSize").find('option:selected').text();

    $.get(contextPath+"/cargo?json",function(data,status){
        console.log(data);

        cargoListLength=data.cargoListLength;
        cargoList=$(data.cargoList);

        cargoList.each(function(index,cargo){
            if(index>(pageSize-1)) {return false;} // break;
            console.log(cargo.id);
            var newTr = orgTr.clone();
            $(".id",newTr).text(cargo.id);
            $(".name",newTr).text(cargo.name);
            $(".weight",newTr).text(cargo.weight);
            $(".price",newTr).text(cargo.price);
            $(".count",newTr).text(cargo.count);
            $(".taskId",newTr).append("<a href='/view/logistics/cgoTaskInfo.jsp?sid="+cargo.task+"'>运单详情</a>");

            $("#mainTbody").append(newTr);
        });
        pageNum=Math.ceil(cargoListLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+cargoListLength+" pageSize->"+pageSize);

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
    $.get(contextPath+"/cargo?json",{start:startIdx,size:pageSize},function(data,status){
        //console.log(data);
        cargoList=$(data.cargoList);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        cargoList.each(function(index,cargo){
            if(index>(pageSize-1)) {return false;} // break;
            var newTr = orgTr.clone();
            $(".id",newTr).text(cargo.id);
            $(".name",newTr).text(cargo.name);
            $(".weight",newTr).text(cargo.weight);
            $(".price",newTr).text(cargo.price);
            $(".count",newTr).text(cargo.count);
            $(".taskId",newTr).append("<a href='/view/logistics/cgoTaskInfo.jsp?sid="+cargo.task+"'>运单详情</a>");

            $("#mainTbody").append(newTr);

        });
    });
}

/**
 * 处理更换页面显示条目，引起的页码更新
 */
function pageChange(){
    pageNum=Math.ceil(cargoListLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
    var newPageNum=Math.ceil(cargoListLength/pageSize);

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
    var type        = $(".type",tr).children().first().val(); //不为空
    var cargoName   = $(".cargoName",tr).children().first().val(); //不为空
    var price       = $(".price",tr).children().first().val();
    var count       = $(".count",tr).children().first().val();
    var weight      = $(".weight",tr).children().first().val();
    console.log("type->"+type);
    var notNull =[type,cargoName];                            //不为空的属性写进数组
    var numberOnly = [price,count,weight];                    //输入类型需要是数字的写进数组
    console.log("notNull->"+notNull);
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