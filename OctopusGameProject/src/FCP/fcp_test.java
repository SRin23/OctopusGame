package FCP;

import java.util.Timer;
import java.util.TimerTask;

public class fcp_test {
	static int countDown = 0;
	static boolean win;
	public static void main(String[] args) {
		int heart=3;
		fcp fg = new fcp();

		//3초 뒤 게임 시작
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {
			
			@Override
			public void run() {
				if(countDown<3) {
					System.out.println(3-countDown+"...");
					countDown++;
				}
				else {
					t.cancel();
					System.out.println("게임 스타트");
					win = fg.startGame();
				}
			}
		};
		t.scheduleAtFixedRate(tt, 1000,1000);
		
		if(!win) heart--; 
	}
	
}
