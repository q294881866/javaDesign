package org.jiumao.example.ipsearch;

import java.io.File;

/**
 * IP地址的相关操作
 * @author ppf@jiumao.org
 * @date 2016年12月23日
 */
public class IPAbout implements IP{

	
	public static void main(String[] args) {
		String s =new IPAbout().getIndex("8.1.2.16");
		System.out.println(s);
	}

	@Override
	public String getIndex(String ip) {
		char[] ips = new char[32];
		char tmp;
		int index=1;
		int countDot=0;
		int count = 0;
		int digit = 0;
//		1.将IP字符串转16进制或二进制整数。
//		2.切分为文件目录高位0去除
		for (int i = ip.length()-1; i >-1 ; i--) {
			tmp = ip.charAt(i);
			if (tmp>='0'&&tmp<='9') {
				digit += ((int)tmp-48)*index;
				index*=10;
				count++;
				if (0==i) {
					ip2Binary(ips, countDot, digit);
					digit = 0;
					count = 0;
					index=1;
					countDot++;
				}
			}else {
				ip2Binary(ips, countDot, digit);
				digit = 0;
				count = 0;
				index=1;
				countDot++;
			}
		}
		StringBuilder builder = new StringBuilder();
		int k;
		for ( k=ips.length-1; k >-1; k--) {
			//1.去除最后的空
			if ('\0'==ips[k]) {
				continue;
			}else {
				break;
			}
		}
		
		//2.从头开始
		for (int j = 0; j < k+1; j++) {
			char c = ips[j];
			if ('1'==c) {
				builder.append(File.separator+c);
			}else {
				builder.append(File.separator+'0');
			}
		}
		
		return builder.toString();

	}

	private void ip2Binary(char[] ips, int countDot, int digit) {
		String s = Integer.toBinaryString(digit);
		for (int j = s.length()-1,k=0; j >-1 && k<8 ; j--) {
			ips[countDot*8+k]=s.charAt(j);
			k++;
		}
	}
}
