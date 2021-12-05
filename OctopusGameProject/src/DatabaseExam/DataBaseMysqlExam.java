package DatabaseExam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseMysqlExam {
	static Connection conn = null;
	static Statement stmt = null; 
    String table;
 
    public DataBaseMysqlExam() {
        this.table = "OctopusGame";
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
            this.stmt = conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 삽입
    public void insert(String name, int number, int score) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into OctopusGame (name, number, score) values(")
                .append("'" + name + "',")
                .append(number + ",")
                .append(score)
                .append(");")
                .toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


	public void delete(int id) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("delete from OctopusGame where id = ")
                .append(id)
                .append(";")
                .toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 수정
    public void update(int id, String name, int score, int number) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("update OctopusGame set")
                .append(" name = ")
                .append("'" + name + "',")
                .append(" number = ")
                .append(number)
                .append(" score = ")
                .append(score)
                .append(" where id = ")
                .append(id)
                .append(";").toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 모든 검색
    public void selectAll() {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from OctopusGame")
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.print("id");
            System.out.print("\t");
            System.out.print("name");
            System.out.print("\t");
            System.out.print("number");
            System.out.print("\t");
            System.out.print("score");
            System.out.print("\t");
            System.out.print("date");
            System.out.print("\n");
            System.out.println("────────────────────────────────────────────────");
            
              while(rs.next()){
                     System.out.print(rs.getInt("id"));
                     System.out.print("\t");
                     System.out.print(rs.getString("name"));
                     System.out.print("\t");
                     System.out.print(rs.getInt("number"));
                     System.out.print("\t");
                     System.out.print(rs.getInt("score"));
                     System.out.print("\t");
                     System.out.print(rs.getDate("date"));
                     System.out.print("\n");
                }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 검색
    public void select(int id) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from OctopusGame where")
                .append(" id = ")
                .append(id)
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            

            System.out.print("id");
            System.out.print("\t");
            System.out.print("name");
            System.out.print("\t");
            System.out.print("number");
            System.out.print("\t");
            System.out.print("score");
            System.out.print("\t");
            System.out.print("date");
            System.out.print("\n");
            System.out.println("────────────────────────────────────────────────");
            
            while(rs.next()){
                System.out.print(rs.getInt("id"));
                System.out.print("\t");
                System.out.print(rs.getString("name"));
                System.out.print("\t");
                System.out.print(rs.getInt("number"));
                System.out.print("\t");
                System.out.print(rs.getInt("score"));
                System.out.print("\t");
                System.out.print(rs.getDate("date"));
                System.out.print("\n");
           }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
    	DataBaseMysqlExam sql = new DataBaseMysqlExam();
    }
}
