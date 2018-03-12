package javaSe.netORio.net.web;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

import javaSe.basic.Domain.User;

public class TcpClient {
	public static void main(String[] args) throws Exception {
	    main0(args);
	}

    private static void main0(String[] args) {
        Socket s;
        try {
            s = new Socket(InetAddress.getLocalHost().getHostAddress(), 10002);
        }
        catch (IOException e) {
           throw new RuntimeException(e);
        }
        
        try(ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());){
            User u = new User("jiumao");
            u.setBirthday(new Date());
            oos.writeObject(u);
        }catch (Exception e) {
        }finally {
            try {
                s.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
