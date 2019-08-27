package javase.basic.dataStructure.collection;
/**
 * 存储链表单位数据<br>
 * 由数据和下一个结点的引用组成<br>
 * 如果是双向链表可以加一个Node prev前一个结点的引用。
 */
public class Node {
	private Object data;//数据
	private Node next;//下一个节点
	
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
