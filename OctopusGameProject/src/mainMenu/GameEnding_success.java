package mainMenu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Random;

public class GameEnding_success extends JFrame{
	Container c1 = getContentPane();
	
	private ImageIcon mainDefault = new ImageIcon(GameMain.class.getResource("../img/mainDefault.png"));
	private ImageIcon mainEntered = new ImageIcon(GameMain.class.getResource("../img/mainEntered.png"));
	private ImageIcon rankingDefault = new ImageIcon(GameMain.class.getResource("../img/rankingDefault.png"));
	private ImageIcon rankingEntered = new ImageIcon(GameMain.class.getResource("../img/rankingEntered.png"));
	private JButton ranking;
	private JButton main;
	
	private JTextField setName;
	
	private Image failBackground = new ImageIcon(GameEnding_fail.class.getResource("../img/endingClear.png")).getImage();
	
    private Connection conn; //DB 커넥션 연결 객체
    private static final String USERNAME = "root";//DBMS접속 시 아이디
    private static final String PASSWORD = "yeanwoo0619";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/OctopusGame";//DBMS접속할 db명
    
    private String name;
    private int userNumber;
	private int score;
    
	public GameEnding_success(String userNumber){
		//this.userNumber = Integer.parseInt(userNumber);
		this.userNumber= 123;
		this.score = (int)(Math.random()*346)+101;
		System.out.println(score);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("성공");
		setResizable(false);	
		setSize(1200, 900);
		c1.setLayout(null);
		setLocationRelativeTo(null);

		setName = new JTextField(5);
		setName.setLocation(450, 400);
		setName.setSize(300, 100);
		setName.setFont(setName.getFont().deriveFont(40.0f));
		setName.setHorizontalAlignment(JLabel.CENTER);
		setName.setBackground(Color.white);
		setName.setForeground(Color.black);

		setName.setVisible(true);
		setName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField jf = (JTextField) e.getSource();
				String nameEx = jf.getText();
				setName.setEnabled(false);
				insert(nameEx);
				setName.setFont(setName.getFont().deriveFont(20.0f));
				setName.setText(score + "억이 입금되었습니다.");
			}
		});
		
		main = new JButton(mainDefault);
		main.setBounds(300, 600, 200, 80);
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
				setVisible(false);
			}
		});
		
		
		
		ranking = new JButton(rankingDefault);
		ranking.setBounds(700, 600, 200, 80);
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
				setVisible(false);
			}
		});
		
		
		c1.add(setName);

		c1.add(ranking);
		c1.add(main);

		
		setVisible(true);
	}
	
	public void insert(String name){
		this.name = name;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("드라이버 로딩 성공");
        } catch (Exception e) {
            System.out.println("드라이버 로딩 실패 ");
            try {
                conn.close();
            } catch (SQLException e1) {    }
        }
		
        //쿼리문 준비
        String sql = "insert into ranking (name, number, score) values(?,?,?)";
        
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, this.name);
            pstmt.setInt(2, userNumber);
            pstmt.setInt(3, score);
            
            int result = pstmt.executeUpdate();
            if(result==1) {
                System.out.println("데이터 삽입 성공!");
                
            }
            
        } catch (Exception e) {
            System.out.println("데이터 삽입 실패!");
        }    finally {
            try {
                if(pstmt!=null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
            	}
            }
        }        
	
	
	public void paint(Graphics g){
		g.drawImage(failBackground, 0, 0, null);
	}
	
}
