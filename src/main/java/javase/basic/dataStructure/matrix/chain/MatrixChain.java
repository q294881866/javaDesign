package javase.basic.dataStructure.matrix.chain;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixChain {
	/**
	 * 存放矩阵
	 */
	private List<Matrix> matrixChain;
	
	/**
	 * 存放矩阵子链计算次数
	 */
	private  double[][] m;
	/**
	 * 存放子链最优划分位置
	 */
	private int[][] s;
	
	private static int[] sizeLimit;
	
	
	public MatrixChain(int[] sizeLimit){
		int n = sizeLimit.length-1;
		this.setMatrixChain(generateMatrixChain(sizeLimit));
		this.setM(new double[n][n]);
		this.setS(new int[n][n]);
	}
	
	/**
	 * 生成矩阵链
	 * @param sizeLimit 这是矩阵的行列数组 根据接着的两个元素表示一个矩阵的行与列 
	 * 矩阵链为如下形式：A0A1.....An-1
	 * @return
	 */
	private static  List<Matrix> generateMatrixChain(int[] sizeLimit){
		List<Matrix> resultChain = new ArrayList<Matrix>();
		for(int i =0;i<sizeLimit.length-1;i++){
			Matrix temp = new Matrix(sizeLimit[i],sizeLimit[i+1]);
			Matrix.generateElement(temp);
			resultChain.add(temp);
		} 
		return resultChain;
	}
	/**
	 * 生成numbs个整数表示n-1个矩阵的行与列大小
	 * 此处的15 并没有特殊含义，只是为了保证数组中元素值大小都不为0
	 * @param nums
	 */
	public static int[] generateMatrixDetails(int nums){
		Random random = new Random(47);
		sizeLimit = new int[nums];
		for(int i=0;i<nums;i++){
				sizeLimit[i]= (int)random.nextInt(nums);
			}
		return sizeLimit;
	}
	/**
	 * 普通 的矩阵链直接顺序乘法
	 * @param matrixChain
	 * @return
	 */
	public Matrix mutiplyMatrixChain(List<Matrix> matrixChain){
		checkMatrixChain(matrixChain);
		
		Matrix result =null;
		for(int i=0;i<matrixChain.size()-1;i++){
			if(i==0)
				result = Matrix.mutiplyMatrix(matrixChain.get(0), matrixChain.get(1));
			else
				result = Matrix.mutiplyMatrix(result, matrixChain.get(i+1));
		}
		return result;
	}
	/**
	 * 优化的矩阵链乘
	 * @param matrixChain
	 * @return
	 */
	public Matrix optimizedMutiplyMatixChain(List<Matrix> matrixChain){
		checkMatrixChain(matrixChain);
		
		Matrix result =null;
		long start = System.nanoTime();
		calMinMutiplyTimes(matrixChain,sizeLimit);
		long end = System.nanoTime();
		System.out.println("OptimizedMutiplyMatixChain Method calculate postion totally costs  "+(end-start)+"  nanoseconds");
		result = mutiplyMatrixChainOptimized(matrixChain,s,0,matrixChain.size()-1);
		return result;
	}
	
	 private Matrix mutiplyMatrixChainOptimized(List<Matrix> matrixChain,int[][]s,int i,int j){

         Matrix x,y;

         if (j>i){

                x=mutiplyMatrixChainOptimized(matrixChain,s,i,s[i][j]);

                y=mutiplyMatrixChainOptimized(matrixChain,s,s[i][j]+1,j);

                return Matrix.mutiplyMatrix(x, y);
                

         }

        return matrixChain.get(i); 

  }
	private void initMArray(){
		for(int i =0;i<m.length;i++)
			m[i][i] = 0;
		
		for(int i=0;i<m.length;i++)
			for(int j =i+1;j<m.length;j++)
				m[i][j] = 999999999999999999999.0;
	}
	
	private void initSArray(){
		for(int i=0;i<m.length;i++)
				s[i][i] = 0;
	}
	
	/**
	 * 计算最小乘法次数，将子链最小次数放在m数组中，将加括号位置放在s数组中
	 * @param matrixChain
	 * @param sizeLimit
	 * 算法思想：
	 * 1 计算出m[p][q]的起始值并将括号位置放在p位置
	 * 2在p q之间取出括号位置，计算出比原值小的m[p][q]值替换掉原值，并替换掉括号位置
	 */
	
	private void calMinMutiplyTimes(List<Matrix> matrixChain, int[] sizeLimit) {

		initMArray();
		initSArray();

		int n = matrixChain.size();
		for (int l = 2; l < n; l++) {

			for (int i = 1; i < (n - l + 1); i++) {

				int j = i + l - 1;

				for (int k = i; k < j; k++) {

					double q = m[i][k] + m[k + 1][j] + sizeLimit[i - 1]
							* sizeLimit[k] * sizeLimit[j];

					if (q < m[i][j]) {

						m[i][j] = q;

						s[i][j] = k;

					}

				}

			}

		}

	}
	
	/**
	 * 校验输入的矩阵链是否符合乘法运算条件
	 * @param matrixChain
	 */
	private void checkMatrixChain(List<Matrix> matrixChain){
		if(matrixChain.size()<2){
			System.out.println("矩阵个数小于2，不能做乘法");
			System.exit(0);
		}
	}
	
	
	public void setM(double[][] m) {
		this.m = m;
	}
	public double[][] getM() {
		return m;
	}
	public void setS(int[][] s) {
		this.s = s;
	}
	public int[][] getS() {
		return s;
	}
	public void setMatrixChain(List<Matrix> matrixChain) {
		this.matrixChain = matrixChain;
	}
	public List<Matrix> getMatrixChain() {
		return matrixChain;
	}
	
	public int[] getSizeLimit(){
		return MatrixChain.sizeLimit;
	}
}
