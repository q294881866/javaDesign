package org.jiumao.example.email;

import java.io.IOException;
import javax.mail.MessagingException;


/**
 * @author ppf
 */
public class MailUtil {

    public static void sendMail(String receiver, String subject, String maiBody)
            throws IOException, MessagingException {
        String server = ConfigLoader.getServer();
        String sender = ConfigLoader.getSender();
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        String nickname = ConfigLoader.getNickname();
        MailSender mail = new MailSender(server);
        mail.setNeedAuth(true);
        mail.setNamePass(username, password, nickname);
        mail.setSubject(subject);
        mail.setBody(maiBody);
        mail.setReceiver(receiver);
        mail.setSender(sender);
        mail.sendout();
    }


    public static void sendMailAndFile(String receiver, String subject, String filePath, String maiBody)
            throws IOException, MessagingException {
        String server = ConfigLoader.getServer();
        String sender = ConfigLoader.getSender();
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        String nickname = ConfigLoader.getNickname();
        MailSender mail = new MailSender(server);
        mail.setNeedAuth(true);
        mail.setNamePass(username, password, nickname);
        mail.setSubject(subject);
        mail.setBody(maiBody);
        mail.addFileAffix(filePath);
        mail.setReceiver(receiver);
        mail.setSender(sender);
        mail.sendout();
    }

}
