/**
 * Created by wuhaibin on 15/8/24.
 */
/**
 * Created by wuhaibin on 15/8/18.
 */

var vehicleRepairs,vehicleRepairsLength;
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
    var vehicle_id   = $(".vehicle_id",tr).text(); //不为空
    var goodsRecord_id       = $(".goodsRecord_id",tr).text();
    var reasonDesc    = $(".reasonDesc",tr).text();
    var repairman      = $(".repairman",tr).text();

    var record = {"vehicle_id":vehicle_id,"goodsRecord_id":goodsRecord_id,"reasonDesc":reasonDesc,"repairman":repairman
    };

    //var jsonData = JSON.stringify(data);

    console.log(record);

    //作用同$.post();
    //$.ajax({
    //    url:contextPath+"/goods",
    //    type:"get",
    //    data:{
    //        goodsId: 1234
    //    },
    //    success: function (res) {
    //        // {a: 123, b: 444}
    //        var obj = JSON.parse(res);
    //        obj.a
    //    }
    //});

    $.post(contextPath+"/vehicle",record,function(data,status){
        console.log("... add..../ndata= "+ data+ "/n Status = "+ status);
        $(".id",tr).text(data);
        vehiclesLength++;
        /**
         * --------------------------------------
         */
        slideTip("数据添加成功");
        pageCheck();
    });
}

/**
 * 更新修改后的数据
 * @param tr
 */

function updateRecord(tr){
    var id        = $(".id",tr).text(); //不为空
    var carNumber   = $(".carNumber",tr).text(); //不为空
    var identity = $(".identity",tr).text();
    var driver       = $(".driver",tr).text();
    //var vehicleType       = $(".vehicleType",tr).text();


    var record = {"ID":id,"carNumber":carNumber,"identity":identity,"driver":driver,
        "_method":"put"};

    $.ajax({
        url:contextPath+"/vehicle?json",
        type:"post",
        data:record,
        success: function (res) {
            /**
             * --------------------------------------
             */
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
    var record = {"vehicleId":id,"_method":"delete"}; //后台写的是goodsId就传goodsId

    if(id>0){
        $.ajax({
            url:contextPath+"/vehicle?json",
            type:"post",
            data:record,
            success: function (res) {
                /**
                 * --------------------------------------
                 */
                oilBuysLength--;
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
    $.post(contextPath+"/vehicle?many",records,function(data){
        if(data==1){
            /**
             * --------------------------------------
             */
            oilBuysLength--;
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

    $.get(contextPath+"/vehicle?json",function(data,status){
        //console.log("goodsesLength->"+data.goodsesLength);

        vehiclesLength=data.vehiclesLength;
        console.log("length->"+vehiclesLength);
        vehicles=$(data.vehicles);

        vehicles.each(function(index,vehicle){
            if(index>(pageSize-1)) {return false;} // break;
            console.log(vehicle.id);
            var newTr = orgTr.clone();
            $(".id",newTr).text(vehicle.id);
            $(".carNumber",newTr).text(vehicle.carNumber);
            $(".color",newTr).text(vehicle.color);
            $(".identity",newTr).text(vehicle.identity);
            $(".driver",newTr).text(vehicle.driver);
            //$(".vehicleType",newTr).text(vehicle.vehicleType);

            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();

            $("#editItem2",newTr).show();
        });
        pageNum=Math.ceil(vehiclesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+vehiclesLength+" pageSize->"+pageSize);
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
    var startIdx = (page-1)*pageSize;
    //console.log("now page is->"+page);
    $.get(contextPath+"/vehicle?json",{start:startIdx,size:pageSize},function(data,status){
        //console.log(data);
        vehicles=$(data.vehicles);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        vehicles.each(function(index,vehicle){
            if(index>(pageSize-1)) {return false;} // break;
            //console.log(goods.id);
            var newTr = orgTr.clone();
            $(".id",newTr).text(vehicle.id);
            $(".carNumber",newTr).text(vehicle.carNumber);
            $(".color",newTr).text(vehicle.color);
            $(".identity",newTr).text(vehicle.identity);
            $(".driver",newTr).text(vehicle.driver);
            //$(".vehicleType",newTr).text(vehicle.vehicleType);
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
    pageNum=Math.ceil(vehiclesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
 * 删除一条记录
 * @param tr
 */
function deleteRecord(tr){
    var id     = $(".id",tr).text();
    var record = {"vehicleId":id,"_method":"delete"}; //后台写的是goodsId就传goodsId

    if(id>0){
        $.ajax({
            url:contextPath+"/vehicle?json",
            type:"post",
            data:record,
            success: function (res) {
                alert("delete item success->"+id);
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
    $.post(contextPath+"/vehicle?many",records,function(data){
        if(data==1){
            console.log("delete ids ok");
        }else{
            alert("deleteRecords error:"+data);
        }
    });
}

/**
 * 处理更换页面显示条目，引起的页码更新
 */
function pageChange(){
    pageNum=Math.ceil(oilBuysLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
    //var type        = $(".type",tr).children().first().val(); //不为空
    //var goodsName   = $(".goodsName",tr).children().first().val(); //不为空
    //var price       = $(".price",tr).children().first().val();
    //var count       = $(".count",tr).children().first().val();
    //var weight      = $(".weight",tr).children().first().val();
    //console.log("type->"+type);
    var notNull =[];                            //不为空的属性写进数组
    var numberOnly = [];                    //输入类型需要是数字的写进数组
    console.log("notNull->"+notNull);
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
    var newPageNum=Math.ceil(vehiclesLength/pageSize);
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
