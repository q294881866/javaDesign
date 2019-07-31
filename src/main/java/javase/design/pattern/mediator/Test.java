package javase.design.pattern.mediator;

public class Test {
	public static void main(String[] args) {  
        Mediator mediator = new MyMediator();  
        mediator.create();
        mediator.workAll();  
    }  
}
