package javaSe.special.annotation;
/**
 * ע���ʵ��
 * public enum RetentionPolicy {    SOURCE,    CLASS,     RUNTIME    }  
 * SOURCE��ʾ���Annotation���͵���Ϣֻ�ᱣ����Դ���Դ�뾭������֮��Annotation�����ݾͻ���ʧ�������ᱣ���ڱ���õ�.class�ļ��
 *    CLASS��ʾ���Annotation���͵���Ϣ��Դ�뱣������.class�ļ�Ҳ���������������Щ��Ϣ���ص��������JVM���У���������ã�ϵͳĬ��ֵ��CLASS��                          
 *    RUNTIME��ʾ��Դ�룬������.class��������Ϣ����ִ�е�ʱ��Ҳ�����Щ��Ϣ���ص�JVM��                                                           
 *    ElementType.java                                                                                            
 *    public enum ElementType {                                                                                   
 *    TYPE, FIELD, METHOD, PARAMETER,                                                                             
 *    CONSTRUCTOR,  LOCAL_VARIABLE,                                                                               
 *    ANNOTATION_TYPE,PACKAGE }                                                                                   
 *    @Target�����ElementType�������ƶ�Annotation����������һ�����ͣ�˵��һ��                                                          
 *    TYPE(����),FILED(�ֶ�)METHOD(����)PARAMETER(����)CONSTRUCTOR����������LOCAL_VARIABLE(�ֲ�����)                               
 *    ANNITATION_TYPE��ע�⣩PACKAGE����������TYPE��ָ��������class��interface��eumn��annotation���棬���û���ƶ�����ô�����������κ�����               
 *    ������annotation�����ݼ̳и����࣬�Ǿ���Ҫ�õ�@Inherited���annotation����                                                       
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javaSe.EnumExample;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ExampleAnnotation {
	String color() default "green";
	String value() default "value1";
	int[] arrayAttr() default {7,4,8};
	MetaAnnotation annotationAttr() default @MetaAnnotation("ppf");
}


