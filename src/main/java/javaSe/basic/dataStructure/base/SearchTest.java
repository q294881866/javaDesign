package javaSe.basic.dataStructure.base;

public class SearchTest {

	/**
	 * 注意数组检查（空）,升序<br>
	 * 
	 * @param arr
	 * @param dest
	 * @return 应该插入的位置（数组下标）
	 */
	public static int binarySearch(int[] arr, int dest, int begin, int end) {
		int index, tmp, left = begin, right = end;
		if (arr[begin] > dest)
			return 0;
		do {// 最后值return判断大小
			index = (left + right) / 2;
			tmp = arr[index];
			if (dest > tmp) {// 大于往后找
				left = index;// 左边界收缩
			} else if (dest == tmp) {
				return index;
			} else {// 小于往前找
				right = index;// 右边界收缩
			}
		} while (right - left > 1);
		return dest > arr[index] ? index + 1 : index;
	}

	public static void main(String[] args) {
		System.out.println(1 / 2);
	}
}
