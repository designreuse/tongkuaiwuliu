/**
 * Created by hwen on 15/8/24.
 */
$(document).ready(function() {

    var flag=true;

    $("#saveEdit",".btnArea").click(function () {
        var taskId          = $("#sid").val();
        var type            = $("#type").val();
        var rate            = $("#rate").val();
        var startPlace      = $("#startPlace").val();
        var endPlace        = $("#endPlace").val();
        var title           = $("#title").val();
        var sumPrice        = $("#sumPrice").val();
        var state           = $("#taskState").val();
        var userId          = $("#uid").val();
        var queryId         = $("#queryId").val();
        var taskStartDate   = $("#taskStartDate").val();
        var taskEndDate   = $("#taskEndDate").val();

        var record = {"id":taskId,"type":type,"rate":rate,"startPlace":startPlace,"endPlace":endPlace,
            "title":title,"state":state,"sumPrice":sumPrice,"customerId":userId,"queryId":queryId,
            "startDate":taskStartDate,"endDate":taskEndDate,"_method":"put"};

        $.ajax({
            url:contextPath+"/transportTask",
            data:record,
            type:"post",
            async:false,
            complete: function (data,status) {
            }
        });

        var cid     = $("#cid").val();
        var company = $("#company").val();
        var isPayed = $("#isPayed").val();

        if(cid==''){
            var record2 = {"companyId":company,"isPayed":isPayed,"transTaskId":taskId};
            $.ajax({
                url:contextPath+"/carrierBusiness?json",
                type:"post",
                data:record2,
                success: function () {
                    location.href="transTask.jsp";
                }
            });
        }else{
            var record2 = {"id":cid,"companyId":company,"isPayed":isPayed,"transTaskId":taskId,"_method":"put"};
            $.ajax({
                url:contextPath+"/carrierBusiness?json",
                type:"post",
                data:record2,
                success: function () {
                    location.href="transTask.jsp";
                }
            });
        }


        if(type==0){
            if($("#carBusinessId").val()==''){
                var record3 = {
                    "dateOfStarting":getDateLong($("#startDate").val()),
                    "dateOfFinishing":getDateLong($("#endDate").val()),
                    "taskId":taskId,
                    "vehicleId":$("#selectVehicle").val()
                };
                $.ajax({
                    url:contextPath+"/carBusiness",
                    type:"post",
                    data:record3
                });
            }else{
                var record3 = {
                    "id":$("#carBusinessId").val(),
                    "dateOfStarting":getDateLong($("#startDate").val()),
                    "dateOfFinishing":getDateLong($("#endDate").val()),
                    "taskId":taskId,
                    "vehicleId":$("#selectVehicle").val(),
                    "_method":"put"
                };
                $.ajax({
                    url:contextPath+"/carBusiness",
                    type:"post",
                    data:record3
                });
            }
        }
    });

    showOrg();

    var hideTr = $(".hidedTbody").children().first();
    var orgTr = hideTr.clone().show();
    function showOrg(){
        var taskId              = $("#sid").val();
        var userId              = $("#uid").val();
        var companyId;

        if(taskId>0){
            $.ajax({
                url:contextPath+"/transportTask/"+taskId+"?json",
                type:"get",
                async:false,
                success: function (data) {
                    var task = data;
                    $("#title").val(task.title);
                    $("#startPlace").val(task.startPlace);
                    $("#endPlace").val(task.endPlace);
                    $("#taskState").val(task.state);
                    $("#type").val(task.type);
                    $("#rate").val(task.rate);
                    $("#sumPrice").val(task.sumPrice);
                    $("#queryId").val(task.queryId);
                    $("#taskStartDate").val(task.startDate);
                    $("#taskEndDate").val(task.endDate);
                }
            });

            $.get(contextPath+"/user/"+userId, function (data) {
                $("#ctmName").text(data.name);
                $("#ctmPhone").text(data.phone);
                $("#ctmEmail").text(data.email);
                var addr = data.address.split("-");
                addr = addr[addr.length-1];
                $("#ctmAddress").text(addr);
            });

            $.get(contextPath+"/cargo/"+ taskId + "?taskId", function (data) {
                
                var cargoList=$(data.cargoList);
                cargoList.each(function (index,cargo) {
                    var newTr = orgTr.clone();
                    $(".cName",newTr).text(cargo.name);
                    $(".cWeight",newTr).text(cargo.weight);
                    $(".cPrice",newTr).text(cargo.price);
                    $(".cCount",newTr).text(cargo.count);

                    $(".mainTbody").append(newTr);
                });
            });

            /**
             * get carrB by taskId
             */
            if($("#type").val()==1){
                $.ajax({
                    url:contextPath+"/carrierBusiness/"+taskId+"?byTaskId",
                    type:"get",
                    async:false,
                    success: function (data) {
                        console.log(data);
                        cb = data[0];
                        companyId=cb.company;
                        $("#cid").val(cb.id);
                        $("#isPayed").empty();
                        $("#isPayed").append("<option value='0'>待支付</option>");
                        $("#isPayed").append("<option value='1'>已支付</option>");
                        $("#isPayed").val(cb.isPayed=='false'?0:1);
                    }
                });

                $.ajax({
                    url:contextPath+"/company/?json",
                    type:"get",
                    success: function (data) {
                        companies=$(data.companies);
                        $("#company").empty();
                        companies.each(function (i,el) {
                            $("#company").append("<option value="+el.id+">"+el.companyName+"</option>");
                        });
                        $("#company").val(companyId);
                    }
                });
            }else{
                $("#carBusinessForm").show();
                flag=false;
                $.ajax({
                    url:contextPath+"/vehicle?json",
                    type:"get",
                    async:false,
                    success: function (data) {
                        var vehicles = $(data.vehicles);
                        vehicles.each(function (i,el) {
                            $("#selectVehicle").append("<option value='"+el.id+"'>"+el.carNumber+"</option>")
                        });
                    }
                });
                $.ajax({
                    url:contextPath+"/carBusiness/"+taskId+"?taskId",
                    type:"get",
                    async:false,
                    success: function (data) {
                        var carB = data[0];
                        if(carB!=''){
                            $("#carBusinessId").val(carB.id);
                            $("#startDate").val(getDateStr(carB.dateOfStarting));
                            $("#endDate").val(getDateStr(carB.dateOfFinishing));
                            $("#selectVehicle").val(carB.vehicle);
                        }
                    }
                });
            }
        }else{
            location.href="transTask.jsp";
        }
    }

    $("#type").change(function () {
        checkType();
    });

    function checkType(){
        if($("#type").val()==1){
            $("#carBusinessForm").slideUp(1000);
            $.ajax({
                url:contextPath+"/company?json",
                type:"get",
                success: function (data) {
                    companies=$(data.companies);
                    $("#company").empty();
                    companies.each(function (i,el) {
                        $("#company").append("<option value="+el.id+">"+el.companyName+"</option>");
                    });
                }
            });
            $("#isPayed").empty();
            $("#isPayed").append("<option value='0'>待支付</option>");
            $("#isPayed").append("<option value='1'>已支付</option>");
        }else{
            $("#company").empty();
            $("#isPayed").empty();
            $("#company").append("<option>无</option>");
            $("#isPayed").append("<option>无</option>");

            $("#carBusinessForm").slideDown(1000);
            if(flag){
                $.ajax({
                    url:contextPath+"/vehicle?json",
                    type:"get",
                    success: function (data) {
                        var vehicles = $(data.vehicles);
                        vehicles.each(function (i,el) {
                            $("#selectVehicle").append("<option value='"+el.id+"'>"+el.carNumber+"</option>")
                        });
                    }
                });
            }
        }
    }
});
