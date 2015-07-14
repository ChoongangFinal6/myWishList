package service;

import java.util.List;

import model.AccountDto;

public interface AccountService {

	int addNewAccount(AccountDto account);

	List<AccountDto> getAccountList(String email);

	int deleteAccount(int account);

	int editBalance(AccountDto account);

}
