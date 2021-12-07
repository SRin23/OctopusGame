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
	final int BTN_CNT = 3; // ���������� ��ư�� ��

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
	int count = 0; // 3�� ī��Ʈ

	boolean timering = false; // timer�� ���� ������ üũ
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

		Font font = new Font("���ﳲ�� ��üB", Font.PLAIN, 20);

		jf = new JFrame();
		Container c1 = jf.getContentPane();

		jf.setTitle("Ȧ¦���߱�");
		// â�� ���� �� ���α׷� ����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ������ â ����
		jf.setResizable(false);
		jf.setSize(1200, 900);
		// ������(���� x �ִ� ��) ���̰� ����
		jf.setVisible(true);

		c1.setBackground(Color.WHITE);
		heartLabel = new JLabel("��� : " + heart + "��");
		heartLabel.setLocation(1080, 10);
		heartLabel.setSize(120, 20);
		heartLabel.setFont(font.deriveFont(20.0f));
		heartLabel.setVisible(true);
		
		// �� ���� ��ư ����
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
				userOddEvenPick = "Ȧ";
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
				userOddEvenPick = "¦";
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


		HowmanyMarble = new JLabel("��� ������ ����Ͻðڽ��ϱ�?");
		HowmanyMarble.setLocation(350, 200);
		HowmanyMarble.setFont(HowmanyMarble.getFont().deriveFont(30.0f));
		HowmanyMarble.setSize(500, 50);
		HowmanyMarble.setHorizontalAlignment(JLabel.CENTER);
		HowmanyMarble.setBackground(Color.black);
		HowmanyMarble.setVisible(false);
		HowmanyMarble.setFont(font);

		MaxMarble = new JLabel("(�ִ� ���� ���� : 50��)");
		MaxMarble.setLocation(500, 250);
		MaxMarble.setFont(MaxMarble.getFont().deriveFont(18.0f));
		MaxMarble.setSize(400, 50);
		MaxMarble.setVisible(false);
		MaxMarble.setFont(font);

		marbleAmountUser = new JTextField(5);
		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.black), "�Է�");
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

		computerScoreText = new JLabel("457�� : 0��");
		computerScoreText.setBounds(0, 0, 200, 30);
		computerScoreText.setForeground(Color.BLACK);
		computerScoreText.setVisible(true);
		computerScoreText.setFont(font.deriveFont(20.0f));
		
		
		userScoreText = new JLabel(playerNo + "�� : 0��");
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
				System.out.println("��ü ���� ���� : " + marbleCount);
				if (marbleCount > 50 || marbleCount < 10)
					HowmanyMarble.setText("�ٽ��Է����ּ���.");
				else {
					HowmanyMarble.setText("�Է��� �Ϸ�Ǿ����ϴ�.");
					computerScore = marbleCount;
					userScore = marbleCount;
					userScoreText.setText(playerNo + "�� : " + userScore + "��(��)");
					computerScoreText.setText("457�� : " + computerScore + "��");
					HowmanyMarble.setVisible(false);
					MaxMarble.setVisible(false);
					marbleAmountUser.setVisible(false);

					if (start == false) { // ����(Ȧ¦)
						//defence();
					} else { // ����(�������)
						oddButton.setVisible(true);
						evenButton.setVisible(true);
						timerCnt.setVisible(true);
					}

				}
			}
		});

	}
	
	// ���� Ȧ, ¦ �� �������� �ϳ� ��
	private void computerOddEvenSelect() {
		int tmp = (int)(Math.random()*2);
		if(tmp==0) {
			computerOddEvenPick = "¦";
		}else {
			computerOddEvenPick = "Ȧ";
		}
		
	}
	
	// ���� �ڽ��� ���� ������ ������ �������� ��
	private void computerMarbleSelect() {
		computerMarblePick = (int)(Math.random()*(computerScore+1));
		System.out.println(computerMarblePick);
	}

	private void checkAttack() {
		if ((userOddEvenPick.equals("¦") && computerMarblePick%2==0)||(userOddEvenPick.equals("Ȧ") && computerMarblePick%2==1)) {
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
		
		userScoreText.setText(playerNo + "��  : " + userScore + "��");
		computerScoreText.setText("457�� : " + computerScore + "��");
		
		if(computerScore==0||userScore==0) {
			oddButton.setVisible(false);
			evenButton.setVisible(false);
			timerCnt.setVisible(false);
			showResult.setVisible(false);
			
			gameOver.setVisible(true);
			

			System.out.println("end ����");
			System.out.println("���� ���� : " + userScore);
			System.out.println("��ǻ�� ���� : " + computerScore);

			if (userScore == 0) {
				resultText.setText("�����ϴ�.");
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
				resultText.setText("�̰���ϴ�.");
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
	
	// 3�� ���� ���� ���� ������
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
