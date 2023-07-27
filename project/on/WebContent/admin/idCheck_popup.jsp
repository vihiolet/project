<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>id 중복체크 팝업 관리자</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body onload= "pValue()">
    <div class="idChk">
        <form action="" name="" method="">
            <input type="text" name= "idInput" id="adminId">
            <input type="button" value= "중복확인" onclick="idChk()">
        </form>
        <div id="mag"></div>
        <input type="button" value="사용하기" id="use_Btn" onclick="sendCheckValue()">
        <input type="button" value="취소" id="cancel_Btn" onclick="window.close()">
    </div>
     
    <script>
        //팝업 창의 id input에 부모창에 입력한 id 삽입
        function pValue(){
            document.getElementById("adminId").value = opener.document.adminInfo.id.value;
        }
        
        //id 중복 체크
        function idChk(){
            let id = document.getElementById("adminId").value
            
            if(!id){
                alert('id를 입력하세요.');
                return false;
            }else if((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){ 
                alert("한글 및 특수문자는 아이디로 사용하실 수 없습니다.");
                return false;
            }
            
            if(id != null){
                $.ajax({
                    type: "POST",
                    url: "adminIdChk.emp",
                    data: { chkId : id },
                    success: function(data){
                      console.log(data);
                      if(data == 0) {
                          document.getElementById("cancel_Btn").style.visibility = "visible";
                          document.getElementById("use_Btn").style.visibility = "hidden";
                          document.getElementById("mag").innerHTML= "사용할 수 없는 아이디입니다";
                      }else if(data == 1){
                          document.getElementById("cancel_Btn").style.visibility = "hidden";
                          document.getElementById("use_Btn").style.visibility = "visible";
                          document.getElementById("mag").innerHTML= "사용할 수 있는 아이디입니다";
                      }  
                    },
                    error: function(){
                      console.log('서버에서 값을 불러오지 못 함');  
                    }                    
                })
            }            
        }
        //join페이지(부모창)으로 중복체크한 id값 전달
        function sendCheckValue() {
            opener.document.adminInfo.idDuplication.value= "idCheck";
            opener.document.adminInfo.id.value= document.getElementById("userId").value;
            
            if(opener != null){
                console.log(opener);
                opener.checkForm = null;    //checkForm은 자식창 이름
                console.log(opener.checkForm);
                self.close();
            }
        }
    </script>   
</body>
</html>
