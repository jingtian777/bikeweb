$.UserLogout=function() {
    $.ajax({
        type: "post",
        url: "/bike-web/hom/logout",
        dataType:"json",
        contentType: "application/json;charset=utf-8",
        success:function(data){
            alert(data.message);
            if(data.errorCode == 0){
                location.href="base_pages_login.html"
            } else{
                alert(data.message);
            }
        },
        error:function () {
            alert("系统内部错误");
        }
    })
}



$.UserGet=function() {
    $.ajax({
        type: "get",
        url: "/bike-web/hom/get",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function(data){
            if(data.errorCode == 0){
                $("#name").html(data.data.name);
                $("#money").html(parseFloat(data.data.money).toFixed(2));
                $("#usednum").html(data.data.usednum);
                $("#costsum").html(parseFloat(data.data.costsum).toFixed(2));
            } else{
                alert(data.message);
            }
        },
        error:function () {
            alert("UserGet系统内部错误");
        }
    })
}

$(function () {
    $.UserGet();
    $("#logout-button").on("click",$.UserLogout);
})