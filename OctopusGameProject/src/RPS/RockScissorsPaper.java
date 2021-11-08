package RPS;

import java.util.Random;
import java.util.Scanner;
public class RockScissorsPaper {

	boolean rpsStart() {
		Scanner sc=new Scanner(System.in);
		Random rand = new Random();
 
		 int ran;  
		 String rpsList[]= {"가위","바위","보"};
		 String user_sel=""; //유저가 고른 것(입력 받음)
		 String master_sel=""; //컴이 고른 것(랜덤)
		 int turn=0; //삼세판으로 진행
		 int win_cnt=0;

		 System.out.println("세판 중 두판을 이기거나 비기면 선공, 두판을 지면 후공입니다.");
		 while(turn<3) {
			 turn++;
			 ran = rand.nextInt(3);  
			 System.out.println("가위 바위 보 중 하나를 골라주세요");
			 
			 user_sel=sc.next();
			 master_sel=rpsList[ran];
			 System.out.println("상대방 : "+master_sel);
			 //유저가 가위를 골랐을 경우
			 if(user_sel.equals("가위")) {
				 if(user_sel.equals(master_sel)) {
					System.out.println("비겼습니다.");
				 }
				 else if(master_sel.equals("보")){
					System.out.println("이겼습니다.");
					win_cnt++;
				 }
				 else {
					 System.out.println("졌습니다.");
				 }
			 }
			 //유저가 바위를 골랐을 경우
			 else if(user_sel.equals("바위")) {
				 if(user_sel.equals(master_sel)) {
					System.out.println("비겼습니다.");
				 }
				 else if(master_sel.equals("가위")){
					System.out.println("이겼습니다.");
					win_cnt++;
				 }
				 else {
					 System.out.println("졌습니다.");
				 }
			 }
			 //유저가 보를 골랐을 경우
			 else if(user_sel.equals("보")) { 
				 if(user_sel.equals(master_sel)) {
					System.out.println("비겼습니다.");
				 }
				 else if(master_sel.equals("바위")){
					System.out.println("이겼습니다.");
					win_cnt++;
				 }
				 else {
					 System.out.println("졌습니다.");
				 }
			 }
			 System.out.println();
		 }
		 if(win_cnt == 2){
			System.out.println("당신은 선공입니다.");
			return true;
		 }
		 else {
			 System.out.println("당신은 후공입니다");
			 return false;
		 }
		 
	}

}
