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

	public AccountDto bankSearch(AccountDto account) {
		return aDao.bankSearch(account);
	}

	public int bankBuyUpdate(AccountDto accountDto) {
		return aDao.bankBuyUpdate(accountDto);
	}

	public int passwordChk(AccountDto accountDto) {
		return aDao.passwordChk(accountDto);
	}

	
	// 계좌 목록 조회
	public List<AccountDto> getAccountList(String email) {
		return aDao.getAccountList(email);
	}

	// 새 계좌 등록	(return 511:등록, 512:동일계좌존재로 등록실패, 513:기타이유로 등록실패)
	public int addNewAccount(AccountDto account) {
		if( aDao.getOneAccount(account)==null ){
			int result = aDao.addNewAccount(account);		// 동일 계좌가 없으면 insert진행
			if(result==1) return 511;
			else return 513;
		}else return 512;
	}

	// 계좌 삭제 (return 521:삭제성공, 522:삭제실패)
	public int deleteAccount(String account) {
		int result = aDao.deleteAccount(account);
		if( result==1){ return 521;}  
		else{ return 522;}
	}

	// 잔고 변경 (return 531:변경성공, 532:잔고부족으로 인출 실패, 533:기타이유로 변경실패)
	public int editBalance(AccountDto account) {
		int currentMoney = aDao.getOneAccount(account).getMoney();
		int sumMoney = currentMoney + account.getMoney();
		if( sumMoney < 0 ){
			return 532; 
		}else {
			account.setMoney(sumMoney);
			int result = aDao.editBalance(account);
			if( result == 1 ){ return 531; }
			else{ return 533; }
		}
	}

}
