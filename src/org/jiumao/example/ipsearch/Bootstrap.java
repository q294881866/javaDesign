package org.jiumao.example.ipsearch;

/**
 * 操作入口
 * 
 * @author ppf@jiumao.org
 * @date 2016年12月23日
 */
public class Bootstrap {

	public static void main(String[] args) {
		long b = System.currentTimeMillis();
		IP ip = new IPAbout();
		Search search = new SearchAbout();
		int i = 100000;
		while (i>0) {
			String path = ip.getIndex("8.1.2.16");
			search.getIndex(path);
			i--;
		}
		long cost = System.currentTimeMillis() - b;
		System.out.println(cost);
	}
}
