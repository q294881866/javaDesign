package javaSe.other.jdbc.factory;



public class MySqlSessionFactory {
	
	/**
	 * ���ģʽ��һ��facade
	 * ������ģʽ
	 * @param url
	 * @param user
	 * @param password
	 * @param driver
	 * @return
	 */
	public static SessionFactory getSessionFactory(String url,String user,String password,String driver){
		MyJdbc jdbc = new MyJdbc(url, user, password, driver);
		DataSource dataSource = new DataSource(jdbc);
		return new SessionFactory(dataSource);
	}
}
