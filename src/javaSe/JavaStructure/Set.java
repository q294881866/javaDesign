package JavaStructure;
/**
 * set集合的实现要保证元素的唯一性<br>
 * hashCode 和equals方法是重要的实现
 */
public interface Set extends Collection {
	/**
	 * 定义内容相等的规则
	 * @param object
	 * @return
	 */
	boolean equals(Object object);
	
	
	/**
	 * 定义数据的hash code规则
	 */
	int hashCode(); 
	
	
	
}
