package javase.design.pattern.command;

public class MM {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void order(Boy b) {
		Command c1 = new ShoppingCommand();
		b.addCommand(c1);
		Command c2 = new HugCommand();
		b.addCommand(c2);
		Command c11 = new ShoppingCommand();
		b.addCommand(c11);
		Command c22 = new HugCommand();
		b.addCommand(c22);
		Command c13 = new ShoppingCommand();
		b.addCommand(c13);
		Command c23 = new HugCommand();
		b.addCommand(c23);
		Command c14 = new ShoppingCommand();
		b.addCommand(c14);
		Command c24 = new HugCommand();
		b.addCommand(c24);
		Command c15 = new ShoppingCommand();
		b.addCommand(c15);
		Command c25 = new HugCommand();
		b.addCommand(c25);
		
		
		b.executeCommands();
	}
}
