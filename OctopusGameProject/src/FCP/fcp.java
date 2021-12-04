package FCP;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class fcp extends JFrame{
		final int BTN_CNT = 32; //�� ���� ��ư ��
		final int BTN_WIDTH = 100; //���� ����
		final int BTN_HEIGHT = 140; //���� ����
		static int count = 1; //Ÿ�̸Ӹ� ���� ��������
		
		String nowColor[]=new String[BTN_CNT]; //���� ���� ����
		JButton pan[]=new JButton[BTN_CNT]; //��
		Container c1 = getContentPane();
		JLabel timeJl; //���� �ð��� �˷���
		JLabel panCnt; //���� ���� �˷���
		JLabel playerNo;
		JLabel masterNo;
		int playerCnt = 0; //player�� �� ��(white)
		int masterCnt = 0; //master�� �� ��(black)
		
		String playerColor = "white"; //������� �� ��
		String masterColor = "black"; //��ǻ���� �� ��
		
		static boolean win; //���� �̰���� ����
		ImageIcon whitePan = new ImageIcon(fcp.class.getResource("../img/whitePan.png"));
		ImageIcon blackPan = new ImageIcon(fcp.class.getResource("../img/blackPan.png"));
		ImageIcon background = new ImageIcon(fcp.class.getResource("../img/background.png"));


		String playerNum;
		//������ ����
		public fcp(String pNum){
			playerNum=pNum;
			Color soil = new Color(217, 171, 130);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Ȧ¦����");
			setLayout(null);
			setResizable(false);	
			setSize(1200, 900);
			c1.setBackground(soil);
			setVisible(true);
			
			//player ��ȣ �� ���� �˷��ִ� ��
			playerNo = new JLabel(playerNum+" : "+playerColor);
			playerNo.setBounds(0,20,150,30);
			playerNo.setFont(playerNo.getFont().deriveFont(25.0f));
			playerNo.setVisible(true);
			c1.add(playerNo);

			//master ��ȣ �� ���� �˷��ִ� ��
			masterNo = new JLabel("457 : "+masterColor);
			masterNo.setBounds(1050,20,150,30);
			masterNo.setFont(masterNo.getFont().deriveFont(25.0f));
			masterNo.setVisible(true);
			c1.add(masterNo);

			//���� �ð� �����ִ� ��
			timeJl = new JLabel();
			timeJl.setBounds(470,30,400,60);
			timeJl.setFont(timeJl.getFont().deriveFont(40.0f));
			timeJl.setVisible(true);
			c1.add(timeJl);
			
			//������ ���� �� ���� ������ �����ִ� ��
			panCnt = new JLabel();
			panCnt.setBounds(410,400,800,100);
			panCnt.setFont(panCnt.getFont().deriveFont(50.0f));
			panCnt.setVisible(false);
			c1.add(panCnt);
			
			int i,a;        
			int pan_y = 160;
			setNowColor();
			for(i=0, a=0; i<BTN_CNT; i++,a++) {
				pan[i] = new JButton();
				
				//�����ϱ� ������  ��Ȱ��ȭ
				pan[i].setVisible(false);
				
				//��ư �ٹٲ��� ���� �ڵ�
				if(i == 8 || i == 16 || i == 24 ||i == 32) {
					pan_y += 160;
					a = 0;
				}
				pan[i].setBounds(50+(a*140), pan_y, BTN_WIDTH, BTN_HEIGHT);
				
				final int tmp = i; //�̺�Ʈ ������ �ȿ��� ���� ���� ���
				//��ư�� Ŭ�� �̺�Ʈ �ο�
				pan[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//��ư �� �ٲ�
						setColorBtn(tmp);
					}
				});
				
				c1.add(pan[i]);
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
			//������ �Ķ������� ä���
			for(int j=i; j<arr.length; j++) {
				arr[j]=1;
			}   
			
			for(i=0; i<BTN_CNT; i++) {
				if(arr[i] == 0) nowColor[i] = playerColor;
				else nowColor[i] = masterColor;
			}	
		}
		
		//nowColor�� ���� ��ư �� �ٲ�
		private void setColorBtn(int tmp) {
			if(nowColor[tmp].equals(playerColor)) {
				nowColor[tmp] = masterColor;
				pan[tmp].setIcon(blackPan);
			}
			else {
				nowColor[tmp] = playerColor;
				pan[tmp].setIcon(whitePan);
			}
		}
		
		//������ �������� �� ���� ������ ��
		private boolean countPan() {
			for(int i=0; i<BTN_CNT; i++) {
				if(nowColor[i].equals(playerColor)) playerCnt++;
				else masterCnt++;
			}
			if(playerCnt>masterCnt) {
				for(int i=0; i<BTN_CNT; i++)
					pan[i].setIcon(whitePan);
				return true;
			} 
			else if(masterCnt>=playerCnt ) {
				for(int i=0; i<BTN_CNT; i++)
					pan[i].setIcon(blackPan);
				return false;
			}
			return true;
		}
		
		//�ڵ����� �� ������
		private void com() {
			Random random = new Random();
			int t = random.nextInt(2800)+1100; //���� ������ �ð�
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					if(count <= 20) {
						int ran = random.nextInt(32);
						if(nowColor[ran].equals(playerColor)) {
							nowColor[ran] = masterColor;
							pan[ran].setIcon(blackPan);
						}
					}
					else timer.cancel();
				}
			};
			timer.schedule(task, 0 , t);
		}
		
		//�� ������ ���� ����
		public boolean startGame() {
			
			for(int i=0; i<BTN_CNT; i++) {
				pan[i].setVisible(true);
				if(nowColor[i].equals(playerColor)) pan[i].setIcon(whitePan);
				else pan[i].setIcon(blackPan);
			}
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					com();
					if(count<=2) {
						timeJl.setText("���� �ð� : "+(20-count));
						
						count++;
					}
					else {
						timeJl.setVisible(false);
						timer.cancel();
						win = countPan();
						System.out.println("���� ��");
						
						panCnt.setText(playerNum+" : "+playerCnt+", 457 : "+masterCnt);
						panCnt.setVisible(true);
						
						for(int i=0; i<BTN_CNT; i++) {
							pan[i].setVisible(false);
						}
					}
				}
			};
			timer.scheduleAtFixedRate(task, 1000, 1000);
			
			return win;
		}
	}
		


	

