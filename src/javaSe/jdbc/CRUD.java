package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * 2008-12-6
 * 
 * @author <a href="mailto:liyongibm@hotmail.com">李勇</a>
 * 
 */
public class CRUD {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		// create();
		read();
		// update();
		// delete();
	}

	static void delete() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSing.getInstance().getConnection();
			// 3.创建语句
			st = conn.createStatement();

			String sql = "delete from user where id>4";

			// 4.执行语句
			int i = st.executeUpdate(sql);

			System.out.println("i=" + i);
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
	}

	static void update() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSing.getInstance().getConnection();
			// 3.创建语句
			st = conn.createStatement();

			String sql = "update user set money=money+10 ";

			// 4.执行语句
			int i = st.executeUpdate(sql);

			System.out.println("i=" + i);
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
	}

	static void create() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSing.getInstance().getConnection();
			// 3.创建语句
			st = conn.createStatement();

			String sql = "insert into user(name,birthday, money) values ('name1', '1987-01-01', 400) ";

			// 4.执行语句
			int i = st.executeUpdate(sql);

			System.out.println("i=" + i);
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
	}

	static void read() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSing.getInstance().getConnection();
			// 3.创建语句
			st = conn.createStatement();

			// 4.执行语句
			rs = st.executeQuery("select id, name, money, birthday  from user");

			// 5.处理结果
			while (rs.next()) {
				System.out.println(rs.getObject("id") + "\t"
						+ rs.getObject("name") + "\t"
						+ rs.getObject("birthday") + "\t"
						+ rs.getObject("money"));
			}
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
	}

}
