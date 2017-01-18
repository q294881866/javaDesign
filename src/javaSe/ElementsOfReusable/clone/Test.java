package ElementsOfReusable.clone;

public class Test {

	
	public static void main(String[] args) throws CloneNotSupportedException {
		User u1= new User();
		u1.setName("ppf");
		User u= new User();
		u.setName("ppf2");
		u1.setU(u);
		
		User u2 = (User) u1.clone();
		
		System.out.println(u2.getU().getName());
		u.setName("pig");
		System.out.println(u2.getU().getName());
		
	}
}
