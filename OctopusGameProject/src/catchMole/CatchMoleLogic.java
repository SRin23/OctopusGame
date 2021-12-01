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
		
		
		//1. ��ü �δ����� �ʱⰪ�� false�̴�(���ۿ� �ִ�)
		boolean mole[] = new boolean[moleCount];
		for(int i = 0; i<moleCount; i++) {
			mole[i] = false;
		}
		//���º���
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
					
					System.out.print("�δ��� : ");
					int userIdx = sc.nextInt()-1;
					
					if(mole[userIdx]==true) {
						mole[up] = false;
						System.out.println("������ϴ�.");
						userScore+=10;
					}
					else {
						userScore-=10;
					}
					System.out.println("���� : " + userScore);
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
		if(status==false)System.out.print("�� ");
		else System.out.print("�� ");
	}
	
	public static void showStatus(int cnt, boolean status[]) {
		//���º���
		for(int i= 0; i<cnt; i++) {
			moleStatus(status[i]);
		}
		System.out.println();
	}
}

//1. ��ü �δ����� �ʱⰪ�� false�̴�(���ۿ� �ִ�)
//2. �������� �δ����� true�� �����Ѵ�(���ۿ��� Ƣ��´�)
//3. ����ڴ� ���� �δ����� Ŭ��/����ڴ� ���� �δ����� �ε����� �Է�
//4. ���� �δ����� ����ڰ� �Է��� �δ����� ���� ������ �δ����� �ٽ� false, ����ڴ� ����
//5. ����, ����ڰ� �Է��� �δ����� false�� ���¶�� ����ڴ� ����
//6. �δ����� �� 1~5�ʻ����� �ð�(����)���� true���ٰ� false�� ��ȯ