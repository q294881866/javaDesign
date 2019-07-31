package javase.netio.file;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

/**
 * 文件、文件夹的创建、读写
 * 
 * @author ppf
 * @since 2017年3月2日
 */
public class FileDemo {
    /** 文件夹名称 */
    final static String dir = "test";
    final static String disk = "D:";
    final static String filename = "test.txt";
    final static String path = disk + File.separator + dir;
    final static String file = path + File.separator + filename;

    /** 注意：char类型与String类型 */
    @Test
    public void params() {
        String useString = File.separator + File.pathSeparator;
        String useChar = "" + File.separatorChar + File.pathSeparatorChar;
        System.out.println(useString.equals(useChar));
    }

    @Test
    public void createFileWithTmpFile() throws Exception {
        File f1 = new File(path + File.separator + dir + File.separator + filename);
        File f2 = new File(File.separator + filename);
        // 创建一个文件
        boolean b1 = f1.createNewFile();// 创建出来的是文件而不是文件夹
        boolean b2 = f2.createNewFile();// 创建出来的是文件而不是文件夹
        System.out.println(b1 + " || " + b2);
        // String 返回字符串
        System.out.println(f1.getAbsolutePath() + " || " + f2.getAbsolutePath() );
        //如果路径是空，返回根据系统参数user.dir当前用户路径
        System.out.println(new File("").getAbsolutePath().equals(System.getProperty("user.dir")));
        // File 返回文件
        System.out.println(f1.getAbsoluteFile() + " || " + f2.getAbsoluteFile());
        // 相对路径
        System.out.println(f1.getPath() + " || " + f2.getPath());
        
        File tmp = File.createTempFile("test", null, null);
        System.out.println(tmp.getAbsolutePath());
        tmp.deleteOnExit();

    }
    
    @Test
    public void charFile() throws Exception {
        File f = new File(file);
        Writer fw = new BufferedWriter(new FileWriter(f));
        fw.write("字节流文件读取");
        fw.close();

        Reader fr = new BufferedReader(new FileReader(f));
        int i=-1;
        while ((i = fr.read())!=-1) {
            System.out.print((char)i);
        }
        fr.close();
    }

}
