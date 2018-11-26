package org.cap.service;

import java.util.ArrayList;
import java.util.List;

import org.cap.dao.IAccountDao;
import org.cap.exception.InsufficientbalanceException;
import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.util.AccountUtil;



public class AccountServiceImpl implements IAccountService{
	
	
	private IAccountDao accountDao;
	
	
	

	public AccountServiceImpl(IAccountDao accountDao2) {
		this.accountDao=accountDao2;
	}




	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}




	@Override
	public Account createAccount(Customer customer, double balance) throws InsufficientbalanceException {
		
		if(customer==null)
			throw new IllegalArgumentException("Sorry! Invalid Customer");
		if(balance<500)
			throw new InsufficientbalanceException("Insufficient Opening Balance");
		
		Account account=new Account();
		account.setAccountNo(AccountUtil.generateAccountno());
		account.setCustomer(customer);
		account.setOpeningBalance(balance);
		
		if(accountDao.createAccount(account))
			return account;
		
		return null;
	}




	@Override
	public Account findAccount(int accountNo) {
		
		return accountDao.findAccount(accountNo);
	}




	@Override
	public Account withdraw(int accountNo, double amount) {
		
		Account account=accountDao.findAccount(accountNo);
		
		if(account!=null) {
			if(account.getOpeningBalance()>amount) {
				double balance=account.getOpeningBalance()-amount;
				account.setOpeningBalance(balance);
				return account;
			}
		}
		
		return null;
	}




	@Override
	public Account deposit(int accountNo, double amount) {
		Account account=accountDao.findAccount(accountNo);
		
		if(account!=null) {
			
				double balance=account.getOpeningBalance()+amount;
				account.setOpeningBalance(balance);
				return account;
			
		}
		
		return null;
	}




	@Override
	public List<Account> fundTransfer(int accountNo1, double amount,int accountNo2) {
		List<Account> list=new ArrayList<>();
		Account account1=accountDao.findAccount(accountNo1);
		Account account2=accountDao.findAccount(accountNo2);
		
		if(account1!=null &&account2!=null) {
			if(account1.getOpeningBalance()>amount) {
				double balance1=account1.getOpeningBalance()-amount;
				account1.setOpeningBalance(balance1);
			}
			
			double balance2=account2.getOpeningBalance()+amount;
			account2.setOpeningBalance(balance2);
			list.add(account1);
			list.add(account2);
			return list;
		}
		
		return null;
		
	}

}
