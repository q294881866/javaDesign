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
	private class BlackTech implements Worker{
		@Override
		public void work() {
			System.out.println("加班 coding！");
		}
	}
	
	public Worker getBlackTech(){
		//通过接口引用私有子类实例
		return new BlackTech();
	}
	public static void main(String[] args) {
		new Coder().getBlackTech().work();
	}
}



