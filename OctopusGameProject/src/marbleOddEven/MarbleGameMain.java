package marbleOddEven;

public class MarbleGameMain {

	public static void main(String[] args) {
		boolean order = true;
		int heart = 3;
		int userNumber = 123;
		
		MarbleGameGUI Firstmg = new MarbleGameGUI();
		System.out.println("main화면입니다.");
		Firstmg.startGame(false);
	}

}
