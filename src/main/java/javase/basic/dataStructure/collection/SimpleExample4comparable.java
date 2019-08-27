package javase.basic.dataStructure.collection;
/**
 * 这里比较对象的value值<br>
 * 用户在实现时候可以选择任意类型的比较
 */
public class SimpleExample4comparable implements Comparable{

	private int value;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Object o) {
		SimpleExample4comparable simp=(SimpleExample4comparable)o;
		if (this.value>simp.getValue()) {
			return 1;
		}else if(this.value<simp.getValue()){
			return -1;
		}else{
			return 0;
		}
	}
		
	public SimpleExample4comparable(int value) {
			super();
			this.value=value;
	}
}
