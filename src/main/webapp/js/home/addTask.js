/**
 * Created by hwen on 15/9/1.
 */

function addTask(){
    var title       = $("#title").val();

    var startPlace  = $("#seachprov").find("option:selected").text();
    startPlace     +="-"+ $("#seachcity").find("option:selected").text();
    if($("#seachdistrict").val()>0){
        startPlace    +="-"+ $("#seachdistrict").find("option:selected").text();
    }
    startPlace    +="-"+ $("#startPlace").val();

    var endPlace   = $("#seachprov2").find("option:selected").text();
    endPlace      +="-"+ $("#seachcity2").find("option:selected").text();
    if($("#seachdistrict2").val()>0){
        endPlace      +="-"+ $("#seachdistrict2").find("option:selected").text();
    }
    endPlace      +="-"+ $("#endPlace").val();

    var startDate = $("#startDate").val();
    var endDate   = $("#endDate").val();
    startDate = getDateLong(startDate);
    endDate   = getDateLong(endDate);
    var customerId = null;

    $.ajax({
        url:contextPath+"/user?json",
        type:"get",
        async:false,
        success: function (data) {
            var user=data;
            customerId=user.id;
        }
    });

    /**
     * type 默认为0（自营），state默认为0（申请中），另外1（进行中），2（已完成）
     * @type {{title: (*|jQuery), startPlace: (*|jQuery), endPlace: (*|jQuery), startDate: (*|jQuery), endDate: (*|jQuery), customer: *, type: number}}
     */
    var taskData = {"title":title,"startPlace":startPlace,"endPlace":endPlace,"startDate":startDate,
                        "endDate":endDate,"customerId":customerId,"type":0,"state":0};


    var cargos     = $(".cargo").children();
    var taskId;

    $.ajax({
        url:contextPath+"/transportTask?json",
        type:"post",
        data:taskData,
        async:false,
        success: function (data) {
            taskId=data;
        }
    });

    cargos.each(function (i,el) {
        var cargo = {"name":$(".cargoName",el).val(),"weight":$(".cargoWeight",el).val(),
                    "count":$(".cargoCount",el).val(),"taskId":taskId};
        $.ajax({
            url:contextPath+"/cargo",
            type:"post",
            data:cargo,
            async:false,
            success: function (data) {
                if(data>0){

                }else{
                    alert("添加出现错误！！");
                }
            }
        });
    });
}

function emptyTask(){
    $("#title").val("");
    $("#seachprov").val(0);
    $("#seachcity").val(0);
    $("#seachdistrict").val(0);
    $("#seachprov2").val(0);
    $("#seachcity2").val(0);
    $("#seachdistrict2").val(0);
    $("#startPlace").val("");
    $("#endPlace").val("");
    $("#startDate").val("");
    $("#endDate").val("");
    $(".cargoList").remove();
    $(".cargoName").val("");
    $(".cargoWeight").val("");
    $(".cargoCount").val("");
}