package RPS;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import marbleOddEven.MarbleGameMain;

public class rps {
	JFrame jf; //프레임
	final int BTN_CNT = 3; //가위바위보 버튼의 수
	JButton user_rps[] = new JButton[BTN_CNT];
	JButton master_rps;
	JLabel timerCnt = new JLabel();
	final int BTN_WIDTH = 150;
	final int BTN_HEIGHT = 150;

	String user_sel=""; //유저가 고른 거
	String master_sel=""; //com이 고른거
	String r = "바위";
	String p = "보";
	String s = "가위";
	int chkWin = 0; //가위바위보 이긴 횟수
	int count = 0; //3초 카운트
	int turn = 0; //턴 수 세기
	boolean timering=false; //timer가 실행 중인지 체크
	
	ImageIcon rock = new ImageIcon(MarbleGameMain.class.getResource("../img/rock.png"));
	ImageIcon scissors = new ImageIcon(MarbleGameMain.class.getResource("../img/scissors.png"));
	ImageIcon paper = new ImageIcon(MarbleGameMain.class.getResource("../img/paper.png"));
	
	//3초 세고 컴이 고른거 보여줌
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
					if(user_sel.equals("")) System.out.println("안 고름"); 
					timering = false;
					timer.cancel();
					//master_rps.setText(master_sel);
					System.out.println(master_sel+"m");
					System.out.println(user_sel+"u");
					count = 0;
					chkUserMasterRps();
					user_sel="";
				}
			}	
		};
		timer.schedule(task, 0, 1000);
	}
	
	//버튼이 클릭되면 다른 버튼은 기본색으로 돌아감
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
	
	//컴이 랜덤으로 하나 고름
	private void chkMasterSel() {
		Random rand = new Random();
		 
		int ran;  
		String rpsList[]= {"가위","바위","보"};
		 //유저가 고른 것(버튼 입력)
		 //컴이 고른 것(랜덤)
		
		ran = rand.nextInt(3);
		master_sel=rpsList[ran];
	}
	
	//컴과 유저의 선택을 비교
	private void chkUserMasterRps() {
		if(user_sel.equals(master_sel)) { //비긴 경우
			System.out.println("비김");
			for(int i=0; i<BTN_CNT; i++) 
				user_rps[i].setBackground(Color.YELLOW);
			master_rps.setBackground(Color.YELLOW);
		}
		
		else if(master_sel.equals(s)) {//컴이 가위
			if(user_sel.equals(p)) {//유저가 보
				System.out.println("졌음");	
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.RED);
				master_rps.setBackground(Color.RED);
			}
			if(user_sel.equals(r)) {//유저가 주먹
				System.out.println("이겼음");
				chkWin++;
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.GREEN);
				master_rps.setBackground(Color.GREEN);
			} 
		} 
		else if(master_sel.equals(r)) {//컴이 바위
			if(user_sel.equals(p)) {//유저가 보
				System.out.println("졌음");
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.RED);
				master_rps.setBackground(Color.RED);
			}
			if(user_sel.equals(r)) {//유저가 바위
				System.out.println("이겼음");
				chkWin++;
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.GREEN);
				master_rps.setBackground(Color.GREEN);
			} 
		}  
		else if(master_sel.equals(p)) {//컴이 보
			if(user_sel.equals(r)) {//유저가 바위
				System.out.println("졌음");
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.RED);
				master_rps.setBackground(Color.RED);
			}
			if(user_sel.equals(s)) {//유저가 바위
				System.out.println("이겼음");
				for(int i=0; i<BTN_CNT; i++) 
					user_rps[i].setBackground(Color.GREEN);
				master_rps.setBackground(Color.GREEN);
				chkWin++;
			} 
		}
	}
	
	rps(){
		jf = new JFrame("가위바위보");
		//창을 닫을 시 프로그램 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		jf.setResizable(false);
		//화면 가운데 배치
		//jf.setLocationRelativeTo(null);
		jf.setSize(1200,900);
		//프레임(위에 x 있는 거) 보이게 설정
		jf.setVisible(true);
		
		chkMasterSel();
		timer();
		
		//timerCnt = new JLabel();
		timerCnt.setSize(100, 50);
		timerCnt.setLocation(0, 0);
		
		
		//jf.getContentPane().add(timerCnt);
		
		for(int i=0; i<BTN_CNT; i++) {
			switch (i) {
			case 0: //가위
				user_rps[i]=new JButton(scissors);
				user_rps[i].setText(s);
				break;
			case 1: //바위
				user_rps[i]=new JButton(rock);
				user_rps[i].setText(r);
				break;
			case 2: //보
				user_rps[i]=new JButton(paper);
				user_rps[i].setText(p);
				break;
			}
			user_rps[i].setSize(150,150);
			user_rps[i].setLocation(215+(i*300), 630);
			user_rps[i].setBackground(Color.WHITE);
			user_rps[i].setFont(user_rps[i].getFont().deriveFont(35.0f));
		
			//user_rps[i].setBorderPainted(false);
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

			jf.getContentPane().add(user_rps[i]);
		}
		
		//컴 선택 버튼 세팅
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
