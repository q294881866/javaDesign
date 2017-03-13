package javaSe.basic.dataStructure;

/**
 * �ڲ���һ��Ԫ����ͨ��һ��Entry(Node)ʵ���������
 * @param <K> ����key���ͣ���������������
 * @param <V> ����value���ͣ���������������
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
