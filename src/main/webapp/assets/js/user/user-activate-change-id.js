$(document).ready(function () {
    $('button.deactivate').on('click', function () {
        let id = $(this).find('#id').val();
        $('#deactivate-id').val(id);
    });
});
$(document).ready(function () {
    $('button.reactivate').on('click', function () {
        let id = $(this).find('#id').val();
        $('#reactivate-id').val(id);
    });
});