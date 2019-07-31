package javase.design.pattern.adapter;
/**
 * 适配器
 * @author Administrator
 */
public class  TargetableImpl implements ITargetable{
    ISource source = new SourceImpl();
    public void 接通电源() {
        source.接通电源();
    }
    public void method2() {
        System.out.println("method2");
    }
}
