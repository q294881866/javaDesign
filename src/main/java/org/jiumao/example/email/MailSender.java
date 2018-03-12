package org.jiumao.example.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 邮件发送
 * 
 * @author ppf
 * @date 2017年8月16日
 */
public class MailSender {
    private MimeMessage mimeMsg;
    private Session session;
    private Properties props;
    private Multipart mp;
    private String username;
    private String password;
    private String nickname;
    private static Logger log = LoggerFactory.getLogger(MailSender.class);


    public MailSender(String smtp) {
        setSmtpHost(smtp);
        createMimeMessage();
    }


    public void setSmtpHost(String hostName) {
        if (props == null)
            props = System.getProperties();
        props.put("mail.smtp.host", hostName);
        log.debug("set system properties success ：mail.smtp.host= " + hostName);

    }


    public void createMimeMessage() {
        // 获得邮件会话对象
        session = SenderConfig.getSendMailSession();
        // 创建MIME邮件对象
        mimeMsg = new MimeMessage(session);
        mp = new MimeMultipart();
        log.debug(" create session and mimeMessage success");
    }


    public void setNeedAuth(boolean need) {
        if (props == null)
            props = System.getProperties();
        if (need) {
            props.put("mail.smtp.auth", "true");
        }
        else {
            props.put("mail.smtp.auth", "false");
        }
        log.debug("set smtp auth success：mail.smtp.auth= " + need);

    }


    public void setSubject(String subject) throws UnsupportedEncodingException, MessagingException {
        mimeMsg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
        log.debug("set mail subject success, subject= " + subject);

    }


    public void setBody(String mailBody) throws MessagingException {
        BodyPart bp = new MimeBodyPart();
        bp.setContent("" + mailBody, "text/html;charset=utf-8");
        mp.addBodyPart(bp);
        log.debug("set mail body content success,mailBody= " + mailBody);
    }


    /**
     * 添加邮件附件
     */
    public void addFileAffix(String filePath) throws MessagingException {
        BodyPart bp = new MimeBodyPart();
        FileDataSource fileds = new FileDataSource(filePath);
        bp.setDataHandler(new DataHandler(fileds));
        bp.setFileName(fileds.getName());
        mp.addBodyPart(bp);
        log.debug("mail add file success,filename= " + filePath);
    }


    public void setSender(String sender)
            throws UnsupportedEncodingException, AddressException, MessagingException {
        nickname = MimeUtility.encodeText(nickname, "utf-8", "B");
        mimeMsg.setFrom(new InternetAddress(nickname + " <" + sender + ">"));
        log.debug(" set mail sender and nickname success , sender= " + sender + ",nickname=" + nickname);
    }


    /**
     * 设置收件人邮箱地址
     */
    public void setReceiver(String receiver) throws AddressException, MessagingException {
        mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        log.debug("set mail receiver success,receiver = " + receiver);
    }


    /**
     * 设置抄送人的邮箱地址
     */
    public void setCopyTo(String copyto) throws AddressException, MessagingException {
        mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copyto));
        log.debug("set mail copyto receiver success,copyto = " + copyto);
    }


    /**
     * 设置发件人用户名密码进行发送邮件操作
     */
    public void sendout() throws MessagingException {
        mimeMsg.setContent(mp);
        mimeMsg.saveChanges();
        Session mailSession = Session.getInstance(props, null);
        Transport transport = mailSession.getTransport("smtp");
        transport.connect((String) props.get("mail.smtp.host"), username, password);
        transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
        transport.close();
        log.debug(" send mail success");
    }


    /**
     * 注入发件人用户名 ，密码 ，昵称
     */
    public void setNamePass(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

}
