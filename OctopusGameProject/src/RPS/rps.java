package RPS;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class rps extends JFrame{
	final int BTN_CNT = 3; //���������� ��ư�� ��
	JButton user_rps[] = new JButton[BTN_CNT];
	JButton master_rps;
	JLabel timerCnt = new JLabel();
	Container c1 = getContentPane();
	
	final int BTN_WIDTH = 150;
	final int BTN_HEIGHT = 150;

	String user_sel=""; //������ �� ��
	String master_sel=""; //com�� ����
	String r = "����";
	String p = "��";
	String s = "����";
	int chkWin = 0; //���������� �̱� Ƚ��
	int count = 0; //3�� ī��Ʈ
	int turn = 0; //�� �� ����
	boolean timering = false; //timer�� ���� ������ üũ
	
	ImageIcon rock = new ImageIcon(rps_test.class.getResource("../img/rock.png"));
	ImageIcon scissors = new ImageIcon(rps_test.class.getResource("../img/scissors.png"));
	ImageIcon paper = new ImageIcon(rps_test.class.getResource("../img/paper.png"));
	
	//3�� ���� ���� ���� ������
	private void timer() {
		turn++;

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {

				if(count < 3) {
					timering=true;
					System.out.println(3-count+"...");
					timerCnt.setText(3-count+"...");
					count++;
				}
				
				else {
					if(user_sel.equals("")) System.out.println("�� ��"); 
					timering = false;
					timer.cancel();
					count = 0;
					chkUserMasterRps();
					user_sel="";
					
					//3���� �� �� ����
					if(turn>=3) {
						if(chkWin>=2) System.out.println("����");
						else System.out.println("�İ�");
						for(int i=0; i<BTN_CNT; i++)
							user_rps[i].setEnabled(false);
						master_rps.setEnabled(false);
						return;
					}
				}
			}	
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
	//��ư�� Ŭ���Ǹ� �ٸ� ��ư�� �⺻������ ���ư�
	private void chkColor(int index) {

		switch (index) {
		case 0:
			user_rps[1].setBackground(Color.WHITE);
			user_rps[2].setBackground(Color.WHITE);
			break;
		case 1:
			user_rps[0].setBackground(Color.WHITE);
			user_rps[2].setBackground(Color.WHITE);
			break;
		case 2:
			user_rps[0].setBackground(Color.WHITE);
			user_rps[1].setBackground(Color.WHITE);
			break;
		}
	}
	
	//���� �������� �ϳ� ��
	private void chkMasterSel() {
		Random rand = new Random();
		 
		int ran;  
		String rpsList[]= {"����","����","��"};
		ran = rand.nextInt(3);
		master_sel=rpsList[ran];
	}
	
	//�İ� ������ ������ ��
	private void chkUserMasterRps() {
		if(user_sel.equals(master_sel)) { //��� ���
			System.out.println("���");
			for(int i=0; i<BTN_CNT; i++) 
				user_rps[i].setBackground(Color.YELLOW);
			master_rps.setBackground(Color.YELLOW);
		}
		
		else if(master_sel.equals(s)) {//���� ����
			if(user_sel.equals(p)) {//������ ��
				System.out.println("����");	
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.RED);
				master_rps.setBackground(Color.RED);
			}
			if(user_sel.equals(r)) {//������ �ָ�
				System.out.println("�̰���");
				chkWin++;
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.GREEN);
				master_rps.setBackground(Color.GREEN);
			} 
		} 
		else if(master_sel.equals(r)) {//���� ����
			if(user_sel.equals(p)) {//������ ��
				System.out.println("����");
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.RED);
				master_rps.setBackground(Color.RED);
			}
			if(user_sel.equals(r)) {//������ ����
				System.out.println("�̰���");
				chkWin++;
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.GREEN);
				master_rps.setBackground(Color.GREEN);
			} 
		}  
		else if(master_sel.equals(p)) {//���� ��
			if(user_sel.equals(r)) {//������ ����
				System.out.println("����");
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.RED);
				master_rps.setBackground(Color.RED);
			}
			if(user_sel.equals(s)) {//������ ����
				System.out.println("�̰���");
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.GREEN);
				master_rps.setBackground(Color.GREEN);
				chkWin++;
			} 
		}
	}
	
	rps(){
		
		setTitle("����������");
		//â�� ���� �� ���α׷� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//������ â ����
		setResizable(false);
		setSize(1200,900);
		//������(���� x �ִ� ��) ���̰� ����
		setVisible(true);
		
		chkMasterSel();
		timer();
		
		
		for(int i=0; i<BTN_CNT; i++) {
			switch (i) {
			case 0: //����
				user_rps[i]=new JButton(scissors);
				break;
			case 1: //����
				user_rps[i]=new JButton(rock);
				break;
			case 2: //��
				user_rps[i]=new JButton(paper);
				break;
			}
			user_rps[i].setSize(150,150);
			user_rps[i].setLocation(215+(i*300), 630);
			user_rps[i].setBackground(Color.WHITE);
			user_rps[i].setFont(user_rps[i].getFont().deriveFont(35.0f));
		
			user_rps[i].setContentAreaFilled(false);
			user_rps[i].setFocusPainted(false);
			
			final int index = i;
			user_rps[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					master_rps.setBackground(Color.WHITE);
					if(!timering) timer();
					switch(index) {
						case 0:
							user_sel = s;
							break;
						case 1:
							user_sel = r;
							break;
						case 2:
							user_sel = p;
							break;
					}

					user_rps[index].setBackground(Color.BLUE);
					chkColor(index);
				}
			});

			timerCnt.setLocation(0, 0);
			c1.add(timerCnt);
			c1.add(user_rps[i]);
		}
		
		//�� ���� ��ư ����
		master_rps = new JButton();
		master_rps.setSize(150,150);
		master_rps.setLocation(515, 200);
		master_rps.setBackground(Color.WHITE);
		master_rps.setFont(master_rps.getFont().deriveFont(35.0f));
		master_rps.setForeground(Color.BLACK);
		
		c1.add(master_rps);

	}
}