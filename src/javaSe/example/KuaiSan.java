package javaSe.example;

import java.util.Arrays;

public class KuaiSan {

	 /**����������ĺ���*/
	Integer[] jackpot = new Integer[3];

	KuaiSan(Integer... owner) {
		this.jackpot = owner;
		System.out.print("���ں���Ϊ��");
		for (int i : jackpot)
			System.out.print(i);
		System.out.println();
	}

	/**
	 * �����������
	 */
	private void randomSan() {
		for (int i = 0; i < 3; i++)
			jackpot[i] = (int) ((Math.random() * 300 % 6) + 1);
		Arrays.sort(jackpot);
	}

	/**
	 * �˶Ժ��룬�Ƿ��н�
	 * 
	 * @param a
	 *            ������ʾ��������н���ʽ
	 * @param b
	 *            ������ʾÿ��������н���ʽ�µ�С���
	 * @param number
	 *            Ҫ��ʵ����ע����
	 */
	void check(int a, int b, Integer[] number) {

		Arrays.sort(number); 
		System.out.print("��ע����Ϊ��");
		for (int i : number)
			System.out.print(i);
		System.out.println();

		int numSum = jackpot[0] + jackpot[1] + jackpot[2]; // �н������
		int numberSum = number[0] + number[1] + number[2]; // �û������
		switch (a) {
		// ѡ���������ĺ�ֵΪ�н���׼
		case 1:
			switch (b) {
			case 1:// ��ֵ��ͬ�н�
				if (numberSum == numSum)
					System.out.println("��ϲ�㣬(������)�н�����");
				else
					System.out.println("���ź���(������)��û�У�");
				break;
			case 2:// ��ֵ��˫��ͬ�н�
				if ((numberSum % 2) == (numSum % 2))
					System.out.println("��ϲ�㣬(�����͵�˫)�н�����");
				else
					System.out.println("���ź���(�����͵�˫)��û�У�");
				break;
			case 3:// ��ֵ��С�н�
				if ((numberSum < 11 && numSum < 11) || (numberSum > 10 && numSum > 10))
					System.out.println("��ϲ�㣬(�����ʹ�С)�н�����");
				else
					System.out.println("���ź���(�����ʹ�С)��û�У�");
				break;
			}
			break;
		case 2:// ��ͬ���н���ʽ
			switch (b) {
			// ֻҪ�н�����Ϊ����ͬ�ţ����н�
			case 1:
				if (jackpot[0] == jackpot[2]) 
					System.out.println("��ϲ�㣬(��ͬ��)�н�����");
				else
					System.out.println("���ź���(��ͬ��)��û�У�");
				break;
			// �н���Ϊ��ͬ�ţ��Һ��������ͬ��һ��
			case 2:
				if (((jackpot[0] == jackpot[2]) && (number[0] == number[2])) && (jackpot[1] == number[1]))
					System.out.println("��ϲ�㣬(��ͬ��)�н�����");
				else
					System.out.println("���ź���(��ͬ��)��û�У�");
				break;
			}
			break;
		case 3:// ��ͬ���н���ʽ
			switch (b) {
			case 1:// ����������ţ����н��ź���ע�ŵ��м����ֱض����
				if ((number[1] == jackpot[1]) && ((jackpot[1] == jackpot[0]) || (jackpot[1] == jackpot[2])))
					System.out.println("��ϲ�㣬(��ͬ�Ÿ���)�н�����");
				else
					System.out.println("���ź���(��ͬ�Ÿ���)��û�У�");
				break;
			case 2:// ��ͬ�ŵ�˫
				if ((number[1] == jackpot[1]) && ((number[0] == jackpot[0]) || (number[2] == jackpot[2])))
					System.out.println("��ϲ�㣬(��ͬ�ŵ�˫)�н�����");
				else
					System.out.println("���ź���(��ͬ�ŵ�˫)��û�У�");
				break;
			}
			break;
		case 4:// ����ͬ���н���ʽ
			if ((jackpot[0] != jackpot[1]) && (jackpot[1] != jackpot[2])) // �Ƚ����붼��ͬ
				if (((jackpot[0] == number[0]) && (jackpot[1] == number[1])) && (jackpot[2] == number[2])) // ��ע�����뿪������һ��
					System.out.println("��ϲ�㣬(����ͬ��)�н�����");
				else
					System.out.println("���ź���(������)��û�У�");
			break;
		case 5:// ����ͬ���н���ʽ
			if (((number[0] == jackpot[0]) && ((number[1] == jackpot[1]) || (number[1] == jackpot[2])))
					|| ((number[0] == jackpot[1]) && (number[1] == jackpot[2])))
				System.out.println("��ϲ�㣬(����ͬ��)�н�����");
			else
				System.out.println("���ź���(����ͬ��)��û�У�");
			break;
		case 6:// ������
			int num1 = 100 * jackpot[0] + 10 * jackpot[1] + jackpot[2];
			if (((num1 == 123 || num1 == 234) || num1 == 345) || num1 == 456)
				System.out.println("��ϲ�㣬(������)�н�����");
			else
				System.out.println("���ź���(������)��û�У�");
			break;
		default:
			System.out.println("Input Error");
			break;
		}
	}

	public static void main(String[] args) {
		Integer[] number = { 1, 3, 2 };
		new KuaiSan().check(6, 1, number);
	}

}
