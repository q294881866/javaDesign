package javaSe.other.jdbc;
/**
 * ����Java����������ַ�������˳��
 * @author Administrator
 *
 */
public class LoadOrder {

	  static {  
	        System.out.println("����--��̬�����");  
	    }  
	  
	    public LoadOrder() {  
	        System.out.println("����--���캯��");  
	    }  
	  
	    {  
	        System.out.println("����--�Ǿ�̬�����");  
	    }  
	  
	    public static void main(String[] args) {  
	        new child();  
	    }  
	}  
	  
	class child extends LoadOrder {  
	    static {  
	        System.out.println("����--��̬�����");  
	    }  
	    {  
	        System.out.println("����--�Ǿ�̬�����");  
	    }  
	  
	    public child() {  
	        System.out.println("����--���캯��");  
	    }  
	
}
