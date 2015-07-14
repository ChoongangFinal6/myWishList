package service;

import java.util.List;

import model.AccountDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDao;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao ad;
	
	// 새 계좌 등록
	public int addNewAccount(AccountDto account) {
		if( ad.getOneAccount(account)==null ){
			return ad.addNewAccount(account);		// 동일 계좌가 없으면 insert진행
		}else return -1;
	}

	// 계좌 목록 조회
	public List<AccountDto> getAccountList(String email) {
		return ad.getAccountList(email);
	}

	// 계좌 삭제
	public int deleteAccount(int account) {
		int result = ad.deleteAccount(account);
		if( result==1){ return 2;}  
		else{ return 3;}
	}

	// 잔고 변경
	public int editBalance(AccountDto account) {
		int currentMoney = ad.getOneAccount(account).getMoney();
		int sumMoney = currentMoney + account.getMoney();
		if( sumMoney < 0 ){
			return 4; 
		}else {
			account.setMoney(sumMoney);
			int result = ad.editBalance(account);
			if( result == 1 ){ return 5; }
			else{ return 6; }
		}
	}

}
