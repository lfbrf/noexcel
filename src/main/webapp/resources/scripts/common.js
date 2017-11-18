$(document).ready(function () {
    $('.collapsible').collapsible();
    $('#year').html(getCurrentYear());
    $('select').material_select();
    $(".button-collapse").sideNav();

});

function getCurrentYear() {
    return new Date().getFullYear();
}




