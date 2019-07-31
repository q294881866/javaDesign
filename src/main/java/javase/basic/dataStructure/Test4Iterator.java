package javase.basic.dataStructure;

public class Test4Iterator {

	
	public static void main(String[] args) {
		Collection collection = new ArrayList();
		collection.add("abc");
		collection.add("abc");
		collection.add("abc");
		collection.add("abc");
		Iterator iterator = collection.iterator();
		
		while (iterator.hasNext()) {
			Object object = iterator.next();
			System.out.println("object = "+object);
			
		}
		
	}
	
}
