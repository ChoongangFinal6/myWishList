package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.MyWishDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import service.MyWishService;

@Controller
public class RemainDateChk {
	@Autowired
	MyWishService ms;
	
	// 매일 0시에 실행하도록 설정
	@Scheduled(cron="0 00 00 * * ?") 
	//@Scheduled(cron="*/10 * * * * *") //-- 테스트용 숫자는 초단위(ms단위 아님) 
	public void scheduleRun() throws Exception{
		List<MyWishDto> myWishAllList = ms.myWishAllList();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String remainDate = "";
		Date endDate = null;
		Date date = new Date();
		long dateGapLong;
		int dateGap;
		// 현재의 날짜와 시간을 getTime으로 가지고 와서 뒤의 시간을 00:00:00으로 바꾸는 로직
		long today = (date.getTime()/(1000*60*60*24))*(1000*60*60*24)-(1000*60*60*9);
		
		
		for (int i = 0; i < myWishAllList.size(); i++) {
			remainDate = myWishAllList.get(i).getRemainDate();
			endDate = transFormat.parse(remainDate);	
			dateGapLong = (endDate.getTime() - today);
			dateGap = ((int)(dateGapLong/(1000*60*60*24)));
			if(dateGap >= 1 && dateGap <= 3){
				String warning = "[WMP] WishList알림 - \"" + myWishAllList.get(i).getProduct() + "\"의 만료 기한이 " + dateGap + "일 남았습니다.";
				// 문자로 보낼 코드작성
				System.out.println("종료시간이 얼마남지 않았습니다.");
		        String data = URLEncoder.encode("sendId", "UTF-8") + "=" + URLEncoder.encode("WeMakePlanner", "UTF-8");
		        data += "&" + URLEncoder.encode("targetId", "UTF-8") + "=" + URLEncoder.encode("010-0000-2222", "UTF-8");
		        data += "&" + URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(warning, "UTF-8");
		        URL url = new URL("http://211.183.2.53:8181/phone/ext/sendMsg.do");
		        URLConnection conn = url.openConnection();
		        conn.setDoOutput(true);		// Post방식으로 설정
		        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		        wr.write(data);
		        wr.flush();
				
		        // Get the response
		        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		        wr.close();
		        rd.close();
			}
			if(dateGap <= 0){
				ms.myWishFail(myWishAllList.get(i).getWishNo());
			}
		}
	}
}
