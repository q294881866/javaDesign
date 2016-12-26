package JavaStructure.matrix;

/**    
* @Title: MultiThreadMatrix.java 
* @Package matrix 
* @Description: 多线程计算矩阵乘法 
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
		// 矩阵a高度m=100宽度k=80,矩阵b高度k=80宽度n=50 ==> 矩阵c高度m=100宽度n=50
		m = 1024;
		n = 1024;
		k = 1024;
		matrix1 = new int[m][k];
		matrix2 = new int[k][n];
		matrix3 = new int[m][n];

		// 随机初始化矩阵a,b
		fillRandom(matrix1);
		fillRandom(matrix2);
		startTime = new Date().getTime();

		// 输出a,b
		// printMatrix(matrix1);
		// printMatrix(matrix2);

		// 创建线程,数量 <= 4
		for (int i = 0; i < 4; i++) {
			if (index < m) {
				Thread t = new Thread(new MyThread());
				t.start();
			} else {
				break;
			}
		}

		// 等待结束后输出
		while (threadCount != 0) {
			Thread.sleep(20);
		}

		// printMatrix(matrix3);
		long finishTime = new Date().getTime();
		System.out.println("计算完成,用时" + (finishTime - startTime) + "毫秒");
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
				// 每个元素设置为0到99的随机自然数
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
			System.out.println("进程: " + Thread.currentThread().getName()
					+ "\t开始计算第 " + (task + 1) + "行");
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
