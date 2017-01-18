package JavaStructure.matrix.chain;


import java.util.Random;

public class Matrix {

	private int m;//矩阵行
	
	private int n;//矩阵列
	
	private double [][] matrix ;//存放矩阵元素
	private  static int count=0;//统计一次运算中调用的乘法运算次数
	
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Matrix.count = count;
	}

	public  Matrix(int m,int n){
		this.setM(m);
		this.setN(n);
		setMatrix(new double[m][n]);
		
	}
	
	public  static  Matrix mutiplyMatrix(Matrix A,Matrix B){
		if(A.getN()!=B.getM()){
			System.out.println("不合矩阵相乘条件");
			System.exit(0);
		}
		Matrix result = new Matrix(A.getM(),B.getN());
		for(int i = 0;i<result.getM();i++)
			for(int j = 0;j<result.getN();j++){
				double temp=0;
				for(int p = 0;p<A.getN();p++){
					temp += A.getMatrix()[i][p]*B.getMatrix()[p][j];
					count++;
					}
				result.getMatrix()[i][j] = temp;
			}
		return result;
	}

	public static Matrix generateElement(Matrix input){
		Random random = new Random(47);
		for(int i = 0;i<input.getM();i++)
			for(int j = 0;j<input.getN();j++)
				input.getMatrix()[i][j] = random.nextDouble()*1000;
		
		return input;
	}
	public void setM(int m) {
		this.m = m;
	}

	public int getM() {
		return m;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public double [][] getMatrix() {
		return matrix;
	}
}

