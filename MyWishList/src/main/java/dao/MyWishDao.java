package dao;

import java.util.List;

import model.MyWishDto;

public interface MyWishDao {
	public List<MyWishDto> wishList(MyWishDto myWishDto);
	public int total(String email);
	public int write(MyWishDto myWishDto);
	public MyWishDto selectItem(String email, int wishNo);
	public MyWishDto wishInfo(int wishNo);
	public int delete(String email, int wishNo);
}
