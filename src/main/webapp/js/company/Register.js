function addRecord(){
    //var id        = $(".id",tr).text(); //不为空
    //GET  /user/register
    var name       = $("#name").val(); //不为空
    var password   = $("#password").val();
    var address    = $("#address").val();
    var email      = $("#email").val();
    var phone      = $("#phone").val();
    var record = {"name":name,"password":password,"address":address,"email":email,"phone":phone
    };
    console.log(record);
    $.post(contextPath+"/user/register",record,function(data,status){
        console.log("... add..../ndata= "+ data+ "/n Status = "+ status);
        //$(".id").text(data);
    });

    //作用同$.post();
    //$.ajax({
    //    url:contextPath+"/user/register",
    //    type:"post",
    //    data:{
    //        "name":name,"password":password,"address":address,"email":email,"phone":phone
    //    },
    //    success: function (res) {
    //        // {a: 123, b: 444}
    //        //var obj = JSON.parse(res);
    //        //obj.a
    //        console.log("ccocococooc");
    //
    //    }
    //});

}
