package JavaStructure;

import java.util.List;
import java.util.Set;

/**
 * ģ��ʵ��ArrayList<br>
 * ������������洢Ԫ�أ�
 * ���鲻����ʱ���ݲ����½�һ�������鿽��������Ԫ��
 */
public class ArrayList implements Collection{
	// aka 16 ��̬��������淶����ĸȫ����д���»���_ƴ��
	private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 
	Object[] objects = new Object[DEFAULT_INITIAL_CAPACITY];
	
	int index = 0;//�����ָ��
	@Override
	public void add(Object object) {
		if (index == objects.length) {
			Object[] newObjects = new Object[objects.length*2-1];
			System.arraycopy(objects, 0, newObjects, 0, objects.length);
			objects = newObjects;
		}
		objects[index] = object;
		index++;
	}

	@Override
	public int size() {
		return index;
	}

	@Override
	public Iterator iterator() {
		return new ArrayListIterator();
	}
	private class ArrayListIterator implements Iterator{
		private int currentIndex = 0;
		
		@Override
		public Object next() {
			Object object = objects[currentIndex];
			currentIndex++;
			return object;
		}

		@Override
		public boolean hasNext() {
			
			if(currentIndex >= index)
				return false;
			else 
				return true;
		}
		
	}
}
