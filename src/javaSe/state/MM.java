package state;

public class MM {
	private String name;
	
	private MMState state = new MMHappyState();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void smile() {
		state.smile();
	}
	
	public void cry() {
		state.cry();
	}
	
	public void say() {
		state.say();
	}
	
}
