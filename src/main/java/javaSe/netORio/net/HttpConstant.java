package javaSe.netORio.net;


/**
 * @author peipengfei
 * @date 2018/8/13
 */
public class HttpConstant {


    private HttpConstant() {
    }

    /**
     * 保持连接
     */
    public static final boolean KEEP_ALIVE = true;

    public static final long HTTP_MAX_LENGTH = 2147483647L;

    /**
     * 异步http NIO缓冲区
     */
    public static final int BUFFER_SIZE = 8 * 1024;

    /**
     * 连接请求的超时时间 5s
     */
    public static final int HTTP_CONNECT_TIMEOUT = 5 * 1000;

    /**
     * socket操作请求的超时时间 10s
     */
    public static final int HTTP_SOCKET_TIMEOUT = 10 * 1000;

    /**
     * 从连接池获取链接的等待超时时间 2s
     */
    public static final int HTTP_CONNECT_REQUEST_TIMEOUT = 2 * 1000;

    /**
     * 最大连接数
     */
    public static final int HTTP_MAX_TOTAL = 200;

    /**
     * 默认的每个host的链接数
     */
    public static final int DEFAULT_MAX_PER_ROUTE = 20;

    /**
     * HTTPS头协议
     */
    public static final String HTTPS_SCHEME = "https";

    /**
     * HTTP头协议
     */
    public static final String HTTP_SCHEME = "http";

}
