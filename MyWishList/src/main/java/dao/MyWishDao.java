package dao;

import java.util.List;

import model.MyWishDto;

public interface MyWishDao {
	public List<MyWishDto> wishList(MyWishDto myWishDto);
	public int total(String email);
	public MyWishDto wishInfo(int wishNo);
	public int myWishUpdate(MyWishDto myWishDto);
}
