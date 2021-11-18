//수비
package BeanOddEvenGame;


import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class defenceBeaningGameGUI extends JFrame{
	//변수 정의
	
	//문자열 추가
	JLabel vsLabel = new JLabel(); //텍스트를 보여줄 JLabel 생성
	
	//버튼 2개 생성
	JButton user_odd = new JButton("홀");
	JButton user_even = new JButton("짝");
	
	//mouse 좌표
	private int mouseX, mouseY;
	
	
	 void beaningFrame(int userNo, int heart){
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
		 //프레임(위에 x 있는 거) 보이게 설정
		 setVisible(true);
		 //
		 setBackground(new Color(0, 0, 0, 0));
		 //
		 setLayout(null);
	 }
			
}
