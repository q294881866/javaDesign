package JavaStructure.base;

import JavaStructure.Collection;
import JavaStructure.Iterator;

/**
 * 栈：先进后出的数据结构。计算机科学的基础知识
 */
public class Stack implements Collection{

	/**初始化数组容量*/
	private static final int INIT = 1<<4;
	/**数据容量增长策略 用于数组扩充,例如：newArray[INIT+0.75*INIT]*/
	private static final double Step = 0.75;
	/**数组*/
	private static Object[] stack;
	private int size = 0;//元素个数
	private int index = 0;//数组下标
	
	static{//静态代码块
		stack = new Object[INIT];
	}
	
	public int size(){
		return size;
	}
	
	@Override
	public void add(Object object) {
		stack[index] = object;
		index ++;
	}
	
	public Object remove(){//删除栈顶元素即队尾
		if (0>index) {
			return null;
		}else {
			Object object = stack[index-1];
			System.out.println("index="+index);
			stack[index-1] = null;
			index --;
			return object;
		}
	}

	@Override
	public Iterator iterator() {
		// TODO 留待作业 读者实现
		return null;
	}
}