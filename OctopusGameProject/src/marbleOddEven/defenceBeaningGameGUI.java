//����
package BeanOddEvenGame;

import java.util.Random;
import java.util.Scanner;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class defenceBeaningGameGUI extends JFrame{
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	//----------------�ý��� ���� ����------------------	
	int marble;	//����� ������ ����
	int computerScore;	//��ǻ���� ������ ����
	int userScore;	//������� ������ ����
	int userNumber;
	boolean start;
	int heart;
	
	//----------------GUI ���� ����------------------	
	private static final long serialVersionUID = -1628752121408948294L;
	
	//Ȧ, ¦ Click ����
	boolean oddEven;
	
	//���ڿ� �߰�
	JLabel vsLabel = new JLabel(); //�ؽ�Ʈ�� ������ JLabel ����
	//JLabel heartLabel = new JLabel(); //�ؽ�Ʈ�� ������ JLabel ����
	
	//��ư 2�� ����
	JButton user_odd = new JButton("Ȧ");
	JButton user_even = new JButton("¦");
	
	void beaningFrame(int userNo, int heart){
		this.userNumber = userNo;
		this.heart = heart;
		//----------------Frame����-----------------
		 
		//â�� ���� ����
		setTitle("�޲ٹ̳���(Ȧ¦����)");
		//â ������(ũ��) ����
		setSize(1200, 900);
		//����ڰ� â ũ�� �ٲ��� ���ϰ���
		setResizable(false);	
		//�����, â�� ȭ�� �� �߾ӿ� ���.
		setLocationRelativeTo(null);
		//â�� ���� �� ���α׷� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//â ���̰� ����
		setVisible(true);
		//��ġ������ ����
		setLayout(null);
		 
		//----------------Label ����-----------------
		//text���� �� ��ġ, ũ��
		vsLabel.setText(userNumber + "��(��) VS 457��");
		vsLabel.setBounds(10,10,500,30);
		//heartLabel.setText("��� : " + heart);
		//heartLabel.setBounds(900,10,500,30);
		
		//label ���� ũ�� ����
		vsLabel.setFont(vsLabel.getFont().deriveFont (30.0f));
		//heartLabel.setFont(vsLabel.getFont().deriveFont (30.0f));

		//----------------Button ����-----------------
		//Ȧ¦ ��ư ũ�� �� ��ġ ����
		user_odd.setBounds(300, 630, 150, 150);
		user_even.setBounds(750, 630, 150, 150);
		user_odd.setFont(user_odd.getFont().deriveFont(30.0f));
		user_even.setFont(user_even.getFont().deriveFont(30.0f));
		 
		//��ư ���� ����
		user_odd.setBackground(Color.white);
		user_even.setBackground(Color.white);
		
		//Ȧ�� UI�� ���콺 �������� ������ ������
		//user_odd.setToolTipText("1, 3, 5, 7...");
		
		
		//��ư Listener ����
		user_even.addActionListener(evenBtnListener);
		user_odd.addActionListener(oddBtnListener);
		
		//----------------��ҵ� â�� �߰�-----------------
		add(vsLabel);
		//add(heartLabel);
		add(user_odd);
		add(user_even);
	}
	//Ȧ����ư -> true, ¦����ư -> false
	ActionListener oddBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			oddEven = true;
			System.out.println(oddEven + " / Ȧ���� Ŭ���ϼ̽��ϴ�.");
		}
	};
	
	ActionListener evenBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			oddEven = false;
			System.out.println(oddEven + " / ¦���� Ŭ���ϼ̽��ϴ�.");
		}
	};
}
