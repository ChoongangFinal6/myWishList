/**
 * WishList에 쓰이는 모든 js 모아둠
 */

$(function() {
	$('#account').keyup(function() {
		var accountVal = "account=" + $(this).val();
		$.ajax({
			url : "bankSearch.html",
			data : accountVal,
			context : document.body
		}).done(function(data) {
			if (data > 0) {
				$('#warning').html("<font color='red'>이미 등록한 계좌입니다</font>");
			} else {
				$('#warning').html("");
			}
		});
	});

	$('#addBank').click(function() {
		var account = $('#account').val();
		var money = $('#money').val();
		var bank = $('#bank').val();

	});

	$('#createBank').click(function() {
		$.ajax({
			url : "bankCreateForm.html",
			context : document.body
		}).done(function(data) {
			$('#bankCreForm').html(data);
		});
	});

	var option = {
		chart : {type : 'column'}, // 차트의 형태를 결정
		title : {text : '위시리스트 진행상황'}, // 제목
		subtitle : {text : ''}, // 부제목
		xAxis : {type : 'category'}, // 아래(x축)의 표현 형태
		yAxis : {
			title : {
				text : '목표 금액' // 왼쪽(y축)의 차트의 글씨
			}
		},
		legend : {
			enabled : false
		},

		// 막대 차트의 설정
		plotOptions : {
			series : {
				borderWidth : 0, // 차트 막대의 바깥과의 간격 수치. style에서 margin같은 느낌?
				dataLabels : {
					enabled : true,
					format : '{point.y}원'
				}
			}
		},

		// 해당 차트에 mouse up 시에 보여주는 정보의 구성
		tooltip : {
			headerFormat : '<span style="font-size:11px"></span>', // series의 data에 있는 name값을 가지고 온다.
			pointFormat : '<span><b>{point.name}</b></span>: {point.y}원' // drilldown의 series의 내용과 맞는 값을 가지고 온다. point.y는 위의 format과 같다.
		},
		// series 부분
		series : [ {
			name : "금액",
			colorByPoint : true
		} ]
	};

	$('.buttons').on('click', function(e) {
		e.stopImmediatePropagation();
	});
	$('.wishDiv').on('click', function() {
		var sendData = $(this).find($('.wishNo')).val();
		var params = "wishNo=" + sendData;
		$('#bankSelect').prop('value', 'All');
		$('#buyBtn').css('display', 'none');
		$('#buyDiv').css('display', 'none');
		$.getJSON('myWishChart.html', params, function(data) {
			$('#chart').css('display', 'block');
			$('#bankSelect').css('display', 'block');
			$('#imsiWishNo').val(sendData);

			option.series[0].data = data;
			$('#wishChart').highcharts(option);
		});
	});
	$('#bankSelect').change(function(){
		var account = $(this).val();
		var wishNo = $('#imsiWishNo').val()
		var sendData = "account=" + account + "&wishNo=" + wishNo;
		$.getJSON('bankSelect.html', sendData, function(data) {
			option.series[0].data = data;
			$('#wishChart').highcharts(option);
			$('#imsiBank').val(account);
	    });
		if(account != 'All'){
			$.get('buyCheck.html', sendData, function(data) {
				if (data > 0) {
					$('#buyBtn').css('display', 'block');
				} else {
					$('#buyBtn').css('display', 'none');
					$('#buyDiv').css('display', 'none');
				}
			});
		} else {
			$('#buyBtn').css('display', 'none');
			$('#buyDiv').css('display', 'none');
		}
	});

	$('#buyBtn').click(function() {
		$('#buyDiv').css('display', 'block');
		$(this).css('display', 'none');
	});

	$('#buy').click(function() {
		var result = "";
		var account = $('#bankSelect').val();
		var wishNo = $('#imsiWishNo').val();
		var password = $('#password').val();
		var params = "account=" + account + "&password=" + password;
		$.get("passChk.html", params, function(data) {
			if(data == 1){
				var sendData = "account=" + account + "&wishNo=" + wishNo;
				$.get("wishBuy.html", sendData, function(data) {
					alert("구매에 성공하셨습니다");
					location.href = 'myList.html';
				});
			} else {
				alert("비밀번호가 틀렸습니다.");
			}
		});
	});
	$('#imgSpan').bind('click', '#modifyImg', function() {
		$('#imageInput').show();
		$('#imgInput').val("");
		$('#modifyImg').remove();
		
	});
	$('.imgDiv').mouseover(function() {
	    $(this).next().addClass('buttonOver');
	}).mouseout(function() {
		$(this).next().removeClass('buttonOver');
	});

	$('#wishSucFailPage').change(function(){
		var view = $(this).val();
		console.log(view);
		if(view == "All"){
			location.href="myList.html";
		}else{
			location.href="mySucFailList.html?view="+view;
		}
	});
	
});
function modify(wishNo) {
	$('#modifyImg').remove();
	var sendData = "wishNo=" + wishNo;
	$.getJSON('myWishUpdate.html', sendData, function(data) {
		$('#wishNo').val(data.wishNo);
		$('#product').val(data.product);
		$('#price').val(data.price);
		$('#remainDate').val(data.remainDate);
		$('#success').val(data.success);
		if (data.img == null || data.img == "" || data.img == "null") {
			$('#imgInput').val("");
			$('#imageInput').show();
		} else {
			$('#imgInput').val(data.img);
			$('#imageInput').hide();
			$('#imgSpan').append("<a id='modifyImg'>변경</a>");
		}	
	});
	
}
function newItem() {
	$('.input').val("");
	$('#imgInput').val("");
	$('#imageInput').show();
}
function del(wishNo) {
	if (confirm("삭제하시겠습니까")) {
		var sendData = "wishNo=" + wishNo;
		$.ajax({
			url : "myWishDelete.html",
			data : sendData,
			async : true,
			success : function(data) {
				if (data == 1) {
					location.reload();
				}
			}
		});
	}
}

$(function(){
	loadAccountList();		
});

// 계좌 관리 팝업창
function manageAccount(){
	var win = window.open("manageAccount.html", "계좌관리", "width=650, height=400,resizable=false");
}

// 계좌 목록 Load
function loadAccountList(){
	$.ajax({
		url: "loadAccountList.html",
		success : function(result) {
			$('#mwl_acc_list').html(result);
		}
	});
}

function accountDetail(){
	$('#accounDetail').toggle('1000');
}