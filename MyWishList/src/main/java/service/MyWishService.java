package service;

import java.util.List;

import model.MyWishDto;

public interface MyWishService {
	public List<MyWishDto> wishList(MyWishDto myWishDto);
	public int total(String email);
	public int write(MyWishDto myWishDto);
	public MyWishDto selectItem(String email, int wishNo);
	public MyWishDto wishInfo(int wishNo);
	public int delete(String email, int wishNo);
	public int myWishUpdate(MyWishDto myWishDto);
	public List<MyWishDto> myWishAllList();
	public int myWishFail(int wishNo);
	
	public int sucTotal(String email);
	public int failTotal(String email);
	
	public List<MyWishDto> sucWishList(MyWishDto myWishDto);
	public List<MyWishDto> failWishList(MyWishDto myWishDto);
}
