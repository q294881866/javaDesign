package dom4Java;
/**
 * ��ȡ��·��
 * ���뵱ǰ�ļ����·��
 * �����ļ�����·��
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Classpath {
	public Classpath() {
	}
	public String path(String relativePath) throws IOException{
		URL base = this.getClass().getResource("");
		String path = new File(base.getFile(),relativePath).getCanonicalPath();
		return path;
	}
	public static void main(String[] args) {
		Classpath classpath = new Classpath();
//		System.out.println(ClassLoader.getSystemResource(""));
		try {
			System.out.println(classpath.path("MyXml.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
