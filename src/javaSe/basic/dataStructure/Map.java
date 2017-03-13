package javaSe.basic.dataStructure;

/**
 * 内部是一个元素是通过一个Entry(Node)实例来保存的
 * @param <K> 泛型key类型，可以是任意类型
 * @param <V> 泛型value类型，可以是任意类型
 */
public interface Map<K,V> {
	int size();
	V get(Object key);
	V remove(Object key);
	void put(K key,V value);
	 interface Entry<K,V> {
	        K getKey();

	        V getValue();

	        V setValue(V value);

	        boolean equals(Object o);

	        int hashCode();
	    }

	   
}
