package Facede;
/**
 * ������ģʽ
 * @author Administrator
 *
 */
public class Common {

	public void start() {
		new CPU().start();
		new Memory().open();
		new Disk().on();
	}
	
	public void shutdown() {
		new CPU().shutdown();
		new Memory().shutdown();
		new Disk().shutdown();
	}
}
