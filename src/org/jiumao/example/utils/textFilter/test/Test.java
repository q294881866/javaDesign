package org.jiumao.example.utils.textFilter.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jiumao.example.utils.io.FileUtil;
import org.jiumao.example.utils.io.MyFileFilterUtil;
import org.jiumao.example.utils.textFilter.Format;

public class Test {

	public static void main(String[] args) throws Exception {
		if (0 == args.length || args[0].isEmpty() || "".equals(args[0])) {
			args = new String[] { "D:\\test" };
		}

		File[] files = FileUtil.getFiles(args[0]);
		List<Format> formats = new ArrayList<>();
		
		formats.add(new MacFormatImpl());
		List<String> ls =isMac(args[0], formats );
		System.out.println(ls);
	}

	private static List<String> isMac(String dirPath, List<Format> formats) {
		List<String> ls = new ArrayList<>();
		File[] files = FileUtil.getFiles(dirPath);
		for (File file : files) {
			ls.addAll((Collection<? extends String>) MyFileFilterUtil.doWithReadLine(file, formats));
		}
		return ls;
	}

}
