package javaSe.special.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited/*ע�������ڸ��࣬�����̳�ע��*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
public @interface/*ע������*/ Base {
	String value() default/*Ĭ��ֵ*/ "default";
}
