package JavaStructure.base;
/**
 * 用java实现一个简单的队列<br>
 * 用数组实现的队列。
 * 1.读者可以想想怎样用链表实现队列
 * 2.读者可以查看JDK队列的实现
 */
public class Queue {
	private int head,tail;//头尾下标
	private int num;//队列元素个数
	private Object[] data;
	
	public Queue(int size){//传入设置队列的大小
		//初始化
		head=0;
		tail=0;	
		num=0;//记录队列中元素个数
		setData(new Object[size]);
	}
	public void queueput(Object a){
		//队尾加入元素
		tail=(tail+1) % this.data.length;
		if (num==this.data.length){
			System.out.println("the queue is full");
		}else{
			num++;
			this.getData()[tail]=a;
		}
		
	}
	public Object queueout(){
		//队首弹出元素
		Object temp=this.getData()[head];
		if (num==0){
			System.out.println("the queue is empty"+head+" "+tail);
			
		}else{
			head=(head+1) % this.data.length;
			num--;
			return temp;
		}
		return -999999;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getTail() {
		return tail;
	}

	public void setTail(int tail) {
		this.tail = tail;
	}
	
}
