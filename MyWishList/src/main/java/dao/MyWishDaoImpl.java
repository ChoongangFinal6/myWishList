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
}
