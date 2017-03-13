package javaSe.basic.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test4junit {
	
	@BeforeClass//�����з���֮ǰִ��
	public void globaInit(){
		System.out.println("init all method-------");
	}
	@AfterClass//�����з���֮��ִ��
	public void globaFinal(){
		System.out.println("after all method-------");
	}
	
	@Before//��ÿ�����Է���֮ǰִ��
	public void setUp(){
		System.out.println("start before method------------");
	}
	
	@After//��ÿ�����Է���֮��ִ��
	public void tearDown(){
		System.out.println("start after method");
		
	}
	
	@Test(timeout=50)// ���ò�������ʱ�� ��������򷵻ش���
	public void testMax(){
		System.out.println("testMax method---------------");
		int result = Math.max(2, 3);
		
	}
	
	@Test
	public void testMin() throws Exception{
		System.out.println("testMin method------------ ");
		int result = Math.max(2, 3);
	}
	
	@Test
	public void testFloor(){
		System.out.println("testFloor method-------------");
		int result = (int) Math.floor(5.36);
	}
	
	@Test(expected = Exception.class )
	public void testDivision(){
		System.out.println("testDivision method--------------");
		fail("��������ʧ��");
	}
	
	public static void main(String[] args) {
		
	}
}
