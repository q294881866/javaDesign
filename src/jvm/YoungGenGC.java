package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError<br>
 * ��������java�Ѵ�С20m����Сֵ�����ֵһ�������Զ���չ�� 
 * ���ڴ����ʱDump����ǰ���ڴ��ת������
 * 
 * @author ppf@jiumao.org
 * @date 2017��1��10��
 */
public class YoungGenGC {
	public static void main(String[] args) {
		List<OOMObj> list = new ArrayList<OOMObj>();
		while (true) {
			list.add(new OOMObj());
		}
	}
}
