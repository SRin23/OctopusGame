package miniGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import RPS.rps;


public class marbleStory extends JFrame{

	Container c1 = getContentPane();
    JButton nextBtn;
    JLabel talk;
    JLabel img;
    ImageIcon sMan = new ImageIcon(panStory.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(panStory.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(panStory.class.getResource("../img/circleManager.png"));

    int chkEnter = 0;
    boolean first;
    private void startRspGame(String playerNo){
        setVisible(false);
        rps r = new rps();
        boolean first = r.startGame();
    }

    //story를 한글자씩 말함
    private void talking(String s, String s2){
        String str="";
        try {
            for(int i=6; i<s.length(); i++){
                if(chkEnter == 2)
                    break;
                if(chkEnter == 1) 
                    talk.setText(s);
                
                Thread.sleep(110);
                str+=s.charAt(i);
                talk.setText("<html>"+str);
            }			
		}catch(Exception e) {
			System.out.println(e);
		}

        str="<html>";
        talk.setText(str);

        try {
            for(int i=6; i<s2.length(); i++){
                if(chkEnter == 4) {
                    return ;
                }
                if(chkEnter == 3) {
                    talk.setText(s2);
                    break;
                }
                Thread.sleep(110);
                str+=s2.charAt(i);
                talk.setText(str);
            }
            Thread.sleep(3000);

        }catch(Exception e) {
            System.out.println(e);
        }
        return;
    }

    public marbleStory(String s, String play,String playerNo){
        setTitle("말말");
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
        img.setBounds(0, 100, 300, 400);
        c1.add(img);

        //story 넘기기
        nextBtn = new JButton("> SKIP");
        nextBtn.setBounds(1030, 30, 150, 30);
        nextBtn.setBorderPainted(false);
		nextBtn.setContentAreaFilled(false);
        nextBtn.setFont(nextBtn.getFont().deriveFont(30.0f));
        nextBtn.setForeground(Color.WHITE);
		nextBtn.setFocusPainted(false);
        nextBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //story skip
                setVisible(false);
                startRspGame(playerNo);
            }
        });

        //story 내용이 담겨져있는 박스
        talk = new JLabel();
        talk.setBounds(350,400,800,420);
        talk.setOpaque(true); //라벨의 배경은 기본적으로 투명이기 때문에 배경색이 안먹힘
        talk.setBackground(Color.WHITE);
        //talk.setHorizontalAlignment(SwingConstants.TOP);
        talk.setFont(talk.getFont().deriveFont(30.0f));
        talk.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
        talk.setForeground(Color.BLACK);
        c1.add(talk);
        c1.add(nextBtn);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER ||e.getKeyCode() == KeyEvent.VK_SPACE ){
                    chkEnter++; 
                }
            }
        });
        talking(s,play);
        startRspGame(playerNo);
    }

    public static void main(String[] args){
        String playerNo="123";
        String firstStory = "지금까진 살아있는 당신은<br>매우 운이 좋거나 실력이 좋은 편입니다. <br>그럼 마지막 게임을 시작하겠습니다."+"</html>";
        String howGame = "<html>마지막 게임은 홀짝 게임입니다.<br>홀짝 게임의 공수를 정하기 위해 가위바위보를 진행합니다.<br>"+
        "홀짝 게임은 턴마다 공수가 변경됩니다.<br>먼저 공격은 자신의 손에 쥘 갯수를 선택합니다.<br>"+
        "수비는 공격이 쥔 구슬의 수가 홀수인지 짝수인지 맞추게 됩니다.<br>"+ 
        "이때 수비가 맞출 경우 공격의 구슬은 수비에게 넘어가고,<br>맞추지 못할 경우 구슬을 넘겨줍니다.<br>"+
        "최종적으로 구슬이 없어지는 사람은 지게 됩니다.<br>"+
        "그럼 무운을 빕니다.</html>";
        new panStory(firstStory,howGame,playerNo);
    }
}

