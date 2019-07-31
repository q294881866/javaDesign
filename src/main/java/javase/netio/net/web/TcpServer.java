package javase.netio.net.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;


public class TcpServer {
    private static final Semaphore TRAFFIC_POLICE = new Semaphore(3);


    public static void main(String[] args) throws Exception {
        main0(args);
    }


    private static void main0(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10002);

        shutdownPoint: while (true) {
            try {
                Socket s = ss.accept();
                IPPool.add(s.getInetAddress());
                TRAFFIC_POLICE.acquire();
                InboundHandler<Boolean> handler = new ObjectInboundHandler();
                Boolean status = handler.handler(s);
                System.out.println(status);
                TRAFFIC_POLICE.release();
                s.close();
            }
            catch (Exception e) {
                // bula bula bula ...
                if (e instanceof ShutdownException) {
                    break shutdownPoint;
                }
            }

        }

        ss.close();
    }

}
