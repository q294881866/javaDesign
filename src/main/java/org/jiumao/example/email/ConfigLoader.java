package org.jiumao.example.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件发送配置信息加载类
 */
public class ConfigLoader {
    // 日志记录对象
    private static Logger log = LoggerFactory.getLogger(ConfigLoader.class);
    // 配置文件路径
    private static String mailPath = "mail/mail.properties";
    // 邮件发送SMTP主机
    private static String server;
    // 发件人邮箱地址
    private static String sender;
    // 发件人邮箱用户名
    private static String username;
    // 发件人邮箱密码
    private static String password;
    // 发件人显示昵称
    private static String nickname;
    // 发件人服务器端口
    private static String port;
    // 邮件服务器是否需要验证
    private static String auth;
    // 邮箱调试信息
    private static String debug;
    static {
        // 类初始化后加载配置文件
        InputStream in = ConfigLoader.class.getClassLoader()
                .getResourceAsStream(mailPath);
        Properties props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            log.error("load mail setting error,pleace check the file path:"
                    + mailPath);
            log.error(e.toString(), e);
        }
        server = props.getProperty("mail.server");
        sender = props.getProperty("mail.sender");
        username = props.getProperty("mail.username");
        password = props.getProperty("mail.password");
        nickname = props.getProperty("mail.nickname");
        port = props.getProperty("mail.port");
        auth = props.getProperty("mail.auth");
        debug = props.getProperty("mail.debug");
        log.debug("load mail setting success,file path:" + mailPath);
    }

    public static String getServer() {
        return server;
    }

    public static String getSender() {
        return sender;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getNickname() {
        return nickname;
    }

    public static void setMailPath(String mailPath) {
        ConfigLoader.mailPath = mailPath;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        ConfigLoader.port = port;
    }

    public static String getAuth() {
        return auth;
    }

    public static void setAuth(String auth) {
        ConfigLoader.auth = auth;
    }

    public static String getDebug() {
        return debug;
    }

    public static void setDebug(String debug) {
        ConfigLoader.debug = debug;
    }


}
