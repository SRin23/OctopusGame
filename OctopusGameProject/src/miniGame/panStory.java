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

import FCP.panGame;

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
        panGame f = new panGame(playerNo);
        f.startGame();
    }

    //story를 한글자씩 말함
    private void talking(String s, String s2){
        String str="";
        try {
            for(int i=5; i<s.length(); i++){
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
        JLabel jl = new JLabel();
        c1.add(jl);
        talking(s,play);
        startPanGame(playerNo);
    }
}

