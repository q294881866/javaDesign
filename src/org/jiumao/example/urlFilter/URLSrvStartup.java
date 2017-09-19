package org.jiumao.example.urlFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class URLSrvStartup {
    private static Properties config = null;
    private static final String configPath = MixAll.projectPath + "URLSrv.properties";

    static {
        config = new Properties();
        try (InputStream inStream = new FileInputStream(new File(configPath));) {
            config.load(inStream);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        String home = config.getProperty(MixAll.USR_HOME);// µ±Ç°Â·¾¶
        System.setProperty(MixAll.URLSRV_HOME, null == home ? MixAll.projectPath : home);
    }


    public static void startup() {
    }


    public static void main(String[] args) {
        startup();
    }
}
