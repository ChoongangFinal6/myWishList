package model;

/**
 * email   VARCHAR2(50) NOT NULL, -- 아이디(이메일)
 * bank    VARCHAR2(20) NOT NULL, -- 은행명
 * account INTEGER      NOT NULL  -- 계좌번호
 * money   INTEGER      NULL      -- 잔고
 */
public class AccountDto {
	private String account;
	private String email;
	private String password;
	private String bank;
	private int money;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public AccountDto() {
		super();
	}
	@Override
	public String toString() {
		return "AccountDto [email=" + email + ", bank=" + bank + ", account=" + account + ", money="
				+ money + "]";
	}
	
}
