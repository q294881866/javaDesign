package javaSe.basic.dataStructure;
/**
 * 模拟实现compareble接口<br>
 * 这里的比较仅仅是业务逻辑意义:<br>
 * 1.返回值0表示对象相等<br>
 * 2.小于0表示当前对象比要比较对象小<br>
 * 3.大于0表示当前对象比要比较对象大
 */
public interface Comparable {
	public int compareTo(Object o);
}
