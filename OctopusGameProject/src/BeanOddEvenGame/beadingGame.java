package BeanOddEvenGame;

import java.util.Random;
import java.util.Scanner;
public class beadingGame {
	final int odd = 1;
	final int even = 0;
	
	//start가 false면 컴퓨터부터, true면 사용자부터 구슬을 쥔다.
	public int gameStart(boolean start, int heart, int userNumber) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		int marble;	//사용할 구슬의 개수
		
		int computerScore;	//컴퓨터의 구슬의 개수
		int userScore;	//사용자의 구슬의 개수
		
		//사용 구슬의 개수 묻기(최대 구슬 사용 개수 50개)
		while(true) {
			System.out.print("몇개의 구슬을 사용하시겠습니까?(최대 구슬 개수 : 50개) -> ");
			marble = sc.nextInt();
			if(marble>50) {
				System.out.println("최대 구슬 사용 개수를 넘었습니다. 다시 입력해 주십시오.");
				System.out.println();
				continue;
			}
			break;
		}
		
		//사용자&컴퓨터 점수 reset
		computerScore = marble;
		userScore = marble;
		
		
		//게임 시작(선,후공은 초기에 정한 후, 번
		while(userScore>0&&computerScore>0) {
			//컴퓨터가 구슬을 쥔다.
			if(start==false) {
				
				//System.out.println("----------후공----------");
				
				//컴퓨터가 랜덤으로 구슬의 개수를 정하는 코드
				int computerSelect = rand.nextInt(marble)+1;
				
				//사용자가
				System.out.println("457번이 구슬을 쥐었습니다.");
				System.out.print("홀짝을 선택해 주십시오 -> ");
				
				//사용자가 홀짝을 입력
				String user_oddeven = sc.next();
				
				if((computerSelect%2==0&&user_oddeven.equals("짝"))||(computerSelect%2==1&&user_oddeven.equals("홀"))) {
					System.out.println("457번의 구슬 개수 : " + computerSelect);
					System.out.println(user_oddeven + "을 맞췄습니다.");
					System.out.println("당신의 구슬이 " + computerSelect + "만큼 증가됩니다.");
					userScore+=computerSelect;
					computerScore-=computerSelect;
				}else {
					System.out.println("457번의 구슬 개수 : " + computerSelect);
					System.out.println("당신의 구슬이 " + computerSelect + "만큼 차감됩니다.");
					userScore-=computerSelect;
					computerScore+=computerSelect;
				}
				
				if(userScore<0) {
					userScore = 0;
				}else if(computerScore<0) {
					computerScore = 0;
				}
				System.out.println("현재 구슬의 개수  | " + userNumber + " : " + userScore + " | 457 : " + computerScore);
				start=true;
			}
			
			
			if(start==true) {
				//System.out.println("----------선공----------");
				int userSelect;
				
				while(true) {
					System.out.println("0~" + userScore + "중 손에 쥘 구슬의 개수를 입력하십시오 -> ");
					userSelect = sc.nextInt();
					if(userSelect > userScore) {
						System.out.println(userSelect + "만큼의 구슬이 존재하지 않습니다.");
						System.out.println("다음 턴으로 돌아갑니다.");
						start = false;
						break;
					}
				}
				
				
				int computerOddEven = rand.nextInt(2);
				
				String rand_str;
				if(computerOddEven==0) {
					rand_str = "짝";
				}else {
					rand_str = "홀";
				}
				
				
				if((userSelect%2==0&&rand_str.equals("짝"))||(userSelect%2==1&&rand_str.equals("홀"))) {
					System.out.println("당신의 구슬 개수 : " + userSelect);
					System.out.println("457번이 " + rand_str + "을 맞췄습니다.");
					System.out.println("당신의 구슬이 " + userSelect + "만큼 차감됩니다.");
					userScore-=userSelect;
					computerScore+=userSelect;
				}else {
					System.out.println("당신의 구슬 개수 : " + userSelect);
					System.out.println("457번이 " + rand_str + "로, 틀렸습니다.");
					System.out.println("당신의 구슬이 " + userSelect + "만큼 증가됩니다.");
					userScore+=userSelect;
					computerScore-=userSelect;
				}
				
				if(userScore<0) {
					userScore = 0;
				}else if(computerScore<0) {
					computerScore = 0;
				}
				
				System.out.println("현재 구슬의 개수  | " + userNumber + " : " + userScore + " | 457 : " + computerScore );
				start=false;
			}
			
		}
		
		if(userScore<=0) {
			System.out.println("당신의 구슬이 존재하지 않습니다.");
			System.out.println("목숨이 하나 사라집니다.");
			heart--;
		}
		else if(computerScore<=0) {
			System.out.println("457번의 구슬이 존재하지 않습니다.");
			System.out.println("당신이 이겼습니다.");
			System.out.println("무사히 다음 게임으로 넘어갑니다.");
		}
		
		return heart;
		
	}
	
}
