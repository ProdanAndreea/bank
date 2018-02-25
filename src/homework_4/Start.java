package homework_4;

public class Start {
	
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		new ViewPerson(controller).setVisible(true);
		new ViewAccount(controller).setVisible(true);
	}
}
