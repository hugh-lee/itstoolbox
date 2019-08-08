$(document).ready(function () {
    resizeContent();

    $('.nav a').click(function (e) {
        document.getElementsByName('subFrame')[0].src = e.target.href;
    });

    $(window).resize(function (e) {
        resizeContent();
    });
});

function resizeContent() {
    if ($('.nav').height() > 50) {
        $('.content').css('margin-top', $('.nav').height());
    } else {
        $('.content').css('margin-top', '50px');
    }
}