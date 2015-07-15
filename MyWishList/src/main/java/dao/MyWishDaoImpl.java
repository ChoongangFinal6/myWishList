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

	// 진행중인 모든 위시리스트 가져옴
	@Override
	public List<MyWishDto> myWishAllList() {
		List<MyWishDto> mywishAllList = session.selectList("myWishAllList");
		return mywishAllList;
	}

	// 위시리스트 success의 값을 2(실패)로 바꿈
	@Override
	public int myWishFail(int wishNo) {
		int mywishFail = session.update("myWishFail", wishNo);
		return mywishFail;
	}

	// 성공한 리스트 총값
	public int sucTotal(String email) {
		int Suctotal = session.selectOne("sucTotal", email);
		return Suctotal;
	}

	// 실패한 리스트 총값
	public int failTotal(String email) {
		int Failtotal = session.selectOne("failTotal", email);
		return Failtotal;
	}

	@Override
	public List<MyWishDto> sucWishList(MyWishDto myWishDto) {
		List<MyWishDto> sucwishList = session.selectList("sucWishList", myWishDto);
		return sucwishList;
	}

	@Override
	public List<MyWishDto> failWishList(MyWishDto myWishDto) {
		List<MyWishDto> failwishList = session.selectList("failWishList", myWishDto);
		return failwishList;
	}
}
