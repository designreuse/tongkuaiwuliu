/**
 * Created by hwen on 15/8/18.
 */

var companies,companyLength;
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
    var record = {"companyId":id,"_method":"delete"};

    if(id>0){
        $.ajax({
            url:contextPath+"/company?json",
            type:"post",
            data:record,
            success: function (res,status) {
                companyLength--;
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

    $.post(contextPath+"/company?many",records,function(data){
        companyLength--;
        pageCheck();
        slideTip("成功删除数据");
    });
}

/**
 * 显示数据
 */
function showRecord(){
    pageSize= $("#pageSize").find('option:selected').text();

    $.get(contextPath+"/company?json",function(data){

        companyLength=data.companyLength;
        companies=$(data.companies);

        companies.each(function(index,company){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();

            $(".id",newTr).text(company.id);
            $(".companyName",newTr).text(company.companyName);
            $(".phoneNumber",newTr).text(company.phoneNumber);
            $(".corporation",newTr).text(company.corporation);
            var intro = company.introduction;
            intro = intro.substr(0,18);
            $(".introduction",newTr).text(intro);

            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();

            $("#editItemNewPage",newTr).show();
        });
        pageNum=Math.ceil(companyLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+companyLength+" pageSize->"+pageSize);

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
    var startIdx = (page-1)*pageSize;
    $.get(contextPath+"/company?json",{start:startIdx,size:pageSize},function(data,status){

        companies=$(data.companies);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        companies.each(function(index,company){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();

            $(".id",newTr).text(company.id);
            $(".companyName",newTr).text(company.companyName);
            $(".phoneNumber",newTr).text(company.phoneNumber);
            $(".corporation",newTr).text(company.corporation);
            var intro = company.introduction;
            intro = intro.substr(0,18);
            $(".introduction",newTr).text(intro);

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
    pageNum=Math.ceil(companyLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
    var newPageNum=Math.ceil(companyLength/pageSize);

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
        //test=element.replace(/\s/g,"");  //过滤空格
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


