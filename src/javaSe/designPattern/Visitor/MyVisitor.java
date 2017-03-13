package javaSe.designPattern.Visitor;

public class MyVisitor implements Visitor{

	 @Override  
	    public void visit(Subject sub) {  
	        System.out.println("visit the subject£º"+sub.getSubject());  
	    }


}
