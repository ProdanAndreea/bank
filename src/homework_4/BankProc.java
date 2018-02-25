package homework_4;

import java.util.ArrayList;
import java.util.List;

public interface BankProc {

	/**
	 *  @param person the person to be added
	 *  @pre person != null && name != null && name != ""
	 *  @pre id > 0 && phone is number format
	 *  @post size() == size()@pre + 1
	 *  @post containsKey(person) == true
	 *  @invariant isWellFormed() 
	 */
	public void addPerson(Person person);

	/**
	 * @param person the person to be removed
	 * @pre size() > 0
	 * @post size() == size()@pre - 1 
	 * @post !contains(person)
	 * @invariant isWellFormed() 
	 */
	public void removePerson(Person person);
	
	/**
	 * @param person the person to whom the account is added
	 * @param the account to be added
	 * @pre person != null  && person.getId() > 0
     * @pre account != null && accountNo > 0 && balance >= 0
	 * @post map.get(person).size() == map.get(person).size()@pre + 1
	 * @invariant isWellFormed() 
	 */
	public void addAccount(Person person, Account account);
	
	/**
	 * @param person the person to whom the account is removed
	 * @param the account to be removed
	 * @pre person != null && person.getId() > 0
	 * @pre account != null && account.getAccountNo() > 0
	 * @post map.get(person).size() == map.get(person).size()@pre - 1
	 * @post !map.get(person).cotains(account)
	 * @invariant isWellFormed() 
	 */
	public void removeAccount(Person person, Account account);
	
	/**
	 * @param person the person to look for his/her account
	 * @param account the searched account
	 * @pre person != null && person.getId() > 0
	 * @pre account != null && account.getAccountNo() > 0
	 * @post if found ==> @result != null 	
	 */ 
	public Account getAccountData(Person person, Account account);
	
	/**
	 * @param person the main_holder of the account
	 * @param account the account to which the amount must be added
	 * @param amount the amount which must be added
	 * @pre person != null && person.getId() > 0
	 * @pre account != null && account.getAccountNo() > 0
	 * @pre amount >= 0
	 * @post account.getAmount = account.getAmount@pre + amount
	 */
	public void deposit(Person person, Account account, double amount);
	
	/**
	 * @param person the main_holder of the account
	 * @param account the account to which the amount must be withdraw
	 * @param amount the amount which must be withdraw
	 * @pre person != null && person.getId() > 0
	 * @pre account != null && account.getAccountNo() > 0
	 * @pre amount >= 0
	 * @post account.getAmount = account.getAmount@pre - amount
	 */
	public void withdraw(Person person, Account account, double amount);
	
	/**
	 * @param person the person from whom to get data
	 * @pre person != null && person.getId() > 0
	 * @post if found ==> @result != null 
	 */
	public Person getPerson(Person person);
	
	/**
	 * @pre none
	 * @post @return @result != null
	 */
	public List<Person> getAllPersons();
	
	/**
	 * @param ids list where to save the accounts' main_holders id
	 * @pre ids != null
	 * @post ids != null && ids.size() == arr.size()
	 * @post @return @result != null
	 */
	public List<Account> getAllAccounts(ArrayList<Integer> ids); 
	
	/**
	 * @param person the person from whom to get the account
	 * @param account the account to get
	 * @pre person != null && person.getId() > 0
	 * @pre account != null && account.getAccountNo() > 0
	 * @post @return if found ==> @result != null else result == null
	 */
	public Account getAccount(Person person, Account account); 
	
	/**
	 * @param person the person to be subscribed
	 * @param account the account which to subscribe
	 * @pre person != null && person.getId() > 0
	 * @pre account != null && account.getAccountNo() > 0
	 * @post the person is subscribed 
	 */
	public void subscribe(Person person, Account account);
	
	/**
	 * @param person the person to be unsubscribed
	 * @param account the account which to unsubscribe
	 * @pre person != null && person.getId() > 0
	 * @pre account != null && account.getAccountNo() > 0
	 * @post the person is unsubscribed 
	 */
	public void unsubscribe(Person person, Account account);
	
	/**
	 * Called at the end of any public method which has changed 
	 * state (any "mutator" method)
	 * @return true if map is in a well state
	 */
	public boolean isWellFormed();
	
}
