package JavaStructure;
public class SimpleExample4comparator implements Comparator {

	/**
	 * һ�����ﴫ�ݵ��ǽӿ����ͣ���������ʵ��ͳһ�ӿڵĲ�ͬʵ���Ƚ�
	 * ����è�빷��ʵ���˶���ӿڣ�������ߺ��������ԡ�����ԱȽ�
	 */
	@Override
	public int compare(Object o1, Object o2) {
		Cat c1 = (Cat)o1;
		Cat c2 = (Cat)o2;
		if(c1.getWeight() > c2.getWeight()) return -1;
		else if(c1.getHeight() < c2.getHeight()) return 1;
		return 0;
	}

}
