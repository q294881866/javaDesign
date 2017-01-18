package someQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayKuaiSan {

	public static  Map<Float, String> hm = new HashMap<>();
	/**1.选择三个数的和值为中奖标准*/
//	public static final int SUM_RESULT = 1;
	/**1.1选择三个数的和值为中奖标准  和值相同中奖*/
	public static final float SUM_RESULT_EQUAL = 1.1f;
	/**1.2选择三个数的和值为中奖标准 中 和值单双相同中奖*/
	public static final float SUM_RESULT_ODD_AND_EVEN = 1.2f;
	/**1.3选择三个数的和值为中奖标准 中 和值大小中奖*/
	public static final float SUM_RESULT_BIG_SMALL = 1.3f;
	
	/**2.三同号中奖方式*/
	/**2.1三同号中奖方式  只要开奖号码为三个同号，就中奖*/
	public static final float AAA_ALL = 2.1f;
	/**2.2三同号中奖方式  开奖号为三同号，且和你买的三同号一样*/
	public static final float AAA_ONE = 2.2f;
	
	
	/**3. 二同号中奖方式*/
	/**3.1 二同号中奖方式  如果是两连号，则开奖号和下注号的中间数字必定相等*/
	public static final float AAB_ALL = 3.1f;
	/**3.2 二同号单选*/
	public static final float AAB_ONE = 3.2f;
	 
	/**4. 三不同号中奖方式*/
	public static final float THREE_DIFFERENT  = 4f;
	
	/**5  二不同号中奖方式*/
	public static final float TWO_DIFFERENT = 5f;
	
	/**6 三连号*/
	public static final float ABC = 6f;
	private static void init(){
		hm.put(SUM_RESULT_EQUAL, "和值相同中奖");
		hm.put(SUM_RESULT_ODD_AND_EVEN, "和值单双相同中奖");
		hm.put(SUM_RESULT_BIG_SMALL, "和值大小中奖");
		
		hm.put(AAA_ALL, "只要开奖号码为三个同号，就中奖");
		hm.put(AAA_ONE, "开奖号为三同号，且和你买的三同号一样");
		
		hm.put(AAB_ALL, "只要开奖号码为两个同号，就中奖");
		hm.put(AAB_ONE, " 二同号单选");
		
		/**4. 三不同号中奖方式*/
		hm.put(THREE_DIFFERENT, "三不同号中奖方式");
		
		/**5  二不同号中奖方式*/
		hm.put(TWO_DIFFERENT, " 二不同号中奖方式");
		
		/**6 三连号*/
		hm.put(ABC, " 三连号");
	}
	public PlayKuaiSan() {
		init();
	}
	
	/**
	 * 请输入三个1到6的整数
	 * @param x
	 * @param y
	 * @param z
	 */
	public static List<Float> inputThreeNum(int x,int y,int z){
		List<Float> floats = new PlayKuaiSan().inputCheck(x,y,z);
//		if (null == floats) {
//			System.out.println("输入有误");
//		}else {
//			for (Float float1 : floats) {
//				System.out.println("根据您的号码，可以选择如下投注：\n"+PlayKuaiSan.hm.get(float1));
//			}
//		}
		return floats;
	}
	
	//用于验证输入是不是合格
	private List<Float> inputCheck(int... number) {

		Arrays.sort(number); // 对下注号码排序
		if (number[0]<1||number[2]>6) {
			return null;//输入不合法，有小于1和大于6的数
		}
		int number2 = 100 * number[0] + 10 * number[1] + number[2];
		List<Float> list = new ArrayList<>();
		//1.选择三个数的和值为中奖标准
		list.add(SUM_RESULT_BIG_SMALL);
		list.add(SUM_RESULT_EQUAL);
		list.add(SUM_RESULT_ODD_AND_EVEN);
			
		//2. 三同号中奖方式
		if (number[0] == number[2]) { // 排序后如果第一个和最后一个相等，则表示是三个同号
			list.add(AAA_ALL);
			list.add(AAA_ONE);
		} else 	if // 3. 二同号中奖方式
		((number[0] == number[1]) || (number[1] == number[2])) {
			list.add(AAB_ALL);
			list.add(AAB_ONE);
			/** 5 二不同号中奖方式 */
			list.add(TWO_DIFFERENT);
		}else if //6.三连号
		(((number2 == 123 || number2 == 234) || number2 == 345)
				|| number2 == 456) {
			list.add(ABC);
		} else		{
			/** 4. 三不同号中奖方式 */
			list.add(THREE_DIFFERENT);
		}
		
		return list;

	}
	
	
	public static void main(String[] args) {
		List<Float> floats = PlayKuaiSan.inputThreeNum(1, 5, 1);
		Integer[] i = {1, 5, 1};
		for (Float float1 : floats) {
			PlayKuaiSan.checkPoint(float1, i,i);
		}
		
	}
	
	/**
	 * 根据投注查看是否中奖
	 * @param point
	 * 			压的注
	 * @param input
	 * 			用户输入的数字
	 * @param owner
	 * 			系统产生的本期数字
	 */
	public static void checkPoint(Float point,Integer[] input,Integer[] owner){
		String s = point.toString();
		if (null == point || null == input) {
			return;
		}
		new KuaiSan(owner).check(Integer.parseInt(s.substring(0, 1)), Integer.parseInt(s.substring(2)), input);
	}
		
}
