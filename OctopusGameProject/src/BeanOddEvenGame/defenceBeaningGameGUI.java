//����
package BeanOddEvenGame;


import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class defenceBeaningGameGUI extends JFrame{
	//���� ����
	
	//���ڿ� �߰�
	JLabel vsLabel = new JLabel(); //�ؽ�Ʈ�� ������ JLabel ����
	
	//��ư 2�� ����
	JButton user_odd = new JButton("Ȧ");
	JButton user_even = new JButton("¦");
	
	//mouse ��ǥ
	private int mouseX, mouseY;
	
	
	 void beaningFrame(int userNo, int heart){
		 //â�� ���� ����
		 setTitle("�޲ٹ̳���(Ȧ¦����)");
		 //â ������(ũ��) ����
		 setSize(1200, 900);
		 //����ڰ� â ũ�� �ٲ��� ���ϰ���
		 setResizable(false);	
		 //�����, â�� ȭ�� �� �߾ӿ� ���.
		 setLocationRelativeTo(null);
		 //â�� ���� �� ���α׷� ����
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //������(���� x �ִ� ��) ���̰� ����
		 setVisible(true);
		 //
		 setBackground(new Color(0, 0, 0, 0));
		 //
		 setLayout(null);
	 }
			
}
