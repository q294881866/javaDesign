package JavaStructure;

/**
 * 模拟 链表的实现<br>
 * 分为两部分：
 * 1.数据的存储Node类
 * 2.数据的管理LinkedList
 */
public class LinkedList implements Collection{
	Node head = null;//头指针
	Node tail = null;//尾指针
	int size = 0;//元素个数
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



