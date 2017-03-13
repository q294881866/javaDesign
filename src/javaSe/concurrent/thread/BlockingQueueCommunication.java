package javaSe.concurrent.thread;
/*
 * ArrayBlockingQueue����������ʵ�ֵ�һ���������У�
 * �ڴ���ArrayBlockingQueue����ʱ�����ƶ�������С��
 * ���ҿ���ָ����ƽ����ǹ�ƽ�ԣ�Ĭ�������Ϊ�ǹ�ƽ�ģ�
 * ������֤�ȴ�ʱ����Ķ����������ܹ����ʶ��С�
 * 
 * LinkedBlockingQueue����������ʵ�ֵ�һ���������У�
 * �ڴ���LinkedBlockingQueue����ʱ�����ָ��������С��
 * ��Ĭ�ϴ�СΪInteger.MAX_VALUE��
 * 
 * PriorityBlockingQueue������2�ֶ��ж����Ƚ��ȳ����У�
 * ��PriorityBlockingQueueȴ���ǣ����ᰴ��Ԫ�ص����ȼ�
 * ��Ԫ�ؽ������򣬰������ȼ�˳����ӣ�ÿ�γ��ӵ�Ԫ��
 * �������ȼ���ߵ�Ԫ�ء�ע�⣬����������Ϊ�޽��������У�
 * ������û�����ޣ�ͨ��Դ��Ϳ���֪������û�����������źű�־��
 * ��ǰ��2�ֶ����н���С�
 * 
 * DelayQueue������PriorityQueue��һ����ʱ�������У�
 * DelayQueue�е�Ԫ��ֻ�е���ָ�����ӳ�ʱ�䵽�ˣ�
 * ���ܹ��Ӷ����л�ȡ����Ԫ�ء�DelayQueueҲ��һ���޽���У�
 * ����������в������ݵĲ����������ߣ���Զ���ᱻ������
 * ��ֻ�л�ȡ���ݵĲ����������ߣ��Żᱻ������
 * 
�� */
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueCommunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Business business = new Business();
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
					
						for(int i=1;i<=50;i++){
							business.sub(i);
						}
						
					}
				}
		).start();
		
		for(int i=1;i<=50;i++){
			business.main(i);
		}
		
	}

	 static class Business {
		 
		 
		  BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
		  BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
		  
		  {
			  Collections.synchronizedMap(null);
			  try {
				  System.out.println("xxxxxdfsdsafdsa");
				queue2.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  
		  public  void sub(int i){
			  	try {
					queue1.put(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int j=1;j<=10;j++){
					System.out.println("sub thread sequece of " + j + ",loop of " + i);
				}
				try {
					queue2.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  
		  public  void main(int i){
			  	try {
					queue2.put(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int j=1;j<=100;j++){
					System.out.println("main thread sequece of " + j + ",loop of " + i);
				}
				try {
					queue1.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
	  }

}
