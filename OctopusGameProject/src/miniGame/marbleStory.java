package miniGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import FCP.fcp;
import RPS.rps;
import marbleOddEven.MarbleGameMain;

//진행 요원이 진행하는거 
public class marbleStory extends JFrame{

	Container c1 = getContentPane();
    JButton nextBtn;
    JLabel talk;

    private void startMarbleGame(String playerNo){
        rps r = new rps();
    }


    marbleStory(String s){
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
                startMarbleGame("123");
            }

        });

        //story 내용이 담겨져있는 박스
        talk = new JLabel(s);
        talk.setBounds(400,400,1000,420);
        talk.setOpaque(true); //라벨의 배경은 기본적으로 투명이기 때문에 배경색이 안먹힘
        talk.setBackground(Color.WHITE);
        //talk.setHorizontalAlignment(SwingConstants.TOP);
        talk.setFont(talk.getFont().deriveFont(30.0f));
        c1.add(talk);
        c1.add(nextBtn);
    }
    public static void main(String[] args){
        String story = "우승을 기원한다.";
        new marbleStory(story);
    }
}
