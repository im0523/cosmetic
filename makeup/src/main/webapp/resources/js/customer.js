//아이디 중복체크
function usable(){
	$.ajax({
		type: 'post',
		url : 'id_usable',
		data: {customer_id : $('#customer_id').val() },
		dataType: 'json',
		success: function(data){
			
			if( $('#customer_id').val() == '' ){
				alert('아이디를 입력해주세요');
				$('#customer_id').focus();
				return;
			}else if( $('#customer_id').val().length < 5 ){
				alert('아이디가 너무 짧습니다');
				$('#customer_id').focus();
				return;
			}
// 			alert(data);
			
			if( data == true ){
				alert('사용 가능한 아이디입니다');
				$('#usable').val('1');
			}else{
				alert('이미 사용중인 아이디입니다');
				$('#usable').val('0');
			}
			
		},error: function(data, req, status){
			alert('실패');
		}
	})
}

// 입력한 id 값에 변경이 생길 시 #usable 값을 0으로
function id_usable(){
	$('#usable').val('0');
}

// 우편번호 API
function execPostCode(){
	new daum.Postcode({
		oncomplete: function(data){
			//우편번호 찾기 버튼을 눌렀을 때
			var post, address;
			
			if ( data.userSelectedType == 'J' ){
				post = data.postcode;
				address = data.jibunAddress;
			}else {
				post = data.zonecode;
				address = data.roadAddress;
				var extra = '';
				if( data.buildingName !=''){	//건물 이름이 있으면
					extra = address += data.buildingName;	//넣어주고
				}
				address += extra=='' ? '' : '(' + extra + ')';
			}	
			
			//우편번호와 주소 정보를 해당 필드에 넣는다.
			$('#customer_postCode').val(post);	//5자리 새 우편번호 사용
			$('#customer_address').val(address);
			$('#customer_detailAddress').val('');
			$('#customer_detailAddress').focus();
		}
	}).open();
}

// 회원 등록 버튼 클릭시
function go_insert(){
	var check = validate();
	
	if(check == false){
		return;
	}
	
	if( $('#usable').val() == 1){
		addData();
		
		$('.insertForm').submit();
		alert('회원가입 성공!');
	}
}

// 주민등록번호, 전화번호, 이메일 각각 합쳐서 하나의 data로 저장
function addData(){
	var socialNum = $('#birth_b').val() + $('#birth_e').val();
	var phone = $('#phone1').val() + '-' +$('#phone2').val() + '-' + $('#phone3').val();
	var email = $('#customer_emailId').val() + $('#customer_domain').val();
	
	$('#customer_phone').val(phone);
	$('#customer_socialNum').val(socialNum);

	if( $('#customer_domain').val() == '1' ){
		$('#customer_email').val(  $('#customer_emailId').val()  );
	}else{
		$('#customer_email').val(email);
	}
}

// 회원 정보수정 버튼 클릭시
function go_update(){
	var check = validate();
	
	if( check == false ){
		return;
	}

	addData();
	
	$('.updateForm').submit();
	alert('회원정보가 변경되었습니다');
}