package homework_4;

public interface Observable {

	public void addObserver(Observer observer);
	public void removeObserver();
	public void notifyObserver(String message);
}
