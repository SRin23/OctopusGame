package catchMole;

public class CatchMoleMain {
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 900;
	public static void main(String args[]) {
		int heart = 3;
		int userNumber = 123;
		CatchMoleGUI cm = new CatchMoleGUI(heart, userNumber);
		cm.startGame();
	}
}
