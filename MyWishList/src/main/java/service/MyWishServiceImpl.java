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

	public MyWishDto wishInfo(int wishNo) {
		return mw.wishInfo(wishNo);
	}

	public int myWishUpdate(MyWishDto myWishDto) {
		return mw.myWishUpdate(myWishDto);
	}

}
