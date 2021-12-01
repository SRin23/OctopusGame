package catchMole;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CatchMoleGUI extends JFrame{
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	private int heart;
	private int userNumber;
	private int moleCount = 8;
	
	private int userScore;
	
	private ImageIcon moleShowDefault = new ImageIcon(CatchMoleMain.class.getResource("../img/attackMoleDefault.png"));
	private ImageIcon moleHideDefault = new ImageIcon(CatchMoleMain.class.getResource("../img/HideMoleDefault.png"));
	private ImageIcon emptyHoleDefault = new ImageIcon(CatchMoleMain.class.getResource("../img/EmptyHoleDefault.png"));
	private ImageIcon moleAttackDefault = new ImageIcon(CatchMoleMain.class.getResource("../img/attackMoleDefault.png"));
	private ImageIcon moleShowEntered = new ImageIcon(CatchMoleMain.class.getResource("../img/attackMoleEntered.png"));
	private ImageIcon moleHideEntered = new ImageIcon(CatchMoleMain.class.getResource("../img/HideMoleEntered.png"));
	private ImageIcon emptyHoleEntered = new ImageIcon(CatchMoleMain.class.getResource("../img/EmptyHoleEntered.png"));
	private ImageIcon moleAttackEntered = new ImageIcon(CatchMoleMain.class.getResource("../img/attackMoleEntered.png"));
	JButton[] MoleStatus= new JButton[moleCount];

	
	boolean moleStatus[] = new boolean[moleCount];
	Container c1 = getContentPane();
	
	CatchMoleGUI(int heart, int userNumber){
		this.heart = heart;
		this.userNumber = userNumber;
		
		//false로 초기화(false는 구멍에 들어가있을때)
		for(int i = 0; i<moleCount; i++) {
			moleStatus[i] = false;
		}
		
		//기본 Frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("두더지 잡기");
		c1.setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		c1.setBackground(Color.white);
		
		for(int i = 0; i<moleCount; i++) {
			final int di = i;
			MoleStatus[i] = new JButton(emptyHoleDefault);
			if(i<4) {
				MoleStatus[i].setBounds(300*i, 0, 300, 450);
			}else {
				MoleStatus[i].setBounds(300*(i-4), 400, 300, 450);
			}
			MoleStatus[i].setBorderPainted(false);
			MoleStatus[i].setContentAreaFilled(false);
			MoleStatus[i].setFocusPainted(false);
			MoleStatus[i].setVisible(true);
			MoleStatus[i].addMouseListener(new MouseAdapter(){
				//버튼 위에 마우스
				@Override
				public void mouseEntered(MouseEvent e) {
					//커서모양 손가락
					MoleStatus[di].setCursor(new Cursor(Cursor.HAND_CURSOR));
					if(moleStatus[di]==true) {
						MoleStatus[di].setIcon(moleShowDefault);
					}else {
						MoleStatus[di].setIcon(moleHideDefault);
					}	
				}
				//기본
				@Override
				public void mouseExited(MouseEvent e) {
					MoleStatus[di].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if(moleStatus[di]==true) {
						MoleStatus[di].setIcon(moleShowDefault);
					}else {
						MoleStatus[di].setIcon(emptyHoleDefault);
					}					
				}
				//버튼을 마우스가 누를때
				@Override
				public void mousePressed(MouseEvent e) {
					MoleStatus[di].setCursor(new Cursor(Cursor.HAND_CURSOR));
					if(moleStatus[di]==true) {
						MoleStatus[di].setIcon(moleAttackDefault);
						userScore+=10;
						System.out.println("득 점수 : " + userScore);
					}else {
						MoleStatus[di].setIcon(emptyHoleDefault);
						userScore-=10;
						System.out.println("실 점수 : " + userScore);
					}
					
				}
			});
			MoleStatus[i].setVisible(true);
			c1.add(MoleStatus[i]);
		}
		
		int randtime = ((int)(Math.random()*5000)+1000)/1000*1000;
		System.out.println("반복 시간  : " + randtime);
		tm.scheduleAtFixedRate(tmTask, 3000, randtime);
		
		try {
			Thread.sleep(randtime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		setVisible(true);
		
	}
	
	Timer tm = new Timer();
	
	TimerTask tmTask = new TimerTask() {		
		@Override
		public void run() {
			int upIdx = rand.nextInt(moleCount);
			if(moleStatus[upIdx]==false) {
				moleStatus[upIdx] = true;
				MoleStatus[upIdx].setIcon(moleShowDefault);
				int randSleep = ((int)(Math.random()*3000)+500)/100*100;
				
				try {
					Thread.sleep(randSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				moleStatus[upIdx] = false;
				MoleStatus[upIdx].setIcon(emptyHoleDefault);
			}
		}
	};
}
