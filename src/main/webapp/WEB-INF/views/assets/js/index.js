$(document).ready(function () {
    $('.menu li').click(function () {
        $(this).addClass('on').siblings('li').removeClass('on');
    });

    $('.menu li:last-child').click(function () {
        $('.cont2').show();
        $('.cont1').hide();
    });

    $('.menu li:first-child').click(function () {
        $('.cont1').show();
        $('.cont2').hide();
    });

    $('.cont1 .movepage').click(function () {
        $('.cont2').show();
        $('.cont1').hide();
        $('.menu li:last-child').addClass('on').siblings('li').removeClass('on');
    });

    $('.tab li').click(function () {
        $(this).addClass('active').siblings('li').removeClass('active');
    });

    $('.list ul li').click(function () {
        $(this).addClass('active').siblings('li').removeClass('active');
    });

    $('.close').click(function () {
        $('.wrap').hide();
    });


});
