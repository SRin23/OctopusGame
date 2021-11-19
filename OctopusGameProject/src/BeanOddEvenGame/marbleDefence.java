package BeanOddEvenGame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.*;

public class marbleDefence extends JFrame{
	private boolean start;
	private int heart;
	private int userNumber;
	
	private int userScore;
	private int computerScore;
	
	private ImageIcon evenButtonDefault = new ImageIcon(MarbleGame.class.getResource("../img/evenButton.png"));
	private ImageIcon evenButtonEntered = new ImageIcon(MarbleGame.class.getResource("../img/evenButtonEntered.png"));
	private ImageIcon oddButtonDefault = new ImageIcon(MarbleGame.class.getResource("../img/oddButton.png"));
	private ImageIcon oddButtonEntered = new ImageIcon(MarbleGame.class.getResource("../img/oddButtonEntered.png"));
	
	Container c1 = getContentPane();
	
	marbleDefence(boolean start, int heart, int userNumber){
		this.start = start;
		this.heart = heart;
		this.userNumber = userNumber;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ȧ¦����");
		c1.setLayout(null);
		c1.setBackground(Color.lightGray);
		
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
		JLabel ComputerSelectUI = new JLabel("5");
		ComputerSelectUI.setLocation(450, 200);
		ComputerSelectUI.setFont(ComputerSelectUI.getFont().deriveFont(120.0f));
		ComputerSelectUI.setSize(300, 100);
		ComputerSelectUI.setHorizontalAlignment(JLabel.CENTER);
		
		//Ȧ ��ư
		JButton evenButton = new JButton(evenButtonDefault);
		evenButton.setBounds(700, 500, 300, 300);
		evenButton.setBorderPainted(false);
		evenButton.setContentAreaFilled(false);
		evenButton.setFocusPainted(false);
		
		//¦ ��ư
		JButton oddButton = new JButton(oddButtonDefault);
		oddButton.setBounds(200, 500, 300, 300);
		oddButton.setBorderPainted(false);
		oddButton.setContentAreaFilled(false);
		oddButton.setFocusPainted(false);
		
		//�����̳ʿ� �߰�
		c1.add(scoreLabelUser);
		c1.add(scoreLabelComp);
		c1.add(heartLabel);
		c1.add(ComputerSelectUI);
		c1.add(evenButton);
		c1.add(oddButton);

		
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		setVisible(true);
	}
}
