package BeanOddEvenGame;

public class MarbleGame {

	public static void main(String[] args) {
		int heart = 3;
		int userNumber = 123;
		int marbleCount = 30;
		
		//marbleDefence mDefence = new marbleDefence(false, heart, userNumber);
		//marbleAttact mAttact = new marbleAttact(false, heart, userNumber, marbleCount);
		FirstMarbleGame Firstmg = new FirstMarbleGame(true, heart, userNumber);
	}

}
