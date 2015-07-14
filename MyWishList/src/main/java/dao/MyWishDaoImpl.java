package dao;

import java.util.List;

import model.MyWishDto;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyWishDaoImpl implements MyWishDao{
	@Autowired
	private SqlSession session;

	public List<MyWishDto> wishList(MyWishDto myWishDto) {
		List<MyWishDto> wishlist = session.selectList("myWishList", myWishDto);
		return wishlist;
	}

	public int total(String email) {
		int totals = session.selectOne("total", email);
		return totals;
	}

	@Override
	public int write(MyWishDto myWishDto) {
		int isIn = session.selectOne("isIn", myWishDto);
		int result = 0;
		if (isIn == 1 ) {
			result = session.update("updateMyWish",myWishDto);
		} else {
			result = session.insert("insertMyWish", myWishDto);
		}
		return result;
	}

	@Override
	public MyWishDto selectItem(String email, int wishNo) {
		MyWishDto mwd= new MyWishDto();
		mwd.setEmail(email);
		mwd.setWishNo(wishNo);
		MyWishDto myWishDto = session.selectOne("selectItem", mwd);
		return myWishDto;
	}
}
