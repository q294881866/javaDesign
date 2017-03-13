package javaSe.special.sugar;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 
 * @author ppf@jiumao.org
 * @date 2017Äê1ÔÂ25ÈÕ
 */
public class InnerORAutoTry {

	public class Inner{}
	
	public static class StaticInner{}
	
	public static void main(String[] args) {
		//Stream implements Closeable
		try(InputStream in = new FileInputStream(new File(""))) 
		{new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Anonymous Inner Class");
			}
		}).start(); } 
		catch (Exception e) { }
	}
}

class Another{}
