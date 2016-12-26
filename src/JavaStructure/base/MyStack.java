package JavaStructure.base;

import java.util.ArrayList;
import java.util.List;
/**
 * 泛型栈的例子
 * @author ppf@jiumao.org
 * @date 2016年12月13日 <br>
 * @param <T>
 */
public class MyStack<T> {//用JDK的ArrayList做存储
	private List<T> stack;
	int count,capacity;	//记录栈顶位置
	public MyStack(int capacity) {
		this.capacity = capacity;
		stack = new ArrayList<T>(capacity);	//默认16大小
		count = -1;
	}
	public void push(T o) {//添加元素
		if(count < capacity-1) {
			stack.add(o);
			count++;
		}
		else{System.out.println("栈满");}
	}
	public T pop() {//出栈
		if(stack.isEmpty()) {
			System.out.println("栈空");
			return null;
		}
		T o = stack.remove(count);
		count--;
		return o;
	}
	public boolean isEmpty(){
		return stack.isEmpty();
	}
}

