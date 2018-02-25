package homework_4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Bank implements BankProc, Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<Person, HashSet<Account>> map;
	
	public Bank() {
		   map = new HashMap<>();
		   assert map != null : this;
	}

	public void addPerson(Person person) {
		assert validatePerson(person) == true: person;
		HashSet<Account> acc = new HashSet<Account>();
		int size = map.size();
		map.put(person, acc);
		assert map.size() == size + 1; 
		assert map.containsKey(person);
		assert isWellFormed(): this;
	}
	
	private boolean validatePerson(Person person) {
		if (person == null) {
			return false;
		}
		if (person.getName() == null || person.getName() == "") { 
			return false;
		}
		if (person.getId() <= 0) {
			return false;
		}
		try{
			int p = Integer.parseInt(person.getPhone());
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public void removePerson(Person person) {
		assert map.size() > 0;
		//HashMap<Person, HashSet<Account>> map_pre = map;
		int size = map.size();
		map.remove(person);
		assert map.size() == size - 1;
		assert !map.containsKey(person);	
		assert isWellFormed(): this;
	}
		
	public void addAccount(Person person, Account account) {	
		assert validateAccount(account) == true: account;
		assert person != null && person.getId() > 0: person;
		HashSet<Account> set = map.get(person);
		int size = 0;
		if (set != null) {
			size = set.size();
		}
		set.add(account);	
		assert set.size() == size + 1;	
		assert isWellFormed(): this;
	}
	
	private boolean validateAccount(Account account) {
		if (account == null) {
			return false;
		}
		if (account.getAccountNo() <= 0) {
			return false;
		}
		if (account.getAccountBalance() < 0) {
			return false;
		}
		return true;
	}
		
	public void removeAccount(Person person, Account account) {	
		assert person != null && person.getId() > 0: person;
		assert account != null && account.getAccountNo() > 0: account;
		HashSet<Account> set = map.get(person);
		int size = set.size();
		set.remove(account);
		assert set.size() == size - 1;
		assert !map.get(person).contains(account);
		assert isWellFormed(): this;
	}
		
	public Account getAccountData(Person person, Account account) {
		assert person != null && person.getId() > 0: person;
		assert account != null && account.getAccountNo() > 0: account;
		HashSet<Account> set = map.get(person);
		
		for(Account acc: set) {
			if (acc.equals(account)) {
				//System.out.println(acc);
				assert acc!= null;
				return acc;
			}
		}
		return null;
	}	
		
	public void deposit(Person person, Account account, double amount) {
		assert person != null && person.getId() > 0: person;
		assert account != null && account.getAccountNo() > 0: account;
		assert amount >= 0: amount;
		HashSet<Account> set = map.get(person);
		double amount_pre = 0.0;
		double amount_post = 0.0;
		// don't use account bc is an instance of Account 
		// (in Controller, new Account(accountNo)
		for(Account acc: set) {
			if (acc.equals(account)) {
				amount_pre = acc.getAccountBalance();
				if (acc instanceof SavingAccount) {
					((SavingAccount) acc).deposit(amount);
				} else ((SpendingAccount) acc).deposit(amount);
				amount_post = acc.getAccountBalance();
				break;
			}
		}
		assert amount_post == amount_pre + amount : "ammount_post: " + amount_post + "amount_pre: " + amount_pre; 
	}
		
	public void withdraw(Person person, Account account, double amount) {
		assert person != null && person.getId() > 0: person;
		assert account != null && account.getAccountNo() > 0: account;
		assert amount >= 0: amount;
		HashSet<Account> set = map.get(person);
		double amount_pre = 0.0;
		double amount_post = 0.0;
		for(Account acc: set) {
			amount_pre = acc.getAccountBalance();
			// don't use account in instanceof bc is given from Controller as new Account(accNo)
			if (acc.equals(account)) {
				if (acc instanceof SavingAccount) {
					((SavingAccount) acc).withdraw(amount);
				} else ((SpendingAccount) acc).withdraw(amount);
				amount_post = acc.getAccountBalance();
				break;
			}
		}
		assert amount_post == amount_pre - amount : "ammount_post: " + amount_post + "amount_pre: " + amount_pre;
	}
	
	public Person getPerson(Person person) {
		assert person != null && person.getId() > 0: person;
		for (Map.Entry<Person, HashSet<Account>> entry : map.entrySet()) {
		    Person key = entry.getKey();
		    if (key.equals(person)) {
		    	assert key != null;
		    	//System.out.println(key.getId() + " " + key.getName() + " " + key.getPhone() + " " + key.getBalance());
		    	return key;
		    }
		}
		return null;
	}
		
	public List<Person> getAllPersons() {
		ArrayList<Person> arr  = new ArrayList<Person> ();
		
		for (Map.Entry<Person, HashSet<Account>> entry : map.entrySet()) {
		    Person key = entry.getKey();
		    arr.add(key);
		}	
		assert arr != null;
		return arr;	
	}	
	
	public List<Account> getAllAccounts(ArrayList<Integer> ids) {
		assert ids != null;
		ArrayList<Account> arr  = new ArrayList<Account> ();
		
		for (Map.Entry<Person, HashSet<Account>> entry : map.entrySet()) {
		    HashSet<Account> accounts = entry.getValue();
		    for (Account acc: accounts) {
		    	arr.add(acc);
		    	ids.add(entry.getKey().getId());
		    }
		}	
		assert arr.size() == ids.size();
		assert arr != null;
		return arr;	
	}	
	
	public Account getAccount(Person person, Account account) {
		assert person != null && person.getId() > 0: person;
		assert account != null && account.getAccountNo() > 0: account;
		//ArrayList<Account> arr  = new ArrayList<Account> ();	
		HashSet<Account> set = map.get(person);
		if (set != null) {
			for(Account acc: set) {
				if (acc.equals(account)) {
					assert acc != null;
					return acc;
				}
			}
		}
		return null;	
	}
		
	public void subscribe(Person person, Account account) {
		assert person != null && person.getId() > 0: person;
		assert account != null && account.getAccountNo() > 0: account;
		HashSet<Account> set = map.get(person);

		for (Account acc: set) {
			if (acc.equals(account)) {
				person.subscribe(acc);
				assert acc.getObserver() != null;
			}
		}
	}
		
	public void unsubscribe(Person person, Account account) {
		assert person != null && person.getId() > 0: person;
		assert account != null && account.getAccountNo() > 0: account;
		HashSet<Account> set = map.get(person);
		
		for (Account acc: set) {
			if (acc.equals(account)) {
				person.unsubscribe(acc);
				assert acc.getObserver() == null;
			}
		}
	}	
	
	public void display(Person person) {
		HashSet<Account> set = map.get(person);
		
		System.out.println(person.getName() + ": ");
		
		for(Account acc: set) {
			System.out.println(acc);
		}
	}
		
	public void serialize() {	
	    try (
	      OutputStream file = new FileOutputStream("bank.ser");
	      OutputStream buffer = new BufferedOutputStream(file);
	      ObjectOutput output = new ObjectOutputStream(buffer);
	    ){
	      output.writeObject(map);
	      System.err.println("Successfully serialized"); 
	      output.flush(); // flush the object output stream
	    }
	    catch(IOException exception){
	      System.err.println(exception);
	    }
	}	
	
	public boolean isWellFormed() { 
		int noKeys = 0;
		int noV = 0;
		
		for (Map.Entry<Person, HashSet<Account>> entry : map.entrySet()) {
			noKeys ++;
			noV = 0;
			// count the values for each entry
			for(Account acc: entry.getValue()) {
				noV ++;
			}
			if (noV != entry.getValue().size()) {
				return false;
			}
		}
		if (noKeys != map.size()) {
			return false;
		}
		return true;
	}
	
	/**
	 * @invariant isWellFormed() 
	 */
	public void deserialize() {		
	   try (
	      InputStream file = new FileInputStream("bank.ser");
	      InputStream buffer = new BufferedInputStream(file);
	      ObjectInput input = new ObjectInputStream(buffer);
	    ){
		   map = (HashMap<Person, HashSet<Account>>)input.readObject();
	       System.out.println("Recovered account: " + map);

	       assert isWellFormed();
	       //file.close();
	    }
	    catch(IOException | ClassNotFoundException exception ){
	      System.err.println(exception);
	    }   
	}
		
	public void printMap() {
		System.out.println(map);
	}

	public static void main(String[] args) {
		Bank bank = new Bank();
	}
	
}
