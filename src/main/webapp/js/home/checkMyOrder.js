/**
 * Created by hwen on 15/9/1.
 */
var taskLength=0;

function checkOrder(){
    var customerId;
    var taskId;
    $.ajax({
        url:contextPath+"/user?json",
        type:"get",
        async:false,
        success: function (data) {
            var user=data;
            customerId=user.id;
        }
    });

    $.ajax({
        url:contextPath+"/transportTask/"+customerId+"?getByCusId",
        type:"get",
        async:false,
        success: function (data) {
            tasks=$(data);
            tasks.each(function (i,el) {
                var orderBox = $(".hideOrderBox").children().first().clone().show();
                $("#queryId",orderBox).text("运单号："+el.queryId);
                getDateStr(el.create_time);
                $("#create_time",orderBox).text("下单时间："+getDateStr(el.create_time));
                $("#orderStartPlace",orderBox).text(el.startPlace);
                $("#orderEndPlace",orderBox).text(el.endPlace);
                $("#orderTitle",orderBox).text(el.title);
                switch(el.state){
                    case '0':
                        $("#orderState",orderBox).text("申请中");
                        orderBox.find(".content").css("border","2px solid #e3722e");
                        $(".content",orderBox).find("i").css("color","#e3722e");
                        $(".content",orderBox).find("span").css("color","#e3722e");
                        taskLength++;
                        break;
                    case '1':
                        $("#orderState",orderBox).text("进行中");
                        orderBox.find(".content").css("border","2px solid #8AC007");
                        $(".content",orderBox).find("i").css("color","#8AC007");
                        $(".content",orderBox).find("span").css("color","#8AC007");
                        taskLength++;
                        break;
                    case '2':
                        $("#orderState",orderBox).text("已完成");
                        break;
                }


                $.ajax({
                    url:contextPath+"/cargo/"+el.id+"?taskId",
                    type:"get",
                    async:false,
                    success:function(data){
                        var cargos = $(data.cargoList);
                        cargos.each(function (i,el) {
                            var html= "<br><label class='col-sm-2'>"+el.name+"</label><label class='col-sm-2'>"+
                                el.weight+"</label><label class='col-sm-2'>"+el.count+"</label>";
                            $(".cargos",orderBox).append(html);
                        });
                    }
                });


                $(".myOrderBox").prepend(orderBox);
            });
        }
    });

}