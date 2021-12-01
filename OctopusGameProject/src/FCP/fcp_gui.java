package FCP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class fcp_gui {
	final int BTN_CNT = 32; //각 줄의 버튼 수
	final int BTN_WIDTH = 100; //판의 가로
	final int BTN_HEIGHT = 100; //판의 세로
	static int count = 1; //타이머를 위한 전역변수
	
	String nowColor[]=new String[BTN_CNT]; //판의 색을 저장
	JButton pan[]=new JButton[BTN_CNT]; //판
	JFrame jf; 
	JLabel timeJl; //남은 시간을 알려줌
	int redCnt = 0; //player의 판 수(red)
	int blueCnt = 0; //master의 판 수(blue)
	
	String playerColor = "red"; //사용자의 판 색
	String masterColor = "blue"; //컴퓨터의 판 색
	
	//프레임 생성
	fcp_gui(){
		jf = new JFrame("판 뒤집기");
		//창을 닫을 시 프로그램 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 창 고정
		jf.setResizable(false);
		//화면 가운데 배치
		//jf.setLocationRelativeTo(null);
		jf.setSize(1200,900);
		//프레임(위에 x 있는 거) 보이게 설정
		jf.setVisible(true);
		
		timeJl=new JLabel("남은 시간 : 20");
		//timeJl.setBounds(0, 0, 200, 30);
		timeJl.setLocation(0,0);
		timeJl.setSize(200,30);
		timeJl.setFont(timeJl.getFont().deriveFont(20.0f));
		//jf.getContentPane().add(timeJl);
		
		int i,a;        
		int pan_y = 160;
		setNowColor();
		for(i=0, a=0; i<BTN_CNT; i++,a++) {
			pan[i] = new JButton();
			
			//시작하기 전까지  비활성화
			pan[i].setEnabled(false);
			
			//버튼 줄바꿈을 위한 코드
			if(i == 8 || i == 16 || i == 24 ||i == 32) {
				pan_y += 140;
				a = 0;
			}
			pan[i].setBounds(50+(a*140), pan_y, BTN_WIDTH, BTN_HEIGHT);
			pan[i].setBackground(Color.BLACK); //시작 전에 모두 검은색
			
			final int tmp = i; //이벤트 리스너 안에서 쓰기 위한 상수
			//버튼에 클릭 이벤트 부여
			pan[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//버튼 색 바뀜
					setColorBtn(tmp);
				}
			});
			
			jf.getContentPane().add(pan[i]);
		}
	}
	
	//초기 색 설정을 위한 함수
	private void setNowColor() {
		Random random = new Random();
		int arr[] = new int[32];
		int zeroCnt = 0;
		int i;
		for( i=0; i<arr.length; i++) {
			int ran = random.nextInt(2);
			if(ran == 0) zeroCnt++;
			if(zeroCnt == 16) break;
				arr[i]= ran;
		}
		//나머지 파란색으로 채우기
		for(int j=i; j<arr.length; j++) {
			arr[j]=1;
		}   
		
		for(i=0; i<BTN_CNT; i++) {
			if(arr[i] == 0) nowColor[i] = playerColor;
			else nowColor[i] = masterColor;
		}	
	}
	
	//nowColor에 따른 버튼 색 바뀜
	private void setColorBtn(int tmp) {
		if(nowColor[tmp].equals(playerColor)) {
			nowColor[tmp] = masterColor;
			pan[tmp].setBackground(Color.BLUE);
		}
		else {
			nowColor[tmp] = playerColor;
			pan[tmp].setBackground(Color.RED);
		}
	}
	
	//게임이 마무리된 후 판의 갯수를 셈
	private boolean countPan() {
		for(int i=0; i<BTN_CNT; i++) {
			if(nowColor[i].equals(playerColor)) redCnt++;
			else blueCnt++;
		}
		if(redCnt>blueCnt) {
			System.out.println("이겼음");
			return true;
		} 
		else if(blueCnt>=redCnt ) {
			System.out.println("졌음"); 
			return false;
		}
		return true;
	}
	
	//자동으로 판 뒤집기
	private void com() {
		Random random = new Random();
		int t = random.nextInt(3000)+1000;
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				if(count <= 20) {
					int ran = random.nextInt(32);
					if(nowColor[ran].equals(playerColor)) {
						nowColor[ran] = masterColor;
						pan[ran].setBackground(Color.BLUE);
					}
				}
				else timer.cancel();
				//랜덤 - 시간, 바꿀 배열의 방
			}
		};
		timer.schedule(task, 0 , t);
	}
	
	//판 뒤집기 게임 시작
	public int startGame(int heart) {

		for(int i=0; i<BTN_CNT; i++) {
			pan[i].setEnabled(true);
			if(nowColor[i].equals(playerColor)) pan[i].setBackground(Color.RED);
			else pan[i].setBackground(Color.BLUE);
		}
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				com();
				if(count<=20) {
					System.out.println("남은 시간 : "+(20-count));
					//timeJl.setText("남은 시간 : "+(20-count));
					
					count++;
				}
				else {
					timer.cancel();
					if(!countPan()) {
						System.out.println(playerColor+" : "+redCnt+", "+ masterColor + " : "+blueCnt);
					}
					else {
						System.out.println(playerColor+" : "+redCnt+", "+ masterColor + " : "+blueCnt);
					}
					System.out.println("게임 끝");
					for(int i=0; i<BTN_CNT; i++) {
						pan[i].setEnabled(false);
					}
				}
			}
		};
		timer.schedule(task, 1000, 1000);
		
		return heart; 
	}
}
	

