package FCP;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class fcpTest {

	JFrame jf; 
	JButton playerPan;
	JButton masterPan;
	fcpTest(){
		jf = new JFrame("�� ������");
		//â�� ���� �� ���α׷� ����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ â ����
		jf.setResizable(false);
		//ȭ�� ��� ��ġ
		//jf.setLocationRelativeTo(null);
		jf.setSize(1200,900);
		//������(���� x �ִ� ��) ���̰� ����
		jf.setVisible(true);
		
		playerPan = new JButton();
		masterPan = new JButton();
		
		playerPan.setBounds(20,10,100,100);
		masterPan.setBounds(170,10,100,100);
		
		jf.getContentPane().add(playerPan);
		jf.getContentPane().add(masterPan);
		
		
		String[] colorList_player= {"red","blue","yellow","green","white","black"};
		String[] colorList_master= {"blue","red","green","yellow","black","white"};
		
		JList playerList = new JList(colorList_player);
		playerList.setBounds(200,200,300,300);
		jf.getContentPane().add(playerList);
	}
	

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new fcpTest();
	}

}
