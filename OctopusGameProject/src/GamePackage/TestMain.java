package GamePackage;

import BeanOddEvenGame.beadingGame;

public class TestMain {

	public static void main(String[] args) {
		int heart = 3;
		RockScissorsPaper rps=new RockScissorsPaper();
		boolean first = rps.rpsStart();
		
		beadingGame bg = new beadingGame();
		heart = bg.gameStart(first, heart, 456);
	}

}
