package main;

import javax.swing.JButton;
import javax.swing.JFrame;

public class mainMenu extends JFrame{
	public mainMenu(){
		//------프레임 설정
		JFrame jf = new JFrame("쭈꾸미 놀이 - by 연우, 세린");
		
		//창 닫을 시 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//프레임을 화면 가운데 배치
		//jf.setLocationRelativeTo(null);
		//프레임 사이즈 - 전체화면 창모드
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setVisible(true);
		
		//-----버튼 설정
		JButton b1 = new JButton("게임시작");
		JButton b2 = new JButton("도움말");
			
		//버튼 위치 및 크기 
		//x,y,w,h
		b1.setBounds(30, 170, 122, 30);
		b2.setBounds(182, 170, 122, 30);
			
		//프레임에 버튼 추가
		jf.getContentPane().add(b1);
		jf.getContentPane().add(b2);
	}
}
