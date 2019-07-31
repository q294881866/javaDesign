package javase.design.pattern.interpreter;
/**
 * 解释器模式：
 * 用来做各种各样的解释器
 * 如正则表达式解释器
 * @author Administrator
 *
 */
public interface Expression {
	int interpret(Context context);
}
