//페이지 로드될 때 스크립트 시작 너무 느리다 첫 슬라이드까지 9초 넘게 걸린다
setInterval(function(){
    moveRight();
},4000);

let slideCount = $(".slider ul li").length;
let slideWidth = $(".slider ul li").width();
let slideUlWidth = slideCount * slideWidth;

$('.slider ul').css({ width: slideUlWidth, left: -slideWidth });
$('.slider ul li').css({ width: slideUlWidth/3 });
$('.slider ul li:last-child').prependTo('.slider ul');  //마지막 사진이 맨 위에 추가됨
console.log($(".slider ul").width());    

/*9행에서 ul 너비를 모든 li의 너비를 합한 수로 설정하면 자식요소인 li는 ul의 너비를 상속받는다
모르고 css에서 li에 width: 100%를 줬으니 li의 너비는 ul의 너비가 된거지
그래서 li에 다음 li가 붙을 자리가 없었고 슬라이딩 다음에 여백이 생겼다
  ㄴ 모든 li의 너비가 할당되었으니까 이미지 하나 외에 그 두 배가 남는다(이미지 3개 기준)
*/

function moveRight(){
    $('.slider ul').animate({
        left: -slideWidth
    },2000, function(){
        $('.slider ul li:first-child').appendTo('.slider ul');
        $('.slider ul').css('left', '');    //left값 초기화
    });
}
//슬라이드만 하면 이미지가 연결되면서 슬라이드되지 않고 하나씩 끊겨서 빈화면까지 나오면서 슬라이드된다.

