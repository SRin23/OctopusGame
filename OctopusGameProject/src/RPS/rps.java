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
	JFrame jf; //프레임
	final int BTN_CNT = 3; //가위바위보 버튼의 수
	JButton user_rps[] = new JButton[BTN_CNT];
	JButton master_rps;
	final int BTN_WIDTH = 150;
	final int BTN_HEIGHT = 150;

	String user_sel=""; //유저가 고른 거
	String master_sel="";
	String r = "바위";
	String p = "보";
	String s = "가위";
	int chkWin;
	int count = 0;
	
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
	private boolean chkUserMasterRps() {
		if(user_sel.equals(master_sel)) { //비긴 경우
			System.out.println("비김");
		}
		
		else if(master_sel.equals(s)) {//컴이 가위
			if(user_sel.equals(p)) {//유저가 보
				System.out.println("졌음");	
				return false;
			}
			if(user_sel.equals(r)) {//유저가 주먹
				System.out.println("이겼음");
				chkWin++;
				return true;
			} 
		} 
		else if(master_sel.equals(r)) {//컴이 바위
			if(user_sel.equals(p)) {//유저가 보
				System.out.println("졌음");
				return false;
			}
			if(user_sel.equals(r)) {//유저가 바위
				System.out.println("이겼음");
				chkWin++;
				return true;
			} 
		}  
		else if(master_sel.equals(p)) {//컴이 보
			if(user_sel.equals(r)) {//유저가 바위
				System.out.println("졌음");
				return false;
			}
			if(user_sel.equals(s)) {//유저가 바위
				System.out.println("이겼음");
				chkWin++;
				return true;
			} 
		}
		return true;
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
		
		for(int i=0; i<BTN_CNT; i++) {
			user_rps[i]=new JButton("");
			switch (i) {
			case 0: //가위
				user_rps[i].setText(s);
				break;
			case 1: //바위
				user_rps[i].setText(r);
				break;
			case 2: //보
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
