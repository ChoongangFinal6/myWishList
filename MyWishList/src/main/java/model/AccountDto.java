package model;

/**
 * aNo     INTEGER      NOT NULL, -- 일련번호
 * email   VARCHAR2(50) NOT NULL, -- 아이디(이메일)
 * bank    VARCHAR2(20) NOT NULL, -- 은행명
 * account INTEGER      NOT NULL  -- 계좌번호
 */
public class AccountDto {
	private int aNo;
	private String email;
	private String bank;
	private int account;
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public AccountDto() {
		super();
	}
	@Override
	public String toString() {
		return "AccountDto [aNo=" + aNo + ", email=" + email + ", bank=" + bank + ", account=" + account + "]";
	}
	
}
