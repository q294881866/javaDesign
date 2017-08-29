package javaSe.netORio.net.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

import javaSe.basic.Domain.User;


public class ObjectInboundHandler implements InboundHandler<Boolean> {

    private static final int SUCCESS_CODE = 0;


    @Override
    public Boolean handler(Socket s) {

        String tmp = s.getInetAddress().getHostAddress();
        System.out.println(tmp);

        try (ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                OutputStream out = s.getOutputStream();) {

            User u = (User) ois.readObject();
            
            System.out.println(u);

            out.write(SUCCESS_CODE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }
}
