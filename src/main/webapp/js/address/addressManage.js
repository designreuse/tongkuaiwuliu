/**
 * Created by Mklaus on 15/8/24.
 */

/**
 * Created by hwen on 15/8/18.
 */

var addresses,addressesLength;
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
    var areaName    = $(".areaName",tr).text();
    var level       = $(".selectLevel",tr).val();
    var pinyin      = $(".pinyin",tr).text();
    var parent      = $(".selectParent",tr).val();

    var record = {"areaName":areaName,"level":level,"pinyin":pinyin,"parentId":parent};

    console.log(record);

    $.ajax({
        url:contextPath+"/address?json",
        type:"post",
        data:record,
        complete: function (data,status) {
            var selectLevel = $("#selectLevel",tr).find("option:selected").text();
            var selectParent = $("#selectParent",tr).find("option:selected").text();
            $(".level",tr).text(selectLevel);
            $(".parent",tr).text(selectParent);
            slideTip("数据添加成功");
            $(".id",tr).text(data);
            addressesLength++;
            pageCheck();
        }
    });
}

/**
 * 更新修改后的数据
 * @param tr
 */
function updateRecord(tr){
    var id          = $(".id",tr).text();
    var areaName    = $(".areaName",tr).text();
    var level       = $(".selectLevel",tr).val();
    var pinyin      = $(".pinyin",tr).text();
    var parent      = $(".selectParent",tr).val();

    var record = {"id":id,"areaName":areaName,"level":level,"pinyin":pinyin,"parentId":parent,"_method":"put"};

    $.ajax({
        url:contextPath+"/address?json",
        type:"post",
        data:record,
        async:false,
        complete: function (res) {
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
    var record = {"addressId":id,"_method":"delete"}; //后台写的是addressId就传addressId

    if(id>0){
        $.ajax({
            url:contextPath+"/address?json",
            type:"post",
            data:record,
            complete: function (res) {
                addressesLength--;
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

    $.ajax({
        url:contextPath+"/address?many",
        data:records,
        type:"post",
        complete: function () {
            addressesLength--;
            pageCheck();
            slideTip("成功删除数据");
        }
    });
}

/**
 * 显示数据
 */
function showRecord(){
    pageSize= $("#pageSize").find('option:selected').text();

    $.get(contextPath+"/address?json",function(data,status){
        addressesLength=data.addressLength;
        addresses=$(data.addresses);

        addresses.each(function(index,address){
            if(index>(pageSize-1)) {return false;} // break;
            console.log(address.id);
            var newTr = orgTr.clone();
            $(".id",newTr).text(address.id);
            $(".areaName",newTr).text(address.areaName);
            $(".pinyin",newTr).text(address.pinyin);

            $(".levelValue",newTr).text(address.level);
            $(".parentValue",newTr).text(address.parent);
            switch (address.level){
                case "1":
                    $(".level",newTr).text("省（自治区、直辖市）");
                    break;
                case "2":
                    $(".level",newTr).text("市");
                    break;
                case "3":
                    $(".level",newTr).text("县、区");
                    break;
                case "4":
                    $(".level",newTr).text("乡、镇");
                    break;
                default :
                    $(".level",newTr).text("乡镇以下");
                    break;
            }
            if(address.parent==undefined){
                $(".parent",newTr).text("无");
            }else{
                $(".parent",newTr).text(address.parent);
            }


            $("#mainTbody").append(newTr);
            $("#saveAdd",newTr).hide();
            $("#reset",newTr).hide();
            $("#deleteItem2",newTr).show();
            $("#saveEdit",newTr).hide();
            $("#cancal",newTr).hide();
            $("#editItem2",newTr).show();
        });
        pageNum=Math.ceil(addressesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
        console.log("page size->"+pageNum+" lenght->"+addressesLength+" pageSize->"+pageSize);

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
    console.log("option change,pageSize is->"+pageSize);
    var startIdx = (page-1)*pageSize;
    console.log("now page is->"+page);
    $.get(contextPath+"/address?json",{start:startIdx,size:pageSize},function(data,status){

        addresses=$(data.addresses);
        $("#mainTbody").children().remove();       //移除旧记录 remove old record
        addresses.each(function(index,address){
            if(index>(pageSize-1)) {return false;} // break;
            var newTr = orgTr.clone();
            $(".id",newTr).text(address.id);
            $(".areaName",newTr).text(address.areaName);
            $(".pinyin",newTr).text(address.pinyin);


            $(".levelValue",newTr).text(address.level);
            $(".parentValue",newTr).text(address.parent);
            switch (address.level){
                case "1":
                    $(".level",newTr).text("省（自治区、直辖市）");
                    break;
                case "2":
                    $(".level",newTr).text("市");
                    break;
                case "3":
                    $(".level",newTr).text("县、区");
                    break;
                case "4":
                    $(".level",newTr).text("乡、镇");
                    break;
                default :
                    $(".level",newTr).text("乡镇以下");
                    break;
            }
            if(address.parent==undefined){
                $(".parent",newTr).text("无");
            }else{
                $(".parent",newTr).text(address.parent);
            }

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
    pageNum=Math.ceil(addressesLength/pageSize); //向上取整, Math.floor():向下,Math.round():四舍五入
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
    var newPageNum=Math.ceil(addressesLength/pageSize);

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
    var areaName     = $(".areaName",tr).children().first().val(); //不为空
    var level        = $(".selectLevel",tr).val(); //不为空

    var notNull =[areaName,level];                            //不为空的属性写进数组
    var numberOnly = [];                    //输入类型需要是数字的写进数组
    console.log("notNull->"+notNull);
    var test,flag=true;
    $(notNull).each(function(index,element){
        test=element.replace(/\s/g,"");  //过滤空格
        if(test===''){
            slideTip("名称和级别不能为空",true);
            flag=false;
            return false;               //break
        }
    });

    $(numberOnly).each(function(index,element){
        test=element;
        if(isNaN(test)){
            slideTip("",true);
            flag=false;
            return false;               //break
        }
    });
    return flag;
}

/**
 *
 *
 * @param selected -- level value
 * @param tr
 */
function levelChange(selected,tr){

    $.get(contextPath+"/address?json",function(data,status){
        addressesLength=data.addressLength;
        addresses=$(data.addresses);

        addresses.each(function(index,address){
            console.log(address.level);
            if(address.level==(selected-1)){
                $("#selectParent",tr).append("<option value="+address.id+">"+address.areaName+"</option>");
            }
        });
    });
}

$("#mainTbody").on("change","#selectLevel", function () {
    var tr = $(this).parent().parent();
    var selected = $(this).val();
    var selected = parseInt(selected);
    console.log("selected val is"+selected);
    $("#selectParent",tr).empty();
    levelChange(selected,tr);
});