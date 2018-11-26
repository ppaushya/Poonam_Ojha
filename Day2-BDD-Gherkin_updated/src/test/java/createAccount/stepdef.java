package createAccount;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.cap.dao.IAccountDao;
import org.cap.exception.InsufficientbalanceException;
import org.cap.model.Account;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;
import junit.framework.Assert;

public class stepdef {
	
	private double balance;
	
	private IAccountService accountService;
	
	private Customer customer;
	
	@Mock
	private IAccountDao accountDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		accountService=new AccountServiceImpl(accountDao);
		
	}
	
	@Given("^Customer details$")
	public void cusotmer_details() throws Throwable {
	    
		Address address=new Address("23 North avvenue","Chennai");
		customer=new Customer("Tom", "Jerry", address);
	}

	@When("^Valid Cusotmer Details$")
	public void valid_Cusotmer_Details() throws Throwable {
	  assertNotNull(customer);
	}

	@Then("^Create new Account$")
	public void create_new_Account() throws Throwable {
		
				Account account=new Account();
				//account.setAccountNo(0);
				account.setCustomer(customer);
				account.setOpeningBalance(1000.0);
		
				System.out.println(account);
				
		//Dummy Declartion
		Mockito.when(accountDao.createAccount(account)).thenReturn(true);
		
		//Actual Logic
		Account account2=accountService.createAccount(customer, 1000.0);
		
		System.out.println(account2);
		//validate using Mock
		Mockito.verify(accountDao).createAccount(account);
		
	}

	@Given("^Customer details and opening balance$")
	public void customer_details_and_opening_balance() throws Throwable {
	  customer=null;
	}



	@When("^Invalid Customer Details$")
	public void invalid_Customer_Details() throws Throwable {
	    assertNull(customer);
	}

	@Rule
	public ExpectedException thrown=ExpectedException.none();
	@Then("^throw error message 'Invalid Customer Details'$")
	public void throw_error_message_Invalid_Customer_Details() throws Throwable {
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Sorry! Invalid Customer");
		try {
			accountService.createAccount(customer, 1000.0);
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
		}
	}

	@Given("^Valid Customer details$")
	public void valid_Customer_details() throws Throwable {
		Address address=new Address("23 North avvenue","Chennai");
		customer=new Customer("Tom", "Jerry", address);
	}

	@Given("^Opening balance$")
	public void opening_balance() throws Throwable {
	   
	}

	@When("^Invalid opening balance$")
	public void invalid_opening_balance() throws Throwable {
	   balance=300;
	}

	@Rule
	public ExpectedException thrown1=ExpectedException.none();
	
	
	@Then("^throw error message 'Insufficient Opening Balance'$")
	public void throw_error_message_Insufficient_Opening_Balance() throws Throwable {
		
		thrown1.expect(InsufficientbalanceException.class);
		thrown1.expectMessage("Insufficient Opening Balance");
		try {
		accountService.createAccount(customer,balance );
		}catch (InsufficientbalanceException e) {
			// TODO: handle exception
		}
	}


}
