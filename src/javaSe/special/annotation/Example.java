package javaSe.special.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ע���ʵ��
 * <ul>
 * public enum RetentionPolicy { SOURCE, CLASS, RUNTIME }
 * <li>SOURCE��ʾ���Annotation���͵���Ϣֻ�ᱣ����Դ���Դ�뾭������֮��Annotation�����ݾͻ���ʧ�������ᱣ���ڱ���õ�.
 * class�ļ��
 * <li>CLASS��ʾ���Annotation���͵���Ϣ��Դ�뱣������.class�ļ�Ҳ���������������Щ��Ϣ���ص��������JVM���У���������ã�
 * ϵͳĬ��ֵ��CLASS��
 * <li>RUNTIME��ʾ��Դ�룬������.class��������Ϣ����ִ�е�ʱ��Ҳ�����Щ��Ϣ���ص�JVM��
 * <p>
 * ���annotation�����ݼ̳и����࣬ �Ǿ���Ҫ�õ�@Inherited
 * <ul>
 * ElementType
 * <li>
 * TYPE(����)��������class�� interface ��eumn��annotation����
 * <li>
 * FILED(�ֶ�)
 * <li>METHOD(����)
 * <li>PARAMETER(����)
 * <li>
 * CONSTRUCTOR����������
 * <li>
 * LOCAL_VARIABLE(�ֲ�����)
 * <li>
 * ANNITATION_TYPE��ע�⣩
 * <li>
 * PACKAGE����������TYPE��ָ�����û���ƶ��� ��ô�����������κ�����
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Example {
	String msg();
	int[] iArr() default { 2, 4, 6, 7, 8 };
	Base anno() default @Base("����������һ��");
}
