/**
 * Created by wuhaibin on 15/8/25.
 */
function userLogin(){
    var email      = $("#email").val();
    var password   = $("#password").val();
    var record = {"email":email,"password":password
    };
    //console.log(record.name);
    console.log(record.password);
    $.get(contextPath+"/user/login",record,function(resp){
        //console.log("... add..../ndata= " "/n Status = "+ status);
        if(resp.status == 0){
            //console.log(resp);
            console.log('ssssss');
        }else {
            console.log('ddddd');
        }
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
