package org.jiumao.example.utils.textFilter.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jiumao.example.utils.io.FileUtil;
import org.jiumao.example.utils.io.MyFileFilterUtil;
import org.jiumao.example.utils.textFilter.Format;

public class CopyOfTextUtil {
	
	public static final int READ_LINE=0;
	public static final int FIXED_LENGTH=1;
	public static final int FILE=2;

//	private final static  int INIT_SIZE = 100*1024*1024;
//	static ByteBuffer in ;
//	static CharBuffer out;
//	static{
//		Charset charset = Charset.forName("UTF-8");
//		CharsetDecoder decoder = charset.newDecoder();
//		in = ByteBuffer.allocate(INIT_SIZE);
//		out=CharBuffer.allocate((int) (INIT_SIZE* decoder.averageCharsPerByte()));
//	}
	/**
	 * 按文件夹，选择过滤方式
	 * @param dirPath 文件夹
	 * @param formats 过滤集
	 * @param processMode 选择处理文本的方式
	 * @return
	 */
	public static List<String> filterByDir(String dirPath,List<Format> formats,int processMode) {
		List<String> ls= new ArrayList<>();
		File[] files = FileUtil.getFiles(dirPath);
		for (File file : files) {
			ls.addAll(filterByFile(file, formats,processMode));
		}
		return ls;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> filterByFile(File file,List<Format> formats,int processMode) {
		switch (processMode) {
		case READ_LINE:
			return (List<String>) MyFileFilterUtil.doWithReadLine(file, formats);
//		case FILE:
//			return (List<String>) MyFileFilterUtil.doWithFile(new RandomAccessFile(file, "rw").getChannel(), formats, in, out);
//		case FIXED_LENGTH:
//			return (List<String>) MyFileFilterUtil.doWithFixedLength(10000, formats, new RandomAccessFile(file, "rw").getChannel());
		default:
			break;
		}
		return null;
	}
	
//	public static List<String> filterByDir(String dirPath,List<Format> formats) {
//		List<String> ls= new ArrayList<>();
//		File[] files = FileUtil.getFiles(dirPath);
//		for (File file : files) {
//			ls.addAll(filterByFile(file, formats,processMode));
//		}
//		return ls;
//	}
	
	public static List<String> isMac(String dirPath,List<Format> formats) {
		return filterByDir(dirPath, formats, READ_LINE);
	}

}


