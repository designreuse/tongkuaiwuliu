/**
 * Created by wuhaibin on 15/8/18.
 */

var vehicles,vehiclesLength;
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
        url:contextPath+"/vehicleType?json",
        type:"get",
        async:false,
        success:function(data){
            var vTypes = $(data.vehicleTypes);
            $(".vehicleTypeSelect",tr).empty();
            vTypes.each(function (i,el) {
                $(".vehicleTypeSelect",tr).append("<option value="+el.id+">"+el.description+"</option>");
            });
        }
    });

    $.ajax({
        url:contextPath+"/staff?getByJob",
        type:"get",
        async:false,
        data:{"job":encodeURI("驾驶员")},
        success: function (data) {
            staffs =data;
            $(".driverSelect",tr).empty();
            $(staffs).each(function (i,el) {
                $(".driverSelect",tr).append("<option value="+el.id+">"+el.realName+"</option>");
            });
        }
    });
}

/**
 * 增加数据
 * @param tr
 */
function addRecord(tr){
    //var id        = $(".id",tr).text(); //不为空
    var carNumber   = $(".carNumber",tr).text(); //不为空
    var color       = $(".color",tr).text();
    var identity    = $(".identity",tr).text();
    var driver      = $(".driverSelect",tr).val();
    var vehicleType = $(".vehicleTypeSelect",tr).val();

    var record = {"carNumber":carNumber,"color":color,"identity":identity,"driverId":driver,"vehicleTypeId":vehicleType,
        };

    console.log(record);

    $.post(contextPath+"/vehicle",record,function(data,status){
        console.log("... add..../ndata= "+ data+ "/n Status = "+ status);
        $(".id",tr).text(data);
        $(".driver",tr).empty();
        $(".vehicleType",tr).empty();
        $(".driver",tr).append("<a href='" + contextPath + "/view/vehicle/driverInfo.jsp?sid="+driver+"'>驾驶员详情</a>");
        $(".vehicleType",tr).append("<a href='" + contextPath + "/view/vehicle/vehicleTypeInfo.jsp?sid="+vehicleType+"'>车型详情</a>");
        vehiclesLength++;
        slideTip("数据添加成功");
        pageCheck();
    });
}

/**
 * 更新修改后的数据
 * @param tr
 */

function updateRecord(tr){
    var id           = $(".id",tr).text(); //不为空
    var carNumber    = $(".carNumber",tr).text(); //不为空
    var identity     = $(".identity",tr).text();
    var color       = $(".color",tr).text();
    var driver      = $(".driverSelect",tr).val();
    var vehicleType = $(".vehicleTypeSelect",tr).val();


    console.log("vid->"+id+"--vtid = " + vehicleType+"NUM"+carNumber+"iden"+identity+"color"+color);

    var record = {"id":id,"carNumber":carNumber,"identity":identity,"color":color,"driverId":driver,"vehicleTypeId":vehicleType,
        "_method":"put"};

    $.ajax({
        url:contextPath+"/vehicle?json",
        type:"post",
        data:record,
        async:false,
        success: function (res) {
            $(".driver",tr).empty();
            $(".vehicleType",tr).empty();
            $(".driver",tr).append("<a href='" + contextPath + "/view/vehicle/driverInfo.jsp?sid="+driver+"'>驾驶员详情</a>");
            $(".vehicleType",tr).append("<a href='" + contextPath + "/view/vehicle/vehicleTypeInfo.jsp?sid="+vehicleType+"'>车型详情</a>");
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
            complete: function (res) {
                console.log("res->"+res);
                if(res==-1){
                    slideTip("删除数据失败");
                }else{
                    vehiclesLength--;
                    pageCheck();
                    slideTip("成功删除数据");
                }
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
            vehiclesLength=vehiclesLength-selectedId.length;
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

    $.get(contextPath+"/vehicle?json",function(data){

        vehiclesLength=data.vehiclesLength;
        console.log("length->"+vehiclesLength);
        vehicles=$(data.vehicles);

        vehicles.each(function(index,vehicle){
            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(vehicle.id);
            $(".carNumber",newTr).text(vehicle.carNumber);
            $(".color",newTr).text(vehicle.color);
            $(".identity",newTr).text(vehicle.identity);
            $(".driverId",newTr).text(vehicle.driver);
            $(".vehicleTypeId",newTr).text(vehicle.vehicleType);
            $(".driver",newTr).empty();
            $(".vehicleType",newTr).empty();
            $(".driver",newTr).append("<a href='" + contextPath + "/view/vehicle/driverInfo.jsp?sid="+vehicle.driver+"'>驾驶员详情</a>");
            $(".vehicleType",newTr).append("<a href='" + contextPath + "/view/vehicle/vehicleTypeInfo.jsp?sid="+vehicle.vehicleType+"'>车型详情</a>");

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

    $.get(contextPath+"/vehicle?json",{start:startIdx,size:pageSize},function(data,status){

        vehicles=$(data.vehicles);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        vehicles.each(function(index,vehicle){

            if(index>(pageSize-1)) {return false;} // break;

            var newTr = orgTr.clone();
            $(".id",newTr).text(vehicle.id);
            $(".carNumber",newTr).text(vehicle.carNumber);
            $(".color",newTr).text(vehicle.color);
            $(".identity",newTr).text(vehicle.identity);
            $(".driverId",newTr).text(vehicle.driver);
            $(".vehicleTypeId",newTr).text(vehicle.vehicleType);
            $(".driver",newTr).empty();
            $(".vehicleType",newTr).empty();
            $(".driver",newTr).append("<a href='" + contextPath + "/view/vehicle/driverInfo.jsp?sid="+vehicle.driver+"'>驾驶员详情</a>");
            $(".vehicleType",newTr).append("<a href='" + contextPath + "/view/vehicle/vehicleTypeInfo.jsp?sid="+vehicle.vehicleType+"'>车型详情</a>");


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
 * 处理更换页面显示条目，引起的页码更新
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
 * 检查输入的数据是否合法，如果合法返回true，否则返回false
 * @returns {boolean}
 */
function checkFormat(tr){
    var carNumber   = $(".carNumber",tr).children().first().val(); //不为空

    var notNull =[carNumber];                            //不为空的属性写进数组
    var intOnly = [];                                   //输入类型需要是数字的写进数组
    var test,flag=true;

    if(notNull.length>0){
        $(notNull).each(function(index,element){
            test=element.replace(/\s/g,"");  //过滤空格
            if(test===''){
                slideTip("车牌号不能为空!",true);
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
