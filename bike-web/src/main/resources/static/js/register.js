$.UserInfoCommit=function() {
    $.ajax({
        type: "post",
        url: "/bike-web/hom/register",
        dataType:"json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            phone:$("#register-username").val(),
            password:$("#register-password").val(),
            email:$("#register-email").val()
        }),
        success:function(data){
            if(data.errorCode == 0){
                location.href="base_pages_profile.html"
            } else{
                alert(data.message);
            }
        },
        error:function () {
            alert("系统内部错误");
        }
    })
}

$(function () {
    $("#register-button").on("click",$.UserInfoCommit);
})