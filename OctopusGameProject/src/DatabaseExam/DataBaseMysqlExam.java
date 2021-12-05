package DatabaseExam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseMysqlExam {
	 
    private Connection conn; //DB 커넥션 연결 객체
    private static final String USERNAME = "root";//DBMS접속 시 아이디
    private static final String PASSWORD = "mirim";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/OctopusGame";//DBMS접속할 db명
    
    public DataBaseMysqlExam() {
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
        
        
    }
    
    public void insert(String name, int number, int score){
        //쿼리문 준비
        String sql = "insert into ranking (name, number, score) values(?,?,?)";
        
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, number);
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
            } catch (Exception e2) {}
        }        
    }    
    public static void main(String args[]) {
    	DataBaseMysqlExam dm = new DataBaseMysqlExam();
    	dm.insert("나나나", 123, 345);
    }
}