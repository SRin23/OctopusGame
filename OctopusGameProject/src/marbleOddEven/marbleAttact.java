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
	//�⺻ setting
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	private boolean start;
	private int heart;
	private int userNumber;
	
	private int marbleCount;	//�ʱ� ���� ����
	
	private int userScore;
	private int computerScore;
	
	Container c1 = getContentPane();
	
	public marbleAttact(boolean start, int heart, int userNumber, int marbleCount) {
		this.start = start;
		this.heart = heart;
		this.userNumber = userNumber;
		this.marbleCount = marbleCount;
		
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
		
		//��ǻ�Ͱ� �� ��
		JLabel ComputerSelectUI = new JLabel("Ȧ");
		ComputerSelectUI.setLocation(450, 200);
		ComputerSelectUI.setFont(ComputerSelectUI.getFont().deriveFont(120.0f));
		ComputerSelectUI.setSize(300, 200);
		ComputerSelectUI.setHorizontalAlignment(JLabel.CENTER);
		
		//
		JLabel marbleAmountCheck = new JLabel("���� : ");
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
		
		//�����̳ʿ� �߰�
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
