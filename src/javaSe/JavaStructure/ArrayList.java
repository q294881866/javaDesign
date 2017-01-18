package JavaStructure;

import java.util.List;
import java.util.Set;

/**
 * 模拟实现ArrayList<br>
 * 本质是用数组存储元素，
 * 数组不够用时根据参数新建一个大数组拷贝旧数组元素
 */
public class ArrayList implements Collection{
	// aka 16 静态常量定义规范：字母全部大写用下划线_拼接
	private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 
	Object[] objects = new Object[DEFAULT_INITIAL_CAPACITY];
	
	int index = 0;//数组的指针
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
