package org.jiumao.example.ipsearch;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取城市，通过区号<br>
 * 1.写区号、城市键值对到文件
 * 2.读到内存中HashMap
 * @author ppf@jiumao.org
 * @date 2016年12月23日
 */
public class CTContent implements Content<Integer>{
	
	static final Map<Integer, String> cities= new HashMap<Integer, String>();
	
	static{
		//读到内存cities
		//just for test,这个类读者按需实现
		cities.put(010, "北京");
	}

	@Override
	public String getContent(Integer index) {
		return cities.get(index);
	}

}
