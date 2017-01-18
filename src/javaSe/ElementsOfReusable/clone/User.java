package ElementsOfReusable.clone;

public class User implements Cloneable{

	private User u;
	private String name;
	public User() {
		System.out.println("新对象实例");
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		if(null!=this.u)
		this.u = (User) this.getU().clone();
		return super.clone();
	}
}
