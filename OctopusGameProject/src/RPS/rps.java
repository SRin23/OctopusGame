package RPS;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class rps {
	JFrame jf; 
	static final int BTN_CNT = 3; //���������� ��ư�� ��
	JButton user_rps[] = new JButton[BTN_CNT];

	static final int BTN_WIDTH = 150;
	static final int BTN_HEIGHT = 150;
	
	String r = "����";
	String p = "��";
	String s = "����";
	
	rps(){
		jf = new JFrame("����������");
		//â�� ���� �� ���α׷� ����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ â ����
		jf.setResizable(false);
		//ȭ�� ��� ��ġ
		//jf.setLocationRelativeTo(null);
		jf.setSize(1200,900);
		//������(���� x �ִ� ��) ���̰� ����
		jf.setVisible(true);
		
		for(int i=0; i<BTN_CNT; i++) {
			user_rps[i]=new JButton("");
			user_rps[i].setBounds(215+(i*300),630, 150,150);
			
			jf.getContentPane().add(user_rps[i]);
		}
		user_rps[0].setText(r); //����
		user_rps[1].setText(p); //��
		user_rps[2].setText(s); //����
	}
}
