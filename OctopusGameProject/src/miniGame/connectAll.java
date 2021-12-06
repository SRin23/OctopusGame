package miniGame;

import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import FCP.panGame;
import RSP.rspGame;
import mainMenu.GameEnding_fail;
import mainMenu.GameEnding_success;
import marbleOddEven.MarbleGameGUI;

public class connectAll {
    final int FRAME_CNT=4;
    public JFrame panJf;
    public JFrame marbleJf;
    public JFrame catchMoleJf;
    public JFrame rspJf;

    JButton nextBtn[] = new JButton[FRAME_CNT];
    JLabel talk[] = new JLabel[FRAME_CNT];
    JLabel img[] = new JLabel[FRAME_CNT];

    ImageIcon sMan = new ImageIcon(connectAll.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(connectAll.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(connectAll.class.getResource("../img/circleManager.png"));

    boolean chkEnter = false;
    boolean start;
    public static int heart = 2;
    
    public connectAll(String pNum){

    	Font font = new Font("서울남산 장체B",Font.PLAIN,20);

        panJf = new JFrame();
        rspJf = new JFrame();
        marbleJf = new JFrame();
        catchMoleJf = new JFrame();

        Container c1 = panJf.getContentPane();
        Container c2 = catchMoleJf.getContentPane();
        Container c3 = rspJf.getContentPane();
        Container c4 = marbleJf.getContentPane();

        panJf.setTitle("pan");
		//창을 닫을 시 프로그램 종료
		panJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		panJf.setResizable(false);
		panJf.setSize(1200,900);
        c1.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		panJf.setVisible(true);

        rspJf.setTitle("rsp");
		//창을 닫을 시 프로그램 종료
		rspJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		rspJf.setResizable(false);
		rspJf.setSize(1200,900);
        c3.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		rspJf.setVisible(false);

        marbleJf.setTitle("marble");
		//창을 닫을 시 프로그램 종료
		marbleJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		marbleJf.setResizable(false);
		marbleJf.setSize(1200,900);
        c4.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		marbleJf.setVisible(false);

        catchMoleJf.setTitle("catchMole");
		//창을 닫을 시 프로그램 종료
		catchMoleJf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		catchMoleJf.setResizable(false);
		catchMoleJf.setSize(1200,900);
        c2.setBackground(Color.BLACK); 
		//프레임(위에 x 있는 거) 보이게 설정
		catchMoleJf.setVisible(false);

        panJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                	
                	chkEnter = true;
                    System.out.println(chkEnter);
                    if(chkEnter) {
                        panJf.dispose();
                        new panGame(pNum).startGame();
                        chkEnter = false;
                    }
                }
            }
        });

        marbleJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter = true;
                    if(chkEnter) {
                        marbleJf.dispose();
                       new MarbleGameGUI(pNum).startGame(start);
                        chkEnter =false;
                    	
                    }
                }
            }
        });

        catchMoleJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter = true;
                    if(chkEnter) {
                        catchMoleJf.dispose();
                        new catchMole.CatchMoleGUI(pNum).startGame();
                        chkEnter = false;
                        
                    }
                }
            }
        });

        rspJf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                	chkEnter = true;
                    if(chkEnter) {
                        rspJf.dispose();
                        start = new rspGame(pNum).startGame();
                        chkEnter = false;
                    }
                }
            }
        });

        talk[0] = new JLabel("<html>참가번호 "+pNum+", 일어나시기 바랍니다.<br>"
        		+ "현재 당신은 쭈꾸미 놀이에 참가했습니다.<br> "
        		+ "최선을 다해 게임을 참가하여 우승 상금을 받아가시기 바랍니다.<br><br>" 
        		+"첫번째 게임은 판 뒤집기 입니다.<br>"
        		+ "판 뒤집기는 본인에게 주어진 색의 판을 더 많이 뒤집은 사람이 이기는 게임입니다.<br>"  
        		+"판을 뒤집기 위해선 마우스로 클릭을 하면 됩니다.<br>"
        		+ "주어진 시간이 끝난 후 본인의 색이 더 많이 보이면 이길 수 있습니다.<br><br>"  
        		+"그럼 무운을 빕니다.</html>" );
        talk[1] = new JLabel("<html>다음 게임은 두더지 잡기입니다.<br>"
        		+ "게임은 매우 간단합니다.<br>눈에 보이는 두더지를 열심히 클릭하여 점수를 얻으면 됩니다.<br><br>"
        		+ "그럼 무운을 빕니다.</html>");
        talk[2] = new JLabel("<html>지금까진 살아있는 당신은<br>"
        		+ "매우 운이 좋거나 실력이 좋은 편입니다.<br><br>"+
        		"마지막 게임은 홀짝 게임입니다.<br>"
        		+ "홀짝 게임의 공수를 정하기 위해 가위바위보를 진행합니다.<br>"
        		+"가위바위보는 자신이 낼 포지션을 고르면 됩니다.<br>"+
        		"이기면 초록색, 지면 빨간색, 비기면 노란색입니다.<br>"+
        		"총 삼세판 중 두 게임을 이기면 됩니다.<br><br>"+
        		"그럼 무운을 빕니다.</html>");
        talk[3] = new JLabel("<html>홀짝 게임은 턴마다 공수가 변경됩니다.<br>" + 
        		"먼저 공격은 손에 쥘 갯수를 선택합니다.<br>" + 
        		"수비는 공격이 쥔 구슬의 수가 홀인지 짝인지 맞추게 됩니다.<br>" + 
        		"수비가 맞출 경우 공격의 구슬은 " + 
        		"수비에게 넘어가고," + 
        		"맞추지 못할 경우 구슬을 넘겨줍니다.<br>" + 
        		"최종적으로 구슬이 없는 사람이 지게 됩니다.<br>"
        		+ "본격적으로 홀짝 게임을 시작해보겠습니다.<br><br>"
        		+ "그럼 무운을 빕니다.</html>");
        
        for(int i=0; i<FRAME_CNT; i++){
        	talk[i].setFont(font.deriveFont(30.0f));
            talk[i].setBounds(350,400,800,420);
            talk[i].setOpaque(true); //라벨의 배경은 기본적으로 투명이기 때문에 배경색이 안먹힘
            talk[i].setBackground(Color.WHITE);
            talk[i].setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
            talk[i].setForeground(Color.BLACK);
        }
        c1.add(talk[0]);
        c2.add(talk[1]);
        c3.add(talk[2]);
        c4.add(talk[3]);

        //진행 요원 이미지
        img[0] = new JLabel(sMan);
        img[0].setBounds(0, 500, 300, 400);
        c1.add(img[0]);

        img[1] = new JLabel(tMan);
        img[1].setBounds(0, 500, 300, 400);
        c2.add(img[1]);

        img[2] = new JLabel(cMan);
        img[2].setBounds(0, 500, 300, 400);
        c3.add(img[2]);

        img[3] = new JLabel(cMan);
        img[3].setBounds(0, 500, 300, 400);
        c4.add(img[3]);

        //story 넘기기
        for(int i=0; i<FRAME_CNT; i++){
            nextBtn[i] = new JButton("ENTER 또는 이곳을 누르면 넘어갑니다.");
            nextBtn[i].setBounds(800, 30, 400, 30);
            nextBtn[i].setBorderPainted(false);
            nextBtn[i].setContentAreaFilled(false);
            nextBtn[i].setFont(font.deriveFont(20.0f));
            nextBtn[i].setForeground(Color.WHITE);
            nextBtn[i].setFocusPainted(false);

            switch(i){
                case 0:        
                    nextBtn[i].setText("ENTER 또는 이곳을 누르면 넘어갑니다.");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panJf.dispose();
                            new panGame(pNum).startGame();
                            System.out.println(heart);
                        }
                    });
                    c1.add(nextBtn[i]);
                    break;
                case 1:
                nextBtn[i].setText("ENTER 또는 이곳을 누르면 넘어갑니다.");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	catchMoleJf.dispose();
                        	new catchMole.CatchMoleGUI(pNum).startGame();
                        }
                    });
                    c2.add(nextBtn[i]);
                    break;
                case 2:
                nextBtn[i].setText("ENTER 또는 이곳을 누르면 넘어갑니다.");
                    nextBtn[i].addActionListener(new ActionListener() {
            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rspJf.dispose();
                            start = new rspGame(pNum).startGame();
                        }
                    });
                    c3.add(nextBtn[i]);
                    break;
                case 3:
                nextBtn[i].setText("ENTER 또는 이곳을 누르면 넘어갑니다.");
                nextBtn[i].addActionListener(new ActionListener() {
            
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        marbleJf.dispose();
                        new MarbleGameGUI(pNum).startGame(start);
                    }
                });
                    c4.add(nextBtn[i]);
                    break;
            }
        }
        JLabel jl[] = new JLabel[FRAME_CNT];
        for(int i=0; i<FRAME_CNT; i++)
            jl[i] = new JLabel();
        c1.add(jl[0]);
        c2.add(jl[1]);
        c3.add(jl[2]);
        c4.add(jl[3]);
    }
}
