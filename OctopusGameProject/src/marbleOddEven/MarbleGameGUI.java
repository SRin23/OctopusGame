package marbleOddEven;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import mainMenu.GameEnding_fail;
import mainMenu.GameEnding_success;
import miniGame.connectAll;

public class MarbleGameGUI extends connectAll {
	final int BTN_WIDTH = 400;
	final int BTN_HEIGHT = 400;
	final int BTN_CNT = 3; // 가위바위보 버튼의 수

	private int marbleCount;
	private boolean start;
	JFrame jf;
	private JLabel showResult;
	private JLabel timerCnt;
	private JLabel gameOver;
	private JLabel computerScoreText;
	private JLabel userScoreText;
	int computerScore;
	int userScore;
	private JLabel heartLabel;

	private String playerNo;
	int count = 0; // 3초 카운트

	boolean timering = false; // timer가 실행 중인지 체크
	boolean win;

	int computerMarblePick;
	private String computerOddEvenPick;
	int userMarblePick;
	private String userOddEvenPick;
	
	private JLabel HowmanyMarble;
	private JLabel MaxMarble;
	private JTextField marbleAmountUser;
	private JLabel resultText;
	// Image back = new
	// ImageIcon(mableGameFinal.class.getResource("../img/rspBack.png")).getImage();
	ImageIcon oddButtonDefault = new ImageIcon(MarbleGameGUI.class.getResource("../img/oddButton.png"));
	ImageIcon oddButtonEntered = new ImageIcon(MarbleGameGUI.class.getResource("../img/oddButtonEntered.png"));
	ImageIcon evenButtonDefault = new ImageIcon(MarbleGameGUI.class.getResource("../img/evenButton.png"));
	ImageIcon evenButtonEntered = new ImageIcon(MarbleGameGUI.class.getResource("../img/evenButtonEntered.png"));
	private JButton oddButton;
	private JButton evenButton;

	/*
	 * public void paint(Graphics g) { g.drawImage(back, 0, 0, null); }
	 */

	public MarbleGameGUI(String playerNo) {
		super(playerNo);
		this.playerNo = playerNo;
		panJf.dispose();

		Font font = new Font("서울남산 장체B", Font.PLAIN, 20);

		jf = new JFrame();
		Container c1 = jf.getContentPane();

		jf.setTitle("홀짝맞추기");
		// 창을 닫을 시 프로그램 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 프레임 창 고정
		jf.setResizable(false);
		jf.setSize(1200, 900);
		// 프레임(위에 x 있는 거) 보이게 설정
		jf.setVisible(true);

		c1.setBackground(Color.WHITE);
		heartLabel = new JLabel("목숨 : " + heart + "개");
		heartLabel.setLocation(1080, 10);
		heartLabel.setSize(120, 20);
		heartLabel.setFont(font.deriveFont(20.0f));
		heartLabel.setVisible(true);
		
		// 컴 선택 버튼 세팅
		showResult = new JLabel();
		showResult.setBounds(400, 100, BTN_WIDTH, BTN_HEIGHT);
		showResult.setFont(font.deriveFont(150.0f));
		showResult.setHorizontalAlignment(JLabel.CENTER);
		showResult.setVerticalAlignment(JLabel.CENTER);
		showResult.setVisible(false);
		
		timerCnt = new JLabel("3");
		timerCnt.setBounds(400, 0, 400, 100);
		timerCnt.setVisible(false);
		timerCnt.setHorizontalAlignment(JLabel.CENTER);
		timerCnt.setVerticalAlignment(JLabel.CENTER);
		timerCnt.setForeground(Color.RED);
		timerCnt.setFont(font.deriveFont(50.0f));
		

		oddButton = new JButton();
		oddButton.setBounds(150, 450, BTN_WIDTH, BTN_HEIGHT);
		oddButton.setBorderPainted(false);
		oddButton.setContentAreaFilled(false);
		oddButton.setFocusPainted(false);
		oddButton.setVisible(false);
		oddButton.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				userOddEvenPick = "홀";
				System.out.println(userOddEvenPick);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				oddButton.setIcon(oddButtonDefault);
				oddButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				oddButton.setIcon(oddButtonEntered);
				oddButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				count = 0;
				Attack();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});

		evenButton = new JButton();
		evenButton.setBounds(650, 450, BTN_WIDTH, BTN_HEIGHT);
		evenButton.setBorderPainted(false);
		evenButton.setContentAreaFilled(false);
		evenButton.setFocusPainted(false);
		evenButton.setVisible(false);
		evenButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				userOddEvenPick = "짝";
				System.out.println(userOddEvenPick);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				evenButton.setIcon(evenButtonDefault);
				evenButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				evenButton.setIcon(evenButtonEntered);
				evenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				count = 0;
				Attack();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});


		HowmanyMarble = new JLabel("몇개의 구슬을 사용하시겠습니까?");
		HowmanyMarble.setLocation(350, 200);
		HowmanyMarble.setFont(HowmanyMarble.getFont().deriveFont(30.0f));
		HowmanyMarble.setSize(500, 50);
		HowmanyMarble.setHorizontalAlignment(JLabel.CENTER);
		HowmanyMarble.setBackground(Color.black);
		HowmanyMarble.setVisible(false);
		HowmanyMarble.setFont(font);

		MaxMarble = new JLabel("(최대 구슬 개수 : 50개)");
		MaxMarble.setLocation(500, 250);
		MaxMarble.setFont(MaxMarble.getFont().deriveFont(18.0f));
		MaxMarble.setSize(400, 50);
		MaxMarble.setVisible(false);
		MaxMarble.setFont(font);

		marbleAmountUser = new JTextField(5);
		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.black), "입력");
		marbleAmountUser.setLocation(500, 500);
		marbleAmountUser.setSize(200, 100);
		marbleAmountUser.setFont(marbleAmountUser.getFont().deriveFont(60.0f));
		marbleAmountUser.setHorizontalAlignment(JLabel.CENTER);
		marbleAmountUser.setBackground(Color.white);
		marbleAmountUser.setBorder(oneTb);
		marbleAmountUser.setVisible(false);
		marbleAmountUser.setFont(font);

		
		gameOver = new JLabel();
		gameOver.setText("GAME OVER");
		gameOver.setBounds(200, 300, 800, 100);
		gameOver.setForeground(Color.BLACK);
		gameOver.setVisible(false);
		gameOver.setHorizontalAlignment(JLabel.CENTER);
		gameOver.setVerticalAlignment(JLabel.CENTER);
		gameOver.setFont(font.deriveFont(100.0f));
		
		resultText = new JLabel();
		resultText.setBounds(450, 500, 300, 100);
		resultText.setFont(resultText.getFont().deriveFont(20.0f));
		resultText.setHorizontalAlignment(SwingConstants.CENTER);
		resultText.setVerticalAlignment(SwingConstants.CENTER);
		resultText.setVisible(true);
		c1.add(resultText);

		computerScoreText = new JLabel("457번 : 0점");
		computerScoreText.setBounds(0, 0, 200, 30);
		computerScoreText.setForeground(Color.BLACK);
		computerScoreText.setVisible(true);
		computerScoreText.setFont(font.deriveFont(20.0f));
		
		
		userScoreText = new JLabel(playerNo + "번 : 0점");
		userScoreText.setBounds(0, 30, 200, 30);
		userScoreText.setForeground(Color.BLACK);
		userScoreText.setVisible(true);
		userScoreText.setFont(font.deriveFont(20.0f));

		JLabel jl = new JLabel();
		
		c1.add(heartLabel);
		c1.add(showResult);
		c1.add(timerCnt);
		c1.add(oddButton);
		c1.add(evenButton);
		c1.add(gameOver);
		c1.add(computerScoreText);
		c1.add(userScoreText);
		c1.add(HowmanyMarble);
		c1.add(MaxMarble);
		c1.add(marbleAmountUser);
		c1.add(jl);

	}

	
	public int startGame(boolean start) {
		this.start = true;
		basic();
		return heart;
	}

	
	void basic() {
		HowmanyMarble.setVisible(true);
		MaxMarble.setVisible(true);
		marbleAmountUser.setVisible(true);
		marbleAmountUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField jf = (JTextField) e.getSource();
				marbleCount = Integer.parseInt(jf.getText());
				System.out.println("전체 구슬 개수 : " + marbleCount);
				if (marbleCount > 50 || marbleCount < 10)
					HowmanyMarble.setText("다시입력해주세요.");
				else {
					HowmanyMarble.setText("입력이 완료되었습니다.");
					computerScore = marbleCount;
					userScore = marbleCount;
					userScoreText.setText(playerNo + "번 : " + userScore + "개(나)");
					computerScoreText.setText("457번 : " + computerScore + "개");
					HowmanyMarble.setVisible(false);
					MaxMarble.setVisible(false);
					marbleAmountUser.setVisible(false);

					if (start == false) { // 수비(홀짝)
						//defence();
					} else { // 공격(구슬쥐기)
						oddButton.setVisible(true);
						evenButton.setVisible(true);
						timerCnt.setVisible(true);
					}

				}
			}
		});

	}
	
	// 컴이 홀, 짝 중 랜덤으로 하나 고름
	private void computerOddEvenSelect() {
		int tmp = (int)(Math.random()*2);
		if(tmp==0) {
			computerOddEvenPick = "짝";
		}else {
			computerOddEvenPick = "홀";
		}
		
	}
	
	// 컴이 자신이 가진 구슬의 개수중 랜덤으로 고름
	private void computerMarbleSelect() {
		computerMarblePick = (int)(Math.random()*(computerScore+1));
		System.out.println(computerMarblePick);
	}

	private void checkAttack() {
		if ((userOddEvenPick.equals("짝") && computerMarblePick%2==0)||(userOddEvenPick.equals("홀") && computerMarblePick%2==1)) {
			userScore+=computerMarblePick;
			computerScore -= computerMarblePick;

			if(userScore>=marbleCount*2) {
				userScore = marbleCount*2;
				computerScore = 0;
			}else if(computerScore>=marbleCount*2) {
				userScore = 0;
				computerScore = marbleCount*2;
			}
		}else {
			userScore-=computerMarblePick;
			computerScore += computerMarblePick;

			if(userScore>=marbleCount*2) {
				userScore = marbleCount*2;
				computerScore = 0;
			}else if(computerScore>=marbleCount*2) {
				userScore = 0;
				computerScore = marbleCount*2;
			}
		}
		
		userScoreText.setText(playerNo + "번  : " + userScore + "점");
		computerScoreText.setText("457번 : " + computerScore + "점");
		
		if(computerScore==0||userScore==0) {
			oddButton.setVisible(false);
			evenButton.setVisible(false);
			timerCnt.setVisible(false);
			showResult.setVisible(false);
			
			gameOver.setVisible(true);
			

			System.out.println("end 도착");
			System.out.println("유저 점수 : " + userScore);
			System.out.println("컴퓨터 점수 : " + computerScore);

			if (userScore == 0) {
				resultText.setText("졌습니다.");
				win = false;
				heart--;
				if (heart <= 0) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jf.dispose();
					new GameEnding_fail();
					return;
				}if (heart > 0) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jf.dispose();
					new GameEnding_success(playerNo);
					return;
				}
				System.out.println("win : " + win);

			} else {
				resultText.setText("이겼습니다.");
				win = true;
				System.out.println("win : " + win);
				if (heart > 0) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jf.dispose();
					new GameEnding_success(playerNo);
					return;
				}
			}
		}
	}
	
	// 3초 세고 컴이 고른거 보여줌
	private void Attack() {
		evenButton.setVisible(true);
		oddButton.setVisible(true);
		computerMarbleSelect();
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (count < 3) {
					timering = true;
					showResult.setVisible(false);
					timerCnt.setVisible(true);
					timerCnt.setText(3 - count + "");
					//System.out.println(3-count);
					count++;
				}

				else {
					timer.cancel();
					timerCnt.setVisible(false);
					timering = false;
					
					showResult.setText(computerMarblePick + "");
					showResult.setVisible(true);
					
					checkAttack();
					

				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
}
