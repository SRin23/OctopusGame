package marbleOddEven;

import java.util.Random;
import java.util.Scanner;

public class MarbleGameLogic {
   //�⺻ setting
   Scanner sc = new Scanner(System.in);
   Random rand = new Random();
   
   //Ȧ¦
   final int odd = 1;
   final int even = 0;
   
   //��������
   boolean start;
   int heart;
   int userNumber;
   int computerScore;   //��ǻ���� ������ ����
   int userScore;   //������� ������ ����
   
   //�ʱⰪ ����
   MarbleGameLogic(boolean start, int heart, int userNumber){
      this.start = start;
      this.heart = heart;
      this.userNumber = userNumber;
   }
   
   //start�� false�� ��ǻ�ͺ���, true�� ����ں��� ������ ���.
   public int gameStart() {
      marbleSetting();
      gamePlay();
      return heart;
   }

   //����� ���� ���� ����
   void marbleSetting() {
      int marble;   //����� ������ ����
      //��� ������ ���� ����(�ִ� ���� ��� ���� 50��)
      while(true) {
         System.out.print("��� ������ ����Ͻðڽ��ϱ�?(�ִ� ���� ���� : 50��) -> ");
         marble = sc.nextInt();
         if(marble>50) {
            System.out.println("�ִ� ���� ��� ������ �Ѿ����ϴ�. �ٽ� �Է��� �ֽʽÿ�.");
            System.out.println();
            continue;
         }
         break;
      }
      
      //�����&��ǻ�� ���� reset
      computerScore = marble;
      userScore = marble;
   }
   
   //���� play
      void gamePlay() {
         while(userScore>0&&computerScore>0) {
            System.out.println();
            if(start==true) attack();
            else if(start==false) defence();
         }
         if(userScore<=0) {
            System.out.println("\n");
            System.out.println("----------���----------");
            System.out.println("����� ������ �������� �ʽ��ϴ�.");
            System.out.println("����� �ϳ� ������ϴ�.");
            heart--;
         }
         else if(computerScore<=0) {
            System.out.println();
            System.out.println("----------���----------");
            System.out.println("457���� ������ �������� �ʽ��ϴ�.");
            System.out.println("����� �̰���ϴ�.");
            System.out.println("������ ���� �������� �Ѿ�ϴ�.");
         }
      }
   
   //����� ����(���� ���)
   void attack() {
      System.out.println("----------����----------");
      int userSelect;
      
      int marbleCheck = 0;
      
      //�տ� �� ���� ���� �Է�
      while(true) {
         System.out.print("0~" + userScore + "�� �տ� �� ������ ������ �Է��Ͻʽÿ� -> ");
         userSelect = sc.nextInt();
         marbleCheck++;
         if(marbleCheck>=3) {
            System.out.println("������ 3�� �߸� �Է��ϼ̽��ϴ�.");
            System.out.println("���� ������ ���ư��ϴ�.");
            start = false;
            break;
         }
         if(userSelect > userScore) {
            System.out.println(userSelect + "��ŭ�� ������ �������� �ʽ��ϴ�.");
            System.out.println("�ٽ� ������ �ֽʽÿ�");
            continue;
         }
         break;
      }
      
      //��ǻ�Ͱ� Ȧ/¦ �� ����
      int computerOddEven = rand.nextInt(2);
      
      //bool Ÿ���� string���� ��ȯ
      String rand_str;
      if(computerOddEven==0) {
         rand_str = "¦";
      }else {
         rand_str = "Ȧ";
      }
      
      
      if((userSelect%2==0&&rand_str.equals("¦"))||(userSelect%2==1&&rand_str.equals("Ȧ"))) {
         System.out.println("����� ���� ���� : " + userSelect);
         System.out.println("457���� " + rand_str + "�� ������ϴ�.");
         System.out.println("����� ������ " + userSelect + "��ŭ �����˴ϴ�.");
         userScore-=userSelect;
         computerScore+=userSelect;
      }
      else {
         System.out.println("����� ���� ���� : " + userSelect);
         System.out.println("457���� " + rand_str + "��, Ʋ�Ƚ��ϴ�.");
         System.out.println("����� ������ " + userSelect + "��ŭ �����˴ϴ�.");
         userScore+=userSelect;
         computerScore-=userSelect;
      }
      
      if(userScore<0) {
         userScore = 0;
      }else if(computerScore<0) {
         computerScore = 0;
      }
      
      System.out.println("\n| ���� ������ ����  | " + userNumber + "��(��) : " + userScore + "�� | 457�� : " + computerScore + "�� | ");
      start=false;
   }
   
   //����� ����(Ȧ¦ ���߱�)
   void defence() {
      //System.out.println("----------�İ�----------");
      System.out.println("----------����----------");
      
      //��ǻ�Ͱ� �������� ������ ������ ���ϴ� �ڵ�
      int computerSelect = rand.nextInt(computerScore)+1;
      
      //����ڰ�
      System.out.println("457���� ������ ������ϴ�.");
      System.out.print("Ȧ¦�� ������ �ֽʽÿ� -> ");
      
      //����ڰ� Ȧ¦�� �Է�
      String user_oddeven = sc.next();
      
      if((computerSelect%2==0&&user_oddeven.equals("¦"))||(computerSelect%2==1&&user_oddeven.equals("Ȧ"))) {
         System.out.println("457���� ���� ���� : " + computerSelect);
         System.out.println(user_oddeven + "�� ������ϴ�.");
         System.out.println("����� ������ " + computerSelect + "��ŭ �����˴ϴ�.");
         userScore+=computerSelect;
         computerScore-=computerSelect;
      }else {
         System.out.println("457���� ���� ���� : " + computerSelect);
         System.out.println("����� ������ " + computerSelect + "��ŭ �����˴ϴ�.");
         userScore-=computerSelect;
         computerScore+=computerSelect;
      }
      
      if(userScore<0) {
         userScore = 0;
      }else if(computerScore<0) {
         computerScore = 0;
      }
      System.out.println("\n| ���� ������ ����  | " + userNumber + "��(��) : " + userScore + "�� | 457�� : " + computerScore + "�� | ");
      start=true;
   }

}