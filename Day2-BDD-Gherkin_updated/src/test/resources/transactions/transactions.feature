Feature: Perform transactions
	Transactions like withdraw, deposit and fund transfer 
 
 Background: 
 For valid account and balance details
		Given Valid Account details
  		And Positive amount
  	
  Scenario: Perform withdrawal Operation
 		When For valid balance in the account
  	Then Perform account withdrawal 
  	
  Scenario: Perform deposit Operation
 		When Balance is positive
  	Then Perform account deposit 
  Scenario: Perform Fund Transfer
  		When Balance in withdrawing account is positive
  		Then Perform Fund Transfer