package oop;

public class Learner implements Animal{
	private Action act;
	private Art art;
	public Learner(Action act,Art art) {
		this.act= act;
		this.art= art;
	}

	@Override
	public void action() {
		act.action();
	}

	@Override
	public void show() {
		art.show();
	}

}
