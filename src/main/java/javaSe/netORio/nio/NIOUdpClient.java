package javaSe.netORio.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class NIOUdpClient {
    
    public static void main(String[] args) throws Exception{
        DatagramChannel channel = DatagramChannel.open();
        SocketAddress target = new InetSocketAddress(11111);
//      channel.connect(target);
//      channel.write(ByteBuffer.wrap("write".getBytes()));
        ByteBuffer dst = ByteBuffer.allocate(512);
//      channel.read(dst);
//      channel.disconnect();
        
        dst.putChar('！');
        channel.send(dst, target);
        dst.clear();
        channel.receive(dst);
        
        System.err.println(Charset.defaultCharset().decode(dst).toString());
        
        channel.close();
    }

}
