package service;

import java.util.List;

import model.AccountDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDao;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDao aDao;
	
	public int createBank(AccountDto accountDto) {
		return aDao.createBank(accountDto);
	}

	public int searchAccount(String account) {
		return aDao.searchAccount(account);
	}

	public List<AccountDto> bankList(String email) {
		return aDao.bankList(email);
	}

	@Override
	public AccountDto bankSearch(AccountDto account) {
		return aDao.bankSearch(account);
	}
}
