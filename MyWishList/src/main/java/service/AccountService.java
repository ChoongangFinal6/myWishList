package service;

import java.util.List;

import model.AccountDto;

public interface AccountService {
	public int createBank(AccountDto accountDto);
	public int searchAccount(String account);
	public List<AccountDto> bankList(String email);
	public AccountDto bankSearch(AccountDto account);
}
