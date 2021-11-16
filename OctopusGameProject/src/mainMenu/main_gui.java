package mainMenu;

import RPS.rpsGame;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class main_gui{
	//텍스트 필드를 통해 얻은 string값
	String userNo;
	JTextField userNoTf;
	
	main_gui(){
		JFrame mainMenuJF = new JFrame("쭈꾸미 게임");
		Container c = mainMenuJF.getContentPane();
		//창 닫을 시 프로그램 종료
		mainMenuJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuJF.setSize(1440, 1000);
		mainMenuJF.setVisible(true);
		
		JButton gameStartBtn = new JButton("게임 시작");
		JButton helpBtn = new JButton("게임 방법");
		
		gameStartBtn.setBounds(300, 600, 240, 120);
		helpBtn.setBounds(900, 600, 240, 120);
		
		c.add(gameStartBtn);
		c.add(helpBtn);
		
		JFrame inUserNoJf = new JFrame("이름 입력");
		
		userNoTf = new JTextField("456",10);
		inUserNoJf.getContentPane().add(userNoTf);
		
		
		inUserNoJf.setSize(200, 100);
		
		//번호 입력 후 이벤트 처리(enter감지)
		userNoTf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				inUserNoJf.setVisible(false);
				userNo = userNoTf.getText();
				
			}
		});
		
		//게임 시작 버튼을 누르면 이름 입력 후 게임 시작
		gameStartBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//mainMenuJF.setVisible(false);
				inUserNoJf.setVisible(true);
				
			}
		});
		
		helpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}

}
