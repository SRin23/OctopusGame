package RSP;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import miniGame.connectAll;

public class rspGame extends connectAll{
	final int BTN_WIDTH = 400;
	final int BTN_HEIGHT = 400;
	final int BTN_CNT = 3; //가위바위보 버튼의 수
	
	JFrame jf;
	JButton user_rps[] = new JButton[BTN_CNT];
	JButton master_rps;
	JLabel timerCnt;
	JLabel gameOver;
	JLabel score;
	

	String user_sel=""; //유저가 고른 거
	String master_sel=""; //com이 고른거
	String r = "바위";
	String p = "보";
	String s = "가위";
	int chkWin = 0; //가위바위보 이긴 횟수
	int count = 0; //3초 카운트
	int turn = 0; //턴 수 세기
	boolean timering = false; //timer가 실행 중인지 체크
	boolean win;
	private Image back= new ImageIcon(rspGame.class.getResource("../img/rspBack.png")).getImage();
	ImageIcon rock = new ImageIcon(rspGame.class.getResource("../img/rock.png"));
	ImageIcon scissors = new ImageIcon(rspGame.class.getResource("../img/scissors.png"));
	ImageIcon paper = new ImageIcon(rspGame.class.getResource("../img/paper.png"));
	
	public rspGame(String playerNo){
		super(playerNo);
		panJf.dispose();
		jf = new JFrame();
		Container c1 = jf.getContentPane();
		
		jf.setTitle("가위바위보");
		//창을 닫을 시 프로그램 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		jf.setResizable(false);
		jf.setSize(1200,900);
		//프레임(위에 x 있는 거) 보이게 설정
		jf.setVisible(true);
		
		JLabel heartLabel = new JLabel("목숨 : " + heart + "개");
		heartLabel.setLocation(1080, 10);
		heartLabel.setFont(heartLabel.getFont().deriveFont(20.0f));
		heartLabel.setSize(120, 20);
		c1.add(heartLabel);
		//컴 선택 버튼 세팅
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
			case 0: //가위
				user_rps[i]=new JButton(scissors);
				break;
			case 1: //바위
				user_rps[i]=new JButton(rock);
				break;
			case 2: //보
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

		score = new JLabel();
		score.setBounds(560,450,200,80);
		score.setFont(score.getFont().deriveFont(50.0f));
		score.setForeground(Color.WHITE);
		score.setVisible(false);
		c1.add(score);

		JLabel jl  = new JLabel();
		c1.add(jl);
	}

	//컴이 랜덤으로 하나 고름
	private void chkMasterSel() {
		String rpsList[]= {"가위","바위","보"};
		master_sel=rpsList[new Random().nextInt(3)];
	}
	
	//컴과 유저의 선택을 비교
	private void chkUserMasterRps() {
		if(user_sel.equals(master_sel)) { //비긴 경우
			master_rps.setBackground(Color.YELLOW);
			turn--; //무효
		}
		
		else if(master_sel.equals(s)) {//컴이 가위
			if(user_sel.equals(p)) //유저가 보
				master_rps.setBackground(Color.RED);
			else if(user_sel.equals(r)) {//유저가 주먹
				chkWin++;
				master_rps.setBackground(Color.GREEN);
			}
		} 

		else if(master_sel.equals(r)){		 //컴이 바위
			if(user_sel.equals(s)) //유저가 가위
				master_rps.setBackground(Color.RED);
			
			else if(user_sel.equals(p)) {//유저가 보
				chkWin++;
				master_rps.setBackground(Color.GREEN);	
			}
		}  

		else if(master_sel.equals(p)) {//컴이 보
			if(user_sel.equals(r)) //유저가 바위
				master_rps.setBackground(Color.RED);
			
			else if(user_sel.equals(s)) {//유저가 가위
				chkWin++;
				master_rps.setBackground(Color.GREEN);
			}
		}
	}

	public boolean startGame(){
		timer();
		return win;
	}

	//3초 세고 컴이 고른거 보여줌
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
						case "가위" : 
							master_rps.setIcon(scissors);
							break;
						case "바위" : 
							master_rps.setIcon(rock);
							break;
						case "보" :
							master_rps.setIcon(paper);
							break;
					}

					if(user_sel.equals("")) master_rps.setBackground(Color.RED);
					chkUserMasterRps();
					count = 0;
					timerCnt.setVisible(false);

					//3판을 할 수 있음
					if(turn>=3) {
						if(chkWin>1) {
							score.setText("선공");
							win=true;
						}
						else{
							score.setText("후공");
							win=false;

						}
						for(int i=0; i<BTN_CNT; i++)
							user_rps[i].setVisible(false);
						master_rps.setVisible(false);

						gameOver.setVisible(true);
						score.setVisible(true);
						try {
							Thread.sleep(2000);
							jf.dispose();
							marbleJf.setVisible(true);

							return;
						}catch(Exception e) {
							System.out.println(e);
						}
					}
				}
			}	
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	public void paint(Graphics g){
		g.drawImage(back, 0, 0, null);
	}
}