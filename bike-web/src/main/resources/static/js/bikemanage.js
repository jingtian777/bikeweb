$.BikeGet=function() {
    $.ajax({
        type: "get",
        url: "/bike-web/bike/get",
        async: false,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function(data){
            if(data.errorCode == 0){
                var obj = data.data;
                for(var val in obj){
                    var bike = obj[val];

                    var first = document.getElementById("bike-table");
                    var second = document.createElement('tr');
                    first.appendChild(second);

                    var thirdId = document.createElement('td');
                    thirdId.className = 'text-center';
                    thirdId.innerHTML = bike.id;
                    second.appendChild(thirdId);

                    var thirdNumber = document.createElement('td');
                    thirdNumber.className = 'font-w600';
                    thirdNumber.innerHTML = bike.number;
                    second.appendChild(thirdNumber);

                    var thirdType = document.createElement('td');
                    thirdType.className = 'hidden-xs';
                    thirdType.innerHTML = bike.type;
                    second.appendChild(thirdType);

                    var thirdGps = document.createElement('td');
                    thirdGps.className = 'hidden-xs';
                    thirdGps.innerHTML = bike.gps;
                    second.appendChild(thirdGps);

                    var third = document.createElement('td');
                    third.className = 'text-center';
                    second.appendChild(third);

                    var fourth = document.createElement('div');
                    fourth.className = 'btn-group';
                    third.appendChild(fourth);

                    var use = document.createElement('button');
                    use.className = 'btn btn-xs btn-default';
                    use.setAttribute("data-toggle","tooltip");
                    use.setAttribute("value",bike.number);
                    use.setAttribute("name","use");
                    use.innerHTML = "借车";
                    fourth.appendChild(use);

                    var fix = document.createElement('button');
                    fix.className = 'btn btn-xs btn-default';
                    fix.setAttribute("data-toggle","tooltip");
                    fix.setAttribute("value",bike.number);
                    fix.setAttribute("name","fix");
                    fix.innerHTML = "报修";
                    fourth.appendChild(fix);

                }
            } else{
                alert(data.message);
            }
        },
        error:function () {
            alert("BikeGet系统内部错误");
        }
    })
}

$.BikeUpdate=function(){
    $.ajax({
        type: "post",
        url: "/bike-web/bike/fix",
        async: false,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({
            number:$(this).attr("value"),
            status:1
        }),
        success:function(data){
            alert(data.message);
            if(data.errorCode == 0){
                location.reload();
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
    $.BikeGet();
    $("button[name='fix']").on("click",$.BikeUpdate);
})