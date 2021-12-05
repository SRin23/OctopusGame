package FCP;
import miniGame.ddddd;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class panGame extends ddddd{
	
		final int BTN_CNT = 32; //각 줄의 버튼 수
		final int BTN_WIDTH = 100; //판의 가로
		final int BTN_HEIGHT = 140; //판의 세로
		static int count = 1; //타이머를 위한 전역변수
		
		JFrame jf;
		String nowColor[]=new String[BTN_CNT]; //판의 색을 저장
		JButton pan[]=new JButton[BTN_CNT]; //판
		
		JLabel timeJl; //남은 시간을 알려줌
		JLabel panCnt; //판의 수를 알려줌
		JLabel playerNo; //플레이어 번호
		JLabel masterNo; //master의 번호
		JLabel cnt; //3..2..1..
		JLabel gameOver; //gameOver
		JLabel score; //결과

		int playerCnt = 0; //player의 판 수(white)
		int masterCnt = 0; //master의 판 수(black)
		
		String playerColor = "white"; //사용자의 판 색
		String masterColor = "black"; //컴퓨터의 판 색
		
		static boolean win; //게임 이겼는지 여부
		Image back = new ImageIcon(panGame.class.getResource("../img/fcpBack.png")).getImage();
		ImageIcon whitePan = new ImageIcon(panGame.class.getResource("../img/whitePan.png"));
		ImageIcon blackPan = new ImageIcon(panGame.class.getResource("../img/blackPan.png"));

		String playerNum;
		
		public void paint(Graphics g){
			g.drawImage(back, 0, 0, null);
		}

		//프레임 생성
		public panGame(String pNum){
			panJf.dispose();
			jf= new JFrame();
			Container c1 = jf.getContentPane();
			playerNum=pNum;
			Color soil = new Color(217, 171, 130);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setTitle("홀짝게임");
			jf.setLayout(null);
			jf.setResizable(false);	
			jf.setSize(1200, 900);
			c1.setBackground(soil);
			jf.setVisible(true);

			//3..2..1..보여주는 라벨
			cnt = new JLabel("3");
			cnt.setBounds(510,250,300,300);
			cnt.setFont(cnt.getFont().deriveFont(300.0f));
			cnt.setForeground(Color.BLACK);
			cnt.setVisible(true);
			c1.add(cnt);
			
			//player 번호 및 색을 알려주는 라벨
			playerNo = new JLabel(playerNum+" : "+playerColor);
			playerNo.setBounds(0,20,150,30);
			playerNo.setFont(playerNo.getFont().deriveFont(25.0f));
			playerNo.setVisible(false);
			c1.add(playerNo);

			//master 번호 및 색을 알려주는 라벨
			masterNo = new JLabel("457 : "+masterColor);
			masterNo.setBounds(1050,20,150,30);
			masterNo.setFont(masterNo.getFont().deriveFont(25.0f));
			masterNo.setVisible(false);
			c1.add(masterNo);

			//남은 시간 보여주는 라벨
			timeJl = new JLabel("남은 시간 : 20");
			timeJl.setBounds(470,30,400,60);
			timeJl.setFont(timeJl.getFont().deriveFont(40.0f));
			timeJl.setVisible(false);
			c1.add(timeJl);
			
			//게임이 끝난 후 판의 개수를 보여주는 라벨
			panCnt = new JLabel();
			panCnt.setBounds(410,400,800,100);
			panCnt.setFont(panCnt.getFont().deriveFont(50.0f));
			panCnt.setForeground(Color.BLACK);
			panCnt.setVisible(false);
			c1.add(panCnt);
			
			int i,a;        
			int pan_y = 160;
			setNowColor();
			for(i=0, a=0; i<BTN_CNT; i++,a++) {
				pan[i] = new JButton();
				
				//시작하기 전까지  비활성화
				pan[i].setVisible(false);
				
				//버튼 줄바꿈을 위한 코드
				if(i == 8 || i == 16 || i == 24 ||i == 32) {
					pan_y += 160;
					a = 0;
				}
				pan[i].setBounds(50+(a*140), pan_y, BTN_WIDTH, BTN_HEIGHT);
				
				final int tmp = i; //이벤트 리스너 안에서 쓰기 위한 상수
				//버튼에 클릭 이벤트 부여
				pan[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//버튼 색 바뀜
						setColorBtn(tmp);
					}
				});
				c1.add(pan[i]);
			}

			gameOver = new JLabel();
			gameOver.setText("GAME OVER");
			gameOver.setBounds(300,300,800,100);
			gameOver.setForeground(Color.BLACK);
			gameOver.setFont(gameOver.getFont().deriveFont(100.0f));
			gameOver.setVisible(false);
			c1.add(gameOver);
	
			score = new JLabel();
			score.setBounds(520,500,300,80);
			score.setFont(score.getFont().deriveFont(50.0f));
			score.setForeground(Color.BLACK);
			score.setVisible(false);
			c1.add(score);
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
			//나머지 채우기
			for(int j=i; j<arr.length; j++) 
				arr[j]=1;
			
			for(i=0; i<BTN_CNT; i++) {
				if(arr[i] == 0) nowColor[i] = playerColor;
				else nowColor[i] = masterColor;
			}	
		}
		
		//nowColor에 따른 버튼 색 바뀜
		private void setColorBtn(int tmp) {
			if(nowColor[tmp].equals(playerColor)) {
				nowColor[tmp] = masterColor;
				pan[tmp].setIcon(blackPan);
			}
			else {
				nowColor[tmp] = playerColor;
				pan[tmp].setIcon(whitePan);
			}
		}
		
		//게임이 마무리된 후 판의 갯수를 셈
		private boolean countPan() {
			for(int i=0; i<BTN_CNT; i++) {
				if(nowColor[i].equals(playerColor)) playerCnt++;
				else masterCnt++;
			}
			if(playerCnt>masterCnt) {
				for(int i=0; i<BTN_CNT; i++)
					pan[i].setIcon(whitePan);
				return true;
			} 
			else if(masterCnt>=playerCnt ) {
				for(int i=0; i<BTN_CNT; i++)
					pan[i].setIcon(blackPan);
				return false;
			}
			return true;
		}
		
		//자동으로 판 뒤집기
		private void com() {
			Random random = new Random();
			int t = random.nextInt(2800)+1100; //판을 뒤집는 시간
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					if(count <= 20) {
						int ran = random.nextInt(32);
						if(nowColor[ran].equals(playerColor)) {
							nowColor[ran] = masterColor;
							pan[ran].setIcon(blackPan);
						}
					}
					else timer.cancel();
				}
			};
			timer.schedule(task, 0 , t);
		}
		void game(){
			playerNo.setVisible(true);
			masterNo.setVisible(true);
			timeJl.setVisible(true);

			for(int i=0; i<BTN_CNT; i++) {
				pan[i].setVisible(true);
				if(nowColor[i].equals(playerColor)) pan[i].setIcon(whitePan);
				else pan[i].setIcon(blackPan);
			}
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					com();
					if(count<=20) {
						timeJl.setText("남은 시간 : "+(20-count));
						
						count++;
					}
					else {
						timeJl.setVisible(false);
						timer.cancel();
						win = countPan();
						
						if(win) score.setText(playerNum+" 이김");
						else score.setText("457 이김");

						gameOver.setVisible(true);
						score.setVisible(true);
						
						panCnt.setText(playerNum+" : "+playerCnt+", 457 : "+masterCnt);
						panCnt.setVisible(true);
						
						for(int i=0; i<BTN_CNT; i++) 
							pan[i].setVisible(false);
						
						try {
							Thread.sleep(2000);
							jf.dispose();
							catchMoleJf.setVisible(true);
							
							return;
						}catch(Exception e) {
							System.out.println(e);
						}
					}
				}
			};
			timer.scheduleAtFixedRate(task, 1000, 1000);
			
		}
		static int countDown=1;

		//판 뒤집기 게임 시작
		public boolean startGame() {
			Timer t = new Timer();
			TimerTask tt = new TimerTask() {

			@Override
			public void run() {
				if(countDown<3) {
					cnt.setText(""+(3-countDown));
					countDown++;
				}
				else {
					t.cancel();
					cnt.setVisible(false);
					game();
				}
			}
		};
		t.scheduleAtFixedRate(tt, 1000,1000);
		return win;
		}
	}
		


	

