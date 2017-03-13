package javaSe.designPattern.Visitor;

public class Test {
	public static void main(String[] args) {  
        Visitor visitor = new MyVisitor();  
        Subject sub = new MySubject();  
        Subject sub2 = new MySubject2();  
        sub.accept(visitor); 
        sub2.accept(visitor);
    }  
}
