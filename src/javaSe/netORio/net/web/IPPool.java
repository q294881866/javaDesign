package javaSe.netORio.net.web;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


/**
 * ���ӵ�IP��
 * <p>
 * �ж�IP���䣬���ֲ��ҺͲ�¡����
 * 
 * @author ppf@jiumao.org
 */
public final class IPPool {

    private static List<Integer> pool = new ArrayList<>();


    private static Integer ipV4ToInt(String ip) {
        return null;

    }


    private static void ipV4ToLong() {

    }


    public static void add(InetAddress inetAddress) {
        pool.add(ipV4ToInt(inetAddress.getHostAddress()));
    }


    public static boolean exist(String ip) {
        return false;
    }
}
