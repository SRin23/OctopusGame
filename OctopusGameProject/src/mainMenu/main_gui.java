package mainMenu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import miniGame.panStory;

public class main_gui extends JFrame{
	//텍스트 필드를 통해 얻은 string값
	String userNo;
	JTextField userNoTf;
	
	// bubble buffer를 위해서 설정한 변수
	private Image screenImage;
	private Graphics screenGraphic;

	
	Container c1 = getContentPane();
	
	private ImageIcon gameStartDefault = new ImageIcon(GameMain.class.getResource("../img/gameStartDefault.png"));
	private ImageIcon gameStartEntered = new ImageIcon(GameMain.class.getResource("../img/gameStartEntered.png"));
	private ImageIcon gameHelpDefault = new ImageIcon(GameMain.class.getResource("../img/gameHelpDefault.png"));
	private ImageIcon gameHelpEntered = new ImageIcon(GameMain.class.getResource("../img/gameHelpEntered.png"));
	private ImageIcon rankingDefault = new ImageIcon(GameMain.class.getResource("../img/rankingDefault.png"));
	private ImageIcon rankingEntered = new ImageIcon(GameMain.class.getResource("../img/rankingEntered.png"));
	private Image introBackground = new ImageIcon(GameMain.class.getResource("../img/introBackground.png")).getImage();
	private JButton gameStart;
	private JButton gameHelp;
	private JButton ranking;
	private panStory PS;
	
	main_gui(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("쭈꾸미게임");
		c1.setLayout(null);
		setResizable(false);	
		setLocationRelativeTo(null);
		setSize(1200, 900);
		setVisible(true);
		c1.setBackground(Color.black);
	
		
		gameStart = new JButton(gameStartDefault);
		gameStart.setBounds(500, 450, 200, 80);
		gameStart.setBorderPainted(false);
		gameStart.setContentAreaFilled(false);
		gameStart.setFocusPainted(false);
		gameStart.setVisible(true);
		gameStart.addMouseListener(new MouseAdapter(){
			//버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				//커서모양 손가락
				gameStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
				gameStart.setIcon(gameStartEntered);
			}
			//기본
			@Override
			public void mouseExited(MouseEvent e) {
				gameStart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				gameStart.setIcon(gameStartDefault);
			}
			//버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				
				PS = new panStory("적을 말이 현재 들어가는건가요?","123");
				setVisible(false);
			}
		});
		
		
		gameHelp = new JButton(gameHelpDefault);
		gameHelp.setBounds(500, 550, 200, 80);
		gameHelp.setBorderPainted(false);
		gameHelp.setContentAreaFilled(false);
		gameHelp.setFocusPainted(false);
		gameHelp.setVisible(true);
		gameHelp.addMouseListener(new MouseAdapter(){
			//버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				//커서모양 손가락
				gameHelp.setCursor(new Cursor(Cursor.HAND_CURSOR));
				gameHelp.setIcon(gameHelpEntered);
			}
			//기본
			@Override
			public void mouseExited(MouseEvent e) {
				gameHelp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				gameHelp.setIcon(gameHelpDefault);
			}
			//버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		
		
		ranking = new JButton(rankingDefault);
		ranking.setBounds(500, 650, 200, 80);
		ranking.setBorderPainted(false);
		ranking.setContentAreaFilled(false);
		ranking.setFocusPainted(false);
		ranking.setVisible(true);
		ranking.addMouseListener(new MouseAdapter(){
			//버튼 위에 마우스
			@Override
			public void mouseEntered(MouseEvent e) {
				//커서모양 손가락
				ranking.setCursor(new Cursor(Cursor.HAND_CURSOR));
				ranking.setIcon(rankingEntered);
			}
			//기본
			@Override
			public void mouseExited(MouseEvent e) {
				ranking.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				ranking.setIcon(rankingDefault);
			}
			//버튼을 마우스가 누를때
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
	
		
		c1.add(gameStart);
		c1.add(gameHelp);
		c1.add(ranking);
	}
	// 가장 처음 화면을 그려냄
		public void paint(Graphics g) {
			g.drawImage(introBackground, 0, 0, null);
		}

}
