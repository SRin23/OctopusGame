package BeanOddEvenGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class MarbleGameGUI extends JFrame{
	
	//기본 setting
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	
	//멤버변수 -> private

	// image를 담을 변수
	private Image background = new ImageIcon(MarbleGame.class.getResource("../img/marbleBackground.jpg")).getImage();
		
	//홀, 짝
	private final int odd = 1;
	private final int even = 0;
	
	//초기 변수 setting
	private boolean start;
	private int heart;
	private int userNumber;
	private int computerScore;	//컴퓨터의 구슬의 개수
	private int userScore;	//사용자의 구슬의 개수
	int computerSelect;

	//초기 구슬 setting 변수
	private JLabel explain = new JLabel();
	private JTextField setFirstMarble = new JTextField("구슬개수를 입력하세요.", 12);
	
	//수비(홀, 짝) 변수
	private ImageIcon evenButtonDefault = new ImageIcon(MarbleGame.class.getResource("../img/evenButton.png"));
	private ImageIcon evenButtonEntered = new ImageIcon(MarbleGame.class.getResource("../img/evenButtonEntered.png"));
	private ImageIcon oddButtonDefault = new ImageIcon(MarbleGame.class.getResource("../img/oddButton.png"));
	private ImageIcon oddButtonEntered = new ImageIcon(MarbleGame.class.getResource("../img/oddButtonEntered.png"));
	private JButton evenButton = new JButton(evenButtonDefault);
	private JButton oddButton = new JButton(oddButtonDefault);
	
	private JLabel Score = new JLabel();
	private JLabel HeartLabel = new JLabel("목숨 : " + heart);
	
	//공격(구슬쥐기) 변수
	
	
	MarbleGameGUI(boolean start, int heart, int userNumber){
		this.start = start;
		this.heart = heart;
		this.userNumber = userNumber;
		//----------------Frame설정-----------------
		//setUndecorated(true);
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
		//배경색 설정
		setBackground(Color.darkGray);
		//배치관리자 제거
		setLayout(null);
		
		
		//--------------- Defence 버튼 ---------------
		//홀 버튼
		oddButton.setBounds(200, 500, 300, 300);
		oddButton.setBorderPainted(false);
		oddButton.setContentAreaFilled(false);
		oddButton.setFocusPainted(false);
		oddButton.setVisible(true);
		oddButton.addMouseListener(new MouseAdapter(){
			//버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				//커서모양 손가락
				oddButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				oddButton.setIcon(oddButtonEntered);
			}
			//기본
			@Override
			public void mouseExited(MouseEvent e) {
				oddButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				oddButton.setIcon(oddButtonDefault);
			}
			//버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("홀입니다.");
				if(computerSelect%2==1) {
					userScore += computerSelect;
					computerScore -= computerSelect;
				}else {
					userScore -= computerSelect;
					computerScore += computerSelect;
				}
				System.out.println("| 현재 구슬의 개수  | " + userNumber + "번(나) : " + userScore + "개 | 457번 : " + computerScore + "개 | ");
			}
		});
		add(oddButton);
		
		//짝 버튼
		evenButton.setBounds(700, 500, 300, 300);
		evenButton.setBorderPainted(false);
		evenButton.setContentAreaFilled(false);
		evenButton.setFocusPainted(false);
		evenButton.setVisible(true);
		evenButton.addMouseListener(new MouseAdapter(){
			//버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				//커서모양 손가락
				evenButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				evenButton.setIcon(evenButtonEntered);
			}
			//기본
			@Override
			public void mouseExited(MouseEvent e) {
				evenButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				evenButton.setIcon(evenButtonDefault);
			}
			//버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("짝입니다.");
				if(computerSelect%2==0) {
					userScore += computerSelect;
					computerScore -= computerSelect;
				}else {
					userScore -= computerSelect;
					computerScore += computerSelect;
				}
				System.out.println("| 현재 구슬의 개수  | " + userNumber + "번(나) : " + userScore + "개 | 457번 : " + computerScore + "개 | ");
			}
		});
		add(evenButton);
		
		//----------------Label---------------
		Score.setBounds(0, 20, 100, 50);
		Score.setText(userNumber + "(나) : " + userScore + "개\n" + 457 + " : " + computerScore + "개");
		add(Score);
	}
	
	void marbleSetting() {
		int marble;	//사용할 구슬의 개수
		//사용 구슬의 개수 묻기(최대 구슬 사용 개수 50개)
		while(true) {
			System.out.print("몇개의 구슬을 사용하시겠습니까?(최대 구슬 개수 : 50개) -> ");
			marble = sc.nextInt();
			if(marble>50) {
				System.out.println("최대 구슬 사용 개수를 넘었습니다. 다시 입력해 주십시오.");
				System.out.println();
				continue;
			}
			break;
		}
		
		//사용자&컴퓨터 점수 reset
		computerScore = marble;
		userScore = marble;
	}
	// 가장 처음 화면을 그려냄(배경 그리기)
	//public void paint(Graphics g) {
		// screenImage를 0, 0위치에 그려줌
		//g.drawImage(background, 0, 100, null);
	//}

}
