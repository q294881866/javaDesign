package javase.basic;

/**
 * Java  null
 * 
 * @author ppf@jiumao.org
 * @date 2016/12/21
 */
public class NullInJava {
    
    public static void main(String[] args) {
        object();
        NullInJava e =null;
        e.sysoNull();
        //NullPointerException
        e.testNull();


    }
    
    public void testNull() {
        System.out.println("testNull");
    }
    public static void sysoNull() {
        System.out.println("sysoNull");
    }

    public static void object() {
        String s = null;
        Integer integer = null; 
        Double dbl = null; 
        s = (String) null;
        integer = (Integer) null; 
        dbl = (Double) null; 
        System.out.println(integer instanceof Integer);

        
        int i = 0;
        Integer inter= 56;
        i=inter;
        double d = dbl;
        System.out.println(d);
    }
}
