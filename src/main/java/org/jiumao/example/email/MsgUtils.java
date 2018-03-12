package org.jiumao.example.email;

import java.io.IOException;

import javax.mail.MessagingException;


/**
 * 发送错误信息邮件到指定地址 
 * <p>
 * 1.catch抓取过程中的错误信息 <br>
 * 2.错误信息整理 <br>
 * 3.发送到指定邮箱<br>
 * 
 * @author ppf
 *
 */
public class MsgUtils {

    static String receiver = "294881866@qq.com";


    public static void sendErrorMsg(String error) {
        try {
            MailUtil.sendMail(receiver, "严重错误信息请及时改正", error);
        }
        catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }


    public static void sendInfoMsg(String info) {
        try {
            MailUtil.sendMail(receiver, "也没啥事，就想跟你聊聊天", info);
        }
        catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }


    public static void sendRedMsg(String redMsg) {
        try {
            MailUtil.sendMail(receiver, "当你看到这条信息是我已崩溃", redMsg);
        }
        catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }

}
