package transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.cap.dao.IAccountDao;
import org.cap.model.Account;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef {
	
	private Account account;
	private Account account2;
	private IAccountService accountService;
	private double amount;
	
	@Mock
	private IAccountDao accountDao;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		accountService=new AccountServiceImpl(accountDao);
		amount=2000;
	}
	
	@Given("^Valid Account details$")
	public void valid_Account_details() throws Throwable {
	   Account account=new Account();
	   Address address=new Address("34 North Street", "Pune");
	   Customer customer=new Customer("Kamal", "Singh", address);
		   
	   account.setAccountNo(1001);
	   account.setCustomer(customer);
	   account.setOpeningBalance(3500);
	   
	   
	   //dummy declarion
	   Mockito.when(accountDao.findAccount(1001)).thenReturn(account);
	   
	   //Actual Logic
	   this.account=accountService.findAccount(1001);
	   
		//Mockito Verification
	   Mockito.verify(accountDao).findAccount(1001);
	   assertNotNull(this.account);
	   
	}

	@Given("^Positive amount$")
	public void positive_amount() throws Throwable {
	 assertTrue(this.amount>0);
	}

	@When("^For valid balance in the account$")
	public void for_valid_balance_in_the_account() throws Throwable {
	   assertTrue(this.account.getOpeningBalance()>this.amount);
	}

	@Then("^Perform account withdrawal$")
	public void perform_account_withdrawal() throws Throwable {
	   
		Account account=accountService.withdraw(1001, this.amount);
		
		
		assertEquals(1500, account.getOpeningBalance(),0.0);
	}

	@When("^Balance is positive$")
	public void balance_is_positive() throws Throwable {
	   assertTrue(this.account.getOpeningBalance()>0);
	}

	@Then("^Perform account deposit$")
	public void perform_account_deposit() throws Throwable {
		Account account=accountService.deposit(1001, this.amount);
		
		assertEquals(5500, account.getOpeningBalance(),0.0);
	}


	@When("^Balance in withdrawing account is positive$")
	public void balance_in_withdrawing_account_is_positive() throws Throwable {
	    assertTrue(this.account.getOpeningBalance()>this.amount);
	}

	@Then("^Perform Fund Transfer$")
	public void perform_Fund_Transfer() throws Throwable {
		
		Account account2=new Account();
		   Address address2=new Address("34 North Avvenue", "chennai");
		   Customer customer2=new Customer("Maqbool", "miya", address2);
			   
		   account2.setAccountNo(1002);
		   account2.setCustomer(customer2);
		   account2.setOpeningBalance(3500);
		   
		   List<Account> list=accountService.fundTransfer(1001, this.amount,1002);
		   account=list.get(0);
		   account2=list.get(1);
			//assertEquals(1500,account.getOpeningBalance(),0.0);
			assertEquals(5500,account2.getOpeningBalance(),0.0);
	
	}



}
