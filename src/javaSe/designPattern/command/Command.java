package javaSe.designPattern.command;
/**
 * 参考线程用的就是命令方法
 * @author Administrator
 *
 */
public abstract class Command {
	public abstract void execute();
	public abstract void unDo();
}
