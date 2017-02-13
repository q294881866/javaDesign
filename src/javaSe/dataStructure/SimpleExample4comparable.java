package javaSe.dataStructure;
/**
 * ����Ƚ϶����valueֵ<br>
 * �û���ʵ��ʱ�����ѡ���������͵ıȽ�
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
