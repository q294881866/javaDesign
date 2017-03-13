package javaSe.designPattern.Facede;


/**
 * 测试外观设计模式的优缺点
 * @author Administrator
 *
 */
public class Test {

	public void chuanTong() throws Exception {
		new CPU().start();
		new Memory().open();
		new Disk().on();
		
		
		new CPU().shutdown();
		new Memory().shutdown();
		new Disk().shutdown();
	}
	
	public void facedetest() throws Exception {
		//start
		Common c = new Common();
		c.start();
		
		c.shutdown();
	}
}
