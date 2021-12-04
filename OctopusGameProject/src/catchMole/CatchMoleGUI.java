package catchMole;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CatchMoleGUI extends JFrame{
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	private boolean win;
	
	private int timeFast = 0;
	private int timeCount = 0;
	
	private int heart;
	private int userNumber;
	private int moleCount = 8;
	
	private int userScore;
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private JLabel gameOver;
	private JLabel resultText;
	
	//image�� ���� ����
	//private Image introBackground= new ImageIcon(CatchMoleMain.class.getResource("../img/backgroundGreen.png")).getImage();
	
	private ImageIcon moleShowDefault = new ImageIcon(CatchMoleMain.class.getResource("../img/showMoleDefault.png"));
	private ImageIcon moleShowEntered = new ImageIcon(CatchMoleMain.class.getResource("../img/showMoleEntered.png"));
	private ImageIcon moleHideDefault = new ImageIcon(CatchMoleMain.class.getResource("../img/HideMoleDefault.png"));
	private ImageIcon moleHideEntered = new ImageIcon(CatchMoleMain.class.getResource("../img/HideMoleEntered.png"));
	private ImageIcon emptyHoleDefault = new ImageIcon(CatchMoleMain.class.getResource("../img/EmptyHoleDefault.png"));
	private ImageIcon emptyHoleEntered = new ImageIcon(CatchMoleMain.class.getResource("../img/EmptyHoleEntered.png"));
	private ImageIcon moleAttackDefault = new ImageIcon(CatchMoleMain.class.getResource("../img/attackMoleDefault.png"));
	private ImageIcon moleAttackEntered = new ImageIcon(CatchMoleMain.class.getResource("../img/attackMoleEntered.png"));
	JButton[] moleBtn = new JButton[moleCount];
	
	private JLabel score;
	private JLabel heartLabel;
	private JLabel timeText;

	//0�̸� ���ִ°�, 1�̸� Ƣ��°� 2�� �����ִ°�
	int moleStatus[] = new int[moleCount];
	Container c1 = getContentPane();
	
	CatchMoleGUI(int heart, int userNumber){
		this.heart = heart;
		this.userNumber = userNumber;
		
		//false�� �ʱ�ȭ(false�� ���ۿ� ��������)
		for(int i = 0; i<moleCount; i++) {
			moleStatus[i] = 0;
		}
		
		//�⺻ Frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�δ��� ���");
		c1.setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		
		score = new JLabel("���� : " + userScore);
		score.setBounds(10,0, 200, 50);
		score.setFont(score.getFont().deriveFont(20.0f));
		score.setVisible(true);
		c1.add(score);
		
		heartLabel = new JLabel("��� : " + heart + "��");
		heartLabel.setBounds(1050,0, 100, 50);
		heartLabel.setFont(heartLabel.getFont().deriveFont(20.0f));
		heartLabel.setVisible(true);
		c1.add(heartLabel);
		
		timeText = new JLabel("���� �ð� : " + timeCount + "��");
		timeText.setBounds(350,10, 500, 50);
		timeText.setFont(timeText.getFont().deriveFont(30.0f));
		timeText.setVisible(true);
		timeText.setHorizontalAlignment(SwingConstants.CENTER);
		timeText.setVerticalAlignment(SwingConstants.CENTER);
		c1.add(timeText);
		
		for(int i = 0; i<moleCount; i++) {
			final int di = i;
			moleBtn[i] = new JButton(emptyHoleDefault);
			if(i<4) {
				moleBtn[i].setBounds(300*i+20, 50,250, 380);
			}else {
				moleBtn[i].setBounds(300*(i-4)+20, 450, 250, 380);
			}
			moleBtn[i].setBorderPainted(false);
			moleBtn[i].setContentAreaFilled(false);
			moleBtn[i].setFocusPainted(false);
			moleBtn[i].setVisible(true);
			moleBtn[i].addMouseListener(new MouseAdapter(){
				//��ư ���� ���콺
				@Override
				public void mouseEntered(MouseEvent e) {
					//Ŀ����� �հ���
					moleBtn[di].setCursor(new Cursor(Cursor.HAND_CURSOR));
					if(moleStatus[di]==1) {
						moleBtn[di].setIcon(moleShowEntered);
					}
					else if(moleStatus[di] == 2) {
						moleBtn[di].setIcon(moleHideEntered);
					}
					else {
						moleBtn[di].setIcon(emptyHoleEntered);
					}	
				}
				//�⺻
				@Override
				public void mouseExited(MouseEvent e) {
					moleBtn[di].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if(moleStatus[di]==1) {
						moleBtn[di].setIcon(moleShowDefault);
					}
					else if(moleStatus[di]==2) {
						moleBtn[di].setIcon(moleHideDefault);
					}
					else {
						moleBtn[di].setIcon(emptyHoleDefault);
					}					
				}
				//��ư�� ���콺�� ������
				@Override
				public void mousePressed(MouseEvent e) {
					moleBtn[di].setCursor(new Cursor(Cursor.HAND_CURSOR));
					if(moleStatus[di]==1) {
						moleBtn[di].setIcon(moleAttackEntered);
						userScore+=10;
						System.out.println("�� ���� : " + userScore);
					}
					else {
						moleBtn[di].setIcon(emptyHoleDefault);
						userScore-=10;
						System.out.println("�� ���� : " + userScore);
					}
					score.setText("���� : " + userScore);
					
				}
			});
			moleBtn[i].setVisible(true);
			c1.add(moleBtn[i]);
		}
		
		int randtime = ((int)(Math.random()*5000-timeFast)+2000-timeFast)/1000*1000;
		//System.out.println("�ݺ� �ð�  : " + randtime);
		tm.scheduleAtFixedRate(tmTask, 1000, randtime);
		
		int randtime2 = ((int)(Math.random()*8000-timeFast)+3000-timeFast)/1000*1000;
		//System.out.println("�ݺ� �ð�  : " + randtime);
		tm2.scheduleAtFixedRate(tmTask2, 3000, randtime2);
		
		int randtime3 = ((int)(Math.random()*10000)+3000)/1000*1000;
		//System.out.println("�ݺ� �ð�  : " + randtime);
		System.out.println(randtime3);
		tm3.scheduleAtFixedRate(tmTask3, randtime3, 5000);
		
		int randtime4 = ((int)(Math.random()*15000)+8000)/1000*1000;
		//System.out.println("�ݺ� �ð�  : " + randtime);
		tm4.scheduleAtFixedRate(tmTask4, randtime4, 8000);
		
	
		leaveTm.scheduleAtFixedRate(leaveTmTask, 1000, 1000);
		
		setVisible(true);
	}
	
	Timer tm = new Timer();
	TimerTask tmTask = new TimerTask() {		
		@Override
		public void run() {
			timeFast+=50;
			int upIdx = rand.nextInt(moleCount);
			if(moleStatus[upIdx]==0) {
				moleStatus[upIdx] = 1;
				moleBtn[upIdx].setIcon(moleShowDefault);
				int randSleep = ((int)(Math.random()*3000)+500)/100*100;
				
				try {
					Thread.sleep(randSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moleBtn[upIdx].setIcon(emptyHoleDefault);
				moleStatus[upIdx] = 0;	
			}
		}
	};
	

	Timer tm2 = new Timer();
	TimerTask tmTask2 = new TimerTask() {		
		@Override
		public void run() {
			int upIdx = rand.nextInt(moleCount);
			if(moleStatus[upIdx]==0) {
				moleStatus[upIdx] = 1;
				moleBtn[upIdx].setIcon(moleShowDefault);
				int randSleep = ((int)(Math.random()*3000)+500)/100*100;
				
				try {
					Thread.sleep(randSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moleBtn[upIdx].setIcon(emptyHoleDefault);
				moleStatus[upIdx] = 0;
				
			}
		}
	};
	
	Timer tm3 = new Timer();
	TimerTask tmTask3 = new TimerTask() {		
		@Override
		public void run() {
			int upIdx = rand.nextInt(moleCount);
			if(moleStatus[upIdx]!=1) {
				moleStatus[upIdx] = 2;
				moleBtn[upIdx].setIcon(moleHideDefault);
				int randSleep = ((int)(Math.random()*3000)+500)/100*100;
				
				try {
					Thread.sleep(randSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moleBtn[upIdx].setIcon(emptyHoleDefault);
				moleStatus[upIdx] = 0;
			}
		}
	};
	

	Timer tm4 = new Timer();
	TimerTask tmTask4 = new TimerTask() {		
		@Override
		public void run() {
			int upIdx = rand.nextInt(moleCount);
			if(moleStatus[upIdx]!=1) {
				moleStatus[upIdx] = 2;
				moleBtn[upIdx].setIcon(moleHideDefault);
				int randSleep = ((int)(Math.random()*3000)+500)/100*100;
				
				try {
					Thread.sleep(randSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moleBtn[upIdx].setIcon(emptyHoleDefault);
				moleStatus[upIdx] = 0;
			}
		}
	};
	
	Timer leaveTm = new Timer();
	TimerTask leaveTmTask = new TimerTask() {

		@Override
		public void run() {
			if(timeCount<=20) {
				timeText.setText("���� �ð� : "+(20-timeCount) + "��");
				timeCount++;
			}
			else {
				leaveTm.cancel();
				tm.cancel();
				tm2.cancel();
				tm3.cancel();
				tm4.cancel();
				
				for(int i = 0; i<moleCount; i++) {
					//moleBtn[i].setEnabled(false);
					moleBtn[i].setVisible(false);
				}
				
				gameOver = new JLabel("GAME OVER");
				gameOver.setBounds(200,300, 800, 150);
				gameOver.setFont(gameOver.getFont().deriveFont(50.0f));
				gameOver.setVisible(true);
				gameOver.setHorizontalAlignment(SwingConstants.CENTER);
				gameOver.setVerticalAlignment(SwingConstants.CENTER);
				c1.add(gameOver);
				
				resultText = new JLabel();
				resultText.setBounds(450, 500, 300, 100);
				resultText.setFont(resultText.getFont().deriveFont(20.0f));
				resultText.setHorizontalAlignment(SwingConstants.CENTER);
				resultText.setVerticalAlignment(SwingConstants.CENTER);
				resultText.setVisible(true);
				c1.add(resultText);
				
				if(userScore<150) {
					win = false;
					resultText.setText("�����ϴ�.");
				}else {
					win = true;
					resultText.setText("�̰���ϴ�.");
				}
			}
		}
	};
//	
//	public void paint(Graphics g) {
//		//�̹����� ���� ��, screenImage�� ����
//		screenImage = createImage(CatchMoleMain.SCREEN_WIDTH, CatchMoleMain.SCREEN_HEIGHT);
//		//screenImage�� ���� �׷����� ������ screenGraphic�� ����
//		screenGraphic = screenImage.getGraphics();
//		
//		//�Լ� ȣ��
//		screenDraw(screenGraphic);
//		
//		//screenImage�� 0, 0��ġ�� �׷���
//		g.drawImage(screenImage,  0,  0,  null);
//	}
//	
//	public void screenDraw(Graphics g) {
//		g.drawImage(introBackground,  0,  0,  null);
//		this.repaint();	//�ٽ� paint�Լ��� �ҷ���(����ؼ� ȭ���� �׸�)
//	}
}
