package mainMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;



public class GameEnding_fail {
	JFrame jf;
	Container c1;
	
	private ImageIcon mainDefault = new ImageIcon(GameMain.class.getResource("../img/mainDefault.png"));
	private ImageIcon mainEntered = new ImageIcon(GameMain.class.getResource("../img/mainEntered.png"));
	private ImageIcon rankingDefault = new ImageIcon(GameMain.class.getResource("../img/rankingDefault.png"));
	private ImageIcon rankingEntered = new ImageIcon(GameMain.class.getResource("../img/rankingEntered.png"));
	private JButton ranking;
	private JButton main;
	
	private Image failBackground = new ImageIcon(GameEnding_fail.class.getResource("../img/endingFail.png")).getImage();
	
	public GameEnding_fail(){

		jf = new JFrame();
		c1 = jf.getContentPane();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("실패");
		jf.setResizable(false);	
		jf.setSize(1200, 900);
		c1.setLayout(null);
		jf.setLocationRelativeTo(null);
		
		main = new JButton(mainDefault);
		main.setBounds(300, 400, 200, 80);
		main.setBorderPainted(false);
		main.setContentAreaFilled(false);
		main.setFocusPainted(false);
		main.setVisible(true);
		main.addMouseListener(new MouseAdapter() {
			// 버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				// 커서모양 손가락
				main.setCursor(new Cursor(Cursor.HAND_CURSOR));
				main.setIcon(mainEntered);
			}

			// 기본
			@Override
			public void mouseExited(MouseEvent e) {
				main.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				main.setIcon(mainDefault);
			}

			// 버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				GameMainGUI GG = new GameMainGUI();
				jf.setVisible(false);
			}
		});
		
		

		ranking = new JButton(rankingDefault);
		ranking.setBounds(700, 400, 200, 80);
		ranking.setBorderPainted(false);
		ranking.setContentAreaFilled(false);
		ranking.setFocusPainted(false);
		ranking.setVisible(true);
		ranking.addMouseListener(new MouseAdapter() {
			// 버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				// 커서모양 손가락
				ranking.setCursor(new Cursor(Cursor.HAND_CURSOR));
				ranking.setIcon(rankingEntered);
			}

			// 기본
			@Override
			public void mouseExited(MouseEvent e) {
				ranking.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				ranking.setIcon(rankingDefault);
			}

			// 버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				RankingGUI RK = new RankingGUI();
				jf.setVisible(false);
			}
		});
		jf.add(ranking);
		jf.add(main);
		
		jf.setVisible(true);
	}
	
	public void paint(Graphics g){
		g.drawImage(failBackground, 0, 0, null);
	}
	
}
