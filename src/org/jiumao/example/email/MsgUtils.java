package org.jiumao.example.email;

import java.io.IOException;

import javax.mail.MessagingException;


/**
 * ���ʹ�����Ϣ�ʼ���ָ����ַ 
 * <p>
 * 1.catchץȡ�����еĴ�����Ϣ <br>
 * 2.������Ϣ���� <br>
 * 3.���͵�ָ������<br>
 * 
 * @author ppf
 *
 */
public class MsgUtils {

    static String receiver = "294881866@qq.com";


    public static void sendErrorMsg(String error) {
        try {
            MailUtil.sendMail(receiver, "���ش�����Ϣ�뼰ʱ����", error);
        }
        catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }


    public static void sendInfoMsg(String info) {
        try {
            MailUtil.sendMail(receiver, "Ҳûɶ�£��������������", info);
        }
        catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }


    public static void sendRedMsg(String redMsg) {
        try {
            MailUtil.sendMail(receiver, "���㿴��������Ϣ�����ѱ���", redMsg);
        }
        catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }

}
