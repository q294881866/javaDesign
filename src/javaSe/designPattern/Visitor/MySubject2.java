package javaSe.designPattern.Visitor;

public class MySubject2 implements Subject{
	 	@Override  
	    public void accept(Visitor visitor) {  
	        visitor.visit(this);  
	    }  
	  
	    @Override  
	    public String getSubject() {  
	        return "change";  
	    }  
}
