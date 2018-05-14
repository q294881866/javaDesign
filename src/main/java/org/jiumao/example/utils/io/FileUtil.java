package org.jiumao.example.utils.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件相关操作
 * 
 * @author 作者：ppf@jiumao.org
 * @version 创建时间：2016年11月24日 下午1:37:01<br>
 *          标注：<br>
 * 
 * 
 */
public class FileUtil {

    /**
     * 
     * @param path
     * @return
     */
    public static boolean createFile(String path, String fileName) {
        File root = new File(path + File.separator + fileName);
        if (!root.exists()) {
            try {
                root.createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("文件已经存在");
        return false;
    }

    /**
     * 
     * @param path
     * @param ifNoCreate 如果父目录不存在则创建
     * @return
     */
    public static boolean createDir(String path, boolean ifNoCreate) {
        File root = new File(path);
        if (!root.exists()) {
            if (ifNoCreate) {
                root.mkdirs();
            } else {
                root.mkdir();
            }
            return true;
        }
        System.out.println("文件夹创建失败");
        return false;
    }



    /**
     * 得到某一路径下所有的文件, 不包含子目录
     * 
     * @param filePath
     * @return
     */
    public static File[] getFiles(String filePath) {
        File root = new File(filePath);
        return root.listFiles(new IsNotDirFilter());
    }

    /***
     * 获取指定目录下的所有的文件（包括子目录） 不包含文件夹
     * 
     * @param obj
     * @return
     */
    @SuppressWarnings("null")
    public static List<File> getListFiles(Object obj, Command fileCmd, Command dirCmd) {
        File file = null;
        if (obj instanceof File) {
            file = (File) obj;
        } else {
            file = new File(obj.toString());
        }
        List<File> files = new ArrayList<File>();

        if (!file.exists()) {
            throw new RuntimeException("文件或者目录不存在");
        }
        if (file.isFile()) {
            // files.add(file);
            if (null != fileCmd) fileCmd.exec(file);
        } else if (file.isDirectory() || file.canRead()) {
            if (null != dirCmd) dirCmd.exec(file);

            File[] fileArr = file.listFiles();
            if (null != fileArr) {
                for (File fileOne : fileArr) {
                    List<File> ls = getListFiles(fileOne, fileCmd, dirCmd);
                    if (null != ls || !ls.isEmpty()) files.addAll(ls);
                }
            }
        }
        return files;
    }


}


class IsNotDirFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        return !file.isDirectory();
    }

}
