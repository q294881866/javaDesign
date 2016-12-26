package test;

import java.util.concurrent.atomic.AtomicInteger;

public class Test4Atomic {
	static AtomicInteger index;
	
	public static void main(String[] args) {
		index = new AtomicInteger(8);
		int line;
		while ((line=index.getAndDecrement())>-1) {
			System.out.println(line);
			
		}
	}

}
