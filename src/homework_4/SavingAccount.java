package homework_4;

public class SavingAccount extends Account {
	

	private static final long serialVersionUID = -2139257515728993408L;
	private double annualInterestRate;
	private double lastAmountOfInterestEarned;
	  
	 
	 public SavingAccount(int accountNo, double depositAmount, double interestRate) {
		 super(accountNo, depositAmount);
		 
	     annualInterestRate = interestRate;
	     lastAmountOfInterestEarned = 0.0;
	 }
	 
	 
	 public void withdraw(double withdrawAmount) {
		 balance -= withdrawAmount;		 
		 notifyObserver("withdraw");
	 }

	 
	 public void deposit(double depositAmount) {
		 balance += depositAmount;
		 addInterest();	 
		 notifyObserver("deposit");
	 }
	 
	 public void addInterest() {
	    // calculate monthly interest rate
	    double monthlyInterestRate = annualInterestRate / 12;
	    // calculate the last amount of interest earned
	    lastAmountOfInterestEarned = monthlyInterestRate * balance;
	    // add the interest to the balance
	    annualInterestRate += lastAmountOfInterestEarned;
	 }

	 public double getAnnualInterestRate() {
	    return annualInterestRate;
	 }

	 public double getLastAmountOfInterestEarned() {
	    return lastAmountOfInterestEarned;
	 }
	
	/*
	 @Override  
	 public boolean equals(Object obj) {  
	     //if (obj == null) { return false; }  
	     //if (getClass() != obj.getClass()) { return false; }  
	    return super.equals(obj);
	     
	 }*/

	 
	 @Override
	public String toString() {
		return "SavingAccount [ accountNo = " + accountNo + ", balance = $" + balance + ", annualInterestRate = " + annualInterestRate 
				+ ", lastAmountOfInterestEarned = " + lastAmountOfInterestEarned + "]";
	}
}
