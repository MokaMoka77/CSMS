$(document).ready(function () {
    $('#user-management-add-user').blur(function () {
        $.ajax({
            url: 'users',
            type: 'get',
            success: function (responseText) {
                $('#layoutSidenav_content').text(responseText);
            }
        });
    });
});

$(document).ready(function () {
    $('#user-management-edit-user').blur(function () {
        $.ajax({
            url: 'users',
            type: 'get',
            success: function (responseText) {
                $('#layoutSidenav_content').text(responseText);
            }
        });
    });
});