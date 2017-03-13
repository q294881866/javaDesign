package javaSe.basic.dataStructure.matrix;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * ���ؾ������<br>
 * Ĭ�Ϲ���8*8����
 */
public class SimpleMatrix {
	int[][] matrix1;//����1
	int[][] matrix2;//����2
	int[][] result;//����1*2
	int m, n, k;
	static int threadCount = 0;
//	int index = -1;// ����ǰ���㵽������
	AtomicInteger index;//ԭ�������

	public SimpleMatrix() {
		this(8, 8, 8);
	}

	public SimpleMatrix(int m, int n, int k) {
		super();
		// 1.�������
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
	 * ���̼߳���
	 */
	public void singleThread() {
		int line;
		while ((line = getLine()) >-1) {
			System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName()
					+ " ����������" + line);
			System.out.println(line);
			computerByLine(n, k, line);
		}
	}

	/**
	 * ���̼߳���
	 * 
	 * @param size
	 *            �߳���
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
			this.matrix = matrix;//��ѡ���̼߳���ľ������
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
	 * �����ࣺ��ӡ�����
	 * ��ӡ�������
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
	 * �����ࣺ����һ���յ�m��n�о���<br>
	 * ÿ��Ԫ�����һ��0��99�������Ȼ��
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
		// 1.���̼߳���
		 s.singleThread();//��ʱ13367����
		 s.printResult(s.result);

		// 2.���̼߳���
//		s.multiThread(4);//4731����
//		// �ȴ����������
//		while (threadCount != 0) {//��ǰ���е��̼߳�����
//			Thread.sleep(20);
//		}

//		s.printResult(s.result);
		long end = System.currentTimeMillis();
		System.out.println("�������,��ʱ" + (end - begin) + "����");
	}
}
