package JavaStructure;
/**
 * �洢����λ����<br>
 * �����ݺ���һ�������������<br>
 * �����˫��������Լ�һ��Node prevǰһ���������á�
 */
public class Node {
	private Object data;//����
	private Node next;//��һ���ڵ�
	
	public Node(Object data, Node next) {
		super();
		this.data = data;
		this.next = next;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
}
