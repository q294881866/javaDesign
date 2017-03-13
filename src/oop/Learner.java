package oop;

public class Learner implements Animal{
	private Action act; //不同的行动
	private Art art;//不同的表现力
	public Learner(Action act,Art art) {
		this.act= act; this.art= art;
	}
	@Override
	public void action() { act.action(); }
	@Override
	public void show() { art.show(); }
}
