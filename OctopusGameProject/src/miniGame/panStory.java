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

import FCP.fcp;


public class panStory extends JFrame{

	Container c1 = getContentPane();
    JButton nextBtn;
    JLabel talk;
    JLabel img;
    ImageIcon sMan = new ImageIcon(panStory.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(panStory.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(panStory.class.getResource("../img/circleManager.png"));

    int chkEnter = 0;

    private void startPanGame(String playerNo){
        setVisible(false);
        fcp f = new fcp(playerNo);
        f.startGame();
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

    public panStory(String s, String play,String playerNo){
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
                startPanGame(playerNo);
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
        startPanGame(playerNo);
    }

    public static void main(String[] args){
        String playerNo="123";
        String firstStory = "<html>참가번호 "+playerNo+"번, 일어나시기 바랍니다.<br> 현재 당신은 쭈꾸미 놀이에 참가했습니다.<br>"+ 
        "최선을 다해 게임을 참여해 우승을 하여<br>상금을 받아가시길 바랍니다.<br>총 상금은 457억이며, 각 게임에 참가자가<br>"+
        "탈락할 때마다 채워지는 방식입니다."+"</html>";
        String howGame = "<html>첫번째 게임은 판 뒤집기 입니다.<br> 판 뒤집기는 본인에게 주어진 색의 판을<br>더 많이 뒤집은 사람이 이기는 게임입니다.<br>"+
        "판을 뒤집기 위해선 마우스로 클릭을 하면 됩니다.<br> 주어진 시간이 끝난 후 본인의 색이<br>더 많이 보이면 이길 수 있습니다. <br>그럼 무운을 빕니다.</html>";
        new panStory(firstStory,howGame,playerNo);
    }
}

