package model;

/**
 * email   	VARCHAR2(50) NOT NULL, -- 아이디(이메일)
 * bank    	VARCHAR2(20) NOT NULL, -- 은행명
 * account 	INTEGER      NOT NULL  -- 계좌번호
 * pass		INTEGER 			-- 비밀번호
 * money	INTEGER 			-- 잔고
 */
/**
 * @author 402-c2
 *
 */
public class AccountDto {
	private String email;
	private String bank;
	private int account;
	private int pass;
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
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
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
		return "AccountDto [email=" + email + ", bank=" + bank + ", account="
				+ account + ", pass=" + pass + ", money=" + money + "]";
	}
	

	
	
}
