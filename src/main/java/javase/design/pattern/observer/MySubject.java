package javase.design.pattern.observer;


public class MySubject extends AbstractSubject{
	@Override  
    public void operation() {  
        System.out.println("go with girls");  
        notifyObservers();  
    }  
}
