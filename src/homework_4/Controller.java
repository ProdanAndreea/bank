package homework_4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;

public class Controller {

	private Bank bank;
	
	public Controller() {
		bank = new Bank();
		bank.deserialize();
	}
	
	public void addPerson(int id, String name, String phone) {
		Person person = new Person(id, name, phone);
		bank.addPerson(person);	
	}
	
	public JTable editPerson(int id) {
		Person p = new Person(id);
		Person person = bank.getPerson(p);

		return tablePerson(person);
		
	}	
	
	private JTable tablePerson(Person person) {		
		Vector<Object> row = new Vector<Object>();
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector> rowData = new Vector<Vector>();
		
		columnNames.add("id");
		columnNames.addElement("Name");
		columnNames.addElement("Phone");
		
		if (person != null) {
			row.add(person.getName());
			row.add(person.getId());
			row.add(person.getPhone());
		} 						
		// add row to vector
		rowData.add(row);
		
		return new JTable(rowData, columnNames);
	}
	
	public JTable viewAllPersons() {
		return viewAllPersons(bank.getAllPersons());
	}
	
	private JTable viewAllPersons(List<Person> list) {
		Vector<Object> row = new Vector<Object>();
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector> rowData = new Vector<Vector>();
		
		columnNames.add("id");
		columnNames.addElement("Name");
		columnNames.addElement("Phone");
		if (list != null) {
			for (Person person: list) {
				row =  new Vector<Object>();	
				if (person != null) {
					row.add(person.getId());
					row.add(person.getName());
					row.add(person.getPhone());
				} 						
				rowData.add(row);	
			}
		}
		return new JTable(rowData, columnNames);
	}
		
	public void removePerson(int id) {
		Person p = new Person(id);
		bank.removePerson(p);
	}
	
	public void serialize() {
		bank.serialize();
	}
	
	public void addAccount(int id, String type, int accountNo, double balance, double interestRate) {
		Person person = new Person(id);
		if (type.equals("SA")) {
			//System.out.println(type);
			bank.addAccount(person, new SavingAccount(accountNo, balance, interestRate));
		} else if (type.equals("SP")) {
			//System.out.println(type);
			bank.addAccount(person, new SpendingAccount(accountNo, balance));
		}
	}
	
	public JTable viewAllAccounts() {
		ArrayList<Integer> ids = new ArrayList<>(100);
		return viewAllAccounts(bank.getAllAccounts(ids), ids);
	}

	private JTable viewAllAccounts(List<Account> list, ArrayList<Integer> ids) {
			Vector<Object> row = new Vector<Object>();
			Vector<String> columnNames = new Vector<String>();
			Vector<Vector> rowData = new Vector<Vector>();
			
			columnNames.add("Id");
			columnNames.add("No");
			columnNames.addElement("balance");
			columnNames.addElement("Interest Rate");
			columnNames.addElement("Last Amount Of Interests");
			columnNames.addElement("Subscribed");
			int i = 0;
			if (list != null) {
				for (Account account: list) {
					row =  new Vector<Object>();	
					if (account != null) {
						row.add(ids.get(i)); i++;
						row.add(account.getAccountNo());
						row.add("$" + round(account.getAccountBalance(), 3));
						if (account instanceof SavingAccount) {
							row.add("$" + round(((SavingAccount) account).getAnnualInterestRate(), 3));
							row.add("$" + round(((SavingAccount) account).getLastAmountOfInterestEarned(), 3));
							//System.out.println(((SavingAccount) account).getAnnualInterestRate() + ((SavingAccount) account).getLastAmountOfInterestEarned());
						} else {
							row.add("");
							row.add("");
						}
						if (account.isSubscribed() == true) {
							row.add("YES");
						} else {
							row.add("NO");
						}
					}
					rowData.add(row);	
				}
			}
			return new JTable(rowData, columnNames);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public JTable editAccount(int id, int accountNo) {	
		return tableAccount(bank.getAccount(new Person(id), new Account(accountNo)), id);
	}
	
	private JTable tableAccount(Account account, int id) {		
		Vector<Object> row = new Vector<Object>();
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector> rowData = new Vector<Vector>();
		
		columnNames.add("Id");
		columnNames.add("No");
		columnNames.addElement("balance");
		columnNames.addElement("Interest Rate");
		columnNames.addElement("Last Amount Of Interests");
		columnNames.addElement("Subscribed");
		if (account != null) {
			row.add(id); 
			row.add(account.getAccountNo());
			row.add("$" + round(account.getAccountBalance(), 3));
			if (account instanceof SavingAccount) {
				row.add("$" + round(((SavingAccount) account).getAnnualInterestRate(), 3));
				row.add("$" + round(((SavingAccount) account).getLastAmountOfInterestEarned(), 3));	
			} else {
				row.add("");
				row.add("");
			}
			if (account.isSubscribed() == true) {
				row.add("YES");
			} else {
				row.add("NO");
			}
		}						
		// add row to vector
		rowData.add(row);
		
		return new JTable(rowData, columnNames);
	}
	
	public void removeAccount(int id, int accountNo) {
		bank.removeAccount(new Person(id), new Account(accountNo));
	}
	
	public void subscribe(int id, int accountNo) {
		bank.subscribe(new Person(id), new Account(accountNo));	
	}
	
	public void unsubscribe(int id, int accountNo) {
		bank.unsubscribe(new Person(id), new Account(accountNo));	
	}
	
	public void print() {
		bank.printMap();
	}
	
	
	public void deposit(int id, int accountNo, double amount) {
		bank.deposit(new Person(id), new Account(accountNo), amount);	
	}
	
	public void withdraw(int id, int accountNo, double amount) {
		bank.withdraw(new Person(id), new Account(accountNo), amount);	
	}
	
	// get data from observer
	public double getDataObserver(int id) {
		//System.out.println(bank.getPerson(new Person(id)).getBalance() + " " + bank.getPerson(new Person(id)).getId());
		return bank.getPerson(new Person(id)).getBalance();
	}
}
