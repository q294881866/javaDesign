package javaSe.netORio.net.web;

import com.youzan.http.impl.nio.client.CloseableHttpAsyncClient;
import com.youzan.http.impl.nio.client.HttpAsyncClients;
import com.youzan.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import com.youzan.http.nio.conn.NoopIOSessionStrategy;
import com.youzan.http.nio.conn.SchemeIOSessionStrategy;
import com.youzan.http.nio.conn.ssl.SSLIOSessionStrategy;
import javaSe.netORio.net.HttpConstant;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步http请求
 * <pre>
 *     <code>{@link #setBasicAuth(String, int, String, String)}.{@link #proxy(String, int)}.{@link #create()}.start()</code>
 * </pre>
 *
 * @author peipengfei
 * @date 2018/9/4
 */
public class AsyncHttpSupport {

    @Getter
    private Map<String, String> headers = new HashMap<>(8);
    private CloseableHttpAsyncClient httpClient;


    /**
     * 日志的长度，负数表示不限制长度
     */
    private int logMaxLength = -1;

    /**
     * 最大连接数
     */
    private int maxTotal = HttpConstant.HTTP_MAX_TOTAL;

    /**
     * 默认的每个host的链接数
     */
    private int defaultMaxPerRoute = HttpConstant.DEFAULT_MAX_PER_ROUTE;

    /**
     * 从连接池获取链接的等待超时时间
     */
    private int connectionRequestTimeout = HttpConstant.HTTP_CONNECT_REQUEST_TIMEOUT;

    /**
     * 建立链接的超时时间
     */
    private int connectTimeout = HttpConstant.HTTP_CONNECT_TIMEOUT;

    /**
     * 请求的超时时间
     */
    private int socketTimeout = HttpConstant.HTTP_SOCKET_TIMEOUT;

    /**
     * 默认是长连接
     */
    private boolean keepAlive = HttpConstant.KEEP_ALIVE;

    /**
     * 默认8k
     */
    private int bufferSize = HttpConstant.BUFFER_SIZE;

    @Getter
    private PoolingNHttpClientConnectionManager connManager;
    @Getter
    private CredentialsProvider credentialsProvider;
    @Getter
    private RequestConfig defaultRequestConfig;

    private TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    // don't check
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    // don't check
                }
            }
    };


    public AsyncHttpSupport() {

        try {
            this.create().start();
        } catch (Exception e) {
            throw new RuntimeException("[HttpClient创建异常]", e);
        }
    }

    public void connManager() throws Exception {
        // Create I/O reactor configuration
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(keepAlive)
                .build();

        Registry<SchemeIOSessionStrategy> registry = RegistryBuilder.<SchemeIOSessionStrategy>create()
                .register("http", NoopIOSessionStrategy.INSTANCE)
                .register("https", new SSLIOSessionStrategy(SSLContexts.createDefault()))
                .build();

        // Create a custom I/O reactort
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);

        // Create a connection manager with custom configuration.
        connManager = new PoolingNHttpClientConnectionManager(ioReactor, registry);

        // Create connection configuration
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .setBufferSize(bufferSize).build();
        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setMaxTotal(maxTotal);
        connManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

    }


    public AsyncHttpSupport setBasicAuth(String host, int port, String userName, String password) {
        credentialsProvider.setCredentials(
                new AuthScope(host, port),
                new UsernamePasswordCredentials(userName, password));

        return this;
    }


    public AsyncHttpSupport proxy(String host, int port) {
        defaultRequestConfig = RequestConfig
                .copy(defaultRequestConfig)
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setProxy(new HttpHost(host, port))
                .build();

        return this;
    }


    public CloseableHttpAsyncClient create() throws Exception {


        defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();

        connManager();

        return httpClient = HttpAsyncClients.custom()
                .setConnectionManager(connManager)
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
    }


    public void addDefaultHttpHeader(String key, String value) {
        headers.put(key, value);
    }

    public Future<HttpResponse> get(String url, FutureCallback<HttpResponse> callback) {

        HttpGet get = new HttpGet(url);
        headers.forEach((key, value) -> {
            get.setHeader(key, value);
        });

        return httpClient.execute(get, callback);
    }


    /**
     * 请求格式form
     *
     * @param url
     * @param callback
     * @param params
     * @param encoding
     * @return
     */
    public Future<HttpResponse> post(String url, FutureCallback<HttpResponse> callback,
                                     List<NameValuePair> params, String encoding) {
        HttpPost post = new HttpPost(url);
        headers.forEach((key, value) -> {
            post.setHeader(key, value);
        });
        HttpEntity entity = new UrlEncodedFormEntity(params, getEncode(encoding));
        post.setEntity(entity);

        return httpClient.execute(post, callback);
    }

    public Future<HttpResponse> post(String url, FutureCallback<HttpResponse> callback,
                                     String json, String encoding) {

        HttpPost post = new HttpPost(url);
        headers.forEach((key, value) -> {
            post.setHeader(key, value);
        });
        post.setHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        post.setEntity(new ByteArrayEntity(json.getBytes(getEncode(encoding))));


        return httpClient.execute(post, callback);
    }

    /**
     * 原始post
     *
     * @param post
     * @param callback
     * @return
     */
    public Future<HttpResponse> post(HttpPost post, FutureCallback<HttpResponse> callback) {
        return httpClient.execute(post, callback);
    }

    public Future<HttpResponse> post(String url, String json) {
        return post(url, null, json, null);
    }


    public void close() {
        if (httpClient.isRunning()) {
            try {
                httpClient.close();
            } catch (IOException e) {
                // doNothing Asynchttpclient close failed
            }
        }
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }


    public static Charset getEncode(String encoding) {
        if (StringUtils.isBlank(encoding)) {
            return Consts.UTF_8;
        }
        return Charset.isSupported(encoding) ? Charset.forName(encoding)
                : Consts.UTF_8;
    }


    /**
     * 获取http请求数据
     *
     * @param responseFuture 异步http请求返回结果
     * @param timeout        毫秒
     * @return
     */
    public static String parseResponse(Future<HttpResponse> responseFuture, int timeout) {
        try {
            return EntityUtils.toString(getEntity(responseFuture, timeout));
        } catch (IOException e) {
            // do nothing
        }
        return null;
    }

    /**
     * 获取http请求数据
     *
     * @param responseFuture 异步http请求返回结果
     * @param timeout        毫秒
     * @return
     */
    public static HttpEntity getEntity(Future<HttpResponse> responseFuture, int timeout) {
        try {
            if (!Objects.isNull(responseFuture)) {
                HttpResponse httpResponse = null;
                if (responseFuture.isDone()) {
                    httpResponse = responseFuture.get();
                }
                if (Objects.isNull(httpResponse)) {
                    httpResponse = responseFuture.get(timeout, TimeUnit.MILLISECONDS);
                }

                return httpResponse.getEntity();
            }
        } catch (Exception e) {
            // do nothing
        }
        return null;
    }
}
