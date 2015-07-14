package dao;

import java.util.List;

import model.AccountDto;

public interface AccountDao {

	public int addNewAccount(AccountDto account);
	public AccountDto getOneAccount(AccountDto account);
	public List<AccountDto> getAccountList(String email);
	public int deleteAccount(int account);
	public int editBalance(AccountDto account);
}
