package javase.basic.dataStructure.base;

public class Search {

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
			// 大于往后找
			if (dest > tmp) {
				// 左边界收缩
				left = index;
			} else if (dest == tmp) {
				return index;
			}else {// 小于往前找
				// 右边界收缩
				right = index;
			}
		} while (right - left > 1);
		return dest > arr[index] ? index + 1 : index;
	}

	public static void main(String[] args) {
		System.out.println(1 / 2);
	}
}
