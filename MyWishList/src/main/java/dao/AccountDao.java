package dao;

import java.util.List;

import model.AccountDto;


public interface AccountDao {
	public int createBank(AccountDto accountDto);
	public int searchAccount(String account);
	public List<AccountDto> bankList(String email);
	public AccountDto bankSearch(AccountDto account);
	public int getBankMoney(String bank);
	public int bankBuyUpdate(AccountDto accountDto);
	public int passwordChk(AccountDto accountDto);
	public int addNewAccount(AccountDto account);
	public AccountDto getOneAccount(AccountDto account);
	public List<AccountDto> getAccountList(String email);
	public int deleteAccount(String account);
	public int editBalance(AccountDto account);

}
