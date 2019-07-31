package javase.basic;

/**
 * Java�е�null
 * 
 * @author ppf@jiumao.org
 * @date 2016��12��21��
 */
public class NullInJava {
    
    public static void main(String[] args) {
        NullInJava e =null;
        e.sysoNull();//�������
        e.testNull();//NullPointerException
    }
    
    public void testNull() {
        System.out.println("�Ǿ�̬����");
    }
    public static void sysoNull() {
        System.out.println("��̬����");
    }

    public static void object() {
        //��������nullֵ
        String s = null; 
        Integer integer = null; 
        Double dbl = null; 
        //null ��Ϊֵ���Ը�ֵ��������������
        s = (String) null; 
        integer = (Integer) null; 
        dbl = (Double) null; 
        //null���������������͵�ʵ��
        System.out.println(integer instanceof Integer);

        
        //��������
        int i = 0;//null;//���󣬻�������û�п�ֵ��ֻ��Ĭ��ֵ
        Integer inter= 56;//�Զ���int����56װ��ΪInteger����
        i=inter;//�Զ������Integer����תΪint����
        double d = dbl;//dbl=null,��������d=null�ĸ���
        
    }
}
