package homework_4;

import java.io.Serializable;

public class Person implements Observer, Serializable {
	
	private static final long serialVersionUID = 9151664113931236181L;
	private String name;
	private int id;
	private String phone;
	private double balance;

	public Person( int id, String name, String phone) {
		this.name = name;
		this.id = id;
		this.phone = phone;
		//this.balance = 0.0;
	}
	
	public Person( int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
		
	public void update(Account account, String message) {
		balance = account.getAccountBalance();
		System.out.println( "Observer: " + id + " " + account.getAccountNo() + " operation " + message + ", new balance " + getBalance() ); 
	}
	
	public void subscribe(Account account) {
		account.addObserver(this);
	}
	
	public void unsubscribe(Account account) {
		account.removeObserver();
	}
		
	public String getPhone() {
		return phone;
	}

	public int getId() {
		return id;
	}
	
	public double getBalance() {
		return balance;
	}
	
	@Override
	public int hashCode() {
		int result = id;
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
}
