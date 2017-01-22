package javaSe.other.jdbc;
/**
 * 介绍Java父类子类各种方法加载顺序
 * @author Administrator
 *
 */
public class LoadOrder {

	  static {  
	        System.out.println("父类--静态代码块");  
	    }  
	  
	    public LoadOrder() {  
	        System.out.println("父类--构造函数");  
	    }  
	  
	    {  
	        System.out.println("父类--非静态代码块");  
	    }  
	  
	    public static void main(String[] args) {  
	        new child();  
	    }  
	}  
	  
	class child extends LoadOrder {  
	    static {  
	        System.out.println("子类--静态代码块");  
	    }  
	    {  
	        System.out.println("子类--非静态代码块");  
	    }  
	  
	    public child() {  
	        System.out.println("子类--构造函数");  
	    }  
	
}
