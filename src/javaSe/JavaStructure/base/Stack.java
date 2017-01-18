package JavaStructure.base;

import JavaStructure.Collection;
import JavaStructure.Iterator;

/**
 * ջ���Ƚ���������ݽṹ���������ѧ�Ļ���֪ʶ
 */
public class Stack implements Collection{

	/**��ʼ����������*/
	private static final int INIT = 1<<4;
	/**���������������� ������������,���磺newArray[INIT+0.75*INIT]*/
	private static final double Step = 0.75;
	/**����*/
	private static Object[] stack;
	private int size = 0;//Ԫ�ظ���
	private int index = 0;//�����±�
	
	static{//��̬�����
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
	
	public Object remove(){//ɾ��ջ��Ԫ�ؼ���β
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
		// TODO ������ҵ ����ʵ��
		return null;
	}
}