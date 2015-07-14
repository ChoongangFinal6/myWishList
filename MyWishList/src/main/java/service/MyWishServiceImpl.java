package service;

import java.util.List;

import model.MyWishDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MyWishDao;

@Service
public class MyWishServiceImpl implements MyWishService{
	@Autowired
	private MyWishDao mw;
	
	public List<MyWishDto> wishList(MyWishDto myWishDto) {
		return mw.wishList(myWishDto);
	}

	public int total(String email) {
		return mw.total(email);
	}

	@Override
	public int write(MyWishDto myWishDto) {
		return mw.write(myWishDto);
	}

	@Override
	public MyWishDto selectItem(String email, int wishNo) {
		return mw.selectItem(email,wishNo);
	}
	public MyWishDto wishInfo(int wishNo) {
		return mw.wishInfo(wishNo);
	}

	@Override
	public int delete(String email, int wishNo) {
		return mw.delete(email, wishNo);
	}

}
