package someQuestion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *  * 问题描述：
 * 打印 'A', 'B', 'C', 'D', 'E', 'F' 
 * 三个字母任意组合，顺序不限，如AAB=BAA
 * 方法二：利用排序和hashmap不重复key的性质
 * @author Administrator
 *
 */

public class ThreeLetter {

	
	char[] letter= {'A','B','C','D','E','F'};
	
	/**
	 * 打印字母
	 * 一个字母对一个数组下表，
	 * ABC等于letter[0]+letter[1]+letter[2]即012
	 * cba等于210，然而排序结果都是012
	 */
	public void printLetter(){
		Map hm = new HashMap();
		int[] array = new int[3];
		int min,middle,max;
		for (int i = 0; i < letter.length; i++) {
			for (int j = 0; j < letter.length; j++) {
				
				for (int j2 = 0; j2 < letter.length;  j2++) {
					
					//三个数排序
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
					//字母输出样式
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
	 * 遍历hashmap
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
	 * 二分插入
	 * 传入要插入的数num
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
				middle = ( left + right ) / 2; //　指向已排序好的中间位置
				if( num < array[middle] )// 即将插入的元素应当在在左区间
					right = middle-1;
				else                    //　即将插入的元素应当在右区间
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
	 * 质数算法
	 * 非质数必然是质数与其它数的有限积
	 */
	public static void prime(int n){
		int[] prime=new int[3];
		prime[0]=2;
		prime[0]=3;
		prime[0]=5;
		for (int i = 0; i < n; i++) {
			//TODO 质数算法
		}
	}
	
	/**
	 * 数组转换成字符串
	 * 比如[1,2,3] 结果输出123
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
