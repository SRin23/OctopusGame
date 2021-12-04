package miniGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.awt.event.KeyAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import FCP.fcp;

//진행 요원이 진행하는거 

public class panStory extends JFrame{

	Container c1 = getContentPane();
    JButton nextBtn;
    JLabel talk;
    JLabel img;
    ImageIcon sMan = new ImageIcon(panStory.class.getResource("../img/squareManager.png"));
    ImageIcon tMan = new ImageIcon(panStory.class.getResource("../img/triangleManager.png"));
    ImageIcon cMan = new ImageIcon(panStory.class.getResource("../img/circleManager.png"));

    boolean chkEnter = false;

    private void startPanGame(String playerNo){
        fcp f = new fcp(playerNo);
        f.startGame();
    }

    //story를 한글자씩 말함
    private void talking(String s){
        String str="";
        try {
            for(int i=0; i<s.length(); i++){
                if(chkEnter) {
                    return;
                }
                Thread.sleep(300);
                str+=s.charAt(i);
                talk.setText(str);
            }			
		}catch(Exception e) {
			System.out.println(e);
		}
    }

    public panStory(String s,String playerNo){
        setTitle("말말");
		//창을 닫을 시 프로그램 종료
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//프레임 창 고정
		setResizable(false);
		setSize(1200,900);
        c1.setBackground(Color.BLACK);
		//프레임(위에 x 있는 거) 보이게 설정
		setVisible(true);

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
        talk.setBounds(400,400,1000,420);
        talk.setOpaque(true); //라벨의 배경은 기본적으로 투명이기 때문에 배경색이 안먹힘
        talk.setBackground(Color.WHITE);
        //talk.setHorizontalAlignment(SwingConstants.TOP);
        talk.setFont(talk.getFont().deriveFont(30.0f));
        c1.add(talk);
        c1.add(nextBtn);
        talking(s);

        c1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(chkEnter) startPanGame(playerNo); //엔터키를 두번 누르면 넘어감
                    chkEnter = true; 
                }
            }
        });
    }

    public static void main(String[] args){
        String story = "우승을 기원한다.";
        new panStory(story,"123");
    }
}

