package BeanOddEvenGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FirstMarbleGame extends JFrame{
	private static final long serialVersionUID = -7290086117028538892L;
	
	//�⺻ setting
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	private static boolean start;
	private int heart;
	private int userNumber;
	
	private int marbleCount = 5;
	
	private int userScore = 1;
	private int computerScore = 1;
	
	private JLabel ComputerSelectOddEven;
	private JLabel marbleAmountCheck;
	private JLabel warning;
	private JComboBox<String> intCombo;
	
	private ImageIcon evenButtonDefault = new ImageIcon(MarbleGame.class.getResource("../img/evenButton.png"));
	private ImageIcon evenButtonEntered = new ImageIcon(MarbleGame.class.getResource("../img/evenButtonEntered.png"));
	private ImageIcon oddButtonDefault = new ImageIcon(MarbleGame.class.getResource("../img/oddButton.png"));
	private ImageIcon oddButtonEntered = new ImageIcon(MarbleGame.class.getResource("../img/oddButtonEntered.png"));
	private JLabel ComputerSelectUI;
	private JButton evenButton;
	private JButton oddButton;
	
	
	Container c1 = getContentPane();
	
	public FirstMarbleGame(boolean start1, int heart, int userNumber) {
		this.start = start1;
		this.heart = heart;
		this.userNumber = userNumber;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ȧ¦����");
		c1.setLayout(null);
		c1.setBackground(Color.white);
		

		//����� ����
		JLabel scoreLabelUser = new JLabel(userNumber + "�� : " + userScore + "��(��)");
		scoreLabelUser.setLocation(10, 10);
		scoreLabelUser.setSize(200, 20);
		scoreLabelUser.setFont(scoreLabelUser.getFont().deriveFont(20.0f));
		
		//��ǻ�� ����
		JLabel scoreLabelComp = new JLabel("457�� : " + computerScore + "��");
		scoreLabelComp.setLocation(10, 30);
		scoreLabelComp.setFont(scoreLabelComp.getFont().deriveFont(20.0f));
		scoreLabelComp.setSize(200, 20);
		
		//���
		JLabel heartLabel = new JLabel("��� : " + heart + "��");
		heartLabel.setLocation(1080, 10);
		heartLabel.setFont(heartLabel.getFont().deriveFont(20.0f));
		heartLabel.setSize(120, 20);
		
		
		//--------------------------------�ʱ� ȭ��---------------------------------------------
		JLabel HowmanyMarble = new JLabel("��� ������ ����Ͻðڽ��ϱ�?");
		HowmanyMarble.setLocation(350, 200);
		HowmanyMarble.setFont(HowmanyMarble.getFont().deriveFont(30.0f));
		HowmanyMarble.setSize(500, 50);
		HowmanyMarble.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel MaxMarble = new JLabel("(�ִ� ���� ���� : 30��)");
		MaxMarble.setLocation(500, 250);
		MaxMarble.setFont(MaxMarble.getFont().deriveFont(18.0f));
		MaxMarble.setSize(200, 50);
		
		JTextField marbleAmountUser = new JTextField(5);
		marbleAmountUser.setLocation(500, 500);
		marbleAmountUser.setSize(200, 100);
		marbleAmountUser.setFont(marbleAmountUser.getFont().deriveFont(60.0f));
		marbleAmountUser.setHorizontalAlignment(JLabel.CENTER);
		marbleAmountUser.setBackground(Color.lightGray);
		marbleAmountUser.setBorder(null);
		marbleAmountUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField jf = (JTextField)e.getSource();
				marbleCount = Integer.parseInt(jf.getText());
				System.out.println(marbleCount);
				if(marbleCount>30) HowmanyMarble.setText("�ٽ��Է����ּ���.");
				else {
					HowmanyMarble.setText("�Է��� �Ϸ�Ǿ����ϴ�.");
					computerScore = marbleCount;
					userScore = marbleCount;
					scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
					scoreLabelComp.setText("457�� : " + computerScore + "��");
					HowmanyMarble.setVisible(false);
					MaxMarble.setVisible(false);
					marbleAmountUser.setVisible(false);
					
					if(start == false) {
						ComputerSelectUI.setVisible(true);
						evenButton.setVisible(true);
						oddButton.setVisible(true);
					}else {
						ComputerSelectOddEven.setVisible(true);
						marbleAmountCheck.setVisible(true);
						intCombo.setVisible(true);
					}
					
				}
			}
		});
		
		//--------------------------------���� ȭ��---------------------------------------------
		//��ǻ�Ͱ� �� ��
		ComputerSelectOddEven = new JLabel("Hello");
		ComputerSelectOddEven.setLocation(450, 200);
		ComputerSelectOddEven.setFont(ComputerSelectOddEven.getFont().deriveFont(120.0f));
		ComputerSelectOddEven.setSize(300, 200);
		ComputerSelectOddEven.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectOddEven.setVisible(false);
		
		//���� ��
		marbleAmountCheck = new JLabel("���� : ");
		marbleAmountCheck.setLocation(500, 500);
		marbleAmountCheck.setFont(marbleAmountCheck.getFont().deriveFont(30.0f));
		marbleAmountCheck.setSize(100, 100);
		marbleAmountCheck.setVisible(false);
		
		//���â
		warning = new JLabel();
		warning.setLocation(400, 300);
		warning.setSize(400, 100);
		warning.setFont(warning.getFont().deriveFont(20.0f));
		warning.setHorizontalAlignment(JLabel.CENTER);
		warning.setForeground(Color.red);
		warning.setVisible(false);
		
		//�޺��ڽ�(���� ���� ����)
		String[] ComboArr = new String[marbleCount];
		for(int i = 0; i<=userScore; i++) {
			ComboArr[i] = Integer.toString(i);
		}
		
		intCombo = new JComboBox<String>(ComboArr);
		intCombo.setLocation(600, 530);
		intCombo.setSize(100, 50);
		intCombo.setVisible(false);
		intCombo.addActionListener(new ActionListener() {
			//start = false�ؾ���!!
			int check = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jb = (JComboBox)e.getSource();
				int index = jb.getSelectedIndex();
				System.out.println(index);
				
				if(index<0) {
					warning.setText("������ ������ �߸��Ǿ����ϴ�.");
					warning.setVisible(true);
					check++;
					//4���̻� �߸� �Է½� �� ���ư�
				}
				else if(index>userScore) {
					warning.setText("������ ������ �ʹ� Ů�ϴ�.");
					warning.setVisible(true);
					check++;
					//4���̻� �߸� �Է½� �� ���ư�
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
					System.out.println(rand_str);
					ComputerSelectOddEven.setText(rand_str);
					
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
					if(userScore<0) {
						userScore = 0;
					}else if(computerScore<0) {
						computerScore = 0;
					}
					
					scoreLabelUser.setText(userNumber + "�� : " + userScore + "��(��)");
					scoreLabelComp.setText("457�� : " + computerScore + "��");
					HowmanyMarble.setVisible(false);
					MaxMarble.setVisible(false);
					marbleAmountUser.setVisible(false);
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					ComputerSelectOddEven.setVisible(false);
					marbleAmountCheck.setVisible(false);
					warning.setVisible(false);
					intCombo.setVisible(false);
					
					ComputerSelectUI.setVisible(true);
					evenButton.setVisible(true);
					oddButton.setVisible(true);
				}
			}
		});
		
		//--------------------------------���� ȭ��---------------------------------------------
		//��ǻ�Ͱ� �� ��
		ComputerSelectUI = new JLabel();
		ComputerSelectUI.setLocation(450, 200);
		ComputerSelectUI.setFont(ComputerSelectUI.getFont().deriveFont(120.0f));
		ComputerSelectUI.setSize(300, 100);
		ComputerSelectUI.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectUI.setVisible(false);
		
		//Ȧ ��ư
		evenButton = new JButton(evenButtonDefault);
		evenButton.setBounds(700, 500, 300, 300);
		evenButton.setBorderPainted(false);
		evenButton.setContentAreaFilled(false);
		evenButton.setFocusPainted(false);
		evenButton.setVisible(false);
		
		//¦ ��ư
		oddButton = new JButton(oddButtonDefault);
		oddButton.setBounds(200, 500, 300, 300);
		oddButton.setBorderPainted(false);
		oddButton.setContentAreaFilled(false);
		oddButton.setFocusPainted(false);
		oddButton.setVisible(false);
		
		//--------------------------------�����̳� �߰�----------------------------------------
		//�����̳ʿ� �߰�(�⺻)
		c1.add(scoreLabelUser);
		c1.add(scoreLabelComp);
		c1.add(heartLabel);
		
		//�����̳ʿ� �߰�(�ʱ�)
		c1.add(HowmanyMarble);
		c1.add(MaxMarble);
		c1.add(marbleAmountUser);
		
		//�����̳ʿ� �߰�(����)
		c1.add(ComputerSelectOddEven);
		c1.add(marbleAmountCheck);
		c1.add(intCombo);

		//�����̳ʿ� �߰�(����)
		c1.add(ComputerSelectUI);
		c1.add(evenButton);
		c1.add(oddButton);
		
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		setVisible(true);
	}
}
