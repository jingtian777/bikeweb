$.UserInfoCommit=function() {
    $.ajax({
        type: "post",
        url: "/bike-web/hom/login",
        dataType:"json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            phone:$("#login-username").val(),
            password:$("#login-password").val()
        }),
        success:function(data){
            if(data.errorCode == 0){
                location.href="base_pages_profile.html"
            } else{
                alert(data.message);
            }
        },
        error:function () {
            alert("系统内部错误2");
        }
    })
}

$(function () {
    $("#login-button").on("click",$.UserInfoCommit);
})