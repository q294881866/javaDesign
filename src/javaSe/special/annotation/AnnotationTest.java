package javaSe.special.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

@Example(msg = "��ǰ������")
public class AnnotationTest {

	@Example( msg = "�ι�һȺѼ")
	public static void main(String[] args) throws Exception{
		Method m = AnnotationTest.class.getMethod("main", String[].class);
		if(AnnotationTest.class.isAnnotationPresent(Example.class)){
			Example annotation = (Example)AnnotationTest.class.getAnnotation(Example.class);
			System.out.println(annotation.msg());
			Example a2 = m.getAnnotation(Example.class);
			System.out.println(a2.msg());
			System.out.println(annotation.anno().value());
			System.out.println(Arrays.toString(annotation.iArr()));
		}
	}
}
