package javaSe.adapter;
/**
 * ������
 * @author Administrator
 */
public class  TargetableImpl implements ITargetable{
	ISource source = new SourceImpl();
	public void ��ͨ��Դ() {
		source.��ͨ��Դ();
	}
	public void method2() {
		System.out.println("method2");
	}
}
