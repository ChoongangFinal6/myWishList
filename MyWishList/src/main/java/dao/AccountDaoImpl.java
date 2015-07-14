package dao;

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
		System.out.println("searchAc : " + searchAc);
		return searchAc;
	}
}
