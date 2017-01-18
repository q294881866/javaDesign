package proxy;
import java.lang.reflect.Method;
public class $Proxy1 implements proxy.Moveable{
    public $Proxy1(InvocationHandler h) {
        this.h = h;
    }
    proxy.InvocationHandler h;
@Override
public void move() {
    try {
    Method md = proxy.Moveable.class.getMethod("move");
    h.invoke(this, md);
    }catch(Exception e) {e.printStackTrace();}
}}