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
	//bubble buffer를 위해서 설정한 변수
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
		setTitle("게임 방법");
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
	
	//가장 처음 화면을 그려냄
		public void paint(Graphics g) {
			//이미지를 만든 후, screenImage에 저장
			screenImage = createImage(GameMain.SCREEN_WIDTH, GameMain.SCREEN_HEIGHT);
			//screenImage를 통해 그래픽을 가져와 screenGraphic에 저장
			screenGraphic = screenImage.getGraphics();
			
			//함수 호출
			screenDraw(screenGraphic);
			
			//screenImage를 0, 0위치에 그려줌
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
			this.repaint();	//다시 paint함수를 불러냄(계속해서 화면을 그림)
		}
	
}
