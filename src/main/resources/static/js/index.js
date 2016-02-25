$(function(){

    var printData = function(data){
        $("#data").text(JSON.stringify(data));
    }

    $("#generate").click(function(){
        $.ajax({
            type: 'GET',
            dataType: 'json',
            contentType:'application/json',
            url: "generate",
            success: printData
        });
    });

    $("#next").click(function(){
        $.ajax({
            type: 'POST',
            dataType: 'json',
            contentType:'application/json',
            url: "next",
            data: $("#data").text(),
            success: printData
        });
    });

});
