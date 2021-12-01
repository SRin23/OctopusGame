package catchMole;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class CatchMoleLogic {
	public static int tmCount;
	public static int userScore;
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		int moleCount = 5;
		
		
		//1. 전체 두더지의 초기값은 false이다(구멍에 있다)
		boolean mole[] = new boolean[moleCount];
		for(int i = 0; i<moleCount; i++) {
			mole[i] = false;
		}
		//상태보기
		showStatus(moleCount, mole);
		
		tmCount = 0;
		userScore = 0;
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				
				System.out.println("-------------Stage" + (tmCount+1) + "-------------");
				if(tmCount<10) {
					int up = rand.nextInt(moleCount);
					mole[up] = true;
					
					showStatus(moleCount, mole);
					
					System.out.print("두더지 : ");
					int userIdx = sc.nextInt()-1;
					
					if(mole[userIdx]==true) {
						mole[up] = false;
						System.out.println("맞췄습니다.");
						userScore+=10;
					}
					else {
						userScore-=10;
					}
					System.out.println("점수 : " + userScore);
					showStatus(moleCount, mole);
					tmCount++;
				}else {
					timer.cancel();
				}
			}
		};
		timer.schedule(timerTask, 2000, 5000);
		
	}
	public static void moleStatus(boolean status) {
		if(status==false)System.out.print("● ");
		else System.out.print("♧ ");
	}
	
	public static void showStatus(int cnt, boolean status[]) {
		//상태보기
		for(int i= 0; i<cnt; i++) {
			moleStatus(status[i]);
		}
		System.out.println();
	}
}

//1. 전체 두더지의 초기값은 false이다(구멍에 있다)
//2. 랜덤으로 두더지를 true로 변경한다(구멍에서 튀어나온다)
//3. 사용자는 나온 두더지를 클릭/사용자는 나온 두더지의 인덱스를 입력
//4. 나온 두더지와 사용자가 입력한 두더지의 값이 같으면 두더지는 다시 false, 사용자는 득점
//5. 만약, 사용자가 입력한 두더지가 false인 상태라면 사용자는 실점
//6. 두더지는 약 1~5초사이의 시간(랜덤)동안 true였다가 false로 전환