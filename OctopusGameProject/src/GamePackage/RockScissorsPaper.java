package GamePackage;

import java.util.Random;
import java.util.Scanner;
public class RockScissorsPaper {
	boolean rpsStart() {
		Scanner sc=new Scanner(System.in);
		Random rand = new Random();
 
		 int ran;  
		 String rpsList[]= {"가위","바위","보","가위","바위","보","가위","바위","보","가위","바위","보"};
		 String user_sel="";
		 String com_sel;
		 int turn=0;
		 int lose_cnt=0;
		 while(turn<3) {
			 turn++;
			 ran = rand.nextInt(12);  
			 System.out.println("가위 바위 보 중 하나를 골라주세요.");
			 System.out.println("exit를 적으면 종료");
			 user_sel=sc.next();
			 com_sel=rpsList[ran];
			 if(user_sel.equals("exit")) break;
			 System.out.println("컴이 고른 거 : "+com_sel);
			 if(user_sel.equals("가위")) {
				 if(user_sel.equals(com_sel)) {
					 System.out.println("비겼습니다.");
					 
				 }
				 else if(com_sel.equals("보")){
					 System.out.println("이겼습니다.");
				 }
				 else {
					 System.out.println("졌습니다.");
					 lose_cnt++;
					 if(lose_cnt==2) {
						 System.out.println("죽었음");
						 return false;
					 } 
				 }
			 }
			 else if(user_sel.equals("바위")) {
				 if(user_sel.equals(com_sel)) {
					 System.out.println("비겼습니다.");
				 }
				 else if(com_sel.equals("가위")){
					 System.out.println("이겼습니다.");
				 }
				 else {
					 System.out.println("졌습니다.");
					 lose_cnt++;
					 if(lose_cnt==2) {
						 System.out.println("죽었음");
						 return false;
					 } 
				 }
			 }
			 else if(user_sel.equals("보")) {
				 if(user_sel.equals(com_sel)) {
					 System.out.println("비겼습니다.");
				 }
				 else if(com_sel.equals("바위")){
					 System.out.println("이겼습니다.");
				 }
				 else {
					 System.out.println("졌습니다.");
					 lose_cnt++;
					 if(lose_cnt==2) {
						 System.out.println("죽었음");
						 return false;
					 } 
				 }
			 }
			 System.out.println();
		 }
		 System.out.println();
		 System.out.println("통과");
		 return true;
	}

}
