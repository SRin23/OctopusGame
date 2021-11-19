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
	public static final int BTN_CNT = 32; //각 줄의 버튼 수
	public static final int PAN_WIDTH = 100; //판의 가로
	public static final int PAN_HEIGHT = 100; //판의 세로
	static int count = 1;
	
	String nowColor[]=new String[BTN_CNT]; //판의 색을 저장
	JButton pan[]=new JButton[BTN_CNT]; //판
	JFrame jf; 
	JLabel timeJl; //남은 시간을 알려줌
	public static int redCnt = 0; //player의 판 수(red)
	public static int blueCnt = 0; //master의 판 수(blue)
	
	//프레임 생성
	fcp_gui(){
		jf = new JFrame("판 뒤집기");
		//창을 닫을 시 프로그램 종료
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		jf.setSize(1200,900);
		//프레임(위에 x 있는 거) 보이게 설정
		jf.setVisible(true);
		
		timeJl=new JLabel("남은 시간 : 20");
		timeJl.setBounds(1200, 20, 200, 30);
		//jf.getContentPane().add(timeJl);
		
		int i,a;        
		int pan_y = 100;
		setNowColor();
		for(i=0, a=0; i<BTN_CNT; i++,a++) {
			pan[i] = new JButton();
			
			//시작하기 전까지  비활성화
			pan[i].setEnabled(false);
			
			//버튼 줄바꿈을 위한 코드
			if(i == 8 || i == 16 || i == 24 ||i == 32) {
				pan_y += 170;
				a = 0;
			}
			pan[i].setBounds(50+(a*140), pan_y, PAN_WIDTH, PAN_HEIGHT);
			if(nowColor[i] =="red") pan[i].setBackground(Color.RED);
			else pan[i].setBackground(Color.BLUE);
			
			final int tmp = i; //이벤트 리스너 안에서 쓰기 위한 상수
			//버튼들에 클릭 이벤트 부여
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
			if(arr[i] == 0) nowColor[i]="red";
			else nowColor[i]="blue";
		}	
	}
	
	//nowColor에 따른 버튼 색 바뀜
	private void setColorBtn(int tmp) {
		if(nowColor[tmp] == "red") {
			nowColor[tmp]="blue";
			pan[tmp].setBackground(Color.BLUE);
		}
		else {
			nowColor[tmp]="red";
			pan[tmp].setBackground(Color.RED);
		}
	}
	
	//게임이 마무리된 후 판의 갯수를 셈
	private boolean countPan() {
		for(int i=0; i<BTN_CNT; i++) {
			if(nowColor[i] =="red") redCnt++;
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
		int t = random.nextInt(3000)+200;
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				//랜덤 - 시간, 바꿀 배열의 방
				int ran = random.nextInt(32);
				if(nowColor[ran].equals("red")) {
					nowColor[ran] ="blue";
					pan[ran].setBackground(Color.BLUE);
				}
			}
		};
		timer.schedule(task, t , 1000);
	}
	
	//판 뒤집기 게임 시작
	public int startGame(int heart) {

		for(int j=0; j<BTN_CNT; j++) {
			pan[j].setEnabled(true);
		}
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				com();
				if(count<=20) {
					System.out.println("남은시간 : "+(20-count));
					count++;
				}
				else {
					timer.cancel();
					if(!countPan()) {
						System.out.println("red : "+redCnt+", blue : "+blueCnt);
					}
					else {
						System.out.println("red : "+redCnt+", blue : "+blueCnt);
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
	

