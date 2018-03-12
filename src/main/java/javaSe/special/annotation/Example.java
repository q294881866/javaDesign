package javaSe.special.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解简单实用
 * <ul>
 * public enum RetentionPolicy { SOURCE, CLASS, RUNTIME }
 * <li>SOURCE表示这个Annotation类型的信息只会保留在源码里，源码经过编译之后，Annotation的数据就会消失，并不会保留在编译好的.
 * class文件里；
 * <li>CLASS表示这个Annotation类型的信息在源码保留，在.class文件也保留，但不会把这些信息加载到虚拟机（JVM）中，如果不设置，
 * 系统默认值是CLASS；
 * <li>RUNTIME表示在源码，编译后的.class都保存信息，在执行的时候也会把这些信息加载到JVM中
 * <p>
 * 想把annotation的数据继承给子类， 那就需要用到@Inherited
 * <ul>
 * ElementType
 * <li>
 * TYPE(类型)可以用在class， interface ，eumn，annotation上面
 * <li>
 * FILED(字段)
 * <li>METHOD(方法)
 * <li>PARAMETER(参数)
 * <li>
 * CONSTRUCTOR（构造器）
 * <li>
 * LOCAL_VARIABLE(局部变量)
 * <li>
 * ANNITATION_TYPE（注解）
 * <li>
 * PACKAGE（包）其中TYPE是指，如果没有制定， 那么它可以用在任何上面
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Example {
	String msg();
	int[] iArr() default { 2, 4, 6, 7, 8 };
	Base anno() default @Base("快来快来数一数");
}
