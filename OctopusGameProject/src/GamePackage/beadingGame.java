package GamePackage;

import java.util.Scanner;
public class beadingGame {
	final int odd = 1;
	final int even = 0;
	
	//false�� ��ǻ�ͺ���, true�� ����ں��� ������ ��� ����
	int gameStart(boolean start, int heart) {
		Scanner sc = new Scanner(System.in);
		
		//��� ������ ���� ����
		System.out.print("��� ������ ����ϽÁٽ��ϱ�?");
		int marble = sc.nextInt();
		
		
		//��ǻ�Ͱ� ������ ���.
		if(start==false) {
			//��ǻ�Ͱ� �������� ������ ���� ����(0~marble)
			int rand = (int)(Math.random()*marble);
			
			//����ڰ� Ȧ¦ ����(����, Ȧ¦ ���ù� �����)
			System.out.println("��ǻ�Ͱ� ������ ������ϴ�.");
			System.out.print("Ȧ¦�� ������ �ֽʽÿ�.(Ȧ : 1, ¦ : 0)");
			System.out.print("(Ȧ : 1, ¦ : 0)");
			int user_oddeven = sc.nextInt();
			
			String user_str;
			if(user_oddeven==0) {
				user_str = "¦";
			}else if(user_oddeven==1) {
				user_str = "Ȧ";
			}else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ֽʽÿ�");
				return heart;
			}
			
			if(rand%2==user_oddeven) {
				System.out.println( user_str + "�� ������ϴ�.");
				System.out.println("��ǻ�� : " + rand + "  ����� : " + user_str);
			}else {
				System.out.println(rand + "���� Ʋ�Ƚ��ϴ�.");
				return heart--;
			}
			
		}else {
			System.out.println("0~" + marble + "�� ���� �ϳ��� ������ �ּ���.");
			int user_num = sc.nextInt();
			
			int rand = (int)(Math.random()*2)+1;
			
			String comp_str;
			if(rand==0) {
				comp_str = "¦";
			}else {
				comp_str = "Ȧ";
			}
			
			
			if(user_num%2==rand) {
				System.out.println( comp_str + "�� ������ϴ�.");
				System.out.println("��ǻ�� : " + comp_str + "  ����� : " + user_num);
			}else {
				System.out.println(rand + "���� ����� �̰���ϴ�.");
				return heart;
			}
		}
		return heart;
	}
	
}
