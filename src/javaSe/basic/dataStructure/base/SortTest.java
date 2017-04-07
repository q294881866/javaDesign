package javaSe.basic.dataStructure.base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.print.attribute.IntegerSyntax;
import javax.print.attribute.standard.NumberUpSupported;

import org.junit.Test;

/**
 * ע��Խ����
 * 
 * @author ppf
 * @since 2017��3��31��
 */
public class SortTest {

	@FunctionalInterface
	interface Sort {
		void sort();// invokedynamic ָ��
	}

	private static int[] arr/* = { 2, 1, 3 } */;
	private static int size = 30;// ջ�Ϸ���

	/**
	 * @param size
	 *            >0
	 */
	public static int[] generateArray(int size) {
		int[] arr = new int[size];
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			arr[i] = r.nextInt(100);
		}
		return arr;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void runTest(Sort s) {
		arr = generateArray(size);
		printArray(arr);
		// --execute--
		s.sort();
		printArray(arr);
	}

	// ===========================sorting_algorithm=========================================

	@Test
	public void bubble() throws Exception {
		Sort bubble = () -> {
			int max;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length - i; j++) {
					max = arr[j];// ��ʱ����
					if (j + 1 < arr.length - i && arr[j + 1] < max) {// ����
						arr[j] = arr[j + 1];
						arr[j + 1] = max;
					}
				}
			}
		};
		runTest(bubble);
	}

	@Test
	public void select() throws Exception {
		Sort select = () -> {
			// �������������С
			selectSort(0, size - 1);
		};
		runTest(select);
	}

	int[] tmp = new int[size];
	int limit, index2, index1, k = 0, gap, num;

	@Test
	public void merge() throws Exception {
		Sort merge = () -> {
			// ���� �����鳤�ȷ��飬��2 2^2 2^4 ֱ�� >length/2,���κϲ�����
			for (gap = 1; arr.length / gap > 0; gap = gap << 1) {
				mergeSort();
			}
		};
		runTest(merge);
	}

	private void mergeSort() {
		// ==============ÿ�λ��ֶ��ٸ�����==============
		num = arr.length / gap;
		if (arr.length % gap != 0) {
			num = num + 1;
		}
		// ==============�����ϲ�==============
		for (int i = 0; i < num; i += 2) {
			index1 = gap * i;
			index2 = gap * i + gap;
			limit = index2;
			copyValues(limit, Math.min(size, limit + gap));
		}
		// ==============���һ������鲢=================
		if (1 == num) {
			index2 += gap;
			limit = index2;
			copyValues(limit, arr.length);
		}
		// ==============��������=================
		int[] swap = arr;
		arr = tmp;
		tmp = swap;
		k = 0;// ��ԭ��ʱ����
	}

	private void copyValues(int begin, int end) {
		while (index1 < begin && index2 < end) {
			if (arr[index1] > arr[index2]) {
				tmp[k++] = arr[index2++];
			} else if (arr[index1] < arr[index2]) {
				tmp[k++] = arr[index1++];
			} else {// ���ȫ������
				tmp[k++] = arr[index1++];
				tmp[k++] = arr[index2++];
			}
		}
		// ʣ��Ԫ��
		if (index1 < begin) {
			System.arraycopy(arr, index1, tmp, k, limit - index1);
			k += limit - index1;
		} else if (index2 < end) {// �ұ�������ʣ��
			System.arraycopy(arr, index2, tmp, k, limit - index2 + gap);
			k += limit - index2 + gap;
		}
	}

	private void selectSort(int begin, int end) {
		int maxIndex = end, littleIndex = begin;
		for (int i = begin; i < end; i++) {
			int tmp = arr[i];
			if (tmp > arr[maxIndex]) {
				maxIndex = i;// ��ע���
			} else if (tmp < arr[littleIndex]) {
				littleIndex = i;// ��ע��С
			}
		}
		// ���������С
		swap(arr, maxIndex, end);
		swap(arr, littleIndex, begin);
		if (end - begin > 2)
			selectSort(begin + 1, end - 1);
	}

	@Test
	public void insert() throws Exception {
		Sort insert = () -> {
			// ����Ԫ�ز�����
			for (int i = 1; i < arr.length; i++) {
				int tmp = arr[i];
				// ���Ҳ���λ��
				int index = SearchTest.binarySearch(arr, tmp, 0, i);
				// ����,�����ʼ
				for (int j = i; j > index - 1 && j > 0; j--) {
					arr[j] = arr[j - 1];
				}
				// �������λ��
				arr[index] = tmp;
			}
		};
		runTest(insert);
	}

	@Test
	public void quick() throws Exception {
		Sort quick = () -> {
			quickSort(0, size - 1);
		};
		runTest(quick);
	}

	// =======================================static_tools==============================================
	/**
	 * @param base
	 *            ������������Ƚϻ�׼
	 * @param range
	 *            ��Χ����߻���ѡ��Ч��
	 * @return ����
	 */
	public static int selectBase(int[] arr, int begin, int end, int base, int range) {
		int gap/* �������С */ = base, index/* Ҫ��Ļ��� */ = 0;
		for (int j = begin; j < end; j++) {
			int k = arr[j];
			int newGap = Math.abs(base - k);
			if (newGap < range) {
				return j;
			}
			if (gap > newGap) {// ��Ҫ����
				index = j;
				gap = newGap;
			}
		}
		return index;
	}

	private static void quickSort(int begin, int end) {
		int start = begin;
		int stop = end;
		int key = arr[begin];

		while (stop > start) {
			// �Ӻ���ǰ�Ƚ�
			while (stop > start && arr[stop] >= key)
				stop--;
			if (arr[stop] <= key) { // ���ڣ� �ܷ��Ż�
				swap(arr, start, stop);
			}
			// ��ǰ����Ƚ�
			while (stop > start && arr[start] <= key)
				start++;
			if (arr[start] >= key) {
				swap(arr, start, stop);
			}
		}
		if (start > begin)
			quickSort(begin, start - 1);
		if (stop < end)
			quickSort(stop + 1, end);
	}

	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 6 };
		int i = 0;
		System.out.println(arr[i++] + " | " + arr[++i]);
	}

}
