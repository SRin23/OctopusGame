package BeanOddEvenGame;

import java.awt.Color;
import java.awt.Container;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class marbleAttact extends JFrame{
	//기본 setting
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	private boolean start;
	private int heart;
	private int userNumber;
	
	private int marbleCount;	//초기 구슬 개수
	
	private int userScore;
	private int computerScore;
	
	Container c1 = getContentPane();
	
	public marbleAttact(boolean start, int heart, int userNumber, int marbleCount) {
		this.start = start;
		this.heart = heart;
		this.userNumber = userNumber;
		this.marbleCount = marbleCount;
		
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
		
		//컴퓨터가 고른 수
		JLabel ComputerSelectUI = new JLabel("홀");
		ComputerSelectUI.setLocation(450, 200);
		ComputerSelectUI.setFont(ComputerSelectUI.getFont().deriveFont(120.0f));
		ComputerSelectUI.setSize(300, 200);
		ComputerSelectUI.setHorizontalAlignment(JLabel.CENTER);
		
		//
		JLabel marbleAmountCheck = new JLabel("구슬 : ");
		marbleAmountCheck.setLocation(500, 500);
		marbleAmountCheck.setFont(marbleAmountCheck.getFont().deriveFont(30.0f));
		marbleAmountCheck.setSize(100, 100);
		
		String[] ComboArr = new String[marbleCount];
		for(int i = 0; i<=userScore; i++) {
			ComboArr[i] = Integer.toString(i);
		}
		
		JComboBox<String> intCombo = new JComboBox<String>(ComboArr);
		intCombo.setLocation(600, 530);
		intCombo.setSize(100, 50);
		
		//컨테이너에 추가
		c1.add(scoreLabelUser);
		c1.add(scoreLabelComp);
		c1.add(heartLabel);
		c1.add(ComputerSelectUI);
		c1.add(marbleAmountCheck);
		c1.add(intCombo);
		
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		setVisible(true);
	}
	
	
	
		
}
