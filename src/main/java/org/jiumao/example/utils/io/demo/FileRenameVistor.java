package org.jiumao.example.utils.io.demo;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.jiumao.example.utils.io.FileUtil;



public class FileRenameVistor {

    public static void main(String[] args) throws Exception {
        exec();
    }


    public static int comAge2(String fileName) {
        fileName = fileName.replace(".jpg", "");
        String[] bothInfo = fileName.split("_");
        if (bothInfo.length == 3) {
            String both = bothInfo[1].split("-")[0];
            int age = Integer.parseInt(bothInfo[2]) - Integer.parseInt(both);
            if (age < 10)
                age = 10;
            else if (age > 99) age = 99;
            return age;
        }
        System.err.println(fileName);
        return -1;
    }

    private static void exec() {
        AtomicInteger count = new AtomicInteger(578210);
        FileUtil.getListFiles("D:\\face DB",(f) -> {
            String fileName = f.getName();
            int age = comAge(fileName);
            if (age > 0) {
                int fNum = count.incrementAndGet();
                System.out.println(fileName);
                
                String ref = "E:\\faces\\" + (fNum / 10000) + "\\" + age + "_" + fNum + ".jpg";
                boolean over = false;
                for (int i = 0; i <3&& !over; i++) {
                    over = f.renameTo(new File(ref));
                }
                System.err.println(ref);
            }
        }, null);
       
    }

    private static int comAge(String fileName) {
        fileName = fileName.replace(".jpg", "");
        String[] bothInfo = fileName.split("_");
        if (bothInfo.length == 4) {
            String both = bothInfo[2].split("-")[0];
            int age = Integer.parseInt(bothInfo[3]) - Integer.parseInt(both);
            if (age < 10)
                age = 10;
            else if (age > 99) age = 99;
            return age;
        }
        System.err.println(fileName);
        return -1;
    }

}
