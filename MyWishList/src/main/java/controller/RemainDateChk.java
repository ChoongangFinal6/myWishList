package controller;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.MyWishDto;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class RemainDateChk {
	/*@Autowired
	MyWishService ms;*/
	/*@Autowired
	AccountService as;*/
	
	private SqlSession getSession() throws IOException {
		String src = "mybatis/remainChkconfig.xml";
		Reader reader = Resources.getResourceAsReader(src);
		SqlSessionFactory ssf = 
			new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = ssf.openSession(true);
		return session;
	}
	
	@Scheduled(cron="0 00 00 * * ?") 
	public void scheduleRun() throws Exception{
		List<MyWishDto> myWishAllList = null;
		SqlSession session = null;
		try {
			session = getSession();

			myWishAllList = session.selectList("myWishAllList");
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
					// 문자로 보낼 코드작성
					System.out.println("종료시간이 얼마남지 않았습니다.");
				}
				if(dateGap <= 0){
					session.update("myWishFail", myWishAllList.get(i).getWishNo());
				}
			}
			System.out.println("실행이 되긴하는감");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally { session.close(); }
	}
}
