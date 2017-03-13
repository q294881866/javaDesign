package oop.closure;

interface Worker {
	void work();
}

class Programmer implements Worker{
	@Override
	public void work() {
		System.out.println("�������");
	}
}

class Coder extends Programmer{
	private String get = "�Ӱ� coding��";
	private class BlackTech implements Worker{
		@Override
		public void work() {
			//�ڲ����Է����ⲿ���˽�г�Ա
			System.out.println(get);
		}
	}
	
	public Worker getBlackTech(){
		//ͨ���ӿ�����˽������ʵ��
		return new BlackTech();
	}
	public static void main(String[] args) {
		new Coder().work();
		new Coder().getBlackTech().work();
	}
}



