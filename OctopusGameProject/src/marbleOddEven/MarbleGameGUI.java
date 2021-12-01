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
	
	//기본 setting
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
		setTitle("홀짝게임");
		c1.setLayout(null);
		c1.setBackground(Color.white);
		

		//사용자 점수
		JLabel scoreLabelUser = new JLabel(userNumber + "번 : " + userScore + "개(나)");
		scoreLabelUser.setLocation(10, 10);
		scoreLabelUser.setSize(200, 20);
		scoreLabelUser.setFont(scoreLabelUser.getFont().deriveFont(20.0f));
		
		//컴퓨터 점수
		JLabel scoreLabelComp = new JLabel("457번 : " + computerScore + "개");
		scoreLabelComp.setLocation(10, 30);
		scoreLabelComp.setFont(scoreLabelComp.getFont().deriveFont(20.0f));
		scoreLabelComp.setSize(200, 20);
		
		//목숨
		JLabel heartLabel = new JLabel("목숨 : " + heart + "개");
		heartLabel.setLocation(1080, 10);
		heartLabel.setFont(heartLabel.getFont().deriveFont(20.0f));
		heartLabel.setSize(120, 20);
		
		
		//--------------------------------초기 화면---------------------------------------------
		JLabel HowmanyMarble = new JLabel("몇개의 구슬을 사용하시겠습니까?");
		HowmanyMarble.setLocation(350, 200);
		HowmanyMarble.setFont(HowmanyMarble.getFont().deriveFont(30.0f));
		HowmanyMarble.setSize(500, 50);
		HowmanyMarble.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel MaxMarble = new JLabel("(최대 구슬 개수 : 30개)");
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
				if(marbleCount>30) HowmanyMarble.setText("다시입력해주세요.");
				else {
					HowmanyMarble.setText("입력이 완료되었습니다.");
					computerScore = marbleCount;
					userScore = marbleCount;
					scoreLabelUser.setText(userNumber + "번 : " + userScore + "개(나)");
					scoreLabelComp.setText("457번 : " + computerScore + "개");
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
		
		//--------------------------------공격 화면---------------------------------------------
		//컴퓨터가 고른 수
		ComputerSelectOddEven = new JLabel("Hello");
		ComputerSelectOddEven.setLocation(450, 200);
		ComputerSelectOddEven.setFont(ComputerSelectOddEven.getFont().deriveFont(120.0f));
		ComputerSelectOddEven.setSize(300, 200);
		ComputerSelectOddEven.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectOddEven.setVisible(false);
		
		//구슬 라벨
		marbleAmountCheck = new JLabel("구슬 : ");
		marbleAmountCheck.setLocation(500, 500);
		marbleAmountCheck.setFont(marbleAmountCheck.getFont().deriveFont(30.0f));
		marbleAmountCheck.setSize(100, 100);
		marbleAmountCheck.setVisible(false);
		
		//경고창
		warning = new JLabel();
		warning.setLocation(400, 300);
		warning.setSize(400, 100);
		warning.setFont(warning.getFont().deriveFont(20.0f));
		warning.setHorizontalAlignment(JLabel.CENTER);
		warning.setForeground(Color.red);
		warning.setVisible(false);
		
		//콤보박스(구슬 개수 고르는)
		String[] ComboArr = new String[marbleCount];
		for(int i = 0; i<=userScore; i++) {
			ComboArr[i] = Integer.toString(i);
		}
		
		intCombo = new JComboBox<String>(ComboArr);
		intCombo.setLocation(600, 530);
		intCombo.setSize(100, 50);
		intCombo.setVisible(false);
		intCombo.addActionListener(new ActionListener() {
			//start = false해야함!!
			int check = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jb = (JComboBox)e.getSource();
				int index = jb.getSelectedIndex();
				System.out.println(index);
				
				if(index<0) {
					warning.setText("구슬의 범위가 잘못되었습니다.");
					warning.setVisible(true);
					check++;
					//4번이상 잘못 입력시 턴 돌아감
				}
				else if(index>userScore) {
					warning.setText("구슬의 범위가 너무 큽니다.");
					warning.setVisible(true);
					check++;
					//4번이상 잘못 입력시 턴 돌아감
				}
				else {
					warning.setVisible(false);
					int computerOddEven = rand.nextInt(2);
					
					String rand_str;
					if(computerOddEven==0) {
						rand_str = "짝";
					}else {
						rand_str = "홀";
					}
					System.out.println(rand_str);
					ComputerSelectOddEven.setText(rand_str);
					
					if((index%2==0&&rand_str.equals("짝"))||(index%2==1&&rand_str.equals("홀"))) {
						System.out.println("당신의 구슬 개수 : " + index);
						System.out.println("457번이 " + rand_str + "을 맞췄습니다.");
						System.out.println("당신의 구슬이 " + index + "만큼 차감됩니다.");
						userScore-=index;
						computerScore+=index;
					}
					else {
						System.out.println("당신의 구슬 개수 : " + index);
						System.out.println("457번이 " + rand_str + "로, 틀렸습니다.");
						System.out.println("당신의 구슬이 " + index + "만큼 증가됩니다.");
						userScore+=index;
						computerScore-=index;
					}
					if(userScore<0) {
						userScore = 0;
					}else if(computerScore<0) {
						computerScore = 0;
					}
					
					scoreLabelUser.setText(userNumber + "번 : " + userScore + "개(나)");
					scoreLabelComp.setText("457번 : " + computerScore + "개");
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
		
		//--------------------------------수비 화면---------------------------------------------
		//컴퓨터가 고른 수
		ComputerSelectUI = new JLabel();
		ComputerSelectUI.setLocation(450, 200);
		ComputerSelectUI.setFont(ComputerSelectUI.getFont().deriveFont(120.0f));
		ComputerSelectUI.setSize(300, 100);
		ComputerSelectUI.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectUI.setVisible(false);
		
		//홀 버튼
		evenButton = new JButton(evenButtonDefault);
		evenButton.setBounds(700, 500, 300, 300);
		evenButton.setBorderPainted(false);
		evenButton.setContentAreaFilled(false);
		evenButton.setFocusPainted(false);
		evenButton.setVisible(false);
		
		//짝 버튼
		oddButton = new JButton(oddButtonDefault);
		oddButton.setBounds(200, 500, 300, 300);
		oddButton.setBorderPainted(false);
		oddButton.setContentAreaFilled(false);
		oddButton.setFocusPainted(false);
		oddButton.setVisible(false);
		
		//--------------------------------컨테이너 추가----------------------------------------
		//컨테이너에 추가(기본)
		c1.add(scoreLabelUser);
		c1.add(scoreLabelComp);
		c1.add(heartLabel);
		
		//컨테이너에 추가(초기)
		c1.add(HowmanyMarble);
		c1.add(MaxMarble);
		c1.add(marbleAmountUser);
		
		//컨테이너에 추가(공격)
		c1.add(ComputerSelectOddEven);
		c1.add(marbleAmountCheck);
		c1.add(intCombo);

		//컨테이너에 추가(수비)
		c1.add(ComputerSelectUI);
		c1.add(evenButton);
		c1.add(oddButton);
		
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		setVisible(true);
	}
}
