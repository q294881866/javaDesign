package JavaStructure;
/**
 * set���ϵ�ʵ��Ҫ��֤Ԫ�ص�Ψһ��<br>
 * hashCode ��equals��������Ҫ��ʵ��
 */
public interface Set extends Collection {
	/**
	 * ����������ȵĹ���
	 * @param object
	 * @return
	 */
	boolean equals(Object object);
	
	
	/**
	 * �������ݵ�hash code����
	 */
	int hashCode(); 
	
	
	
}
