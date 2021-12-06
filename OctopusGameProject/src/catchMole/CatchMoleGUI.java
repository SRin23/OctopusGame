package catchMole;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.SwingConstants;

import mainMenu.GameEnding_fail;
import miniGame.connectAll;

public class CatchMoleGUI extends connectAll {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	JFrame jf;
	private boolean win;

	private int timeFast = 0;
	private int timeCount = 0;

	private String userNumber;
	private int moleCount = 8;

	private int userScore;

	private JLabel gameOver;
	private JLabel resultText;

	// image를 담을 변수
	private Image introBackground = new ImageIcon(CatchMoleGUI.class.getResource("../img/backgroundGreen.png"))
			.getImage();
	Font font;
	private ImageIcon moleShowDefault = new ImageIcon(CatchMoleGUI.class.getResource("../img/showMoleDefault.png"));
	private ImageIcon moleShowEntered = new ImageIcon(CatchMoleGUI.class.getResource("../img/showMoleEntered.png"));
	private ImageIcon moleHideDefault = new ImageIcon(CatchMoleGUI.class.getResource("../img/HideMoleDefault.png"));
	private ImageIcon moleHideEntered = new ImageIcon(CatchMoleGUI.class.getResource("../img/HideMoleEntered.png"));
	private ImageIcon emptyHoleDefault = new ImageIcon(CatchMoleGUI.class.getResource("../img/EmptyHoleDefault.png"));
	private ImageIcon emptyHoleEntered = new ImageIcon(CatchMoleGUI.class.getResource("../img/EmptyHoleEntered.png"));
	private ImageIcon moleAttackDefault = new ImageIcon(
			CatchMoleGUI.class.getResource("../img/attackMoleDefault.png"));
	private ImageIcon moleAttackEntered = new ImageIcon(
			CatchMoleGUI.class.getResource("../img/attackMoleEntered.png"));
	JButton[] moleBtn = new JButton[moleCount];
	private JLabel cnt;
	private JLabel score;
	private JLabel heartLabel;
	private JLabel timeText;

	// 0이면 들어가있는거, 1이면 튀어나온거 2면 숨어있는거
	int moleStatus[] = new int[moleCount];
	Container c1;

	public CatchMoleGUI(String userNumber) {
		super(userNumber);

		font = new Font("서울남산 장체B",Font.PLAIN,20);
    	
		panJf.dispose();
		jf = new JFrame();
		c1 = jf.getContentPane();
		this.userNumber = userNumber;
		// 기본 Frame setting
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("두더지 잡기");
		c1.setLayout(null);
		jf.setResizable(false);
		jf.setSize(1200, 900);

		// c1.setBackground(Color.black);

		score = new JLabel("점수 : " + userScore+"/300");
		score.setBounds(10, 0, 200, 50);
		score.setFont(font.deriveFont(20.0f));
		score.setVisible(true);
		c1.add(score);

		heartLabel = new JLabel("목숨 : " + heart + "개");
		heartLabel.setBounds(1050, 0, 100, 50);
		heartLabel.setFont(font.deriveFont(20.0f));
		heartLabel.setVisible(true);
		c1.add(heartLabel);

		// 3..2..1..보여주는 라벨
		cnt = new JLabel("3");
		cnt.setBounds(510, 250, 300, 300);
		cnt.setFont(font.deriveFont(300.0f));
		cnt.setForeground(Color.black);
		cnt.setVisible(true);
		c1.add(cnt);
		
		jf.setVisible(true);
	}

	static int countDown = 1;

	//게임 시작
	public int startGame() {
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				if (countDown < 3) {
					cnt.setText("" + (3 - countDown));
					countDown++;
				} else {
					t.cancel();
					cnt.setVisible(false);
					System.out.println("게임 스타트");
					game();
				}
			}
		};
		t.scheduleAtFixedRate(tt, 1000, 1000);

		return heart;

	}

	void game() {
		// false로 초기화(false는 구멍에 들어가있을때)
		for (int i = 0; i < moleCount; i++) {
			moleStatus[i] = 0;
		}
		timeText = new JLabel("남은 시간 : " + timeCount + "초");
		timeText.setBounds(350, 10, 500, 50);
		timeText.setFont(font.deriveFont(30.0f));
		timeText.setVisible(true);
		timeText.setHorizontalAlignment(SwingConstants.CENTER);
		timeText.setVerticalAlignment(SwingConstants.CENTER);
		c1.add(timeText);

		for (int i = 0; i < moleCount; i++) {
			final int di = i;
			moleBtn[i] = new JButton(emptyHoleDefault);
			if (i < 4) {
				moleBtn[i].setBounds(300 * i + 20, 50, 250, 380);
			} else {
				moleBtn[i].setBounds(300 * (i - 4) + 20, 450, 250, 380);
			}
			moleBtn[i].setBorderPainted(false);
			moleBtn[i].setContentAreaFilled(false);
			moleBtn[i].setFocusPainted(false);
			moleBtn[i].setVisible(true);
			moleBtn[i].addMouseListener(new MouseAdapter() {
				// 버튼 위에 마우스
				@Override
				public void mouseEntered(MouseEvent e) {
					// 커서모양 손가락
					moleBtn[di].setCursor(new Cursor(Cursor.HAND_CURSOR));
					if (moleStatus[di] == 1) {
						moleBtn[di].setIcon(moleShowEntered);
					} else if (moleStatus[di] == 2) {
						moleBtn[di].setIcon(moleHideEntered);
					} else {
						moleBtn[di].setIcon(emptyHoleEntered);
					}
				}

				// 기본
				@Override
				public void mouseExited(MouseEvent e) {
					moleBtn[di].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if (moleStatus[di] == 1) {
						moleBtn[di].setIcon(moleShowDefault);
					} else if (moleStatus[di] == 2) {
						moleBtn[di].setIcon(moleHideDefault);
					} else {
						moleBtn[di].setIcon(emptyHoleDefault);
					}
				}

				// 버튼을 마우스가 누를때
				@Override
				public void mousePressed(MouseEvent e) {
					moleBtn[di].setCursor(new Cursor(Cursor.HAND_CURSOR));
					if (moleStatus[di] == 1) {
						moleBtn[di].setIcon(moleAttackEntered);
						userScore += 10;
						System.out.println("득 점수 : " + userScore);
					} else {
						moleBtn[di].setIcon(emptyHoleDefault);
						userScore -= 10;
						System.out.println("실 점수 : " + userScore);
					}
					score.setText("점수 : " + userScore+"/300");
				}
			});
			moleBtn[i].setVisible(true);
			c1.add(moleBtn[i]);
		}

		int randtime = ((int) (Math.random() * 5000 - timeFast) + 2000 - timeFast) / 1000 * 1000;
		// System.out.println("반복 시간 : " + randtime);
		tm.scheduleAtFixedRate(tmTask, 1000, randtime);

		int randtime2 = ((int) (Math.random() * 8000 - timeFast) + 3000 - timeFast) / 1000 * 1000;
		// System.out.println("반복 시간 : " + randtime);
		tm2.scheduleAtFixedRate(tmTask2, 3000, randtime2);

		int randtime3 = ((int) (Math.random() * 10000) + 3000) / 1000 * 1000;
		// System.out.println("반복 시간 : " + randtime);
		System.out.println(randtime3);
		tm3.scheduleAtFixedRate(tmTask3, randtime3, 5000);

		int randtime4 = ((int) (Math.random() * 15000) + 8000) / 1000 * 1000;
		// System.out.println("반복 시간 : " + randtime);
		tm4.scheduleAtFixedRate(tmTask4, randtime4, 8000);

		leaveTm.scheduleAtFixedRate(leaveTmTask, 1000, 1000);
	}

	Timer tm = new Timer();
	TimerTask tmTask = new TimerTask() {
		@Override
		public void run() {
			timeFast += 50;
			int upIdx = rand.nextInt(moleCount);
			if (moleStatus[upIdx] == 0) {
				moleStatus[upIdx] = 1;
				moleBtn[upIdx].setIcon(moleShowDefault);
				int randSleep = ((int) (Math.random() * 3000) + 500) / 100 * 100;

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
			if (moleStatus[upIdx] == 0) {
				moleStatus[upIdx] = 1;
				moleBtn[upIdx].setIcon(moleShowDefault);
				int randSleep = ((int) (Math.random() * 3000) + 500) / 100 * 100;

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
			if (moleStatus[upIdx] != 1) {
				moleStatus[upIdx] = 2;
				moleBtn[upIdx].setIcon(moleHideDefault);
				int randSleep = ((int) (Math.random() * 3000) + 500) / 100 * 100;

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
			if (moleStatus[upIdx] != 1) {
				moleStatus[upIdx] = 2;
				moleBtn[upIdx].setIcon(moleHideDefault);
				int randSleep = ((int) (Math.random() * 3000) + 500) / 100 * 100;

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
			if (timeCount <= 20) {
				timeText.setText("남은 시간 : " + (20 - timeCount) + "초");
				timeCount++;
			} else {
				leaveTm.cancel();
				tm.cancel();
				tm2.cancel();
				tm3.cancel();
				tm4.cancel();

				for (int i = 0; i < moleCount; i++) {
					// moleBtn[i].setEnabled(false);
					moleBtn[i].setVisible(false);
				}

				gameOver = new JLabel();
				gameOver.setText("GAME OVER");
				gameOver.setBounds(350,300,800,100);
				gameOver.setForeground(Color.BLACK);
				gameOver.setVisible(true);
				gameOver.setFont(font.deriveFont(100.0f));
				c1.add(gameOver);


				resultText = new JLabel();
				resultText.setBounds(540,450,200,80);
				resultText.setForeground(Color.BLACK);
				resultText.setFont(font.deriveFont(30.0f));
				resultText.setHorizontalAlignment(SwingConstants.CENTER);
				resultText.setVerticalAlignment(SwingConstants.CENTER);
				resultText.setVisible(true);
				c1.add(resultText);

				if (userScore < 300) {
					resultText.setText("졌습니다.");
					heart--;
					if(heart<=0) {
						
						try {
							Thread.sleep(2000);
							jf.dispose();
							new GameEnding_fail();
							return;
						}catch(Exception e) {
							System.out.println(e);
						}
					} 
				} else {

					resultText.setText("이겼습니다.");
				}
				try {
					Thread.sleep(2000);
					jf.dispose();
					rspJf.setVisible(true);
					
					return;
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		}
	};

//	public void paint(Graphics g) {
//		g.drawImage(introBackground, 0, 0, null);
//	}

}
