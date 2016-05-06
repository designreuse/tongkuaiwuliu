/**
 * Created by hwen on 15/8/23.
 */
$(document).ready(
    function doSave() {
        $("#saveEdit", ".btnArea").click(function () {
            if (checkFormat()) {
                var id = $("#sid").val();
                var realName = $("#realName").val();    //不为空
                var phoneNumber = $("#phoneNumber").val();
                var gender = $("#gender").val();
                var job = $("#job").val();
                var idCardNumber = $("#idCardNumber").val();  //不为空
                var levelOfEducation = $("#levelOfEducation").val();
                var politicalGroup = $("#politicalGroup").val();
                var state = $("#state").val();
                var salary = $("#salary").val();

                var dateOfHire = getDateLong($("#dateOfHire").val());


                var record = {
                    "id": id,
                    "realName": realName,
                    "phoneNumber": phoneNumber,
                    "gender": gender,
                    "job": job,
                    "idCardNumber": idCardNumber,
                    "levelOfEducation": levelOfEducation,
                    "politicalGroup": politicalGroup,
                    "state": state,
                    "salary": salary,
                    "dateOfHire": dateOfHire,
                    "_method": "put"
                };
                $.post(contextPath + "/staff?json", record, function (data) {
                    console.log("data = " + data);
                    $("#imgUrl").val(id);
                    var uploadBtn = $("#uploadImage");
                    uploadBtn.click();
                    location.href = contextPath + "/view/staff/staff.jsp";
                });

            }

        });

        showOrg();
        getImg();
        function showOrg() {
            var staffId = $("#sid").val();
            var url = contextPath + "/staff/" + staffId + "?json";
            if (staffId > 0) {
                $.get(url, {"json": ""}, function (data) {

                    var staff = data;

                    $("#realName").val(staff.realName);
                    $("#phoneNumber").val(staff.phoneNumber);
                    $("#gender").val(staff.gender);
                    $("#job").val(staff.job);
                    $("#idCardNumber").val(staff.idCardNumber);
                    $("#levelOfEducation").val(staff.levelOfEducation);
                    $("#politicalGroup").val(staff.politicalGroup);
                    $("#state").val(staff.state);
                    $("#salary").val(staff.salary);
                    $("#dateOfHire").val(getDateStr(staff.dateOfHire));
                });
            } else {
                console.log("url" + url);
                location.href = contextPath + "/view/staff/staff.jsp";
            }
        }

    });

function getImg() {
    var img = contextPath + '/image/get?staffId=' + $("#sid").val();
    var imgPreview = document.getElementById("preview");

    imgPreview.style.display = "block";
    imgPreview.style.width = '150px';
    imgPreview.style.height = '200px';
    imgPreview.src = img;

    $("#imgUrl").val($("#sid").val());
    $('.ace-file-container').hide();
}

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
    $("#imgUrl").val($("#sid").val());
    $('.ace-file-container').hide();
}

/**
 * 检查输入的数据是否合法，如果合法返回true，否则返回false
 * @returns {boolean}
 */
function checkFormat() {
    var realName = $("#realName").val();    //不为空
    var phoneNumber = $("#phoneNumber").val();
    var idCardNumber = $("#idCardNumber").val();  //不为空
    var salary = $("#salary").val();


    var notNull = [realName, idCardNumber, salary];             //不为空的属性写进数组
    var intOnly = [salary, phoneNumber];                                  //输入类型需要是正整数的写进数组
    var test, flag = true;
    console.log("intOnly->" + intOnly);

    console.log("null le:" + notNull.length + "int le:" + intOnly.length);
    if (notNull.length > 0) {
        $(notNull).each(function (index, element) {
            test = element.replace(/\s/g, "");  //过滤空格
            if (test === '') {
                slideTip("名字，身份证，工资不能为空！", true);
                flag = false;
                return false;               //break
            }
        });

    }

    if (intOnly.length > 0) {
        $(intOnly).each(function (index, element) {
            var testInt = /^[0-9]*[1-9][0-9]*$/;
            if (!testInt.test(element)) {
                slideTip("工资,电话为正数!!", true);
                flag = false;
                return false;               //break
            }
        });
    }
    return flag;
}

function slideTip(tip, warning) {
    if (warning) {
        $(".slideHeader", ".slideTip").css("background-color", "#d14836");
        $(".slideTip").css("border", "1px solid #d14836");
    } else {
        $(".slideHeader", ".slideTip").css("background-color", "#438EB9");
        $(".slideTip").css("border", "1px solid #438EB9");
    }
    $(".slideContent", ".slideTip").text(tip);
    $(".slideTip").slideDown(1000, function () {
        $(".slideTip").fadeOut(5000);
    });
}