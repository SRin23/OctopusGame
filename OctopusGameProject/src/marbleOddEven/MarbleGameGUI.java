package marbleOddEven;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MarbleGameGUI extends JFrame{
	private static final long serialVersionUID = -7290086117028538892L;
	
	//�⺻ setting
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	private boolean endCheck = false;
	private static boolean start;
	private int heart;
	private int userNumber;
	
	
	private JLabel gameOver;
	private JLabel resultText;
	
	private int marbleCount;
	
	private int userScore;
	private int computerScore;
	private JLabel scoreLabelUser;
	private JLabel scoreLabelComp;
	
	private JLabel ComputerSelectOddEven;
	private JLabel marbleAmountCheck;
	private JLabel warning;
	private JComboBox<String> intCombo;
	
	private ImageIcon evenButtonDefault = new ImageIcon(MarbleGameMain.class.getResource("../img/evenButton.png"));
	private ImageIcon evenButtonEntered = new ImageIcon(MarbleGameMain.class.getResource("../img/evenButtonEntered.png"));
	private ImageIcon oddButtonDefault = new ImageIcon(MarbleGameMain.class.getResource("../img/oddButton.png"));
	private ImageIcon oddButtonEntered = new ImageIcon(MarbleGameMain.class.getResource("../img/oddButtonEntered.png"));
	private JLabel ComputerSelectUI;
	private JButton evenButton;
	private JButton oddButton;
	
	
	Container c1 = getContentPane();
	
	public MarbleGameGUI(boolean start, int heart, int userNumber) {
		this.start = start;
		this.heart = heart;
		this.userNumber = userNumber;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ȧ¦����");
		c1.setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		c1.setBackground(Color.white);
		
		//--------------�⺻ Layout---------------------
		
		//����� ����
		scoreLabelUser = new JLabel(userNumber + "�� : " + userScore + "��(��)");
		scoreLabelUser.setLocation(10, 10);
		scoreLabelUser.setSize(200, 20);
		scoreLabelUser.setFont(scoreLabelUser.getFont().deriveFont(20.0f));
		
		//��ǻ�� ����
		scoreLabelComp = new JLabel("457�� : " + computerScore + "��");
		scoreLabelComp.setLocation(10, 30);
		scoreLabelComp.setFont(scoreLabelComp.getFont().deriveFont(20.0f));
		scoreLabelComp.setSize(200, 20);
		
		//���
		JLabel heartLabel = new JLabel("��� : " + heart + "��");
		heartLabel.setLocation(1080, 10);
		heartLabel.setFont(heartLabel.getFont().deriveFont(20.0f));
		heartLabel.setSize(120, 20);
		
		//�����̳ʿ� �߰�(�⺻)
		c1.add(scoreLabelUser);
		c1.add(scoreLabelComp);
		c1.add(heartLabel);
		
		basic();
		
		setVisible(true);
	}
	
	void basic() {
		//--------------------------------�ʱ� ȭ��---------------------------------------------
		JLabel HowmanyMarble = new JLabel("��� ������ ����Ͻðڽ��ϱ�?");
		HowmanyMarble.setLocation(350, 200);
		HowmanyMarble.setFont(HowmanyMarble.getFont().deriveFont(30.0f));
		HowmanyMarble.setSize(500, 50);
		HowmanyMarble.setHorizontalAlignment(JLabel.CENTER);
		HowmanyMarble.setVisible(true);
		
		JLabel MaxMarble = new JLabel("(�ִ� ���� ���� : 50��)");
		MaxMarble.setLocation(500, 250);
		MaxMarble.setFont(MaxMarble.getFont().deriveFont(18.0f));
		MaxMarble.setSize(200, 50);
		MaxMarble.setVisible(true);
		
		JTextField marbleAmountUser = new JTextField(5);
		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.black), "�Է�");
		marbleAmountUser.setLocation(500, 500);
		marbleAmountUser.setSize(200, 100);
		marbleAmountUser.setFont(marbleAmountUser.getFont().deriveFont(60.0f));
		marbleAmountUser.setHorizontalAlignment(JLabel.CENTER);
		marbleAmountUser.setBackground(Color.white);
		marbleAmountUser.setBorder(oneTb);
		marbleAmountUser.setVisible(true);
		marbleAmountUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField jf = (JTextField)e.getSource();
				marbleCount = Integer.parseInt(jf.getText());
				System.out.println("��ü ���� ���� : " + marbleCount);
				if(marbleCount>50||marbleCount<10) HowmanyMarble.setText("�ٽ��Է����ּ���.");
				else {
					HowmanyMarble.setText("�Է��� �Ϸ�Ǿ����ϴ�.");
					computerScore = marbleCount;
					userScore = marbleCount;
					System.out.println("��ǻ�� ���� ���� : " + computerScore);
					System.out.println("����� ���� ���� : " + userScore);
					scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
					scoreLabelComp.setText("457�� : " + computerScore + "��");
					HowmanyMarble.setVisible(false);
					MaxMarble.setVisible(false);
					marbleAmountUser.setVisible(false);
					
					if(start == false) {	//����(Ȧ¦)
						defence();
					}else {	//����(�������)
						attact();
					}
					
				}
			}
		});
		//�����̳ʿ� �߰�(�ʱ�)
		c1.add(HowmanyMarble);
		c1.add(MaxMarble);
		c1.add(marbleAmountUser);
		
	}
	
	void attact() {
		//��ǻ�Ͱ� �� ��
		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.black), "457�� ����");
		ComputerSelectOddEven = new JLabel();
		ComputerSelectOddEven.setLocation(450, 200);
		ComputerSelectOddEven.setFont(ComputerSelectOddEven.getFont().deriveFont(120.0f));
		ComputerSelectOddEven.setSize(300, 200);
		ComputerSelectOddEven.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectOddEven.setVisible(true);
		ComputerSelectOddEven.setBorder(twoTb);
		
		//���� ��
		marbleAmountCheck = new JLabel("���� : ");
		marbleAmountCheck.setLocation(500, 500);
		marbleAmountCheck.setFont(marbleAmountCheck.getFont().deriveFont(30.0f));
		marbleAmountCheck.setSize(100, 100);
		marbleAmountCheck.setVisible(true);
		
		//���â
		warning = new JLabel();
		warning.setLocation(400, 300);
		warning.setSize(400, 100);
		warning.setFont(warning.getFont().deriveFont(20.0f));
		warning.setHorizontalAlignment(JLabel.CENTER);
		warning.setForeground(Color.red);
		warning.setVisible(false);
		
		//�޺��ڽ�(���� ���� ����)
		String[] ComboArr = new String[userScore+1];
		for(int i = 0; i<=userScore; i++) {
			ComboArr[i] = Integer.toString(i);
		}
		System.out.println("���� ���� : " + userScore + ", " + ComboArr.length);
		
		intCombo = new JComboBox<String>(ComboArr);
		intCombo.setLocation(600, 530);
		intCombo.setFont(intCombo.getFont().deriveFont(30.0f));
		intCombo.setSize(100, 50);
		intCombo.setVisible(true);
		intCombo.addActionListener(new ActionListener() {
			//start = false�ؾ���!!
			int check = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				start = false;
				JComboBox<String> jb = (JComboBox)e.getSource();
				int index = jb.getSelectedIndex();
				System.out.println("�ε��� : " + index);
				if(index<0) {
					warning.setText("������ ������ �߸��Ǿ����ϴ�.");
					warning.setVisible(true);
					check++;
					System.out.println("Ȯ��  : " + check);
				}
				else if(index>userScore) {
					warning.setText("������ ������ �ʹ� Ů�ϴ�.");
					warning.setVisible(true);
					System.out.println("������ ŭ");
					check++;
					System.out.println("Ȯ��  : " + check);
				}
				else {
					warning.setVisible(false);
					int computerOddEven = rand.nextInt(2);
					
					String rand_str;
					if(computerOddEven==0) {
						rand_str = "¦";
					}else {
						rand_str = "Ȧ";
					}
					System.out.println("��ǻ�Ͱ� : " + computerOddEven);
					System.out.println("��ǻ�Ͱ� ���ڿ� : " + rand_str);

					ComputerSelectOddEven.setText(rand_str + "");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					if((index%2==0&&rand_str.equals("¦"))||(index%2==1&&rand_str.equals("Ȧ"))) {
						System.out.println("����� ���� ���� : " + index);
						System.out.println("457���� " + rand_str + "�� ������ϴ�.");
						System.out.println("����� ������ " + index + "��ŭ �����˴ϴ�.");
						userScore-=index;
						computerScore+=index;
					}
					else {
						System.out.println("����� ���� ���� : " + index);
						System.out.println("457���� " + rand_str + "��, Ʋ�Ƚ��ϴ�.");
						System.out.println("����� ������ " + index + "��ŭ �����˴ϴ�.");
						userScore+=index;
						computerScore-=index;
					}
					
					if(check>=3) {
						warning.setText("3�� Ʋ�Ƚ��ϴ�. ���������� �Ѿ�ϴ�.");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						defence();
					}
					
					if(userScore<=0) {
						userScore = 0;
						computerScore = marbleCount*2;
						end();
					}else if(computerScore<=0) {
						computerScore = 0;
						userScore = marbleCount*2;
						end();
					}
					
					scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
					scoreLabelComp.setText("457�� : " + computerScore + "��");
					
					
					if(start == false&&endCheck!=true) {
						ComputerSelectOddEven.setVisible(false);
						marbleAmountCheck.setVisible(false);
						warning.setVisible(false);
						intCombo.setVisible(false);
						
						defence();
					}
					
				}
			}
		});
		//�����̳ʿ� �߰�(����)
		c1.add(ComputerSelectOddEven);
		c1.add(marbleAmountCheck);
		c1.add(intCombo);
	}
	
	
	void defence() {
		//��ǻ�Ͱ� �� ��
		ComputerSelectUI = new JLabel("����");
		ComputerSelectUI.setLocation(450, 200);
		ComputerSelectUI.setFont(ComputerSelectUI.getFont().deriveFont(120.0f));
		ComputerSelectUI.setSize(300, 100);
		ComputerSelectUI.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectUI.setVisible(true);
		
		//Ȧ ��ư
		evenButton = new JButton(evenButtonDefault);
		evenButton.setBounds(700, 500, 300, 300);
		evenButton.setBorderPainted(false);
		evenButton.setContentAreaFilled(false);
		evenButton.setFocusPainted(false);
		evenButton.setVisible(true);
		evenButton.addMouseListener(new MouseAdapter(){
			//��ư ���� ���콺
			@Override
			public void mouseEntered(MouseEvent e) {
				//Ŀ����� �հ���
				evenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				evenButton.setIcon(evenButtonEntered);
			}
			//�⺻
			@Override
			public void mouseExited(MouseEvent e) {
				evenButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				evenButton.setIcon(evenButtonDefault);
			}
			//��ư�� ���콺�� ������
			@Override
			public void mousePressed(MouseEvent e) {
				start = true;
				
				int computerSelect = rand.nextInt(computerScore+1);
				//System.out.println("��ǻ�Ͱ� �� ������ �� : " + computerSelect);
				ComputerSelectUI.setText(computerSelect + "��");
				
				if(computerSelect%2==0) {
					userScore += computerSelect;
					computerScore -= computerSelect;
				}else {
					userScore -= computerSelect;
					computerScore += computerSelect;
				}
				
				check();
				
				scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
				scoreLabelComp.setText("457�� : " + computerScore + "��");
				System.out.println(start);
				if(start == true&&endCheck!=true) {
					ComputerSelectUI.setVisible(false);
					evenButton.setVisible(false);
					oddButton.setVisible(false);
					
					attact();
				}
			}
		});
		
		//¦ ��ư
		oddButton = new JButton(oddButtonDefault);
		oddButton.setBounds(200, 500, 300, 300);
		oddButton.setBorderPainted(false);
		oddButton.setContentAreaFilled(false);
		oddButton.setFocusPainted(false);
		oddButton.setVisible(true);
		oddButton.addMouseListener(new MouseAdapter(){
			//��ư ���� ���콺
			@Override
			public void mouseEntered(MouseEvent e) {
				//Ŀ����� �հ���
				oddButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				oddButton.setIcon(oddButtonEntered);
			}
			//�⺻
			@Override
			public void mouseExited(MouseEvent e) {
				oddButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				oddButton.setIcon(oddButtonDefault);
			}
			//��ư�� ���콺�� ������
			@Override
			public void mousePressed(MouseEvent e) {
				start = true;
				int computerSelect = rand.nextInt(computerScore+1);
				System.out.println("��ǻ�Ͱ� �� ������ �� : " + computerSelect);
				ComputerSelectUI.setText(computerSelect + "��");
				
				if(computerSelect%2==1) {
					userScore += computerSelect;
					computerScore -= computerSelect;
					
				}else {
					userScore -= computerSelect;
					computerScore += computerSelect;
					
				}
				
				check();
				
				System.out.println("| ���� ������ ����  | " + userNumber + "��(��) : " + userScore + "�� | 457�� : " + computerScore + "�� | ");
				scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
				scoreLabelComp.setText("457�� : " + computerScore + "��");
				
				if(start == true&&endCheck!=true) {
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
		
		//�����̳ʿ� �߰�(����)
		c1.add(ComputerSelectUI);
		c1.add(evenButton);
		c1.add(oddButton);
		
	}
	
	int end() {
		endCheck = true;
		System.out.println("start : " + start);
		if(start == false) {
			System.out.println("���� ����");
			ComputerSelectOddEven.setVisible(false);
			marbleAmountCheck.setVisible(false);
			warning.setVisible(false);
			intCombo.setVisible(false);
			
			
		}else {
			System.out.println("���� ����");
			ComputerSelectUI.setVisible(false);
			evenButton.setVisible(false);
			oddButton.setVisible(false);
			
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
		
		
		
		System.out.println("end ����");
		System.out.println("���� ���� : " + userScore);
		System.out.println("��ǻ�� ���� : " + computerScore);
		
		if(userScore == 0) {
			resultText.setText("�����ϴ�.");
			return heart-1;
		}else {
			resultText.setText("�̰���ϴ�.");
			return heart;
		}
		
	}
	
	void check() {
		if(userScore<=0) {
			System.out.println("������ 0");
			userScore = 0;
			computerScore = marbleCount*2;
			end();
		}else if(computerScore<=0) {
			System.out.println("��ǻ�ʹ� 0");
			computerScore = 0;
			userScore = marbleCount*2;
			end();
		}
	}
}
