package javase.design.pattern.mediator;

/**
 * 中介者模式：解决类与类之间关联，
 * 类似spring容器
 *
 * @author Administrator
 */
public interface Mediator {
    /**
     * 创建中介者
     */
    public void create();

    /**
     * 全部工作
     */
    public void workAll();
}
