package javase.design.pattern.observer;

public class Test1 {
	public static void main(String[] args) {  
        Subject sub = new MySubject();  
        sub.add(new Observer1());  
        sub.add(new Observer2());  
          
        sub.operation();  //动作
    } 
}
