package oop;

public class People implements Animal{

	@Override
	public void action() {
		System.out.println("依赖工具产生影响，总希望其他人能帮你做的很好");
	}

	@Override
	public void show() {
		System.out.println("哲学：把吃饭、睡觉、工作的时间尽量用在抽象方法论上");
	}

}
