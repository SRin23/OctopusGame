package BeanOddEvenGame;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class defenceBeaningGameGUI extends JFrame{
	 void beaningFrame(int userNo, int heart){
			
			//프레임 생성
		 	JFrame bf = new JFrame("구슬치기(홀짝)");
		 	
			//창을 닫을 시 프로그램 종료
		 	bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 	bf.setSize(1200,900);
		 	
			//프레임(위에 x 있는 거) 보이게 설정
		 	bf.setVisible(true);

			//버튼 2개 생성
			JButton user_odd = new JButton("홀");
			JButton user_even = new JButton("짝");
			
			//문자열 추가
			JLabel vsLabel = new JLabel(); //텍스트를 보여줄 JLabel 생성

			vsLabel.setText(userNo + "번 VS 457번");
			vsLabel.setBounds(10,10,500,30);
			
			//문자열 추가
			JLabel heartLabel = new JLabel(); //텍스트를 보여줄 JLabel 생성

			heartLabel.setText("남은 목숨의 수 : " + heart);
			heartLabel.setBounds(900,10,500,30);
			
			//label 글자 크기 조절
			vsLabel.setFont(vsLabel.getFont().deriveFont (30.0f));
			heartLabel.setFont(vsLabel.getFont().deriveFont (30.0f));

			//x,y,w,h
			user_odd.setBounds(300, 630, 150, 150);
			user_even.setBounds(750, 630, 150, 150);
			user_odd.setFont(user_odd.getFont().deriveFont(30.0f));
			user_even.setFont(user_even.getFont().deriveFont(30.0f));
			
			bf.getContentPane().add(vsLabel);
			bf.getContentPane().add(heartLabel);
			bf.getContentPane().add(user_odd);
			bf.getContentPane().add(user_even);
			
			
	    }
}
