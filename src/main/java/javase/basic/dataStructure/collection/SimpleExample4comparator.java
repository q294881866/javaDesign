package javase.basic.dataStructure.collection;
public class SimpleExample4comparator implements Comparator {

	/**
	 * 一般这里传递的是接口类型，这样方便实现统一接口的不同实例比较
	 * 比如猫与狗都实现了动物接口，都有身高和体重属性。便可以比较
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
