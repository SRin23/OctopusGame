package marbleOddEven;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

	// �⺻ setting
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	private boolean endCheck = false;
	private static boolean start;
	private String userNumber;

	private JLabel gameOver;
	private JLabel resultText;

	private int marbleCount;

	private int userScore;
	private int computerScore;
	private JLabel scoreLabelUser;
	private JLabel scoreLabelComp;

	JFrame jf;
	private JLabel HowmanyMarble;
	private JLabel MaxMarble;
	private JTextField marbleAmountUser;
	private JLabel ComputerSelectOddEven;
	private JLabel marbleAmountCheck;
	private JComboBox<String> intCombo;

	private boolean win;

	private ImageIcon evenButtonDefault = new ImageIcon(MarbleGameGUI.class.getResource("../img/evenButton.png"));
	private ImageIcon evenButtonEntered = new ImageIcon(
			MarbleGameGUI.class.getResource("../img/evenButtonEntered.png"));
	private ImageIcon oddButtonDefault = new ImageIcon(MarbleGameGUI.class.getResource("../img/oddButton.png"));
	private ImageIcon oddButtonEntered = new ImageIcon(MarbleGameGUI.class.getResource("../img/oddButtonEntered.png"));
	private JLabel ComputerSelectUI;
	private JButton evenButton;
	private JButton oddButton;

	private JLabel cnt;

	Container c1;

	public MarbleGameGUI(String pNum) {
		super(pNum);
		Font font = new Font("���ﳲ�� ��üB", Font.PLAIN, 20);

		this.userNumber = pNum;

		jf = new JFrame();
		c1 = jf.getContentPane();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("Ȧ¦����");
		c1.setLayout(null);
		jf.setResizable(false);
		jf.setSize(1200, 900);
		// jf.setLocationRelativeTo(null);
		c1.setBackground(Color.white);

		// --------------�⺻ Layout---------------------

		// ����� ����
		scoreLabelUser = new JLabel(userNumber + "�� : " + userScore + "��(��)");
		scoreLabelUser.setLocation(10, 10);
		scoreLabelUser.setSize(200, 20);
		scoreLabelUser.setFont(scoreLabelUser.getFont().deriveFont(20.0f));
		scoreLabelUser.setFont(font);

		// ��ǻ�� ����
		scoreLabelComp = new JLabel("457�� : " + computerScore + "��");
		scoreLabelComp.setLocation(10, 30);
		scoreLabelComp.setFont(scoreLabelComp.getFont().deriveFont(20.0f));
		scoreLabelComp.setSize(200, 20);
		scoreLabelComp.setFont(font);

		// ���
		JLabel heartLabel = new JLabel("��� : " + heart + "��");
		heartLabel.setLocation(1080, 10);
		heartLabel.setFont(heartLabel.getFont().deriveFont(20.0f));
		heartLabel.setSize(120, 20);
		heartLabel.setFont(font);

		// 3..2..1..�����ִ� ��
		cnt = new JLabel("3");
		cnt.setBounds(510, 250, 300, 300);
		cnt.setVisible(true);
		cnt.setForeground(Color.black);
		cnt.setFont(cnt.getFont().deriveFont(300.0f));
		c1.add(cnt);

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

		// �����̳ʿ� �߰�(�⺻)
		c1.add(scoreLabelUser);
		c1.add(scoreLabelComp);
		c1.add(heartLabel);
		c1.add(cnt);

		// �����̳ʿ� �߰�(�ʱ�)
		c1.add(HowmanyMarble);
		c1.add(MaxMarble);
		c1.add(marbleAmountUser);

		jf.setVisible(true);
	}

	static int countDown = 1;

	// �� ������ ���� ����
	public int startGame(boolean start) {
		this.start = start;
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				if (countDown < 3) {
					cnt.setText("" + (3 - countDown));
					countDown++;
				} else {
					t.cancel();
					System.out.println("Ÿ�̸Ӱ� ����Ǿ����ϴ�.");
					cnt.setVisible(false);
					System.out.println("���� ��ŸƮ");
					basic();
				}
			}
		};
		t.scheduleAtFixedRate(tt, 1000, 1000);

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
//					System.out.println("��ǻ�� ���� ���� : " + computerScore);
//					System.out.println("����� ���� ���� : " + userScore);
					scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
					scoreLabelComp.setText("457�� : " + computerScore + "��");
					HowmanyMarble.setVisible(false);
					MaxMarble.setVisible(false);
					marbleAmountUser.setVisible(false);

					if (start == false) { // ����(Ȧ¦)
						defence();
					} else { // ����(�������)
						attact();
					}

				}
			}
		});

	}

	void attact() {
		int computerOddEven = rand.nextInt(2);

		String rand_str;
		if (computerOddEven == 0) {
			rand_str = "¦";
		} else {
			rand_str = "Ȧ";
		}

		// ��ǻ�Ͱ� �� ��
		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.black), "457�� ����");
		ComputerSelectOddEven = new JLabel(rand_str);
		ComputerSelectOddEven.setLocation(450, 200);
		ComputerSelectOddEven.setFont(ComputerSelectOddEven.getFont().deriveFont(120.0f));
		ComputerSelectOddEven.setSize(300, 200);
		ComputerSelectOddEven.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectOddEven.setVisible(true);
		ComputerSelectOddEven.setBorder(twoTb);

		// ���� ��
		marbleAmountCheck = new JLabel("���� : ");
		marbleAmountCheck.setLocation(500, 500);
		marbleAmountCheck.setFont(marbleAmountCheck.getFont().deriveFont(30.0f));
		marbleAmountCheck.setSize(100, 100);
		marbleAmountCheck.setVisible(true);

		// �޺��ڽ�(���� ���� ����)
		String[] ComboArr = new String[userScore + 1];
		for (int i = 0; i <= userScore; i++) {
			ComboArr[i] = Integer.toString(i);
		}
		intCombo = new JComboBox<String>(ComboArr);
		intCombo.setLocation(600, 530);
		intCombo.setFont(intCombo.getFont().deriveFont(30.0f));
		intCombo.setSize(100, 50);
		intCombo.setVisible(true);
		intCombo.addActionListener(new ActionListener() {
			// start = false�ؾ���!!
			@Override
			public void actionPerformed(ActionEvent e) {
				start = false;
				ComputerSelectOddEven.setVisible(true);
				JComboBox<String> jb = (JComboBox) e.getSource();
				int index = jb.getSelectedIndex();

				if ((index % 2 == 0 && rand_str.equals("¦")) || (index % 2 == 1 && rand_str.equals("Ȧ"))) {
					userScore -= index;
					computerScore += index;
				} else {
					userScore += index;
					computerScore -= index;
				}

				if (userScore <= 0) {
					userScore = 0;
					computerScore = marbleCount * 2;
					end();
				} else if (computerScore <= 0) {
					computerScore = 0;
					userScore = marbleCount * 2;
					end();
				}

				scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
				scoreLabelComp.setText("457�� : " + computerScore + "��");

				if (start == false && endCheck != true) {
					ComputerSelectOddEven.setVisible(false);
					marbleAmountCheck.setVisible(false);
					intCombo.setVisible(false);

					defence();
				}

			}
		});
		// �����̳ʿ� �߰�(����)
		c1.add(ComputerSelectOddEven);
		c1.add(marbleAmountCheck);
		c1.add(intCombo);
	}

	void defence() {
		int computerSelect = rand.nextInt(computerScore + 1);
		// System.out.println("��ǻ�Ͱ� �� ������ �� : " + computerSelect);
		
		// ��ǻ�Ͱ� �� ��
		ComputerSelectUI = new JLabel(computerSelect + "��");
		ComputerSelectUI.setLocation(450, 200);
		ComputerSelectUI.setFont(ComputerSelectUI.getFont().deriveFont(120.0f));
		ComputerSelectUI.setSize(300, 100);
		ComputerSelectUI.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectUI.setVisible(true);

		// Ȧ ��ư
		evenButton = new JButton(evenButtonDefault);
		evenButton.setBounds(700, 500, 300, 300);
		evenButton.setBorderPainted(false);
		evenButton.setContentAreaFilled(false);
		evenButton.setFocusPainted(false);
		evenButton.setVisible(true);
		evenButton.addMouseListener(new MouseAdapter() {
			// ��ư ���� ���콺
			@Override
			public void mouseEntered(MouseEvent e) {
				// Ŀ����� �հ���
				evenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				evenButton.setIcon(evenButtonEntered);
			}

			// �⺻
			@Override
			public void mouseExited(MouseEvent e) {
				evenButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				evenButton.setIcon(evenButtonDefault);
			}

			// ��ư�� ���콺�� ������
			@Override
			public void mousePressed(MouseEvent e) {
				start = true;

				if (computerSelect % 2 == 0) {
					userScore += computerSelect;
					computerScore -= computerSelect;
				} else {
					userScore -= computerSelect;
					computerScore += computerSelect;
				}

				check();

				scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
				scoreLabelComp.setText("457�� : " + computerScore + "��");
				System.out.println(start);
				if (start == true && endCheck != true) {
					ComputerSelectUI.setVisible(false);
					evenButton.setVisible(false);
					oddButton.setVisible(false);

					attact();
				}
			}
		});

		// ¦ ��ư
		oddButton = new JButton(oddButtonDefault);
		oddButton.setBounds(200, 500, 300, 300);
		oddButton.setBorderPainted(false);
		oddButton.setContentAreaFilled(false);
		oddButton.setFocusPainted(false);
		oddButton.setVisible(true);
		oddButton.addMouseListener(new MouseAdapter() {
			// ��ư ���� ���콺
			@Override
			public void mouseEntered(MouseEvent e) {
				// Ŀ����� �հ���
				oddButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				oddButton.setIcon(oddButtonEntered);
			}

			// �⺻
			@Override
			public void mouseExited(MouseEvent e) {
				oddButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				oddButton.setIcon(oddButtonDefault);
			}

			// ��ư�� ���콺�� ������
			@Override
			public void mousePressed(MouseEvent e) {
				start = true;
				int computerSelect = rand.nextInt(computerScore + 1);
				System.out.println("��ǻ�Ͱ� �� ������ �� : " + computerSelect);
				ComputerSelectUI.setText(computerSelect + "��");

				if (computerSelect % 2 == 1) {
					userScore += computerSelect;
					computerScore -= computerSelect;

				} else {
					userScore -= computerSelect;
					computerScore += computerSelect;

				}

				check();

				scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
				scoreLabelComp.setText("457�� : " + computerScore + "��");

				if (start == true && endCheck != true) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ComputerSelectUI.setVisible(false);
					evenButton.setVisible(false);
					oddButton.setVisible(false);

					attact();
				}
			}
		});

		// �����̳ʿ� �߰�(����)
		c1.add(ComputerSelectUI);
		c1.add(evenButton);
		c1.add(oddButton);

	}

	void end() {
		endCheck = true;
		System.out.println("start : " + start);
		if (start == false) {
			System.out.println("���� ����");
			ComputerSelectOddEven.setVisible(false);
			marbleAmountCheck.setVisible(false);
			intCombo.setVisible(false);

		} else {
			System.out.println("���� ����");
			ComputerSelectUI.setVisible(false);
			evenButton.setVisible(false);
			oddButton.setVisible(false);

		}

		gameOver = new JLabel("GAME OVER");
		gameOver.setBounds(200, 300, 800, 150);
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

		System.out.println("end ����");
		System.out.println("���� ���� : " + userScore);
		System.out.println("��ǻ�� ���� : " + computerScore);

		if (userScore == 0) {
			resultText.setText("�����ϴ�.");
			win = false;
			heart--;
			if (heart <= 0) {
				jf.dispose();
				new GameEnding_fail();
				return;
			}
			System.out.println("win : " + win);

		} else {
			resultText.setText("�̰���ϴ�.");
			win = true;
			System.out.println("win : " + win);
			if (heart > 0) {
				jf.dispose();
				new GameEnding_success(userNumber);
				return;
			}
		}
		try {
			Thread.sleep(2000);
			jf.dispose();

			return;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void check() {
		if (userScore <= 0) {
			System.out.println("������ 0");
			userScore = 0;
			computerScore = marbleCount * 2;
			end();
		} else if (computerScore <= 0) {
			System.out.println("��ǻ�ʹ� 0");
			computerScore = 0;
			userScore = marbleCount * 2;
			end();
		}
	}
}
