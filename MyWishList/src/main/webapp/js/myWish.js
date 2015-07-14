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
	
	
	
	
	function pageShift(num){
		alert(num);
	}
	
	
	
	
	
	
	

	$('#prePage').click(function(){
		var pageNum = "currentPage=" + $('#preNum').val();
		$.ajax({
			  url: "myListChange.html",
			  data: pageNum,
			  context: document.body
			}).done(function(data) {
				$('#wishList').html(data);
		});
		$.ajax({
			url: "pageFormChange.html",
			data: pageNum,
			context: document.body
		}).done(function(data) {
			$('#pageForm').html(data);
		});
	});
	
	$('.pageNum').click(function(){
		var pageNum = "currentPage=" + $(this).attr('id');
		$.ajax({
			  url: "myListChange.html",
			  data: pageNum,
			  context: document.body
			}).done(function(data) {
				 $('#wishList').html(data);
		});
	});
	
	$('#nextPage').click(function(){
		var pageNum = "currentPage=" + $('#nextNum').val();
		$.ajax({
			  url: "myListChange.html",
			  data: pageNum,
			  context: document.body
			}).done(function(data) {
				$('#wishList').html(data);
		});
		$.ajax({
			url: "pageFormChange.html",
			data: pageNum,
			context: document.body
		}).done(function(data) {
			$('#pageForm').html(data);
		});
	});
	
	
    // Create the chart
    $('#wishChart').highcharts({
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
        series: [{
            name: "금액", // 상세목록에 나오는 Back to ~~~ 부분에서 ~~~에 입력할 텍스트 
            colorByPoint: true, // 차트의 값들이 다를 때마다 막대의 색 차이를 줄 것인지에 대한 여부
            data: [{
                name: "목표금액",	// 차트 목록내에서의 이름
                y: 25000,	// 차트 상에 보여주는 그래프 퍼센트의 크기
            },{
                name: "신한은행",	// 차트 목록내에서의 이름
                y: 30000,	// 차트 상에 보여주는 그래프 퍼센트의 크기
            }, {
                name: "국민은행",
                y: 20000
            }]
        }]
    });
});