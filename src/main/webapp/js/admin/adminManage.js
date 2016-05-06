/**
 * Created by hwen on 15/9/2.
 */

var admins,adminLength;
var pageSize=10,pageNum=1,page=1; // pageNum--页数,pageSize--每页显示数目,page--当前页码

//tr
var hideTr = $("#hidedTbody").children().first();
var orgTr = hideTr.clone().show();
$("input[type='text']",orgTr).each(function(index,element){
    element=$(element);
    element.remove();
});


/**
 * 增加数据
 * @param tr
 */
function addRecord(tr){
    var type        = $(".selectType",tr).val(); //不为空
    var adminName   = $(".adminName",tr).text(); //不为空
    var password    = $(".adminPassword",tr).text();


    var record = {"type":type,"adminName":adminName,"password":password};

    //var jsonData = JSON.stringify(data);

    console.log(record);
    $(".type",tr).text($(".selectType",tr).find("option:selected").text());
    $.post(contextPath+"/admin",record,function(data,status){
        slideTip("数据添加成功");
        $(".id",tr).text(data);
        adminLength++;
        pageCheck();
    });
}

/**
 * 更新修改后的数据
 * @param tr
 */
function updateRecord(tr){
    var id          = $(".id",tr).text();
    var type        = $(".type",tr).text(); //不为空
    var adminName   = $(".adminName",tr).text(); //不为空
    var description = $(".description",tr).text();
    var price       = $(".price",tr).text();
    var count       = $(".count",tr).text();
    var weight      = $(".weight",tr).text();

    var record = {"id":id,"type":type,"adminName":adminName,"description":description,"price":price,
        "count":count,"weight":weight,"_method":"put"};

    $.ajax({
        url:contextPath+"/admin?json",
        type:"post",
        data:record,
        success: function (res) {
            slideTip("成功修改数据");
        }
    });
}

/**
 * 删除一条记录
 * @param tr
 */
function deleteRecord(tr){
    var id     = $(".id",tr).text();
    var record = {"adminId":id,"_method":"delete"}; //后台写的是adminId就传adminId

    if(id>0){
        $.ajax({
            url:contextPath+"/admin",
            type:"post",
            data:record,
            success: function (res) {
                adminLength--;
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

    var records = {"ids":idstr,"_method":"delete"};

    $.post(contextPath+"/admin?many",records,function(data){
        adminLength--;
        pageCheck();
        slideTip("成功删除数据");
    });
}

/**
 * 显示数据
 */
function showRecord(){
    pageSize= $("#pageSize").find('option:selected').text();
    var startIdx = 0;

    $.get(contextPath+"/admin",{start:startIdx,size:pageSize},function(data,status){

        adminLength=data.adminLength;
        admins=$(data.admins);

        admins.each(function(index,admin){
            if(index>(pageSize-1)) {return false;} // break;
            console.log(admin.id);
            var newTr = orgTr.clone();
            $(".id",newTr).text(admin.id);
            $(".type",newTr).text(getType(admin.type));
            $(".adminName",newTr).text(admin.adminName);
            $(".adminPassword",newTr).text("******");

            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();

            $("#editItem2",newTr).show();
        });
        pageNum=Math.ceil(adminLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+adminLength+" pageSize->"+pageSize);

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

    $.get(contextPath+"/admin",{start:startIdx,size:pageSize},function(data,status){

        admins=$(data.admins);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        admins.each(function(index,admin){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(admin.id);
            $(".type",newTr).text(getType(admin.type));
            $(".adminName",newTr).text(admin.adminName);
            $(".adminPassword",newTr).text("******");
            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();

            $("#editItem2",newTr).show();
        });
    });
}

/**
 * 处理更换页面显示条目，引起的页码更新
 */
function pageChange(){
    pageNum=Math.ceil(adminLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
    var newPageNum=Math.ceil(adminLength/pageSize);

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
    var password        = $(".adminPassword",tr).children().first().val(); //不为空
    var adminName   = $(".adminName",tr).children().first().val(); //不为空

    var notNull =[password,adminName];                            //不为空的属性写进数组
    var numberOnly = [];                    //输入类型需要是数字的写进数组
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

function getType(value){
    switch (value){
        case "1":
            return "超级管理员";
        case "2":
            return "物资管理员";
        case "3":
            return "运输管理员";
        case "4":
            return "车辆管理员";
        case "5":
            return "人力资源管理员";
        case "6":
            return "公司信息管理员";
        case "7":
            return "财务管理员";
    }
}