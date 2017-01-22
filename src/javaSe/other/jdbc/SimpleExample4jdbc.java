package javaSe.other.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleExample4jdbc {

	/**
	 * 一个简单的面向过程的jdbc链接例子
	 * 
	 */
	public void con2mysql() throws Exception{
		// 1.注册驱动
//		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//		System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver");// 推荐方式

		// 2.建立连接
		String url = "jdbc:mysql://localhost:3306/db";
		String user = "root";
		String password = "";
		Connection conn = DriverManager.getConnection(url, user, password);

		// 3.创建语句
		Statement st = conn.createStatement();

		// 4.执行语句
		ResultSet rs = st.executeQuery("select * from user");

		// 5.处理结果
		while (rs.next()) {
			System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
					+ rs.getObject(3) + "\t" + rs.getObject(4));
		}

		// 6.释放资源
		rs.close();
		st.close();
		conn.close();
	}

	
}
