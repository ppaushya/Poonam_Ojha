package org.cap.model;

public class Account {

	private int accountNo;
	private Customer customer;
	private double openingBalance;
	
	public Account() {
		
	}
	
	public Account(int accountNo, Customer customer, double openingBalance) {
		super();
		this.accountNo = accountNo;
		this.customer = customer;
		this.openingBalance = openingBalance;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNo;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		long temp;
		temp = Double.doubleToLongBits(openingBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNo != other.accountNo)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (Double.doubleToLongBits(openingBalance) != Double.doubleToLongBits(other.openingBalance))
			return false;
		return true;
	}

	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", customer=" + customer + ", openingBalance=" + openingBalance
				+ "]";
	}
	
	
}
