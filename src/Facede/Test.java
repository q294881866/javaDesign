package Facede;

import static org.junit.Assert.*;

/**
 * ����������ģʽ����ȱ��
 * @author Administrator
 *
 */
public class Test {

	@org.junit.Test
	public void chuanTong() throws Exception {
		new CPU().start();
		new Memory().open();
		new Disk().on();
		
		
		new CPU().shutdown();
		new Memory().shutdown();
		new Disk().shutdown();
	}
	
	@org.junit.Test
	public void facedetest() throws Exception {
		//start
		Common c = new Common();
		c.start();
		
		c.shutdown();
	}
}
