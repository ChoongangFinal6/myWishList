package service;

import java.util.List;

import model.MyWishDto;

public interface MyWishService {
	public List<MyWishDto> wishList(MyWishDto myWishDto);
	public int total(String email);
	public MyWishDto wishInfo(int wishNo);
}
