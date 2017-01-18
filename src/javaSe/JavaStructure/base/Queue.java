package JavaStructure.base;
/**
 * ��javaʵ��һ���򵥵Ķ���<br>
 * ������ʵ�ֵĶ��С�
 * 1.���߿�����������������ʵ�ֶ���
 * 2.���߿��Բ鿴JDK���е�ʵ��
 */
public class Queue {
	private int head,tail;//ͷβ�±�
	private int num;//����Ԫ�ظ���
	private Object[] data;
	
	public Queue(int size){//�������ö��еĴ�С
		//��ʼ��
		head=0;
		tail=0;	
		num=0;//��¼������Ԫ�ظ���
		setData(new Object[size]);
	}
	public void queueput(Object a){
		//��β����Ԫ��
		tail=(tail+1) % this.data.length;
		if (num==this.data.length){
			System.out.println("the queue is full");
		}else{
			num++;
			this.getData()[tail]=a;
		}
		
	}
	public Object queueout(){
		//���׵���Ԫ��
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
