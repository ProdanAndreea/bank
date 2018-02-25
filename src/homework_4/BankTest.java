package homework_4;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {

	@Test
	public void testGetPerson() {
		Bank bank = new Bank();
		Person person = new Person(1, "Aldec", "1231110000");
		bank.addPerson(person);
		assertEquals(person, bank.getPerson(new Person(1)));
		assertTrue(bank.getPerson(new Person(1)) != null);
	}
	
	@Test
	public void testAddPerson() {
		Bank bank = new Bank();
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		Person person = bank.getPerson(new Person(1));
		assertEquals(person.getId(), 1);
		assertEquals(person.getName(), "Aldec");
		assertEquals(person.getPhone(), "1231110000");
	}
	
	@Test
	public void testRemovePerson() {
		Bank bank = new Bank();
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		bank.removePerson(new Person(1, "Aldec", "1231110000"));
		assertTrue(bank.getPerson(new Person(1)) == null);
	}

	@Test
	public void testaddAccount() {
		Bank bank = new Bank();
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		Account accSP = new SpendingAccount(1, 1000);
		Account accSA = new SavingAccount(2, 2000, 2);
		bank.addAccount(new Person(1), accSP);
		bank.addAccount(new Person(1), accSA);
		Account acc = bank.getAccount(new Person(1), new Account(1));
		assertTrue(acc == accSP);
		assertFalse(acc == accSA);
		acc = bank.getAccount(new Person(1), new Account(2));
		assertTrue(acc == accSA);
		assertFalse(acc == accSP);
	}
	
	@Test
	public void testRemoveAccount() {
		Bank bank = new Bank();
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		bank.addAccount(new Person(1), new SpendingAccount(1, 1000));
		bank.addAccount(new Person(1), new SavingAccount(2, 2000, 2));
		assertFalse(bank.getAccount(new Person(1), new Account(1)) == null);
		assertFalse(bank.getAccount(new Person(1), new Account(2)) == null);
		bank.removeAccount(new Person(1), new Account(1));
		bank.removeAccount(new Person(1), new Account(2));
		assertTrue(bank.getAccount(new Person(1), new Account(1)) == null);
		assertTrue(bank.getAccount(new Person(1), new Account(2)) == null);
	}
	
	
	@Test
	public void testGetAccountData() {
		Bank bank = new Bank();
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		Account a = new SpendingAccount(1, 1000);
		bank.addAccount(new Person(1), a);	
		Account account = bank.getAccountData(new Person(1, "", ""), new SpendingAccount(1, 0));
		assertEquals(account, a);	
	}
	
	
	@Test
	public void testDeposit() {
		Bank bank = new Bank();
		
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		bank.addAccount(new Person(1), new SpendingAccount(1, 1000));
		bank.addAccount(new Person(1), new SavingAccount(2, 2000, 2));
		
		bank.deposit(new Person(1), new SpendingAccount(1, 0), 100);
		bank.deposit(new Person(1), new SavingAccount(2, 0, 0), 100);
		
		assertTrue(bank.getAccount(new Person(1), new Account(1)).getAccountBalance() == 1100);
		assertTrue(bank.getAccount(new Person(1), new Account(2)).getAccountBalance() == 2100);
	}
	
	
	@Test
	public void testWithdraw() {
		Bank bank = new Bank();
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		bank.addAccount(new Person(1), new SpendingAccount(1, 1100));
		bank.addAccount(new Person(1), new SavingAccount(2, 2100, 2));
		
		bank.withdraw(new Person(1), new SpendingAccount(1, 0), 100);
		bank.withdraw(new Person(1), new SavingAccount(2, 0, 0), 100);
		
		assertTrue(bank.getAccount(new Person(1), new Account(1)).getAccountBalance() == 1000);
		assertTrue(bank.getAccount(new Person(1), new Account(2)).getAccountBalance() == 2000);
	}
	
	
	@Test
	public void testSubscribe() {
		Bank bank = new Bank();
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		bank.addAccount(new Person(1), new SpendingAccount(1, 1100));
		bank.addAccount(new Person(1), new SavingAccount(2, 2100, 2));
		
		bank.subscribe(new Person(1), new Account(1));
		assertTrue(bank.getAccount(new Person(1), new Account(1)).getObserver() != null);
		bank.subscribe(new Person(1), new Account(2));
		assertTrue(bank.getAccount(new Person(1), new Account(2)).getObserver() != null);
	}
	
	
	@Test
	public void testUnsubscribe() {
		Bank bank = new Bank();
		bank.addPerson(new Person(1, "Aldec", "1231110000"));
		bank.addAccount(new Person(1), new SpendingAccount(1, 1100));
		bank.addAccount(new Person(1), new SavingAccount(2, 2100, 2));
		
		bank.unsubscribe(new Person(1), new Account(1));
		assertTrue(bank.getAccount(new Person(1), new Account(1)).getObserver() == null);
		bank.unsubscribe(new Person(1), new Account(2));
		assertTrue(bank.getAccount(new Person(1), new Account(2)).getObserver() == null);
	}
	
	
	
}
