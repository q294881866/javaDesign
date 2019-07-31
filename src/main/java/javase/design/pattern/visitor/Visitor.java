package javase.design.pattern.visitor;
/**
 * 访问者模式
 * 适用于数据结构相对稳定
 * 算法又易变化的系统
 * @author Administrator
 *
 */
public interface Visitor {
	void visit(Subject subject);
}
