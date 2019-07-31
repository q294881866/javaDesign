package javase.netio.net.protocol.example;

import java.io.BufferedReader;
import java.io.IOException;


public class ReaderService implements Runnable {

    private final BufferedReader r;
    private SocketHandler handler;


    public ReaderService(SocketHandler handler) {
        super();
        this.r = handler.in;
        this.handler = handler;
    }


    @Override
    public void run() {
        for (;;) {
            try {
                String res = r.readLine();

                String ret = new ReadHandler().handler(res);
                handler.write(ret);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 其它类中处理 ReadHandler类中处理
    public static String readHandler(String res) {
        return null;
    }

}
