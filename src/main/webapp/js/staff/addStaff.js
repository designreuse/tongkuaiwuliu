/**
 * Created by hwen on 15/8/23.
 */
$(document).ready(function () {
    $("#saveAdd", ".btnArea").click(function () {
        console.log("save add click");

        var realName = $("#realName").val();    //不为空
        var phoneNumber = $("#phoneNumber").val();
        var gender = $("#gender").val();
        var job = $("#job").val();
        var imgUrl = $("#imgUrl").val();
        var idCardNumber = $("#idCardNumber").val();  //不为空
        var levelOfEducation = $("#levelOfEducation").val();
        var politicalGroup = $("#politicalGroup").val();
        var state = $("#state").val();
        var salary = $("#salary").val();
        var dateOfHire = getDateLong($("#dateOfHire").val());

        /**
         * 注意，还没有添加dateOfBirth属性，等待后台处理 dateOfBirth 字符串,同样还有 dateOfHire
         * @type {{realName: (*|jQuery), phoneNumber: (*|jQuery), gender: (*|jQuery)}}
         */
        var record = {
            "realName": realName,
            "phoneNumber": phoneNumber,
            "gender": gender,
            "job": job,
            "idCardNumber": idCardNumber,
            "levelOfEducation": levelOfEducation,
            "politicalGroup": politicalGroup,
            "dateOfHire": dateOfHire,
            "state": state,
            "salary": salary,
            "imgUrl": imgUrl
        };

        console.log(record);


        $.ajax({
            type: "post",
            url: contextPath + "/staff?json",
            data: record,
            success: function (res) {
                console.log("添加成功 id = " + res);
                var docObj = document.getElementById("upfile");
                //if (docObj.files && docObj.files[0]) {
                //    $("#imgUrl").val(res);
                //    var uploadBtn = $("#uploadImage");
                //    uploadBtn.click();
                //} else {
                    location.href = contextPath + "/view/staff/staff.jsp";
                //}
            }
        });

    });
});

function refleshFile(file) {
    var docObj = document.getElementById("upfile");
    var imgPreview = document.getElementById("preview");
    if (docObj.files && docObj.files[0]) {
        var size = docObj.files[0].size;
        if (size > 1179648) {
            alert("上传图片大小超过限制。");
            window.location.reload();
        }
        imgPreview.style.display = "block";
        imgPreview.style.width = '150px';
        imgPreview.style.height = '200px';
        imgPreview.src = window.URL.createObjectURL(docObj.files[0]);
    } else {
        docObj.select();
        var imgSrc = document.selection.createRange().text;
    }
    $('.ace-file-container').hide();
}



