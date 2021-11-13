package BeanOddEvenGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseMysqlExam {
	public static void main(String[] args) {
		try {
			// Class.forName("org.git.mm.mysql.Driver");// 1. jdbc 드라이브 영역
			Class.forName("com.mysql.cj.jdbc.Driver"); // 로 변경이 됨
			System.out.println("드라이브 연결이 잘 됨");
		} catch(ClassNotFoundException ee) {
			System.out.println("DB 연결 드라이브가 없음");
		}
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/OctopusGame";
		String id = "root";
		String pw = "#";	//비밀번호 바꾸기
		
		try {
			conn = DriverManager.getConnection(url, id, pw);// 2. 데이터베이스 연결
		} catch(SQLException ee) {
			System.out.println("DB 연결 실패");
		}
		
		Statement stmt = null; // 3. 명령어 처리 객체 생성
		try {
			stmt = conn.createStatement();
		} catch(SQLException ee) {
			System.out.println("작업 처리 객체 생성 실패");
		}
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("select * from rank");// 4. 결과보기
			while(rs.next()) {
				System.out.println(rs.getString("ranking")+"-" + 
						rs.getString("name")+"-"+
						rs.getString("score"));
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
