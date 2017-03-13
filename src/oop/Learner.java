package oop;

public class Learner implements Animal{
	private Action act; //��ͬ���ж�
	private Art art;//��ͬ�ı�����
	public Learner(Action act,Art art) {
		this.act= act; this.art= art;
	}
	@Override
	public void action() { act.action(); }
	@Override
	public void show() { art.show(); }
}
