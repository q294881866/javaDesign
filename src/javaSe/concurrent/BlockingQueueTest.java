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
	 * ��ȡ����
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
			/*ģ�⴦��16����־������Ĵ��������16����־���󣬵�ǰ������Ҫ����16����ܴ�ӡ����Щ��־��
			�޸ĳ�����룬���ĸ��߳�����16��������4���Ӵ��ꡣ
			*/
			for(int i=0;i<16;i++){  //���д��벻�ܸĶ�
				final String log = ""+(i+1);//���д��벻�ܸĶ�
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
	 * �������
	 */
	public static void put(){
		final BlockingQueue queue = new ArrayBlockingQueue(3);
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep((long)(Math.random()*1000));
							System.out.println(Thread.currentThread().getName() + "׼��������!");							
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + "�Ѿ��������ݣ�" + 							
										"����Ŀǰ��" + queue.size() + "������");
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
						//���˴���˯��ʱ��ֱ��Ϊ100��1000���۲����н��
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "׼��ȡ����!");
						queue.take();
						System.out.println(Thread.currentThread().getName() + "�Ѿ�ȡ�����ݣ�" + 							
								"����Ŀǰ��" + queue.size() + "������");					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();	
	}

	
	
	
	/**
	 * ��־��ӡ
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
