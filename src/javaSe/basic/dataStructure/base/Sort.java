package javaSe.basic.dataStructure.base;

public class Sort {
	public static <T extends Comparable<? super T>> T[] quickSort(T[] targetArr,
			int start, int end) {
		int i = start, j = end;
		T key = targetArr[start];

		while (i < j) {
			/* ��j--�������Ŀ�����飬ֱ����keyС��ֵΪֹ */
			while (j > i && targetArr[j].compareTo(key) >= 0) {
				j--;
			}
			if (i < j) {
				/* targetArr[i]�Ѿ�������key�У��ɽ������������ */
				targetArr[i] = targetArr[j];
				i++;
			}
			/* ��i++�������Ŀ�����飬ֱ����key���ֵΪֹ */
			while (i < j && targetArr[i].compareTo(key) <= 0)
			/*
			 * �˴�һ��ҪС�ڵ����㣬��������֮����һ�ڸ�1��0������ֵĻ�����key��ֵ��ǡ����1�Ļ���
			 * ��ô���С�ڵ��ڵ����þͻ�ʹ�����if�����ִ��һ�ڴΡ�
			 */
			{
				i++;
			}
			if (i < j) {
				/* targetArr[j]�ѱ�����targetArr[i]�У��ɽ�ǰ���ֵ���� */
				targetArr[j] = targetArr[i];
				j--;
			}
		}
		/* ��ʱi==j */
		targetArr[i] = key;

		/* �ݹ���ã���keyǰ���������� */
		quickSort(targetArr, start, i - 1);

		/* �ݹ���ã���key������������ */
		quickSort(targetArr, j + 1, end);

		return targetArr;
	}
}
