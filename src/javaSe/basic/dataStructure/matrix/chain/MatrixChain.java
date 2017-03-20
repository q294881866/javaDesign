package javaSe.basic.dataStructure.matrix.chain;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixChain {
	/**
	 * ��ž���
	 */
	private List<Matrix> matrixChain;
	
	/**
	 * ��ž��������������
	 */
	private  double[][] m;
	/**
	 * ����������Ż���λ��
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
	 * ���ɾ�����
	 * @param sizeLimit ���Ǿ������������ ���ݽ��ŵ�����Ԫ�ر�ʾһ������������� 
	 * ������Ϊ������ʽ��A0A1.....An-1
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
	 * ����numbs��������ʾn-1������������д�С
	 * �˴���15 ��û�����⺬�壬ֻ��Ϊ�˱�֤������Ԫ��ֵ��С����Ϊ0
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
	 * ��ͨ �ľ�����ֱ��˳��˷�
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
	 * �Ż��ľ�������
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
	 * ������С�˷���������������С��������m�����У���������λ�÷���s������
	 * @param matrixChain
	 * @param sizeLimit
	 * �㷨˼�룺
	 * 1 �����m[p][q]����ʼֵ��������λ�÷���pλ��
	 * 2��p q֮��ȡ������λ�ã��������ԭֵС��m[p][q]ֵ�滻��ԭֵ�����滻������λ��
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
	 * У������ľ������Ƿ���ϳ˷���������
	 * @param matrixChain
	 */
	private void checkMatrixChain(List<Matrix> matrixChain){
		if(matrixChain.size()<2){
			System.out.println("�������С��2���������˷�");
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
