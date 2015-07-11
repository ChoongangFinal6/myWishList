package model;

import java.util.Date;

/**
 * mNo        INTEGER      NOT NULL, -- 일련번호
 * email      VARCHAR2(50) NOT NULL, -- 아이디(이메일)
 * name       VARCHAR2(50) NOT NULL, -- 품명
 * price      INTEGER      NOT NULL, -- 금액
 * remainDate DATE         NOT NULL, -- 기한
 * success    number(1,0)  null      -- 성공여부, 0(기본값)은 아직, 1은 성공, 2는 실패(로 할지 고려중 sysdate>remaindate에 0이면 실패 고를 수 있음)
 * img        VARCHAR2(50) NULL,     -- 이미지파일명
 * aNo        INTEGER      NOT NULL  -- 계좌번호
 */
public class MyWishDto {
	private int mno;
	private String email;
	private String name;
	private int price;
	private Date remainDate;
	private int success;
	private String img;
	private int ano;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getRemainDate() {
		return remainDate;
	}
	public void setRemainDate(Date remainDate) {
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
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public MyWishDto() {
		super();
	}
	@Override
	public String toString() {
		return "MyWishDto [mno=" + mno + ", email=" + email + ", name=" + name + ", price=" + price + ", remainDate="
				+ remainDate + ", success=" + success + ", img=" + img + ", ano=" + ano + "]";
	}

	
}
