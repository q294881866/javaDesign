package javase.basic.dataStructure.collection;
/**
 * 模拟重构java数据结构<br>
 * 集合的顶级接口
 * @author ppf@jiumao.org
 * @date 2016年12月13日 <br>
 */
public interface/*接口关键字*/ Collection {
		void add(Object object);//默认是public abstract
		public abstract int size();//等同于int size()
		Iterator iterator();
}
