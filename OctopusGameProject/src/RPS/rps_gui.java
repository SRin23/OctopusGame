package RPS;

import javax.swing.*;

public class rps_gui extends JFrame{
    void rpsFrame(int userNo, int masterNo){
		
		//프레임 생성
        JFrame jf = new JFrame("선후공 정하기 - 가위바위보");
		//창을 닫을 시 프로그램 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jf.setSize(1200,900);
		//프레임(위에 x 있는 거) 보이게 설정
		jf.setVisible(true);

		//버튼 3개 생성
		JButton user_rock = new JButton("가위");
		JButton user_sissors = new JButton("바위");
		JButton user_paper = new JButton("보");

		//문자열 추가
		JLabel vsLabel = new JLabel(); //텍스트를 보여줄 JLabel 생성

		vsLabel.setText("456 vs 457");
		vsLabel.setBounds(10, 10,200,30);
		//label 글자 크기 조절
		vsLabel.setFont(vsLabel.getFont ().deriveFont (30.0f));

		//x,y,w,h
		user_rock.setBounds(200, 630, 150, 150);
		user_sissors.setBounds(500, 630, 150, 150);
		user_paper.setBounds(800, 630, 150, 150);
		
		jf.getContentPane().add(vsLabel);
		jf.getContentPane().add(user_rock);
		jf.getContentPane().add(user_sissors);
		jf.getContentPane().add(user_paper);
    }
}
