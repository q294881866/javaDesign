package javaSe.other.jdbc;

/**
 * Java�쳣������ϰ
 * example ���ݿ��쳣
 * @author Administrator
 *
 */
public class DaoException extends RuntimeException {

	/**
	 * ���췽���ֳƹ���������дĬ���������ʱע��һ���޲ι��ɷ���
	 * Ĭ�ϵĹ��췽������ÿ�ζ�д��
	 * ������в����Ĺ��췽������Ĭ�������޲ι��ɷ���
	 */
	public DaoException() {
		// TODO ���ע�ͱ�������û����� ��Task�����������ҵ�
		
	}

	/**
	 * �ο�super�����ע��
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
		
	}

	/**
	 * �ο�super�����ע��
	 * @param message
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * �ο�super�����ע��
	 * @param message
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

}

