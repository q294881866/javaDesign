package ElementsOfReusable;

import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.annotation.Resource;

import proxy.InvocationHandler;
import sun.reflect.CallerSensitive;
import sun.reflect.MethodAccessor;
import sun.reflect.Reflection;

public class DesignPatternsInJava<E> {

	/**
	 * <P>
	 * 抽象工厂<br>
	 * <P>
	 * 
	 * @see javax.xml.parsers.DocumentBuilderFactory#newInstance()
	 * @see javax.xml.transform.TransformerFactory#newInstance()
	 * @see javax.xml.xpath.XPathFactory#newInstance()
	 * 
	 *      <P>
	 *      建造者
	 *      <P>
	 *      <br>
	 * @see java.lang.StringBuilder#append()
	 * @see java.lang.StringBuffer#append()
	 * @see java.nio.ByteBuffer#put() (also
	 *      on CharBuffer, ShortBuffer, IntBuffer,
	 *       LongBuffer, FloatBuffer and DoubleBuffer)
	 * @see javax.swing.GroupLayout.Group#addComponent() All implementations of 
	 * @see java.lang.Appendable
	 * 
	 *      <p>
	 *      工厂（Factory method ）
	 *      </p>
	 * <br>
	 * @see java.util.Calendar#getInstance() CharBuffer
	 * @see java.util.ResourceBundle#getBundle()
	 * @see java.text.NumberFormat#getInstance()
	 * @see java.nio.charset.Charset#forName()
	 * @see java.net.URLStreamHandlerFactory#createURLStreamHandler
	 * @see (String) (Returns singleton object per protocol)
	 * @see java.util.EnumSet#of()
	 * @see javax.xml.bind.JAXBContext#createMarshaller()
	 * @see and other similar methods
	 *          <P>
	 *          原型（Prototype） <br>
	 *          <P>
	 * @see java.lang.Object#clone()  (the class has to implement
	 * @see java.lang.Cloneable)
	 * 
	 *                           <P>
	 *                           单例（Singleton）
	 *                           <P>
	 * @see java.lang.Runtime#getRuntime()
	 * @see java.awt.Desktop#getDesktop()
	 * @see java.lang.System#getSecurityManager()
	 */
	public static void Creational() {
	}

	/**
	 * 结构型模式（Structural patterns）
	 * 
	 * 
	 * <P>
	 * 适配器（Adapter）
	 * </P>
	 * 
	 * @see java.util.Arrays#asList()
	 * @see java.util.Collections#list()
	 * @see java.util.Collections#enumeration()
	 * @see java.io.InputStreamReader(InputStream)
	 * @see java.io.OutputStreamWriter(OutputStream)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal() #unmarshal()
	 * 
	 *      <P>
	 *      桥接（Bridge ）
	 *      </P>
	 * @see java.util.Collections#newSetFromMap(Map<E, Boolean>)
	 *                                                  and singletonXXX() 
	 * 
	 *                                                 <P>
	 *                                                 组合（Composite）
	 *                                                 <P>
	 * @see java.awt.Container#add(Component)
	 * @see javax.faces.component.UIComponent#getChildren() (practically all
	 *      over JSF UI thus)
	 *      <P>
	 *      装饰（Decorator）
	 *      <P>
	 *      All subclasses of 
	 * @see java.io.InputStream, OutputStream, Reader and Writer have a
	 *      constructor taking an instance of same type. java.util.Collections,
	 *      the checkedXXX(), synchronizedXXX() and unmodifiableXXX() methods.
	 * @see javax.servlet.http.HttpServletRequestWrapper
	 *       and HttpServletResponseWrapper
	 *      <P>
	 *      外观（Facade）
	 *      </P>
	 * @see javax.faces.context.FacesContext <P>
	 *      it internally uses among others the abstract/interface
	 *      <P>
	 *      types LifeCycle, ViewHandler, NavigationHandler and many more
	 *      without that the enduser has to worry about it (which are however
	 *      overrideable by injection).
	 * 
	 * @see javax.faces.context.ExternalContext, which internally
	 *      uses ServletContext, HttpSession, HttpServletRequest, 
	 *      HttpServletResponse, etc.
	 *      <P>
	 *      享元（Flyweight）
	 *      </P>
	 * @see java.lang.Integer#valueOf(int) (also
	 *      on Boolean, Byte, Character, Short, Long and BigDecimal) 代理（Proxy）
	 * @see java.lang.reflect.Proxy java.rmi.* javax.ejb.EJB (explanation here)
	 *       
	 * @see javax.inject.Inject (explanation here)
	 * @see javax.persistence.PersistenceContext
	 */
	public static void Structural() {
		
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public void invoke(Object o, Method m) {
				// TODO Auto-generated method stub
				
			}
		};
	     Class<?> proxyClass = Proxy.getProxyClass(DesignPatternsInJava<E>.class.getClassLoader(), Foo.class);
	      proxyClass.getConstructor(InvocationHandler.class).
	                     newInstance(handler);
	}

	/**
	 * <blockquote> 行为型设计模式（Behavioral patterns） </blockquote>
	 * <P>
	 * 责任链（Chain of responsibility）
	 * <P>
	 * 
	 * @see java.util.logging.Logger#log() javax.servlet.Filter#doFilter()
	 *      <P>
	 *      命令（Command）
	 *      <P>
	 *      All implementations of java.lang.Runnable(like java.lang.Thread) All
	 *      implementations of javax.swing.Action
	 *      <P>
	 *      解释器（Interpreter）
	 *      <P>
	 *      java.util.Pattern java.text.Normalizer All subclasses
	 *      of java.text.Format All subclasses of javax.el.ELResolver
	 *      <P>
	 *      迭代器（Iterator）
	 *      <P>
	 *      All implementations of java.util.Iterator (thus among others
	 *      also java.util.Scanner!). All implementations
	 *      of java.util.Enumeration
	 *      <P>
	 *      中介者（Mediator）
	 *      <P>
	 *      java.util.Timer (all scheduleXXX() methods)
	 *      java.util.concurrent.Executor#execute()
	 *      java.util.concurrent.ExecutorService
	 *       (the invokeXXX() and submit() methods)
	 *      java.util.concurrent.ScheduledExecutorService
	 *       (all scheduleXXX() methods) java.lang.reflect.Method#invoke()
	 *      <P>
	 *      备忘录（Memento）
	 *      <P>
	 *      java.util.Date (the setter methods do that, Date is internally
	 *      represented by a long value) All implementations
	 *      of java.io.Serializable All implementations
	 *      of javax.faces.component.StateHolder
	 *      <P>
	 *      观察者（Observer or Publish/Subscribe）
	 *      <P>
	 *      java.util.Observer/java.util.Observable (rarely used in real world
	 *      though) All implementations of java.util.EventListener (practically
	 *      all over Swing thus) javax.servlet.http.HttpSessionBindingListener
	 *      javax.servlet.http.HttpSessionAttributeListener
	 *      javax.faces.event.PhaseListener
	 *      <P>
	 *      状态（State）
	 *      <P>
	 *      javax.faces.lifecycle.LifeCycle#execute() (controlled
	 *      by FacesServlet, the behaviour is dependent on current phase (state)
	 *      of JSF lifecycle)
	 *      <P>
	 *      策略（Strategy）
	 *      <P>
	 *      java.util.Comparator#compare(), executed by among
	 *      others Collections#sort(). javax.servlet.http.HttpServlet,
	 *      the service() and all doXXX() methods
	 *      take HttpServletRequest and HttpServletResponse and the implementor
	 *      has to process them (and not to get hold of them as instance
	 *      variables!). javax.servlet.Filter#doFilter()
	 *      <P>
	 *      模板（Template method）
	 *      <P>
	 *      All non-abstract methods
	 *      of java.io.InputStream, java.io.OutputStream, java
	 *      .io.Reader and java .io.Writer. All non-abstract methods
	 *      of java.util.AbstractList, java.util.
	 *      AbstractSet and java.util.AbstractMap.
	 *      javax.servlet.http.HttpServlet, all the doXXX() methods by default
	 *      sends a HTTP 405 "Method Not Allowed" error to the response. You're
	 *      free to implement none or any of them.
	 *      <P>
	 *      访问者（Visitor）
	 *      <P>
	 *      javax.lang.model.element.AnnotationValue and AnnotationValueVisitor
	 *      javax.lang.model.element.Element and ElementVisitor
	 *      javax.lang.model.type.TypeMirror and TypeVisitor
	 *      java.nio.file.FileVisitor and SimpleFileVisitor and Files#walkFileTree(Path, Set<FileVisitOption>, int, FileVisitor<? super Path>)
	 *      javax.faces.component.visit.VisitContext and VisitCallback
	 */
	public static void Behavioral() {
		java.lang.reflect.Method
	}



}
