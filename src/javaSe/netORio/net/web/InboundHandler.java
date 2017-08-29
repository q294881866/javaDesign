package javaSe.netORio.net.web;

import java.net.Socket;

public interface InboundHandler<T> {
    
   T handler(Socket s);

}
