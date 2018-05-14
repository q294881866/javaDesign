package org.jiumao.example.utils.io.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jiumao.example.utils.io.FileUtil;

public class ExecShell {

    public static void main(String[] args) throws Exception {
        final String exePath = "cmd /c start D:\\360\\app\\QsFaceScore.exe ";
        AtomicBoolean flag = new AtomicBoolean(false);
        FileUtil.getListFiles("E:\\faces", null, (dir) -> {
            String dirPath = dir.getAbsolutePath();
            if (!flag.get()) {
                flag.set(true);
            } else {
                dirPath = exePath + dirPath + "\\";
                try {
                    System.out.println(dirPath);
                    execCMD(dirPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void execCMD(String strCmd) throws Exception {
        Process process = Runtime.getRuntime().exec(strCmd);

        BufferedReader strCon = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = strCon.readLine()) != null) {
            System.out.println("java print:" + line);
        }
    }
}
