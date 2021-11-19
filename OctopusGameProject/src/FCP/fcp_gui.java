package FCP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;

public class fcp_gui {
	public static final int BTN_CNT = 32; //�� ���� ��ư ��
	public static final int PAN_WIDTH = 100; //���� ����
	public static final int PAN_HEIGHT = 100; //���� ����
	static int count = 1;
	
	String nowColor[]=new String[BTN_CNT]; //���� ���� ����
	JButton pan[]=new JButton[BTN_CNT]; //��
	JFrame jf; 
	
	public static int redCnt = 0; //player�� �� ��(red)
	public static int blueCnt = 0; //master�� �� ��(blue)
	
	//������ ����
	fcp_gui(){
		jf = new JFrame("�� ������");
		//â�� ���� �� ���α׷� ����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jf.setSize(1200,900);
		//������(���� x �ִ� ��) ���̰� ����
		jf.setVisible(true);
		
		int i,a;        
		int pan_y = 100;
		setNowColor();
		for(i=0, a=0; i<BTN_CNT; i++,a++) {
			pan[i] = new JButton();
			
			//�����ϱ� ������  ��Ȱ��ȭ
			pan[i].setEnabled(false);
			//��ư �ٹٲ��� ���� �ڵ�
			if(i == 8 || i == 16 || i == 24 ||i == 32) {
				pan_y += 170;
				a = 0;
			}
			pan[i].setBounds(50+(a*140), pan_y, PAN_WIDTH, PAN_HEIGHT);
			if(nowColor[i] =="red") pan[i].setBackground(Color.RED);
			else pan[i].setBackground(Color.BLUE);
			
			final int tmp = i; //�̺�Ʈ ������ �ȿ��� ���� ���� ���
			//��ư�鿡 Ŭ�� �̺�Ʈ �ο�
			pan[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//��ư �� �ٲ�
					setColorBtn(tmp);
				}
			});
			
			jf.getContentPane().add(pan[i]);
		}
	}
	
	//�ʱ� �� ������ ���� �Լ�
	private void setNowColor() {
		Random random = new Random();
		int arr[] = new int[32];
		int zeroCnt = 0;
		int i;
		for( i=0; i<arr.length; i++) {
			int ran = random.nextInt(2);
			if(ran == 0) zeroCnt++;
			if(zeroCnt == 16) break;
				arr[i]= ran;
		}
		for(int j=i; j<arr.length; j++) {
			arr[j]=1;
		}   
		
		for(i=0; i<BTN_CNT; i++) {
			if(arr[i] == 0) nowColor[i]="red";
			else nowColor[i]="blue";
		}	
	}
	
	//nowColor�� ���� ��ư �� �ٲ�
	private void setColorBtn(int tmp) {
		if(nowColor[tmp] == "red") {
			nowColor[tmp]="blue";
			pan[tmp].setBackground(Color.BLUE);
		}
		else {
			nowColor[tmp]="red";
			pan[tmp].setBackground(Color.RED);
		}
	}
	
	//������ �������� �� ���� ������ ��
	private boolean countPan() {
		for(int i=0; i<BTN_CNT; i++) {
			if(nowColor[i] =="red") redCnt++;
			else blueCnt++;
		}
		if(redCnt>blueCnt) {
			System.out.println("�̰���");
			return true;
		} 
		else if(blueCnt>=redCnt ) {
			System.out.println("����"); 
			return false;
		}
		return true;
	}

	//�� ������ ���� ����
	public int startGame(int heart) {

		for(int j=0; j<BTN_CNT; j++) {
			pan[j].setEnabled(true);
		}
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if(count<=20) {
					System.out.println("���� �ð� : "+(20-count));
					count++;
				}
				else {
					timer.cancel();
					if(!countPan()) {
						System.out.println("red : "+redCnt+", blue : "+blueCnt);
					}
					else {
						System.out.println("red : "+redCnt+", blue : "+blueCnt);
					}
					System.out.println("���� ��");
					for(int i=0; i<BTN_CNT; i++) {
						pan[i].setEnabled(false);
					}
				}
			}
		};
		timer.schedule(task, 1000, 1000);
		
		return heart; 
	}
}
	

