/**
 * Created by hwen on 15/8/18.
 */

var goodses,goodsesLength;
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
    var type        = $(".type",tr).text(); //不为空
    var goodsName   = $(".goodsName",tr).text(); //不为空
    var description = $(".description",tr).text();
    var price       = $(".price",tr).text();
    var count       = $(".count",tr).text();
    var weight      = $(".weight",tr).text();

    var record = {"type":type,"goodsName":goodsName,"description":description,"price":price,
        "count":count,"weight":weight};

    //var jsonData = JSON.stringify(data);

    console.log(record);

    $.post(contextPath+"/goods",record,function(data,status){
        console.log("data = " + data + " status = " + status);
        if(data > 0) {
            slideTip("数据添加成功");
            $(".id", tr).text(data);
            goodsesLength++;
            pageCheck();
        }else{
            alert("没有权限");
            window.location.href = contextPath+"/view/company/goodsManage.jsp";
        }
    });
}

/**
 * 更新修改后的数据
 * @param tr
 */
function updateRecord(tr){
    var id          = $(".id",tr).text();
    var type        = $(".type",tr).text(); //不为空
    var goodsName   = $(".goodsName",tr).text(); //不为空
    var description = $(".description",tr).text();
    var price       = $(".price",tr).text();
    var count       = $(".count",tr).text();
    var weight      = $(".weight",tr).text();

    var record = {"id":id,"type":type,"goodsName":goodsName,"description":description,"price":price,
        "count":count,"weight":weight,"_method":"put"};

    $.ajax({
        url:contextPath+"/goods?json",
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
    var record = {"goodsId":id,"_method":"delete"}; //后台写的是goodsId就传goodsId

    if(id>0){
        $.ajax({
            url:contextPath+"/goods?json",
            type:"post",
            data:record,
            success: function (res) {
                goodsesLength--;
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

    $.post(contextPath+"/goods?many",records,function(data){
        goodsesLength--;
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

    $.get(contextPath+"/goods?json",{start:startIdx,size:pageSize},function(data,status){

        goodsesLength=data.goodsesLength;
        goodses=$(data.goodses);

        goodses.each(function(index,goods){
            if(index>(pageSize-1)) {return false;} // break;
            console.log(goods.id);
            var newTr = orgTr.clone();
            $(".id",newTr).text(goods.id);
            $(".type",newTr).text(goods.type);
            $(".goodsName",newTr).text(goods.goodsName);
            $(".description",newTr).text(goods.description);
            $(".price",newTr).text(goods.price);
            $(".count",newTr).text(goods.count);
            $(".weight",newTr).text(goods.weight);

            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();

            $("#editItem2",newTr).show();
        });
        pageNum=Math.ceil(goodsesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+goodsesLength+" pageSize->"+pageSize);

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
    $.get(contextPath+"/goods?json",{start:startIdx,size:pageSize},function(data,status){
        //console.log(data);
        goodses=$(data.goodses);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        goodses.each(function(index,goods){
            if(index>(pageSize-1)) {return false;} // break;
            //console.log(goods.id);
            var newTr = orgTr.clone();
            $(".id",newTr).text(goods.id);
            $(".type",newTr).text(goods.type);
            $(".goodsName",newTr).text(goods.goodsName);
            $(".description",newTr).text(goods.description);
            $(".price",newTr).text(goods.price);
            $(".count",newTr).text(goods.count);
            $(".weight",newTr).text(goods.weight);

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
    pageNum=Math.ceil(goodsesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
    var newPageNum=Math.ceil(goodsesLength/pageSize);

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
    var goodsName   = $(".goodsName",tr).children().first().val(); //不为空
    var price       = $(".price",tr).children().first().val();
    var count       = $(".count",tr).children().first().val();
    var weight      = $(".weight",tr).children().first().val();

    var notNull =[type,goodsName];                            //不为空的属性写进数组
    var intOnly = [price,count,weight];                    //输入类型需要是数字的写进数组
    var test,flag=true;

    if(notNull.length>0){
        $(notNull).each(function(index,element){
            test=element.replace(/\s/g,"");  //过滤空格
            if(test===''){
                slideTip("名称和类型不能为空",true);
                flag=false;
                return false;               //break
            }
        });

    }

    if(intOnly.length>0){
        $(intOnly).each(function(index,element){
            var testInt = /^[0-9]*[1-9][0-9]*$/;
            if(!testInt.test(element)){
                slideTip("价格，数量，重量只能为正整数!!",true);
                flag=false;
                return false;               //break
            }
        });
    }
    return flag;
}