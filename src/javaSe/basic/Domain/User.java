package javaSe.basic.Domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * JavaBean规范，任何类都可能是一个JavaBean
 * 符合set、get规范的Java类就是一个Javabean
 * 以后学到的spring bean工厂就是通过set、get方法注入参数的
 * User用户抽象类，一个典型的Javabean
 * 
 */
public class User implements Serializable{
	private int id;
	/**
	 * transient:非静态数据不想被序列化可以使用这个关键字修饰。 
	 */
	private transient String name;
	private Date birthday;
	private float money;
	private int age;
	private static final long serialVersionUID = 9527l;
	public User() {

	}

	public User(String name) {
		this.name = name;
	}

	public User(float money) {
		this.money = money;
	}

	public void showName() {
		System.out.println(this.name);
	}

	/**
	 * 著名的toString方法用户可以重写
	 */
	@Override //重写注解，返回值类型 方法名 参数数量和类型要完全一致可以理解直接覆盖了父类方法
	public String toString() {
		return "id=" + this.id + " name=" + this.name + " birthday="
				+ this.birthday + " money=" + this.money;
	}

	

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}
	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof User)) {
			return false;	
		}
		User user = (User)obj;
		//if(this.name==user.name && this.age==user.age)
		if(this.name.equals(user.name) 
			&& this.age==user.age) {
			return true;
		}
		else {
			return false;
		}
	}
	public int hashCode() {
		return name.hashCode() + age;
	}
	
	public Object clone()  {
		Object object = null;
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {}
		return object;
	}
}
