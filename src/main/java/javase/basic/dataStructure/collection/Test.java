package javase.basic.dataStructure.collection;

import java.util.Objects;


public class Test<K,V> implements Map.Entry<K,V>{
	  Test<K,V> head ;
	  final K key;
	  V value;
	 Test<K,V> next;
	 Test<K,V> tail;
	 int hash;
	 
	 /**
	  * 创建一个新的entry类似树结构
	  */
	Test(int h,K k,V v) {
			value = v;
          key = k;
          hash = h;
          if (head == null) {
  			head=this;
  			tail=this;
  		}
          tail.next=this;
          tail=this;
	}
	 
	 
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V newValue) {
		V oldValue = value;
		value = newValue;
		return oldValue;
	}
	
	 public final int hashCode() {
          return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }
	 
	 public Iterator iterator() {
			return new EntryIterator(this);
	 }
	 
		
		private class EntryIterator implements Iterator{
				private Test currentEntry;

			EntryIterator(Test<K, V> entry) {
		           this.currentEntry = entry;
		        }

		        public boolean hasNext() {
		            return null!=currentEntry.next;
		        }

		        public Object next() {
		        	currentEntry = currentEntry.next;
		            return currentEntry;
		        }
			
		}


		public Test<K, V> getNext() {
			return next;
		}


		public void setNext(Test<K, V> next) {
			this.next = next;
		}
	 
		public static void main(String[] args) {
			Test test=new Test(0, 0, 1);
			System.err.println(test.head.hash);
		}
	
}
