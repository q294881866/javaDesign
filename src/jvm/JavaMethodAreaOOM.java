package jvm;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10m
 * <br>这个使用CGLib字节码技术
 * @author ppf@jiumao.org
 * @date 2017年1月11日
 */
public class JavaMethodAreaOOM {
	
	public static void main(String[] args) {
		while (true) {
			Enhancer en = new Enhancer();
			en.setSuperclass(OOMObj.class);
			en.setUseCache(false);
			en.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object obj, Method method, Object[] args,
						MethodProxy proxy) throws Throwable {
					return proxy.invoke(obj, args);
				}
			});
			
			en.create();
		}
	}
}
