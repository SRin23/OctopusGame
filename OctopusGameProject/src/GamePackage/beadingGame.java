package GamePackage;

import java.util.Scanner;
public class beadingGame {
	final int odd = 1;
	final int even = 0;
	
	//false면 컴퓨터부터, true면 사용자부터 구슬을 쥐는 수비
	int gameStart(boolean start, int heart) {
		Scanner sc = new Scanner(System.in);
		
		//사용 구슬의 개수 묻기
		System.out.print("몇개의 구슬을 사용하시곘습니까?");
		int marble = sc.nextInt();
		
		
		//컴퓨터가 구슬을 쥔다.
		if(start==false) {
			//컴퓨터가 랜덤으로 구슬의 개수 정함(0~marble)
			int rand = (int)(Math.random()*marble);
			
			//사용자가 홀짝 정함(이후, 홀짝 선택바 만들기)
			System.out.println("컴퓨터가 구슬을 쥐었습니다.");
			System.out.print("홀짝을 선택해 주십시오.(홀 : 1, 짝 : 0)");
			System.out.print("(홀 : 1, 짝 : 0)");
			int user_oddeven = sc.nextInt();
			
			String user_str;
			if(user_oddeven==0) {
				user_str = "짝";
			}else if(user_oddeven==1) {
				user_str = "홀";
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주십시오");
				return heart;
			}
			
			if(rand%2==user_oddeven) {
				System.out.println( user_str + "을 맞췄습니다.");
				System.out.println("컴퓨터 : " + rand + "  사용자 : " + user_str);
			}else {
				System.out.println(rand + "개로 틀렸습니다.");
				return heart--;
			}
			
		}else {
			System.out.println("0~" + marble + "중 숫자 하나를 선택해 주세요.");
			int user_num = sc.nextInt();
			
			int rand = (int)(Math.random()*2)+1;
			
			String comp_str;
			if(rand==0) {
				comp_str = "짝";
			}else {
				comp_str = "홀";
			}
			
			
			if(user_num%2==rand) {
				System.out.println( comp_str + "을 맞췄습니다.");
				System.out.println("컴퓨터 : " + comp_str + "  사용자 : " + user_num);
			}else {
				System.out.println(rand + "개로 당신이 이겼습니다.");
				return heart;
			}
		}
		return heart;
	}
	
}
