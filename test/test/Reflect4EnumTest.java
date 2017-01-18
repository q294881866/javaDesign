package test;

import java.awt.Color;

public class Reflect4EnumTest {
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		Class<?> c1 =  Class.forName("javaSe.Color");
		Color color =(Color) c1.newInstance();
		System.out.println(c1.getDeclaredConstructor());
		System.out.println(color);
	}

}
