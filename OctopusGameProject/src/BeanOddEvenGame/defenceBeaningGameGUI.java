package BeanOddEvenGame;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class defenceBeaningGameGUI extends JFrame{
	 void beaningFrame(int userNo, int heart){
			
			//������ ����
		 	JFrame bf = new JFrame("����ġ��(Ȧ¦)");
		 	
			//â�� ���� �� ���α׷� ����
		 	bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 	bf.setSize(1200,900);
		 	
			//������(���� x �ִ� ��) ���̰� ����
		 	bf.setVisible(true);

			//��ư 2�� ����
			JButton user_odd = new JButton("Ȧ");
			JButton user_even = new JButton("¦");
			
			//���ڿ� �߰�
			JLabel vsLabel = new JLabel(); //�ؽ�Ʈ�� ������ JLabel ����

			vsLabel.setText(userNo + "�� VS 457��");
			vsLabel.setBounds(10,10,500,30);
			
			//���ڿ� �߰�
			JLabel heartLabel = new JLabel(); //�ؽ�Ʈ�� ������ JLabel ����

			heartLabel.setText("���� ����� �� : " + heart);
			heartLabel.setBounds(900,10,500,30);
			
			//label ���� ũ�� ����
			vsLabel.setFont(vsLabel.getFont().deriveFont (30.0f));
			heartLabel.setFont(vsLabel.getFont().deriveFont (30.0f));

			//x,y,w,h
			user_odd.setBounds(300, 630, 150, 150);
			user_even.setBounds(750, 630, 150, 150);
			user_odd.setFont(user_odd.getFont().deriveFont(30.0f));
			user_even.setFont(user_even.getFont().deriveFont(30.0f));
			
			bf.getContentPane().add(vsLabel);
			bf.getContentPane().add(heartLabel);
			bf.getContentPane().add(user_odd);
			bf.getContentPane().add(user_even);
			
			
	    }
}
