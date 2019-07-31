package javase.other.jdbc.factory;
/**
 * 桥接模式
 * 对应不同的数据源提供不同的数据库连接
 */
import java.sql.Connection;
import java.sql.SQLException;
import javase.other.jdbc.DaoException;


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
	 * 把连接放回去
	 */
	public void close(){
		dataSource.free(connection);
	}
	
	/**
	 * 开启事务
	 * 并设置事务隔离级别
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
			new DaoException("事务开启时异常", e);
		}
	}
	
	
	/**
	 * 提交事务
	 */
	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			new DaoException("事务提交异常", e);
		}
	}
}
