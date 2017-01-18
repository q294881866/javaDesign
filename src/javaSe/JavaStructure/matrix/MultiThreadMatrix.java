package JavaStructure.matrix;

/**    
* @Title: MultiThreadMatrix.java 
* @Package matrix 
* @Description: ���̼߳������˷� 
* @author Administrator
* @Date 
*/ 


import java.util.Date;


public class MultiThreadMatrix {

	static int[][] matrix1;
	static int[][] matrix2;
	static int[][] matrix3;
	static int m, n, k;
	static int index;
	static int threadCount;
	static long startTime;

	public static void main(String[] args) throws InterruptedException {
		// ����a�߶�m=100���k=80,����b�߶�k=80���n=50 ==> ����c�߶�m=100���n=50
		m = 1024;
		n = 1024;
		k = 1024;
		matrix1 = new int[m][k];
		matrix2 = new int[k][n];
		matrix3 = new int[m][n];

		// �����ʼ������a,b
		fillRandom(matrix1);
		fillRandom(matrix2);
		startTime = new Date().getTime();

		// ���a,b
		// printMatrix(matrix1);
		// printMatrix(matrix2);

		// �����߳�,���� <= 4
		for (int i = 0; i < 4; i++) {
			if (index < m) {
				Thread t = new Thread(new MyThread());
				t.start();
			} else {
				break;
			}
		}

		// �ȴ����������
		while (threadCount != 0) {
			Thread.sleep(20);
		}

		// printMatrix(matrix3);
		long finishTime = new Date().getTime();
		System.out.println("�������,��ʱ" + (finishTime - startTime) + "����");
	}

	static void printMatrix(int[][] x) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				System.out.print(x[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	static void fillRandom(int[][] x) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				// ÿ��Ԫ������Ϊ0��99�������Ȼ��
				x[i][j] = (int) (Math.random() * 100);
			}
		}
	}

	synchronized static int getTask() {
		if (index < m) {
			return index++;
		}
		return -1;
	}

}

class MyThread implements Runnable {
	int task;

	@Override
	public void run() {
		MultiThreadMatrix.threadCount++;
		while ((task = MultiThreadMatrix.getTask()) != -1) {
			System.out.println("����: " + Thread.currentThread().getName()
					+ "\t��ʼ����� " + (task + 1) + "��");
			for (int i = 0; i < MultiThreadMatrix.n; i++) {
				for (int j = 0; j < MultiThreadMatrix.k; j++) {
					MultiThreadMatrix.matrix3[task][i] += MultiThreadMatrix.matrix1[task][j]
							* MultiThreadMatrix.matrix2[j][i];
				}
			}
		}
		MultiThreadMatrix.threadCount--;
	}
}
