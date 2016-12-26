package JavaStructure;

/**
 * ģ�� �����ʵ��<br>
 * ��Ϊ�����֣�
 * 1.���ݵĴ洢Node��
 * 2.���ݵĹ���LinkedList
 */
public class LinkedList implements Collection{
	Node head = null;//ͷָ��
	Node tail = null;//βָ��
	int size = 0;//Ԫ�ظ���
	@Override
	public void add(Object object) {
		if (null == head) {
			head = new Node(object, null); 
			tail = head;
		}else{
			Node node = new Node(object, null);
			tail.setNext(node);
			tail = node;
		}
		size ++;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public Iterator iterator() {
		return new LinkedListIterator();
	}
	
	public Node removeFirst(){
		Node tmp = head;
		head = head.getNext();
		size--;
		return tmp;
		
	}
	
	private class LinkedListIterator implements Iterator{
			private Node currentIndex = head;

	        LinkedListIterator() {
	        }

	        public boolean hasNext() {
	            return null!=currentIndex;
	        }

	        public Object next() {
	        	currentIndex = currentIndex.getNext();
	            return currentIndex;
	        }
		
	}
	
}



