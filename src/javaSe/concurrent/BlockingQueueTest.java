package javaSe.concurrent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
//			put();
			take();
	}
	
	/**
	 * ArrayBlockingQueue
	 * 提取数据
	 */
	public static void take(){
		 final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
			for(int i=0;i<4;i++){
				new Thread(new Runnable(){
					@Override
					public void run() {
						while(true){
							try {
								String log = queue.take();
								parseLog(log);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
				}).start();
			}
			
			System.out.println("begin:"+(System.currentTimeMillis()/1000));
			/*模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。
			修改程序代码，开四个线程让这16个对象在4秒钟打完。
			*/
			for(int i=0;i<16;i++){  //这行代码不能改动
				final String log = ""+(i+1);//这行代码不能改动
				{
						try {
							queue.put(log);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		     			//Test.parseLog(log);
				}
			}
	}
	
	/**
	 * ArrayBlockingQueue
	 * 添加数据
	 */
	public static void put(){
		final BlockingQueue queue = new ArrayBlockingQueue(3);
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep((long)(Math.random()*1000));
							System.out.println(Thread.currentThread().getName() + "准备放数据!");							
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + "已经放了数据，" + 							
										"队列目前有" + queue.size() + "个数据");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
				
			}.start();
		}
		
		new Thread(){
			public void run(){
				while(true){
					try {
						//将此处的睡眠时间分别改为100和1000，观察运行结果
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "准备取数据!");
						queue.take();
						System.out.println(Thread.currentThread().getName() + "已经取走数据，" + 							
								"队列目前有" + queue.size() + "个数据");					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();	
	}

	
	
	
	/**
	 * 日志打印
	 * @param log
	 */
		public static void parseLog(String log){
			System.out.println(log+":"+(System.currentTimeMillis()/1000));
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	
}
