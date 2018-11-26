Feature: Create new Account
 Create new account details for valid customers. 
  Scenario: For valid Customer create new account
  	Given Customer details
  	When Valid Cusotmer Details
  	Then Create new Account
  Scenario: Invalid Customer Details
	  Given Customer details and opening balance
	  When Invalid Customer Details
	  Then throw error message 'Invalid Customer Details'
	Scenario: Cusotmer details with invalid opening balance
		Given Valid Customer details
		And Opening balance
		When  Invalid opening balance
		Then throw error message 'Insufficient Opening Balance'
  
  