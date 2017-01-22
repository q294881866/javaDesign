package javaSe.other.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleExample4jdbc {

	/**
	 * һ���򵥵�������̵�jdbc��������
	 * 
	 */
	public void con2mysql() throws Exception{
		// 1.ע������
//		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//		System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver");// �Ƽ���ʽ

		// 2.��������
		String url = "jdbc:mysql://localhost:3306/db";
		String user = "root";
		String password = "";
		Connection conn = DriverManager.getConnection(url, user, password);

		// 3.�������
		Statement st = conn.createStatement();

		// 4.ִ�����
		ResultSet rs = st.executeQuery("select * from user");

		// 5.������
		while (rs.next()) {
			System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
					+ rs.getObject(3) + "\t" + rs.getObject(4));
		}

		// 6.�ͷ���Դ
		rs.close();
		st.close();
		conn.close();
	}

	
}
