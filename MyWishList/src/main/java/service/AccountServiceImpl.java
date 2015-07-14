package service;

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
}
