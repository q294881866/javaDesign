package javaSe.other.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 * ���ܽ�����ģʽ
 * ���ݿ����ӳ�ʵϰ
 * @author Administrator
 *
 */
public class DataSource {

	private static int initCount = 1<<6;
	private static int maxCount = 1<<8;
	int currentCount = 0;
	private MyJdbc jdbc;

	//���ݿ����ӳ�<>��ʾ����Connection��ʾֻ��Connection���������ﱨ����  ���Ͳ���ת��
//	LinkedList<Connection> connectionsPool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());
	LinkedList<Connection> connectionsPool =new LinkedList<Connection>();
		
	private DataSource(){
		
	}
	public DataSource(MyJdbc jdbc) {
		this.jdbc = jdbc;
		
		try {
			for (int i = 0; i < initCount; i++) {
				this.connectionsPool.addLast(jdbc.getConnection());
				this.currentCount++;
			}
		} catch (SQLException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public Connection getConnection() throws SQLException {
		synchronized (connectionsPool) {
			if (this.connectionsPool.size() > 0)
				return this.connectionsPool.removeFirst();

			//�����ǰû��������
			if (this.currentCount < maxCount) {
				this.currentCount++;
				return jdbc.getConnection();
			}

			throw new SQLException("��û������");
		}
	}

	public void free(Connection conn) {
		this.connectionsPool.addLast(conn);
	}

	
}
