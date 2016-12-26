package someQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayKuaiSan {

	public static  Map<Float, String> hm = new HashMap<>();
	/**1.ѡ���������ĺ�ֵΪ�н���׼*/
//	public static final int SUM_RESULT = 1;
	/**1.1ѡ���������ĺ�ֵΪ�н���׼  ��ֵ��ͬ�н�*/
	public static final float SUM_RESULT_EQUAL = 1.1f;
	/**1.2ѡ���������ĺ�ֵΪ�н���׼ �� ��ֵ��˫��ͬ�н�*/
	public static final float SUM_RESULT_ODD_AND_EVEN = 1.2f;
	/**1.3ѡ���������ĺ�ֵΪ�н���׼ �� ��ֵ��С�н�*/
	public static final float SUM_RESULT_BIG_SMALL = 1.3f;
	
	/**2.��ͬ���н���ʽ*/
	/**2.1��ͬ���н���ʽ  ֻҪ��������Ϊ����ͬ�ţ����н�*/
	public static final float AAA_ALL = 2.1f;
	/**2.2��ͬ���н���ʽ  ������Ϊ��ͬ�ţ��Һ��������ͬ��һ��*/
	public static final float AAA_ONE = 2.2f;
	
	
	/**3. ��ͬ���н���ʽ*/
	/**3.1 ��ͬ���н���ʽ  ����������ţ��򿪽��ź���ע�ŵ��м����ֱض����*/
	public static final float AAB_ALL = 3.1f;
	/**3.2 ��ͬ�ŵ�ѡ*/
	public static final float AAB_ONE = 3.2f;
	 
	/**4. ����ͬ���н���ʽ*/
	public static final float THREE_DIFFERENT  = 4f;
	
	/**5  ����ͬ���н���ʽ*/
	public static final float TWO_DIFFERENT = 5f;
	
	/**6 ������*/
	public static final float ABC = 6f;
	private static void init(){
		hm.put(SUM_RESULT_EQUAL, "��ֵ��ͬ�н�");
		hm.put(SUM_RESULT_ODD_AND_EVEN, "��ֵ��˫��ͬ�н�");
		hm.put(SUM_RESULT_BIG_SMALL, "��ֵ��С�н�");
		
		hm.put(AAA_ALL, "ֻҪ��������Ϊ����ͬ�ţ����н�");
		hm.put(AAA_ONE, "������Ϊ��ͬ�ţ��Һ��������ͬ��һ��");
		
		hm.put(AAB_ALL, "ֻҪ��������Ϊ����ͬ�ţ����н�");
		hm.put(AAB_ONE, " ��ͬ�ŵ�ѡ");
		
		/**4. ����ͬ���н���ʽ*/
		hm.put(THREE_DIFFERENT, "����ͬ���н���ʽ");
		
		/**5  ����ͬ���н���ʽ*/
		hm.put(TWO_DIFFERENT, " ����ͬ���н���ʽ");
		
		/**6 ������*/
		hm.put(ABC, " ������");
	}
	public PlayKuaiSan() {
		init();
	}
	
	/**
	 * ����������1��6������
	 * @param x
	 * @param y
	 * @param z
	 */
	public static List<Float> inputThreeNum(int x,int y,int z){
		List<Float> floats = new PlayKuaiSan().inputCheck(x,y,z);
//		if (null == floats) {
//			System.out.println("��������");
//		}else {
//			for (Float float1 : floats) {
//				System.out.println("�������ĺ��룬����ѡ������Ͷע��\n"+PlayKuaiSan.hm.get(float1));
//			}
//		}
		return floats;
	}
	
	//������֤�����ǲ��Ǻϸ�
	private List<Float> inputCheck(int... number) {

		Arrays.sort(number); // ����ע��������
		if (number[0]<1||number[2]>6) {
			return null;//���벻�Ϸ�����С��1�ʹ���6����
		}
		int number2 = 100 * number[0] + 10 * number[1] + number[2];
		List<Float> list = new ArrayList<>();
		//1.ѡ���������ĺ�ֵΪ�н���׼
		list.add(SUM_RESULT_BIG_SMALL);
		list.add(SUM_RESULT_EQUAL);
		list.add(SUM_RESULT_ODD_AND_EVEN);
			
		//2. ��ͬ���н���ʽ
		if (number[0] == number[2]) { // ����������һ�������һ����ȣ����ʾ������ͬ��
			list.add(AAA_ALL);
			list.add(AAA_ONE);
		} else 	if // 3. ��ͬ���н���ʽ
		((number[0] == number[1]) || (number[1] == number[2])) {
			list.add(AAB_ALL);
			list.add(AAB_ONE);
			/** 5 ����ͬ���н���ʽ */
			list.add(TWO_DIFFERENT);
		}else if //6.������
		(((number2 == 123 || number2 == 234) || number2 == 345)
				|| number2 == 456) {
			list.add(ABC);
		} else		{
			/** 4. ����ͬ���н���ʽ */
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
	 * ����Ͷע�鿴�Ƿ��н�
	 * @param point
	 * 			ѹ��ע
	 * @param input
	 * 			�û����������
	 * @param owner
	 * 			ϵͳ�����ı�������
	 */
	public static void checkPoint(Float point,Integer[] input,Integer[] owner){
		String s = point.toString();
		if (null == point || null == input) {
			return;
		}
		new KuaiSan(owner).check(Integer.parseInt(s.substring(0, 1)), Integer.parseInt(s.substring(2)), input);
	}
		
}
