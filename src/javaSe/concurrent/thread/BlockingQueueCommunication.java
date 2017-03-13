package javaSe.concurrent.thread;
/*
 * ArrayBlockingQueue：基于数组实现的一个阻塞队列，
 * 在创建ArrayBlockingQueue对象时必须制定容量大小。
 * 并且可以指定公平性与非公平性，默认情况下为非公平的，
 * 即不保证等待时间最长的队列最优先能够访问队列。
 * 
 * LinkedBlockingQueue：基于链表实现的一个阻塞队列，
 * 在创建LinkedBlockingQueue对象时如果不指定容量大小，
 * 则默认大小为Integer.MAX_VALUE。
 * 
 * PriorityBlockingQueue：以上2种队列都是先进先出队列，
 * 而PriorityBlockingQueue却不是，它会按照元素的优先级
 * 对元素进行排序，按照优先级顺序出队，每次出队的元素
 * 都是优先级最高的元素。注意，此阻塞队列为无界阻塞队列，
 * 即容量没有上限（通过源码就可以知道，它没有容器满的信号标志）
 * ，前面2种都是有界队列。
 * 
 * DelayQueue：基于PriorityQueue，一种延时阻塞队列，
 * DelayQueue中的元素只有当其指定的延迟时间到了，
 * 才能够从队列中获取到该元素。DelayQueue也是一个无界队列，
 * 因此往队列中插入数据的操作（生产者）永远不会被阻塞，
 * 而只有获取数据的操作（消费者）才会被阻塞。
 * 
　 */
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
