setInterval(function(){
    moveRight();
},4000);

let slideCount = $(".slider ul li").length;
let slideWidth = $(".slider ul li").width();
let slideUlWidth = slideCount * slideWidth;

$('.slider ul').css({ width: slideUlWidth, left: -slideWidth });
$('.slider ul li').css({ width: slideWidth });
$('.slider ul li:last-child').prependTo('.slider ul');  //마지막 사진이 맨 위에 추가됨 width: slideUlWidth,
//console.log($(".slider ul").width());                         

function moveRight(){
    $('.slider ul').animate({
        left: -slideWidth
    },2000, function(){
        $('.slider ul li:first-child').appendTo('.slider ul');
        $('.slider ul').css('left', '');    //left값 초기화
    });
}
//슬라이드만 하면 이미지가 연결되면서 슬라이드되지 않고 하나씩 끊겨서 빈화면까지 나오면서 슬라이드된다.

