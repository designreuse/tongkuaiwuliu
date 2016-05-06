/**
 * Created by hwen on 15/9/1.
 */


function getDateStr(date){
    var fullYear = new Date();
    fullYear.setTime(date);
    var str = fullYear.toLocaleDateString();
    str = str.split("/");
    str[1]=str[1].length==1?("0"+str[1]):str[1];
    str[0]=str[0].length==1?("0"+str[0]):str[0];
    str = str[2]+"-"+str[0]+"-"+str[1];
    return str;
}

function getDateLong(date){
    var str = date.split("-");
    var year = str[0];
    var month  = str[1];
    var day  = str[2];

    var fullYear = new Date();
    fullYear.setUTCFullYear(year,month,day);
    return fullYear.getTime();
}
