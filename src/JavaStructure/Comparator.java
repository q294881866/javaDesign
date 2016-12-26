package JavaStructure;
/**
 * 这个是比较的进一步抽象<br>
 * 把比较单独抽取出来，用户可以任意选择不同的比较器。
 * 同时比较器不限于单一用户场景
 */
public interface Comparator {
	int compare(Object o1, Object o2);
}
