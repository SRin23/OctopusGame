package RPS;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class rps {
	JFrame jf; //������
	final int BTN_CNT = 3; //���������� ��ư�� ��
	JButton user_rps[] = new JButton[BTN_CNT];
	JButton master_rps;
	final int BTN_WIDTH = 150;
	final int BTN_HEIGHT = 150;

	String user_sel=""; //������ �� ��
	String master_sel="";
	String r = "����";
	String p = "��";
	String s = "����";
	int chkWin;
	int count = 0;
	
	//��ư�� Ŭ���Ǹ� �ٸ� ��ư�� �⺻������ ���ư�
	private void chkColor(int index) {

		if(index == 0) {
			user_rps[1].setBackground(Color.WHITE);
			user_rps[2].setBackground(Color.WHITE);
		}
		else if(index == 1) {
			user_rps[0].setBackground(Color.WHITE);
			user_rps[2].setBackground(Color.WHITE);
		} 
		if(index == 2) {
			user_rps[0].setBackground(Color.WHITE);
			user_rps[1].setBackground(Color.WHITE);
		}
	}
	
	//���� �������� �ϳ� ��
	private void chkMasterSel() {
		Random rand = new Random();
		 
		int ran;  
		String rpsList[]= {"����","����","��"};
		 //������ �� ��(��ư �Է�)
		 //���� �� ��(����)
		
		ran = rand.nextInt(3);
		master_sel=rpsList[ran];
	}
	
	//�İ� ������ ������ ��
	private boolean chkUserMasterRps() {
		if(user_sel.equals(master_sel)) { //��� ���
			System.out.println("���");
		}
		
		else if(master_sel.equals(s)) {//���� ����
			if(user_sel.equals(p)) {//������ ��
				System.out.println("����");	
				return false;
			}
			if(user_sel.equals(r)) {//������ �ָ�
				System.out.println("�̰���");
				chkWin++;
				return true;
			} 
		} 
		else if(master_sel.equals(r)) {//���� ����
			if(user_sel.equals(p)) {//������ ��
				System.out.println("����");
				return false;
			}
			if(user_sel.equals(r)) {//������ ����
				System.out.println("�̰���");
				chkWin++;
				return true;
			} 
		}  
		else if(master_sel.equals(p)) {//���� ��
			if(user_sel.equals(r)) {//������ ����
				System.out.println("����");
				return false;
			}
			if(user_sel.equals(s)) {//������ ����
				System.out.println("�̰���");
				chkWin++;
				return true;
			} 
		}
		return true;
	}
	
	rps(){
		jf = new JFrame("����������");
		//â�� ���� �� ���α׷� ����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ â ����
		jf.setResizable(false);
		//ȭ�� ��� ��ġ
		//jf.setLocationRelativeTo(null);
		jf.setSize(1200,900);
		//������(���� x �ִ� ��) ���̰� ����
		jf.setVisible(true);
		
		for(int i=0; i<BTN_CNT; i++) {
			user_rps[i]=new JButton("");
			switch (i) {
			case 0: //����
				user_rps[i].setText(s);
				break;
			case 1: //����
				user_rps[i].setText(r);
				break;
			case 2: //��
				user_rps[i].setText(p);
				break;
			}
			user_rps[i].setSize(150,150);
			user_rps[i].setLocation(215+(i*300), 630);
			user_rps[i].setBackground(Color.WHITE);
			user_rps[i].setFont(user_rps[i].getFont().deriveFont(35.0f));
			
			final int index = i;
			user_rps[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
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
					chkMasterSel();
					user_rps[index].setBackground(Color.GREEN);
					chkColor(index);
					
					Timer timer = new Timer();
					TimerTask task = new TimerTask() {
						
						@Override
						public void run() {
							if(count<3) {
								
								System.out.println(3-count+"...");
								count++;
							}
							else {
								timer.cancel();
								//master_rps.setText(master_sel);
								System.out.println(master_sel);
								count =0;
							}
						}	
					};
					timer.schedule(task, 0, 1000);
				}
			});

			jf.getContentPane().add(user_rps[i]);
		}
		
		//�� ���� ��ư ����
		master_rps = new JButton("?");
		master_rps.setSize(150,150);
		master_rps.setLocation(515, 200);
		master_rps.setBackground(Color.WHITE);
		master_rps.setFont(master_rps.getFont().deriveFont(35.0f));
		//master_rps.setEnabled(false);
		master_rps.setForeground(Color.BLACK);
		
		jf.getContentPane().add(master_rps);
		
				
	}
}
