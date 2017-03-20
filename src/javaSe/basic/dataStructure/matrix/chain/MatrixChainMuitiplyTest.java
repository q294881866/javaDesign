package javaSe.basic.dataStructure.matrix.chain;
/**
 * ��������
 * @author shuofenglxy
 */
public class MatrixChainMuitiplyTest {

	
	public static void main(String[]args){
		
		MatrixChain demo = new MatrixChain(MatrixChain.generateMatrixDetails(200));
		
		
		/**�Ż��ĵľ�����ȡ��Ѽ�����λ�����
		 * ͳ������ʱ��͹��Ƶļӷ�����
		 */
		long startNomal = System.nanoTime();
		demo.optimizedMutiplyMatixChain(demo.getMatrixChain());
		long endNomal = System.nanoTime();
		System.out.println("OptimizedMutiplyMatixChain Method totally costs  "+(endNomal-startNomal)+"  nanoseconds");
		System.out.println("OptimizedMutiplyMatixChain Method caluclating times is: "+Matrix.getCount()+" times");
		
		//��ͳ���������������0
		Matrix.setCount(0);
		
		System.out.println("---------------------");
		
		/**�����ľ�����ֱ�����
		 * ͳ������ʱ��͹��Ƶļӷ�����
		 */
		startNomal = System.nanoTime();
		demo.mutiplyMatrixChain(demo.getMatrixChain());
		endNomal = System.nanoTime();
		System.out.println("NomalMethod totally costs  "+(endNomal-startNomal)+"  nanoseconds");
		System.out.println("NomalMethod caluclating times is: "+Matrix.getCount()+" times");
	}
}

