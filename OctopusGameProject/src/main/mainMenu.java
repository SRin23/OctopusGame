package main;

import javax.swing.JButton;
import javax.swing.JFrame;

public class mainMenu extends JFrame{
	public mainMenu(){
		//------������ ����
		JFrame jf = new JFrame("�޲ٹ� ���� - by ����, ����");
		
		//â ���� �� ����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//�������� ȭ�� ��� ��ġ
		//jf.setLocationRelativeTo(null);
		//������ ������ - ��üȭ�� â���
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setVisible(true);
		
		//-----��ư ����
		JButton b1 = new JButton("���ӽ���");
		JButton b2 = new JButton("����");
			
		//��ư ��ġ �� ũ�� 
		//x,y,w,h
		b1.setBounds(30, 170, 122, 30);
		b2.setBounds(182, 170, 122, 30);
			
		//�����ӿ� ��ư �߰�
		jf.getContentPane().add(b1);
		jf.getContentPane().add(b2);
	}
}
