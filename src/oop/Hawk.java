package oop;
/**
 * 一直会唱京剧的老鹰
 */
public class Hawk extends BigBird{
	Opera opera = new Opera();
	@Override
	public void show() { opera.show(); }
}
