/**
 * Created by hwen on 15/8/24.
 */
$(document).ready(function() {
    showOrg();

    var hideTr = $(".hidedTbody").children().first();
    var orgTr = hideTr.clone().show();
    function showOrg(){
        var taskId              = $("#sid").val();
        var userId              = $("#uid").val();

        $.ajax({
            url:contextPath+"/transportTask/"+taskId+"?json",
            type:"get",
            async:false,
            success: function (data) {
                var task = data;

                $("#title").text(task.title);
                $("#startPlace").text(task.startPlace);
                $("#endPlace").text(task.endPlace);
                $("#startDate").text(getDateStr(task.startDate));
                $("#endDate").text(getDateStr(task.endDate));
                switch (task.state){
                    case "0":
                        $("#state").text("申请中");
                        break;
                    case "1":
                        $("#state").text("进行中");
                        break;
                    case "3":
                        $("#state").text("完成");
                        break;
                }

                switch (task.type){
                    case "0":
                        $("#type").text("自营");
                        break;
                    case "1":
                        $("#type").text("承运");
                        break;
                }
                $("#typeVal").val(task.type);
                $("#rate").text(task.rate);
                $("#cRate").text(task.rate);
                $("#sumPrice").text(task.sumPrice);
            }
        });

        if($("#typeVal").val()==1){
            $(".carrierBox").show();
            $.ajax({
                url:contextPath+"/carrierBusiness/"+taskId+"?byTaskId",
                type:"get",
                async:false,
                success: function (data) {
                    console.log(data);
                    cb = data[0];
                    companyId=cb.company;
                    $("#isPayed").text(cb.isPayed=='false'?"待支付":"已支付");
                }
            });

            $.ajax({
                url:contextPath+"/company/"+companyId+"?json",
                type:"get",
                success: function (data) {
                    var company=data;
                    $("#company").text(company.companyName);
                }
            });
        }else{
            var vehicleId;
            var driverId;
            $(".carInfo").show();
            $.ajax({
                url:contextPath+"/carBusiness/"+taskId+"?taskId",
                type:"get",
                async:false,
                success: function (data) {
                    var carB = data[0];
                    vehicleId = carB.vehicle;
                }
            });

            $.ajax({
                url:contextPath+"/vehicle/"+vehicleId,
                type:"get",
                async:false,
                success: function (data) {
                    var v = data;
                    driverId = v.driver;
                    $("#carNumber").text(v.carNumber);
                    $("#color").text(v.color);
                }
            });

            $.ajax({
                url:contextPath+"/staff/"+driverId+"?json",
                type:"get",
                async:false,
                success: function (data) {
                    var s = data;
                    $("#driver").text(s.realName);
                }
            });
        }



        $.get(contextPath+"/user/"+userId, function (data) {
            $("#ctmName").text(data.name);
            $("#ctmPhone").text(data.phone);
            $("#ctmEmail").text(data.email);
            var addr = data.address.split("-");
            addr = addr[addr.length-1];
            $("#ctmAddress").text(addr);
        });

        $.get(contextPath+"/cargo/"+ taskId + "?taskId", {},function (data) {
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
    }

});

