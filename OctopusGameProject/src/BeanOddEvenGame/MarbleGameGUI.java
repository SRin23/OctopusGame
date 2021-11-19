package BeanOddEvenGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class MarbleGameGUI extends JFrame{
	
	//�⺻ setting
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	//������� -> private

	// image�� ���� ����
	private Image background = new ImageIcon(MarbleGame.class.getResource("../img/marbleBackground.jpg")).getImage();
		
	//Ȧ, ¦
	private final int odd = 1;
	private final int even = 0;
	
	//�ʱ� ���� setting
	private boolean start;
	private int heart;
	private int userNumber;
	private int computerScore;	//��ǻ���� ������ ����
	private int userScore;	//������� ������ ����
	int computerSelect;

	//�ʱ� ���� setting ����
	private JLabel explain = new JLabel();
	private JTextField setFirstMarble = new JTextField("���������� �Է��ϼ���.", 12);
	
	//����(Ȧ, ¦) ����
	private ImageIcon evenButtonDefault = new ImageIcon(MarbleGame.class.getResource("../img/evenButton.png"));
	private ImageIcon evenButtonEntered = new ImageIcon(MarbleGame.class.getResource("../img/evenButtonEntered.png"));
	private ImageIcon oddButtonDefault = new ImageIcon(MarbleGame.class.getResource("../img/oddButton.png"));
	private ImageIcon oddButtonEntered = new ImageIcon(MarbleGame.class.getResource("../img/oddButtonEntered.png"));
	private JButton evenButton = new JButton(evenButtonDefault);
	private JButton oddButton = new JButton(oddButtonDefault);
	
	private JLabel Score = new JLabel();
	private JLabel HeartLabel = new JLabel("��� : " + heart);
	
	//����(�������) ����
	
	
	MarbleGameGUI(boolean start, int heart, int userNumber){
		this.start = start;
		this.heart = heart;
		this.userNumber = userNumber;
		//----------------Frame����-----------------
		//setUndecorated(true);
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
		//���� ����
		setBackground(Color.darkGray);
		//��ġ������ ����
		setLayout(null);
		
		
		//--------------- Defence ��ư ---------------
		//Ȧ ��ư
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
				System.out.println("Ȧ�Դϴ�.");
				if(computerSelect%2==1) {
					userScore += computerSelect;
					computerScore -= computerSelect;
				}else {
					userScore -= computerSelect;
					computerScore += computerSelect;
				}
				System.out.println("| ���� ������ ����  | " + userNumber + "��(��) : " + userScore + "�� | 457�� : " + computerScore + "�� | ");
			}
		});
		add(oddButton);
		
		//¦ ��ư
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
				System.out.println("¦�Դϴ�.");
				if(computerSelect%2==0) {
					userScore += computerSelect;
					computerScore -= computerSelect;
				}else {
					userScore -= computerSelect;
					computerScore += computerSelect;
				}
				System.out.println("| ���� ������ ����  | " + userNumber + "��(��) : " + userScore + "�� | 457�� : " + computerScore + "�� | ");
			}
		});
		add(evenButton);
		
		//----------------Label---------------
		Score.setBounds(0, 20, 100, 50);
		Score.setText(userNumber + "(��) : " + userScore + "��\n" + 457 + " : " + computerScore + "��");
		add(Score);
	}
	
	void marbleSetting() {
		int marble;	//����� ������ ����
		//��� ������ ���� ����(�ִ� ���� ��� ���� 50��)
		while(true) {
			System.out.print("��� ������ ����Ͻðڽ��ϱ�?(�ִ� ���� ���� : 50��) -> ");
			marble = sc.nextInt();
			if(marble>50) {
				System.out.println("�ִ� ���� ��� ������ �Ѿ����ϴ�. �ٽ� �Է��� �ֽʽÿ�.");
				System.out.println();
				continue;
			}
			break;
		}
		
		//�����&��ǻ�� ���� reset
		computerScore = marble;
		userScore = marble;
	}
	// ���� ó�� ȭ���� �׷���(��� �׸���)
	//public void paint(Graphics g) {
		// screenImage�� 0, 0��ġ�� �׷���
		//g.drawImage(background, 0, 100, null);
	//}

}
