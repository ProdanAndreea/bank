package homework_4;

public class SpendingAccount extends Account {


	private static final long serialVersionUID = -4485849521506979320L;

	public SpendingAccount(int accountNo, double depositAmount) {
		 super(accountNo, depositAmount);
	 }
	 
	 
	 public void withdraw(double withdrawAmount) {
		 balance -= withdrawAmount; 
		 notifyObserver("withdraw");
	 }

	 
	 public void deposit(double depositAmount) {
		 balance += depositAmount;
		 notifyObserver("deposit");
	 }

	 
	 @Override
	public String toString() {
		return "SpendingAccount [accountNo=" + accountNo + ", balance=" + balance + "]";
	}
	 
	 
}
