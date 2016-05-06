/**
 * Created by wuhaibin on 15/8/18.
 */

var vehicleTypes,vehicleTypesLength;
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
    //var id        = $(".id",tr).text(); //不为空
    var capacity   = $(".capacity",tr).text(); //不为空
    var description = $(".description",tr).text();
    var height       = $(".height",tr).text();
    var length       = $(".length",tr).text();
    var oilType      = $(".oilType",tr).text();
    var seat        = $(".seat",tr).text();
    var width       = $(".width",tr).text();

    var record = {"capacity":capacity,"description":description,"height":height,
        "length":length,"oilType":oilType,"seat":seat,"width":width};


    //var jsonData = JSON.stringify(data);

    console.log(record);

    $.post(contextPath+"/vehicleType",record,function(data,status){
        console.log("... add..../ndata= "+ data+ "/n Status = "+ status);
        slideTip("成功添加数据");
        $(".id",tr).text(data);
        vehicleTypesLength++;
        var newPageNum=Math.ceil(vehicleTypesLength/pageSize);
        if(pageNum<newPageNum){
            pageNum=newPageNum;
            $(".pagination li:nth-last-of-type(2)").before("<li id='page'><a>"+pageNum+"</a></li>");
        }
    });
}

/**
 * 更新修改后的数据
 * @param tr
 */
function updateRecord(tr){
    var id           = $(".id",tr).text(); //不为空
    var capacity     = $(".capacity",tr).text(); //不为空
    var description  = $(".description",tr).text();
    var height       = $(".height",tr).text();
    var length       = $(".length",tr).text();
    var oilType      = $(".oilType",tr).text();
    var seat         = $(".seat",tr).text();
    var width        = $(".width",tr).text();

    var record = {"id":id,"capacity":capacity,"description":description,"height":height,"length":length,
        "oilType":oilType,"seat":seat,"width":width,"_method":"put"};
    $.ajax({
        url:contextPath+"/vehicleType?json",
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
    var record = {"vehicleTypeId":id,"_method":"delete"};

    if(id>0){
        $.ajax({
            url:contextPath+"/vehicleType?json",
            type:"post",
            data:record,
            success: function (res) {
                /**
                 * --------------------------------------
                 */
                vehicleTypesLength--;
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

    $.post(contextPath+"/vehicleType?many",records,function(data){
        if(data==1){

            /**
             * --------------------------------------
             */
            vehicleTypesLength--;
            pageCheck();
            slideTip("成功删除数据");
        }else{
            alert("deleteRecords error:"+data);
        }
    });
}

/**
 * 显示数据
 */
function showRecord(){
    pageSize= $("#pageSize").find('option:selected').text();
    // or $("#pageSize option:selected").text();
    //    $("#pageSize").val(); auto get selected's val but not text


    $.get(contextPath+"/vehicleType?json",function(data,status){

        vehicleTypesLength=data.vehicleTypesLength;
        console.log("length->"+vehicleTypesLength);
        vehicleTypes=$(data.vehicleTypes);

        vehicleTypes.each(function(index,vehicleType){
            if(index>(pageSize-1)) {return false;} // break;
            console.log(vehicleType.id);
            var newTr = orgTr.clone();
            $(".id",newTr).text(vehicleType.id);
            $(".capacity",newTr).text(vehicleType.capacity);
            $(".description",newTr).text(vehicleType.description);
            $(".height",newTr).text(vehicleType.height);
            $(".length",newTr).text(vehicleType.length);
            $(".oilType",newTr).text(vehicleType.oilType);
            $(".seat",newTr).text(vehicleType.seat);
            $(".width",newTr).text(vehicleType.width);

            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();

            $("#editItem2",newTr).show();
        });
        pageNum=Math.ceil(vehicleTypesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+vehicleTypesLength+" pageSize->"+pageSize);
        /**
         * --------------------------------------
         */
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
    var startIdx = (page-1)*pageSize+1;

    console.log("jump start:"+startIdx+"pagesize:"+pageSize);

    $.get(contextPath+"/vehicleType?json",{start:startIdx,size:pageSize},function(data,status){
        vehicleTypes=$(data.vehicleTypes);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        vehicleTypes.each(function(index,vehicleType){

            var newTr = orgTr.clone();
            $(".id",newTr).text(vehicleType.id);
            $(".capacity",newTr).text(vehicleType.capacity);
            $(".description",newTr).text(vehicleType.description);
            $(".height",newTr).text(vehicleType.height);
            $(".length",newTr).text(vehicleType.length);
            $(".oilType",newTr).text(vehicleType.oilType);
            $(".seat",newTr).text(vehicleType.seat);
            $(".width",newTr).text(vehicleType.width);


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
 * 页码更新
 */
function pageChange(){
    pageNum=Math.ceil(vehicleTypesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
 * 处理更换页面显示条目，引起的页码更新
 */
function pageChange(){
    pageNum=Math.ceil(vehicleTypesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
 * 检查输入的数据是否合法，如果合法返回true，否则返回false
 * @returns {boolean}
 */
function checkFormat(tr){
    var capacity     = $(".capacity",tr).children().first().val(); //不为空
    var description  = $(".description",tr).children().first().val();
    var height       = $(".height",tr).children().first().val();
    var length       = $(".length",tr).children().first().val();
    var oilType      = $(".oilType",tr).children().first().val();
    var seat         = $(".seat",tr).children().first().val();
    var width        = $(".width",tr).children().first().val();

    var notNull =[description,capacity];                            //不为空的属性写进数组
    var intOnly = [capacity,height,length,width,oilType,seat];                    //输入类型需要是数字的写进数组
    var test,flag=true;

    if(notNull.length>0){
        $(notNull).each(function(index,element){
            test=element.replace(/\s/g,"");  //过滤空格
            if(test===''){
                slideTip("装载量，描述不能为空！",true);
                flag=false;
                return false;               //break
            }
        });

    }

    if(intOnly.length>0){
        $(intOnly).each(function(index,element){
            var testInt = /^[0-9]*[1-9][0-9]*$/;
            if(!testInt.test(element)){
                slideTip("规格参数只能为正整数!!",true);
                flag=false;
                return false;               //break
            }
        });
    }
    return flag;
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
 * 处理添加，删除数据引起的页码改变
 */
function pageCheck(){
    var newPageNum=Math.ceil(vehicleTypesLength/pageSize);
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
