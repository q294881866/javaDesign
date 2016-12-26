package javaSe;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import annotationClassloader.ReflectPoint;

public class IntroSpectorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	private static void setProperties(Object pt1, String propertyName,
			Object value) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,pt1.getClass());
		Method methodSetX = pd2.getWriteMethod();
		methodSetX.invoke(pt1,value);
	}

	private static Object getProperty(Object pt1, String propertyName)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		/*PropertyDescriptor pd = new PropertyDescriptor(propertyName,pt1.getClass());
		Method methodGetX = pd.getReadMethod();
		Object retVal = methodGetX.invoke(pt1);*/
		
		BeanInfo beanInfo =  Introspector.getBeanInfo(pt1.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		Object retVal = null;
		for(PropertyDescriptor pd : pds){
			if(pd.getName().equals(propertyName))
			{
				Method methodGetX = pd.getReadMethod();
				retVal = methodGetX.invoke(pt1);
				break;
			}
		}
		return retVal;
	}

}
