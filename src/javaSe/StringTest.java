package javaSe;

public class StringTest {
	public static void main(String[] args) {
		String s = new String();//��Ч��String s = "";  ����ЧString s = null;
		String s0 = "abc";//����һ���ַ��������ڳ������С�		
		String s1 = new String("abc");//������������һ��newһ���ַ��������ڶ��ڴ��С�
		
		System.out.println(s0==s1);//false
		
		System.out.println(s0.equals(s1));
		//string���е�equals��дObject�е�equals������string���Լ����ж��ַ��������Ƿ���ͬ�����ݡ�
		//��ʵ���ǱȽ��ַ������ݡ�
		
		
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

