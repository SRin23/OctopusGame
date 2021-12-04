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
	final int BTN_WIDTH = 400;
	final int BTN_HEIGHT = 400;
	final int BTN_CNT = 3; //���������� ��ư�� ��

	JButton user_rps[] = new JButton[BTN_CNT];
	JButton master_rps;
	JLabel timerCnt;
	JLabel gameOver;
	JLabel score;
	Container c1 = getContentPane();

	String user_sel=""; //������ �� ��
	String master_sel=""; //com�� ����
	String r = "����";
	String p = "��";
	String s = "����";
	int chkWin = 0; //���������� �̱� Ƚ��
	int count = 0; //3�� ī��Ʈ
	int turn = 0; //�� �� ����
	boolean timering = false; //timer�� ���� ������ üũ
	boolean win;

	ImageIcon rock = new ImageIcon(rpsMain.class.getResource("../img/rock.png"));
	ImageIcon scissors = new ImageIcon(rpsMain.class.getResource("../img/scissors.png"));
	ImageIcon paper = new ImageIcon(rpsMain.class.getResource("../img/paper.png"));

	public rps(){
		setTitle("����������");
		//â�� ���� �� ���α׷� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//������ â ����
		setResizable(false);
		setSize(1200,900);
		//������(���� x �ִ� ��) ���̰� ����
		setVisible(true);
		c1.setBackground(Color.BLACK);

		//�� ���� ��ư ����
		master_rps = new JButton();
		master_rps.setBounds(500, 50,BTN_WIDTH/2,BTN_HEIGHT/2);
		master_rps.setBackground(Color.WHITE);
		master_rps.setBorderPainted(false);
		c1.add(master_rps);

		timerCnt = new JLabel("3");
		timerCnt.setBounds(600,350,150,50);
		timerCnt.setFont(timerCnt.getFont().deriveFont(50.0f));
		timerCnt.setVisible(true);
		timerCnt.setForeground(Color.WHITE);
		c1.add(timerCnt);

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

			user_rps[i].setBounds((i*400), 530,BTN_WIDTH,BTN_HEIGHT);
			user_rps[i].setBorderPainted(false);
			user_rps[i].setContentAreaFilled(false);
			user_rps[i].setFocusPainted(false);
			
			final int index = i;
			user_rps[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
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
				}
			});
			c1.add(user_rps[i]);
		}
		gameOver = new JLabel();
		gameOver.setText("GAME OVER");
		gameOver.setBounds(300,300,800,100);
		gameOver.setForeground(Color.WHITE);
		gameOver.setFont(gameOver.getFont().deriveFont(100.0f));
		gameOver.setVisible(false);
		c1.add(gameOver);

		score = new JLabel("����");
		score.setBounds(560,450,200,80);
		score.setFont(score.getFont().deriveFont(50.0f));
		score.setForeground(Color.WHITE);
		score.setVisible(false);
		c1.add(score);

		JLabel jl  = new JLabel();
		c1.add(jl);
	}

	//���� �������� �ϳ� ��
	private void chkMasterSel() {
		String rpsList[]= {"����","����","��"};
		master_sel=rpsList[new Random().nextInt(3)];
	}
	
	//�İ� ������ ������ ��
	private void chkUserMasterRps() {
		if(user_sel.equals(master_sel)) { //��� ���
			master_rps.setBackground(Color.YELLOW);
			turn--; //��ȿ
		}
		
		else if(master_sel.equals(s)) {//���� ����
			if(user_sel.equals(p)) //������ ��
				master_rps.setBackground(Color.RED);
			else if(user_sel.equals(r)) {//������ �ָ�
				chkWin++;
				master_rps.setBackground(Color.GREEN);
			}
		} 

		else if(master_sel.equals(r)){		 //���� ����
			if(user_sel.equals(s)) //������ ����
				master_rps.setBackground(Color.RED);
			
			else if(user_sel.equals(p)) {//������ ��
				chkWin++;
				master_rps.setBackground(Color.GREEN);	
			}
		}  

		else if(master_sel.equals(p)) {//���� ��
			if(user_sel.equals(r)) //������ ����
				master_rps.setBackground(Color.RED);
			
			else if(user_sel.equals(s)) {//������ ����
				chkWin++;
				master_rps.setBackground(Color.GREEN);
			}
		}
	}

	public boolean startGame(){
		timer();
		System.out.println(win);
		return win;
	}

	//3�� ���� ���� ���� ������
	private void timer() {
		turn++;
		chkMasterSel();

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
				
			@Override
			public void run() {
	
				if(count < 3) {
					timering=true;
					timerCnt.setVisible(true);
					timerCnt.setText(3-count+"");
					count++;
				}
					
				else {
					timer.cancel();
					timering = false;
					switch(master_sel){
						case "����" : 
							master_rps.setIcon(scissors);
							break;
						case "����" : 
							master_rps.setIcon(rock);
							break;
						case "��" :
							master_rps.setIcon(paper);
							break;
					}

					if(user_sel.equals("")) master_rps.setBackground(Color.RED);
					chkUserMasterRps();
					count = 0;
					timerCnt.setVisible(false);

					//3���� �� �� ����
					if(turn>=3) {
						if(chkWin>1) {
							score.setText("����");
							win=true;
						}
						else{
							score.setText("�İ�");
							win=false;
						}
						for(int i=0; i<BTN_CNT; i++)
							user_rps[i].setVisible(false);
						master_rps.setVisible(false);

						gameOver.setVisible(true);
						score.setVisible(true);

						return;
					}
				}
			}	
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
}