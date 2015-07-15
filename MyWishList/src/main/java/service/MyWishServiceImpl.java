package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.MyWishDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dao.MyWishDao;

@Service
public class MyWishServiceImpl implements MyWishService {
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
		return mw.selectItem(email, wishNo);
	}

	@Override
	public MyWishDto wishInfo(int wishNo) {
		return mw.wishInfo(wishNo);
	}

	@Override
	public int delete(String email, int wishNo) {
		return mw.delete(email, wishNo);
	}

	@Override
	public int myWishUpdate(MyWishDto myWishDto) {
		return mw.myWishUpdate(myWishDto);
	}

	@Override
	public List<MyWishDto> myWishAllList() {
		return mw.myWishAllList();
	}

	@Override
	public int myWishFail(int wishNo) {
		return mw.myWishFail(wishNo);
	}

	@Override
	public int sucTotal(String email) {
		return mw.sucTotal(email);
	}

	@Override
	public int failTotal(String email) {
		return mw.failTotal(email);
	}

	@Override
	public List<MyWishDto> sucWishList(MyWishDto myWishDto) {
		return mw.sucWishList(myWishDto);
	}

	@Override
	public List<MyWishDto> failWishList(MyWishDto myWishDto) {
		return mw.failWishList(myWishDto);
	}
}
