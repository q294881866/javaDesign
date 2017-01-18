package javaSe;

public class StringTest {
	public static void main(String[] args) {
		String s = new String();//等效于String s = "";  不等效String s = null;
		String s0 = "abc";//创建一个字符串对象在常量池中。		
		String s1 = new String("abc");//创建两个对象一个new一个字符串对象在堆内存中。
		
		System.out.println(s0==s1);//false
		
		System.out.println(s0.equals(s1));
		//string类中的equals复写Object中的equals建立了string类自己的判断字符串对象是否相同的依据。
		//其实就是比较字符串内容。
		
		
		//------------------------------------------------StringBuffer
		
		StringBuffer sb = new StringBuffer("abce");
				
		//		sb.append("xixi");
		sb.insert(2, "qq");
		/**
		* * 
		* class StringBuffer jdk1.0
		*	{
		*		object lock;
		*		public   StirngBuffer append(int x)
		*		{
		*			synchronized(lock)
		*			{
		*			}
		*		}
		*		
		*		
		*		public synchronized StringBuffer delete(int start,int end)
		*		{
		*			synchronized(lock)
		*			{
		*			}
		*		}
		*	}
		 */
		
		
	}
}

