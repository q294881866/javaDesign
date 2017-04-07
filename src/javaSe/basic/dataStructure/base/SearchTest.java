package javaSe.basic.dataStructure.base;

public class SearchTest {

	/**
	 * ע�������飨�գ�,����<br>
	 * 
	 * @param arr
	 * @param dest
	 * @return Ӧ�ò����λ�ã������±꣩
	 */
	public static int binarySearch(int[] arr, int dest, int begin, int end) {
		int index, tmp, left = begin, right = end;// arr.length !-1
		if (arr[begin] > dest)
			return 0;
		do {// ���ֵreturn�жϴ�С
			index = (left + right) / 2;
			tmp = arr[index];
			if (dest > tmp) {// ����������
				left = index;// ��߽�����
			} else if (dest == tmp) {
				return index;
			} else {// С����ǰ��
				right = index;// �ұ߽�����
			}
		} while (right - left > 1);
		return dest > arr[index] ? index + 1 : index;
	}

	public static void main(String[] args) {
		System.out.println(1 / 2);
	}
}
