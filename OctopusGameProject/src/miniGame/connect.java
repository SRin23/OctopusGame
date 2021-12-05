package miniGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Image;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class connect extends JFrame{
    final int BTN_WIDTH = 400;
	final int BTN_HEIGHT = 400;
	final int BTN_CNT = 3; //가위바위보 버튼의 수
	
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
	private Image back= new ImageIcon(connect.class.getResource("../img/rspBack.png")).getImage();
	ImageIcon rock = new ImageIcon(connect.class.getResource("../img/rock.png"));
	ImageIcon scissors = new ImageIcon(connect.class.getResource("../img/scissors.png"));
	ImageIcon paper = new ImageIcon(connect.class.getResource("../img/paper.png"));

    JButton nextBtn;
    JLabel talk;
    JLabel img;
    Container c1 = getContentPane();

    ImageIcon sMan = new ImageIcon(panStory.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(panStory.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(panStory.class.getResource("../img/circleManager.png"));
    
    boolean chkEnter = false;
    public connect(){

        setTitle("멘트1");
        //창을 닫을 시 프로그램 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//프레임 창 고정
		setResizable(false);
		setSize(1200,900);
        c1.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		setVisible(true);

        //진행 요원 이미지
        img = new JLabel(sMan);
        img.setBounds(0, 500, 300, 400);
 
        //story 넘기기
        nextBtn = new JButton("Enter를 누르거나 이곳을 클릭하면 넘어갑니다");
        nextBtn.setBounds(700, 30, 500, 30);
        nextBtn.setBorderPainted(false);
        nextBtn.setContentAreaFilled(false);
        nextBtn.setFont(nextBtn.getFont().deriveFont(20.0f));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);

        nextBtn.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                //story skip
                storyVisibleFalse();
                startGame();

            }
        });

        //story 내용이 담겨져있는 박스
        talk = new JLabel();
        talk.setText("<html>마지막 게임은 홀짝 게임입니다.<br> 홀짝 게임의 공수를 정하기 위해 가위바위보를 진행합니다.<br>"+
        "홀짝 게임은 턴마다 공수가 변경됩니다.<br> 먼저 공격은 자신의 손에 쥘 갯수를 선택합니다.<br>"+ 
        "수비는 공격이 쥔 구슬의 수가 홀수인지 짝수인지 맞추게 됩니다.<br>"+ 
        "이때 수비가 맞출 경우 공격의 구슬은 수비에게 넘어가고,<br> 맞추지 못할 경우 구슬을 넘겨줍니다.<br>"+
        "최종적으로 구슬이 없어지는 사람은 지게 됩니다.<br><br>"+
        "그럼 무운을 빕니다.</html> ");
        talk.setBounds(350,400,800,420);
        talk.setOpaque(true); //라벨의 배경은 기본적으로 투명이기 때문에 배경색이 안먹힘
        talk.setBackground(Color.WHITE);
        //talk.setHorizontalAlignment(SwingConstants.TOP);
        talk.setFont(talk.getFont().deriveFont(25.0f));
        talk.setBorder(BorderFactory.createEmptyBorder(0,40,0,0));
        talk.setForeground(Color.BLACK);

        //talking("1234556","!2334555");
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter=true;
                    if(chkEnter){
                        storyVisibleFalse();
                        startGame();
                    }
                }
            }
        });
        c1.add(img);
        c1.add(talk);
        c1.add(nextBtn);
        
        JLabel jl = new JLabel();
        c1.add(jl);
    }

    void setGame (){
        setTitle("가위바위보");
        //컴 선택 버튼 세팅
		master_rps = new JButton();
		master_rps.setBounds(500, 50,BTN_WIDTH/2,BTN_HEIGHT/2);
		master_rps.setBackground(Color.WHITE);
		master_rps.setBorderPainted(false);
        master_rps.setVisible(true);
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
            user_rps[i].setVisible(true);
			
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

        JLabel jl = new JLabel();
        c1.add(jl);
    }
    void storyVisibleFalse(){
        nextBtn.setVisible(false);
        talk.setVisible(false);
        img.setVisible(false);
    }
    void storyVisibleTrue(){
        nextBtn.setVisible(true);
        talk.setVisible(true);
        img.setVisible(true);
    }
    void gameVisibleFalse(){
        master_rps.setVisible(false);
        timerCnt.setVisible(false);
        for(int i=0; i<BTN_CNT; i++)
            user_rps[i].setVisible(false);
        gameOver.setVisible(false);
        score.setVisible(false);
    }
    void gameVisibleTrue(){
        master_rps.setVisible(true);
        timerCnt.setVisible(true);
        for(int i=0; i<BTN_CNT; i++)
            user_rps[i].setVisible(true);
        gameOver.setVisible(true);
        score.setVisible(true);
    }

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
        setGame();
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
                            storyVisibleTrue();
                            gameVisibleFalse();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

					}
				}
			}	
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
    // private void talking(String s, String s2){
    //     String str="";
    //     try {
    //         for(int i=5; i<s.length(); i++){
    //             if(chkEnter == 2)
    //                 break;
    //             if(chkEnter == 1) 
    //                 talk.setText(s);
                
    //             Thread.sleep(110);
    //             str+=s.charAt(i);
    //             talk.setText("<html>"+str);
    //         }			
	// 	}catch(Exception e) {
	// 		System.out.println(e);
	// 	}

    //     str="<html>";
    //     talk.setText(str);

    //     try {
    //         for(int i=6; i<s2.length(); i++){
    //             if(chkEnter == 4) {
    //                 return ;
    //             }
    //             if(chkEnter == 3) {
    //                 talk.setText(s2);
    //                 break;
    //             }
    //             Thread.sleep(110);
    //             str+=s2.charAt(i);
    //             talk.setText(str);
    //         }
    //         Thread.sleep(3000);

    //     }catch(Exception e) {
    //         System.out.println(e);
    //     }
    //     return;
    // }
}
