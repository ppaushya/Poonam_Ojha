package org.cap.dao;

import org.cap.model.Account;

public interface IAccountDao {

	public boolean createAccount(Account account);

	public Account findAccount(int accountNo);
}
