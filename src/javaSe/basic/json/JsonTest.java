package javaSe.basic.json;

import java.lang.Throwable;
import java.lang.reflect.Field;
import java.util.Arrays;

import sun.misc.*;

public class JsonTest {
	static class Json {
		String v = "{src_root}/index\\.php\\?qa=\\d+\\&qa_1=.+";
	}

	private static int byteArrayBaseOffset;

	public static void main(String[] args) throws Exception {
		Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafe.setAccessible(true);
		Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
		System.out.println(UNSAFE);

		byte[] data = new byte[10];
		System.out.println(Arrays.toString(data));
		byteArrayBaseOffset = UNSAFE.arrayBaseOffset(byte[].class);

		System.out.println(byteArrayBaseOffset);
		UNSAFE.putByte(data, byteArrayBaseOffset, (byte) 1);
		UNSAFE.putByte(data, byteArrayBaseOffset + 5, (byte) 5);
		System.out.println(Arrays.toString(data));
	}
}
