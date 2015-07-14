package model;

import java.util.Date;

/**
 * email      VARCHAR2(50) NOT NULL, -- 아이디(이메일)
 * name       VARCHAR2(50) NOT NULL, -- 품명
 * price      INTEGER      NOT NULL, -- 금액
 * remainDate DATE         NOT NULL, -- 기한
 * success    number(1,0)  null      -- 성공여부, 0(기본값)은 아직, 1은 성공, 2는 실패(로 할지 고려중 sysdate>remaindate에 0이면 실패 고를 수 있음)
 * img        VARCHAR2(50) NULL,     -- 이미지파일명
 */
public class MyWishDto {
	private int wishNo;
	private String email;
	private String product;
	private int price;
	private String remainDate;
	private int success;
	private String img;
	
	// 조회용
	private String pageNum;
	private int start;
	private int end;
	
	public int getWishNo() {
		return wishNo;
	}
	public void setWishNo(int wishNo) {
		this.wishNo = wishNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRemainDate() {
		return remainDate;
	}
	public void setRemainDate(String remainDate) {
		this.remainDate = remainDate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public MyWishDto() {
		super();
	}
	
	
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	@Override
	public String toString() {
		return "MyWishDto [email=" + email + ", product=" + product + ", price=" + price + ", remainDate="
				+ remainDate + ", success=" + success + ", img=" + img + "]";
	}

	
}
