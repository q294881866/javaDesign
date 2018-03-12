package org.jiumao.example.utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jiumao.example.utils.strings.StringUtil;
import org.jiumao.example.utils.textFilter.Format;

/**
 * 文本文件过滤
 * @author ppf@jiumao.org
 * @date 2016年11月24日
 */
public class MyFileFilterUtil {

	/**
	 * 固定长度的字符串匹配在内存中处理,节约内存，文本大<BR>
	 * NIO
	 * 
	 * @param readBytes
	 *            如果是多个长度 ，传最长<br>
	 *            实际效果是内存越大性能越好
	 * @return
	 * @throws IOException
	 * <br>
	 *             这个会造成内容重复，存在多次匹配问题<br>
	 *             如果要求匹配结果无误，请使用
	 * @see MyFileFilterUtil#doWithReadLine 或者使用其它API
	 */
	public static List<?> doWithFixedLength(int readBytes,
			List<Format> formats, FileChannel inChannel) throws IOException {
		if (0 == readBytes) {
			return null;
		}
		
		// 读用到的数据
		ByteBuffer buf = ByteBuffer.allocate(readBytes);
		// 处理的数据用
		ByteBuffer tmp = ByteBuffer.allocate(readBytes * 2);
		// 上次数据
		ByteBuffer pre = ByteBuffer.allocate(readBytes);

		List es = new ArrayList<>();
		String s = "";
		while (inChannel.read(buf) != -1) {
			// 1.要处理的数据
			pre.flip();
			buf.flip();
			tmp.put(pre.array());
			tmp.put(buf.array());
			tmp.flip();
			s = StringUtil.byteBuffer2String(tmp);
			// 2.处理数据
			for (Format format : formats) {
				es.addAll((Collection) format.doWithFormat(s));
			}

			// 3.清空并保存上一次数据
			pre.clear();
			tmp.clear();
			buf.clear();
			pre.put(buf.array());
		}
		return es;
	}

	
	/**
	 * 按行处理数据<br>
	 * 
	 * @param file 文件
	 * @param formats 过滤集
	 * @return
	 */
	public static List<?> doWithReadLine(File file, List<Format> formats) {
		List es = null;
		String temp = "";
		try (//
		FileInputStream fis = new FileInputStream(file);//
				InputStreamReader isr = new InputStreamReader(fis);//
				BufferedReader br = new BufferedReader(isr);//
		) {
			es = new ArrayList<>();
			// 保存该行前面的内容
			for (; (temp = br.readLine()) != null;) {
				// 2.处理数据
				for (Format format : formats) {
					es.addAll((Collection) format.doWithFormat(temp));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return es;
	}

	/**
	 * 文本不大 建议使用<br>
	 * 全部load到内存
	 * 
	 * @param FileChannel
	 *            inChannel
	 * @param formats
	 * @param in
	 *            建议重复使用ByteBuffer 文件大小=申请的内存大小 保证in，out的容量装下文件
	 * @param out
	 *            这里会清空CharBuffer,ByteBuffer不需要关注。
	 * @return
	 * @throws IOException
	 */
	public static synchronized List<?> doWithFile(FileChannel inChannel,
			List<Format> formats,ByteBuffer in,CharBuffer out)
			throws IOException {
		if ( null == inChannel || null == formats
				|| formats.isEmpty()||null == in ||null==out)
			return null;
		//1.清空内存
		in.clear();
		out.clear();
		//2.读数据
		inChannel.read(in);
		in.flip();
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		decoder.decode(in, out, false);
		out.flip();
		// 3.处理数据
		String s = out.toString();
		List es = new ArrayList<>();

		for (Format format : formats) {
			es.addAll((Collection) format.doWithFormat(s));// 不要使用 trim()
		}
		return es;
	}
}
