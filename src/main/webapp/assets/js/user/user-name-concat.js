$(document).ready(function () {
    $('button.summit').on('click', function () {
        $('#inputFullName').val($('#inputLastName,#inputFirstName').map(function () {
            return $(this).val();
        }).get().join(' '));
    });
});