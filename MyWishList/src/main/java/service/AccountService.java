package service;

import model.AccountDto;

public interface AccountService {
	public int createBank(AccountDto accountDto);
	public int searchAccount(String account);
}
