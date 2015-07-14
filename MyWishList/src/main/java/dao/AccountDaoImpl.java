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
		return session.insert("Account.addNewAccount", account);
	}

	// 계좌 삭제
	public int deleteAccount(int account) {
		return session.delete("Account.deleteAccount", account);
	}
	
	// 잔고 변경
	public int editBalance(AccountDto account) {	
		return session.update("Account.editBalance", account);
	}

	
	
}
