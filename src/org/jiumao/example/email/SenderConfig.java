package org.jiumao.example.email;

import java.util.Properties;
import javax.mail.Authenticator;


public class SenderConfig {
    private static Properties properties;
    private static javax.mail.Session sendMailSession;

    static {
        properties = new Properties();
        properties.put("mail.smtp.host", ConfigLoader.getServer());
        properties.put("mail.smtp.port", ConfigLoader.getPort());
        properties.put("mail.smtp.auth", ConfigLoader.getAuth());
        properties.put("mail.debug", ConfigLoader.getDebug());
        Authenticator authenticator =
                new EmailAuthenticator(ConfigLoader.getUsername(), ConfigLoader.getPassword());
        sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
    }


    public static Properties getProperties() {
        return properties;
    }


    public static void setProperties(Properties properties) {
        SenderConfig.properties = properties;
    }


    public static javax.mail.Session getSendMailSession() {
        return sendMailSession;
    }


    public static void setSendMailSession(javax.mail.Session sendMailSession) {
        SenderConfig.sendMailSession = sendMailSession;
    }

}