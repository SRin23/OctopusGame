package RPS;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class rps {
	JFrame jf; 
	static final int BTN_CNT = 3; //가위바위보 버튼의 수
	JButton user_rps[] = new JButton[BTN_CNT];

	static final int BTN_WIDTH = 150;
	static final int BTN_HEIGHT = 150;
	
	String r = "바위";
	String p = "보";
	String s = "가위";
	
	rps(){
		jf = new JFrame("가위바위보");
		//창을 닫을 시 프로그램 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		jf.setResizable(false);
		//화면 가운데 배치
		//jf.setLocationRelativeTo(null);
		jf.setSize(1200,900);
		//프레임(위에 x 있는 거) 보이게 설정
		jf.setVisible(true);
		
		for(int i=0; i<BTN_CNT; i++) {
			user_rps[i]=new JButton("");
			user_rps[i].setBounds(215+(i*300),630, 150,150);
			
			jf.getContentPane().add(user_rps[i]);
		}
		user_rps[0].setText(r); //바위
		user_rps[1].setText(p); //보
		user_rps[2].setText(s); //가위
	}
}
