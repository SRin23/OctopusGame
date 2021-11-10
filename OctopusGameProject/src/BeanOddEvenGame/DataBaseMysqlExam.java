package BeanOddEvenGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseMysqlExam {
	public static void main(String[] args) {
		try {
			// Class.forName("org.git.mm.mysql.Driver");// 1. jdbc ����̺� ����
			Class.forName("com.mysql.cj.jdbc.Driver"); // �� ������ ��
			System.out.println("����̺� ������ �ߵ�");
		} catch(ClassNotFoundException ee) {
			System.out.println("DB ���� ����̺갡 ����");
		}
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/OctopusGame";
		String id = "root";
		String pw = "#";
		
		try {
			conn = DriverManager.getConnection(url, id, pw);// 2. �����ͺ��̽� ����
		} catch(SQLException ee) {
			System.out.println("DB �������");
		}
		
		Statement stmt = null; // 3. ��ɾ� ó�� ��ü ����
		try {
			stmt = conn.createStatement();
		} catch(SQLException ee) {
			System.out.println("�۾� ó�� ��ü ���� ����");
		}
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("select * from rank");// 4. �������
			while(rs.next()) {
				System.out.println(rs.getString("ranking")+"-" + 
						rs.getString("name")+"-"+
						rs.getString("score"));
			}
		} catch(SQLException ee) {
			System.out.println("��ɾ� ���� ����"+ee.toString());
		}
		
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException ee) {
			System.out.println("���� ���� ����"+ee.toString());
		}
	}
}
