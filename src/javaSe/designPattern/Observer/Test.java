package javaSe.designPattern.Observer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


class WakenUpEvent {
	private long time;
	private String loc;
	private Child source;
	
	public WakenUpEvent(long time, String loc, Child source) {
		super();
		this.time = time;
		this.loc = loc;
		this.source = source;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public Child getSource() {
		return source;
	}
	public void setSource(Child source) {
		this.source = source;
	}
}

class Child implements Runnable {
	private List<WakenUpListener> wakenUpListeners = new ArrayList<WakenUpListener>();

	public void addWakenUpListener(WakenUpListener l) {
		wakenUpListeners.add(l);
	}
	
	void wakeUp() {
		for(int i=0; i<wakenUpListeners.size(); i++) {
			WakenUpListener l = wakenUpListeners.get(i);
			l.ActionToWakenUp(new WakenUpEvent(System.currentTimeMillis(), "bed", this));
		}
	}

	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.wakeUp();
	}
	
}

class Dad implements WakenUpListener {

	public void ActionToWakenUp(WakenUpEvent wakenUpEvent) {
		System.out.println("feed child");
	}
	
}

class GrandFather  implements WakenUpListener {

	public void ActionToWakenUp(WakenUpEvent wakenUpEvent) {
		System.out.println("hug child");
	}
	
}

class Dog implements WakenUpListener {

	public void ActionToWakenUp(WakenUpEvent arg0) {
		System.out.println("wang wang ...");
	}
	
}

interface WakenUpListener {
	public void ActionToWakenUp(WakenUpEvent wakenUpEvent);
}

public class Test {
	public static void main(String[] args) {
		Child c = new Child();
		
		
		String[] observers = PropertyMgr.getProperty("observers").split(",");

		for(String s : observers) {
			try {
				c.addWakenUpListener((WakenUpListener)(Class.forName(s).newInstance()));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		new Thread(c).start();
	}
}

class PropertyMgr {
	private static Properties props = new Properties();
	
	static {
		try {
			props.load(Test.class.getClassLoader().getResourceAsStream("com/bjsxt/dp/observer/observer.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}

class CryEvent {
}

abstract class Event {

}
