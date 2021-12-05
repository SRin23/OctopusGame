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

public class connectPan extends JFrame{
    final int BTN_CNT = 32; //각 줄의 버튼 수
    final int BTN_WIDTH = 100; //판의 가로
    final int BTN_HEIGHT = 140; //판의 세로
    static int count = 1; //타이머를 위한 전역변수
    
    String nowColor[]=new String[BTN_CNT]; //판의 색을 저장
    JButton pan[]=new JButton[BTN_CNT]; //판
    
    JLabel timeJl; //남은 시간을 알려줌
    JLabel panCnt; //판의 수를 알려줌
    JLabel playerNo; //플레이어 번호
    JLabel masterNo; //master의 번호
    JLabel cnt; //3..2..1..
    JLabel gameOver; //gameOver
    JLabel score; //결과

    int playerCnt = 0; //player의 판 수(white)
    int masterCnt = 0; //master의 판 수(black)
    
    String playerColor = "white"; //사용자의 판 색
    String masterColor = "black"; //컴퓨터의 판 색
    
    static boolean win; //게임 이겼는지 여부
    Image back = new ImageIcon(connectPan.class.getResource("../img/fcpBack.png")).getImage();
    ImageIcon whitePan = new ImageIcon(connectPan.class.getResource("../img/whitePan.png"));
    ImageIcon blackPan = new ImageIcon(connectPan.class.getResource("../img/blackPan.png"));

    String playerNum;
    JButton nextBtn;
    JLabel talk;
    JLabel img;
    Container c1 = getContentPane();

    ImageIcon sMan = new ImageIcon(panStory.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(panStory.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(panStory.class.getResource("../img/circleManager.png"));
    
    boolean chkEnter = false;
    public connectPan(String pnum){
        this.playerNum=pnum;
        System.out.println(playerNum);
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
        talk.setText("<html>참가번호 000, 일어나시기 바랍니다.<br>"+
        "현재 당신은 쭈꾸미 놀이에 참가했습니다.<br>"+
        "최선을 다해 게임을 참가하여<br>"+ 
        "우승 상금을 받아가시기 바랍니다.<br>"+
        "첫번째 게임은 판 뒤집기 입니다.<br>"+
        "판 뒤집기는 본인에게 주어진 색의 판을<br>"+ 
        "더 많이 뒤집은 사람이 이기는 게임입니다.<br>"+
        "판을 뒤집기 위해선 마우스로 클릭을 하면 됩니다.<br>"+
        "주어진 시간이 끝난 후 본인의 색이<br>"+
        "더 많이 보이면 이길 수 있습니다.<br>"+
        "그럼 무운을 빕니다. </html> ");
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
        setTitle("판 뒤집기");
        c1.setBackground(Color.WHITE);
        //3..2..1..보여주는 라벨
			cnt = new JLabel("3");
			cnt.setBounds(510,250,300,300);
			cnt.setFont(cnt.getFont().deriveFont(300.0f));
			cnt.setForeground(Color.BLACK);
			cnt.setVisible(true);
			c1.add(cnt);
			
			//player 번호 및 색을 알려주는 라벨
			playerNo = new JLabel(playerNum+" : "+playerColor);
			playerNo.setBounds(0,20,150,30);
			playerNo.setFont(playerNo.getFont().deriveFont(25.0f));
			playerNo.setVisible(false);
			c1.add(playerNo);

			//master 번호 및 색을 알려주는 라벨
			masterNo = new JLabel("457 : "+masterColor);
			masterNo.setBounds(1050,20,150,30);
			masterNo.setFont(masterNo.getFont().deriveFont(25.0f));
			masterNo.setVisible(false);
			c1.add(masterNo);

			//남은 시간 보여주는 라벨
			timeJl = new JLabel("남은 시간 : 20");
			timeJl.setBounds(470,30,400,60);
			timeJl.setFont(timeJl.getFont().deriveFont(40.0f));
			timeJl.setVisible(false);
			c1.add(timeJl);
			
			//게임이 끝난 후 판의 개수를 보여주는 라벨
			panCnt = new JLabel();
			panCnt.setBounds(410,400,800,100);
			panCnt.setFont(panCnt.getFont().deriveFont(50.0f));
			panCnt.setForeground(Color.BLACK);
			panCnt.setVisible(false);
			c1.add(panCnt);
			
			int i,a;        
			int pan_y = 160;
			setNowColor();
			for(i=0, a=0; i<BTN_CNT; i++,a++) {
				pan[i] = new JButton();
				
				//시작하기 전까지  비활성화
				pan[i].setVisible(false);
				
				//버튼 줄바꿈을 위한 코드
				if(i == 8 || i == 16 || i == 24 ||i == 32) {
					pan_y += 160;
					a = 0;
				}
				pan[i].setBounds(50+(a*140), pan_y, BTN_WIDTH, BTN_HEIGHT);
				
				final int tmp = i; //이벤트 리스너 안에서 쓰기 위한 상수
				//버튼에 클릭 이벤트 부여
				pan[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//버튼 색 바뀜
						setColorBtn(tmp);
					}
				});
				c1.add(pan[i]);
			}

			gameOver = new JLabel();
			gameOver.setText("GAME OVER");
			gameOver.setBounds(300,300,800,100);
			gameOver.setForeground(Color.BLACK);
			gameOver.setFont(gameOver.getFont().deriveFont(100.0f));
			gameOver.setVisible(false);
			c1.add(gameOver);
	
			score = new JLabel();
			score.setBounds(520,500,300,80);
			score.setFont(score.getFont().deriveFont(50.0f));
			score.setForeground(Color.BLACK);
			score.setVisible(false);
			c1.add(score);
		
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
         for(int i=0; i<BTN_CNT; i++)
            pan[i].setVisible(false); //판
    
         timeJl.setVisible(false); //남은 시간을 알려줌
         panCnt.setVisible(false); //판의 수를 알려줌
         playerNo.setVisible(false); //플레이어 번호
         masterNo.setVisible(false); //master의 번호
         cnt.setVisible(false); //3..2..1..
         gameOver.setVisible(false); //gameOver
         score.setVisible(false); //결과
    }
    void gameVisibleTrue(){
        for(int i=0; i<BTN_CNT; i++)
        pan[i].setVisible(true); //판

     timeJl.setVisible(true); //남은 시간을 알려줌
     panCnt.setVisible(true); //판의 수를 알려줌
     playerNo.setVisible(true); //플레이어 번호
     masterNo.setVisible(true); //master의 번호
     cnt.setVisible(true); //3..2..1..
     gameOver.setVisible(true); //gameOver
     score.setVisible(true); //결과
    }
//초기 색 설정을 위한 함수
private void setNowColor() {
    Random random = new Random();
    int arr[] = new int[32];
    int zeroCnt = 0;
    int i;
    for( i=0; i<arr.length; i++) {
        int ran = random.nextInt(2);
        if(ran == 0) zeroCnt++;
        if(zeroCnt == 16) break;
        arr[i]= ran;
    }
    //나머지 채우기
    for(int j=i; j<arr.length; j++) 
        arr[j]=1;
    
    for(i=0; i<BTN_CNT; i++) {
        if(arr[i] == 0) nowColor[i] = playerColor;
        else nowColor[i] = masterColor;
    }	
}

//nowColor에 따른 버튼 색 바뀜
private void setColorBtn(int tmp) {
    if(nowColor[tmp].equals(playerColor)) {
        nowColor[tmp] = masterColor;
        pan[tmp].setIcon(blackPan);
    }
    else {
        nowColor[tmp] = playerColor;
        pan[tmp].setIcon(whitePan);
    }
}

//게임이 마무리된 후 판의 갯수를 셈
private boolean countPan() {
    for(int i=0; i<BTN_CNT; i++) {
        if(nowColor[i].equals(playerColor)) playerCnt++;
        else masterCnt++;
    }
    if(playerCnt>masterCnt) {
        for(int i=0; i<BTN_CNT; i++)
            pan[i].setIcon(whitePan);
        return true;
    } 
    else if(masterCnt>=playerCnt ) {
        for(int i=0; i<BTN_CNT; i++)
            pan[i].setIcon(blackPan);
        return false;
    }
    return true;
}

//자동으로 판 뒤집기
private void com() {
    Random random = new Random();
    int t = random.nextInt(2800)+1100; //판을 뒤집는 시간
    
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        
        @Override
        public void run() {
            if(count <= 20) {
                int ran = random.nextInt(32);
                if(nowColor[ran].equals(playerColor)) {
                    nowColor[ran] = masterColor;
                    pan[ran].setIcon(blackPan);
                }
            }
            else timer.cancel();
        }
    };
    timer.schedule(task, 0 , t);
}
void game(){
    playerNo.setVisible(true);
    masterNo.setVisible(true);
    timeJl.setVisible(true);

    for(int i=0; i<BTN_CNT; i++) {
        pan[i].setVisible(true);
        if(nowColor[i].equals(playerColor)) pan[i].setIcon(whitePan);
        else pan[i].setIcon(blackPan);
    }
    
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            com();
            if(count<=20) {
                timeJl.setText("남은 시간 : "+(20-count));
                
                count++;
            }
            else {
                timeJl.setVisible(false);
                timer.cancel();
                win = countPan();
                
                if(win) score.setText(playerNum+" 이김");
                else score.setText("457 이김");

                gameOver.setVisible(true);
                score.setVisible(true);
                
                panCnt.setText(playerNum+" : "+playerCnt+", 457 : "+masterCnt);
                panCnt.setVisible(true);
                
                for(int i=0; i<BTN_CNT; i++) 
                    pan[i].setVisible(false);
                
            }
        }
    };
    timer.scheduleAtFixedRate(task, 1000, 1000);
    
}
static int countDown=1;

//판 뒤집기 게임 시작
public boolean startGame() {
    setGame();
    Timer t = new Timer();
    TimerTask tt = new TimerTask() {

    @Override
    public void run() {
        if(countDown<3) {
            cnt.setText(""+(3-countDown));
            countDown++;
        }
        else {
            t.cancel();
            cnt.setVisible(false);
            game();
        }
    }
};
t.scheduleAtFixedRate(tt, 1000,1000);
return win;
}
}
