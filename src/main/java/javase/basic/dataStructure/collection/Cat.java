package javase.basic.dataStructure.collection;

public class Cat implements java.lang.Comparable<Cat> {
	private int height;
	private int weight;
	private Comparator comparator =  new SimpleExample4comparator();
	@Override
	public String toString() {
		return height + "|" + weight;
	}
	@Override
	public int compareTo(Cat o) {
		return comparator.compare(this, o);
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
