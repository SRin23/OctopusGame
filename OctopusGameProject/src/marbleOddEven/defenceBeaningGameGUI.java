//수비
package BeanOddEvenGame;

import java.util.Random;
import java.util.Scanner;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class defenceBeaningGameGUI extends JFrame{
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	//----------------시스템 변수 정의------------------	
	int marble;	//사용할 구슬의 개수
	int computerScore;	//컴퓨터의 구슬의 개수
	int userScore;	//사용자의 구슬의 개수
	int userNumber;
	boolean start;
	int heart;
	
	//----------------GUI 변수 정의------------------	
	private static final long serialVersionUID = -1628752121408948294L;
	
	//홀, 짝 Click 변수
	boolean oddEven;
	
	//문자열 추가
	JLabel vsLabel = new JLabel(); //텍스트를 보여줄 JLabel 생성
	//JLabel heartLabel = new JLabel(); //텍스트를 보여줄 JLabel 생성
	
	//버튼 2개 생성
	JButton user_odd = new JButton("홀");
	JButton user_even = new JButton("짝");
	
	void beaningFrame(int userNo, int heart){
		this.userNumber = userNo;
		this.heart = heart;
		//----------------Frame설정-----------------
		 
		//창의 제목 설정
		setTitle("쭈꾸미놀이(홀짝게임)");
		//창 사이즈(크기) 설정
		setSize(1200, 900);
		//사용자가 창 크기 바꾸지 못하게함
		setResizable(false);	
		//실행시, 창이 화면 정 중앙에 뜬다.
		setLocationRelativeTo(null);
		//창을 닫을 시 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//창 보이게 설정
		setVisible(true);
		//배치관리자 제거
		setLayout(null);
		 
		//----------------Label 설정-----------------
		//text내용 및 위치, 크기
		vsLabel.setText(userNumber + "번(나) VS 457번");
		vsLabel.setBounds(10,10,500,30);
		//heartLabel.setText("목숨 : " + heart);
		//heartLabel.setBounds(900,10,500,30);
		
		//label 글자 크기 조절
		vsLabel.setFont(vsLabel.getFont().deriveFont (30.0f));
		//heartLabel.setFont(vsLabel.getFont().deriveFont (30.0f));

		//----------------Button 설정-----------------
		//홀짝 버튼 크기 및 위치 설정
		user_odd.setBounds(300, 630, 150, 150);
		user_even.setBounds(750, 630, 150, 150);
		user_odd.setFont(user_odd.getFont().deriveFont(30.0f));
		user_even.setFont(user_even.getFont().deriveFont(30.0f));
		 
		//버튼 색깔 지정
		user_odd.setBackground(Color.white);
		user_even.setBackground(Color.white);
		
		//홀수 UI에 마우스 가져가면 도움말을 보여줌
		//user_odd.setToolTipText("1, 3, 5, 7...");
		
		
		//버튼 Listener 적용
		user_even.addActionListener(evenBtnListener);
		user_odd.addActionListener(oddBtnListener);
		
		//----------------요소들 창에 추가-----------------
		add(vsLabel);
		//add(heartLabel);
		add(user_odd);
		add(user_even);
	}
	//홀수버튼 -> true, 짝수버튼 -> false
	ActionListener oddBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			oddEven = true;
			System.out.println(oddEven + " / 홀수를 클릭하셨습니다.");
		}
	};
	
	ActionListener evenBtnListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			oddEven = false;
			System.out.println(oddEven + " / 짝수를 클릭하셨습니다.");
		}
	};
}
