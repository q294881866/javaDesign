package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError<br>
 * 参数意义java堆大小20m，最小值和最大值一样避免自动扩展。 
 * 当内存溢出时Dump出当前的内存堆转储快照
 * 
 * @author ppf@jiumao.org
 * @date 2017年1月10日
 */
public class YoungGenGC {
	public static void main(String[] args) {
		List<OOMObj> list = new ArrayList<OOMObj>();
		while (true) {
			list.add(new OOMObj());
		}
	}
}
