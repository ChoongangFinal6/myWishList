package dao;

import model.AccountDto;

public interface AccountDao {
	public int createBank(AccountDto accountDto);
	public int searchAccount(String account);
}
