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
	
	//기본 setting
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
		setTitle("홀짝게임");
		c1.setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		c1.setBackground(Color.white);
		
		//--------------기본 Layout---------------------
		
		//사용자 점수
		scoreLabelUser = new JLabel(userNumber + "번 : " + userScore + "개(나)");
		scoreLabelUser.setLocation(10, 10);
		scoreLabelUser.setSize(200, 20);
		scoreLabelUser.setFont(scoreLabelUser.getFont().deriveFont(20.0f));
		
		//컴퓨터 점수
		scoreLabelComp = new JLabel("457번 : " + computerScore + "개");
		scoreLabelComp.setLocation(10, 30);
		scoreLabelComp.setFont(scoreLabelComp.getFont().deriveFont(20.0f));
		scoreLabelComp.setSize(200, 20);
		
		//목숨
		JLabel heartLabel = new JLabel("목숨 : " + heart + "개");
		heartLabel.setLocation(1080, 10);
		heartLabel.setFont(heartLabel.getFont().deriveFont(20.0f));
		heartLabel.setSize(120, 20);
		
		//컨테이너에 추가(기본)
		c1.add(scoreLabelUser);
		c1.add(scoreLabelComp);
		c1.add(heartLabel);
		
		basic();
		
		setVisible(true);
	}
	
	void basic() {
		//--------------------------------초기 화면---------------------------------------------
		JLabel HowmanyMarble = new JLabel("몇개의 구슬을 사용하시겠습니까?");
		HowmanyMarble.setLocation(350, 200);
		HowmanyMarble.setFont(HowmanyMarble.getFont().deriveFont(30.0f));
		HowmanyMarble.setSize(500, 50);
		HowmanyMarble.setHorizontalAlignment(JLabel.CENTER);
		HowmanyMarble.setVisible(true);
		
		JLabel MaxMarble = new JLabel("(최대 구슬 개수 : 50개)");
		MaxMarble.setLocation(500, 250);
		MaxMarble.setFont(MaxMarble.getFont().deriveFont(18.0f));
		MaxMarble.setSize(200, 50);
		MaxMarble.setVisible(true);
		
		JTextField marbleAmountUser = new JTextField(5);
		TitledBorder oneTb = new TitledBorder(new LineBorder(Color.black), "입력");
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
				System.out.println("전체 구슬 개수 : " + marbleCount);
				if(marbleCount>50||marbleCount<10) HowmanyMarble.setText("다시입력해주세요.");
				else {
					HowmanyMarble.setText("입력이 완료되었습니다.");
					computerScore = marbleCount;
					userScore = marbleCount;
					System.out.println("컴퓨터 구슬 개수 : " + computerScore);
					System.out.println("사용자 구슬 개수 : " + userScore);
					scoreLabelUser.setText(userNumber + "번 : " + userScore + "개(나)");
					scoreLabelComp.setText("457번 : " + computerScore + "개");
					HowmanyMarble.setVisible(false);
					MaxMarble.setVisible(false);
					marbleAmountUser.setVisible(false);
					
					if(start == false) {	//수비(홀짝)
						defence();
					}else {	//공격(구슬쥐기)
						attact();
					}
					
				}
			}
		});
		//컨테이너에 추가(초기)
		c1.add(HowmanyMarble);
		c1.add(MaxMarble);
		c1.add(marbleAmountUser);
		
	}
	
	void attact() {
		//컴퓨터가 고른 수
		TitledBorder twoTb = new TitledBorder(new LineBorder(Color.black), "457번 구슬");
		ComputerSelectOddEven = new JLabel();
		ComputerSelectOddEven.setLocation(450, 200);
		ComputerSelectOddEven.setFont(ComputerSelectOddEven.getFont().deriveFont(120.0f));
		ComputerSelectOddEven.setSize(300, 200);
		ComputerSelectOddEven.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectOddEven.setVisible(true);
		ComputerSelectOddEven.setBorder(twoTb);
		
		//구슬 라벨
		marbleAmountCheck = new JLabel("구슬 : ");
		marbleAmountCheck.setLocation(500, 500);
		marbleAmountCheck.setFont(marbleAmountCheck.getFont().deriveFont(30.0f));
		marbleAmountCheck.setSize(100, 100);
		marbleAmountCheck.setVisible(true);
		
		//경고창
		warning = new JLabel();
		warning.setLocation(400, 300);
		warning.setSize(400, 100);
		warning.setFont(warning.getFont().deriveFont(20.0f));
		warning.setHorizontalAlignment(JLabel.CENTER);
		warning.setForeground(Color.red);
		warning.setVisible(false);
		
		//콤보박스(구슬 개수 고르는)
		String[] ComboArr = new String[userScore+1];
		for(int i = 0; i<=userScore; i++) {
			ComboArr[i] = Integer.toString(i);
		}
		System.out.println("구슬 개수 : " + userScore + ", " + ComboArr.length);
		
		intCombo = new JComboBox<String>(ComboArr);
		intCombo.setLocation(600, 530);
		intCombo.setFont(intCombo.getFont().deriveFont(30.0f));
		intCombo.setSize(100, 50);
		intCombo.setVisible(true);
		intCombo.addActionListener(new ActionListener() {
			//start = false해야함!!
			int check = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				start = false;
				JComboBox<String> jb = (JComboBox)e.getSource();
				int index = jb.getSelectedIndex();
				System.out.println("인덱스 : " + index);
				if(index<0) {
					warning.setText("구슬의 범위가 잘못되었습니다.");
					warning.setVisible(true);
					check++;
					System.out.println("확인  : " + check);
				}
				else if(index>userScore) {
					warning.setText("구슬의 범위가 너무 큽니다.");
					warning.setVisible(true);
					System.out.println("범위가 큼");
					check++;
					System.out.println("확인  : " + check);
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
					System.out.println("컴퓨터값 : " + computerOddEven);
					System.out.println("컴퓨터값 문자열 : " + rand_str);

					ComputerSelectOddEven.setText(rand_str + "");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
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
					
					if(check>=3) {
						warning.setText("3번 틀렸습니다. 다음턴으로 넘어갑니다.");
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
					
					scoreLabelUser.setText(userNumber + "번 : " + userScore + "개(나)");
					scoreLabelComp.setText("457번 : " + computerScore + "개");
					
					
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
		//컨테이너에 추가(공격)
		c1.add(ComputerSelectOddEven);
		c1.add(marbleAmountCheck);
		c1.add(intCombo);
	}
	
	
	void defence() {
		//컴퓨터가 고른 수
		ComputerSelectUI = new JLabel("수비");
		ComputerSelectUI.setLocation(450, 200);
		ComputerSelectUI.setFont(ComputerSelectUI.getFont().deriveFont(120.0f));
		ComputerSelectUI.setSize(300, 100);
		ComputerSelectUI.setHorizontalAlignment(JLabel.CENTER);
		ComputerSelectUI.setVisible(true);
		
		//홀 버튼
		evenButton = new JButton(evenButtonDefault);
		evenButton.setBounds(700, 500, 300, 300);
		evenButton.setBorderPainted(false);
		evenButton.setContentAreaFilled(false);
		evenButton.setFocusPainted(false);
		evenButton.setVisible(true);
		evenButton.addMouseListener(new MouseAdapter(){
			//버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				//커서모양 손가락
				evenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				evenButton.setIcon(evenButtonEntered);
			}
			//기본
			@Override
			public void mouseExited(MouseEvent e) {
				evenButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				evenButton.setIcon(evenButtonDefault);
			}
			//버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				start = true;
				
				int computerSelect = rand.nextInt(computerScore+1);
				//System.out.println("컴퓨터가 고른 구슬의 수 : " + computerSelect);
				ComputerSelectUI.setText(computerSelect + "개");
				
				if(computerSelect%2==0) {
					userScore += computerSelect;
					computerScore -= computerSelect;
				}else {
					userScore -= computerSelect;
					computerScore += computerSelect;
				}
				
				check();
				
				scoreLabelUser.setText(userNumber + "번 : " + userScore + "개(나)");
				scoreLabelComp.setText("457번 : " + computerScore + "개");
				System.out.println(start);
				if(start == true&&endCheck!=true) {
					ComputerSelectUI.setVisible(false);
					evenButton.setVisible(false);
					oddButton.setVisible(false);
					
					attact();
				}
			}
		});
		
		//짝 버튼
		oddButton = new JButton(oddButtonDefault);
		oddButton.setBounds(200, 500, 300, 300);
		oddButton.setBorderPainted(false);
		oddButton.setContentAreaFilled(false);
		oddButton.setFocusPainted(false);
		oddButton.setVisible(true);
		oddButton.addMouseListener(new MouseAdapter(){
			//버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				//커서모양 손가락
				oddButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				oddButton.setIcon(oddButtonEntered);
			}
			//기본
			@Override
			public void mouseExited(MouseEvent e) {
				oddButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				oddButton.setIcon(oddButtonDefault);
			}
			//버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				start = true;
				int computerSelect = rand.nextInt(computerScore+1);
				System.out.println("컴퓨터가 고른 구슬의 수 : " + computerSelect);
				ComputerSelectUI.setText(computerSelect + "개");
				
				if(computerSelect%2==1) {
					userScore += computerSelect;
					computerScore -= computerSelect;
					
				}else {
					userScore -= computerSelect;
					computerScore += computerSelect;
					
				}
				
				check();
				
				System.out.println("| 현재 구슬의 개수  | " + userNumber + "번(나) : " + userScore + "개 | 457번 : " + computerScore + "개 | ");
				scoreLabelUser.setText(userNumber + "번 : " + userScore + "개(나)");
				scoreLabelComp.setText("457번 : " + computerScore + "개");
				
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
		
		//컨테이너에 추가(수비)
		c1.add(ComputerSelectUI);
		c1.add(evenButton);
		c1.add(oddButton);
		
	}
	
	int end() {
		endCheck = true;
		System.out.println("start : " + start);
		if(start == false) {
			System.out.println("수비 없앰");
			ComputerSelectOddEven.setVisible(false);
			marbleAmountCheck.setVisible(false);
			warning.setVisible(false);
			intCombo.setVisible(false);
			
			
		}else {
			System.out.println("공격 없앰");
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
		
		
		
		System.out.println("end 도착");
		System.out.println("유저 점수 : " + userScore);
		System.out.println("컴퓨터 점수 : " + computerScore);
		
		if(userScore == 0) {
			resultText.setText("졌습니다.");
			return heart-1;
		}else {
			resultText.setText("이겼습니다.");
			return heart;
		}
		
	}
	
	void check() {
		if(userScore<=0) {
			System.out.println("유저는 0");
			userScore = 0;
			computerScore = marbleCount*2;
			end();
		}else if(computerScore<=0) {
			System.out.println("컴퓨터는 0");
			computerScore = 0;
			userScore = marbleCount*2;
			end();
		}
	}
}
