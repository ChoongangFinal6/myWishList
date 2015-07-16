/**
 * 
 */



$(function() {
	
	// 계좌 등록, 삭제, 잔고 변경 처리후 결과메세지 선택
	var result = "${result}";
	switch (result) {
	case '511':
		showMsg("<div class='fadeMsg'>새로운 계좌가 등록되었습니다</div>");
		refreshParent();
		break;
	case '512':
		showMsg("<div class='fadeMsg' style='background-color:#c74467'>이미 등록되어있는 계좌입니다</div>");
		break;
	case '513':
		showMsg("<div class='fadeMsg' style='background-color:#c74467'>계좌등록에 실패하였습니다</div>");
		break;
	case '521':
		showMsg("<div class='fadeMsg'>계좌 정보가 삭제되었습니다</div>");
		refreshParent();
		break;
	case '522':
		showMsg("<div class='fadeMsg' style='background-color:#c74467'>계좌 정보 삭제에 실패하였습니다</div>");
		break;
	case '531':
		showMsg("<div class='fadeMsg'>계좌의 잔고가 변경되었습니다.</div>");
		refreshParent();
		break;
	case '532':
		showMsg("<div class='fadeMsg' style='background-color:#c74467'>현재 잔고보다 많은 금액을 차감할 수 없습니다</div>");
		break;
	case '533':
		showMsg("<div class='fadeMsg' style='background-color:#c74467'>계좌의 잔고 변경에 실패하였습니다</div>");
		break;
	default:
		break;
	}
	
	// 새계좌등록버튼 클릭 이벤트
	$('#addNewAccountBtn').click(function(){
		$('#newAccountForm').toggle('500');	
	});
});

function plus(acc) {
	$('#editForm'+acc).submit();
}	

function minus(acc) {
	var money = $('#money_'+acc).val();
	$('#money_'+acc).val(money*-1);
	$('#editForm'+acc).submit();
}

//	새 계좌 등록, 삭제 요청, 삭제확인 등 메세지 출력
function showMsg(msg) {
	$('body').append(msg);
	$('.fadeMsg').fadeIn();
	setTimeout(function() {
		$('.fadeMsg').fadeOut();
	}, 1500);
}

// 계좌 삭제 Confirm창
function deleteConfirm(bank, account) {
	if (confirm(bank + "은행 (" + account + ")\n계좌 정보를 삭제 하시겠습니까?")) {
		location.href = "deleteAccount.html?account=" + account;
	}
}

// 계좌생성 및 변경시 부모창 갱신
function refreshParent() {
	opener.loadAccountList();
}