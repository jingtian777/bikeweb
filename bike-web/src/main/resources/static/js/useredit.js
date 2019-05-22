var id;
var oldpassword;
var newpassword;

$.UserGet=function() {
    $.ajax({
        type: "get",
        url: "/bike-web/hom/get",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function(data){
            if(data.errorCode == 0){
                id = data.data.id;
                oldpassword = data.data.password;
                $("#name").html(data.data.name);
                $("#name2").attr("value",data.data.name);
                $("#phone").html(data.data.phone);
                $("#money").html(parseFloat(data.data.money).toFixed(2));
                $("#usednum").html(data.data.usednum);
                $("#costsum").html(parseFloat(data.data.costsum).toFixed(2));
                $("#age").attr("value",data.data.age);
                $("#email").attr("value",data.data.email);
                if(data.data.gender == 1){
                    $("#men").removeAttr("checked");
                    $("#women").attr("checked","checked");
                } else{
                    $("#women").removeAttr("checked");
                    $("#women").attr("checked","checked");
                }
            } else{
                alert(data.message);
            }
        },
        error:function () {
            alert("UserGet系统内部错误");
        }
    })
}

$.UserInfoUpdate=function() {
    $.ajax({
        type: "post",
        url: "/bike-web/hom/update",
        dataType:"json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            id:id,
            name:$("#name2").val(),
            email:$("#email").val(),
            age:$("#age").val(),
            gender:$("input[name=profile-gender-group]:checked").val(),
            password:newpassword
        }),
        success:function(data){
            if(data.errorCode == 0){
                alert(data.message);
                location.reload();
            } else{
                alert(data.message);
            }
        },
        error:function () {
            alert("UserInfoUpdate系统内部错误");
        }
    })
}


$.PasswordUpdate=function() {
    newpassword = $("#profile-password-new").val();
    if($("#profile-password-new-confirm").val() != newpassword){
        alert("两次新密码输入不一致");
    }
    if($("#profile-password").val() != oldpassword){
        alert("当前密码错误");
    }
    $.UserInfoUpdate();
}

$.ChargeUpdate=function() {
    $.ajax({
        type: "post",
        url: "/bike-web/hom/charge",
        dataType:"json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            number:$("#profile-zhanghao").val(),
            password:$("#profile-mima").val()
        }),
        success:function(data){
            if(data.errorCode == 0){
                alert(data.message);
                location.reload();
            } else{
                alert(data.message);
            }
        },
        error:function () {
            alert("UserInfoUpdate系统内部错误");
        }
    })
}
$(function () {
    $.UserGet();
    $("#user-edit-button").on("click",$.UserInfoUpdate);
    $("#password-edit-button").on("click",$.PasswordUpdate);
    $("#charge-edit-button").on("click",$.ChargeUpdate);
})