package dao;

import java.util.List;

import model.MyWishDto;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyWishDaoImpl implements MyWishDao {
	@Autowired
	private SqlSession session;

	public List<MyWishDto> wishList(MyWishDto myWishDto) {
		List<MyWishDto> wishlist = session.selectList("myWishList", myWishDto);
		return wishlist;
	}

	public int total(String email) {
		int totals = session.selectOne("MyWish.total", email);
		return totals;
	}

	@Override
	public int write(MyWishDto myWishDto) {
		int isIn = session.selectOne("MyWish.isIn", myWishDto);
		System.out.println("isIn"+isIn);
		int result = 0;
		if (isIn >= 1) {
			result = session.update("MyWish.updateMyWish", myWishDto);
		} else {
			result = session.insert("MyWish.insertMyWish", myWishDto);
		}
		return result;
	}

	@Override
	public MyWishDto selectItem(String email, int wishNo) {
		MyWishDto mwd = new MyWishDto();
		mwd.setEmail(email);
		mwd.setWishNo(wishNo);
		MyWishDto myWishDto = session.selectOne("MyWish.selectItem", mwd);
		return myWishDto;
	}

	public MyWishDto wishInfo(int wishNo) {
		MyWishDto wishinfo = session.selectOne("MyWish.wishInfo", wishNo);
		return wishinfo;
	}

	@Override
	public int delete(String email, int wishNo) {
		MyWishDto mwd = new MyWishDto();
		mwd.setEmail(email);
		mwd.setWishNo(wishNo);
		return session.delete("MyWish.delete",mwd);
	}
	public int myWishUpdate(MyWishDto myWishDto) {
		int mywishUpddate = session.update("myWishUpdate", myWishDto);
		return mywishUpddate;
	}
}
