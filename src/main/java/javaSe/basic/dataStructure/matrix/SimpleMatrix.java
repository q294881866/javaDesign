package javaSe.basic.dataStructure.matrix;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 朴素矩阵计算<br>
 * 默认构建8*8方阵
 */
public class SimpleMatrix {
	int[][] matrix1;//矩阵1
	int[][] matrix2;//矩阵2
	int[][] result;//矩阵1*2
	int m, n, k;
	static int threadCount = 0;
//	int index = -1;// 矩阵当前计算到多少行
	AtomicInteger index;//原子类操作

	public SimpleMatrix() {
		this(8, 8, 8);
	}

	public SimpleMatrix(int m, int n, int k) {
		super();
		// 1.构造矩阵
		this.m = m;
		this.n = n;
		this.k = k;
		index= new AtomicInteger(m);
		matrix1 = new int[m][k];
		matrix2 = new int[k][n];
		result = new int[m][n];
		createMatrixByRadom(matrix1);
		createMatrixByRadom(matrix2);

	}

	public  int getLine() {  		 //public synchronized int getLine() {
	return index.decrementAndGet();  //	if (index < m) {    
	}                                //		return index++; 
	                                 //	}                   
	                                 //	return -1;          
	                                 //}                    
	 

	/**
	 * 单线程计算
	 */
	public void singleThread() {
		int line;
		while ((line = getLine()) >-1) {
			System.out.println("当前线程：" + Thread.currentThread().getName()
					+ " 处理行数：" + line);
			System.out.println(line);
			computerByLine(n, k, line);
		}
	}

	/**
	 * 多线程计算
	 * 
	 * @param size
	 *            线程数
	 */
	public void multiThread(int size) {
		if (1 > size) {
			size = 1;
		}
		for (int i = 0; i < size; i++) {
			Thread t = new Thread(new MyThread(this));
			t.start();
		}
	}

	class MyThread implements Runnable {
		final SimpleMatrix matrix;

		public MyThread(SimpleMatrix matrix) {
			this.matrix = matrix;//可选择线程计算的矩阵对象
		}

		@Override
		public void run() {
			SimpleMatrix.threadCount++;
			matrix.singleThread();
			SimpleMatrix.threadCount--;
		}
	}

	private void computerByLine(int n, int k, int line) {
		if (line < 0) {
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				result[line][i] += matrix1[line][j] * matrix2[j][i];
			}
		}
	}

	/**
	 * 工具类：打印结果，
	 * 打印传入矩阵
	 * @param x
	 */
	public static void printResult(int[][] x) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				System.out.print(x[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	/**
	 * 工具类：传入一个空的m行n列矩阵<br>
	 * 每个元素填充一个0到99的随机自然数
	 * @param mn
	 * @return 
	 */
	public static int[][] createMatrixByRadom(int[][] mn) {
		for (int i = 0; i < mn.length; i++) {
			for (int j = 0; j < mn[i].length; j++) {
				mn[i][j] = (int) (Math.random() * 100);
			}
		}
		return mn;
	}

	public static void main(String[] args) throws Exception {
		SimpleMatrix s = new SimpleMatrix();
		long begin = System.currentTimeMillis();
		// 1.单线程计算
		 s.singleThread();//用时13367毫秒
		 s.printResult(s.result);

		// 2.多线程计算
//		s.multiThread(4);//4731毫秒
//		// 等待结束后输出
//		while (threadCount != 0) {//当前运行的线程计数器
//			Thread.sleep(20);
//		}

//		s.printResult(s.result);
		long end = System.currentTimeMillis();
		System.out.println("计算完成,用时" + (end - begin) + "毫秒");
	}
}
