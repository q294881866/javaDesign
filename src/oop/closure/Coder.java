package oop.closure;

interface Worker {
	void work();
}

class Programmer implements Worker{
	@Override
	public void work() {
		System.out.println("程序设计");
	}
}

class Coder extends Programmer{
	private String get = "加班 coding！";
	private class BlackTech implements Worker{
		@Override
		public void work() {
			//内部可以访问外部类的私有成员
			System.out.println(get);
		}
	}
	
	public Worker getBlackTech(){
		//通过接口引用私有子类实例
		return new BlackTech();
	}
	public static void main(String[] args) {
		new Coder().work();
		new Coder().getBlackTech().work();
	}
}



