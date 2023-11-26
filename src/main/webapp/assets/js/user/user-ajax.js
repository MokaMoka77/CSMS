$(document).ready(function () {
    $('#userName').blur(function () {
        $.ajax({
            url: 'user?id=',
            type: 'delete',
            data: {
                userName: $('#userName').val()
            },
            success: function (responseText) {
                $('#ajaxGetUserServletResponse').text(responseText);
            }
        });
    });
});

$(document).ready(function () {
    $('#userName').blur(function () {
        $.ajax({
            url: 'user?id=',
            type: 'delete',
            data: {
                userName: $('#userName').val()
            },
            success: function (responseText) {
                $('#ajaxGetUserServletResponse').text(responseText);
            }
        });
    });
});