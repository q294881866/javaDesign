package someQuestion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *  * ����������
 * ��ӡ 'A', 'B', 'C', 'D', 'E', 'F' 
 * ������ĸ������ϣ�˳���ޣ���AAB=BAA
 * �����������������hashmap���ظ�key������
 * @author Administrator
 *
 */

public class ThreeLetter {

	
	char[] letter= {'A','B','C','D','E','F'};
	
	/**
	 * ��ӡ��ĸ
	 * һ����ĸ��һ�������±�
	 * ABC����letter[0]+letter[1]+letter[2]��012
	 * cba����210��Ȼ������������012
	 */
	public void printLetter(){
		Map hm = new HashMap();
		int[] array = new int[3];
		int min,middle,max;
		for (int i = 0; i < letter.length; i++) {
			for (int j = 0; j < letter.length; j++) {
				
				for (int j2 = 0; j2 < letter.length;  j2++) {
					
					//����������
					if(i>j){
						min=j;max=i;
					}else {
						min=i;max=j;
					}
					
					if (min>j2) {
						middle=j;
						min = j2;
					}else if (min<=j2&&j2<max) {
						middle=j2;
					}else {
						middle=max;
						max=j2;
					}
//					System.out.println(min );
					//��ĸ�����ʽ
					String s = ""+letter[i]+letter[j]+letter[j2];
					String key = ""+min+middle+max;
					hm.put(Integer.parseInt(key),s);
					
				}
			}
		}
		
		
		//print hm
		iteratorHashMap(hm);

	

	}

	/**
	 * ����hashmap
	 * @param hm
	 */
	private void iteratorHashMap(Map hm) {
		Iterator iter = hm.entrySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			Map.Entry entry = (Entry) iter.next();
			Object key = entry.getKey();
//			System.out.println(key+"key");
			Object val = entry.getValue();
			i++;
			System.out.println(val+""+i);
			
		}
	}
	
	
	public static void main(String[] args) {
		ThreeLetter threeLetter = new ThreeLetter();
		threeLetter.printLetter();
	}
	
	/*
	 * ���ֲ���
	 * ����Ҫ�������num
	 */
	public static int[] binaryInsertSort(int num,int[] array){
		 
		int length=array.length+1;
		int[] arr=new int[length];
		if (array.length==0) 
			return arr=new int[]{num};
		
		if (array.length==1){
			int i=array[0];
			if(i>num){
				return arr=new int[]{num,i};
			}else {
				return arr=new int[]{i,num};
			}
		}
			
		int left=0;
		int right=array.length-1;
		int middle=array.length/2+1;
			
			while (right>=left) 
			{
				middle = ( left + right ) / 2; //��ָ��������õ��м�λ��
				if( num < array[middle] )// ���������Ԫ��Ӧ������������
					right = middle-1;
				else                    //�����������Ԫ��Ӧ����������
					left = middle+1;    
			}
			
		for (int i = 0; i < arr.length; i++) {
			if (i<left) {
				arr[i]=array[i];
			}else if (i==left) {
				arr[i]=num;
			}else {
				arr[i]=array[i-1];
			}
			
		}
			
			
		
		
		return arr;
	  
	 }
	
	/**
	 * �����㷨
	 * ��������Ȼ�������������������޻�
	 */
	public static void prime(int n){
		int[] prime=new int[3];
		prime[0]=2;
		prime[0]=3;
		prime[0]=5;
		for (int i = 0; i < n; i++) {
			//TODO �����㷨
		}
	}
	
	/**
	 * ����ת�����ַ���
	 * ����[1,2,3] ������123
	 * @param array
	 * @return
	 */
	private static String toString(int[] array) {
		String key ="";
		for (int i = 0; i < array.length; i++) {
			key = key+""+array[i];
		}
		
		return key;
	}
}
