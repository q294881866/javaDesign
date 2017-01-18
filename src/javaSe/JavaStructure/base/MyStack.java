package JavaStructure.base;

import java.util.ArrayList;
import java.util.List;
/**
 * ����ջ������
 * @author ppf@jiumao.org
 * @date 2016��12��13�� <br>
 * @param <T>
 */
public class MyStack<T> {//��JDK��ArrayList���洢
	private List<T> stack;
	int count,capacity;	//��¼ջ��λ��
	public MyStack(int capacity) {
		this.capacity = capacity;
		stack = new ArrayList<T>(capacity);	//Ĭ��16��С
		count = -1;
	}
	public void push(T o) {//���Ԫ��
		if(count < capacity-1) {
			stack.add(o);
			count++;
		}
		else{System.out.println("ջ��");}
	}
	public T pop() {//��ջ
		if(stack.isEmpty()) {
			System.out.println("ջ��");
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

