/**
 * Created by hwen on 15/8/26.
 */

$(document).ready(function() {
    showOrg();

    function showOrg(){
        var vTypeId              = $("#sid").val();

        $.ajax({
            url:contextPath+"/vehicleType/"+vTypeId,
            type:"get",
            async:true,
            success: function (data) {
                var vType = data;
                $("#capacity").text(vType.capacity);
                $("#description").text(vType.description);
                $("#height").text(vType.height);
                $("#length").text(vType.length);
                $("#oilType").text(vType.oilType);
                $("#seat").text(vType.seat);
                $("#width").text(vType.width);
            }
        });
    }

});