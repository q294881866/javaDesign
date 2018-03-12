package org.jiumao.example.ipsearch;

import java.io.File;

import org.jiumao.example.utils.io.FileUtil;

/**
 * 查询相关
 * @author ppf@jiumao.org
 * @date 2016年12月23日
 */
public class SearchAbout implements Search{
	String root ="F:";

	@Override
	public String getIndex(String para) {
		File[] fs =FileUtil.getFiles(root+para);
		return fs[0].getName();
	}

	@Override
	public boolean addIndex(String index,String para) {
		boolean flag= FileUtil.createDir(root+index, true);
		flag = FileUtil.createFile(root+index, para);
		return flag;
	}

	
}
