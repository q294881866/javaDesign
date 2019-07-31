package javase.basic.dataStructure.base;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import javase.basic.dataStructure.Collection;
import javase.basic.dataStructure.Iterator;

/**
 * 堆可以被看成是一棵树，如：堆排序。
 * 
 * @author ppf@jiumao.org
 * @date 2016年12月13日 <br>
 * @param <T>
 */
public class Heap<T extends Comparable<T>> implements Collection {
	ArrayList<T> items;
	int cursor; // 用于计数

	public Heap(int size) {
		items = new ArrayList<T>(size);
		cursor = -1;
	}

	public Heap() {
		items = new ArrayList<T>();
		cursor = -1;
	}

	/**
	 * 上移操作
	 * 
	 * @param index
	 *            被上移元素的起始位置。
	 */
	void siftUp(int index) {
		T intent = items.get(index);
		while (index > 0) {
			int pindex = (index - 1) / 2;
			T parent = items.get(pindex);
			if (intent.compareTo(parent) > 0) {// 上移的条件，比父节点大
				items.set(index, parent);
				index = pindex;
			} else
				break;
		}
		items.set(index, intent);
	}

	/**
	 * 下移操作
	 */
	void siftDown(int index) {
		T intent = items.get(index);
		int l_index = 2 * index + 1;
		while (l_index < items.size()) {
			T maxChild = items.get(l_index);
			int max_index = l_index;

			int r_index = l_index + 1;
			if (r_index < items.size()) {
				T rightChild = items.get(r_index);
				if (rightChild.compareTo(maxChild) > 0) {
					maxChild = rightChild;
					max_index = r_index;
				}
			}

			if (maxChild.compareTo(intent) > 0) {
				items.set(index, maxChild);
				index = max_index;
				l_index = index * 2 + 1;
			} else
				break;
		}
		items.set(index, intent);
	}

	public T deleteTop() {
		if (items.isEmpty()) {
			throw new NoSuchElementException();
		}
		// 先获取顶部节点
		T maxItem = items.get(0);
		T lastItem = items.remove(items.size() - 1);
		if (items.isEmpty()) {
			return lastItem;
		}
		// 将尾部的节点放置顶部，下移，完成重构
		items.set(0, lastItem);
		siftDown(0);
		return maxItem;
	}

	public void clear(){
		items.clear();
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}

	public int size() {
		return items.size();
	}
	@Override
	public void add(Object item) {
		// 先添加到最后
		items.add((T) item);
		// 循环上移，以完成重构 这里构建大根堆
		siftUp(items.size() - 1);
	}

	@Override
	public Iterator iterator() {
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator {
		private int currentIndex = 0;
		@Override
		public Object next() {
			Object o = items.get(currentIndex);
			currentIndex++;
			return o;
		}
		@Override
		public boolean hasNext() {
			if (currentIndex < 0 || currentIndex >= items.size()) {
				return false;
			}
			if (null != items.get(currentIndex)) {
				return true;
			}
			return false;
		}
		HeapIterator() {		}
		public T first() {
			if (items.size() == 0)
				return null;
			cursor = 0;
			return items.get(0);
		}
	}
}
