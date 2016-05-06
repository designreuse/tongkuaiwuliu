

var carBussiness,carBussinessLength;
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
        url:contextPath+"/transportTask?json",
        type:"get",
        async:false,
        success: function (data) {
            tasks =$(data.tasks);
            $(".taskSelect",tr).empty();
            $(tasks).each(function (i,el) {
                $(".taskSelect",tr).append("<option value="+el.id+">"+el.title+"</option>");
            });
        }
    });
}

/**
 * 增加数据
 * @param tr
 */
function addRecord(tr){
    var dateOfStarting      = getDateLong($("#startDateVal",tr).val()); //不为空
    var dateOfFinishing        = getDateLong($("#endDateVal",tr).val());
    var taskId         = $(".taskSelect",tr).val();
    var vehicleId      = $(".vehicleSelect",tr).val();

    $(".startDate",tr).text($("#startDateVal",tr).val());
    $(".endDate",tr).text($("#endDateVal",tr).val());

    var record = {"dateOfStarting":dateOfStarting,"dateOfFinishing":dateOfFinishing,"taskId":taskId,"vehicleId":vehicleId};

    console.log(record);

    $.post(contextPath+"/carBusiness",record,function(data,status){
        console.log("... add..../ndata= "+ data+ "/n Status = "+ status);
        $(".id",tr).text(data);
        $(".vehicle",tr).empty();
        $(".task",tr).empty();
        $(".vehicle",tr).append("<a href='" + contextPath + "/view/logistics/cbVehicleInfo.jsp?sid="+vehicleId+"'>车辆详情</a>");
        $(".task",tr).append("<a href='" + contextPath + "/view/logistics/taskInfo.jsp?sid="+taskId+"'>运单详情</a>");
        carBussinessLength++;
        slideTip("数据添加成功");
        pageCheck();
    });
}

/**
 * 更新修改后的数据
 * @param tr
 */

function updateRecord(tr){
    var id                  = $(".id",tr).text(); //不为空
    var dateOfStarting      = getDateLong($("#startDateVal",tr).val()); //不为空
    var dateOfFinishing     = getDateLong($("#endDateVal",tr).val());
    var taskId              = $(".taskSelect",tr).val();
    var vehicleId           = $(".vehicleSelect",tr).val();

    var record = {"id":id,"dateOfStarting":dateOfStarting,"dateOfFinishing":dateOfFinishing,"taskId":taskId,"vehicleId":vehicleId,
        "_method":"put"};

    $(".startDate",tr).text($("#startDateVal",tr).val());
    $(".endDate",tr).text($("#endDateVal",tr).val());

    $.ajax({
        url:contextPath+"/carBusiness",
        type:"post",
        data:record,
        async:false,
        success: function (res) {
            $(".vehicle",tr).empty();
            $(".task",tr).empty();
            $(".vehicle",tr).append("<a href='" + contextPath + "/view/logistics/cbVehicleInfo.jsp?sid="+vehicleId+"'>车辆详情</a>");
            $(".task",tr).append("<a href='" + contextPath + "/view/logistics/taskInfo.jsp?sid="+taskId+"'>运单详情</a>");
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
    var record = {"id":id,"_method":"delete"};

    if(id>0){
        $.ajax({
            url:contextPath+"/carBusiness",
            type:"post",
            data:record,
            success: function (res) {
                carBussinessLength--;
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
    $.post(contextPath+"/carBusiness?many",records,function(data){
        if(data==1){
            carBussinessLength=carBussinessLength-selectedId.length;
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

    $.get(contextPath+"/carBusiness",function(data){

        carBussinessLength = data.carBussinessLength;
        carBussiness=$(data.carBussinessList);

        carBussiness.each(function(index,carB){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(carB.id);
            $(".startDate",newTr).text( getDateStr(carB.dateOfStarting));
            $(".endDate",newTr).text( getDateStr(carB.dateOfFinishing));
            $(".vehicleId",newTr).text(carB.vehicle);
            $(".taskId",newTr).text(carB.task);

            $(".vehicle",newTr).empty();
            $(".task",newTr).empty();
            $(".vehicle",newTr).append("<a href='" + contextPath + "/view/logistics/cbVehicleInfo.jsp?sid="+carB.vehicle+"'>车辆详情</a>");
            $(".task",newTr).append("<a href='" + contextPath + "/view/logistics/taskInfo.jsp?sid="+carB.task+"'>运单详情</a>");

            $("#mainTbody").append(newTr);

            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();

            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();

            $("#editItem2",newTr).show();
        });
        pageNum=Math.ceil(carBussinessLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+carBussinessLength+" pageSize->"+pageSize);

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

    $.get(contextPath+"/carBusiness",{start:startIdx,size:pageSize},function(data,status){

        carBussiness=$(data.carBussinessList);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        carBussiness.each(function(index,carB){

            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(carB.id);
            $(".startDate",newTr).text( getDateStr(carB.dateOfStarting));
            $(".endDate",newTr).text( getDateStr(carB.dateOfFinishing));
            $(".vehicleId",newTr).text(carB.vehicle);
            $(".taskId",newTr).text(carB.task);

            $(".vehicle",newTr).empty();
            $(".task",newTr).empty();
            $(".vehicle",newTr).append("<a href='" + contextPath + "/view/logistics/cbVehicleInfo.jsp?sid="+carB.vehicle+"'>车辆详情</a>");
            $(".task",newTr).append("<a href='" + contextPath + "/view/logistics/taskInfo.jsp?sid="+carB.task+"'>运单详情</a>");


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
    pageNum=Math.ceil(carBussinessLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
    pageNum=Math.ceil(carBussinessLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
    var newPageNum=Math.ceil(carBussinessLength/pageSize);
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
