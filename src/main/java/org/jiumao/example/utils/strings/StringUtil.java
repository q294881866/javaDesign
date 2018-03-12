package org.jiumao.example.utils.strings;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;

import sun.nio.cs.ArrayDecoder;


public class StringUtil {

	public static String byteBuffer2String(ByteBuffer buffer) {
		CharBuffer charBuffer = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();// 新内存
			charBuffer = decoder.decode(buffer);
			buffer.flip();
			return charBuffer.toString();// 新内存again
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * equals new String(byte[],charsetName);
	 */
	@Deprecated
	public static char[] byte2String(byte[] bs, Charset cs, int off, int len) {
		CharsetDecoder cd = cs.newDecoder();
		int en = (int) (len * (double) cd.maxCharsPerByte());
		char[] ca = new char[en];
		if (len == 0)
			return ca ;
		boolean isTrusted = cs.getClass().getClassLoader() == null;
		if (cd instanceof ArrayDecoder) {
			int clen = ((ArrayDecoder) cd).decode(bs, off, len, ca);
			return safeTrim(ca, clen, cs, isTrusted);
		} else {
			cd.reset();
			ByteBuffer bb = ByteBuffer.wrap(bs, off, len);
			CharBuffer cb = CharBuffer.wrap(ca);
			try {
				CoderResult cr = cd.decode(bb, cb, true);
				if (!cr.isUnderflow())
					cr.throwException();
				cr = cd.flush(cb);
				if (!cr.isUnderflow())
					cr.throwException();
			} catch (CharacterCodingException x) {
				// Substitution is always enabled,
				// so this shouldn't happen
				throw new Error(x);
			}
			return safeTrim(ca, cb.position(), cs, isTrusted);
		}

	}

	private static char[] safeTrim(char[] ca, int len, Charset cs,
			boolean isTrusted) {
		if (len == ca.length
				&& (isTrusted || System.getSecurityManager() == null))
			return ca;
		else
			return Arrays.copyOf(ca, len);
	}

}
