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
	
	static int win_cnt=0;
	static int turn=0; //�＼������ ����

	static void resetBtn() { //Ŭ�� �̺�Ʈ �� ���� �ʱ�ȭ
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
	
	static boolean startGame() { //���� ����
		Random rand = new Random();
		 
		int ran;  
		String rpsList[]= {"����","����","��"};
		 //������ �� ��(��ư �Է�)
		String master_sel=""; //���� �� ��(����)
		
		ran = rand.nextInt(3);
		master_sel=rpsList[ran];
		master_rps.setText(master_sel);
				
		//��� ���
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
		//����-����
		else if(user_sel.equals("����")) {
			System.out.println(user_sel+"  zzz");
			//�̱� ���
			if(master_sel.equals("��")) {
				master_rps.setBackground(Color.GREEN);
				
				user_paper.setBackground(Color.GREEN);
				user_rock.setBackground(Color.GREEN);
				user_sissors.setBackground(Color.GREEN);
				
				return true;
			}
			//�� ���
			else if(master_sel.equals("����")) {
				master_rps.setBackground(Color.RED);
				
				user_paper.setBackground(Color.RED);
				user_rock.setBackground(Color.RED);
				user_sissors.setBackground(Color.RED);
				
				return false;
			}
		}
		
		//����-����
		else if(user_sel.equals("����")) {
			System.out.println(user_sel+"  zzz");
			//�̱� ���
			if(master_sel.equals("����")) {
				master_rps.setBackground(Color.GREEN);
				
				user_paper.setBackground(Color.GREEN);
				user_rock.setBackground(Color.GREEN);
				user_sissors.setBackground(Color.GREEN);
				
				return true;
			}
			//�� ���
			else if(master_sel.equals("��")) {
				master_rps.setBackground(Color.RED);
				
				user_paper.setBackground(Color.RED);
				user_rock.setBackground(Color.RED);
				user_sissors.setBackground(Color.RED);
			}
		}
		//����-��
		else if(user_sel.equals("��")) {
			System.out.println(user_sel+"  zzz");
			//�̱� ���
			if(master_sel.equals("����")) {
				master_rps.setBackground(Color.GREEN);
				
				user_paper.setBackground(Color.GREEN);
				user_rock.setBackground(Color.GREEN);
				user_sissors.setBackground(Color.GREEN);
				
				return true;
			}
			//�� ���
			else if(master_sel.equals("����")) {
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
				user_sel="����";
				if(turn >= 2) {
					master_rps.setBackground(Color.BLACK);
					
					user_paper.setBackground(Color.BLACK);
					user_rock.setBackground(Color.BLACK);
					user_sissors.setBackground(Color.BLACK);
					
					master_rps.setEnabled(false);
					
					user_paper.setEnabled(false);
					user_rock.setEnabled(false);
					user_sissors.setEnabled(false);
					
					if(win_cnt > 2) System.out.println("����");
					else System.out.println("�İ�");
					
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
				user_sel="��";
				if(turn >= 2) {
					master_rps.setBackground(Color.BLACK);
					
					user_paper.setBackground(Color.BLACK);
					user_rock.setBackground(Color.BLACK);
					user_sissors.setBackground(Color.BLACK);
					
					master_rps.setEnabled(false);
					
					user_paper.setEnabled(false);
					user_rock.setEnabled(false);
					user_sissors.setEnabled(false);
					
					if(win_cnt > 2) System.out.println("����");
					else System.out.println("�İ�");
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
				user_sel="����";
				if(turn >= 2) {
					master_rps.setBackground(Color.BLACK);
					
					user_paper.setBackground(Color.BLACK);
					user_rock.setBackground(Color.BLACK);
					user_sissors.setBackground(Color.BLACK);
					
					master_rps.setEnabled(false);
					
					user_paper.setEnabled(false);
					user_rock.setEnabled(false);
					user_sissors.setEnabled(false);
					
					if(win_cnt > 2) System.out.println("����");
					else System.out.println("�İ�");
					return ;
					
				}
				turn++;
				System.out.println(turn);
				if(startGame()) win_cnt++;
			}
		});

	}
	public rpsGame(String userNo, String masterNo) {
		//������ ����
				JFrame jf = new JFrame("���İ� ���ϱ� - ����������");
				//â�� ���� �� ���α׷� ����
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				jf.setSize(1200,900);
				//������(���� x �ִ� ��) ���̰� ����
				jf.setVisible(true);

				//�ؽ�Ʈ ����
				user_paper = new JButton("��");
				user_rock = new JButton("����");
				user_sissors = new JButton("����");
				
				//x y w h ����
				user_sissors.setBounds(200, 630, 150, 150);
				user_rock.setBounds(500, 630, 150, 150);
				user_paper.setBounds(800, 630, 150, 150);
				
				//���� ����
				user_rock.setBackground(Color.WHITE);
				user_paper.setBackground(Color.WHITE);
				user_sissors.setBackground(Color.WHITE);
				
				//�����ӿ� ���̱�
				jf.getContentPane().add(user_rock);
				jf.getContentPane().add(user_sissors);
				jf.getContentPane().add(user_paper);
				
				//�ؽ�Ʈ ����
				master_rps=new JButton("");

				//x y w h
				master_rps.setBounds(500, 200, 150, 150);

				//���� ����
				master_rps.setBackground(Color.WHITE);
				
				//�����ӿ� ���̱�
				jf.getContentPane().add(master_rps);
				
				//���ڿ� �߰�
				JLabel vsLabel = new JLabel(userNo+" vs "+masterNo); 
				vsLabel.setBounds(10, 10,200,30);
				//label ���� ũ�� ����
				vsLabel.setFont(vsLabel.getFont ().deriveFont (30.0f));
				
				jf.getContentPane().add(vsLabel);		
	}
}
