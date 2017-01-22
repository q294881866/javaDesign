package javaSe.other.jdbc.factory;
/**
 * �Ž�ģʽ
 * ��Ӧ��ͬ������Դ�ṩ��ͬ�����ݿ�����
 */
import java.sql.Connection;
import java.sql.SQLException;
import javaSe.other.jdbc.DaoException;


public class SessionFactory {
	private DataSource dataSource;
	private Connection connection;
	
	private SessionFactory() {
	}
	
	public  SessionFactory(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public Connection getSession(){
		
		try {
			this.connection =dataSource.getConnection();
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �����ӷŻ�ȥ
	 */
	public void close(){
		dataSource.free(connection);
	}
	
	/**
	 * ��������
	 * ������������뼶��
	 * level one of the following Connection constants: 
	 * Connection.TRANSACTION_READ_UNCOMMITTED, 
	 * Connection.TRANSACTION_READ_COMMITTED, 
	 * Connection.TRANSACTION_REPEATABLE_READ, 
	 * or Connection.TRANSACTION_SERIALIZABLE. 
	 * (Note that Connection.TRANSACTION_NONE 
	 * cannot be used because it specifies that transactions are not supported.)
	 * @param level
	 */
	public  void startTransaction(int level) {
		try {
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(level);
		} catch (SQLException e) {
			new DaoException("������ʱ�쳣", e);
		}
	}
	
	
	/**
	 * �ύ����
	 */
	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			new DaoException("�����ύ�쳣", e);
		}
	}
}
