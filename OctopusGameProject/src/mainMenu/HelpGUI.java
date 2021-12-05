package mainMenu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import miniGame.connectAll;

public class HelpGUI extends JFrame{
	//bubble buffer�� ���ؼ� ������ ����
	private Image screenImage;
	private Graphics screenGraphic;
	private String userNumber;
	
	Container c1 = getContentPane();
	
	private Image explain0 = new ImageIcon(GameMain.class.getResource("../img/explain0.png")).getImage();
	private Image explain1 = new ImageIcon(GameMain.class.getResource("../img/explain1.png")).getImage();
	private Image explain2 = new ImageIcon(GameMain.class.getResource("../img/explain2.png")).getImage();
	private Image explain3 = new ImageIcon(GameMain.class.getResource("../img/explain3.png")).getImage();
	private Image explain4 = new ImageIcon(GameMain.class.getResource("../img/explain4.png")).getImage();
	private Image explain5 = new ImageIcon(GameMain.class.getResource("../img/explain5.png")).getImage();
	
	int bgIdx = 0;
	
	HelpGUI(String userNumber){
		tihs.userNumber = userNumber;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("���� ���");
		c1.setLayout(null);
		setResizable(false);	
		setSize(1200, 900);
		setVisible(true);
		setLocationRelativeTo(null);
		c1.setBackground(Color.black);
		
		addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==39) {
        			bgIdx++;
        		}else if(e.getKeyCode()==37) {
        			bgIdx--;
        			if(bgIdx<-1) {
        				bgIdx = -1;
        			}
        		}
        	}
        });
		
	}
	
	//���� ó�� ȭ���� �׷���
		public void paint(Graphics g) {
			//�̹����� ���� ��, screenImage�� ����
			screenImage = createImage(GameMain.SCREEN_WIDTH, GameMain.SCREEN_HEIGHT);
			//screenImage�� ���� �׷����� ������ screenGraphic�� ����
			screenGraphic = screenImage.getGraphics();
			
			//�Լ� ȣ��
			screenDraw(screenGraphic);
			
			//screenImage�� 0, 0��ġ�� �׷���
			g.drawImage(screenImage,  0,  0,  null);
		}
		
		public void screenDraw(Graphics g) {
			switch(bgIdx) {
				case -1 : setVisible(false); GameMainGUI gmain = new GameMainGUI(); break; 
				case 0: g.drawImage(explain0, 0, 0, null); break;
				case 1: g.drawImage(explain1, 0, 0, null); break;
				case 2: g.drawImage(explain2, 0, 0, null); break;
				case 3: g.drawImage(explain3, 0, 0, null); break;
				case 4: g.drawImage(explain4, 0, 0, null); break;
				case 5: g.drawImage(explain5, 0, 0, null); break;
				default: setVisible(false); connectAll mg = new connectAll(userNumber); break;
			}
			this.repaint();	//�ٽ� paint�Լ��� �ҷ���(����ؼ� ȭ���� �׸�)
		}
	
}
