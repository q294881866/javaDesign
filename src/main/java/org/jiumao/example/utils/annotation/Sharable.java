package org.jiumao.example.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 对象可以共享，线程安全
 * @author ppf@jiumao.org
 * @date 2017年9月20日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Sharable {

}
