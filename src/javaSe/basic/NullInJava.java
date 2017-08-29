package javaSe.basic;

/**
 * Java中的null
 * 
 * @author ppf@jiumao.org
 * @date 2016年12月21日
 */
public class NullInJava {
    
    public static void main(String[] args) {
        NullInJava e =null;
        e.sysoNull();//正常输出
        e.testNull();//NullPointerException
    }
    
    public void testNull() {
        System.out.println("非静态方法");
    }
    public static void sysoNull() {
        System.out.println("静态方法");
    }

    public static void object() {
        //引用类型null值
        String s = null; 
        Integer integer = null; 
        Double dbl = null; 
        //null 作为值可以赋值给任意引用类型
        s = (String) null; 
        integer = (Integer) null; 
        dbl = (Double) null; 
        //null不是任意引用类型的实例
        System.out.println(integer instanceof Integer);

        
        //基本类型
        int i = 0;//null;//错误，基本类型没有空值，只有默认值
        Integer inter= 56;//自动将int类型56装箱为Integer类型
        i=inter;//自动拆箱把Integer类型转为int类型
        double d = dbl;//dbl=null,避免隐含d=null的复制
        
    }
}
