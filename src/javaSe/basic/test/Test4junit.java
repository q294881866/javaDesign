package javaSe.basic.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test4junit {
	
	@BeforeClass//在所有方法之前执行
	public void globaInit(){
		System.out.println("init all method-------");
	}
	@AfterClass//在所有方法之后执行
	public void globaFinal(){
		System.out.println("after all method-------");
	}
	
	@Before//在每个测试方法之前执行
	public void setUp(){
		System.out.println("start before method------------");
	}
	
	@After//在每个测试方法之后执行
	public void tearDown(){
		System.out.println("start after method");
		
	}
	
	@Test(timeout=50)// 设置测试运行时间 如果超出则返回错误
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
		fail("除法测试失败");
	}
	
	public static void main(String[] args) {
		
	}
}
