package mainMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class RankingGUI extends JFrame{
	Container c1 = getContentPane();
	private int idNum;
	private String name;
	private int number;
	private int score;
	private Date date;
	
	Connection conn = null;
	Statement stmt = null; 
	
	RankingGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ranking");
		setResizable(false);	
		setSize(1200, 900);
		setBackground(Color.black);
		setForeground(Color.white);
		c1.setBackground(Color.black);
		c1.setForeground(Color.white);
		add(new setArea(), BorderLayout.CENTER);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	class setArea extends JPanel{
		JTextArea jTextArea;
		JScrollPane scrollPane;
        setArea(){        	
        	jTextArea=new JTextArea(29, 74);
        	jTextArea.setFont(jTextArea.getFont().deriveFont(20.0f));
    		jTextArea.setBackground(Color.black);
    		jTextArea.setForeground(Color.white); // 글자 색
    		jTextArea.setEditable(false);
    		jTextArea.append("\n");
    		jTextArea.append("\t\t    회차\t    이름\t   번호\t   금액\t      날짜\n");
    		jTextArea.append("\t                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    		linkDB();
    		
    		jTextArea.addKeyListener(new KeyAdapter() {
    			@Override
				public void keyPressed(KeyEvent e) {
					setVisible(false);
					dispose();
					GameMainGUI gm = new GameMainGUI();
				}
			});
    		
    		scrollPane = new JScrollPane(jTextArea);
    		scrollPane.setBackground(Color.black);
    		scrollPane.setForeground(Color.white);
    		
            add(scrollPane);
            
            
        }
        
        void linkDB(){
        	int length = 0;
    		try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			System.out.println("드라이브 연결이 잘 됨");
    		} catch(ClassNotFoundException ee) {
    			System.out.println("DB 연결 드라이브가 없음");
    		}
    		
    		String url = "jdbc:mysql://localhost:3306/OctopusGame";
    		String id = "root";
    		String pw = "mirim";	
    		
    		try {
    			conn = DriverManager.getConnection(url, id, pw);
    		} catch(SQLException ee) {
    			System.out.println("DB 연결 실패");
    		}
    		
    		try {
    			stmt = conn.createStatement();
    		} catch(SQLException ee) {
    			System.out.println("작업 처리 객체 생성 실패");
    		}
    		
    		ResultSet rs = null;
    		try {
    			rs = stmt.executeQuery("select * from ranking");
    			while(rs.next()) {
    				length++;
    				idNum = rs.getInt("id");
    				name = rs.getString("name");
    				number = rs.getInt("number");
    				score = rs.getInt("score");
    				date = rs.getDate("date");
    				System.out.println(idNum + "\t" + name + "\t" + number + "\t" + score  + "\t" + date);
    				
    				jTextArea.append("\t\t   " + idNum + "회\t   " + name + "\t    " + number + "\t    " + score  + "\t " + date + "\n");
    				jTextArea.setCaretPosition(jTextArea.getDocument().getLength());  // 맨아래로 스크롤한다.
    			}
    			if(length==0) {
    				jTextArea.append("\t\t\t       아직 우승자가 존재하지 않습니다.\n");
    				jTextArea.setCaretPosition(jTextArea.getDocument().getLength());  // 맨아래로 스크롤한다.
    			}
    		} catch(SQLException ee) {
    			System.out.println("명령어 전송 실패 "+ee.toString());
    		}
    		
    		try {
    			rs.close();
    			stmt.close();
    			conn.close();
    		} catch(SQLException ee) {
    			System.out.println("접속 종료 실패 "+ee.toString());
    		}
    	}
	}
}
