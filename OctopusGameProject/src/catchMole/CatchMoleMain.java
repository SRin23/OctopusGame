package catchMole;

public class CatchMoleMain {
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 900;
	public static void main(String args[]) {
		String userNumber = "123";
		CatchMoleGUI cm = new CatchMoleGUI(userNumber);
		cm.startGame();
	}
}
