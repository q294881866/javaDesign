package annotationClassloader;

import java.lang.reflect.Method;

import javax.jws.soap.InitParam;

@ExampleAnnotation(annotationAttr=@MetaAnnotation("flx"),color="red",value="abc",arrayAttr=1)
public class AnnotationTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	@ExampleAnnotation(color="xyz")
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		/*System.runFinalizersOnExit(true);
		if(AnnotationTest.class.isAnnotationPresent(ExampleAnnotation.class)){
			ExampleAnnotation annotation = (ExampleAnnotation)AnnotationTest.class.getAnnotation(ExampleAnnotation.class);
			System.out.println(annotation.color());
			System.out.println(annotation.value());
			System.out.println(annotation.arrayAttr().length);
			System.out.println(annotation.annotationAttr().value());
		}*/
		
		Method mainMethod = AnnotationTest.class.getMethod("main", String[].class);
		ExampleAnnotation annotation2 = (ExampleAnnotation)mainMethod.getAnnotation(ExampleAnnotation.class);
		System.out.println(annotation2.color());
	}

	@Deprecated
	public static void sayHello(){
		System.out.println("hi,ppf");
	}
}
