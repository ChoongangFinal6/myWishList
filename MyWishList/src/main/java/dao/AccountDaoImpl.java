package dao;

import java.util.List;

import model.AccountDto;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{
	@Autowired
	private SqlSession session;

	public int createBank(AccountDto accountDto) {
		int createAc = session.insert("createAc", accountDto);
		return createAc;
	}

	public int searchAccount(String account) {
		int searchAc = session.selectOne("searchAccount", account);
		return searchAc;
	}

	public List<AccountDto> bankList(String email) {
		List<AccountDto> banklist = session.selectList("bankList", email);
		return banklist;
	}
	
	public AccountDto bankSearch(AccountDto account) {
		AccountDto banksearch = session.selectOne("bankSearch", account);
		return banksearch;
	}

	public int getBankMoney(String bank) {
		int bankMoney = session.selectOne("getBankMoney", bank);
		return bankMoney;
	}

	public int bankBuyUpdate(AccountDto accountDto) {
		int bankbuyUpdate = session.update("bankBuyUpdate", accountDto);
		return bankbuyUpdate;
	}

	public int passwordChk(AccountDto accountDto) {
		int paswordchk = session.selectOne("passwordChk", accountDto);
		return paswordchk;
	}

}
