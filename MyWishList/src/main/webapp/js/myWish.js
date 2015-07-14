/**
 * 
 */

$(function () {
	
	$('#account').keyup(function(){
		var accountVal = "account=" + $(this).val();
		$.ajax({
		  url: "bankSearch.html",
		  data: accountVal,
		  context: document.body
		}).done(function(data) {
		  if(data > 0){
			  $('#warning').html("<font color='red'>이미 등록한 계좌입니다</font>");
		  }else{
			  $('#warning').html("");
		  }
		});
	});
	
	$('#addBank').click(function(){
		var account = $('#account').val();
		var money = $('#money').val();
		var bank = $('#bank').val();
		
	});
	
	$('#createBank').click(function(){
		$.ajax({
			  url: "bankCreateForm.html",
			  context: document.body
			}).done(function(data) {
			  $('#bankCreForm').html(data);
			});
	});
		
	var option = {
        chart: {type: 'column' },	// 차트의 형태를 결정
        title: { text: '위시리스트 진행상황' },	// 제목
        subtitle: {text: '' },	// 부제목
        xAxis: {type: 'category'},	// 아래(x축)의 표현 형태	 
        yAxis: {title: {
                text: '목표 금액' // 왼쪽(y축)의 차트의 글씨
                }
        },
        legend: {enabled: false},
        
        // 막대 차트의 설정
        plotOptions: {
            series: {
                borderWidth: 0,	// 차트 막대의 바깥과의 간격 수치. style에서 margin같은 느낌?
                dataLabels: {
                    enabled: true,
                    format: '{point.y}원'
                }
            }
        },
		
        // 해당 차트에 mouse up 시에 보여주는 정보의 구성
        tooltip: {
            headerFormat: '<span style="font-size:11px"></span>',  //series의 data에 있는 name값을 가지고 온다.
            pointFormat: '<span><b>{point.name}</b></span>: {point.y}원'  // drilldown의 series의 내용과 맞는 값을 가지고 온다. point.y는 위의 format과 같다.
        },
        // series 부분
        series: [{name: "금액",colorByPoint: true}]
    };
	
//	$('.wishDiv').each(function(index){
//		$(this).click(function(){
//			var sendData = $(this).find($('.wishNo')).val();
//			var params = "wishNo=" + sendData;
//			
//			$.getJSON('myWishChart.html', params, function(data) {
//				$('#chart').css('display', 'block');
//				$('#bankSelect').css('display', 'block');
//				$('#imsiWishNo').val(sendData);
//				
//				option.series[0].data=data;
//				$('#wishChart').highcharts(option);
//		    });
//			
//			$.get('myWishMoney.html', param, function(data) {
//				$('#imsiWishNoMoney').val(data);
//			});
//		});
//	});
//	
	$('#bankSelect').change(function(){
		var bank = $(this).val();
		var wishNo = $('#imsiWishNo').val()
		var sendData = "bank=" + bank + "&wishNo=" + wishNo;
		$.getJSON('bankSelect.html', sendData, function(data) {
			option.series[0].data=data;
			$('#wishChart').highcharts(option);
			$('#imsiBank').val(bank);
	    });
		var param = "bank=" + bank;
		$.get('bankMoney.html', param, function(data) {
			$('#imsiBankMoney').val(data);
		});
	});
	
	$('#buyBtn').click(function(){
		var sendData = "bank=" + $(this).val() + "&wishNo=" + $('#imsiWishNo').val();
		$.get(".html", sendData, function(data) {

		});
	});
});
function modify(wishNo) {
	var sendData = "wishNo="+wishNo;
	$.getJSON('myWishUpdate.html', sendData, function(data) {
		$('#product').val(data.product);
		$('#price').val(data.price);
		$('#remainDate').val(data.remainDate);
		$('#img').val(data.img);
		$('#success').val(data.success);
		$('#wishNo').val(data.wishNo);
	});
}
function del(wishNo) {
	var sendData = "wishNo="+wishNo;
	$.ajax({
		  url: "myWishDelete.html",
		  data: sendData,
			async : true,
			success : function(data) {
				if (data == 1) {
					location.reload();
				}
			}
	});
}
