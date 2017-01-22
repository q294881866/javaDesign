package javaSe.special.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import Domain.Bean;

/**
 * JDK中内省示例
 * 
 * @see Introspector PropertyDescriptor
 */
public class IntroSpectorTest {

	public static class UserInfo {
		private String name; public UserInfo() { }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	static UserInfo userInfo = new UserInfo();

	// ===============================PropertyDescriptor====================================================
	public static void setProperty(Object bean, String name, Object... value)
			throws Exception {
		PropertyDescriptor pd = new PropertyDescriptor(name, bean.getClass());
		pd.getWriteMethod().invoke(bean, value);
	}

	public static Object getProperty(Object bean, String name) throws Exception {
		PropertyDescriptor pd = new PropertyDescriptor(name, bean.getClass());
		return pd.getReadMethod().invoke(bean);
	}

	// ===============================Introspector====================================================

	public static void setPropByIntro(Object bean, String name, Object... value)
			throws Exception {
		BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
		for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
			if (pd.getName().equals(name)) {
				pd.getWriteMethod().invoke(bean, value);
				break;
			}
		}
	}

	public static void getPropByIntro(Object bean, String name)
			throws Exception {
		BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
		for (PropertyDescriptor pd : bi.getPropertyDescriptors()) {
			if (pd.getName().equals(name)) {
				pd.getReadMethod().invoke(bean);
				break;
			}
		}
	}

	// ===============================Apache BeanUtils====================================================
	public static void apacheBeanUtils() throws Exception {
		BeanUtils.setProperty(userInfo, "name", "jiumao");
		BeanUtils.getProperty(userInfo, "name");
		BeanUtils.getProperty(userInfo, "name").getClass().getName();

		PropertyUtils.setProperty(userInfo, "name", "jiumao");
		PropertyUtils.getProperty(userInfo, "name");
		PropertyUtils.getProperty(userInfo, "name").getClass().getName();

	}

}
