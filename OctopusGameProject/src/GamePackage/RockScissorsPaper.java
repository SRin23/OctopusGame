package GamePackage;

import java.util.Random;
import java.util.Scanner;
public class RockScissorsPaper {
	boolean rpsStart() {
		Scanner sc=new Scanner(System.in);
		Random rand = new Random();
 
		 int ran;  
		 String rpsList[]= {"����","����","��","����","����","��","����","����","��","����","����","��"};
		 String user_sel="";
		 String com_sel;
		 int turn=0;
		 int lose_cnt=0;
		 while(turn<3) {
			 turn++;
			 ran = rand.nextInt(12);  
			 System.out.println("���� ���� �� �� �ϳ��� ����ּ���.");
			 System.out.println("exit�� ������ ����");
			 user_sel=sc.next();
			 com_sel=rpsList[ran];
			 if(user_sel.equals("exit")) break;
			 System.out.println("���� �� �� : "+com_sel);
			 if(user_sel.equals("����")) {
				 if(user_sel.equals(com_sel)) {
					 System.out.println("�����ϴ�.");
					 
				 }
				 else if(com_sel.equals("��")){
					 System.out.println("�̰���ϴ�.");
				 }
				 else {
					 System.out.println("�����ϴ�.");
					 lose_cnt++;
					 if(lose_cnt==2) {
						 System.out.println("�׾���");
						 return false;
					 } 
				 }
			 }
			 else if(user_sel.equals("����")) {
				 if(user_sel.equals(com_sel)) {
					 System.out.println("�����ϴ�.");
				 }
				 else if(com_sel.equals("����")){
					 System.out.println("�̰���ϴ�.");
				 }
				 else {
					 System.out.println("�����ϴ�.");
					 lose_cnt++;
					 if(lose_cnt==2) {
						 System.out.println("�׾���");
						 return false;
					 } 
				 }
			 }
			 else if(user_sel.equals("��")) {
				 if(user_sel.equals(com_sel)) {
					 System.out.println("�����ϴ�.");
				 }
				 else if(com_sel.equals("����")){
					 System.out.println("�̰���ϴ�.");
				 }
				 else {
					 System.out.println("�����ϴ�.");
					 lose_cnt++;
					 if(lose_cnt==2) {
						 System.out.println("�׾���");
						 return false;
					 } 
				 }
			 }
			 System.out.println();
		 }
		 System.out.println();
		 System.out.println("���");
		 return true;
	}

}
