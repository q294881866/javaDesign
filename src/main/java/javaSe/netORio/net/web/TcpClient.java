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
        
        try(Socket s = new Socket(InetAddress.getLocalHost().getHostAddress(), 10002);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());){

            User u = new User("jiumao");
            u.setBirthday(new Date());
            oos.writeObject(u);
        }catch (Exception e) {
        }
    }
}

