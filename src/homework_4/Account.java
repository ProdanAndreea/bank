package homework_4;

import java.io.Serializable;

public class Account implements Observable, Serializable {

	private static final long serialVersionUID = -5082315973106773041L;
	protected int accountNo;
	protected double balance;
	
	private Observer main_holder;
	

	public Account(int accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}
	
	public Account(int accountNo) {
		this.accountNo = accountNo;
	}
	
	public int getAccountNo() {
		return accountNo;
	}
	
	public double getAccountBalance() {
	    return balance;
	 }
	
	public void addObserver(Observer observer) {
	     this.main_holder = observer; 		
	}
	
	public void notifyObserver(String message) {
		if (main_holder != null) {
			main_holder.update(this, message);
		}
	}
	
	public void removeObserver() {
		main_holder = null;
	}
	
	public Observer getObserver() {
		return main_holder;
	}
	
	public boolean isSubscribed() {
		if (main_holder != null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Account [accountNo = " + accountNo + ", balance = $" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime + accountNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		Account other = (Account) obj;
		if (accountNo != other.accountNo) {
			return false;
		}
		return true;
	}

}
