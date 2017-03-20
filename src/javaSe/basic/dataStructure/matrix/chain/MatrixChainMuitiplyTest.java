package javaSe.basic.dataStructure.matrix.chain;
/**
 * 矩阵问题
 * @author shuofenglxy
 */
public class MatrixChainMuitiplyTest {

	
	public static void main(String[]args){
		
		MatrixChain demo = new MatrixChain(MatrixChain.generateMatrixDetails(200));
		
		
		/**优化的的矩阵链取最佳加括号位置相乘
		 * 统计消耗时间和共计的加法次数
		 */
		long startNomal = System.nanoTime();
		demo.optimizedMutiplyMatixChain(demo.getMatrixChain());
		long endNomal = System.nanoTime();
		System.out.println("OptimizedMutiplyMatixChain Method totally costs  "+(endNomal-startNomal)+"  nanoseconds");
		System.out.println("OptimizedMutiplyMatixChain Method caluclating times is: "+Matrix.getCount()+" times");
		
		//将统计运算次数总数归0
		Matrix.setCount(0);
		
		System.out.println("---------------------");
		
		/**正常的矩阵链直接相乘
		 * 统计消耗时间和共计的加法次数
		 */
		startNomal = System.nanoTime();
		demo.mutiplyMatrixChain(demo.getMatrixChain());
		endNomal = System.nanoTime();
		System.out.println("NomalMethod totally costs  "+(endNomal-startNomal)+"  nanoseconds");
		System.out.println("NomalMethod caluclating times is: "+Matrix.getCount()+" times");
	}
}

