package BeanOddEvenGame;

public class beadingGameMain {

	public static void main(String[] args) {
		int heart = 3;
		
		beadingGame bg = new beadingGame();
		heart = bg.gameStart(false, heart, 135);
		
		System.out.println("남은 목숨의 개수 : " + heart);
	}

}
