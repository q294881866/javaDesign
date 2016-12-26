package dom4Java;
/**
 * 获取类路径
 * 输入当前文件相对路径
 * 返回文件绝对路径
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
