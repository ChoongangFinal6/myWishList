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

	// 계좌 목록 조회
	public List<AccountDto> getAccountList(String email) {
		return session.selectList("Account.getAccountList", email);
	}
	// 단일 계좌 정보 조회
	public AccountDto getOneAccount(AccountDto account){
		return session.selectOne("Account.getOneAccount", account);
	}
	
	// 새 계좌 등록
	public int addNewAccount(AccountDto account) {
		System.out.println("	DAO addNewAccount : " + account);
		return session.insert("Account.addNewAccount", account);
	}

	// 계좌 삭제
	public int deleteAccount(String account) {
		return session.delete("Account.deleteAccount", account);
	}
	
	// 잔고 변경
	public int editBalance(AccountDto account) {	
		return session.update("Account.editBalance", account);
	}
}
