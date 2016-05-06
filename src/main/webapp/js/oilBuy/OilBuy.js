/**
 * Created by wuhaibin on 15/8/24.
 */

var oilBuys,oilBuysLength;
var pageSize=10,pageNum=1,page=1; // pageNum--页数,pageSize--每页显示数目,page--当前页码

//tr
var hideTr = $("#hidedTbody").children().first();
var orgTr = hideTr.clone().show();
$("input[type='text']",orgTr).each(function(index,element){
    element=$(element);
    element.remove();
});


function showDV(tr){
    $.ajax({
        url:contextPath+"/vehicle?json",
        type:"get",
        async:false,
        success:function(data){
            var vehicles = $(data.vehicles);
            $(".vehicleSelect",tr).empty();
            vehicles.each(function (i,el) {
                $(".vehicleSelect",tr).append("<option value="+el.id+">"+el.carNumber+"</option>");
            });
        }
    });

    $.ajax({
        url:contextPath+"/staff?json",
        type:"get",
        async:false,
        success: function (data) {
            staffs =$(data.staffs);
            $(".staffSelect",tr).empty();
            $(staffs).each(function (i,el) {
                $(".staffSelect",tr).append("<option value="+el.id+">"+el.realName+"</option>");
            });
        }
    });
}

/**
 * 增加数据
 * @param tr
 */
function addRecord(tr){
    var vehicle     = $(".vehicleSelect",tr).val(); //不为空
    var staff       = $(".staffSelect",tr).val();
    var litre       = $(".litre",tr).text();
    var price       = $(".price",tr).text();

    var record = {"vehicleId":vehicle,"staffId":staff,"litre":litre,"price":price
    };


    console.log(record);


    $.post(contextPath+"/oilBuy?json",record,function(data,status){
        console.log("... add..../ndata= "+ data+ "/n Status = "+ status);
        $(".id",tr).text(data);
        $(".vehicle",tr).empty();
        $(".staff",tr).empty();
        $(".vehicle",tr).append("<a href='/view/oilBuy/vehicleInfo.jsp?sid="+vehicle+"'>车辆详情</a>");
        $(".staff",tr).append("<a href='/staff/"+staff+"'>员工详情</a>");
        oilBuysLength++;
        slideTip("数据添加成功");
        pageCheck();
    });
}

/**
 * 更新修改后的数据
 * @param tr
 */

function updateRecord(tr){
    var id          = $(".id",tr).text(); //不为空
    var vehicle     = $(".vehicleSelect",tr).val(); //不为空
    var staff       = $(".staffSelect",tr).val();
    var litre       = $(".litre",tr).text();
    var price       = $(".price",tr).text();

    var record = {"id":id,"vehicleId":vehicle,"staffId":staff,"litre":litre,"price":price,
        "_method":"put"};

    console.log(record);
    $.ajax({
        url:contextPath+"/oilBuy?json",
        type:"post",
        data:record,
        success: function (res) {
            $(".vehicle",tr).empty();
            $(".staff",tr).empty();
            $(".vehicle",tr).append("<a href='/view/oilBuy/vehicleInfo.jsp?sid="+vehicle+"'>车辆详情</a>");
            $(".staff",tr).append("<a href='/staff/"+staff+"'>员工详情</a>");
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
    var record = {"oilBuyId":id,"_method":"delete"}; //后台写的是goodsId就传goodsId

    if(id>0){
        $.ajax({
            url:contextPath+"/oilBuy?json",
            type:"post",
            data:record,
            success: function (res) {
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

    $.post(contextPath+"/oilBuy?many",records,function(data){
        if(data==1){
            oilBuysLength=oilBuysLength-selectedId.length;
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

    $.get(contextPath+"/oilBuy?json",function(data){

        oilBuysLength=data.oilBuysLength;
        console.log("length->"+oilBuysLength);
        oilBuys=$(data.oilBuys);

        oilBuys.each(function(index,oilBuy){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(oilBuy.id);
            $(".vehicleId",newTr).text(oilBuy.vehicle);
            $(".staffId",newTr).text(oilBuy.staff);
            $(".vehicle",newTr).empty();
            $(".staff",newTr).empty();
            $(".vehicle",newTr).append("<a href='/view/oilBuy/vehicleInfo.jsp?sid="+oilBuy.vehicle+"'>车辆详情</a>");
            $(".staff",newTr).append("<a href='/staff/"+oilBuy.staff+"'>员工详情</a>");
            $(".litre",newTr).text(oilBuy.litre);
            $(".price",newTr).text(oilBuy.price);

            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();
            $("#editItem2",newTr).show();
        });
        pageNum=Math.ceil(oilBuysLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+oilBuysLength+" pageSize->"+pageSize);
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

    $.get(contextPath+"/oilBuy?json",{start:startIdx,size:pageSize},function(data,status){

        oilBuys=$(data.oilBuys);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        oilBuys.each(function(index,oilBuy){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(oilBuy.id);
            $(".vehicleId",newTr).text(oilBuy.vehicle);
            $(".staffId",newTr).text(oilBuy.staff);
            $(".vehicle",newTr).empty();
            $(".staff",newTr).empty();
            $(".vehicle",newTr).append("<a href='/view/oilBuy/vehicleInfo.jsp?sid="+oilBuy.vehicle+"'>车辆详情</a>");
            $(".staff",newTr).append("<a href='/staff/"+oilBuy.staff+"'>员工详情</a>");
            $(".litre",newTr).text(oilBuy.litre);
            $(".price",newTr).text(oilBuy.price);

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

    var litre       = $(".litre",tr).children().first().val();
    var price       = $(".price",tr).children().first().val();

    var notNull =[];                            //不为空的属性写进数组
    var intOnly = [litre,price];                    //输入类型需要是数字的写进数组
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
            if(isNaN(element)){
                slideTip("升数，花费只能为正数!!",true);
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
    var newPageNum=Math.ceil(oilBuysLength/pageSize);

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
