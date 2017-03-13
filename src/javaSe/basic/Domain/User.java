package javaSe.basic.Domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * JavaBean�淶���κ��඼������һ��JavaBean
 * ����set��get�淶��Java�����һ��Javabean
 * �Ժ�ѧ����spring bean��������ͨ��set��get����ע�������
 * User�û������࣬һ�����͵�Javabean
 * 
 */
public class User implements Serializable{
	private int id;
	/**
	 * transient:�Ǿ�̬���ݲ��뱻���л�����ʹ������ؼ������Ρ� 
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
	 * ������toString�����û�������д
	 */
	@Override //��дע�⣬����ֵ���� ������ ��������������Ҫ��ȫһ�¿������ֱ�Ӹ����˸��෽��
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
