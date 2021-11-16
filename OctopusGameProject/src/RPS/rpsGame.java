package RPS;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class rpsGame {
	static String user_sel="";
	static JButton user_sissors;
	static JButton user_rock;
	static JButton user_paper ;
	
	static JButton master_rps;
	
	static JLabel first;
	
	static int win_cnt=0;
	static int turn=0; //삼세판으로 진행

	static void resetBtn() { //클릭 이벤트 후 세팅 초기화
		user_sissors.setEnabled(true);
		user_rock.setEnabled(true);
		user_paper.setEnabled(true);
		
		user_rock.setBackground(Color.WHITE);
		user_paper.setBackground(Color.WHITE);
		user_sissors.setBackground(Color.WHITE);
		
		user_rock.setForeground(Color.BLACK);
		user_paper.setForeground(Color.BLACK);
		user_sissors.setForeground(Color.BLACK);
		
		master_rps.setBackground(Color.WHITE);
		master_rps.setForeground(Color.BLACK);

	}
	
	static boolean startGame() { //게임 실행
		Random rand = new Random();
		 
		int ran;  
		String rpsList[]= {"가위","바위","보"};
		 //유저가 고른 것(버튼 입력)
		String master_sel=""; //컴이 고른 것(랜덤)
		
		ran = rand.nextInt(3);
		master_sel=rpsList[ran];
		master_rps.setText(master_sel);
				
		//비긴 경우
		if(user_sel.equals(master_sel)) {
			master_rps.setBackground(Color.YELLOW);

			user_paper.setBackground(Color.YELLOW);
			user_rock.setBackground(Color.YELLOW);
			user_sissors.setBackground(Color.YELLOW);
			
			user_paper.setForeground(Color.BLACK);
			user_rock.setForeground(Color.BLACK);
			user_sissors.setForeground(Color.BLACK);
			
			master_rps.setForeground(Color.BLACK);

		}
		//유저-가위
		else if(user_sel.equals("가위")) {
			System.out.println(user_sel+"  zzz");
			//이긴 경우
			if(master_sel.equals("보")) {
				master_rps.setBackground(Color.GREEN);
				
				user_paper.setBackground(Color.GREEN);
				user_rock.setBackground(Color.GREEN);
				user_sissors.setBackground(Color.GREEN);
				
				return true;
			}
			//진 경우
			else if(master_sel.equals("바위")) {
				master_rps.setBackground(Color.RED);
				
				user_paper.setBackground(Color.RED);
				user_rock.setBackground(Color.RED);
				user_sissors.setBackground(Color.RED);
				
				return false;
			}
		}
		
		//유저-바위
		else if(user_sel.equals("바위")) {
			System.out.println(user_sel+"  zzz");
			//이긴 경우
			if(master_sel.equals("가위")) {
				master_rps.setBackground(Color.GREEN);
				
				user_paper.setBackground(Color.GREEN);
				user_rock.setBackground(Color.GREEN);
				user_sissors.setBackground(Color.GREEN);
				
				return true;
			}
			//진 경우
			else if(master_sel.equals("보")) {
				master_rps.setBackground(Color.RED);
				
				user_paper.setBackground(Color.RED);
				user_rock.setBackground(Color.RED);
				user_sissors.setBackground(Color.RED);
			}
		}
		//유저-보
		else if(user_sel.equals("보")) {
			System.out.println(user_sel+"  zzz");
			//이긴 경우
			if(master_sel.equals("바위")) {
				master_rps.setBackground(Color.GREEN);
				
				user_paper.setBackground(Color.GREEN);
				user_rock.setBackground(Color.GREEN);
				user_sissors.setBackground(Color.GREEN);
				
				return true;
			}
			//진 경우
			else if(master_sel.equals("가위")) {
				master_rps.setBackground(Color.RED);
				
				user_paper.setBackground(Color.RED);
				user_rock.setBackground(Color.RED);
				user_sissors.setBackground(Color.RED);
				
				return false;
			}
		}
		return true;
	}
		
	void onclick() {
		user_rock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				user_sel="바위";
				if(turn >= 2) {
					master_rps.setBackground(Color.BLACK);
					
					user_paper.setBackground(Color.BLACK);
					user_rock.setBackground(Color.BLACK);
					user_sissors.setBackground(Color.BLACK);
					
					master_rps.setEnabled(false);
					
					user_paper.setEnabled(false);
					user_rock.setEnabled(false);
					user_sissors.setEnabled(false);
					
					if(win_cnt > 2) {
						first.setVisible(true);
						first.setText("선공");
					}
					else {
						first.setVisible(true);
						first.setText("후공");
					}
					
					return ;
				}
				
				turn++;
				System.out.println(turn);
				if(startGame()) win_cnt++;
			}
		});
		
		user_paper.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				user_sel="보";
				if(turn >= 2) {
					master_rps.setBackground(Color.BLACK);
					
					user_paper.setBackground(Color.BLACK);
					user_rock.setBackground(Color.BLACK);
					user_sissors.setBackground(Color.BLACK);
					
					master_rps.setEnabled(false);
					
					user_paper.setEnabled(false);
					user_rock.setEnabled(false);
					user_sissors.setEnabled(false);
					
					if(win_cnt > 2) {
						first.setVisible(true);
						first.setText("선공");
					}
					else {
						first.setVisible(true);
						first.setText("후공");
					}
					return ;
				}

				turn++;
				System.out.println(turn);
				if(startGame()) win_cnt++;
			}
		});
		
		user_sissors.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				user_sel="가위";
				if(turn >= 2) {
					master_rps.setBackground(Color.BLACK);
					
					user_paper.setBackground(Color.BLACK);
					user_rock.setBackground(Color.BLACK);
					user_sissors.setBackground(Color.BLACK);
					
					master_rps.setEnabled(false);
					
					user_paper.setEnabled(false);
					user_rock.setEnabled(false);
					user_sissors.setEnabled(false);
					
					if(win_cnt > 2) {
						first.setVisible(true);
						first.setText("선공");
					}
					else {
						first.setVisible(true);
						first.setText("후공");
					}
					return ;
					
				}
				turn++;
				System.out.println(turn);
				if(startGame()) win_cnt++;
			}
		});

	}
	public rpsGame(String userNo, String masterNo) {
		//프레임 생성
				JFrame jf = new JFrame("선후공 정하기 - 가위바위보");
				//창을 닫을 시 프로그램 종료
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				jf.setSize(1200,900);
				//프레임(위에 x 있는 거) 보이게 설정
				jf.setVisible(true);


				//텍스트 설정
				user_paper = new JButton("보");
				user_rock = new JButton("바위");
				user_sissors = new JButton("가위");
				
				//x y w h 설정
				user_sissors.setBounds(215, 630, 150, 150);
				user_rock.setBounds(515, 630, 150, 150);
				user_paper.setBounds(815, 630, 150, 150);
				
				//배경색 지정
				user_rock.setBackground(Color.WHITE);
				user_paper.setBackground(Color.WHITE);
				user_sissors.setBackground(Color.WHITE);
				
				//프레임에 붙이기
				jf.getContentPane().add(user_rock);
				jf.getContentPane().add(user_sissors);
				jf.getContentPane().add(user_paper);
				
				//텍스트 설정
				master_rps=new JButton("");

				//x y w h
				master_rps.setBounds(515, 200, 150, 150);

				//배경색 지정
				master_rps.setBackground(Color.WHITE);
				
				//프레임에 붙이기
				jf.getContentPane().add(master_rps);
				
				//문자열 추가
				JLabel vsLabel = new JLabel(userNo+" vs "+masterNo); 
				vsLabel.setBounds(10, 10,200,30);
				//label 글자 크기 조절
				vsLabel.setFont(vsLabel.getFont ().deriveFont (30.0f));
				
				jf.getContentPane().add(vsLabel);		
				
				//선후공 
				first = new JLabel();
				first.setFont(vsLabel.getFont ().deriveFont (70.0f));
				first.setHorizontalAlignment(JLabel.CENTER);
				first.setVisible(false);
				jf.getContentPane().add(first);
	}
}
